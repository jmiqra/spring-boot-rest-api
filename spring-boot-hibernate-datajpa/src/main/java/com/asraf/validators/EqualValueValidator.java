package com.asraf.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualValueValidator implements ConstraintValidator<EqualValueConstraint, String> {

	@Override
	public void initialize(EqualValueConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
		return contactField != null && contactField.equals("iqrah");
	}

}