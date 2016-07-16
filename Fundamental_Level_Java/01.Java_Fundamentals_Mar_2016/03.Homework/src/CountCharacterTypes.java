import java.io.*;

public class CountCharacterTypes {

    public static void main(String[] args) {

        File inPath = new File("resourses\\words.txt");
        File outPath = new File("resourses\\count-chars.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inPath));
             PrintWriter writer = new PrintWriter(new FileWriter(outPath))) {

            int vowels = 0;
            int consonants = 0;
            int marks = 0;

            String line = reader.readLine();

            while (line != null) {

                for (int i = 0; i < line.length(); i++) {

                    if (line.charAt(i) == ' ') {
                        continue;
                    } else if (line.charAt(i) == 'a' || line.charAt(i) == 'e' ||
                            line.charAt(i) == 'i' || line.charAt(i) == 'o' ||
                            line.charAt(i) == 'u') {
                        vowels++;
                    } else if (line.charAt(i) == '?' || line.charAt(i) == '!' ||
                            line.charAt(i) == '.' || line.charAt(i) == ',') {
                        marks++;
                    } else {
                        consonants++;
                    }
                }
                line = reader.readLine();
            }
            writer.println("Vowels: " + vowels);
            writer.println("Consonants: " + consonants);
            writer.println("Punctuation: " + marks);

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
