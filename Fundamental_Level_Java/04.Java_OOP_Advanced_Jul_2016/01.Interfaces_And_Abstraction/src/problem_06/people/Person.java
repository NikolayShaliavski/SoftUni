package problem_06.people;

import problem_06.interfaces.Buyer;

public abstract class Person implements Buyer {

    private static final Integer START_FOOD = 0;

    private String name;
    private Integer age;
    private Integer food;

    Person(String name, Integer age) {
        this.setName(name);
        this.setAge(age);
        this.food = START_FOOD;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Integer getFood() {
        return this.food;
    }

    protected void buyFood(Integer food) {
        this.food += food;
    }
}
