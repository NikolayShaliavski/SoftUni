package problem_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P07_BasicMath {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String command = tokens[0];
            double num1 = Double.valueOf(tokens[1]);
            double num2 = Double.valueOf(tokens[2]);

            switch (command) {
                case "Sum":
                    System.out.printf("%.2f%n", MathUtil.sumNumbers(num1, num2));
                    break;
                case "Subtract":
                    System.out.printf("%.2f%n", MathUtil.subtractNumbers(num1, num2));
                    break;
                case "Multiply":
                    System.out.printf("%.2f%n", MathUtil.multiplyNumbers(num1, num2));
                    break;
                case "Divide":
                    System.out.printf("%.2f%n", MathUtil.divideNumbers(num1, num2));
                    break;
                case "Percentage":
                    try {
                        System.out.printf("%.2f%n", MathUtil.percentage(num1, num2));
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }

                    break;
            }

            line = reader.readLine();
        }
    }
}
class MathUtil {

    public static double sumNumbers(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtractNumbers(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiplyNumbers(double num1, double num2) {
        return num1 * num2;
    }

    public static double divideNumbers(double num1, double num2) {
        return num1 / num2;
    }

    public static double percentage(double num1, double percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("Percentage cannot be less than zero!");
        }
        return (num1 * percent) / 100;
    }
}