package com.asraf.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class UploadFileResponseDto extends BaseResponseDto {
	private String fileName;
	private String fileType;
	private long size;
}