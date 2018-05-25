package com.asraf.dtos.mapper;

import com.asraf.dtos.request.UserProfileRequestDto;
import com.asraf.dtos.response.UserProfileResponseDto;
import com.asraf.entities.User;
import com.asraf.entities.UserProfile;

public interface UserProfileMappper
		extends RequestResponseDtoMapper<UserProfile, UserProfileResponseDto, UserProfileRequestDto> {
	UserProfile getEntity(UserProfileRequestDto requestDto, User user);
}
