import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipArchive {

    public static void main(String[] args) {

        String file01 = "resourses\\count-chars.txt";
        String file02 = "resourses\\words.txt";
        String file03 = "resourses\\lines.txt";
        File zip = new File("resourses\\text-files.zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip))) {

            addToZip(file01, zos);
            addToZip(file02, zos);
            addToZip(file03, zos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToZip (String file, ZipOutputStream zos) {
        File fileName = new File(file);

        try (FileInputStream fis = new FileInputStream(fileName)) {

            ZipEntry entry = new ZipEntry(file);
            zos.putNextEntry(entry);

            byte[] bytes = new byte[1024];
            int length;

            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
