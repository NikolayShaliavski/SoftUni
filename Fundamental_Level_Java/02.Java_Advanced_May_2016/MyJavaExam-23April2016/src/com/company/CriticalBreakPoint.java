package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CriticalBreakPoint {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        ArrayList<String> out = new ArrayList<>();
        boolean findRatio = false;
        boolean exist = true;
        long neededRatio = 0;
        int counter = 0;
        while (!line.equals("Break it.")) {

            String[] points = line.split(" ");
            long x1 = Integer.parseInt(points[0]);
            long y1 = Integer.parseInt(points[1]);
            long x2 = Integer.parseInt(points[2]);
            long y2 = Integer.parseInt(points[3]);

            long a = (x2 + y2);
            long b = (x1 + y1);
            long ratio =  Math.abs(a - b);

            if (ratio > 0 && !findRatio) {
                neededRatio = ratio;
                findRatio = true;
            }

            if (ratio != neededRatio && ratio != 0) {
                exist = false;
            }
            out.add(line);
            line = scan.nextLine();
            counter++;
        }
        if (exist) {
            BigInteger criticalRatio = new BigInteger(neededRatio + "");
            for (int i = 0; i < out.size(); i++) {
                String[] print = out.get(i).split(" ");
                System.out.println("Line: " + Arrays.toString(print));
            }
            criticalRatio = criticalRatio.pow(counter);
            criticalRatio = criticalRatio.remainder(new BigInteger(counter + ""));
            System.out.printf("Critical Breakpoint: %d", criticalRatio);
        } else {
            System.out.println("Critical breakpoint does not exist.");
        }
    }
}
