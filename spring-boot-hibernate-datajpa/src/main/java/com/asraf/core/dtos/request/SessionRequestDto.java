package com.asraf.core.dtos.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SessionRequestDto extends BaseRequestDto{

	@NotNull
	@Size(min = 3, max = 15)
	private String title;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startdate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date enddate;
	
}
