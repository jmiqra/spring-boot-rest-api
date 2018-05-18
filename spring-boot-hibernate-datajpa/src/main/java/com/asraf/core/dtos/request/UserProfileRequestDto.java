package com.asraf.core.dtos.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserProfileRequestDto extends BaseRequestDto {

	private String address;

	private String phoneNumber;

}
