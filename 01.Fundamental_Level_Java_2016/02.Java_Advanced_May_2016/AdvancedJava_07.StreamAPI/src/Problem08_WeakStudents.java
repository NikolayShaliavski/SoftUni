import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Problem08_WeakStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new LinkedList<>();

        String student = reader.readLine();
        while (!student.equals("END")) {
            students.add(student);
            student = reader.readLine();
        }

        students.stream().map(studentInfo -> studentInfo.split("[\\s]+"))
                .filter(studentInfo -> {
                    int counter = 0;
                    for (int i = 0; i < studentInfo.length; i++) {
                        if (studentInfo[i].equals("2") || studentInfo[i].equals("3")) {
                            counter++;
                        }
                    }
                    if (counter >= 2) {
                        return true;
                    }
                    return false;
                }).forEach(name -> System.out.println(name[0] + " " + name[1]));
    }
}
