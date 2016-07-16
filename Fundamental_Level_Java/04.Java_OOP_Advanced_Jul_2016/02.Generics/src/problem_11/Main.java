package problem_11;

import problem_11.interfaces.Threeuple;
import problem_11.tuples.ThreeupleImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        System.in
                )
        );

        String[] line = reader.readLine().split("\\s+");
        String name = line[0] + " " + line[1];
        String address = line[2];
        String town = line[3];
        Threeuple<String, String, String> firstThreeuple = new ThreeupleImpl<>(name, address, town);
        System.out.println(firstThreeuple);

        line = reader.readLine().split("\\s+");
        name = line[0];
        int litres = Integer.valueOf(line[1]);
        String drunk = line[2];
        boolean isDrunk = drunk.equals("drunk");
        Threeuple<String, Integer, Boolean> secondThreeuple = new ThreeupleImpl<>(name, litres, isDrunk);
        System.out.println(secondThreeuple);

        line = reader.readLine().split("\\s+");
        name = line[0];
        double amount = Double.valueOf(line[1]);
        String bank = line[2];
        Threeuple<String, Double, String> thirdThreeuple = new ThreeupleImpl<>(name, amount, bank);
        System.out.println(thirdThreeuple);
    }

}
