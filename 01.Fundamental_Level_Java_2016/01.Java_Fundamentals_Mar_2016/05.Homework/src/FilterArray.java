import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FilterArray {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] line = scn.nextLine().split(" ");

        List<String> out = Arrays.stream(line).filter(str -> str.length() > 3).collect(Collectors.toList());

        if (out.size() <= 0) {
            System.out.println("(empty)");
        }
        for (String s : out) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
