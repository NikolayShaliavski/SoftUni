package com.company;

import java.util.Scanner;

public class Durts {

    //decision with formulae to check if darts is in CIRCLE, not in cross figure -> 70/100 points in judge -> look at Durts02
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] center = scan.nextLine().trim().split("[\\s]+");
        int centerX = Integer.parseInt(center[0]);
        int centerY = Integer.parseInt(center[1]);

        int radiusInput = Integer.parseInt(scan.nextLine());
        double sideA = radiusInput;
        double sideB = (double) radiusInput / 2;
        double radius = Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
        int lines = Integer.parseInt(scan.nextLine());

        String[] coordinates = scan.nextLine().trim().split("[\\s]+");

        for (int i = 0; i < coordinates.length - 1; i += 2) {
            int coordX = Integer.parseInt(coordinates[i]);
            int coordY = Integer.parseInt(coordinates[i + 1]);

            int calcX = Math.abs(coordX - centerX);
            int calcY = Math.abs(coordY - centerY);
            boolean inFigure = Math.pow(calcX, 2) + Math.pow(calcY, 2) <= Math.pow(radius, 2);
            if (inFigure) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
