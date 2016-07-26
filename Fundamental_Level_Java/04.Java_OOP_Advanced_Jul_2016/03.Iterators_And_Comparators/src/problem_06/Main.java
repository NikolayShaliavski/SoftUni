package problem_06;

import problem_06.comparators.AgeComparator;
import problem_06.comparators.NameComparator;
import problem_06.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        TreeSet<Person> sortedByName = new TreeSet<>(new NameComparator());
        TreeSet<Person> sortedByAge = new TreeSet<>(new AgeComparator());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.valueOf(reader.readLine());

        for (int i = 0; i < numberOfLines; i++) {
            String[] personParams = reader.readLine().split("[\\s]+");
            String name = personParams[0];
            int age = Integer.valueOf(personParams[1]);

            Person person = new Person(name, age);
            sortedByName.add(person);
            sortedByAge.add(person);
        }

        for (Person person : sortedByName) {
            System.out.println(person.toString());
        }
        for (Person person : sortedByAge) {
            System.out.println(person.toString());
        }
    }
}
