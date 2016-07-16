import java.io.*;

public class Problem04_MergeFiles {

    public static void main(String[] args) {
        String pathIn01 = "files\\Problem_04\\02_FileOne.txt";
        String pathIn02 = "files\\Problem_04\\02_FileTwo.txt";

        File merged = new File("files\\Problem_04\\Merged_02.txt");

        try (BufferedReader reader1 = new BufferedReader(new FileReader(pathIn01));
             BufferedReader reader2 = new BufferedReader(new FileReader(pathIn02));
             BufferedWriter writer = new BufferedWriter(new FileWriter(merged))) {

            String line1 = null;
            String line2 = null;

            while ((line1 = reader1.readLine()) != null) {
                line2 = reader2.readLine();
                if (line1 != null) {
                    writer.write(line1);
                    writer.newLine();
                }
                if (line2 != null) {
                    writer.write(line2);
                    writer.newLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
