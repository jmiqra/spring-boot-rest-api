package com.asraf.persistence.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.UserProfile;
import com.asraf.core.repositories.UserProfileRepository;
import com.asraf.core.services.UserProfileService;
import com.asraf.exceptions.EntityNotFoundException;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	private UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	public UserProfile save(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	public void delete(UserProfile userProfile) {
		userProfileRepository.delete(userProfile);
	}

	public UserProfile getById(Long id) throws EntityNotFoundException {
		try {
			return userProfileRepository.findById(id).get();
		} catch (NoSuchElementException nseex) {
			throw new EntityNotFoundException(UserProfile.class, "id", id.toString());
		}
	}

	public Iterable<UserProfile> getAll() {
		return userProfileRepository.findAll();
	}

}
