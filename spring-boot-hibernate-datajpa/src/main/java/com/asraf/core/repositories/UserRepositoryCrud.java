package com.asraf.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.User;

@Transactional
public interface UserRepositoryCrud extends PagingAndSortingRepository<User, Long> {

	/**
	 * Retrieves an user by its email.
	 * 
	 * @param email
	 * @return The user having the passed email or null if no user is found
	 */
	public User findByEmail(String email);

	@Query("select u from User u where u.name like %?1% order by name")
    List<User> findByNameContains(String name);
}
