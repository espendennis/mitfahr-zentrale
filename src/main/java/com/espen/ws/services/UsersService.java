package com.espen.ws.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.espen.ws.model.User;
import com.espen.ws.repositories.UserRepositoryInterface;

@Component
public class UsersService implements UsersServiceInterface {

	@Autowired
	private UserRepositoryInterface repository;

	public User findOne(String username) {
		return repository.findOne(username);
	}

	public UserDetails loadUserByUsername(String username) {
		User user = repository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return user;
	}

	public Collection<User> findAll() {
		return (Collection<User>) repository.findAll();
	}

	public User save(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public User update(User user) {

		User userToUpdate = findOne(user.getUsername());
		if (userToUpdate == null) {
			return null;
		}

		repository.save(user);

		return user;
	}

	public void delete(User user) {
		repository.delete(user);
	}

}
