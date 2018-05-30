package com.asraf.dtos.response.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RequestBodyResponseDto {

	private List<RequestField> fields = new ArrayList<>();

	public void addRequestField(RequestField requestField) {
		fields.add(requestField);
	}

}
