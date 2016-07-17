package problem_07.people;

public class Citizen extends Person {

    private String id;
    private String birthday;

    public Citizen(String name, Integer age, String id, String birthday) {
        super(name, age);
        this.setId(id);
        this.setBirthday(birthday);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public void buyFood() {
        this.buyFood(10);
    }
}
