package com.asraf.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.UserVerification;
import com.asraf.core.repositories.UserVerificationRepository;
import com.asraf.core.services.UserVerificationService;

@Service
@Transactional
public class UserVerificationServiceImpl implements UserVerificationService {

	private UserVerificationRepository userVerificationRepository;

	@Autowired
	public UserVerificationServiceImpl(UserVerificationRepository userVerificationRepository) {
		this.userVerificationRepository = userVerificationRepository;
	}

	public UserVerification save(UserVerification userVerification) {
		return userVerificationRepository.save(userVerification);
	}

	public void delete(UserVerification userVerification) {
		userVerificationRepository.delete(userVerification);
	}

	public UserVerification getById(Long id) {
		return userVerificationRepository.findById(id).get();
	}

	public Iterable<UserVerification> getAll() {
		return userVerificationRepository.findAll();
	}

}
