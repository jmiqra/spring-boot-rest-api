package com.asraf.persistence.dtos.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.core.dtos.mapper.UserMappper;
import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.dtos.response.UserVerificationResponseDto;
import com.asraf.core.entities.User;
import com.asraf.core.entities.UserVerification;

@Component
@Scope(value = "prototype")
public class UserMapperImpl extends DtoMapperImpl<User, UserRequestDto, UserResponseDto> implements UserMappper {

	@Autowired
	public UserMapperImpl(ModelMapper modelMapper) {
		super(User.class, UserResponseDto.class, modelMapper);
		
		PropertyMap<UserVerification, UserVerificationResponseDto> userVerificationEntityToResponsePropertyMap = new PropertyMap<UserVerification, UserVerificationResponseDto>() {
			protected void configure() {
				skip().setUser(null);
			}
		};

		super.setNestedObjectPropertyMap(userVerificationEntityToResponsePropertyMap);

	}

}
