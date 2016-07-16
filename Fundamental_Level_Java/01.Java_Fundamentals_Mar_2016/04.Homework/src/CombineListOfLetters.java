import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombineListOfLetters {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        List<Character> firstList = Arrays.stream(scn.nextLine().split(" ")).map(c -> c.charAt(0)).collect(Collectors.toList());
        List<Character> secondList = Arrays.stream(scn.nextLine().split(" ")).map(c -> c.charAt(0)).collect(Collectors.toList());
        List<Character> thirdList = new ArrayList<>();

        for (int i = 0; i < secondList.size(); i++) {
            if (!firstList.contains(secondList.get(i))) {
                thirdList.add(secondList.get(i));
            }
        }
        firstList.addAll(thirdList);
        for (Character character : firstList) {
            System.out.print(character + " ");
        }

    }
}
