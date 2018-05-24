package com.asraf.core.dtos.response;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SessionResponseDto extends BaseResponseDto{

	private String title;
	
	private Date startdate;
	
	private Date enddate;
	
	private List<StudentResponseDto> students;
	
}
