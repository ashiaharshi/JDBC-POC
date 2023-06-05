package com.mindtree.reservations.entity;

public class Flight {
	private String flightName, source, destination;

	public Flight() {

	}

	public Flight(String flightName, String source, String destination) {
		super();
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
