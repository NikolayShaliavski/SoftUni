import java.util.Scanner;

public class OddOrEvenPairs {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String[] line = input.nextLine().split(" ");

        if (line.length % 2 != 0 ) {
            System.out.println("Invalid length");
        }
        int[] nums = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }

        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] % 2 == 0 && nums[i + 1] % 2 == 0){
                System.out.printf("%d, %d -> both are even\n", nums[i], nums[i + 1]);
            }else if (nums[i] % 2 != 0 && nums[i + 1] % 2 != 0){
                System.out.printf("%d, %d -> both are odd\n", nums[i], nums[i + 1]);
            }else{
                System.out.printf("%d, %d -> different\n", nums[i], nums[i + 1]);
            }
        }
    }
}
