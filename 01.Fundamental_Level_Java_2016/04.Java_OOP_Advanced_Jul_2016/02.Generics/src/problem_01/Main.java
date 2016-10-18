package problem_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        GenericBox<String> genericBox = null;
        for (int i = 0; i < lines; i++) {
            genericBox = new GenericBox<>(reader.readLine());
            System.out.println(genericBox.toString());
        }
    }

}
