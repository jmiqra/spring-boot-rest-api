package com.asraf.core.repositories;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.asraf.core.entities.User;

@Transactional
public interface UserRepository  extends CrudRepository<User, Long> {

	/**
	 * Return the user having the passed email or null if no user is found.
	 * 
	 * @param email
	 *            the user email.
	 */
	public User findByEmail(String email);

}
