package com.jsonexercise.io.readers;

import java.io.IOException;

public interface Reader {

    String read(String filePath) throws IOException;
}
