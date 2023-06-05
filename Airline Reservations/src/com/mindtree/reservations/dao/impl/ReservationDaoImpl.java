package com.mindtree.reservations.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.reservations.dao.ReservationDao;
import com.mindtree.reservations.entity.Flight;
import com.mindtree.reservations.entity.Passenger;
import com.mindtree.reservations.exception.daoexception.DaoException;
import com.mindtree.reservations.exception.daoexception.DatabaseConnectionException;
import com.mindtree.reservations.exception.daoexception.NoFlightFoundException;
import com.mindtree.reservations.utility.JDBCUtil;

public class ReservationDaoImpl implements ReservationDao {
	JDBCUtil db = new JDBCUtil();
	Connection con = null;
	Statement statmnt = null;
	ResultSet result = null;
	PreparedStatement state = null;
	CallableStatement stmt = null;

	// Inserting flight details to Database PreparedStatement
	@Override
	public boolean insertFlight(Flight flight) throws DaoException {
		boolean bool = false;
		try {
			con = db.getConnection();
			state = con.prepareStatement("insert into flight values(?,?,?)");
			state.setString(1, flight.getFlightName());
			state.setString(2, flight.getSource());
			state.setString(3, flight.getDestination());
			bool = state.execute();
			if (!bool)
				throw new NoFlightFoundException("No such Flight available");
			return bool;
		} catch (DatabaseConnectionException | NoFlightFoundException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			db.closeConnection(state);
			db.closeConnection(con);
			return bool;
		}
	}

	// Inserting passenger details to Database using CallableStatement (Stored
	// Procedure)
	@Override
	public boolean insertPassenger(Passenger passenger) throws DaoException {
		boolean bool = false;
		try {
			con = db.getConnection();
			stmt = con.prepareCall("{call insertPassengers(?,?,?,?)}");
			stmt.setString(1, passenger.getPassengerName());
			stmt.setString(2, passenger.getGender());
			stmt.setInt(3, passenger.getAge());
			stmt.setString(4, passenger.getFlight().getFlightName());
			bool = stmt.execute();
			if (!bool)
				throw new NoFlightFoundException("No such Flight available");
			return bool;
		} catch (DatabaseConnectionException | NoFlightFoundException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			db.closeConnection(stmt);
			db.closeConnection(con);
			return bool;
		}
	}

	// Fetching all Flights and Passengers data
	@Override
	public List<Passenger> fetchDetails() throws DaoException {
		List<Passenger> passenger = new ArrayList<Passenger>();
		String sql = "select f.flight_name, f.source, f.destination, p.passenger_name, p.gender, p.age from flight f "
				+ "join passenger p on f.flight_name = p.flight_name";
		try {
			con = db.getConnection();
			state = con.prepareStatement(sql);
			result = state.executeQuery();
			while (result.next()) {
				Passenger passenger1 = new Passenger();
				Flight flight1 = new Flight();
				flight1.setFlightName(result.getString("flight_name"));
				flight1.setSource(result.getString("source"));
				flight1.setDestination(result.getString("destination"));

				passenger1.setPassengerName(result.getString("passenger_name"));
				passenger1.setGender(result.getString("gender"));
				passenger1.setAge(result.getInt("age"));
				passenger1.setFlight(flight1);

				passenger.add(passenger1);
			}
		} catch (DatabaseConnectionException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			db.closeConnection(state);
			db.closeConnection(con);
		}
		return passenger;
	}

	// Fetching Passengers data for particular Flight using CallableStatement
	// (Stored Procedure)
	@Override
	public List<Passenger> fetchPassengers(String flightName) throws DaoException {
		List<Passenger> passenger = new ArrayList<Passenger>();
		try {
			con = db.getConnection();
			stmt = con.prepareCall("{call fetchPassengers(?)}");
			stmt.setString(1, flightName);
			Boolean hasResult = stmt.execute();
			if (hasResult) {
				ResultSet result = stmt.getResultSet();
				while (result.next()) {
					Passenger passenger1 = new Passenger();

					passenger1.setPassengerName(result.getString("passenger_name"));
					passenger1.setGender(result.getString("gender"));
					passenger1.setAge(result.getInt("age"));
					passenger.add(passenger1);
				}
				// stmt.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			db.closeConnection(stmt);
			db.closeConnection(con);
		}
		return passenger;
	}

}
