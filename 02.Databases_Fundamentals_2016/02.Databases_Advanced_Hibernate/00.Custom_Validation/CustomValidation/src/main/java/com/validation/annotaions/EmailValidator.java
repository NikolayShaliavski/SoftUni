package com.validation.annotaions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * for every custom annotation should have validator class
 * types in <Email, String> are annotation and type of the field, which is annotated
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    private int minLength;

    @Override
    public void initialize(Email email) {
        this.minLength = email.minLength();
    }

    /**
     * Here is the logic for validating
     * @param s - field, which we validate
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() >= this.minLength;
    }
}
