import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomizeNumbers {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int firstNum = scn.nextInt();
        int secondNum = scn.nextInt();

        int smallest = Math.min(firstNum, secondNum);
        int biggest = Math.max(firstNum, secondNum);
        int diff = biggest - smallest;

        Random rnd = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();

        for (int i = smallest; i <= biggest; i++) {
            int currentRandom = rnd.nextInt(diff + 1) + smallest;

            while (randomNumbers.contains(currentRandom)){
                currentRandom = rnd.nextInt(diff + 1) + smallest;
            }
            randomNumbers.add(currentRandom);
        }

        for (int i = 0; i < randomNumbers.size(); i++) {

            System.out.printf("%d ", randomNumbers.get(i));
        }
    }
}
