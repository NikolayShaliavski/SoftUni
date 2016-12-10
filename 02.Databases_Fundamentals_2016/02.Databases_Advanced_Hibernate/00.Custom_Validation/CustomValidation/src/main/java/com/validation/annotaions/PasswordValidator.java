package com.validation.annotaions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;
    private boolean containsDigit;
    private boolean containsLowerCase;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowerCase = password.containsLowerCase();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.length() < this.minLength) {
            return false;
        }
        if (s.length() > this.maxLength) {
            return false;
        }
        boolean containsDigit = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                containsDigit = true;
                break;
            }
        }
        if (containsDigit != this.containsDigit) {
            return false;
        }
        boolean containsLowerCase = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                containsLowerCase = true;
                break;
            }
        }
        if (containsLowerCase != this.containsLowerCase) {
            return false;
        }
        return true;
    }
}
