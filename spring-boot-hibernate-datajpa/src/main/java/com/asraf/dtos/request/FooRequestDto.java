package com.asraf.dtos.request;

import com.asraf.validators.RequiredIfExistsConstraint;
import com.asraf.validators.RequiredIfNotExistsConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
//@RequiredIfExistsConstraint.List({
//		@RequiredIfExistsConstraint(baseField = "field1", dependentField = "field2", message = "1st annonation error"),
//		// @RequiredIfExistsConstraint(baseField = "field2", dependentField = "field1")
//})
@RequiredIfNotExistsConstraint.List({
	@RequiredIfNotExistsConstraint(baseField = "field1", dependentField = "field2", message = "1st annonation error"),
	// @RequiredIfExistsConstraint(baseField = "field2", dependentField = "field1")
})
public class FooRequestDto extends BaseRequestDto {

	private String field1;
	private String field2;
	// private String[] list;

}
