package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        List<GenericBox> listOfBoxes = new ArrayList<>();
        //GenericBox<String> genericBox = null;
        GenericBox<Double> genericBox = null;

        for (int i = 0; i < lines; i++) {
            //genericBox = new GenericBox<>(reader.readLine());
            double element = Double.valueOf(reader.readLine());
            genericBox = new GenericBox<>(element);
            listOfBoxes.add(genericBox);
        }
        //String compareElement = reader.readLine();
        double compareElement = Double.valueOf(reader.readLine());
        int result = comparingElements(listOfBoxes, compareElement);
        System.out.println(result);
    }

    private static <T extends Comparable> int comparingElements(List<? extends T> listOfBoxes, T compareElement) {
        int count = 0;
        for (T element : listOfBoxes) {
            if (element.compareTo(compareElement) > 0) {
                count++;
            }
        }
        return count;
    }
}
