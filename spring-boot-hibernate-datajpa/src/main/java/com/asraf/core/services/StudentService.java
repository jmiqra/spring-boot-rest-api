package com.asraf.core.services;

import java.util.List;

import com.asraf.core.entities.Student;
import com.asraf.exceptions.EntityNotFoundException;

public interface StudentService {

	Student save(Student Student);

	void delete(Student Student);

	Iterable<Student> getAll();
	
	Student getById(Long id) throws EntityNotFoundException;

	//Student getByAge(int age) throws EntityNotFoundException;
	
	//Student getByGender(String gender) throws EntityNotFoundException;

	//List<Student> getByNameContains(String name);

}
