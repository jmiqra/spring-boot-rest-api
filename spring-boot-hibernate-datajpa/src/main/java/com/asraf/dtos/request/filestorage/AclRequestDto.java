package com.asraf.dtos.request.filestorage;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.s3.model.CannedAccessControlList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AclRequestDto extends FileObjectRequestDto {
	@NotNull
	private CannedAccessControlList cannedAccessControlList;
}
