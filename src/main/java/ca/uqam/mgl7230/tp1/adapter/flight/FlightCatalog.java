package ca.uqam.mgl7230.tp1.adapter.flight;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;

public interface FlightCatalog {

    FlightInformation getFlightInformation(String flightNumber);
}
