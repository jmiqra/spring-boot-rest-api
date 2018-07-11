package com.asraf.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TwoSidedConditionalValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TwoSidedConditionalConstraint {
	
    
	String message() default "Invalid value";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();
	
//    @Target({TYPE, ANNOTATION_TYPE})
//    @Retention(RUNTIME)
//    @Documented
//            @interface List
//    {
//        FieldMatch[] value();
//    }
    
}