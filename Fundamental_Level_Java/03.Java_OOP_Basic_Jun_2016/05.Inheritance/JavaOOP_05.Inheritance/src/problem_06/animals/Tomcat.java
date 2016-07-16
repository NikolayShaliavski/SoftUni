package problem_06.animals;

public class Tomcat extends Cat {

    public Tomcat(String name, String age) {
        super(name, age, "Male");
    }

    @Override
    public String produceSound() {
        return "Give me one million b***h";
    }
}
