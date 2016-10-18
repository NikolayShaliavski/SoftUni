import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem02_StudentsByFirstAndLastName {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream()
                .map(name -> name.trim().split("[\\s]+"))
                .filter(studentName -> studentName[0].compareTo(studentName[1]) < 0)
                .forEach(studentToPrint -> System.out.println(studentToPrint[0] + " " + studentToPrint[1]));
    }
}
