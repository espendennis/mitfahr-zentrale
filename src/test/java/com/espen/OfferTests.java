package com.espen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OfferTests {

	@Autowired
	private OffersServiceInterface offersService;
	@Autowired
	private UsersServiceInterface usersService;

	
	Offer offer1 = new Offer("Berlin", "München", "espendennis", "2016-10-10T08:30", 30);
	Offer offer2 = new Offer("Frankfurt", "München", "espendennis", "2016-10-10T08:30", 30);
	Offer offer3 = new Offer("Berlin", "Hamburg", "espendennis", "2016-10-10T08:30", 30);
	Offer offer4 = new Offer("Berlin", "München", "espendennis", "2016-10-10T08:30", 2000);
	Offer offer5 = new Offer("Berlin", "München", "POTUS", "2016-10-01T08:30", 30);
	User user1 = new User("espendennis", "espen", "dennis", "dbpass", "dennis.espen@hotmail.com", "5551234");
	User user2 = new User("POTUS", "Barack", "Obama", "123456seven", "barack.obama@hotmail.com", "5551234");
	
	@Before
	public void init(){

	}
	
	@Test
	public void createAndGetOffer(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		assertEquals("Retrieved offer should match", offer1, offersService.findOneById(offer1.getId()));
	}
	
	@Test
	public void testSearch(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, 2016);
		date.set(Calendar.MONTH, 10);
		date.set(Calendar.DAY_OF_MONTH, 2016);
		assertEquals("Retrieved offer should match", offer1, offersService.findSeveral("Berlin", "München", date, 1000).toArray()[0]);
		assertEquals("Only one offer should be found", 1, offersService.findSeveral("Berlin", "München", date, 1000).size());
	}
	
	@Test
	public void testGetAll(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		assertEquals("5 offers should be found", 5, offersService.findAll().size());
	}
	
	@Test
	public void testDelete(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		offersService.delete(offer4);
		offersService.delete(offer5);
		assertEquals("3 offers should be found", 3, offersService.findAll().size());
		Offer offerNull = offersService.findOneById(offer5.getId());
		assertNull("Should not find an offer", offerNull);
	}
	
	@Test
	public void testUpdate(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		System.out.println(offer5);
		offer5.setPrice(1000);
		offer5.setUsername("BenjaminFranklin");
		offer5.setDate("2016-10-10T08:30");
		offer5.setDestination("Innsbruck");
		offer5.setStartingPoint("München");
		offersService.update(offer5);
		assertEquals("Retrieved offer should match", offer5, offersService.findOneById(offer5.getId()));
		System.out.println(offer5);
	}
	
	@Test
	public void testGetByUsername(){
		usersService.save(user1);
		usersService.save(user2);
		offersService.save(offer1);
		offersService.save(offer2);
		offersService.save(offer3);
		offersService.save(offer4);
		offersService.save(offer5);
		
		assertEquals("Retrieved offer should match", offer5, offersService.findByUsername(offer5.getUsername()).toArray()[0]);
		assertEquals("Should find 4 offers", 4, offersService.findByUsername(offer1.getUsername()).size());
	}
	

}