package com.asraf.core.services;

import com.asraf.core.entities.UserVerification;
import com.asraf.exceptions.EntityNotFoundException;

public interface UserVerificationService {

	UserVerification save(UserVerification userVerification);

	void delete(UserVerification userVerification);

	UserVerification getById(Long id) throws EntityNotFoundException;

	Iterable<UserVerification> getAll();

}
