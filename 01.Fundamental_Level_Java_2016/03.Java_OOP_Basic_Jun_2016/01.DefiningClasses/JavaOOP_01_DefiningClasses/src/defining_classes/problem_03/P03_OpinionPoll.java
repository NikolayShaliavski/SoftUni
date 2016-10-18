package defining_classes.problem_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class P03_OpinionPoll {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int lines = Integer.valueOf(reader.readLine());
        TreeSet<Person> people = new TreeSet<>();

        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String name = tokens[0];
            int age = Integer.valueOf(tokens[1]);
            Person person = new Person(name, age);

            if (person.getAge() > 30) {
                people.add(person);
            }
        }

        for (Person person : people) {
            System.out.printf("%s - %s%n", person.getName(), person.getAge());
        }
    }
}

class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        return this.name.compareTo(anotherPerson.name);
    }
}
