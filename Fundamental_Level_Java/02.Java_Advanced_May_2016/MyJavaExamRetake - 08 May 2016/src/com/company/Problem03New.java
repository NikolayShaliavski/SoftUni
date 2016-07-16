package com.company;

import java.util.Scanner;

public class Problem03New {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int lines = Integer.parseInt(scan.nextLine());
        String cards = "";
        for (int i = 0; i < lines; i++) {
            String currentLine = scan.nextLine();
            cards = cards + currentLine;
        }
        String[] faces = cards.split("[s|c|h|d]");
        String[] suits = cards.split("[^s|c|h|d]+");

        String[] flush = {"10", "J", "Q", "K", "A"};
        int flushes = 0;
        for (int i = 0; i < faces.length; i++) {
            String face = faces[i];
            String suit = suits[i + 1];

            if (face.equals("10")) {
                int counter = 1;
                for (int j = i + 1; j < faces.length; j++) {

                    String nextFace = flush[counter];
                    String nextSuit = suits[j + 1];
                    if (faces[j].equals(nextFace) && suit.equals(nextSuit)) {
                        counter++;
                    } else if (!suit.equals(nextSuit)) {
                        continue;
                    } else {
                        break;
                    }
                    if (counter == 5) {
                        flushes++;
                        switch (suit) {
                            case "s":
                                System.out.println("Royal Flush Found - Spades");
                                break;
                            case "c":
                                System.out.println("Royal Flush Found - Clubs");
                                break;
                            case "h":
                                System.out.println("Royal Flush Found - Hearts");
                                break;
                            case "d":
                                System.out.println("Royal Flush Found - Diamonds");
                                break;
                        }
                        break;
                    }
                }
            }
        }
        System.out.println("Royal's Royal Flushes - " + flushes + ".");
    }
}
