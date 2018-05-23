package com.asraf.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.asraf.enums.Gender;

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
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
}
