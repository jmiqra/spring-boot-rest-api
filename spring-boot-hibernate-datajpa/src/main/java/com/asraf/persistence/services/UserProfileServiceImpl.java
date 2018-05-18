package com.asraf.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.UserProfile;
import com.asraf.core.repositories.UserProfileRepository;
import com.asraf.core.services.UserProfileService;

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

	public UserProfile getById(Long id) {
		return userProfileRepository.findById(id).get();
	}

	public Iterable<UserProfile> getAll() {
		return userProfileRepository.findAll();
	}

}
