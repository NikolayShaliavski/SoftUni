package defining_classes.problem_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class P02_CreateConstructors {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Class personClass = Person.class;
        Constructor emptyCtor = personClass.getDeclaredConstructor();
        Constructor ageCtor = personClass.getDeclaredConstructor(int.class);
        Constructor nameAgeCtor = personClass
                .getDeclaredConstructor(String.class, int.class);

        String name = reader.readLine();
        int age = Integer.parseInt(reader.readLine());

        Person basePerson = (Person) emptyCtor.newInstance();
        Person personWithAge = (Person) ageCtor.newInstance(age);
        Person personFull = (Person) nameAgeCtor.newInstance(name, age);

        System.out.printf("%s %s%n", basePerson.name, basePerson.age);
        System.out.printf("%s %s%n", personWithAge.name, personWithAge.age);
        System.out.printf("%s %s%n", personFull.name, personFull.age);

    }
}

class Person {
    static final String DEFAULT_NAME = "No name";
    static final int DEFAULT_AGE = 1;

    String name;
    int age;

    Person() {
        this(DEFAULT_NAME, DEFAULT_AGE);
    }

    Person(int age) {
        this(DEFAULT_NAME, age);
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}