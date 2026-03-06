package shiro;

import java.util.Scanner;
import java.nio.file.Paths;

/**
 * Main entry point for the Shiro chatbot application.
 * Handles user input, command routing and interaction flow.
 */
public class Shiro {

    private static void addTask(TaskList tasks, Task task) {
        tasks.add(task);
        Ui.printAdded(task, tasks.size());
    }

    private static void handleUnmark(TaskList tasks, String input) throws ShiroException {
        int index = Parser.parseTaskIndex(input, "unmark", tasks);
        tasks.get(index).markAsNotDone();
        Ui.printUnmarked(tasks.get(index));
    }

    private static void handleMark(TaskList tasks, String input) throws ShiroException {
        int index = Parser.parseTaskIndex(input, "mark", tasks);
        tasks.get(index).markAsDone();
        Ui.printMarked(tasks.get(index));
    }

    private static void handleDelete(TaskList tasks, String input) throws ShiroException {
        int index = Parser.parseTaskIndex(input, "delete", tasks);
        Task removed = tasks.remove(index);
        Ui.printDeleted(removed, tasks.size());
    }

    private static TaskList loadTasks(Storage storage) {
        try {
            return new TaskList(storage.load());
        } catch (ShiroException e) {
            Ui.printError(e.getMessage());
            return new TaskList();
        }
    }

    private static void handleCommand(String input, TaskList tasks, Storage storage) throws ShiroException {
        String command = Parser.getCommandWord(input);

        if (command.equals("list")) {
            Ui.printList(tasks.getAllTasks());
        } else if (command.equals("mark")) {
            handleMark(tasks, input);
            storage.save(tasks.getAllTasks());
        } else if (command.equals("unmark")) {
            handleUnmark(tasks, input);
            storage.save(tasks.getAllTasks());
        } else if (command.equals("todo")) {
            Task todo = Parser.parseTodo(input);
            addTask(tasks, todo);
            storage.save(tasks.getAllTasks());
        } else if (command.equals("event")) {
            Task event = Parser.parseEvent(input);
            addTask(tasks, event);
            storage.save(tasks.getAllTasks());
        } else if (command.equals("deadline")) {
            Task deadline = Parser.parseDeadline(input);
            addTask(tasks, deadline);
            storage.save(tasks.getAllTasks());
        } else if (command.equals("delete")) {
            handleDelete(tasks, input);
            storage.save(tasks.getAllTasks());
        } else {
            throw new ShiroException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage(Paths.get("./data/shiro.txt"));

        TaskList tasks = loadTasks(storage);

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
