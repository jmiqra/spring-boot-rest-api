package com.asraf.persistence.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.Student;
import com.asraf.core.repositories.StudentRepository;
import com.asraf.core.services.StudentService;
import com.asraf.exceptions.EntityNotFoundException;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public void delete(Student student) {
		studentRepository.delete(student);
	}

	public Iterable<Student> getAll() {
		return studentRepository.findAll();
	}

	public Student getById(Long id) throws EntityNotFoundException {
		try {
			return studentRepository.findById(id).get();
		} catch (NoSuchElementException nsex) {
			throw new EntityNotFoundException(Student.class, "id", id.toString());
		}
	}

	// TODO review below codes
	// public Student getByAge(int age) throws EntityNotFoundException {
	// return null;
	// }
	//
	//
	// public Student getByGender(String gender) throws EntityNotFoundException {
	// return null;
	// }
	//
	//
	// public List<Student> getByNameContains(String name) {
	// return null;
	// }

}
