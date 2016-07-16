import java.util.ArrayList;
import java.util.Scanner;

public class TriangleArea {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        ArrayList<Integer> coordinates = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] points = scn.nextLine().split(" ");
            coordinates.add(Integer.parseInt(points[0]));
            coordinates.add(Integer.parseInt(points[1]));
        }
        int pointAx = coordinates.get(0);
        int pointAy = coordinates.get(1);
        int pointBx = coordinates.get(2);
        int pointBy = coordinates.get(3);
        int pointCx = coordinates.get(4);
        int pointCy = coordinates.get(5);

        double areaInDouble = (pointAx * (pointBy - pointCy) + pointBx * (pointCy - pointAy) + pointCx * (pointAy - pointBy)) / 2;
        areaInDouble = Math.abs(areaInDouble);

        int area = (int)Math.round(areaInDouble);
        System.out.println(area);
    }
}
