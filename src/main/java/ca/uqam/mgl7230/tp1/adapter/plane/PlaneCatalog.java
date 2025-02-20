package ca.uqam.mgl7230.tp1.adapter.plane;

import ca.uqam.mgl7230.tp1.model.plane.PlaneType;

public interface PlaneCatalog {

    int getNumberSeatsFirstClass(PlaneType planeType);
    int getNumberSeatsBusinessClass(PlaneType planeType);
    int getNumberSeatsEconomyClass(PlaneType planeType);

    int getNumberOfTotalSeats(PlaneType planeType);
}
