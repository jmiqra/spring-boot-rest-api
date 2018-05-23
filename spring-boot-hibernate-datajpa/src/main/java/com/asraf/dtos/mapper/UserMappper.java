package com.asraf.dtos.mapper;

import com.asraf.dtos.request.UserRequestDto;
import com.asraf.dtos.response.UserResponseDto;
import com.asraf.entities.User;

public interface UserMappper extends DtoMapper<User, UserRequestDto, UserResponseDto> {

}
