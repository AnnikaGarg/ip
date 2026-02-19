package shiro;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    private static final String TYPE_ICON = "[E]";

    protected String from;
    protected String to;

    /**
     * Creates a new Event task with the given description and time period.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon of an Event task.
     *
     * @return "[E]" representing an Event.
     */
    @Override
    protected String getTypeIcon() {
        return TYPE_ICON;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return Formatted string including event time range.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
