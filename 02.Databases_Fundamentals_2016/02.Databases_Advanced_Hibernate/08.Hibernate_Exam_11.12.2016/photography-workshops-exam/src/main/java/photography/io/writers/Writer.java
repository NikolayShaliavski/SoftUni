package photography.io.writers;

import java.io.IOException;

public interface Writer {

    void write(String content, String... fileName) throws IOException;
}
