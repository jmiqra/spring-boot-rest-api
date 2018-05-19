package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.core.dtos.mapper.UserMappper;
import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.core.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private UserMappper userMappper;

	@Autowired
	public UserController(UserService userService, UserMappper userMappper) {
		this.userMappper = userMappper;
		this.userService = userService;
	}

	@GetMapping("")
	public ResponseEntity<List<UserResponseDto>> getAll() {
		List<UserResponseDto> response = userMappper.getResponseDtos(this.userService.getAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get-by-email/{email}")
	public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
		User user = userService.getByEmail(email);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userMappper.getResponseDto(user));
	}

	@GetMapping("/get-by-name/{name}")
	public ResponseEntity<List<UserResponseDto>> getByName(@PathVariable String name) {
		List<User> users = userService.getByNameContains(name);
		return ResponseEntity.ok(userMappper.getResponseDtos(users));
	}

	@GetMapping("/search-crud")
	public ResponseEntity<List<UserResponseDto>> getBySearchCrud(UserSearch searchItem) {
		List<User> users = userService.getBySearchCrud(searchItem);
		return ResponseEntity.ok(userMappper.getResponseDtos(users));
	}

	/**
	 * @SampleUrl /users/search-crud-pageable?name=asraf&email=ahmed@test.com&page=0&size=4&sort=name,asc&sort=email,desc
	 * @param searchItem
	 * @param pageable
	 * @return
	 */
	@GetMapping("/search-crud-pageable")
	public ResponseEntity<Page<UserResponseDto>> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable) {
		Page<User> pagedUser = userService.getBySearchCrudPageable(searchItem, pageable);
		return ResponseEntity.ok(userMappper.getResponseDtos(pagedUser));
	}

	@PostMapping("")
	public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDto requestDto) {
		User user = userMappper.getEntity(requestDto);
		userService.save(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		User user = userService.getById(id);
		userService.delete(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody UserRequestDto requestDto) {
		User user = userService.getById(id);
		userMappper.loadEntity(requestDto, user);
		userService.save(user);
		return ResponseEntity.ok(user);
	}

}
