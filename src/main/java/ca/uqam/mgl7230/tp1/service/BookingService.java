package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.*;
import java.util.HashMap;
import java.util.Map;

public class BookingService {

    private FlightPassengerService flightPassengerService;
    private PassengerService passengerService;

    public BookingService(FlightPassengerService flightPassengerService,
                          PassengerService passengerService) {
        this.flightPassengerService = flightPassengerService;
        this.passengerService = passengerService;
    }

    public void book(Passenger passenger, FlightInformation flightInformation) {
        Map<PassengerKeyConstants, Object> passengerDataMap = getPassengerKeyConstantsObjectMap(passenger);

        if (flightPassengerService.numberOfTotalSeatsAvailable() == 0) {
            System.out.println("Flight is Full...");
            return;
        }

        PassengerClass passengerClass = passenger.getType();
        if (!tryAddPassenger(passenger, passengerClass, passengerDataMap, flightInformation)) {
            System.out.println("No available seats in any class.");
        } else {
            System.out.println("Passenger added successfully");
        }
    }

    private boolean tryAddPassenger(Passenger passenger, PassengerClass passengerClass,
                                    Map<PassengerKeyConstants, Object> passengerDataMap, FlightInformation flightInformation) {
        if (isClassFull(passengerClass)) {
            passengerClass = switchClass(passengerClass, passengerDataMap);
        }

        if (passengerClass != null) {
            passenger = passengerService.createPassenger(flightInformation, passengerDataMap);
            flightPassengerService.addPassenger(passenger);
            return true;
        }
        return false;
    }

    private PassengerClass switchClass(PassengerClass currentClass, Map<PassengerKeyConstants, Object> passengerDataMap) {
        return switch (currentClass) {
            case FIRST_CLASS -> {
                System.out.println("First Class is Full. Trying Business...");
                passengerDataMap.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.BUSINESS_CLASS);
                yield PassengerClass.BUSINESS_CLASS;
            }
            case BUSINESS_CLASS -> {
                System.out.println("Business Class is Full. Trying Economy...");
                passengerDataMap.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.ECONOMY_CLASS);
                yield PassengerClass.ECONOMY_CLASS;
            }
            case ECONOMY_CLASS -> {
                System.out.println("Economy is Full. Try another type...");
                yield null;
            }
            default -> currentClass;
        };
    }

    private boolean isClassFull(PassengerClass passengerClass) {
        return switch (passengerClass) {
            case FIRST_CLASS -> flightPassengerService.numberOfFirstClassSeatsAvailable() == 0;
            case BUSINESS_CLASS -> flightPassengerService.numberOfBusinessClassSeatsAvailable() == 0;
            case ECONOMY_CLASS -> flightPassengerService.numberOfEconomyClassSeatsAvailable() == 0;
            case BUSINESS -> false;
            case UNKNOWN -> false;
        };
    }

    private Map<PassengerKeyConstants, Object> getPassengerKeyConstantsObjectMap(Passenger passenger) {
        Map<PassengerKeyConstants, Object> passengerDataMap = new HashMap<>();
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_PASSPORT, passenger.getPassport());
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_NAME, passenger.getName());
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_AGE, passenger.getAge());
        return passengerDataMap;
    }
}
