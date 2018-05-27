package com.asraf.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
// @Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends BaseEntity {

	@NotNull
	private String email;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserVerification> userVerifications = new ArrayList<>();

}
