package com.varejonline.budgetsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varejonline.budgetsystem.model.User;
import com.varejonline.budgetsystem.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> result = userService.findAll();
		
		if (result.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(result);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> result = userService.findById(id);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(204).build();	
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> logIn(@RequestBody User user) {
		Optional<User> result = userService.logIn(user);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<User> logOut(@RequestBody User user) {
		Optional<User> result = userService.logOut(user);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
}
