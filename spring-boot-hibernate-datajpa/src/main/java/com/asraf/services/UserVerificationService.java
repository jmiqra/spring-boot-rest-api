package com.asraf.services;

import com.asraf.entities.UserVerification;

public interface UserVerificationService {

	UserVerification save(UserVerification userVerification);

	void delete(UserVerification userVerification);

	UserVerification getById(Long id);

	Iterable<UserVerification> getAll();

}
