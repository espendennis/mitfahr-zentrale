package com.espen.ws.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offers")
public class Offer {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@Size(min = 1, max = 60)
	private String startingPoint;

	@NotNull
	@Size(min = 1, max = 60)
	private String destination;

	@NotNull
	private String username;

	@NotNull
	private Calendar date;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	private double price;

	public Offer() {
		this.date = Calendar.getInstance();
		this.date.set(Calendar.SECOND, 0);
	}

	public Offer(int id, String startingPoint, String destination, String username, String date, double price) {
		super();
		this.id = id;
		this.startingPoint = startingPoint;
		this.destination = destination;
		this.username = username;	
		this.date = Calendar.getInstance();
		this.setDate(date);
		this.price = price;
	}

	public Offer(String startingPoint, String destination, String username, String date, double price) {
		this(0, startingPoint, destination, username, date, price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH:mm");
		String dateString = sdf.format(date.getTime());
		return dateString;
	}

	public void setDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH:mm");
		try {		
			this.date.setTime(sdf.parse(dateString));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	public Calendar getDateObject() {
		return date;
	}

	public void setDateObject(Calendar date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startingPoint == null) ? 0 : startingPoint.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (startingPoint == null) {
			if (other.startingPoint != null)
				return false;
		} else if (!startingPoint.equals(other.startingPoint))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", startingPoint=" + startingPoint + ", destination=" + destination + ", username="
				+ username + ", year=" + date.get(Calendar.YEAR) + ", month=" + date.get(Calendar.MONTH) + ", day="
				+ date.get(Calendar.DAY_OF_MONTH) + ", hour=" + date.get(Calendar.HOUR_OF_DAY) + ", minute="
				+ date.get(Calendar.MINUTE) + ", price=" + price + "]";
	}

}