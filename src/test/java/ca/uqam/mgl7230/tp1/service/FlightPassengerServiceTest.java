package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightPassengerServiceTest {

    @Mock
    private PlaneCatalog planeCatalog;

    @Mock
    private FlightCatalog flightCatalog;

    @InjectMocks
    private FlightPassengerService flightPassengerService;

    private String flightNumber = "FL123";
    private FlightInformation flightInformation;

    @BeforeEach
    void setUp() {
        // Configuration des mocks
        flightInformation = mock(FlightInformation.class);
        when(flightCatalog.getFlightInformation(flightNumber)).thenReturn(flightInformation);
        when(flightInformation.getPlaneType()).thenReturn(PlaneType.valueOf("Boeing-737"));

        // Initialisation des sièges disponibles
        when(planeCatalog.getNumberSeatsFirstClass(PlaneType.valueOf("Boeing-737"))).thenReturn(10);
        when(planeCatalog.getNumberSeatsBusinessClass(PlaneType.valueOf("Boeing-737"))).thenReturn(20);
        when(planeCatalog.getNumberSeatsEconomyClass(PlaneType.valueOf("Boeing-737"))).thenReturn(100);

        // Initialisation du service avec les mocks configurés
        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, flightNumber);
    }

    @Test
    void testInitializeSeatsCorrectly() {

        // Then
        assertEquals(10, flightPassengerService.numberOfFirstClassSeatsAvailable());
        assertEquals(20, flightPassengerService.numberOfBusinessClassSeatsAvailable());
        assertEquals(100, flightPassengerService.numberOfEconomyClassSeatsAvailable());
        assertEquals(130, flightPassengerService.numberOfTotalSeatsAvailable());
    }

    @Test
    void testAddFirstClassPassengerSuccessfully() {
        // Given
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(PassengerClass.FIRST_CLASS);

        // When
        flightPassengerService.addPassenger(passenger);

        // Then
        assertEquals(9, flightPassengerService.numberOfFirstClassSeatsAvailable());
        assertEquals(20, flightPassengerService.numberOfBusinessClassSeatsAvailable());
        assertEquals(100, flightPassengerService.numberOfEconomyClassSeatsAvailable());
    }

    @Test
    void testAddBusinessClassPassengerSuccessfully() {
        // Given
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(PassengerClass.BUSINESS_CLASS);

        // When
        flightPassengerService.addPassenger(passenger);

        // Then
        assertEquals(10, flightPassengerService.numberOfFirstClassSeatsAvailable());
        assertEquals(19, flightPassengerService.numberOfBusinessClassSeatsAvailable());
        assertEquals(100, flightPassengerService.numberOfEconomyClassSeatsAvailable());
    }

    @Test
    void testAddEconomyClassPassengerSuccessfully() {
        // Given
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(PassengerClass.ECONOMY_CLASS);

        // When
        flightPassengerService.addPassenger(passenger);

        // Then
        assertEquals(10, flightPassengerService.numberOfFirstClassSeatsAvailable());
        assertEquals(20, flightPassengerService.numberOfBusinessClassSeatsAvailable());
        assertEquals(99, flightPassengerService.numberOfEconomyClassSeatsAvailable());
    }

    @Test
    void testAddPassengerWhenNoSeatsAvailable() {
        // Given
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(PassengerClass.FIRST_CLASS);

        // Set available seats to 0
        when(planeCatalog.getNumberSeatsFirstClass(PlaneType.valueOf("Boeing-737"))).thenReturn(0);
        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, flightNumber);

        // When
        flightPassengerService.addPassenger(passenger);

        // Then
        assertEquals(0, flightPassengerService.numberOfFirstClassSeatsAvailable());
    }

    @Test
    void testAddPassengerWithInvalidType() {
        // Given
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(null); // Type invalide

        // When
        flightPassengerService.addPassenger(passenger);

        // Then
        assertEquals(10, flightPassengerService.numberOfFirstClassSeatsAvailable());
        assertEquals(20, flightPassengerService.numberOfBusinessClassSeatsAvailable());
        assertEquals(100, flightPassengerService.numberOfEconomyClassSeatsAvailable());
    }
}

