package com.asraf.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestConstraint, Object> {

	private String baseField;
	private String matchField;

	@Override
	public void initialize(TestConstraint constraint) {
		baseField = constraint.baseField();
		matchField = constraint.matchField();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Object baseFieldValue = getFieldValue(object, baseField);
			Object matchFieldValue = getFieldValue(object, matchField);
			// return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
			boolean a = false;
			if((baseFieldValue != null && matchFieldValue != null)
					|| (baseFieldValue == null && matchFieldValue != null)
					|| (baseFieldValue == null && matchFieldValue == null)) {
				a = true;
			}
			return a;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

}