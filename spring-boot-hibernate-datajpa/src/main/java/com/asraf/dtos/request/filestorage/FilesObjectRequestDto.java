package com.asraf.dtos.request.filestorage;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.asraf.dtos.request.BaseRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilesObjectRequestDto extends BaseRequestDto {
	@NotNull
	private List<String> filePaths;
}
