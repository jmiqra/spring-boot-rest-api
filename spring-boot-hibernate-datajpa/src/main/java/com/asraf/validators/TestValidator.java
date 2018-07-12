package com.asraf.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestConstraint, String> {

	@Override
	public void initialize(TestConstraint test) {
	}

	@Override
	public boolean isValid(String testField, ConstraintValidatorContext cxt) {
		return testField != null && testField.matches("[0-9]+") && (testField.length() > 8)
				&& (testField.length() < 14);
	}

}