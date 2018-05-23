package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.core.dtos.mapper.StudentMapper;
import com.asraf.core.dtos.request.StudentRequestDto;
import com.asraf.core.dtos.response.StudentResponseDto;
import com.asraf.core.entities.Student;
import com.asraf.core.services.StudentService;
import com.asraf.exceptions.EntityNotFoundException;

@RestController
@RequestMapping("/students")
public class StudentController {

	private StudentService studentService;
	private StudentMapper studentMapper;

	@Autowired
	public StudentController(StudentService studentService, StudentMapper studentMapper) {
		this.studentService = studentService;
		this.studentMapper = studentMapper;
	}

	
	@GetMapping("")
	public List<StudentResponseDto> getAll() {
		List<StudentResponseDto> response = studentMapper.getResponseDtos(this.studentService.getAll());
		return response;
	}


	@GetMapping("/{id}")
	public StudentResponseDto getById(@PathVariable long id) throws EntityNotFoundException {
		Student student = studentService.getById(id);
		return studentMapper.getResponseDto(student);
	}
	
	
	@PostMapping("")
	public StudentResponseDto create(@Valid @RequestBody StudentRequestDto requestDto) {
		Student student = studentMapper.getEntity(requestDto);
		studentService.save(student);
		return studentMapper.getResponseDto(student);
	}

	@DeleteMapping("/{id}")
	public StudentResponseDto delete(@PathVariable long id) throws EntityNotFoundException {
		Student student = studentService.getById(id);
		studentService.delete(student);
		return studentMapper.getResponseDto(student);
	}

	@PutMapping("/{id}")
	public StudentResponseDto update(@PathVariable long id, @Valid @RequestBody StudentRequestDto requestDto)
			throws EntityNotFoundException {
		Student student = studentService.getById(id);
		studentMapper.loadEntity(requestDto, student);
		studentService.save(student);
		return studentMapper.getResponseDto(student);
	}

}
