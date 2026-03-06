package shiro;

import java.util.ArrayList;

/**
 * Handles interactions between the Shiro chatbot and the user.
 * Provides methods to display messages and task information.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";

    /**
     * Prints the greeting message displayed when the program starts.
     */
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Shiro");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints the farewell message displayed when the program exits.
     */
    public static void printBye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints a confirmation message when a task is added.
     *
     * @param task Task that was added.
     * @param taskCount Total number of tasks in the list.
     */
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

    /**
     * Prints all tasks currently in the task list.
     *
     * @param tasks List of tasks to display.
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints a confirmation message when a task is deleted.
     *
     * @param task Task that was removed.
     * @param taskCount Updated number of tasks in the list.
     */
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

    /**
     * Prints a confirmation message when a task is marked as done.
     *
     * @param task Task that was marked as done.
     */
    public static void printMarked(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints a confirmation message when a task is marked as not done.
     *
     * @param task Task that was marked as not done.
     */
    public static void printUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints tasks that match a given search keyword.
     *
     * @param matchingTasks List of tasks that match the keyword.
     */
    public static void printMatchingTasks(TaskList matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.size() == 0) {
            System.out.println("     No matching tasks found.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println(LINE);
        System.out.println();
    }

    /**
     * Prints an error message.
     *
     * @param message Error message to display.
     */
    public static void printError(String message) {
        System.out.println(LINE);
        System.out.println("     " + message);
        System.out.println(LINE);
        System.out.println();
    }
}
