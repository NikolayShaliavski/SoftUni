package com.jsonexercise.io.readers;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FileReader implements Reader {

    @Override
    public String read(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try(InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(is))) {

            String line = null;
            while ((line = bfr.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
