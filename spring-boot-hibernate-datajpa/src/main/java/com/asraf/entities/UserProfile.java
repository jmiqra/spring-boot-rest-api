package com.asraf.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
// @Table(name = "user_profiles")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserProfile extends BaseEntity {

	@NotNull
	private String address;

	@NotNull
	private String phoneNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;

}
