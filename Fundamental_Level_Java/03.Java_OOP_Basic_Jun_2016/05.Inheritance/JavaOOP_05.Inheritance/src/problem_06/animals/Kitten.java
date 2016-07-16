package problem_06.animals;

public class Kitten extends Cat {

    public Kitten(String name, String age) {
        super(name, age, "Female");
    }

    @Override
    public String produceSound() {
        return "Miau";
    }
}
