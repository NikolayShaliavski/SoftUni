package problem_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class P02_OldestFamilyMember {

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        Family family = new Family();
        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String name = tokens[0];
            int age = Integer.valueOf(tokens[1]);
            Person person = new Person(name, age);
            family.addFamilyMember(person);
        }
        Person personToPrint = family.getOldestMember();
        System.out.printf("%s %s%n",
                personToPrint.getName(),
                personToPrint.getAge());

        Method getOldestMethod = Family.class.getMethod("getOldestMember");
        Method addMemberMethod = Family.class.getMethod("addFamilyMember", Person.class);

    }
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}

class Family {
    private List<Person> family;
    private Person oldestPerson;

    public Family() {
        this.family = new ArrayList<>();
        this.oldestPerson = null;
    }

    public void addFamilyMember(Person person) {
        this.family.add(person);
        if (this.oldestPerson != null) {
            if (person.getAge() > this.oldestPerson.getAge()) {
                this.oldestPerson = person;
            }
        } else {
            this.oldestPerson = person;
        }
    }

    public Person getOldestMember() {
        return this.oldestPerson;
    }
}