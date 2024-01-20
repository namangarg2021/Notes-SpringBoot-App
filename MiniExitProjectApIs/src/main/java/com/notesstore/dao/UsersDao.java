package com.notesstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notesstore.entites.Users;

public interface UsersDao extends JpaRepository<Users, Long> {
	// For Registration
	public Users findByEmail(String email);

	// For Login
	public Users findByEmailAndPassword(String email, String password);
}
