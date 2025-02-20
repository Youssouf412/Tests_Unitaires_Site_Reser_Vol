package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private FlightPassengerService flightPassengerService;

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private BookingService bookingService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    private Passenger createTestPassenger(PassengerClass passengerClass) {
        Passenger passenger = mock(Passenger.class);
        when(passenger.getType()).thenReturn(passengerClass);
        when(passenger.getPassport()).thenReturn("PASS123");
        when(passenger.getName()).thenReturn("John Doe");
        when(passenger.getAge()).thenReturn(30);
        return passenger;
    }

    @Test
    void bookWhenFlightIsFull() {
        // Configuration
        when(flightPassengerService.numberOfTotalSeatsAvailable()).thenReturn(0);
        Passenger passenger = mock(Passenger.class);
        FlightInformation flightInfo = mock(FlightInformation.class);

        // Exécution
        bookingService.book(passenger, flightInfo);

        // Vérifications
        assertThat(outContent.toString().trim()).isEqualTo("Flight is Full...");
        verify(flightPassengerService, never()).addPassenger(any());
    }

    @Test
    void bookFirstClassSuccessfully() {
        // Configuration
        when(flightPassengerService.numberOfTotalSeatsAvailable()).thenReturn(10);
        when(flightPassengerService.numberOfFirstClassSeatsAvailable()).thenReturn(5);
        Passenger passenger = createTestPassenger(PassengerClass.FIRST_CLASS);

        when(passengerService.createPassenger(any(), any())).thenReturn(passenger);

        // Exécution
        bookingService.book(passenger, mock(FlightInformation.class));

        // Vérifications
        verify(flightPassengerService).addPassenger(passenger);
        verify(flightPassengerService, never()).numberOfBusinessClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfEconomyClassSeatsAvailable();
    }
}
