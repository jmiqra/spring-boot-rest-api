package com.asraf.core.dtos.response;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVerificationResponseDto extends BaseResponseDto {

	private String verificationCode;

	private Date creationTime;

	private UserResponseDto User;
	
}
