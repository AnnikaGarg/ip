public class Todo extends Task {
    private static final String TYPE_ICON = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }
}
