package com.notesstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notesstore.config.jwtUtils;
import com.notesstore.entites.Users;
import com.notesstore.entites.jwtRequest;
import com.notesstore.entites.jwtResponse;
import com.notesstore.services.CustomUserDetailService;
import com.notesstore.services.UsersService;

@RestController
@CrossOrigin
public class UsersController {

	@Autowired
	UsersService usersService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private jwtUtils jwtUtil;

	@PostMapping(path = "/register/user", consumes = { "application/json" })
	public String addUsers(@RequestBody Users user) {
		return usersService.addUser(user);
	}

	@PostMapping(path = "/login/user", consumes = { "application/json" })
	public jwtResponse checkCredentials(@RequestBody jwtRequest jwtRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			return new jwtResponse("Bad Credentials");
		}
		Users userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
		System.out.println(userDetails.getPassword());
		String token = jwtUtil.generateToken(userDetails);
		return new jwtResponse(token);
	}

	@GetMapping(path = "/users")
	public List<Users> allUsers() {
		return usersService.getAllUsers();
	}

	@GetMapping("/users/email/{email}")
	public Users getUserByEmail(@PathVariable("email") String email) {
		return usersService.getUserByEmail(email);
	}
}
