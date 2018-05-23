package com.asraf.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Session extends BaseEntity{
	
	@NotNull
	private String title;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startdate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date enddate;
	
}
