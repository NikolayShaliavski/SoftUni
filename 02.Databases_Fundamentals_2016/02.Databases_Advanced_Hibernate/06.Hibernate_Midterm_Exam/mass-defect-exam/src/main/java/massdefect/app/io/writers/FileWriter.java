package massdefect.app.io.writers;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileWriter implements Writer {

    @Override
    public void write(String fileName, String content) throws IOException {
        try (OutputStream os = new FileOutputStream(fileName);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(content));
        }
    }
}
