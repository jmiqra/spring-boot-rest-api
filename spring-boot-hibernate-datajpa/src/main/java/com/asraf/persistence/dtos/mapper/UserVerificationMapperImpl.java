package com.asraf.persistence.dtos.mapper;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.core.dtos.mapper.UserVerificationMappper;
import com.asraf.core.dtos.request.UserVerificationRequestDto;
import com.asraf.core.dtos.response.UserVerificationResponseDto;
import com.asraf.core.entities.UserVerification;

@Component
@Scope(value = "prototype")
public class UserVerificationMapperImpl
		extends DtoMapperImpl<UserVerification, UserVerificationRequestDto, UserVerificationResponseDto>
		implements UserVerificationMappper {

	@Autowired
	public UserVerificationMapperImpl(ModelMapper modelMapper) {
		super(UserVerification.class, UserVerificationResponseDto.class, modelMapper);

		PropertyMap<UserVerificationRequestDto, UserVerification> requestToEntityPropertyMap = new PropertyMap<UserVerificationRequestDto, UserVerification>() {
			protected void configure() {
				map().getUser().setId(source.getUserId());
				// map().setCreationTime(new Date());
				// using(convertMassToLarge).map(source.getMass()).setLarge(false);
			}
		};

		PropertyMap<UserVerification, UserVerificationResponseDto> entityToResponsePropertyMap = new PropertyMap<UserVerification, UserVerificationResponseDto>() {
			protected void configure() {
				skip().getUser().setUserVerifications(null);
			}
		};

		super.setRequestToEntityPropertyMap(requestToEntityPropertyMap)
				.setEntityToResponsePropertyMap(entityToResponsePropertyMap);
	}

	public UserVerification getEntity(UserVerificationRequestDto requestDto) {
		UserVerification userVerification = super.getEntity(requestDto);
		userVerification.setCreationTime(new Date());
		return userVerification;
	}
}
