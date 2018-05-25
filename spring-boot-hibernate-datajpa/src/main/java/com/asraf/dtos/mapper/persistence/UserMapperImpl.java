package com.asraf.dtos.mapper.persistence;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.dtos.mapper.UserMappper;
import com.asraf.dtos.request.UserRequestDto;
import com.asraf.dtos.response.UserResponseDto;
import com.asraf.dtos.response.UserVerificationResponseDto;
import com.asraf.entities.User;
import com.asraf.entities.UserVerification;

@Component
@Scope(value = "prototype")
public class UserMapperImpl extends RequestResponseDtoMapperImpl<User, UserResponseDto, UserRequestDto> implements UserMappper {

	@Autowired
	public UserMapperImpl(ModelMapper modelMapper) {
		super(modelMapper, UserResponseDto.class, User.class);
		
		PropertyMap<UserVerification, UserVerificationResponseDto> userVerificationEntityToResponsePropertyMap = new PropertyMap<UserVerification, UserVerificationResponseDto>() {
			protected void configure() {
				skip().setUser(null);
			}
		};

		super.setNestedObjectPropertyMap(userVerificationEntityToResponsePropertyMap);

	}

}
