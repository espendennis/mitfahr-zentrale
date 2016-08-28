package com.espen.ws.services;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.espen.ws.model.User;

public interface UsersServiceInterface {

	User findOne(String username);

	UserDetails loadUserByUsername(String username);

	Collection<User> findAll();

	User save(User user);

	User update(User user);

	void delete(User user);

}