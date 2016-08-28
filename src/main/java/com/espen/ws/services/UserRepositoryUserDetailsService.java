package com.espen.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.espen.ws.model.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersServiceInterface usersService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersService.findOne(username);
		System.out.println(user);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}

		return user;
	}

}
