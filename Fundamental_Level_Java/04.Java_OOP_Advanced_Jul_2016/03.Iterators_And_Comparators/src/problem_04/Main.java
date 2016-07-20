package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] integers = Arrays.stream(reader.readLine().split("[\\s,]+")).
                mapToInt(Integer::parseInt).toArray();
        String endLine = reader.readLine();
        Lake lake = new Lake(integers);

        List<Integer> resultFromJumping = new ArrayList<>();
        for (Integer integer : lake) {
            resultFromJumping.add(integer);
        }
        String result = resultFromJumping.toString().replaceAll("[\\[\\]]", "");
        System.out.println(result);
    }
}
