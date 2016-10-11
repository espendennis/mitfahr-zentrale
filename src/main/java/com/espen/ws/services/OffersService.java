package com.espen.ws.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.espen.ws.model.Offer;
import com.espen.ws.repositories.OfferRepositoryInterface;

@Component
public class OffersService implements OffersServiceInterface {
	@Autowired
	private OfferRepositoryInterface repository;

	public Offer findOneById(int id) {
		return repository.findOne(id);
	}

	public Collection<Offer> findByUsername(String username) {
		Collection<Offer> offers = (Collection<Offer>) repository.findAll();
		Collection<Offer> result = new ArrayList<Offer>();

		offers.stream().filter(o -> o.getUsername().equals(username))
				.sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate())).forEach(o -> result.add(o));
		return result;
	}

	public Collection<Offer> findSeveral(String startingPoint, String destination, Calendar date, int price) {

		Collection<Offer> offers = (Collection<Offer>) repository.findAll();
		Collection<Offer> result = new ArrayList<Offer>();

		offers.stream().filter(o -> o.getStartingPoint().equals(startingPoint))
				.filter(o -> o.getDestination().equals(destination))
				.filter(o -> o.getDateObject().getTime().getTime() < date.getTime().getTime())
				.filter(o -> o.getPrice() < price).sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
				.forEach(o -> result.add(o));
		return result;
	}

	public Offer save(Offer offer) {

		return repository.save(offer);

	}

	public Offer update(Offer offer) {
		repository.save(offer);

		return offer;
	}

	public void delete(Offer offer) {
		repository.delete(offer);
	}

	public Collection<Offer> findAll() {
		Collection<Offer> offers = (Collection<Offer>) repository.findAll();
		Collection<Offer> result = new ArrayList<Offer>();
		offers.stream().sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate())).forEach(o -> result.add(o));
		return result;

	}

}
