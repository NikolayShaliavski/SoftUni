package massdefect.app.io.writers;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Writer {

    void write(String fileName, String content) throws IOException;
}
