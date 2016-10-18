package problem_10;

import problem_10.interfaces.Tuple;
import problem_10.tuples.TupleImpl;

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
        Tuple<String, String>firstTuple = new TupleImpl<>(name, address);
        System.out.println(firstTuple);

        line = reader.readLine().split("\\s+");
        name = line[0];
        int litres = Integer.valueOf(line[1]);
        Tuple<String, Integer> secondTuple = new TupleImpl<>(name, litres);
        System.out.println(secondTuple);

        line = reader.readLine().split("\\s+");
        int leftNumber = Integer.valueOf(line[0]);
        double rightNumber = Double.valueOf(line[1]);
        Tuple<Integer, Double> thirdTuple = new TupleImpl<>(leftNumber, rightNumber);
        System.out.println(thirdTuple);
    }

}
