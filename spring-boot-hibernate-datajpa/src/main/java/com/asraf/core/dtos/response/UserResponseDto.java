package com.asraf.core.dtos.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponseDto {
	private String email;
	private String name;
}
