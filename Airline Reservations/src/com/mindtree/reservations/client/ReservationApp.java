package com.mindtree.reservations.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.reservations.entity.Flight;
import com.mindtree.reservations.entity.Passenger;
import com.mindtree.reservations.exception.ReservationException;
import com.mindtree.reservations.server.ReservationService;
import com.mindtree.reservations.server.impl.ReservationServiceImpl;

public class ReservationApp {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean flag = true;
		ReservationService serve = new ReservationServiceImpl();
		Flight flight;
		Passenger passenger;
		do {
			System.out.println("Enter:\n 1. Create Flights\n 2. Create Passengers\n "
					+ "3. Fetch Flights and Passengers data by Source and Destination\n 4. fetch the passenger names for given flight\n 5. Exit");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				flight = createFlight();
				boolean bool;
				try {
					bool = serve.addDetails(flight);
					if (bool)
						System.out.println("Inserted Details Successful");
				} catch (ReservationException e1) {
					e1.printStackTrace();
				}
				break;
				
			case 2:
				passenger = createPassenger();
				try {
					boolean bool1 = serve.addDetails(passenger);
					if (bool1)
						System.out.println("Inserted Details Successful");
				} catch (ReservationException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter Source:");
				String source = sc.next();
				System.out.println("Enter Destination:");
				String destination = sc.next();
				try {
					List<Passenger> passengers = serve.getDetails(source, destination);
					if (passengers != null)
						display(passengers);
				} catch (ReservationException e) {
					// System.out.println(e.getLocalizedMessage());
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter the Flight name:");
				String flightName = sc.next();
				List<Passenger> passengers = new ArrayList<Passenger>();
				try {
					passengers = serve.getPassengers(flightName);
					if (passengers != null)
						displayPassengers(passengers);
				} catch (ReservationException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Thank you");
				flag = false;
				break;

			default:

			}
		} while (flag);

	}

	// Displaying Passengers data for particular Flight
	private static void displayPassengers(List<Passenger> passengers) {
		System.out.println("passenger_name \t gender \t age");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Passenger p : passengers)
			System.out.println(p.getPassengerName() + "\t" + p.getGender() + "\t" + p.getAge());
		System.out.println();
	}

	// Displaying Flights and Passengers data by Source and Destination
	private static void display(List<Passenger> passengers) {
		System.out.println("flight_name \t source \t destination \t passenger_name \t gender \t age");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Passenger p : passengers)
			System.out.println(p.getFlight().getFlightName() + "\t" + p.getFlight().getSource() + "\t"
					+ p.getFlight().getDestination() + "\t" + p.getPassengerName() + "\t" + p.getGender() + "\t"
					+ p.getAge());
		System.out.println();
	}

	// Adding Passenger Details
	private static Passenger createPassenger() {
		System.out.println("Enter the passenger name:");
		String passengerName = sc.next();
		System.out.println("Enter the Gender:");
		String gender = sc.next();
		System.out.println("Enter the Age:");
		int age = sc.nextInt();
		System.out.println("Enter the Flight name:");
		String flightName = sc.next();
		Flight flight = new Flight();
		flight.setFlightName(flightName);
		Passenger p = new Passenger(passengerName, gender, age, flight);
		return p;
	}

	// Adding Flight Details
	private static Flight createFlight() {
		System.out.println("Enter the flight name:");
		String flightName = sc.next();
		System.out.println("Enter the Source address:");
		String source = sc.next();
		System.out.println("Enter the Destination Address:");
		String destination = sc.next();
		Flight f = new Flight(flightName, source, destination);
		return f;
	}

}
