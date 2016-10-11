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

import com.espen.ws.model.Offer;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;

@Controller
public class OfferController {

	@Autowired
	private OffersServiceInterface offersService;
	
	@Autowired
	private UsersServiceInterface usersService;

	@RequestMapping(value = "/api/offers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> createOffer(@Validated @RequestBody Offer offer, HttpServletResponse response) throws IOException {
		if(usersService.findOne(offer.getUsername())==null){
			response.sendError(HttpStatus.NOT_FOUND.value());
		}
		Offer savedOffer = offersService.save(offer);
		return new ResponseEntity<Offer>(savedOffer, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/offers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Offer>> getOffers() {
		Collection<Offer> offers = offersService.findAll();
		return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/offers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> getOffersById(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		Offer offer = offersService.findOneById(id);
		if(offer == null){
			response.sendError(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/offers/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Offer>> getOffersByUsername(@PathVariable("username") String username, HttpServletResponse response) throws IOException {
	
		if( usersService.findOne(username) == null ){
			response.sendError(HttpStatus.NOT_FOUND.value());
		}
		Collection<Offer> offers = offersService.findByUsername(username);
		return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/offers/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @Validated @RequestBody Offer offer, HttpServletResponse response) throws IOException {
		offer.setId(id);
		if(offersService.findOneById(id)== null){
			response.sendError(HttpStatus.NOT_FOUND.value());
		}
		Offer updatedOffer = offersService.update(offer);
		return new ResponseEntity<Offer>(updatedOffer, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/offers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Offer> deleteOffer(@PathVariable Integer id, HttpServletResponse response) throws IOException {
		if(offersService.findOneById(id)== null){
			response.sendError(HttpStatus.NOT_FOUND.value());
			return null;
		}
		Offer offerToDelete = offersService.findOneById(id);
		offersService.delete(offerToDelete);
		return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
	}
}
