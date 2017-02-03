package fileWriters;

import java.io.*;

public class GreetingsFileWriter {

    public static void append(String path, String value, Boolean toAppend) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, toAppend))) {

            writer.write(value + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
