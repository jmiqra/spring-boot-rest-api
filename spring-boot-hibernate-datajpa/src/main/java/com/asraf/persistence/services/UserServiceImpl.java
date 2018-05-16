package com.asraf.persistence.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.core.repositories.UserRepository;
import com.asraf.core.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> getByNameContains(String name) {
		return userRepository.findByNameContains(name);
	}
	
	public List<User> getBySearchCrud(UserSearch searchItem) {
		return userRepository.findByNameOrEmail(searchItem.getName(), searchItem.getEmail());
	}
	
	public Slice<User> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable) {
		return userRepository.findByNameContainsOrEmailContainsAllIgnoreCase(searchItem.getName(), searchItem.getEmail(), pageable);
	}
	
}
