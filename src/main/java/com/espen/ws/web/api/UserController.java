package com.espen.ws.web.api;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.espen.ws.model.User;
import com.espen.ws.services.UsersServiceInterface;

@Controller
public class UserController {

	@Autowired
	private UsersServiceInterface usersService;

	@RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Validated @RequestBody User user, HttpServletResponse response)
			throws IOException {
		User testExist = usersService.findOne(user.getUsername());
		if (testExist != null) {
			response.sendError(HttpStatus.CONFLICT.value());
		}
		User savedUser = usersService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> users = usersService.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username,
			HttpServletResponse response) throws IOException {
		User user = usersService.findOne(username);

		if (user == null) {
			response.sendError(HttpStatus.NOT_FOUND.value());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{username}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@Validated @RequestBody User user, HttpServletResponse response)
			throws IOException {
		User updatedUser = usersService.update(user);

		if (updatedUser == null) {
			response.sendError(HttpStatus.NOT_FOUND.value());
		}

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable String username, HttpServletResponse response)
			throws IOException {
		User userToDelete = usersService.findOne(username);

		if (userToDelete == null) {
			response.sendError(HttpStatus.NOT_FOUND.value());
			return null;
		}
		usersService.delete(userToDelete);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}

}
