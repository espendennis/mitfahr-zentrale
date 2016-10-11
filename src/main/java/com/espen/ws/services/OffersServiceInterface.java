package com.espen.ws.services;

import java.util.Calendar;
import java.util.Collection;

import com.espen.ws.model.Offer;

public interface OffersServiceInterface {

	Offer findOneById(int id);

	Collection<Offer> findByUsername(String username);

	Collection<Offer> findSeveral(String startingPoint, String destination, Calendar date, int price);

	Offer save(Offer offer);

	Offer update(Offer offer);

	void delete(Offer offer);

	Collection<Offer> findAll();

}