import java.io.*;

public class Problem01_OddLines {

    public static void main(String[] args) {

        String pathIn = "files\\Problem_01\\03_OddLinesIn.txt";
        File pathOut = new File("files\\Problem_01\\03_OddLinesOutMy.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(pathIn));
             BufferedWriter writer = new BufferedWriter(new FileWriter(pathOut))) {

            String line = null;
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                if (counter % 2 != 0) {
                    writer.write(line);
                    writer.newLine();
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
