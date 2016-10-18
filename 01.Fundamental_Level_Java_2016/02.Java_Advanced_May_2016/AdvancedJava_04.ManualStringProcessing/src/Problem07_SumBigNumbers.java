import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem07_SumBigNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstNum = reader.readLine();
        String secondNum = reader.readLine();
        StringBuilder padded = new StringBuilder();
        StringBuilder sum = new StringBuilder();
        int length = 0;

        if (firstNum.length() > secondNum.length()) {
            length = firstNum.length() - secondNum.length();
            padded.append(secondNum);
            secondNum = paddingString(padded, length);
        } else if (secondNum.length() > firstNum.length()) {
            length = secondNum.length() - firstNum.length();
            padded.append(firstNum);
            firstNum = paddingString(padded, length);
        }
        System.out.println(sumNumbers(sum, firstNum, secondNum));
    }

    private static String sumNumbers(StringBuilder sum, String first, String second) {
        int currentSum = 0;
        int reminder = 0;
        for (int i = first.length() - 1; i >= 0; i--) {
            int firstDigit = Integer.parseInt(first.charAt(i) +"");
            int secondDigit = Integer.parseInt(second.charAt(i) + "");
            currentSum = firstDigit + secondDigit + reminder;
            if (currentSum >= 10) {
                sum.insert(0, currentSum % 10);
                reminder = 1;
            } else {
                sum.insert(0, currentSum);
                reminder = 0;
            }
        }
        if (reminder > 0) {
            sum.insert(0, reminder);
        }
        if (sum.toString().startsWith("0")) {
            while (sum.charAt(0) == '0') {
                sum.deleteCharAt(0);
            }
        }
        return sum.toString();
    }
    private static String paddingString(StringBuilder padded, int length) {
        for (int i = 0; i < length; i++) {
             padded.insert(0, 0);
        }
        return padded.toString();
    }
}
