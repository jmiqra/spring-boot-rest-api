package com.asraf.persistence.dtos.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.core.dtos.mapper.StudentMapper;
import com.asraf.core.dtos.request.StudentRequestDto;
import com.asraf.core.dtos.response.StudentResponseDto;
import com.asraf.core.entities.Student;

@Component
@Scope(value = "prototype")
public class StudentMapperImpl extends DtoMapperImpl<Student, StudentRequestDto, StudentResponseDto> implements StudentMapper{
	
	@Autowired
	public StudentMapperImpl(ModelMapper modelMapper) {
		super(Student.class, StudentResponseDto.class, modelMapper);
		
		PropertyMap<StudentRequestDto, Student> requestToEntityPropertyMap = new PropertyMap<StudentRequestDto, Student>() {
			protected void configure() {
				map().getSession().setId(source.getSessionId());
				// map().setCreationTime(new Date());
				// using(convertMassToLarge).map(source.getMass()).setLarge(false);
			}
		};
		
		PropertyMap<Student, StudentResponseDto> entityToResponsePropertyMap = new PropertyMap<Student, StudentResponseDto>() {
			protected void configure() {
				skip().getSession().setStudents(null);
			}
		};
		
		super.setRequestToEntityPropertyMap(requestToEntityPropertyMap)
			.setEntityToResponsePropertyMap(entityToResponsePropertyMap);
		
	}
	
}
