package com.weddings_planner.io.readers;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Reader {

	String read(String filePath) throws FileNotFoundException, IOException;
}
