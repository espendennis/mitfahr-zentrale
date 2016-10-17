package com.espen.tests;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.espen.utils.UserJsonParser;
import com.espen.ws.model.User;
import com.espen.ws.services.UsersServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class UserApiTests {

	@Autowired
	private UsersServiceInterface usersService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserJsonParser parser;

	private MockMvc mockMvc;

	User user1 = new User("user1", "LastName1", "FirstName1", "password", "mail1@mail.com", "5551111");
	User user2 = new User("user2", "LastName2", "FirstName2", "password", "mail2@mail.com", "5551112");
	User user3 = new User("user3", "LastName3", "FirstName3", "password", "mail3@mail.com", "5551113");
	User user4 = new User("user4", "LastName4", "FirstName4", "password", "mail4@mail.com", "5551114");
	User user5 = new User("user5", "LastName5", "FirstName5", "password", "mail5@mail.com", "5551115");

	@Before
	public void init() {
		user1.setAuthority("ROLE_ADMIN");
		user2.setAuthority("ROLE_ADMIN");
		user3.setAuthority("ROLE_ADMIN");
		usersService.save(user1);
		usersService.save(user2);
		usersService.save(user3);
		usersService.save(user4);
		usersService.save(user5);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		;
	}

	@Test
	public void testGetUsers() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/users")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(5)))
				.andReturn();

		ArrayList<User> users = parser.deserializeArray(result.getResponse().getContentAsString());
		assertEquals(users.get(0), user1);
		assertEquals(users.get(1), user2);
		assertEquals(users.get(2), user3);
		assertEquals(users.get(3), user4);
		assertEquals(users.get(4), user5);

	}

	@Test
	public void testGetUserByUsername() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		MvcResult result = mockMvc.perform(get("/api/users/{username}", userToTest.getUsername()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
		User retrievedUser = parser.deserializeUser(result.getResponse().getContentAsString());
		assertEquals(userToTest, retrievedUser);
	}

	@Test
	public void testPostUser() throws Exception {
		User userToTest = new User("lukeSkywalker", "Skywalker", "Lukas", "TheForce", "luke@tatooine.com", "5552223");
		String json = parser.toJson(userToTest);
		MvcResult result = mockMvc
				.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isCreated()).andReturn();
		User returnedUser = parser.deserializeUser(result.getResponse().getContentAsString());
		assertEquals(userToTest, returnedUser);
		assertNotNull(usersService.findOne("lukeSkywalker"));
		assertEquals(usersService.findOne("lukeSkywalker"), userToTest);
		assertEquals(usersService.findAll().size(), 6);
		assertEquals(usersService.findOne(user1.getUsername()), user1);
		assertEquals(usersService.findOne(user2.getUsername()), user2);
		assertEquals(usersService.findOne(user3.getUsername()), user3);
		assertEquals(usersService.findOne(user4.getUsername()), user4);
		assertEquals(usersService.findOne(user5.getUsername()), user5);
	}

	@Test
	public void testPutUser() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		userToTest.setLastname("Vader");
		userToTest.setFirstname("Darth");
		userToTest.setPassword("TheDarkS1de");
		userToTest.setEmail("darthvader@empire.com");
		userToTest.setPhone("5551234");
		String json = parser.toJson(userToTest);
		MvcResult result = mockMvc
				.perform(put("/api/users/{username}", userToTest.getUsername())
						.contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		User userRetrieved = parser.deserializeUser(result.getResponse().getContentAsString());
		assertEquals(userToTest, userRetrieved);
		assertEquals(usersService.findAll().size(), 5);
		assertEquals(usersService.findOne(userToTest.getUsername()), userToTest);
		assertNotEquals(usersService.findOne(userToTest.getUsername()), user1);
		assertEquals(usersService.findOne(user2.getUsername()), user2);
		assertEquals(usersService.findOne(user3.getUsername()), user3);
		assertEquals(usersService.findOne(user4.getUsername()), user4);
		assertEquals(usersService.findOne(user5.getUsername()), user5);
	}

	@Test
	public void testDeleteUser() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		mockMvc.perform(delete("/api/users/{username}", userToTest.getUsername())).andExpect(status().isNoContent());
		assertNull(usersService.findOne(userToTest.getUsername()));
		assertEquals(usersService.findAll().size(), 4);
		assertEquals(usersService.findOne(user2.getUsername()), user2);
		assertEquals(usersService.findOne(user3.getUsername()), user3);
		assertEquals(usersService.findOne(user4.getUsername()), user4);
		assertEquals(usersService.findOne(user5.getUsername()), user5);
	}

	@Test
	public void testPostExistingUser() throws Exception {
		User userToTest = usersService.findOne("user1");
		String json = parser.toJson(userToTest);
		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isConflict());
	}

	@Test
	public void testGetNotExtistingUser() throws Exception {
		mockMvc.perform(get("/api/users/test")).andExpect(status().isNotFound());

	}

	@Test
	public void testPutNotExistingUser() throws Exception {
		User userToTest = new User("testuser", "testname", "testname", "dbpass", "test@test.com", "01453456756");
		String json = parser.toJson(userToTest);
		mockMvc.perform(put("/api/users/" + userToTest.getUsername()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json)).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteNotExistingUser() throws Exception {
		mockMvc.perform(delete("/api/users/test")).andExpect(status().isNotFound());
	}

}
