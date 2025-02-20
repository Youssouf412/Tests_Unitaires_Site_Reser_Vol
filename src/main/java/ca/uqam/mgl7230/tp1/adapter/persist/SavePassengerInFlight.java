package ca.uqam.mgl7230.tp1.adapter.persist;

import ca.uqam.mgl7230.tp1.model.passenger.Passenger;

import java.io.FileWriter;
import java.io.IOException;

public class SavePassengerInFlight {

    public void save(FileWriter file, Passenger passenger, String flightNumber) throws IOException {
        file.append(flightNumber)
                .append(",")
                .append(passenger.getPassport())
                .append(",")
                .append(passenger.getName())
                .append(",")
                .append(String.valueOf(passenger.getAge()))
                .append(",")
                .append(String.valueOf(passenger.getType()))
                .append(",")
                .append(String.valueOf(passenger.getMillagePoints()))
                .append("\n");
    }
}
