package com.notesstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.notesstore.dao.UsersDao;
import com.notesstore.entites.Users;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UsersDao userDao;

	@Override
	public Users loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found");
		}
		return user;
	}

}
