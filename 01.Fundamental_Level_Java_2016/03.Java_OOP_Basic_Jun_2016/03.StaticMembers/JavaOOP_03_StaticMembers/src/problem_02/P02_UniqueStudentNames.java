package problem_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class P02_UniqueStudentNames {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();

        while (!name.equals("End")) {
            Student student = new Student(name);
            StudentGroup.addStudent(student.getName());
            name = reader.readLine();
        }
        System.out.println(StudentGroup.getCounter());
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class StudentGroup {
    private static HashSet<String> studentGroup = new HashSet<>();
    private static int counter;

    public static int getCounter() {
        return counter;
    }

    public static void addStudent(String name) {
        studentGroup.add(name);
        counter = studentGroup.size();
    }
}