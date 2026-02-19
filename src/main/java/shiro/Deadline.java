package shiro;

/**
 * Represents a deadline task with a specified due time.
 */
public class Deadline extends Task {

    private static final String TYPE_ICON = "[D]";

    protected String by;

    /**
     * Creates a new Deadline task with the given description and due time.
     *
     * @param description Description of the deadline task.
     * @param by          Due time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type icon of a Deadline task.
     *
     * @return "[D]" representing a Deadline.
     */
    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return Formatted string including deadline time.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
