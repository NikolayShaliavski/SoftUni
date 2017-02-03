package fileWriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SurveyFileWriter {

    private static File file;

    public static void write(String value, String absolutePath) {
        createFileIfNotExist(absolutePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            writer.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFileIfNotExist(String absolutePath) {
        Path path = Paths.get(absolutePath);
        if (Files.notExists(path)) {
            file = new File(absolutePath);
        } else {
            file = path.toFile();
        }
    }
}
