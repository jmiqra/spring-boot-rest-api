package com.asraf.core.services;

import com.asraf.core.entities.UserVerification;

public interface UserVerificationService {

	UserVerification save(UserVerification userVerification);

	void delete(UserVerification userVerification);

	UserVerification getById(Long id);

	Iterable<UserVerification> getAll();

}
