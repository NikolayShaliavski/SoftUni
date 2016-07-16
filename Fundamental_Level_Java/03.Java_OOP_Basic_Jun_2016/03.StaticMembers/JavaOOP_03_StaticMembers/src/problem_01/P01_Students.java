package problem_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P01_Students {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        while (!name.equals("End")) {
            Students student = new Students(name);
            name = reader.readLine();
        }
        System.out.println(Students.getCount());
    }

}
class Students {
    private String name;
    private static int count = 0;

    public Students(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}