package defining_classes.problem_01;


import java.lang.reflect.Field;

public class P01_DefineClassPerson {

    public static void main(String[] args) {
        Class person = Person.class;
        Field[] fields = person.getDeclaredFields();
        System.out.println(fields.length);
    }
}

class Person {
    String name;
    int age;
}
