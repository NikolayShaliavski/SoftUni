package pr01_SumTo13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumToThirteen {

    static int[] nums;

    public static void main(String[] args) throws IOException {
        readInput();

        int a = nums[0];
        int b = nums[1];
        int c = nums[2];

        if (a + b + c == 13) {
            System.out.println("Yes");
        } else if (a * -1 + b + c == 13) {
            System.out.println("Yes");
        } else if (a + b * -1 + c == 13) {
            System.out.println("Yes");
        } else if (a + b + c * -1 == 13) {
            System.out.println("Yes");
        } else if (a * -1 + b * -1 + c == 13) {
            System.out.println("Yes");
        } else if (a + b * -1 + c * -1 == 13) {
            System.out.println("Yes");
        }else if (a * -1 + b + c * -1 == 13) {
            System.out.println("Yes");
        } else if (a * -1 + b * -1 + c * -1 == 13) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        nums = new int[line.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(line[i]);
        }
    }
}
