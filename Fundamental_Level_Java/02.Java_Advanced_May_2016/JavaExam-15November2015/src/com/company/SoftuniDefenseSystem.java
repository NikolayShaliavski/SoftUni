package com.company;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftuniDefenseSystem {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        double total = 0;

        while (!line.equals("OK KoftiShans")) {
            Pattern pattern = Pattern.compile("([A-Z][a-z]+).*?([A-Z][a-z]*[A-Z]).*?(\\d+)L");

            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String name = matcher.group(1);
                String alcohol = matcher.group(2);
                int litres = Integer.parseInt(matcher.group(3));
                total += litres;
                System.out.printf("%s brought %d liters of %s!\n", name, litres, alcohol.toLowerCase());
            }
            line = scan.nextLine();
        }
        System.out.printf("%.3f softuni liters", total / 1000);
    }
}
