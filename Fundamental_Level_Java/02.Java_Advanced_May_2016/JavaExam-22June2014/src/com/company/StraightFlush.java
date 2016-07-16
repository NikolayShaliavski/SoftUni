package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class StraightFlush {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("[,\\s]+");
        HashSet<String> cards = new HashSet<>();
        cards.addAll(Arrays.asList(input)); // addAll mehtod cannot take simple Array, so we convert it to ArrayList -> need Collection
        boolean hasFlush = true;
        for (String card : cards) {
            String face = card.substring(0, card.length() - 1);
            String suit = card.substring(card.length() - 1);
            ArrayList<String> flush = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                flush.add(face + suit);
                face = getCardFace(face);
            }
            if (cards.containsAll(flush)) {
                hasFlush = false;
                System.out.println(flush);
            }
        }
        if (hasFlush) {
            System.out.println("No Straight Flushes");
        }
    }
    public static String getCardFace(String card) {
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (int i = 1; i < faces.length; i++) {
            if (faces[i - 1].equals(card)) {
                return faces[i];
            }
        }
        return null;
    }

}
