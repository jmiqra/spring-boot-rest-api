package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.asraf.exceptions.EntityNotFoundException;

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
	public ResponseEntity<UserProfileResponseDto> getById(@PathVariable long id) {
		UserProfile userProfile = userProfileService.getById(id);
		return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
	}

	@PostMapping("users/{userId}/user-profiles")
	public ResponseEntity<UserProfileResponseDto> create(@PathVariable long userId,
			@Valid @RequestBody UserProfileRequestDto requestDto) throws EntityNotFoundException {
		User user = this.userService.getById(userId);
		UserProfile userProfile = userProfileMappper.getEntity(requestDto, user);
		userProfileService.save(userProfile);
		return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
	}

	@DeleteMapping("/user-profiles/{id}")
	public ResponseEntity<UserProfileResponseDto> delete(@PathVariable long id) {
		UserProfile userProfile = userProfileService.getById(id);
		// TODO: stop removing parent-user
		userProfileService.delete(userProfile);
		return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
	}

	@PutMapping("/user-profiles/{id}")
	public ResponseEntity<UserProfileResponseDto> update(@PathVariable long id,
			@Valid @RequestBody UserProfileRequestDto requestDto) {
		UserProfile userProfile = userProfileService.getById(id);
		userProfileMappper.loadEntity(requestDto, userProfile);
		userProfileService.save(userProfile);
		return ResponseEntity.ok(userProfileMappper.getResponseDto(userProfile));
	}

}
