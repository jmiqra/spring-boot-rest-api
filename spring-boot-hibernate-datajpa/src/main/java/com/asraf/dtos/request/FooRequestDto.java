package com.asraf.dtos.request;

import com.asraf.validators.TestConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TestConstraint(baseField = "field1", matchField = "field2")
public class FooRequestDto extends BaseRequestDto {

	private String field1;
	private String field2;

}
