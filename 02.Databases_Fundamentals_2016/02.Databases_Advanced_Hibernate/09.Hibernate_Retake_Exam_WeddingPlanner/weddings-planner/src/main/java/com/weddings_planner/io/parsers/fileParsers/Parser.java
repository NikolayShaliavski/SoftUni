package com.weddings_planner.io.parsers.fileParsers;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Parser {

	<T> T read(Class<T> classes, String file) throws FileNotFoundException, IOException;

	<T> void write(T object, String file);
}
