package com.asraf.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asraf.entities.User;
import com.asraf.exceptions.EntityNotFoundException;
import com.asraf.models.search.UserSearch;
import com.asraf.models.search.extended.UserWithVerificationSearch;

public interface UserService {

	User save(User user);

	void delete(User user);

	User getById(Long id) throws EntityNotFoundException;

	Iterable<User> getAll();

	User getByEmail(String email) throws EntityNotFoundException;

	List<User> getByNameContains(String name);

	List<User> getBySearchCrud(UserSearch searchItem);

	Page<User> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable);
	
	Page<User> getBySearchIntoJoiningTablePageable(UserWithVerificationSearch searchItem, Pageable pageable);
	
	Page<User> getByQuery(String search, Pageable pageable);

}
