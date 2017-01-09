package pr01_Sowing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sowing {

    private static int seeds;
    /**
     * For very big outputs printing on the console is problem for efficiency
     * so we save results in StringBuilder and print it at the end of the program
     */
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        /**
         * When parsing trim input line to avoid exceptions
         */
        seeds = Integer.valueOf(bf.readLine().trim());
        String[] field = bf.readLine().split("[\\s+]");
        sb = new StringBuilder();
        sow(field, 0, 0);
        System.out.println(sb.toString().trim());
    }

    private static void sow(String[] field, int n, int index) {
        if (n >= seeds) {
            if (canGrow(field)) {
                save(field);
            }
            return;
        }
        for (int i = index; i < field.length; i++) {
            if (field[i].equals("1")) {
                field[i] = ".";
                sow(field, n + 1, i + 2);
                field[i] = "1";
            }
        }
    }

    private static boolean canGrow(String[] field) {
        boolean canGrow = true;
        for (int i = 1; i < field.length - 1; i++) {
            if (field[i].equals(".")) {
                if (field[i - 1].equals(".") || field[i + 1].equals(".")) {
                    canGrow = false;
                    break;
                }
            }
        }

        return canGrow;
    }

    private static void save(String[] field) {
        for (int i = 0; i < field.length; i++) {
            sb.append(field[i]);
        }
        sb.append(System.lineSeparator());
    }
}
