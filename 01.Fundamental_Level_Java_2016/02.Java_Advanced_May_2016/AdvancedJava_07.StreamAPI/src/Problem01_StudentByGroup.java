import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem01_StudentByGroup {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new ArrayList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().filter(filteredStudent -> filteredStudent.trim().endsWith("2"))
                .sorted()
                .map(studentName -> studentName.split("[\\s]+"))
                .forEachOrdered(studentToPrint -> System.out.println(studentToPrint[0] + " " + studentToPrint[1]));
    }
}
