package problem_06.animals;

public class Cat extends Animal {

    public Cat(String name, String age, String male) {
        super(name, age, male);
    }

    @Override
    public String produceSound() {
        return "MiauMiau";
    }
}
