package ca.uqam.mgl7230.tp1.utils;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceCalculatorTest {

    private DistanceCalculator distanceCalculator;

    @BeforeEach
    void setUp() {
        distanceCalculator = new DistanceCalculator();
    }

    @Test
    void shouldCalculateDistanceCorrectly() {
        FlightInformation flight = new FlightInformation(
                "Flight-101",
                48.8566, 2.3522,
                45.5017, -73.5673,
                PlaneType.BOEING_737
        );

        int distance = distanceCalculator.calculate(flight);
        assertEquals(5525, distance, 100);
    }

    @Test
    void shouldReturnZeroWhenSameLocation() {

        FlightInformation flight = new FlightInformation(
                "Flight-102",
                40.7128, -74.0060,
                40.7128, -74.0060,
                PlaneType.AIRBUS_A320
        );

        int distance = distanceCalculator.calculate(flight);

        assertEquals(0, distance);
    }

    @Test
    void shouldThrowExceptionWhenFlightInformationIsNull() {
        assertThrows(NullPointerException.class, () -> distanceCalculator.calculate(null));
    }
}
