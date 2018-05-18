package com.asraf.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.asraf.core.dtos.mapper.UserProfileMappper;
import com.asraf.core.dtos.request.UserProfileRequestDto;
import com.asraf.core.dtos.response.UserProfileResponseDto;
import com.asraf.core.entities.User;
import com.asraf.core.entities.UserProfile;
import com.asraf.core.services.UserProfileService;
import com.asraf.core.services.UserService;

@RestController
@RequestMapping("")
public class UserProfileController {

	private UserProfileService userProfileService;
	private UserService userService;
	private UserProfileMappper userProfileMappper;

	@Autowired
	public UserProfileController(UserProfileService userProfileService, UserService userService,
			UserProfileMappper userProfileMappper) {
		this.userProfileMappper = userProfileMappper;
		this.userService = userService;
		this.userProfileService = userProfileService;
	}

	@GetMapping("/user-profiles")
	public ResponseEntity<List<UserProfileResponseDto>> getAll() {
		List<UserProfileResponseDto> response = userProfileMappper.getResponseDtos(this.userProfileService.getAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/user-profiles/{id}")
	public ResponseEntity<Object> getById(@PathVariable long id) {
		try {
			UserProfile userProfile = userProfileService.getById(id);
			return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting the userProfile: " + ex.toString());
		}
	}

	@PostMapping("users/{userId}/user-profiles")
	public ResponseEntity<Object> create(@PathVariable long userId, @RequestBody UserProfileRequestDto requestDto) {
		try {
			User user = this.userService.getById(userId);
			UserProfile userProfile = userProfileMappper.getEntity(requestDto, user);
			userProfileService.save(userProfile);
			return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException divex) {
			return ResponseEntity.badRequest().body("Profile has been created. Please update it.");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error creating the userProfile: " + ex.toString());
		}
	}

	@DeleteMapping("/user-profiles/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		try {
			UserProfile userProfile = userProfileService.getById(id);
			// TODO: stop removing parent-user
			userProfileService.delete(userProfile);
			return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting the userProfile: " + ex.toString());
		}
	}

	@PutMapping("/user-profiles/{id}")
	public ResponseEntity<Object> update(@PathVariable long id, @RequestBody UserProfileRequestDto requestDto) {
		try {
			UserProfile userProfile = userProfileService.getById(id);
			userProfileMappper.loadEntity(requestDto, userProfile);
			userProfileService.save(userProfile);
			return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
		} catch (NoSuchElementException nseex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating the userProfile: " + ex.toString());
		}
	}

}
