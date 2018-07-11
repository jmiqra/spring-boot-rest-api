package com.asraf.dtos.request;

import com.asraf.validators.TwoSidedConditionalConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FooRequestDto extends BaseRequestDto {

	//TwoSidedConditionalConstraint(first = "phoneNo1", second = "phoneNo2")
	private String phoneNo1;
	
	@TwoSidedConditionalConstraint(first = "phoneNo1", second = "phoneNo2")
	private String phoneNo2; 
	
}
