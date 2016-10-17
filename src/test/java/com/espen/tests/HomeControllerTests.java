package com.espen.tests;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersService;
import com.espen.ws.services.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class HomeControllerTests {

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	Offer offer1 = new Offer("Berlin", "München", "user1", "2016-10-10T08:10", 30);
	Offer offer2 = new Offer("Frankfurt", "Hamburg", "user1", "2016-10-10T08:20", 30);
	Offer offer3 = new Offer("Berlin", "München", "admin", "2016-10-10T08:30", 30);
	Offer offer4 = new Offer("Berlin", "München", "user1", "2016-10-10T08:40", 30);
	Offer offer5 = new Offer("Berlin", "München", "user1", "2016-10-10T08:50", 40);
	User user1 = new User("user1", "Espen", "Dennis", "password", "dennis.espen@hotmail.com", "5551234");
	User user2 = new User("admin", "Espen", "Dennis", "123456seven", "dennis.espen@hotmail.com", "5551234");

	@Before
	public void init() {
		usersService.save(user1);
		user2.setAuthority("ROLE_ADMIN");
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

	}

	@Test
	public void testHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp")).andExpect(model().size(1));
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testAbout() throws Exception {
		mockMvc.perform(get("/about")).andExpect(status().isOk()).andExpect(view().name("about"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testApi() throws Exception {
		mockMvc.perform(get("/api")).andExpect(status().isOk()).andExpect(view().name("api"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testOfferCreated() throws Exception {
		mockMvc.perform(get("/offercreated").with(user("admin").password("123456seven"))).andExpect(status().isOk())
				.andExpect(view().name("offercreated"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testUserCreated() throws Exception {
		mockMvc.perform(get("/usercreated").with(user(user2))).andExpect(status().isOk())
				.andExpect(view().name("usercreated"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testShowOffer() throws Exception {
		mockMvc.perform(get("/offer/{id}", offer1.getId())).andExpect(status().isOk()).andExpect(view().name("offer"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"))
				.andExpect(model().attribute("offer", hasProperty("id", is(offer1.getId()))))
				.andExpect(model().attribute("offer", hasProperty("startingPoint", is(offer1.getStartingPoint()))))
				.andExpect(model().attribute("offer", hasProperty("destination", is(offer1.getDestination()))))
				.andExpect(model().attribute("offer", hasProperty("username", is(offer1.getUsername()))))
				.andExpect(model().attribute("offer", hasProperty("date", is(offer1.getDate()))));
	}

	@Test
	public void testLoggingIn() throws Exception {
		mockMvc.perform(formLogin().user("admin").password("123456seven")).andExpect(authenticated());

	}

	@Test
	public void testLogginFail() throws Exception {
		mockMvc.perform(formLogin().user("notExistent").password("asdasdad")).andExpect(unauthenticated());
	}

	@Test
	public void testNewOffer() throws Exception {
		Offer testoffer = new Offer("München", "Bozen", "user1", "2016-10-11T08:50", 100);
		mockMvc.perform(post("/newoffer").with(user("admin").password("123456seven"))
				.param("startingPoint", testoffer.getStartingPoint()).param("destination", testoffer.getDestination())
				.param("username", testoffer.getUsername()).param("date", testoffer.getDate())
				.param("price", new Double(testoffer.getPrice()).toString())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isFound())
				.andExpect(view().name("redirect:/offercreated"));
	}

	@Test
	public void testOwnProfile() throws Exception {
		mockMvc.perform(get("/ownprofile").with(user(user2))).andExpect(status().isFound())
				.andExpect(view().name("redirect:/profile/admin"));
	}

	@Test
	public void testNewUser() throws Exception {
		User testuser = new User("newuser", "Washington", "George", "Constitution", "george@thewhitehouse.gov",
				"01435673423");
		mockMvc.perform(post("/newuser").param("username", testuser.getUsername())
				.param("firstname", testuser.getFirstname()).param("lastname", testuser.getLastname())
				.param("password", testuser.getPassword()).param("email", testuser.getEmail())
				.param("phone", testuser.getPhone()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isFound()).andExpect(view().name("redirect:/usercreated"));
		User saveduser = usersService.findOne(testuser.getUsername());
		assertEquals(testuser, saveduser);
	}

	@Test
	public void testShowCreateUser() throws Exception {
		mockMvc.perform(get("/createuser")).andExpect(status().isOk()).andExpect(view().name("createuser"))
				.andExpect(forwardedUrl("/WEB-INF/tiles/templates/baselayout.jsp"));
	}

	@Test
	public void testCreateOffer() throws Exception {
		mockMvc.perform(get("/createoffer").with(user(user2))).andExpect(status().isOk())
				.andExpect(model().attribute("username", is(user2.getUsername())))
				.andExpect(view().name("createoffer"));
	}

	@Test
	public void testProfile() throws Exception {
		mockMvc.perform(get("/profile/{username}", user2.getUsername()).with(user(user2))).andExpect(status().isOk())
				.andExpect(view().name("profile"))
				.andExpect(model().attribute("user", hasProperty("username", is(user2.getUsername()))))
				.andExpect(model().attribute("user", hasProperty("firstname", is(user2.getFirstname()))))
				.andExpect(model().attribute("user", hasProperty("lastname", is(user2.getLastname()))))
				.andExpect(model().attribute("user", hasProperty("email", is(user2.getEmail()))))
				.andExpect(model().attribute("user", hasProperty("phone", is(user2.getPhone()))))
				.andExpect(model().attribute("user", hasProperty("password", is(user2.getPassword()))))
				.andExpect(model().attribute("user", hasProperty("authority", is(user2.getAuthority()))));
	}

}
