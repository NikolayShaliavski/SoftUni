package problem_07.people;

public class Rebel extends Person {

    private String group;

    public Rebel(String name, Integer age, String group) {
        super(name, age);
        this.setGroup(group);
    }

    private void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void buyFood() {
        this.buyFood(5);
    }
}
