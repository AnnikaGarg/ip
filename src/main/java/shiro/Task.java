package shiro;

/**
 * Represents a generic task in the Shiro task list.
 * A task has a description and a completion status.
 */
public class Task {

    private static final String STATUS_DONE_ICON = "X";
    private static final String STATUS_NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task is initially not marked as done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_DONE_ICON : STATUS_NOT_DONE_ICON);
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type icon of the task.
     * Subclasses override this method.
     *
     * @return Type icon string.
     */
    protected String getTypeIcon() {
        return "";
    }

    @Override
    public String toString() {
        return getTypeIcon() + "[" + getStatusIcon() + "] " + description;
    }
}
