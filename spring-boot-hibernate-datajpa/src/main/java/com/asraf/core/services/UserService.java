package com.asraf.core.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.exceptions.EntityNotFoundException;
import com.querydsl.core.types.Predicate;

public interface UserService {

	User save(User user);

	void delete(User user);

	User getById(Long id) throws EntityNotFoundException;

	Iterable<User> getAll();

	User getByEmail(String email) throws EntityNotFoundException;

	List<User> getByNameContains(String name);

	List<User> getBySearchCrud(UserSearch searchItem);

	Page<User> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable);
	
	Iterable<User> getByQuery(String search);
	
}
