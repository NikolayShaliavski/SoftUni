package com.weddings_planner.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

	private static final String REGEX = "^(([a-zA-Z0-9]+)@([a-z]+.[a-z]+))$";
	private Pattern pattern;
	private Matcher matcher;
	
	public EmailValidator() {
		this.pattern = Pattern.compile(REGEX);
	}

	public boolean isValid(String email) {
		this.matcher = this.pattern.matcher(email);
		if (this.matcher.find()) {
			return true;
		}
		return false;
	}
}
