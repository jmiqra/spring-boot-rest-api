package com.asraf.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.Student;

@Transactional
public interface StudentRepositoryCrud extends CrudRepository<Student, Long> {

	//TODO
	//public Student findByAge(int age);
	
	//public Student findByGender(String gender);

	//@Query("select u from User u where u.name like %?1% order by name")
	//List<Student> findByNameContains(String name);
	
}
