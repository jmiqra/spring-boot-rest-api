package com.asraf.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asraf.core.entities.User;
import com.asraf.core.repositories.UserRepository;
import com.asraf.core.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
