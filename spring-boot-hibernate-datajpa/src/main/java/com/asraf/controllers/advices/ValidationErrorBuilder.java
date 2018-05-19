package com.asraf.controllers.advices;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

	public static ValidationError fromBindingErrors(Errors errors) {
		ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			FieldError fieldError = (FieldError) objectError;
			error.addValidationError(fieldError.getField() + " " + objectError.getDefaultMessage());
		}
		return error;
	}

}
