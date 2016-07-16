import java.io.*;

public class Problem02_LineNumbers {

    public static void main(String[] args) {

        String pathIn = "files\\Problem_02\\03_LinesIn.txt";
        String pathOut = "files\\Problem_02\\03_LinesOutMy.txt";

        File file = new File(pathOut);

        try (BufferedReader reader = new BufferedReader(new FileReader(pathIn));
             BufferedWriter writer = new BufferedWriter(new FileWriter(pathOut))) {

            String line = null;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                line = counter + ". " + line.replaceAll("\\s+$", "");
                writer.write(line);
                writer.newLine();
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
