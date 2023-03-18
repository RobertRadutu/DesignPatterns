public class Event {
    public enum Type {
        DONE_C,
        DONE_F,
        DONE_B,
        DONE_S,
        DONE_P
    }

    private final Type type;

    public Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
