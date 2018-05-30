package com.asraf.dtos.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.asraf.constants.Gender;
import com.asraf.validators.ContactNumberConstraint;
import com.asraf.validators.EnumValueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVerificationRequestDto extends BaseRequestDto {

	@NotNull
	@Size(min = 5, max = 10)
	private String verificationCode;

	@NotNull
	private Long userId;

	@Email
	@Pattern(regexp = "%^%")
	private String email;

	@Min(3)
	@Max(15)
	private int quantity;

	@ContactNumberConstraint
	private String phone;

	@EnumValueConstraint(enumClass=Gender.class, ignoreCase=true)
	private Gender gender;

}
