package com.espen.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.espen.utils.UserJsonParser;
import com.espen.ws.model.User;
import com.espen.ws.services.UsersServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class UserApiDocumentation {

	@Autowired
	private UsersServiceInterface usersService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserJsonParser parser;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	ConstrainedFields fields = new ConstrainedFields(User.class);

	FieldDescriptor[] user = new FieldDescriptor[] { fieldWithPath("username").description("the username"),
			fieldWithPath("lastname").description("the user's lastname"),
			fieldWithPath("firstname").description("the user's firstname"),
			fieldWithPath("password").description("the user's password"),
			fieldWithPath("email").description("the user's email"),
			fieldWithPath("authority").description("the user's authority"),
			fieldWithPath("phone").description("the user's phone number"),
			fieldWithPath("authorities[].authority").description("the user's authorities"),
			fieldWithPath("accountNonExpired").description("if the user is not expired"),
			fieldWithPath("accountNonLocked").description("if the user is not locked"),
			fieldWithPath("credentialsNonExpired").description("if the user's credentials are not expired"),
			fieldWithPath("enabled").description("if the user is enabled") };
	FieldDescriptor[] userRequest = new FieldDescriptor[] { fields.withPath("username").description("the username"),
			fields.withPath("lastname").description("the user's lastname"),
			fields.withPath("firstname").description("the user's firstname"),
			fields.withPath("password").description("the user's password"),
			fields.withPath("email").description("the user's email"),
			fields.withPath("phone").description("the user's phone number"), };

	private MockMvc mockMvc;

	private RestDocumentationResultHandler document;

	User user1 = new User("user1", "LastName1", "FirstName1", "password", "mail1@mail.com", "5551111");
	User user2 = new User("user2", "LastName2", "FirstName2", "password", "mail2@mail.com", "5551112");

	@Before
	public void init() {
		user1.setAuthority("ROLE_ADMIN");
		usersService.save(user1);
		usersService.save(user2);

		// Must be before mockMvc=... if not every call to mockMvc will result
		// in a NullPointerexception
		this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document).build();

	}

	@Test
	public void getUsers() throws Exception {
		mockMvc.perform(get("/api/users")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(this.document.document(responseFields(fieldWithPath("[]").description("An array of users"))
						.andWithPrefix("[].", user)));
	}

	@Test
	public void getUserByUsername() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		mockMvc.perform(get("/api/users/{username}", userToTest.getUsername()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(this.document.document(responseFields(user))).andDo(this.document.document(pathParameters(
						parameterWithName("username").description("The username of the requested user"))));
	}

	@Test
	public void postUser() throws Exception {
		User userToTest = new User("lukeSkywalker", "Skywalker", "Lukas", "TheForce", "luke@tatooine.com", "5552223");
		String json = parser.toJson(userToTest);
		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isCreated()).andDo(this.document.document(requestFields(userRequest)))
				.andDo(this.document.document(responseFields(user)));
	}

	@Test
	public void putUser() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		userToTest.setLastname("Vader");
		userToTest.setFirstname("Darth");
		userToTest.setPassword("TheDarkS1de");
		userToTest.setEmail("darthvader@empire.com");
		userToTest.setPhone("5551234");
		String json = parser.toJson(userToTest);
		mockMvc.perform(put("/api/users/{username}", userToTest.getUsername())
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isOk())
				.andDo(this.document.document(pathParameters(
						parameterWithName("username").description("The username of the user to update"))))
				.andDo(this.document.document(requestFields(userRequest)))
				.andDo(this.document.document(responseFields(user)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void deleteUser() throws Exception {
		ArrayList<User> users = (ArrayList<User>) usersService.findAll();
		User userToTest = users.get(0);
		mockMvc.perform(delete("/api/users/{username}", userToTest.getUsername())).andExpect(status().isNoContent())
				.andDo(this.document.document(pathParameters(
						parameterWithName("username").description("The username of the user to delete"))));
	}

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}

}
