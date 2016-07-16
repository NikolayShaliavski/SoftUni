import java.util.Scanner;

public class HitTheTarget {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int target = scn.nextInt();

        if (target < -19 || target > 40) {
            System.out.println("No hitted targets.");
        }

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {

                if ((i + j) == target){
                    System.out.printf("%d + %d = %d\n", i, j, target);
                }else if ((i - j) == target){
                    System.out.printf("%d - %d = %d\n", i, j, target);
                }
            }
        }
    }
}
