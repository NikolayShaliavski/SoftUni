package problem_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P13_PrintPeople {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();
        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("[\\s]+");
            String name = tokens[0];
            int age = Integer.valueOf(tokens[1]);
            String occupation = tokens[2];
            Person person = new Person(name, age, occupation);
            people.add(person);
            line = reader.readLine();
        }
        people.stream()
                .sorted((person1, person2) -> person1.compareTo(person2))
                .forEach(person -> System.out.println(person.toString()));
    }
}
class Person {
    String name;
    Integer age;
    String occupation;

    public Person(String name, Integer age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }

    private String getName() {
        return name;
    }

    private String getOccupation() {
        return occupation;
    }

    public Integer getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return String.format("%s - age: %d, occupation: %s",
                this.getName(),
                this.getAge(),
                this.getOccupation());
    }

    public int compareTo(Person anotherPerson) {
        return this.getAge().compareTo(anotherPerson.getAge());
    }
}