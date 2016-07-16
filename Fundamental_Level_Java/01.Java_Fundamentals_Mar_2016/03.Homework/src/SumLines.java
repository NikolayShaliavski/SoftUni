import java.io.*;

public class SumLines {

    public static void main(String[] args) {

        File path = new File("resourses\\lines.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line = reader.readLine();

            while (line != null) {
                int sum = 0;
                for (int i = 0; i < line.length(); i++) {
                    sum += line.charAt(i);

                }
                System.out.println(sum);
                line = reader.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            System.out.println("File not found.");;
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
