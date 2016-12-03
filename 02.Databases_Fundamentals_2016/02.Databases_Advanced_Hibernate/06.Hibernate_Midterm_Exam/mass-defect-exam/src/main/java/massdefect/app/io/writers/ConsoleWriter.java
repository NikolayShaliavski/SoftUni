package massdefect.app.io.writers;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "ConsoleWriter")
public class ConsoleWriter implements Writer {

    @Override
    public void write(String content, String... fileName) throws IOException {
        System.out.println(content);
    }
}
