package com.espen.ws.search;

import java.util.Calendar;

public class OfferSearchCriteria {

	private String startingPoint;
	private String destination;
	private String username;
	private Calendar minDate;
	private Calendar maxDate;
	private int minYear;
	private int maxYear;
	private int minMonth;
	private int maxMonth;
	private int minDay;
	private int maxDay;
	private int minHour;
	private int maxHour;
	private int minMinute;
	private int maxMinute;
	private int minSecond;
	private int maxSecond;
	private double minPrice;
	private double maxPrice;

	public OfferSearchCriteria() {
		minYear = -1;
		maxYear = -1;
		minMonth = -1;
		maxMonth = -1;
		minDay = -1;
		maxDay = -1;
		minHour = -1;
		maxHour = -1;
		minMinute = -1;
		maxMinute = -1;
		minSecond = -1;
		maxSecond = -1;
		minPrice = -1;
		maxPrice = -1;
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

	public Calendar getMinDate() {
		return minDate;
	}

	public void setMinDate(Calendar minDate) {
		this.minDate = minDate;
	}

	public Calendar getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Calendar maxDate) {
		this.maxDate = maxDate;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	public int getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	public int getMinDay() {
		return minDay;
	}

	public void setMinDay(int minDay) {
		this.minDay = minDay;
	}

	public int getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}

	public int getMinHour() {
		return minHour;
	}

	public void setMinHour(int minHour) {
		this.minHour = minHour;
	}

	public int getMaxHour() {
		return maxHour;
	}

	public void setMaxHour(int maxHour) {
		this.maxHour = maxHour;
	}

	public int getMinMinute() {
		return minMinute;
	}

	public void setMinMinute(int minMinute) {
		this.minMinute = minMinute;
	}

	public int getMaxMinute() {
		return maxMinute;
	}

	public void setMaxMinute(int maxMinute) {
		this.maxMinute = maxMinute;
	}

	public int getMinSecond() {
		return minSecond;
	}

	public void setMinSecond(int minSecond) {
		this.minSecond = minSecond;
	}

	public int getMaxSecond() {
		return maxSecond;
	}

	public void setMaxSecond(int maxSecond) {
		this.maxSecond = maxSecond;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public String toString() {
		return "OfferSearchCriteria [startingPoint=" + startingPoint + ", destination=" + destination + ", username="
				+ username + ", minDate=" + minDate + ", maxDate=" + maxDate + ", minYear=" + minYear + ", maxYear="
				+ maxYear + ", minMonth=" + minMonth + ", maxMonth=" + maxMonth + ", minDay=" + minDay + ", maxDay="
				+ maxDay + ", minHour=" + minHour + ", maxHour=" + maxHour + ", minMinute=" + minMinute + ", maxMinute="
				+ maxMinute + ", minSecond=" + minSecond + ", maxSecond=" + maxSecond + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}

}
