package com.weddings_planner.io.writers;

import org.springframework.stereotype.Component;

@Component(value = "ConsoleWriter")
public class ConsoleWriter implements Writer {

	@Override
	public void write(String content, String... fileName) {
		System.out.println(content);
	}
}
