package com.asraf.dtos.request;

import com.asraf.validators.IsEqualConstraint;

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
@IsEqualConstraint.List({
		@IsEqualConstraint(baseField = "field1", dependentField = "field2", message = "Not Equal error"),
})
public class FooRequestDto extends BaseRequestDto {

	private float field1;
	private float field2;
	// private String[] list;

}
