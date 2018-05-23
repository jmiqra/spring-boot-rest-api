package com.asraf.persistence.dtos.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.asraf.core.dtos.mapper.StudentMapper;
import com.asraf.core.dtos.request.StudentRequestDto;
import com.asraf.core.dtos.response.StudentResponseDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.Student;
import com.asraf.core.entities.User;

@Component
@Scope(value = "prototype")
public class StudentMapperImpl extends DtoMapperImpl<Student, StudentRequestDto, StudentResponseDto> implements StudentMapper{
	
	@Autowired
	public StudentMapperImpl(ModelMapper modelMapper) {
		super(Student.class, StudentResponseDto.class, modelMapper);
	}
	
}