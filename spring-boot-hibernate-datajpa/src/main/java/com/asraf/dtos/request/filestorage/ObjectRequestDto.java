package com.asraf.dtos.request.filestorage;

import javax.validation.constraints.NotBlank;

import com.asraf.dtos.request.BaseRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ObjectRequestDto extends BaseRequestDto {
	@NotBlank
	private String filePath;
}
