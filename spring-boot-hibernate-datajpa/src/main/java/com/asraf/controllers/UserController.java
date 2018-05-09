package com.asraf.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.dto.UserRequestDto;
import com.asraf.models.User;
import com.asraf.models.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Iterable<User>> getAll() {
		return ResponseEntity.ok(this.userDao.findAll());
	}

	@GetMapping("/get-by-email/{email}")
	@ResponseBody
	public ResponseEntity<User> getByEmail(@PathVariable String email) {
		try {
			User user = userDao.findByEmail(email);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("")
	@ResponseBody
	public ResponseEntity<Object> create(@RequestBody UserRequestDto requestDto) {
		User user = null;
		try {
			user = new User(requestDto.getEmail(), requestDto.getName());
			userDao.save(user);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error creating the user: " + ex.toString());
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable long id) {
		try {
			User user = userDao.findById(id).get();
			if (user == null) {
				return ResponseEntity.notFound().build();
			}
			userDao.delete(user);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting the user: " + ex.toString());
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable long id, @RequestBody UserRequestDto requestDto) {
		try {
			User user = userDao.findById(id).get();
			user.setEmail(requestDto.getEmail());
			user.setName(requestDto.getName());
			userDao.save(user);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating the user: " + ex.toString());
		}
	}

}
