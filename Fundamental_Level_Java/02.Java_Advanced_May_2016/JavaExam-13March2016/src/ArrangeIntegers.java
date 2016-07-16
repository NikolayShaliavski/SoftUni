import java.util.*;

public class ArrangeIntegers {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String[] line = scn.nextLine().split("[ ,]+");
        List<String> sorted = new ArrayList<>();

        for (int i = 0; i < line.length; i++) {
            String digit = line[i];
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < digit.length(); j++) {
                String current = null;
                switch (digit.charAt(j)) {
                    case '0':
                        current = "zero";
                        break;
                    case '1':
                        current = "one";
                        break;
                    case '2':
                        current = "two";
                        break;
                    case '3':
                        current = "three";
                        break;
                    case '4':
                        current = "four";
                        break;
                    case '5':
                        current = "five";
                        break;
                    case '6':
                        current = "six";
                        break;
                    case '7':
                        current = "seven";
                        break;
                    case '8':
                        current = "eight";
                        break;
                    case '9':
                        current = "nine";
                        break;
                }
                builder.append(current);
                if (j < digit.length() - 1) {
                    builder.append("-");
                }
            }
            sorted.add(builder.toString());
        }
        Collections.sort(sorted);

        for (int i = 0; i < sorted.size(); i++) {
            String[] digit = sorted.get(i).split("-");
            for (int j = 0; j < digit.length; j++) {
                String current = null;
                switch (digit[j]) {
                    case "zero":
                        current = "0";
                        break;
                    case "one":
                        current = "1";
                        break;
                    case "two":
                        current = "2";
                        break;
                    case "three":
                        current = "3";
                        break;
                    case "four":
                        current = "4";
                        break;
                    case "five":
                        current = "5";
                        break;
                    case "six":
                        current = "6";
                        break;
                    case "seven":
                        current = "7";
                        break;
                    case "eight":
                        current = "8";
                        break;
                    case "nine":
                        current = "9";
                        break;
                }
                System.out.print(current);
            }
            if (i < sorted.size() - 1) {
                System.out.print(", ");
            }
        }
    }
}
