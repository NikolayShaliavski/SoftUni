package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem01 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] line = scan.nextLine().split(" ");
        ArrayList<String> royalists = new ArrayList<>();
        ArrayList<String> nonRoyalists = new ArrayList<>();

        for (int i = 0; i < line.length; i++) {
            char[] person = line[i].toCharArray();
            int number = 0;
            for (int j = 0; j < person.length; j++) {
                char code = person[j];
                number += code;
                while (number > 26) {
                    number -= 26;
                }
            }
            if (number == 18) {
                royalists.add(line[i]);
            } else {
                nonRoyalists.add(line[i]);
            }
        }
        if (royalists.size() >= nonRoyalists.size()) {
            System.out.println("Royalists - " + royalists.size());
            for (int i = 0; i < royalists.size(); i++) {
                System.out.println(royalists.get(i));
            }
            System.out.println("All hail Royal!");
        } else {
            for (int i = 0; i < nonRoyalists.size(); i++) {
                System.out.println(nonRoyalists.get(i));
            }
            System.out.println("Royalism, Declined!");
        }
    }
}
