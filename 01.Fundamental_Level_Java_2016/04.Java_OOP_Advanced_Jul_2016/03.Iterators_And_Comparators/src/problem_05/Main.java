package problem_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Person> people = new ArrayList<>();
        int equalPeople = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] params = line.split("[\\s]+");
            Person person = new Person(params[0],
                    Integer.valueOf(params[1]),
                    params[2]);
            people.add(person);
            line = reader.readLine();
        }
        int personIndex = Integer.valueOf(reader.readLine());
        if (personIndex > people.size() - 1) {
            System.out.println("No matches");
            return;
        }
        Person personToCheck = people.get(personIndex);
        for (int i = 0; i < people.size(); i++) {
            if (i == personIndex) {
                continue;
            }
            if (personToCheck.compareTo(people.get(i)) == 0) {
                equalPeople++;
            }
        }
        if (equalPeople == 1) {
            System.out.println("No matches");
        } else {
            System.out.printf("%d %d %d",
                    equalPeople,
                    people.size() - equalPeople,
                    people.size());
        }
    }
}
