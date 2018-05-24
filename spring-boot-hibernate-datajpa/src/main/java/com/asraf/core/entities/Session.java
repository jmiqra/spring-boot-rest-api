package com.asraf.core.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();
	
}
