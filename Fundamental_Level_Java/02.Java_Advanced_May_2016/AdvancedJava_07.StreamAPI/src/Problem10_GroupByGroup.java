import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem10_GroupByGroup {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();
        String line = reader.readLine();

        while (!line.equals("END")) {
            String[] personInfo = line.split("[\\s]+");
            String name = personInfo[0] + " " + personInfo[1];
            Integer group = Integer.valueOf(personInfo[2]);

            Person person = new Person(name, group);
            people.add(person);
            line = reader.readLine();
        }

        Map<Integer, List<String>> groups = people.stream()
                .collect(Collectors.groupingBy(Person::getGroup, Collectors.mapping(Person::getName, Collectors.toList())));

        for (Map.Entry<Integer, List<String>> students : groups.entrySet()) {
            List<String> names = students.getValue();
            String toPrint = names.toString().replace("[", "").replace("]", "").trim();
            System.out.printf("%d - %s%n", students.getKey(), toPrint);
        }

    }
}

class Person {
    public String name;
    public int group;

    public Person(String name, Integer group) {
        this.name = name;
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }
}


