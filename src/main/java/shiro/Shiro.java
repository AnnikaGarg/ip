package shiro;

import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;

public class Shiro {
    public static final int MAX_TASKS = 100;
    private static final String LINE = "    ____________________________________________________________";

    private static void printGreeting() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Shiro");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private static void printBye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println();
    }

    private static void displayEcho(String input) {
        System.out.println(LINE);
        System.out.println("     " + input);
        System.out.println(LINE);
        System.out.println();
    }

    private static void printAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        if (taskCount == 1) {
            System.out.println("     Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
        System.out.println(LINE);
        System.out.println();
    }

    private static void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    private static void printDeleted(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    private static void markMessage(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    private static void unmarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    private static void printError(String message) {
        System.out.println(LINE);
        System.out.println("     " + message);
        System.out.println(LINE);
        System.out.println();
    }

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        printAdded(task, tasks.size());
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
        if (description.isEmpty()) {
            throw new ShiroException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String by = parts[1].trim();
        ;
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
        unmarkMessage(tasks.get(index));
    }

    private static void handleMark(ArrayList<Task> tasks, String input) throws ShiroException {
        if (input.trim().equals("mark")) {
            throw new ShiroException("☹ OOPS!!! The task index to mark cannot be empty.");
        }
        String inputIndex = input.substring(5).trim();
        int index = parseTaskIndex(inputIndex, tasks);
        tasks.get(index).markAsDone();
        markMessage(tasks.get(index));
    }

    private static void handleDelete(ArrayList<Task> tasks, String input) throws ShiroException {
        if (input.trim().equals("delete")) {
            throw new ShiroException("☹ OOPS!!! The task index to delete cannot be empty.");
        }
        String inputIndex = input.substring(7).trim(); // after "delete "
        int index = parseTaskIndex(inputIndex, tasks);
        Task removed = tasks.remove(index);
        printDeleted(removed, tasks.size());
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage(Paths.get("./data/shiro.txt"));
        ArrayList<Task> tasks;
        try {
            tasks = storage.load();
        } catch (ShiroException e) {
            printError(e.getMessage());
            tasks = new ArrayList<>();
        }
        printGreeting();
        while (true) {
            System.out.print("> ");
            String input = in.nextLine();
            String trimmedInput = input.trim();
            try {
                if (trimmedInput.equals("bye")) {
                    printBye();
                    break;
                } else if (trimmedInput.equals("list")) {
                    printList(tasks);
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
            } catch (ShiroException e) {
                printError(e.getMessage());
            }
        }
    }
}
