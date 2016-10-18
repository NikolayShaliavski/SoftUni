import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem14_LettersChangeNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split("\\s+");
        double sum = 0.0;
        for (int i = 0; i < line.length; i++) {
            Character startLetter = line[i].charAt(0);
            Character endLetter = line[i].charAt(line[i].length() - 1);
            String numberString = line[i].substring(1, line[i].length() - 1);
            int number = Integer.parseInt(numberString);

            if (Character.isUpperCase(startLetter)) {
                sum += ((double) number / (startLetter - 64));
            } else {
                sum += ((long) number * (startLetter - 96));
            }

            if (Character.isUpperCase(endLetter)) {
                sum -= (endLetter - 64);
            } else {
                sum += (endLetter - 96);
            }
        }
        System.out.printf("%.2f%n", sum);
    }
}
