package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.*;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private DistanceCalculator distanceCalculator;

    @InjectMocks
    private PassengerService passengerService;

    private FlightInformation flightInformation;
    private Map<PassengerKeyConstants, Object> passengerData;

    @BeforeEach
    void setUp() {
        // Initialisation des données de test
        flightInformation = mock(FlightInformation.class);
        passengerData = new HashMap<>();
    }

    @Test
    void testCreateFirstClassPassenger() {
        // Given
        passengerData.put(PassengerKeyConstants.PASSENGER_PASSPORT, "AB123456");
        passengerData.put(PassengerKeyConstants.PASSENGER_NAME, "John Doe");
        passengerData.put(PassengerKeyConstants.PASSENGER_AGE, 30);
        passengerData.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.FIRST_CLASS);

        when(distanceCalculator.calculate(flightInformation)).thenReturn((int) 1000.0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        // Then
        assertNotNull(passenger);
        assertTrue(passenger instanceof FirstClassPassenger);
        assertEquals("AB123456", passenger.getPassport());
        assertEquals("John Doe", passenger.getName());
        assertEquals(30, passenger.getAge());
    }

    @Test
    void testCreateBusinessClassPassenger() {
        // Given
        passengerData.put(PassengerKeyConstants.PASSENGER_PASSPORT, "CD654321");
        passengerData.put(PassengerKeyConstants.PASSENGER_NAME, "Jane Smith");
        passengerData.put(PassengerKeyConstants.PASSENGER_AGE, 25);
        passengerData.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.BUSINESS_CLASS);

        when(distanceCalculator.calculate(flightInformation)).thenReturn((int) 1500.0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        // Then
        assertNotNull(passenger);
        assertTrue(passenger instanceof BusinessClassPassenger);
        assertEquals("CD654321", passenger.getPassport());
        assertEquals("Jane Smith", passenger.getName());
        assertEquals(25, passenger.getAge());
    }

    @Test
    void testCreateEconomyClassPassenger() {
        // Given
        passengerData.put(PassengerKeyConstants.PASSENGER_PASSPORT, "EF987654");
        passengerData.put(PassengerKeyConstants.PASSENGER_NAME, "Alice Johnson");
        passengerData.put(PassengerKeyConstants.PASSENGER_AGE, 22);
        passengerData.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.ECONOMY_CLASS);

        when(distanceCalculator.calculate(flightInformation)).thenReturn((int) 2000.0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        // Then
        assertNotNull(passenger);
        assertTrue(passenger instanceof EconomyClassPassenger);
        assertEquals("EF987654", passenger.getPassport());
        assertEquals("Alice Johnson", passenger.getName());
        assertEquals(22, passenger.getAge());
    }

    @Test
    void testCreatePassengerWithInvalidData() {
        // Given
        passengerData.put(PassengerKeyConstants.PASSENGER_PASSPORT, null); // Donnée invalide
        passengerData.put(PassengerKeyConstants.PASSENGER_NAME, "John Doe");
        passengerData.put(PassengerKeyConstants.PASSENGER_AGE, 30);
        passengerData.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.FIRST_CLASS);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        // Then
        assertNull(passenger);
    }

    @Test
    void testCreatePassengerWithInvalidAge() {
        // Given
        passengerData.put(PassengerKeyConstants.PASSENGER_PASSPORT, "AB123456");
        passengerData.put(PassengerKeyConstants.PASSENGER_NAME, "John Doe");
        passengerData.put(PassengerKeyConstants.PASSENGER_AGE, -5); // Âge invalide
        passengerData.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.FIRST_CLASS);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        // Then
        assertNull(passenger);
    }
}