import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem04_SortStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().map(name -> name.split("[\\s]+"))
                .sorted((student01, student02) -> {
                    Integer result = student01[1].compareTo(student02[1]);
                    if (result == 0) {
                        result = student02[0].compareTo(student01[0]);
                    }
                    return result;
                }).forEach(studentName -> System.out.println(studentName[0] + " " + studentName[1]));
    }
}
