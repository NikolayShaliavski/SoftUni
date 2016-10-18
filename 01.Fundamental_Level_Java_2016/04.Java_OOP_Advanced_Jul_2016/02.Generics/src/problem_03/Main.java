package problem_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        List<GenericBox> listOfBoxes = new ArrayList<>();
        GenericBox<String> genericBox = null;

        for (int i = 0; i < lines; i++) {
            genericBox = new GenericBox<>(reader.readLine());
            listOfBoxes.add(genericBox);
        }
        String[] indexes = reader.readLine().split("\\s+");
        int index01 = Integer.valueOf(indexes[0]);
        int index02 = Integer.valueOf(indexes[1]);
        swap(listOfBoxes, index01, index02);
        print(listOfBoxes);
    }

    private static void swap(List<GenericBox> collection, int index01, int index02) {
        Collections.swap(collection, index01, index02);
    }

    private static void print(List<GenericBox> collection) {
        for (GenericBox element : collection) {
            System.out.println(element.toString());
        }
    }
}
