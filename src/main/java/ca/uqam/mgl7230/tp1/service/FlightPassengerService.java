package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightPassengerService {

    private static final Logger logger = LoggerFactory.getLogger(FlightPassengerService.class);

    private int totalAmountFirstClassSeats;
    private int totalAmountBusinessClassSeats;
    private int totalAmountEconomyClassSeats;

    public FlightPassengerService(PlaneCatalog planeCatalog, FlightCatalog flightCatalog, String flightNumber) {
        this.totalAmountFirstClassSeats = planeCatalog.getNumberSeatsFirstClass(
                flightCatalog.getFlightInformation(flightNumber).getPlaneType());
        this.totalAmountBusinessClassSeats = planeCatalog.getNumberSeatsBusinessClass(
                flightCatalog.getFlightInformation(flightNumber).getPlaneType());
        this.totalAmountEconomyClassSeats = planeCatalog.getNumberSeatsEconomyClass(
                flightCatalog.getFlightInformation(flightNumber).getPlaneType());
    }

    public void addPassenger(Passenger passenger) {
        switch (passenger.getType()) {
            case FIRST_CLASS -> decreaseSeatsIfAvailable("First Class", totalAmountFirstClassSeats);
            case BUSINESS_CLASS -> decreaseSeatsIfAvailable("Business Class", totalAmountBusinessClassSeats);
            case ECONOMY_CLASS -> decreaseSeatsIfAvailable("Economy Class", totalAmountEconomyClassSeats);
            default -> logger.warn("Passenger Type not specified");
        }
    }

    private void decreaseSeatsIfAvailable(String seatClass, int availableSeats) {
        if (availableSeats > 0) switch (seatClass) {
            case "First Class" -> totalAmountFirstClassSeats--;
            case "Business Class" -> totalAmountBusinessClassSeats--;
            case "Economy Class" -> totalAmountEconomyClassSeats--;
        }
        else {
            logger.warn("No available seats in {} class.", seatClass);
        }
    }

    public int numberOfFirstClassSeatsAvailable() {
        return totalAmountFirstClassSeats;
    }

    public int numberOfBusinessClassSeatsAvailable() {
        return totalAmountBusinessClassSeats;
    }

    public int numberOfEconomyClassSeatsAvailable() {
        return totalAmountEconomyClassSeats;
    }

    public int numberOfTotalSeatsAvailable() {
        return totalAmountFirstClassSeats + totalAmountBusinessClassSeats + totalAmountEconomyClassSeats;
    }
}