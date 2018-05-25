package com.asraf.dtos.mapper;

import com.asraf.dtos.request.UserVerificationRequestDto;
import com.asraf.dtos.response.UserVerificationResponseDto;
import com.asraf.entities.UserVerification;

public interface UserVerificationMappper
		extends RequestResponseDtoMapper<UserVerification, UserVerificationResponseDto, UserVerificationRequestDto> {

}
