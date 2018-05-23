package com.asraf.core.dtos.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentResponseDto extends BaseResponseDto{
	
	private String name;
	
	private int age;
	
	private String gender;

}
