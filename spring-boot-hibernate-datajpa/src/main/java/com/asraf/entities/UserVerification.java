package com.asraf.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
//@Table(name = "user_verifications")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVerification extends BaseEntity {

	@NotNull
	private String verificationCode;

	@NotNull
	private Date creationTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private User user;
}
