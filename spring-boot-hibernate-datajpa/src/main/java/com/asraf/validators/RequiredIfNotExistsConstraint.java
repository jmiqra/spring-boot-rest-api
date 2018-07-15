package com.asraf.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RequiredIfNotExistsValidator.class})
@Documented
public @interface RequiredIfNotExistsConstraint {
 
    String message() default "Invalid dependenting";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
    String baseField();
 
    String dependentField();
    
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
    	RequiredIfNotExistsConstraint[] value();
    }
 
}