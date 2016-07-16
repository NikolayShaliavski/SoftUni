import java.util.Scanner;

public class RectangleArea {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int sideA = scn.nextInt();
        scn.nextLine();
        int sideB = scn.nextInt();

        int area = sideA * sideB;
        System.out.println(area);
    }
}
