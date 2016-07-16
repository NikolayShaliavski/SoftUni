package com.company;

import java.util.Scanner;

public class Durts02 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] center = scan.nextLine().trim().split("[\\s]+");
        int centerX = Integer.parseInt(center[0]);
        int centerY = Integer.parseInt(center[1]);

        int radius = Integer.parseInt(scan.nextLine());
        int lines = Integer.parseInt(scan.nextLine());
        String[] coordinates = scan.nextLine().trim().split("[\\s]+");

        //very simple decision -> cross figure is two rectangles - vertical && horizontal -> we check if the darts is in vertical or in horizontal figure
        double verticalYmax = centerY + radius;
        double verticalYmin = centerY - radius;
        double verticalXmax = centerX + (radius * 0.5);
        double verticalXmin = centerX - (radius * 0.5);

        double horizontalXmax = centerX + radius;
        double horizontalXmin = centerX - radius;
        double horizontalYmax = centerY + (radius * 0.5);
        double horizontalYmin = centerY - (radius * 0.5);

        for (int i = 0; i < coordinates.length - 1; i += 2) {
            int coordX = Integer.parseInt(coordinates[i]);
            int coordY = Integer.parseInt(coordinates[i + 1]);

            boolean isInVertical = coordX <= verticalXmax && coordX >= verticalXmin && coordY <= verticalYmax && coordY >= verticalYmin;
            boolean isInHorizontal = coordX <= horizontalXmax && coordX >= horizontalXmin && coordY <= horizontalYmax && coordY >= horizontalYmin;

            if (isInVertical || isInHorizontal) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
