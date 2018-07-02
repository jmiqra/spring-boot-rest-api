package com.asraf.dtos.response.filestorage;

import com.asraf.dtos.response.BaseResponseDto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PresignedUrlResponseDto extends BaseResponseDto {
	private String presignedUrl;
	private String filePath;
	private String publicUrl;
}
