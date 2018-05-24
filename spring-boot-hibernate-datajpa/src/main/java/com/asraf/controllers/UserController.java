package com.asraf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.core.dtos.mapper.UserMappper;
import com.asraf.core.dtos.request.UserRequestDto;
import com.asraf.core.dtos.response.UserResponseDto;
import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.core.services.UserService;
import com.asraf.exceptions.EntityNotFoundException;

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
	public List<UserResponseDto> getAll() {
		List<UserResponseDto> response = userMappper.getResponseDtos(this.userService.getAll());
		return response;
	}

	@GetMapping("/get-by-email/{email}")
	public UserResponseDto getByEmail(@PathVariable String email) throws EntityNotFoundException {
		User user = userService.getByEmail(email);
		return userMappper.getResponseDto(user);
	}

	@GetMapping("/get-by-name/{name}")
	public List<UserResponseDto> getByName(@PathVariable String name) {
		List<User> users = userService.getByNameContains(name);
		return userMappper.getResponseDtos(users);
	}
	
	//Changes Iqra
	@GetMapping("/get-by-id/{id}")
	public UserResponseDto getById(@PathVariable long id) throws EntityNotFoundException {
		User user = userService.getById(id);
		return userMappper.getResponseDto(user);
	}

	@GetMapping("/search-crud")
	public List<UserResponseDto> getBySearchCrud(UserSearch searchItem) {
		List<User> users = userService.getBySearchCrud(searchItem);
		return userMappper.getResponseDtos(users);
	}

	/**
	 * @SampleUrl /users/search-crud-pageable?name=asraf&email=ahmed@test.com&page=0&size=4&sort=name,asc&sort=email,desc
	 * @param searchItem
	 * @param pageable
	 * @return
	 */
	@GetMapping("/search-crud-pageable")
	public Page<UserResponseDto> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable) {
		Page<User> pagedUser = userService.getBySearchCrudPageable(searchItem, pageable);
		return userMappper.getResponseDtos(pagedUser);
	}

	@GetMapping("/query")
	public List<UserResponseDto> getByQuery(@RequestParam(value = "search") String search) {
		Iterable<User> users = userService.getByQuery(search);
		return userMappper.getResponseDtos(users);
	}

	@PostMapping("")
	public UserResponseDto create(@Valid @RequestBody UserRequestDto requestDto) {
		User user = userMappper.getEntity(requestDto);
		userService.save(user);
		return userMappper.getResponseDto(user);
	}

	@DeleteMapping("/{id}")
	public UserResponseDto delete(@PathVariable long id) throws EntityNotFoundException {
		User user = userService.getById(id);
		userService.delete(user);
		return userMappper.getResponseDto(user);
	}

	@PutMapping("/{id}")
	public UserResponseDto update(@PathVariable long id, @Valid @RequestBody UserRequestDto requestDto)
			throws EntityNotFoundException {
		User user = userService.getById(id);
		userMappper.loadEntity(requestDto, user);
		userService.save(user);
		return userMappper.getResponseDto(user);
	}

}
