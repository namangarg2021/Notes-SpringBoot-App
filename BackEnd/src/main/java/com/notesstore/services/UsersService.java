package com.notesstore.services;

import java.util.List;
import java.util.Optional;

import com.notesstore.entites.Users;

public interface UsersService {

	public String addUser(Users user);

	public List<Users> getAllUsers();

	public Optional<Users> getNotesById(Long id);

	public Users getUserByEmail(String email);

	public Users getUserByEmailAndPassword(String email, String password);
}
