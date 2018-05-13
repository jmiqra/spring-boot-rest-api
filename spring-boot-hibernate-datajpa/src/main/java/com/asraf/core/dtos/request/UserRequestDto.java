package com.asraf.core.dtos.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequestDto {
	private String email;
	private String name;
}
