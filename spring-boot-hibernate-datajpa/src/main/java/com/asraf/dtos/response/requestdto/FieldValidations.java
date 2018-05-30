package com.asraf.dtos.response.requestdto;

import java.util.Map;

import lombok.Data;

@Data
public class FieldValidations {
	private Boolean isRequired;
	private Boolean isEmail;
	private Long minValue;
	private Long maxValue;
	private Integer minSize;
	private Integer maxSize;
	private String pattern;
	private Map<String, Object> options;
}
