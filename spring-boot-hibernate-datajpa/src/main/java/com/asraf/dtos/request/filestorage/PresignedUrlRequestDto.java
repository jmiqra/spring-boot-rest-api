package com.asraf.dtos.request.filestorage;

import com.amazonaws.HttpMethod;
import com.asraf.validators.EnumValueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PresignedUrlRequestDto extends FileObjectRequestDto {

	@EnumValueConstraint(enumClass = HttpMethod.class, ignoreCase = false)
	private String httpMethod;

	private Integer expirationInMinute;

}
