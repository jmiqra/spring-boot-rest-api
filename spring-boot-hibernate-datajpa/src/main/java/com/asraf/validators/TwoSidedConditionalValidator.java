package com.asraf.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class TwoSidedConditionalValidator implements ConstraintValidator<TwoSidedConditionalConstraint, Object> {

	private String firstFieldName;
	private String secondFieldName;

	@Override
	public void initialize(final TwoSidedConditionalConstraint annotation) {
		firstFieldName = annotation.first();
		secondFieldName = annotation.second();
		System.out.println("init");
	}

	@Override
	public boolean isValid( final Object value, final ConstraintValidatorContext context) {
		System.out.println("is valid");
		
		//BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
		//System.out.println(wrapper);
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

			System.out.println(firstObj);
			System.out.println(secondObj);
			return true;
//			return firstObj != null && secondObj != null || firstObj == null && secondObj != null;// firstObj == null && secondObj == null || firstObj !=
																// null && firstObj.equals(secondObj);
		} catch (final Exception ignore) {
			// ignore
			System.out.println(" *** " + ignore);
		}
		return true;

	}
}