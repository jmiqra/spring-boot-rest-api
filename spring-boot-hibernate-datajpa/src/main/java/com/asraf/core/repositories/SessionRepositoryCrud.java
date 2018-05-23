package com.asraf.core.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.asraf.core.entities.Session;

@Transactional
public interface SessionRepositoryCrud extends CrudRepository<Session, Long>{

}
