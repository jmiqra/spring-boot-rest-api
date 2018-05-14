package com.asraf.core.dtos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserResponseDto extends BaseResponseDto {
	private String email;
	private String name;
}
