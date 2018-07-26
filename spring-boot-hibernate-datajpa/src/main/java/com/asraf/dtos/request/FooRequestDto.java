package com.asraf.dtos.request;

import com.asraf.validators.IsGreaterConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
// @RequiredIfExistsConstraint.List({
// @RequiredIfExistsConstraint(baseField = "field1", dependentField = "field2",
// message = "1st annonation error"),
// // @RequiredIfExistsConstraint(baseField = "field2", dependentField =
// "field1")
// })
@IsGreaterConstraint.List({
		@IsGreaterConstraint(baseField = "field1", dependentField = "field2", message = "Comparison error"), })
public class FooRequestDto extends BaseRequestDto {

	private Float field1;
	private Float field2;
	// private String[] list;

}
