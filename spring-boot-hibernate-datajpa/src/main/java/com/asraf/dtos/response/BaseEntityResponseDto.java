package com.asraf.dtos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseEntityResponseDto extends BaseResponseDto {
	private Long id;
}
