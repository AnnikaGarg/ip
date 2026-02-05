public class Deadline extends Task {
    private static final String TYPE_ICON = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
