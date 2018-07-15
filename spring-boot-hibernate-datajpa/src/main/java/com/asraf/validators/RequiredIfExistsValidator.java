package com.asraf.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.asraf.utils.StringUtils;

public class RequiredIfExistsValidator implements ConstraintValidator<RequiredIfExistsConstraint, Object> {

	private String baseField;
	private String dependentField;
	// private String[] stringList;

	@Override
	public void initialize(RequiredIfExistsConstraint constraint) {
		baseField = constraint.baseField();
		dependentField = constraint.dependentField();
		// stringList = constraint.stringList();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Object baseFieldValue = getFieldValue(object, baseField);
			Object dependentFieldValue = getFieldValue(object, dependentField);
			boolean flag = false;
			if (baseFieldValue != null && !StringUtils.isNullOrWhitespace(dependentFieldValue.toString())) {
				flag = true;
			}
			return flag;
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