package com.weddings_planner.io.parsers.fileParsers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weddings_planner.io.readers.Reader;

@Component(value = "JsonParser")
public class JsonParser implements Parser {

	private Gson gson;

	@Autowired
	private Reader fileReader;

	// @Autowired
	// @Qualifier(value = "FileWriter")
	// private Writer fileWriter;

	public JsonParser() {
		this.setGson(new GsonBuilder().setPrettyPrinting().serializeNulls().create());
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	@Override
	public <T> T read(Class<T> classes, String file) throws FileNotFoundException, IOException {
		String fileData = this.fileReader.read(file);
		T object = this.getGson().fromJson(fileData, classes);
		return object;
	}

	@Override
	public <T> void write(T object, String file) {
		// TODO Auto-generated method stub

	}
}
