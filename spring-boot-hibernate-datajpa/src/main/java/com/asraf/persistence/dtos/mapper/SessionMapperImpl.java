package com.asraf.persistence.dtos.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.core.dtos.mapper.SessionMapper;
import com.asraf.core.dtos.request.SessionRequestDto;
import com.asraf.core.dtos.response.SessionResponseDto;
import com.asraf.core.dtos.response.StudentResponseDto;
import com.asraf.core.entities.Session;
import com.asraf.core.entities.Student;

@Component
@Scope(value = "prototype")
public class SessionMapperImpl extends DtoMapperImpl<Session, SessionRequestDto, SessionResponseDto>
		implements SessionMapper {

	@Autowired
	public SessionMapperImpl(ModelMapper modelMapper) {
		super(Session.class, SessionResponseDto.class, modelMapper);

		PropertyMap<Student, StudentResponseDto> studentEntityToResponsePropertyMap = new PropertyMap<Student, StudentResponseDto>() {
			protected void configure() {
				skip().setSession(null);
			}
		};

		super.setNestedObjectPropertyMap(studentEntityToResponsePropertyMap);

	}

}
