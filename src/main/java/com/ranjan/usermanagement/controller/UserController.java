package com.ranjan.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranjan.usermanagement.exception.ForbiddenException;
import com.ranjan.usermanagement.exception.ResourceNotFoundException;
import com.ranjan.usermanagement.model.User;
import com.ranjan.usermanagement.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
@Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "View a list of available users", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@ApiOperation(value = "Get a user by Id")
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId, Authentication auth)
			throws ResourceNotFoundException, ForbiddenException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		final User userAccount = (User) auth.getPrincipal();

		if (userAccount.getId() == userId || "ADMIN".equals(userAccount.getRole()))
			return ResponseEntity.ok().body(user);
		 else
			throw new ForbiddenException("User is forbidden to access this id :: " + userId);
	}

	@ApiOperation(value = "Add a user")
	@PostMapping("users")
	public User createUser(@Valid @RequestBody User user) {
		user.setRole("USER");
		return userRepository.save(user);
	}

	@ApiOperation(value = "Update a user")
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails, Authentication auth)
			throws ResourceNotFoundException, ForbiddenException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		final User userAccount = (User) auth.getPrincipal();
		if (userAccount.getId() == userId || "ADMIN".equals(userAccount.getRole())) {
			user.setEmail(userDetails.getEmail());
			user.setLastName(userDetails.getLastName());
			user.setFirstName(userDetails.getFirstName());
			user.setPassword(userDetails.getPassword());
			// To Do : Bug Resolution - Date of birth is not getting updated
			user.setDateOfBirth(userDetails.getDateOfBirth());
			final User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);

		} else
			throw new ForbiddenException("User is forbidden to access this id :: " + userId);
	}

	@ApiOperation(value = "Delete a user")
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable(value = "id") Long userId, Authentication auth)
			throws ResourceNotFoundException, ForbiddenException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		final User userAccount = (User) auth.getPrincipal();

		if (userAccount.getId() == userId || "ADMIN".equals(userAccount.getRole()))
			userRepository.delete(user);
		 else
			throw new ForbiddenException("User is forbidden to access this id :: " + userId);
	}
}
