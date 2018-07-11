package com.asraf.dtos.request;

import com.asraf.validators.NotNullIfAnotherFieldHasValue;

public class SampleRequestDto {
	
	private String status;
	@NotNullIfAnotherFieldHasValue(fieldName = "status", fieldValue = "Canceled", dependFieldName = "fieldOne")
	private String fieldOne;
	@NotNullIfAnotherFieldHasValue(fieldName = "status", fieldValue = "Canceled", dependFieldName = "fieldTwo")
	private String fieldTwo;

	// getters and setters omitted
}