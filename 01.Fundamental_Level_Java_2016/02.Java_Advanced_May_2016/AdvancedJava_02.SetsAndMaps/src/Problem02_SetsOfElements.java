import java.util.HashSet;
import java.util.Scanner;

public class Problem02_SetsOfElements {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int firstLength = scan.nextInt();
        int secondLength = scan.nextInt();
        scan.nextLine();
        HashSet<Integer> firstSet = new HashSet<>();
        HashSet<Integer> secondSet = new HashSet<>();

        for (int i = 0; i < firstLength; i++) {
            firstSet.add(scan.nextInt());
        }
        for (int i = 0; i < secondLength; i++) {
            secondSet.add(scan.nextInt());
        }
        if (firstLength >= secondLength) {
            for (Integer number : firstSet) {
                if (secondSet.contains(number)){
                    System.out.print(number + " ");
                }
            }
        } else {
            for (Integer number : secondSet) {
                if (firstSet.contains(number)) {
                    System.out.print(number + " ");
                }
            }
        }
    }
}
