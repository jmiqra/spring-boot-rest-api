package com.asraf.persistence.dtos.mapper;

import org.springframework.stereotype.Service;

import com.asraf.core.dtos.mapper.UserMappper;
import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.User;

@Service
public class UserMapperImpl extends DtoMapperImpl<User, UserRequestDto, UserResponseDto> implements UserMappper {

	public UserMapperImpl() {
		super(User.class, UserResponseDto.class);
	}

}
