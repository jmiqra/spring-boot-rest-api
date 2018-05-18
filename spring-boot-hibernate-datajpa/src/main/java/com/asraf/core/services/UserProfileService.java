package com.asraf.core.services;

import com.asraf.core.entities.UserProfile;

public interface UserProfileService {

	UserProfile save(UserProfile userProfile);

	void delete(UserProfile userProfile);

	UserProfile getById(Long id);

	Iterable<UserProfile> getAll();

}
