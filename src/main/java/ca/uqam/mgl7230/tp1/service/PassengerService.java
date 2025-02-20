package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.*;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PassengerService {

    private static final Logger logger = LoggerFactory.getLogger(PassengerService.class);

    private final DistanceCalculator distanceCalculator;

    public PassengerService(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public Passenger createPassenger(FlightInformation flightInformation,
                                     Map<PassengerKeyConstants, Object> passengerData) {
        String passengerPassport = (String) passengerData.get(PassengerKeyConstants.PASSENGER_PASSPORT);
        String passengerName = (String) passengerData.get(PassengerKeyConstants.PASSENGER_NAME);
        int passengerAge = (int) passengerData.get(PassengerKeyConstants.PASSENGER_AGE);

        if (passengerPassport == null || passengerName == null || passengerAge <= 0) {
            logger.error("Invalid passenger data");
            return null;
        }

        switch ((PassengerClass) passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)) {
            case FIRST_CLASS:
                return createFirstClassPassenger(passengerPassport, passengerName, passengerAge, flightInformation);
            case BUSINESS_CLASS:
                return createBusinessClassPassenger(passengerPassport, passengerName, passengerAge, flightInformation);
            case ECONOMY_CLASS:
                return createEconomyClassPassenger(passengerPassport, passengerName, passengerAge, flightInformation);
            default:
                logger.warn("Passenger type not existent, please try again");
                return null;
        }
    }

    private FirstClassPassenger createFirstClassPassenger(String passport, String name, int age, FlightInformation flightInfo) {
        return new FirstClassPassenger(passport, name, age, distanceCalculator.calculate(flightInfo));
    }

    private BusinessClassPassenger createBusinessClassPassenger(String passport, String name, int age, FlightInformation flightInfo) {
        return new BusinessClassPassenger(passport, name, age, distanceCalculator.calculate(flightInfo));
    }

    private EconomyClassPassenger createEconomyClassPassenger(String passport, String name, int age, FlightInformation flightInfo) {
        return new EconomyClassPassenger(passport, name, age, distanceCalculator.calculate(flightInfo));
    }
}