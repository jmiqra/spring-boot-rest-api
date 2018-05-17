package com.asraf.core.dtos.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVerificationRequestDto extends BaseRequestDto {

	private String verificationCode;

	private Long userId;

}
