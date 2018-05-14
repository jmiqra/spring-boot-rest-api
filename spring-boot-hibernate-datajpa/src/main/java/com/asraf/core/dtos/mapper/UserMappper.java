package com.asraf.core.dtos.mapper;

import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.User;

public interface UserMappper extends DtoMapper<User, UserRequestDto, UserResponseDto> {

}
