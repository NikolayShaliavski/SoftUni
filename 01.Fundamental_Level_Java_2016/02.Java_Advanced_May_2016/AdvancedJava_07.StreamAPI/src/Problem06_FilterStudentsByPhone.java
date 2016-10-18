import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem06_FilterStudentsByPhone {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().map(name -> name.split("[\\s]+"))
                .filter(studentName -> (studentName[2].startsWith("02") || studentName[2].startsWith("+3592")))
                .forEach(toPrint -> System.out.println(toPrint[0] + " " + toPrint[1]));
    }
}
