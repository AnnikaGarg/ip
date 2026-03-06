package shiro;

/**
 * Parses user input into commands, task details, and task indexes.
 */
public class Parser {

    /**
     * Returns the command word from the given user input.
     *
     * @param input Full user input.
     * @return Command word extracted from the input.
     */
    public static String getCommandWord(String input) {
        return input.trim().split(" ", 2)[0];
    }

    /**
     * Extracts the description part of a command from the user input.
     *
     * @param input Full user input.
     * @param command Command keyword.
     * @return Description following the command word.
     * @throws ShiroException If the description is empty.
     */
    public static String extractDescription(String input, String command) throws ShiroException {
        String description = input.substring(command.length());
        if (description.trim().isEmpty()) {
            throw new ShiroException(":( OOPS!!! The description of a " + command + " cannot be empty.");
        }
        return description;
    }

    /**
     * Parses a todo command into a todo task.
     *
     * @param input Full user input.
     * @return Todo task created from the input.
     * @throws ShiroException If the description is empty.
     */
    public static Task parseTodo(String input) throws ShiroException {
        String description = extractDescription(input, "todo").trim();
        if (description.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Parses a deadline command into a deadline task.
     *
     * @param input Full user input.
     * @return Deadline task created from the input.
     * @throws ShiroException If the input format is invalid.
     */
    public static Task parseDeadline(String input) throws ShiroException {
        String taskDetails = extractDescription(input, "deadline");
        String[] parts = taskDetails.split(" /by ", 2);

        if (parts.length < 2) {
            throw new ShiroException(":( OOPS!!! The deadline must have a /by time.");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The description of a deadline cannot be empty.");
        }

        if (by.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The /by time of a deadline cannot be empty.");
        }

        return new Deadline(description, by);
    }

    /**
     * Parses an event command into an event task.
     *
     * @param input Full user input.
     * @return Event task created from the input.
     * @throws ShiroException If the input format is invalid.
     */
    public static Task parseEvent(String input) throws ShiroException {
        String taskDetails = extractDescription(input, "event");
        String[] parts = taskDetails.split(" /from ", 2);

        if (parts.length < 2) {
            throw new ShiroException(":( OOPS!!! The event must have a /from and /to time.");
        }

        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The description of an event cannot be empty.");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new ShiroException(":( OOPS!!! The event must have a /from and /to time.");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (from.isEmpty() || to.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The /from and /to times cannot be empty.");
        }

        return new Event(description, from, to);
    }

    /**
     * Returns the zero-based task index from a command such as mark, unmark, or delete.
     *
     * @param input Full user input.
     * @param command Command keyword.
     * @param tasks Task list used for bounds checking.
     * @return Zero-based task index.
     * @throws ShiroException If the index is missing, invalid, or out of bounds.
     */
    public static int parseTaskIndex(String input, String command, TaskList tasks) throws ShiroException {
        if (input.trim().equals(command)) {
            throw new ShiroException(":( OOPS!!! The task index to " + command + " cannot be empty.");
        }

        String inputIndex = input.substring(command.length()).trim();

        try {
            int index = Integer.parseInt(inputIndex);
            if (index < 1 || index > tasks.size()) {
                throw new ShiroException(":( OOPS!!! The task index provided is out of bounds.");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new ShiroException(":( OOPS!!! The task index provided is not a valid number.");
        }
    }

    /**
     * Returns the keyword used in a find command.
     *
     * @param input Full user input.
     * @return Keyword used for searching tasks.
     * @throws ShiroException If the keyword is empty.
     */
    public static String parseFindKeyword(String input) throws ShiroException {
        String keyword = extractDescription(input, "find").trim();

        if (keyword.isEmpty()) {
            throw new ShiroException(":( OOPS!!! The keyword for find cannot be empty.");
        }

        return keyword;
    }
}