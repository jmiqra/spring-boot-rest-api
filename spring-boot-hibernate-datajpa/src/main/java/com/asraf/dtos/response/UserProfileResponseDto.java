package com.asraf.dtos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserProfileResponseDto extends BaseEntityResponseDto {

	private String address;

	private String phoneNumber;

	private UserResponseDto User;
	
}
