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

    private static void printAdded(String input) {
        System.out.println(LINE);
        System.out.println("     added: " + input);
        System.out.println(LINE);
        System.out.println();
    }

    private static void printList(Task[] tasks, int taskCount) {
        System.out.println(LINE);
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
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
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                printAdded(input);
            }
        }
    }
}
