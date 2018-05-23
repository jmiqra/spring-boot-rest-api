package com.asraf.core.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.Student;

@Transactional
public interface StudentRepositoryCrud extends CrudRepository<Student, Long> {

	//public Student findByAge(int age);
	
	//public Student findByGender(String gender);

	//@Query("select u from User u where u.name like %?1% order by name")
	//List<Student> findByNameContains(String name);
	
}
