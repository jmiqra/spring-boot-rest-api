package com.asraf.core.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "user_verifications")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVerification extends BaseEntity {

	@NotNull
	private String verificationCode;

	@NotNull
	private String creationTime;

	@ManyToOne
	@NotNull
	private User user;
}