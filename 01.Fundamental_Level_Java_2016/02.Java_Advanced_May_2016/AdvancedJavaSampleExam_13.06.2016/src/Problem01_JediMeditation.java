import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem01_JediMeditation {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder meditation = new StringBuilder();
        StringBuilder masters = new StringBuilder();
        StringBuilder knights = new StringBuilder();
        StringBuilder padawans = new StringBuilder();
        StringBuilder players = new StringBuilder();

        int numberOfLines = Integer.valueOf(reader.readLine());
        for (int i = 0; i < numberOfLines; i++) {
            meditation.append(reader.readLine());
            meditation.append(" ");
        }
        String[] tokens = meditation.toString().split("[\\s]+");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].startsWith("m")) {
                masters.append(tokens[i] + " ");
            } else if (tokens[i].startsWith("k")) {
                knights.append(tokens[i] + " ");
            } else if (tokens[i].startsWith("p")) {
                padawans.append(tokens[i] + " ");
            } else if (tokens[i].startsWith("t") || tokens[i].startsWith("s")) {
                players.append(tokens[i] + " ");
            }
        }
        if (meditation.toString().contains("y")) {
            String out = yodaOrder(masters, knights, padawans, players);
            System.out.println(out);
        } else {
            String out = nonYodaOrder(masters, knights, padawans, players);
            System.out.println(out);
        }
    }

    private static String nonYodaOrder(StringBuilder masters, StringBuilder knights, StringBuilder padawans, StringBuilder players) {
        StringBuilder out = new StringBuilder();
        out.append(players.toString().trim() + " ");
        out.append(masters.toString().trim() + " ");
        out.append(knights.toString().trim() + " ");
        out.append(padawans.toString().trim());
        return out.toString();
    }

    private static String yodaOrder(StringBuilder masters, StringBuilder knights, StringBuilder padawans, StringBuilder players) {
        StringBuilder out = new StringBuilder();
        out.append(masters.toString().trim() + " ");
        out.append(knights.toString().trim() + " ");
        out.append(players.toString().trim() + " ");
        out.append(padawans.toString().trim());
        return out.toString();
    }
}
