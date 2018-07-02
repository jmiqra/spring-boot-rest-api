package com.asraf.dtos.request.filestorage;

import javax.validation.constraints.NotNull;

import com.amazonaws.HttpMethod;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PresignedUrlRequestDto extends FileObjectRequestDto {
	@NotNull
	private HttpMethod httpMethod;
	@NotNull
	private int expirationInMinute;
}
