package ca.uqam.mgl7230.tp1.model.flight;

import ca.uqam.mgl7230.tp1.model.plane.PlaneType;

public class FlightInformation {

    private final String flightNumber;
    private final Double latSource;
    private final Double lonSource;
    private final Double latDestination;
    private final Double lonDestination;
    private final PlaneType planeType;

    public FlightInformation(String flightNumber, Double latSource, Double lonSource,
                             Double latDestination, Double lonDestination, PlaneType planeType) {
        if (flightNumber == null || flightNumber.isEmpty()) {
            throw new IllegalArgumentException("Flight number cannot be null or empty");
        }
        if (latSource == null || lonSource == null || latDestination == null || lonDestination == null) {
            throw new IllegalArgumentException("Latitude and Longitude cannot be null");
        }

        this.flightNumber = flightNumber;
        this.latSource = latSource;
        this.lonSource = lonSource;
        this.latDestination = latDestination;
        this.lonDestination = lonDestination;
        this.planeType = planeType;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Double getLatSource() {
        return latSource;
    }

    public Double getLonSource() {
        return lonSource;
    }

    public Double getLatDestination() {
        return latDestination;
    }

    public Double getLonDestination() {
        return lonDestination;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    @Override
    public String toString() {
        return "FlightInformation{" +
                "flightNumber='" + flightNumber + '\'' +
                ", latSource=" + latSource +
                ", lonSource=" + lonSource +
                ", latDestination=" + latDestination +
                ", lonDestination=" + lonDestination +
                ", planeType=" + planeType +
                '}';
    }

    public void setLatSource(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLatSource'");
    }

    public void setLonSource(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLonSource'");
    }

    public void setLatDestination(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLatDestination'");
    }

    public void setLonDestination(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLonDestination'");
    }
}
