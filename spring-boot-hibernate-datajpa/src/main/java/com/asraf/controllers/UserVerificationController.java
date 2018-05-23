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

import com.asraf.dtos.mapper.UserVerificationMappper;
import com.asraf.dtos.request.UserVerificationRequestDto;
import com.asraf.dtos.response.UserVerificationResponseDto;
import com.asraf.entities.UserVerification;
import com.asraf.exceptions.EntityNotFoundException;
import com.asraf.services.UserVerificationService;

@RestController
@RequestMapping("/user-verifications")
public class UserVerificationController {

	private UserVerificationService userVerificationService;
	private UserVerificationMappper userVerificationMappper;

	@Autowired
	public UserVerificationController(UserVerificationService userVerificationService,
			UserVerificationMappper userVerificationMappper) {
		this.userVerificationMappper = userVerificationMappper;
		this.userVerificationService = userVerificationService;
	}

	@GetMapping("")
	public List<UserVerificationResponseDto> getAll() {
		List<UserVerificationResponseDto> response = userVerificationMappper
				.getResponseDtos(this.userVerificationService.getAll());
		return response;
	}

	@GetMapping("/{id}")
	public UserVerificationResponseDto getById(@PathVariable long id) throws EntityNotFoundException {
		UserVerification userVerification = userVerificationService.getById(id);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@PostMapping("")
	public UserVerificationResponseDto create(@Valid @RequestBody UserVerificationRequestDto requestDto) {
		UserVerification userVerification = userVerificationMappper.getEntity(requestDto);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@DeleteMapping("/{id}")
	public UserVerificationResponseDto delete(@PathVariable long id) throws EntityNotFoundException {
		UserVerification userVerification = userVerificationService.getById(id);
		userVerificationService.delete(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@PutMapping("/{id}")
	public UserVerificationResponseDto update(@PathVariable long id,
			@Valid @RequestBody UserVerificationRequestDto requestDto) throws EntityNotFoundException {
		UserVerification userVerification = userVerificationService.getById(id);
		userVerificationMappper.loadEntity(requestDto, userVerification);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

}
