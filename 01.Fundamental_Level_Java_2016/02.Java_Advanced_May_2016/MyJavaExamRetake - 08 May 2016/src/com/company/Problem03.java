package com.company;

import java.util.Scanner;

public class Problem03 {

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
        int spades = 0;
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;

        String[] flush = {"10", "J", "Q", "K", "A"};

        for (int i = 0; i < faces.length - 1; i++) {
            String face = faces[i];
            String suit = suits[i + 1];
            String nextSuit = suits[i + 2];
            String nextFace = "";
            if (face.equals("10")) {
                switch (suit) {
                    case "s":
                        spades++;
                        break;
                    case "c":
                        clubs++;
                        break;
                    case "h":
                        hearts++;
                        break;
                    case "d":
                        diamonds++;
                        break;
                }
            }
            switch (suit) {
                case "s":
                    nextFace = flush[spades];
                    break;
                case "c":
                    nextFace = flush[clubs];
                    break;
                case "h":
                    nextFace = flush[hearts];
                    break;
                case "d":
                    nextFace = flush[diamonds];
                    break;
            }

            if (faces[i + 1].equals(nextFace) && suit.equals(nextSuit)) {
                if (spades > 0 || clubs > 0 || hearts > 0 || diamonds > 0) {
                    switch (suit) {
                        case "s":
                            spades++;
                            break;
                        case "c":
                            clubs++;
                            break;
                        case "h":
                            hearts++;
                            break;
                        case "d":
                            diamonds++;
                            break;
                    }
                }
            } else if (!faces[i + 1].equals(nextFace) && !suit.equals(nextSuit)) {
                continue;
            } else {
                switch (suit) {
                    case "s":
                        spades = 0;
                        break;
                    case "c":
                        clubs = 0;
                        break;
                    case "h":
                        hearts = 0;
                        break;
                    case "d":
                        diamonds = 0;
                        break;
                }
            }

            if (spades == 5) {
                System.out.println("Royal Flush Found – Spades");
                spades = 0;
            } else if (clubs == 5) {
                System.out.println("Royal Flush Found – Clubs");
                clubs = 0;
            } else if (hearts == 5) {
                System.out.println("Royal Flush Found – Hearts");
                hearts = 0;
            } else if (diamonds == 5) {
                System.out.println("Royal Flush Found – Diamonds");
                diamonds = 0;
            }
        }
    }
}
