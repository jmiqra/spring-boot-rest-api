package com.asraf.core.dtos.mapper;

import com.asraf.core.dtos.request.UserProfileRequestDto;
import com.asraf.core.dtos.response.UserProfileResponseDto;
import com.asraf.core.entities.User;
import com.asraf.core.entities.UserProfile;

public interface UserProfileMappper extends DtoMapper<UserProfile, UserProfileRequestDto, UserProfileResponseDto> {
	UserProfile getEntity(UserProfileRequestDto requestDto, User user);
}
