package com.mindtree.reservations.server;

import java.util.List;

import com.mindtree.reservations.entity.Flight;
import com.mindtree.reservations.entity.Passenger;
import com.mindtree.reservations.exception.ReservationException;

public interface ReservationService {

	public boolean addDetails(Flight flight) throws ReservationException;

	public boolean addDetails(Passenger passenger) throws ReservationException;

	public List<Passenger> getDetails(String source, String destination) throws ReservationException;

	public List<Passenger> getPassengers(String flightName) throws ReservationException;

}
