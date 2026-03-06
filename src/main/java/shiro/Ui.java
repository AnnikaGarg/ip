package shiro;

import java.util.ArrayList;
public class Ui {
    private static final String LINE = "    ____________________________________________________________";

    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Shiro");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    public static void printBye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println();
    }

    public static void printAdded(Task task, int taskCount) {
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

    public static void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    public static void printDeleted(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        if (taskCount == 1) {
            System.out.println("     Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
        System.out.println(LINE);
        System.out.println();
    }

    public static void printMarked(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    public static void printUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    public static void printError(String message) {
        System.out.println(LINE);
        System.out.println("     " + message);
        System.out.println(LINE);
        System.out.println();
    }
}
