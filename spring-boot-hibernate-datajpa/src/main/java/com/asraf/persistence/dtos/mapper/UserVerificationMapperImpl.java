package com.asraf.persistence.dtos.mapper;

import org.springframework.stereotype.Service;

import com.asraf.core.dtos.mapper.UserVerificationMappper;
import com.asraf.core.dtos.request.UserVerificationRequestDto;
import com.asraf.core.dtos.response.UserVerificationResponseDto;
import com.asraf.core.entities.UserVerification;

@Service
public class UserVerificationMapperImpl extends DtoMapperImpl<UserVerification, UserVerificationRequestDto, UserVerificationResponseDto> implements UserVerificationMappper {

	public UserVerificationMapperImpl() {
		super(UserVerification.class, UserVerificationResponseDto.class);
	}

}
