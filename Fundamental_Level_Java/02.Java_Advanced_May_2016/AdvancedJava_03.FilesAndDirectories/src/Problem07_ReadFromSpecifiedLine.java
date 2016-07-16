import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem07_ReadFromSpecifiedLine {

    public static void main(String[] args) {
        String path = "files\\Problem_07\\File1.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int counter = 1;
            String line = null;

            while ((line = reader.readLine()) != null) {
                if (counter > 100) {
                    System.out.println(line);
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
