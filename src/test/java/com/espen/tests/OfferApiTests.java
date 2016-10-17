package com.espen.tests;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
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

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class OfferApiTests {

	@Autowired
	private OffersServiceInterface offersService;
	@Autowired
	private UsersServiceInterface usersService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper mapper;

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

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testGetOffers() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/offers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(5)))
				.andReturn();
		Offer[] offers = mapper.readValue(result.getResponse().getContentAsString(), Offer[].class);
		assertEquals(offers[0], offer1);
		assertEquals(offers[1], offer2);
		assertEquals(offers[2], offer3);
		assertEquals(offers[3], offer4);
		assertEquals(offers[4], offer5);
	}

	@Test
	public void testGetOfferById() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToTest = offers.get(0);
		MvcResult result = mockMvc.perform(get("/api/offers/{id}", offerToTest.getId()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andReturn();
		Offer retrievedOffer = mapper.readValue(result.getResponse().getContentAsString(), Offer.class);
		assertEquals(offerToTest, retrievedOffer);
	}

	@Test
	public void testPostOffer() throws Exception {
		Offer newOffer = new Offer("Innsbruck", "Lindau", "user1", "2016-10-10T09:50", 40);
		String json = mapper.writeValueAsString(newOffer);
		MvcResult result = mockMvc
				.perform(post("/api/offers").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isCreated()).andReturn();
		Offer retrieved = mapper.readValue(result.getResponse().getContentAsString(), Offer.class);
		assertEquals(newOffer, retrieved);
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		assertEquals(offers.size(), 6);
		assertEquals(offers.get(0), offer1);
		assertEquals(offers.get(1), offer2);
		assertEquals(offers.get(2), offer3);
		assertEquals(offers.get(3), offer4);
		assertEquals(offers.get(4), offer5);
		assertEquals(offers.get(5), newOffer);
	}

	@Test
	public void testPutOffer() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToChange = offers.get(4);
		offerToChange.setStartingPoint("München");
		offerToChange.setDestination("Frankfurt");
		offerToChange.setPrice(100);
		offerToChange.setUsername("admin");
		offerToChange.setDate("2017-11-11T09:25");
		String json = mapper.writeValueAsString(offerToChange);
		MvcResult result = mockMvc
				.perform(put("/api/offers/{id}", offerToChange.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(json))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		Offer offerRecieved = mapper.readValue(result.getResponse().getContentAsString(), Offer.class);
		assertEquals(offerToChange, offerRecieved);
		offers = (ArrayList<Offer>) offersService.findAll();
		assertEquals(offers.size(), 5);
		assertEquals(offers.get(0), offer1);
		assertEquals(offers.get(1), offer2);
		assertEquals(offers.get(2), offer3);
		assertEquals(offers.get(3), offer4);
		assertEquals(offers.get(4), offerToChange);

	}

	@Test
	public void testDeleteOffer() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToDelete = offers.get(0);
		mockMvc.perform(delete("/api/offers/{id}", offerToDelete.getId())).andExpect(status().isNoContent());
		assertNull(offersService.findOneById(offerToDelete.getId()));
		offers = (ArrayList<Offer>) offersService.findAll();
		assertEquals(offers.size(), 4);
		assertEquals(offers.get(0), offer2);
		assertEquals(offers.get(1), offer3);
		assertEquals(offers.get(2), offer4);
		assertEquals(offers.get(3), offer5);
	}

	@Test
	public void testGetOffersByUsername() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/offers/username/{username}", user1.getUsername()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4))).andReturn();
		Offer[] offersFromApi = mapper.readValue(result.getResponse().getContentAsString(), Offer[].class);
		ArrayList<Offer> offersFromDatabase = (ArrayList<Offer>) offersService.findByUsername("user1");
		assertEquals(offersFromApi[0], offersFromDatabase.get(0));
		assertEquals(offersFromApi[1], offersFromDatabase.get(1));
		assertEquals(offersFromApi[2], offersFromDatabase.get(2));
		assertEquals(offersFromApi[3], offersFromDatabase.get(3));
	}

	@Test
	public void testGetNotExistingOfferById() throws Exception {
		mockMvc.perform(get("/api/offers/0")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetOfferByNotExistingUsername() throws Exception {
		mockMvc.perform(get("/api/offers/username/test")).andExpect(status().isNotFound());
	}

	@Test
	public void testPutNotExistingOffer() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer testOffer = offers.get(0);
		testOffer.setId(0);
		String json = mapper.writeValueAsString(testOffer);
		mockMvc.perform(
				put("/api/offers/" + testOffer.getId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteNotExistingOffer() throws Exception {
		mockMvc.perform(delete("/api/offers/0")).andExpect(status().isNotFound());
	}

	@Test
	public void testPostOfferNotExistingUser() throws Exception {
		Offer newOffer = new Offer("Innsbruck", "Lindau", "test", "2016-10-10T09:50", 40);
		String json = mapper.writeValueAsString(newOffer);
		mockMvc.perform(post("/api/offers").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isNotFound());
	}

}
