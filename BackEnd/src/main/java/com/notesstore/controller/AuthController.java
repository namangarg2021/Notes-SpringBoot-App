package com.notesstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notesstore.config.jwtUtils;
import com.notesstore.entites.Users;
import com.notesstore.entites.jwtRequest;
import com.notesstore.entites.jwtResponse;
import com.notesstore.services.CustomUserDetailService;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private jwtUtils jwtUtil;

	@PostMapping(value = "/api/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
		Users userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
		System.out.println(userDetails.getPassword());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new jwtResponse(token));
	}

	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("User Not Found");
		}
	}

	@GetMapping("/api/currentuser")
	public Users getCurrentUser(Principal principal) {
		return ((this.customUserDetailService.loadUserByUsername(principal.getName())));
	}
}
