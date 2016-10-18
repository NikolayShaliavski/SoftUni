import java.io.*;

public class ReadAndWriteInFiles {

    public static final String inPath = "C:\\Users\\Nick\\Desktop\\Java Fundamentals-Mar2016\\03.Homework\\Exercise\\users.txt";
    public static final String outPath = "C:\\Users\\Nick\\Desktop\\Java Fundamentals-Mar2016\\03.Homework\\Exercise\\total.txt";

    public static void main(String[] args) {

        File in = new File(inPath);
        File out = new File(outPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             FileWriter fw = new FileWriter(out, true); PrintWriter writer = new PrintWriter(fw, true)) {

            String input = reader.readLine();//reading first line from file

            while (input != null) {
                String[] line = input.split(" ");
                String user = line[0];
                int total = 0;

                for (int i = 1; i < line.length; i++) {
                    String[] currentLog = line[i].split(":");
                    int hours = Integer.parseInt(currentLog[0]);
                    int minutes = Integer.parseInt(currentLog[1]);
                    total += (hours * 60) + minutes;
                }

                String toPrint = user + " " + total;
                int minutes = total % 60;
                total /= 60;
                int hours = total % 60;
                total /= 60;
                int days = total % 24;
                toPrint += " (" + days + " days, " + hours + " hours, " + minutes + " minutes)";
                writer.println(toPrint);
                input = reader.readLine();//reading next line from file!!!!!!!!!!!!!!!!
            }

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
