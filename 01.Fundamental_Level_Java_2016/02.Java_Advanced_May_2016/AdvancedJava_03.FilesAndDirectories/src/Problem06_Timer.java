import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem06_Timer {

    public static void main(String[] args) {

        String path01 = "files\\Problem_06\\File1.csv";
        String path02 = "files\\Problem_06\\File2.txt";

        try (BufferedReader reader1 = new BufferedReader(new FileReader(path01));
             BufferedReader reader2 = new BufferedReader(new FileReader(path02))) {

            long startFirst = System.nanoTime();
            while (reader1.readLine() != null){
            }
            long timeFirst = System.nanoTime();
            timeFirst -= startFirst;
            long startSecond = System.nanoTime();
            while (reader2.readLine() != null) {
            }
            long timeSecond = System.nanoTime();
            timeSecond -= startSecond;
            System.out.println(timeFirst);
            System.out.println(timeSecond);

            if (timeFirst < timeSecond) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
