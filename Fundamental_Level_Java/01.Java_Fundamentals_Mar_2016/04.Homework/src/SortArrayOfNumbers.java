import java.util.Scanner;

public class SortArrayOfNumbers {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int lines = scn.nextInt();

        int[] numbers = new int[lines];
        for (int i = 0; i < lines; i++) {
            numbers[i] = scn.nextInt();
        }
        int temp = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
