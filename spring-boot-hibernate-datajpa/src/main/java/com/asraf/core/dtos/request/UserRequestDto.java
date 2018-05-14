package com.asraf.core.dtos.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRequestDto extends BaseRequestDto {
	private String email;
	private String name;
}
