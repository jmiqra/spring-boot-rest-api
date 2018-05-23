package com.asraf.core.services;

import com.asraf.core.entities.Session;
import com.asraf.exceptions.EntityNotFoundException;

public interface SessionService {

	Session save(Session session);

	void delete(Session session);

	Iterable<Session> getAll();
	
	Session getById(Long id) throws EntityNotFoundException;
	
}
