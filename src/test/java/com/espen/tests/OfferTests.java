package com.espen.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class OfferTests {

	@Autowired
	private OffersServiceInterface offersService;
	@Autowired
	private UsersServiceInterface usersService;

	private final Offer offer1 = new Offer("Frankfurt", "München", "user1", "2016-10-09T08:30", 30);
	private final Offer offer2 = new Offer("Berlin", "Hamburg", "user1", "2016-10-09T08:30", 30);
	private final Offer offer3 = new Offer("Berlin", "München", "user1", "2016-10-11T08:30", 30);
	private final Offer offer4 = new Offer("Berlin", "München", "user1", "2016-10-09T08:30", 200);
	private final Offer offer5 = new Offer("Berlin", "München", "admin", "2016-10-09T08:30", 30);
	private final User user1 = new User("user1", "Skywalker", "Lukas", "TheForce", "luke@therebellion.com", "5551234");
	private final User user2 = new User("admin", "Yodaa", "Master", "123456seven", "master.yoda@dagobah.com",
			"5551235");

	@Before
	public void init() {
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
	}

	@Test
	public void createAndGetOffer() {

		assertEquals("Retrieved offer should match", offer1, offersService.findOneById(offer1.getId()));
	}

	@Test
	public void testSearch() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, 2016);
		date.set(Calendar.MONTH, 9);
		date.set(Calendar.DAY_OF_MONTH, 10);
		assertEquals("Retrieved offer should match", offer5,
				offersService.findSeveral("Berlin", "München", date, 100).toArray()[0]);
		assertEquals("Only one offer should be found", 1,
				offersService.findSeveral("Berlin", "München", date, 100).size());
		assertEquals("Offers should match if sorting was successfull", offer5,
				offersService.findSeveral("Berlin", "München", date, 300).toArray()[1]);
	}

	@Test
	public void testGetAll() {
		assertEquals("5 offers should be found", 5, offersService.findAll().size());
	}

	@Test
	public void testDelete() {
		offersService.delete(offer4);
		offersService.delete(offer5);
		assertEquals("3 offers should be found", 3, offersService.findAll().size());
		Offer offerNull = offersService.findOneById(offer5.getId());
		assertNull("Should not find an offer", offerNull);
	}

	@Test
	public void testUpdate() {
		offer5.setPrice(1000);
		offer5.setUsername("BenjaminFranklin");
		offer5.setDate("2016-10-10T08:30");
		offer5.setDestination("Innsbruck");
		offer5.setStartingPoint("München");
		offersService.update(offer5);
		assertEquals("Retrieved offer should match", offer5, offersService.findOneById(offer5.getId()));
	}

	@Test
	public void testGetByUsername() {

		assertEquals("Retrieved offer should match", offer5,
				offersService.findByUsername(user2.getUsername()).toArray()[0]);
		assertEquals("Should find 4 offers", 4, offersService.findByUsername(user1.getUsername()).size());
	}

}
