package com.jsonexercise.io.writers;

import org.springframework.stereotype.Component;

import java.io.*;

@Component(value = "FileWriter")
public class FileWriter implements Writer {

    @Override
    public void write(String content, String... fileName) throws IOException {
        try (OutputStream os = new FileOutputStream(fileName[0]);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(content));
        }
    }
}
