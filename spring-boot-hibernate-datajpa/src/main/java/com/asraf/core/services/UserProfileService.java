package com.asraf.core.services;

import com.asraf.core.entities.UserProfile;
import com.asraf.exceptions.EntityNotFoundException;

public interface UserProfileService {

	UserProfile save(UserProfile userProfile);

	void delete(UserProfile userProfile);

	UserProfile getById(Long id) throws EntityNotFoundException;

	Iterable<UserProfile> getAll();

}
