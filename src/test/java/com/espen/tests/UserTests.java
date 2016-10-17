package com.espen.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.espen.ws.model.User;
import com.espen.ws.services.UsersServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class UserTests {

	@Autowired
	private UsersServiceInterface usersService;

	User user1 = new User("espendennis", "espen", "dennis", "dbpass", "dennis.espen@hotmail.com", "5551234");
	User user2 = new User("BenjaminFranklin", "Franklin", "Benjamin", "dbpass", "benjamin.franklin@hotmail.com",
			"5551234");

	@Before
	public void init() {
		usersService.save(user1);
		usersService.save(user2);
	}

	@Test
	public void createAndGetUser() {

		assertEquals("Retrieved user should match", user1, usersService.findOne(user1.getUsername()));
		assertEquals("There should be 2 users found", 2, usersService.findAll().size());
	}

	@Test
	public void Update() {
		user2.setAuthority("ROLE_ADMIN");
		user2.setEmail("barack.obama@hotmail.com");
		user2.setFirstname("Barack");
		user2.setLastname("Obama");
		user2.setPassword("123456seven");
		user2.setUsername("POTUS");
		user2.setPhone("5552345");
		usersService.update(user2);
		assertEquals("Retrieved user should match", user1, usersService.findOne(user1.getUsername()));
	}

	@Test
	public void delete() {
		usersService.delete(user2);
		assertEquals("One user should be found", 1, usersService.findAll().size());
		User userNull = usersService.findOne(user2.getUsername());
		assertNull("Should not find the user", userNull);
	}

}
