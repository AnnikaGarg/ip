public class Event extends Task {
    private static final String TYPE_ICON = "[E]";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
