package com.asraf.dtos.request;

import com.asraf.validators.EqualValueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FooRequestDto extends BaseRequestDto {

	@EqualValueConstraint(message="my name is iqrah")
	private String phoneNo;
	
}
