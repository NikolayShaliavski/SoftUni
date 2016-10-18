package problem_05.inhabitants;

public class Robot extends AbstractInhabitant {
    private static final String ROBOT_BIRTHDAY = "I am a robot & I don't celebrate birthday!!!";

    private String id;

    public Robot(String name, String id) {
        super(name, ROBOT_BIRTHDAY);
        this.setId(id);
    }

    private void setId(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Robot's Id cannot be null or empty!");
        }
        this.id = id;
    }
}
