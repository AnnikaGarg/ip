import java.util.Scanner;

public class Shiro {
    public static final int TASK_CAPACITY = 100;
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

    private static void printList(Task[] tasks, int taskCount) {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE);
        System.out.println();
    }

    private static void markMessage(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(LINE);
        System.out.println();
    }

    private static void unmarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       [" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(LINE);
        System.out.println();
    }

    private static int addTask(Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        taskCount++;
        printAdded(task, taskCount);
        return taskCount;
    }

    private static int handleDeadline(Task[] tasks, int taskCount, String input) {
        String taskDetails = input.substring(9).trim();
        String[] parts = taskDetails.split(" /by ", 2);
        String description = parts[0].trim();
        String by = "";
        if (parts.length == 2) {
            by = parts[1].trim();
        }
        Task deadline = new Deadline(description, by);
        return addTask(tasks, taskCount, deadline);
    }

    private static int handleEvent(Task[] tasks, int taskCount, String input) {
        String taskDetails = input.substring(6).trim();
        String[] parts = taskDetails.split(" /from ", 2);
        String description = parts[0].trim();
        String from = "";
        String to = "";
        if (parts.length == 2) {
            String[] timeParts = parts[1].split(" /to ", 2);
            from = timeParts[0].trim();
            if (timeParts.length == 2) {
                to = timeParts[1].trim();
            }
        }
        Task event = new Event(description, from, to);
        return addTask(tasks, taskCount, event);
    }

    private static int handleTodo(Task[] tasks, int taskCount, String input) {
        String description = input.substring(5).trim();
        Task todo = new Todo(description);
        return addTask(tasks, taskCount, todo);
    }

    private static void handleUnmark(Task[] tasks, String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        tasks[index].markAsNotDone();
        unmarkMessage(tasks[index]);
    }

    private static void handleMark(Task[] tasks, String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        tasks[index].markAsDone();
        markMessage(tasks[index]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[TASK_CAPACITY];
        int taskCount = 0;
        printGreeting();
        while (true) {
            System.out.print("> ");
            String input = in.nextLine().trim();
            if (input.equals("bye")) {
                printBye();
                break;
            } else if (input.equals("list")) {
                printList(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                handleMark(tasks, input);
            } else if (input.startsWith("unmark ")) {
                handleUnmark(tasks, input);
            } else if (input.startsWith("todo ")) {
                taskCount = handleTodo(tasks, taskCount, input);
            } else if (input.startsWith("event ")) {
                taskCount = handleEvent(tasks, taskCount, input);
            } else if (input.startsWith("deadline ")) {
                taskCount = handleDeadline(tasks, taskCount, input);
            } else {
                displayEcho(input);
            }
        }
    }
}
