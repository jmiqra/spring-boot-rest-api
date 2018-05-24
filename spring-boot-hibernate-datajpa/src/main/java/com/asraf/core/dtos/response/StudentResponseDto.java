package com.asraf.core.dtos.response;


import com.asraf.enums.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentResponseDto extends BaseResponseDto{
	
	private String name;
	
	private int age;
	
	private Gender gender;
	
	//private SessionResponseDto SessionRDto;

}
