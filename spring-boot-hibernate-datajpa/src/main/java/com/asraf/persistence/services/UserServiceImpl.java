package com.asraf.persistence.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.core.repositories.UserRepository;
import com.asraf.core.services.UserService;
import com.asraf.exceptions.EntityNotFoundException;
import com.asraf.persistence.repositories.UserPredicatesBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User getById(Long id) throws EntityNotFoundException {
		try {
			return userRepository.findById(id).get();
		} catch (NoSuchElementException nseex) {
			throw new EntityNotFoundException(User.class, "id", id.toString());
		}
	}

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	public User getByEmail(String email) throws EntityNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new EntityNotFoundException(User.class, "email", email.toString());
		}
		return user;
	}

	public List<User> getByNameContains(String name) {
		return userRepository.findByNameContains(name);
	}

	public List<User> getBySearchCrud(UserSearch searchItem) {
		return userRepository.findByNameOrEmail(searchItem.getName(), searchItem.getEmail());
	}

	public Page<User> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable) {
		return userRepository.findByNameContainsOrEmailContainsAllIgnoreCase(searchItem.getName(),
				searchItem.getEmail(), pageable);
	}

	public Iterable<User> getByQuery(String search) {
		UserPredicatesBuilder builder = new UserPredicatesBuilder();
		 
		if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();
		Iterable<User> users =  userRepository.findAll(exp);
		return users;
	}

}
