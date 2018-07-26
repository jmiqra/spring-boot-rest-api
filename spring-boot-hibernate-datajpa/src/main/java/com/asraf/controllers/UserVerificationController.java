package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.controllers.resources.UserVerificationCollectionResource;
import com.asraf.controllers.resources.UserVerificationResource;
import com.asraf.dtos.mapper.UserVerificationMappper;
import com.asraf.dtos.request.entities.UserVerificationRequestDto;
import com.asraf.dtos.response.entities.UserVerificationResponseDto;
import com.asraf.dtos.response.requestdto.RequestBodyResponseDto;
import com.asraf.entities.UserVerification;
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
	public UserVerificationCollectionResource getAll() {
		List<UserVerification> userVerifications = (List<UserVerification>) this.userVerificationService.getAll();
		return new UserVerificationCollectionResource(userVerifications, userVerificationMappper);
	}

	@GetMapping("/{id}")
	public UserVerificationResource getById(@PathVariable long id) {
		UserVerification userVerification = userVerificationService.getById(id);
		return new UserVerificationResource(userVerification, userVerificationMappper);
	}

	@GetMapping("/form")
	public RequestBodyResponseDto<UserVerificationRequestDto> getForm() {
		RequestBodyResponseDto<UserVerificationRequestDto> response = new RequestBodyResponseDto<UserVerificationRequestDto>(
				UserVerificationRequestDto.class);
		return response;
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public UserVerificationResponseDto create(@Valid @RequestBody UserVerificationRequestDto requestDto) {
		UserVerification userVerification = userVerificationMappper.getEntity(requestDto);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@DeleteMapping("/{id}")
	public UserVerificationResponseDto delete(@PathVariable long id) {
		UserVerification userVerification = userVerificationService.getById(id);
		UserVerificationResponseDto response = userVerificationMappper.getResponseDto(userVerification);
		userVerificationService.delete(userVerification);
		return response;
	}

	@PutMapping("/{id}")
	public UserVerificationResponseDto update(@PathVariable long id,
			@Valid @RequestBody UserVerificationRequestDto requestDto) {
		UserVerification userVerification = userVerificationService.getById(id);
		userVerificationMappper.loadEntity(requestDto, userVerification);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

}
