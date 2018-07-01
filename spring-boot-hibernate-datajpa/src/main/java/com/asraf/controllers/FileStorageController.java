package com.asraf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.dtos.request.filestorage.AclRequestDto;
import com.asraf.dtos.request.filestorage.ObjectRequestDto;
import com.asraf.dtos.request.filestorage.PresignedUrlRequestDto;
import com.asraf.dtos.response.filestorage.PresignedUrlResponseDto;
import com.asraf.services.aws.s3.S3ObjectService;

@RestController
@RequestMapping("/file-storage")
public class FileStorageController {

	private S3ObjectService s3ObjectService;

	@Autowired
	public FileStorageController(S3ObjectService s3ObjectService) {
		this.s3ObjectService = s3ObjectService;
	}

	@PostMapping("/presigned-url")
	public PresignedUrlResponseDto getPresignedUrl(@RequestBody PresignedUrlRequestDto requestDto) {
		String presignedUrl = s3ObjectService.getPreSignedUrl(requestDto.getFilePath(), requestDto.getHttpMethod(),
				requestDto.getExpirationInMinute()).toString();
		String publicUrl = s3ObjectService.getBucketUrl(requestDto.getFilePath());
		return PresignedUrlResponseDto.builder().presignedUrl(presignedUrl).publicUrl(publicUrl)
				.key(requestDto.getFilePath()).build();
	}

	@PutMapping("/acl")
	public void setAcl(@RequestBody AclRequestDto requestDto) {
		s3ObjectService.setAcl(requestDto.getFilePath(), requestDto.getCannedAccessControlList());
	}

	@PutMapping("/make-public")
	public void makePublic(@RequestBody ObjectRequestDto requestDto) {
		s3ObjectService.makePublic(requestDto.getFilePath());
	}

}
