import java.util.Scanner;

public class ParkingSystem {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] dimention = scan.nextLine().split(" ");

        int rows = Integer.parseInt(dimention[0]);
        int cols = Integer.parseInt(dimention[1]);
        boolean[][] parking = new boolean[rows][cols];

        String line = scan.nextLine();
        while (!line.equals("stop")) {
            String[] entered = line.split(" ");

            int enter = Integer.parseInt(entered[0]);
            int row = Integer.parseInt(entered[1]);
            int col = Integer.parseInt(entered[2]);
            int step = 1;
            step += Math.abs(row - enter);
            boolean noFreeCells = true;
            boolean findLeft = false;
            boolean findRight = false;

            for (int i = 1; i < cols; i++) {
                if (parking[row][i] == false) {
                    noFreeCells = false;
                    break;
                }
            }
            if (parking[row][col] == false) {
                step += col;
                parking[row][col] = true;
            } else {
                int left = col;
                int right = col;
                int leftSearch = 0;
                int rightSearch = 0;
                for (int i = left; i >= 1; i--) {
                    leftSearch++;
                    if (parking[row][i] == false) {
                        left = i;
                        findLeft = true;
                        break;
                    }
                }
                for (int i = right; i < cols; i++) {
                    rightSearch++;
                    if (parking[row][i] == false) {
                        right = i;
                        findRight = true;
                        break;
                    }
                }
                if (findLeft && findRight) {
                    if ((leftSearch <= rightSearch)) {
                        step += left;
                        parking[row][left] = true;
                    } else {
                        step += right;
                        parking[row][right] = true;
                    }
                } else {
                    if (findRight) {
                        step += right;
                        parking[row][right] = true;
                    } else if (findLeft) {
                        step += left;
                        parking[row][left] = true;
                    }
                }
            }

            if (noFreeCells) {
                System.out.printf("Row %d full\n", row);
            } else {
                System.out.println(step);
            }
            line = scan.nextLine();
        }
    }
}
