package com.asraf.core.dtos.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentRequestDto extends BaseRequestDto{

	@NotNull
	@Size(min = 3, max = 10)
	private String name;
	
	@NotNull
	@Min(1)
	@Max(100)
	private int age;
	
	@NotNull
	private String gender;
}
