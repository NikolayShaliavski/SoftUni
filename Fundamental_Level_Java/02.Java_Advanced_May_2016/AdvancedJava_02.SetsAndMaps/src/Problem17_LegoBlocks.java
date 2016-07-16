import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Problem17_LegoBlocks {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rows = Integer.parseInt(scan.nextLine());

        ArrayList<ArrayList<String>> firstArray = new ArrayList<>();
        ArrayList<ArrayList<String>> secondArray = new ArrayList<>();

        boolean fit = true;
        for (int i = 0; i < rows; i++) {
            firstArray.add(new ArrayList<>());
            Collections.addAll(firstArray.get(i), scan.nextLine().trim().split("\\s+"));
        }
        for (int i = 0; i < rows; i++) {
            secondArray.add(new ArrayList<>());
            Collections.addAll(secondArray.get(i), scan.nextLine().trim().split("\\s+"));
        }

        for (int i = 0; i < secondArray.size(); i++) {
            Collections.reverse(secondArray.get(i));
        }
        for (int i = 0; i < firstArray.size(); i++) {
            firstArray.get(i).addAll(secondArray.get(i));
        }

        int size = firstArray.get(0).size();
        for (int i = 1; i < firstArray.size(); i++) {
            if (firstArray.get(i).size() != size) {
                fit = false;
            }
        }

        if (fit) {
            for (int i = 0; i < firstArray.size(); i++) {
                System.out.println(firstArray.get(i));
            }
        } else {
            int counter = 0;
            for (int i = 0; i < firstArray.size(); i++) {
                counter += firstArray.get(i).size();
            }
            System.out.println("The total number of cells is: " + counter);
        }
    }
}
