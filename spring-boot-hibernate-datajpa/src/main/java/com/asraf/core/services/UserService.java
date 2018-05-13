package com.asraf.core.services;

import com.asraf.core.entities.User;

public interface UserService {
	User save(User user);

	void delete(User user);

	User getById(Long id);

	Iterable<User> getAll();

	User getByEmail(String email);
}
