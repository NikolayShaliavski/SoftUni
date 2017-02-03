package calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HTMLReader {

    public static String readHTML(String htmlPath) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader bf = new BufferedReader(new FileReader(htmlPath))) {
            String line = bf.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bf.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
