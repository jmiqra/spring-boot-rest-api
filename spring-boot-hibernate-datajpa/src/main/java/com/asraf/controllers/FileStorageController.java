package com.asraf.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.asraf.dtos.request.filestorage.AclRequestDto;
import com.asraf.dtos.request.filestorage.FileObjectRequestDto;
import com.asraf.dtos.request.filestorage.FilesObjectRequestDto;
import com.asraf.dtos.request.filestorage.FolderObjectRequestDto;
import com.asraf.dtos.request.filestorage.PresignedUrlRequestDto;
import com.asraf.dtos.response.filestorage.PresignedUrlResponseDto;
import com.asraf.dtos.response.requestdto.RequestBodyResponseDto;
import com.asraf.exceptions.StoragePathNotFoundException;
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
	public PresignedUrlResponseDto getPresignedUrl(@Valid @RequestBody PresignedUrlRequestDto requestDto) {
		String presignedUrl = s3ObjectService.getPreSignedUrl(requestDto.getFilePath(),
				HttpMethod.valueOf(requestDto.getHttpMethod().toUpperCase()), requestDto.getExpirationInMinute())
				.toString();
		String publicUrl = s3ObjectService.getBucketUrl(requestDto.getFilePath());
		return PresignedUrlResponseDto.builder().presignedUrl(presignedUrl).publicUrl(publicUrl)
				.filePath(requestDto.getFilePath()).build();
	}

	@GetMapping("/presigned-url/request")
	public RequestBodyResponseDto<PresignedUrlRequestDto> getPresignedUrlRequest() {
		RequestBodyResponseDto<PresignedUrlRequestDto> response = new RequestBodyResponseDto<PresignedUrlRequestDto>(
				PresignedUrlRequestDto.class);
		return response;
	}

	@PutMapping("/acl")
	public void setAcl(@Valid @RequestBody AclRequestDto requestDto) {
		s3ObjectService.setAcl(requestDto.getFilePath(),
				CannedAccessControlList.valueOf(requestDto.getCannedAccessControlList()));
	}

	@GetMapping("/acl/request")
	public RequestBodyResponseDto<AclRequestDto> getAclRequest() {
		RequestBodyResponseDto<AclRequestDto> response = new RequestBodyResponseDto<AclRequestDto>(AclRequestDto.class);
		return response;
	}

	@PutMapping("/make-public")
	public void makePublic(@Valid @RequestBody FileObjectRequestDto requestDto) {
		s3ObjectService.makePublic(requestDto.getFilePath());
	}

	@GetMapping("/make-public/request")
	public RequestBodyResponseDto<FileObjectRequestDto> getMakePublicRequest() {
		RequestBodyResponseDto<FileObjectRequestDto> response = new RequestBodyResponseDto<FileObjectRequestDto>(
				FileObjectRequestDto.class);
		return response;
	}

	@DeleteMapping("/file")
	public void deleteFile(@Valid @RequestBody FileObjectRequestDto requestDto) {
		if (!s3ObjectService.isExists(requestDto.getFilePath())) {
			throw new StoragePathNotFoundException("filePath", requestDto.getFilePath());
		}
		s3ObjectService.delete(requestDto.getFilePath());
	}

	@GetMapping("/file/request")
	public RequestBodyResponseDto<FileObjectRequestDto> getDeleteFileRequest() {
		RequestBodyResponseDto<FileObjectRequestDto> response = new RequestBodyResponseDto<FileObjectRequestDto>(
				FileObjectRequestDto.class);
		return response;
	}

	@DeleteMapping("/files")
	public DeleteObjectsResult deleteFiles(@Valid @RequestBody FilesObjectRequestDto requestDto) {
		return s3ObjectService.delete(requestDto.getFilePaths());
	}

	@GetMapping("/files/request")
	public RequestBodyResponseDto<FilesObjectRequestDto> getDeleteFilesRequest() {
		RequestBodyResponseDto<FilesObjectRequestDto> response = new RequestBodyResponseDto<FilesObjectRequestDto>(
				FilesObjectRequestDto.class);
		return response;
	}

	@DeleteMapping("/folder")
	public DeleteObjectsResult deleteFolder(@Valid @RequestBody FolderObjectRequestDto requestDto) {
		try {
			return s3ObjectService.deleteAllObjects(requestDto.getFolderPath());
		} catch (AmazonS3Exception e) {
			throw new StoragePathNotFoundException("folderPath", requestDto.getFolderPath());
		}
	}

	@GetMapping("/folder/request")
	public RequestBodyResponseDto<FolderObjectRequestDto> getDeleteFolderRequest() {
		RequestBodyResponseDto<FolderObjectRequestDto> response = new RequestBodyResponseDto<FolderObjectRequestDto>(
				FolderObjectRequestDto.class);
		return response;
	}

}
