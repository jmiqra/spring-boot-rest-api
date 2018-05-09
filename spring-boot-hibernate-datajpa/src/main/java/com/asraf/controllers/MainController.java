package com.asraf.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/main")
public class MainController {

	@GetMapping("")
	@ResponseBody
	public String getAll() {
		return "getAll";
	}

	@GetMapping("/{id}")
	@ResponseBody
	public String getById(@PathVariable("id") String id) {
		return "getById -> " + id;
	}
	
	@PostMapping("")
	@ResponseBody
	public String create() {
		return "create";
	}
	
	@PutMapping("")
	@ResponseBody
	public String update() {
		return "update";
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		return "delete -> " + id;
	}
}
