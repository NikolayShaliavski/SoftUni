import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem07_ExcellentStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().filter(studentInfo -> studentInfo.contains("6"))
                .map(studentInfo -> studentInfo.split("[\\s]+"))
                .forEach(toPrint -> System.out.println(toPrint[0] + " " + toPrint[1]));
    }
}
