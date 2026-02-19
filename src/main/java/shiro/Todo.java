package shiro;

/**
 * Represents a todo task without any time constraints.
 */
public class Todo extends Task {
    private static final String TYPE_ICON = "[T]";

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon of a Todo task.
     *
     * @return "[T]" representing a Todo.
     */
    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }
}
