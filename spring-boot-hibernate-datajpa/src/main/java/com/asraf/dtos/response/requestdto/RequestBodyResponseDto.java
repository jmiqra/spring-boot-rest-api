package com.asraf.dtos.response.requestdto;

import java.util.ArrayList;
import java.util.List;

import com.asraf.dtos.response.BaseResponseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RequestBodyResponseDto extends BaseResponseDto {

	private List<RequestField> fields = new ArrayList<>();

	public void addRequestField(RequestField requestField) {
		fields.add(requestField);
	}

}
