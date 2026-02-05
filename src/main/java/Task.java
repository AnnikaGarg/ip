public class Task {
    private static final String STATUS_DONE_ICON = "X";
    private static final String STATUS_NOT_DONE_ICON = " ";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? STATUS_DONE_ICON : STATUS_NOT_DONE_ICON);
    }

    public String getDescription() {
        return description;
    }

    protected String getTypeIcon() {
        return "";
    }

    @Override
    public String toString() {
        return getTypeIcon() + "[" + getStatusIcon() + "] " + description;
    }
}
