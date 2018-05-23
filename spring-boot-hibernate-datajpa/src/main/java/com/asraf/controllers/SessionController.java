package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.core.dtos.mapper.SessionMapper;
import com.asraf.core.dtos.request.SessionRequestDto;
import com.asraf.core.dtos.response.SessionResponseDto;
import com.asraf.core.entities.Session;
import com.asraf.core.services.SessionService;
import com.asraf.exceptions.EntityNotFoundException;

@RestController
@RequestMapping("/sessions")
public class SessionController {
	
	private SessionService sessionService;
	private SessionMapper sessionMapper;
	
	@Autowired
	public SessionController(SessionService sessionService, SessionMapper sessionMapper) {
		this.sessionService = sessionService;
		this.sessionMapper = sessionMapper;
	}
	
	@GetMapping("")
	public List<SessionResponseDto> getAll() {
		List<SessionResponseDto> response = sessionMapper.getResponseDtos(this.sessionService.getAll());
		return response;
	}
	
	@GetMapping("/{id}")
	public SessionResponseDto getById(@PathVariable long id) throws EntityNotFoundException {
		Session session = sessionService.getById(id);
		return sessionMapper.getResponseDto(session);
	}
	
	@PostMapping("")
	public SessionResponseDto create(@Valid @RequestBody SessionRequestDto requestDto) {
		Session session = sessionMapper.getEntity(requestDto);
		sessionService.save(session);
		return sessionMapper.getResponseDto(session);
	}

	@DeleteMapping("/{id}")
	public SessionResponseDto delete(@PathVariable long id) throws EntityNotFoundException {
		Session session = sessionService.getById(id);
		sessionService.delete(session);
		return sessionMapper.getResponseDto(session);
	}

	@PutMapping("/{id}")
	public SessionResponseDto update(@PathVariable long id, @Valid @RequestBody SessionRequestDto requestDto)
			throws EntityNotFoundException {
		Session session = sessionService.getById(id);
		sessionMapper.loadEntity(requestDto, session);
		sessionService.save(session);
		return sessionMapper.getResponseDto(session);
	}
	
}
