package com.asraf.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestConstraint, Object> {

	private String baseField;
	private String dependentField;

	@Override
	public void initialize(TestConstraint constraint) {
		baseField = constraint.baseField();
		dependentField = constraint.dependentField();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Object baseFieldValue = getFieldValue(object, baseField);
			Object dependentFieldValue = getFieldValue(object, dependentField);
			// return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
			boolean a = false;
			if((baseFieldValue != null && dependentFieldValue != null)
					|| (baseFieldValue == null && dependentFieldValue != null)
					|| (baseFieldValue == null && dependentFieldValue == null)) {
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