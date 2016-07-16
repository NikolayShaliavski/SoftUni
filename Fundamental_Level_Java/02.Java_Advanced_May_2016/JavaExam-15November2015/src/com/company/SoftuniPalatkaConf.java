package com.company;

import java.util.Scanner;

public class SoftuniPalatkaConf {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int people = Integer.parseInt(scan.nextLine());
        int lines = Integer.parseInt(scan.nextLine());
        int beds = 0;
        int meals = 0;

        for (int i = 0; i < lines; i++) {
            String[] info = scan.nextLine().split(" ");
            String kind = info[0];
            int quantity = Integer.parseInt(info[1]);
            String type = info[2];

            switch (kind) {
                case "tents":
                    if (type.equals("normal")) {
                        beds += quantity * 2;
                    } else {
                        beds += quantity * 3;
                    }
                    break;
                case "rooms":
                    if (type.equals("single")) {
                        beds += quantity;
                    } else if (type.equals("double")) {
                        beds += quantity * 2;
                    } else {
                        beds += quantity * 3;
                    }
                    break;
                case "food":
                    if (type.equals("musaka")) {
                        meals += quantity * 2;
                    }
                    break;
            }
        }
        if (people <= beds) {
            System.out.printf("Everyone is happy and sleeping well. Beds left: %d\n", beds - people);
        } else {
            System.out.printf("Some people are freezing cold. Beds needed: %d\n", people - beds);
        }
        if (people <= meals) {
            System.out.printf("Nobody left hungry. Meals left: %d\n", meals - people);
        } else {
            System.out.printf("People are starving. Meals needed: %d\n", people - meals);
        }
    }
}
