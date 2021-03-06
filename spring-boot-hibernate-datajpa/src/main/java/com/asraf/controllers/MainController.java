package com.asraf.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.resources.main.MainResource;

@RestController
@RequestMapping("")
public class MainController {

	@GetMapping("")
	public MainResource getAllLinks() {
		return new MainResource();
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") String id) {
		return "getById -> " + id;
	}

	@PostMapping("")
	public String create() {
		return "create";
	}

	@PutMapping("")
	public String update() {
		return "update";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") String id) {
		return "delete -> " + id;
	}
}
