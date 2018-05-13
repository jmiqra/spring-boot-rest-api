package com.asraf.persistence.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asraf.core.dtos.mapper.UserMappper;
import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.User;

@Service
public class UserMapperImpl implements UserMappper {
	@Autowired
	private ModelMapper modelMapper;

	public User getEntityForCreate(UserRequestDto requestDto) {
		return modelMapper.map(requestDto, User.class);
	}

	public User getEntityForUpdate(Long id, UserRequestDto requestDto) {
		User user = modelMapper.map(requestDto, User.class);
		user.setId(id);
		return user;
	}

	public UserResponseDto getResponseDto(User user) {
		return modelMapper.map(user, UserResponseDto.class);
	}

	public List<UserResponseDto> getResponseDtos(Iterable<User> entities) {
		return StreamSupport.stream(entities.spliterator(), false).map(entity -> getResponseDto(entity))
				.collect(Collectors.toList());
	}
}
