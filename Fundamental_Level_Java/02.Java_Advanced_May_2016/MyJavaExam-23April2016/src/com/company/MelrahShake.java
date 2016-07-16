package com.company;

import java.util.Scanner;

public class MelrahShake {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        String patternInput = scan.nextLine();

        int first = line.indexOf(patternInput);
        int last = line.lastIndexOf(patternInput);
        //if there are more than one or zero matches, first will be < than last
        while (first < last && patternInput.length() > 0) {

            line = line.substring(0, first) + line.substring(first + patternInput.length(), last) + line.substring(last + patternInput.length());
            int position = patternInput.length() / 2;
            StringBuilder builder = new StringBuilder(patternInput);
            builder.deleteCharAt(position);
            patternInput = builder.toString();
            System.out.println("Shaked it.");
            first = line.indexOf(patternInput);
            last = line.lastIndexOf(patternInput);
            //if indexOf && lastIndexOf match same substring (because there is only one occurence of this substring in line)
            //first == last
            //if there are no matches (zero occurences of substring in line)
            //first == last == -1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -> returns -1
        }
        System.out.println("No shake.");
        System.out.println(line);
    }
}


