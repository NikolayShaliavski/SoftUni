package com.weddings_planner.validation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.weddings_planner.validation.annotations.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {

	private static final String REGEX = "^(([a-zA-Z0-9]+)@([a-z]+.[a-z]+))$";
	private Pattern pattern;
	private Matcher matcher;

	public void initialize(Email email) {
		this.pattern = Pattern.compile(REGEX);
	}

	public boolean isValid(String email, ConstraintValidatorContext context) {
		this.matcher = this.pattern.matcher(email);
		if (this.matcher.find()) {
			return true;
		}
		return false;
	}

}
