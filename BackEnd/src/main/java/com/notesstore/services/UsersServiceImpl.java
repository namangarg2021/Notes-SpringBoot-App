package com.notesstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesstore.dao.UsersDao;
import com.notesstore.entites.Users;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersDao;

	@Override
	public String addUser(Users user) {
		Users u = usersDao.findByEmail(user.getEmail());
		if (u == null) {
			usersDao.save(user);
			return "User Added Successfully";
		} else {
			return "User Already Exists";
		}
	}

	@Override
	public List<Users> getAllUsers() {
		List<Users> list = usersDao.findAll();
		return list;
	}

	@Override
	public Optional<Users> getNotesById(Long id) {
		System.out.println(id);
		return usersDao.findById(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		return usersDao.findByEmail(email);
	}

	@Override
	public Users getUserByEmailAndPassword(String email, String password) {
		Users user = usersDao.findByEmailAndPassword(email, password);
		return user;
	}

}
