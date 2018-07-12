package com.asraf.dtos.request;

import com.asraf.validators.TestConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FooRequestDto extends BaseRequestDto {

	@TestConstraint
	private String phoneNo;

	// //TwoSidedConditionalConstraint(first = "phoneNo1", second = "phoneNo2")
	// private String phoneNo1;
	//
	// @TwoSidedConditionalConstraint(first = "phoneNo1", second = "phoneNo2")
	// private String phoneNo2;

}
