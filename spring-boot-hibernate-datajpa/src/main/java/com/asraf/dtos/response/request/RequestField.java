package com.asraf.dtos.response.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestField {
	private String name;
	private String type;
	private String value;
	private FieldValidations validations;
}
