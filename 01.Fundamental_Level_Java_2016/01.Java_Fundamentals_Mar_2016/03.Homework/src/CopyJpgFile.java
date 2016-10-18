import java.io.*;

public class CopyJpgFile {

    public static void main(String[] args) {

        File picture = new File("resourses\\picture.jpg");
        File copy = new File("resourses\\my-copied-picture.jpg");

        try (FileInputStream fis = new FileInputStream(picture); FileOutputStream fos = new FileOutputStream(copy)) {

            //this will read all bytes from the image in one line
            //byte[] buffer = Files.readAllBytes(picture.toPath());

            int current = fis.read();

            while (current != -1) {
                fos.write(current);
                current = fis.read();
            }

            System.out.println();

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
