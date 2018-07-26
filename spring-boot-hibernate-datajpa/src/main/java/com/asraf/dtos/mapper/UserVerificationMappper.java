package com.asraf.dtos.mapper;

import com.asraf.dtos.request.entities.UserVerificationRequestDto;
import com.asraf.dtos.response.entities.UserVerificationResponseDto;
import com.asraf.entities.UserVerification;

public interface UserVerificationMappper
		extends RequestResponseDtoMapper<UserVerification, UserVerificationResponseDto, UserVerificationRequestDto> {

}
