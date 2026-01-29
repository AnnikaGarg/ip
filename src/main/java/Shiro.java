import java.util.Scanner;

public class Shiro {
    private static final String line = "    ____________________________________________________________";

    private static void printGreeting() {
        System.out.println(line);
        System.out.println("     Hello! I'm Shiro");
        System.out.println("     What can I do for you?");
        System.out.println(line);
        System.out.println();
    }

    private static void printBye() {
        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
        System.out.println();
    }

    private static void displayEcho(String input) {
        System.out.println(line);
        System.out.println("     " + input);
        System.out.println(line);
        System.out.println();
    }

    private static void printAdded(String input) {
        System.out.println(line);
        System.out.println("     added: " + input);
        System.out.println(line);
        System.out.println();
    }

    private static void printList(String[] items, int itemCount) {
        System.out.println(line);
        for (int i = 0; i < itemCount; i++) {
            System.out.println("     " + (i + 1) + ". " + items[i]);
        }
        System.out.println(line);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
        int itemCount = 0;
        printGreeting();
        while (true) {
            System.out.print("> ");
            String input = in.nextLine();
            if (input.trim().equals("bye")) {
                printBye();
                break;
            } else if (input.trim().equals("list")) {
                printList(items, itemCount);
            } else {
                items[itemCount] = input;
                itemCount++;
                printAdded(input);
            }
        }
    }
}
