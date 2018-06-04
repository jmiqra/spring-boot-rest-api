package com.asraf.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.asraf.utils.StringUtils;

public class EnumValueValidator implements ConstraintValidator<EnumValueConstraint, String> {
	private EnumValueConstraint annotation;

	@Override
	public void initialize(EnumValueConstraint annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
		boolean result = false;

		Object[] enumValues = this.annotation.enumClass().getEnumConstants();

		if (!StringUtils.isNullOrWhitespace(valueForValidation) && enumValues != null) {
			for (Object enumValue : enumValues) {
				if (valueForValidation.equals(enumValue.toString()) || (this.annotation.ignoreCase()
						&& valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

}