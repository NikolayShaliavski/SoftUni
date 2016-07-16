import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem09_StudentsEnrolled {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().map(studentInfo -> studentInfo.split("[\\s]+"))
                .filter(number -> (number[0].endsWith("14") || number[0].endsWith("15")))
                .forEach(toPrint -> {
                    for (int i = 1; i < toPrint.length; i++) {
                        System.out.printf("%s ", toPrint[i]);
                    }
                    System.out.println();
                });
    }
}
