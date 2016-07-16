package problem_05.inhabitants;

public class Citizen extends AbstractInhabitant {

    private Integer age;
    private String id;

    public Citizen(String name, Integer age, String id, String birthday) {
        super(name, birthday);
        this.setAge(age);
        this.setId(id);
    }

    private void setAge(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be less than zero!");
        }
        this.age = age;
    }

    private void setId(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Citizen Id cannot be null or empty!");
        }
        this.id = id;
    }
}
