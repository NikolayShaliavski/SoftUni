package problem_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P01_MethodSaysHello {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        Person person = new Person(name);
        System.out.println(person.sayHello());
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String sayHello() {
        return this.name + " says \"Hello\"!";
    }
}
