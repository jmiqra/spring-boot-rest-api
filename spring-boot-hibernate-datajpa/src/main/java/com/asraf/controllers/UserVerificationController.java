package com.asraf.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.core.dtos.mapper.UserVerificationMappper;
import com.asraf.core.dtos.request.UserVerificationRequestDto;
import com.asraf.core.dtos.response.UserVerificationResponseDto;
import com.asraf.core.entities.UserVerification;
import com.asraf.core.services.UserVerificationService;

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
	public ResponseEntity<List<UserVerificationResponseDto>> getAll() {
		List<UserVerificationResponseDto> response = userVerificationMappper
				.getResponseDtos(this.userVerificationService.getAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable long id) {
		try {
			UserVerification userVerification = userVerificationService.getById(id);
			return ResponseEntity.ok(userVerificationMappper.getResponseDto(userVerification));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting the userVerification: " + ex.toString());
		}
	}

	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody UserVerificationRequestDto requestDto) {
		try {
			UserVerification userVerification = userVerificationMappper.getEntity(requestDto);
			userVerificationService.save(userVerification);
			return ResponseEntity.ok(userVerificationMappper.getResponseDto(userVerification));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error creating the userVerification: " + ex.toString());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		try {
			UserVerification userVerification = userVerificationService.getById(id);
			userVerificationService.delete(userVerification);
			return ResponseEntity.ok(userVerificationMappper.getResponseDto(userVerification));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting the userVerification: " + ex.toString());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable long id, @RequestBody UserVerificationRequestDto requestDto) {
		try {
			UserVerification userVerification = userVerificationService.getById(id);
			userVerificationMappper.loadEntity(requestDto, userVerification);
			userVerificationService.save(userVerification);
			return ResponseEntity.ok(userVerificationMappper.getResponseDto(userVerification));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating the userVerification: " + ex.toString());
		}
	}

}
