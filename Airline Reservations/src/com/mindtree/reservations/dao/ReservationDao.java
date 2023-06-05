package com.mindtree.reservations.dao;

import java.util.List;

import com.mindtree.reservations.entity.Flight;
import com.mindtree.reservations.entity.Passenger;
import com.mindtree.reservations.exception.daoexception.DaoException;

public interface ReservationDao {

	public boolean insertFlight(Flight flight) throws DaoException;

	public boolean insertPassenger(Passenger passenger) throws DaoException;

	public List<Passenger> fetchDetails() throws DaoException;

	public List<Passenger> fetchPassengers(String flightName) throws DaoException;

}
