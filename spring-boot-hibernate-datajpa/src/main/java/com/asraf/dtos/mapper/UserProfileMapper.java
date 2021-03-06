package com.asraf.dtos.mapper;

import com.asraf.dtos.request.entities.UserProfileRequestDto;
import com.asraf.dtos.response.entities.UserProfileResponseDto;
import com.asraf.entities.User;
import com.asraf.entities.UserProfile;

public interface UserProfileMapper
		extends RequestResponseDtoMapper<UserProfile, UserProfileResponseDto, UserProfileRequestDto> {
	UserProfile getEntity(UserProfileRequestDto requestDto, User user);
}
