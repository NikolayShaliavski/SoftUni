package problem_06.animals;

public class Dog extends Animal {

    public Dog(String name, String age, String male) {
        super(name, age, male);
    }

    @Override
    public String produceSound() {
        return "BauBau";
    }
}
