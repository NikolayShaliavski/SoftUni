package problem_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        GenericBox<Integer> genericBox = null;
        for (int i = 0; i < lines; i++) {
            int boxNumber = Integer.valueOf(reader.readLine());
            genericBox = new GenericBox<>(boxNumber);
            System.out.println(genericBox.toString());
        }
    }

}
