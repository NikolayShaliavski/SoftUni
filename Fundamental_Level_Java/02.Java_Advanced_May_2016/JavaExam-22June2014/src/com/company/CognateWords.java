package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CognateWords {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("[\\W\\d]+");
        List<String> words = Arrays.asList(input);
        HashMap<String, String> out = new HashMap<>();
        boolean hasCognate = false;

        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (words.contains(words.get(i) + words.get(j))) {
                    hasCognate = true;
                    String cognateWord = words.get(i) + "|" + words.get(j);
                    String value = words.get(i) + words.get(j);
                    out.put(cognateWord, value);
                }
            }
        }
        if (hasCognate) {
            out.forEach((key, value) -> System.out.println(key + "=" + value));
        } else {
            System.out.println("No");
        }
    }
}
