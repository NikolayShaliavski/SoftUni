package problem_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P03_LastDigitName {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.valueOf(reader.readLine());
        Number number = new Number(num);
        System.out.println(number.getLastDigit());
    }

}
class Number {
    private Integer number;

    public Number(Integer number) {
        this.number = number;
    }

    public String getLastDigit() {
        String lastDigit = "";
        switch (this.number% 10) {
            case 0:
                lastDigit = "zero";
                break;
            case 1:
                lastDigit = "one";
                break;
            case 2:
                lastDigit = "two";
                break;
            case 3:
                lastDigit = "three";
                break;
            case 4:
                lastDigit = "four";
                break;
            case 5:
                lastDigit = "five";
                break;
            case 6:
                lastDigit = "six";
                break;
            case 7:
                lastDigit = "seven";
                break;
            case 8:
                lastDigit = "eight";
                break;
            case 9:
                lastDigit = "nine";
                break;
        }
        return lastDigit;
    }
}