package com.mindtree.reservations.entity;

public class Passenger {
	private String passengerName, gender;
	private int age;
	private Flight flight;

	public Passenger() {

	}

	public Passenger(String passengerName, String gender, int age, Flight flight) {
		this.passengerName = passengerName;
		this.gender = gender;
		this.age = age;
		this.flight = flight;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
