import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem12_LittleJohn {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arrows = new String[4];

        for (int i = 0; i < arrows.length; i++) {
            arrows[i] = reader.readLine();
        }
        int smallArrows = 0;
        int mediumArrows = 0;
        int largeArrows = 0;

        Pattern pattern = Pattern.compile("(>>>----->>)|(>>----->)|(>----->)");
        Matcher matcher;
        for (String arrow : arrows) {
            matcher = pattern.matcher(arrow);

            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    largeArrows++;
                } else if (matcher.group(2) != null) {
                    mediumArrows++;
                } else {
                    smallArrows++;
                }
            }

        }
        StringBuilder builder = new StringBuilder();
        builder.append(smallArrows).append(mediumArrows).append(largeArrows);
        int numberAppended = Integer.valueOf(builder.toString());
        String binary = Integer.toBinaryString(numberAppended);
        builder.delete(0, builder.length());
        builder.append(binary);
        builder.reverse();
        builder.insert(0, binary);

        int result = Integer.parseInt(builder.toString(), 2);
        System.out.println(result);
    }
}
