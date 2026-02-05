import java.util.Scanner;

public class Shiro {
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
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
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
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();
                markMessage(tasks[index]);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].markAsNotDone();
                unmarkMessage(tasks[index]);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                tasks[taskCount] = new Todo(description);
                taskCount++;
                printAdded(tasks[taskCount - 1], taskCount);
            } else if (input.startsWith("event ")) {
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
                tasks[taskCount] = event;
                taskCount++;
                printAdded(event, taskCount);
            } else {
                displayEcho(input);
            }
        }
    }
}
