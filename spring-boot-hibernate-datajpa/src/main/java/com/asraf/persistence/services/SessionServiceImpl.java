package com.asraf.persistence.services;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asraf.core.entities.Session;
import com.asraf.core.repositories.SessionRepository;
import com.asraf.core.services.SessionService;
import com.asraf.exceptions.EntityNotFoundException;

@Service
@Transactional
public class SessionServiceImpl implements SessionService{
	
	private SessionRepository sessionRepository;

	@Autowired
	public SessionServiceImpl(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	public void delete(Session session) {
		sessionRepository.delete(session);
	}

	public Iterable<Session> getAll() {
		return sessionRepository.findAll();
	}

	public Session getById(Long id) throws EntityNotFoundException {
		try {
			return sessionRepository.findById(id).get();
		} catch (NoSuchElementException nsex) {
			throw new EntityNotFoundException(Session.class, "id", id.toString());
		}
	}

}
