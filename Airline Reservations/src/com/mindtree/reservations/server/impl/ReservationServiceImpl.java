package com.mindtree.reservations.server.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.mindtree.reservations.dao.ReservationDao;
import com.mindtree.reservations.dao.impl.ReservationDaoImpl;
import com.mindtree.reservations.entity.Flight;
import com.mindtree.reservations.entity.Passenger;
import com.mindtree.reservations.exception.ReservationException;
import com.mindtree.reservations.exception.daoexception.DaoException;
import com.mindtree.reservations.exception.serviceexception.PlaceNotFoundException;
import com.mindtree.reservations.server.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	
	//Dynamic method dispatch polymorphism(Runtime Polymorphism) 
	private ReservationDao dao = new ReservationDaoImpl();
	
	//Adding flight details to Database by passing flight details to DAO layer
	@Override
	public boolean addDetails(Flight flight) throws ReservationException {
		boolean bool = false;
		try {
			bool = dao.insertFlight(flight);
			if(bool)
				return bool;
		}catch (DaoException e) {
			throw new ReservationException(e.getMessage(),e);
		}
		return bool;
	}
	
	//Adding passenger details to Database by passing passenger details to DAO layer
	@Override
	public boolean addDetails(Passenger passenger) throws ReservationException {
		boolean bool = false;
		try {
			bool = dao.insertPassenger(passenger);
			if(bool)
				return bool;
		}catch (DaoException e) {
			throw new ReservationException(e.getMessage(),e);
		}
		return bool;
	}
	
	//Fetching Flights and Passengers data from DAO Layer and adding data to List by Source and Destination 
	//Using Streams and Filters
	@Override
	public List<Passenger> getDetails(String source, String destination) throws ReservationException {
		try {
			List<Passenger> passenger = dao.fetchDetails();
			List<Passenger> result = passenger.stream().filter(p->(p.getFlight().getSource().equalsIgnoreCase(source)&&p.getFlight().getDestination().equalsIgnoreCase(destination))).collect(Collectors.toList());
			if(result.size()!=0)
				return result;
			else
				throw new PlaceNotFoundException("No such source or destination found");
		}catch (PlaceNotFoundException e) {
			//System.out.println(e.getLocalizedMessage());
			throw new ReservationException(e.getMessage(),e);
		}
	}

	//Fetching Passenger details of particular flight
	@Override
	public List<Passenger> getPassengers(String flightName) throws ReservationException {
		try {
			List<Passenger> passenger = dao.fetchPassengers(flightName);
			if(passenger.size()>0)
				return passenger;
			else
				throw new PlaceNotFoundException("No such source or destination found");
		}catch (PlaceNotFoundException e) {
			//System.out.println(e.getLocalizedMessage());
			throw new ReservationException(e.getMessage(),e);
		}
	}

}
