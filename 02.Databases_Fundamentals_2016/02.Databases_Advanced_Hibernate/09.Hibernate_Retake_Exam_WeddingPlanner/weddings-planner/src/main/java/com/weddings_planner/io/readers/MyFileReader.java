package com.weddings_planner.io.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class MyFileReader implements Reader {

	@Override
	public String read(String filePath) throws FileNotFoundException, IOException {

		FileReader in = new FileReader(filePath);
		BufferedReader bf = new BufferedReader(in);
		StringBuilder content = new StringBuilder();

		String line = bf.readLine();
		while (line != null) {
			content.append(line);
			line = bf.readLine();
		}
		return content.toString();
	}
}
