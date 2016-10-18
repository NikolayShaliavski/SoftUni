package problem_06.animals;

public class Frog extends Animal {

    public Frog(String name, String age, String male) {
        super(name, age, male);
    }

    @Override
    public String produceSound() {
        return "Frogggg";
    }
}
