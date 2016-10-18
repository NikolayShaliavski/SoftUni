import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstOddOrEvenElaments {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] input = scn.nextLine().split(" ");
        String[] command = scn.nextLine().split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            numbers.add(Integer.parseInt(input[i]));
        }
        String oddOrEven = command[2];
        int size = Integer.parseInt(command[1]);

        ArrayList<Integer> result = checkNums(numbers, size, oddOrEven);

        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d ", result.get(i));
        }
        System.out.println();
    }

    private static ArrayList<Integer> checkNums (List<Integer> numbers, int size, String oddOrEven){
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        while (result.size() < size && i < numbers.size()){

            if (oddOrEven.equals("even") && numbers.get(i) % 2 == 0){
                result.add(numbers.get(i));
            }else if (oddOrEven.equals("odd") && numbers.get(i) % 2 != 0){
                result.add(numbers.get(i));
            }
            i++;
        }
        return result;
    }
}
