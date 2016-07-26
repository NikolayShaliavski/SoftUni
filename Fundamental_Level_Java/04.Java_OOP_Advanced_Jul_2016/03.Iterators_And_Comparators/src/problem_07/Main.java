package problem_07;

import problem_07.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        TreeSet<Person> sortedPeople = new TreeSet<>();
        HashSet<Person> uniquePeople = new HashSet<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());

        for (int i = 0; i < lines; i++) {
            String[] params = reader.readLine().split("[\\s]+");
            String personName = params[0];
            int personAge = Integer.valueOf(params[1]);
            Person person = new Person(personName, personAge);

            sortedPeople.add(person);
            uniquePeople.add(person);
        }
        System.out.println(sortedPeople.size());
        System.out.println(uniquePeople.size());
    }
}
