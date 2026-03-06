package shiro;

import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;

/**
 * Main entry point for the Shiro chatbot application.
 * Handles user input, command routing and interaction flow.
 */
public class Shiro {

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        Ui.printAdded(task, tasks.size());
    }

    private static String extractDescription(String input, String command) throws ShiroException {
        String description = input.substring(command.length());
        if (description.trim().isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
        return description;
    }

    private static void handleDeadline(ArrayList<Task> tasks, String input) throws ShiroException {
        String taskDetails = extractDescription(input, "deadline");
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2) {
            throw new ShiroException("☹ OOPS!!! The deadline must have a /by time.");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (by.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The /by time of a deadline cannot be empty.");
        }
        Task deadline = new Deadline(description, by);
        addTask(tasks, deadline);
    }

    private static void handleEvent(ArrayList<Task> tasks, String input) throws ShiroException {
        String taskDetails = extractDescription(input, "event");
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2) {
            throw new ShiroException("☹ OOPS!!! The event must have a /from and /to time.");
        }
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new ShiroException("☹ OOPS!!! The event must have a /from and /to time.");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        if (from.isEmpty() || to.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The /from and /to times cannot be empty.");
        }
        Task event = new Event(description, from, to);
        addTask(tasks, event);
    }

    private static void handleTodo(ArrayList<Task> tasks, String input) throws ShiroException {
        String description = extractDescription(input, "todo").trim();
        if (description.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(description);
        addTask(tasks, todo);
    }

    private static void handleUnmark(ArrayList<Task> tasks, String input) throws ShiroException {
        if (input.trim().equals("unmark")) {
            throw new ShiroException("☹ OOPS!!! The task index to mark cannot be empty.");
        }
        String inputIndex = input.substring(7).trim();
        int index = parseTaskIndex(inputIndex, tasks);
        tasks.get(index).markAsNotDone();
        Ui.printUnmarked(tasks.get(index));
    }

    private static void handleMark(ArrayList<Task> tasks, String input) throws ShiroException {
        if (input.trim().equals("mark")) {
            throw new ShiroException("☹ OOPS!!! The task index to mark cannot be empty.");
        }
        String inputIndex = input.substring(5).trim();
        int index = parseTaskIndex(inputIndex, tasks);
        tasks.get(index).markAsDone();
        Ui.printMarked(tasks.get(index));
    }

    private static void handleDelete(ArrayList<Task> tasks, String input) throws ShiroException {
        if (input.trim().equals("delete")) {
            throw new ShiroException("☹ OOPS!!! The task index to delete cannot be empty.");
        }
        String inputIndex = input.substring(7).trim(); // after "delete "
        int index = parseTaskIndex(inputIndex, tasks);
        Task removed = tasks.remove(index);
        Ui.printDeleted(removed, tasks.size());
    }

    private static int parseTaskIndex(String inputIndex, ArrayList<Task> tasks) throws ShiroException {
        try {
            int index = Integer.parseInt(inputIndex.trim());
            if (index < 1 || index > tasks.size()) {
                throw new ShiroException("☹ OOPS!!! The task index provided is out of bounds.");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new ShiroException("☹ OOPS!!! The task index provided is not a valid number.");
        }
    }

    private static ArrayList<Task> loadTasks(Storage storage) {
        try {
            return storage.load();
        } catch (ShiroException e) {
            Ui.printError(e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void handleCommand(String input, ArrayList<Task> tasks, Storage storage) throws ShiroException {

        String trimmedInput = input.trim();

        if (trimmedInput.equals("list")) {
            Ui.printList(tasks);
        } else if (trimmedInput.equals("mark") || trimmedInput.startsWith("mark ")) {
            handleMark(tasks, input);
            storage.save(tasks);
        } else if (trimmedInput.equals("unmark") || trimmedInput.startsWith("unmark ")) {
            handleUnmark(tasks, input);
            storage.save(tasks);
        } else if (trimmedInput.equals("todo") || trimmedInput.startsWith("todo ")) {
            handleTodo(tasks, input);
            storage.save(tasks);
        } else if (trimmedInput.equals("event") || trimmedInput.startsWith("event ")) {
            handleEvent(tasks, input);
            storage.save(tasks);
        } else if (trimmedInput.equals("deadline") || trimmedInput.startsWith("deadline ")) {
            handleDeadline(tasks, input);
            storage.save(tasks);
        } else if (trimmedInput.equals("delete") || trimmedInput.startsWith("delete ")) {
            handleDelete(tasks, input);
            storage.save(tasks);
        } else {
            throw new ShiroException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage(Paths.get("./data/shiro.txt"));

        ArrayList<Task> tasks = loadTasks(storage);

        Ui.printGreeting();
        while (true) {
            System.out.print("> ");
            String input = in.nextLine();
            String trimmedInput = input.trim();

            if (trimmedInput.equals("bye")) {
                Ui.printBye();
                break;
            }

            try {
                handleCommand(input, tasks, storage);
            } catch (ShiroException e) {
                Ui.printError(e.getMessage());
            }
        }
    }
}
