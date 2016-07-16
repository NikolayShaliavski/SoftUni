package problem_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P05_FibonacciNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.valueOf(reader.readLine());
        int end = Integer.valueOf(reader.readLine());
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.getFibInRange(start, end).toString().replace("[", "").replace("]", ""));
    }

}

class Fibonacci {

    private List<Long> fibNumbers;

    public Fibonacci() {
        this.fibNumbers = new ArrayList<>();
    }

    public List<Long> getFibInRange(int start, int end) {
        long f1 = 0;
        long f2 = 1;
        this.fibNumbers.add(f1);
        this.fibNumbers.add(f2);
        for (int i = 0; i < end; i++) {
            long temp = f1 + f2;
            f1 = f2;
            f2 = temp;
            this.fibNumbers.add(temp);
        }
        return fibNumbers.subList(start, end);
    }
}