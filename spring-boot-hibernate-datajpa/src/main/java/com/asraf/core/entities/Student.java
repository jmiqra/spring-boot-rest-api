package com.asraf.core.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends BaseEntity{

	@NotNull
	private String name;
	
	@NotNull
	private int age;
	
	@NotNull
	private String gender;
	
}
