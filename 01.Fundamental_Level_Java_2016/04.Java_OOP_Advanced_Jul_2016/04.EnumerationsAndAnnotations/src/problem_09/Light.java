package problem_09;

public enum Light {

    RED,
    GREEN,
    YELLOW;

    private static Light[] values = values();

    public Light next() {
        Light next = values[(ordinal() + 1) % values.length];
        return next;
    }
}
