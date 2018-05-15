package com.asraf.core.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.asraf.core.entities.User;

@Transactional
public interface UserRepositoryCrud extends CrudRepository<User, Long> {

	/**
	 * Retrieves an user by its email.
	 * 
	 * @param email
	 * @return The user having the passed email or null if no user is found
	 */
	public User findByEmail(String email);

}
