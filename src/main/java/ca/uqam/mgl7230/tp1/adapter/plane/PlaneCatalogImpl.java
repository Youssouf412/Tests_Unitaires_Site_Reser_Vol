package ca.uqam.mgl7230.tp1.adapter.plane;

import ca.uqam.mgl7230.tp1.model.plane.PlaneInformation;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;

import java.util.HashMap;
import java.util.Map;

public class PlaneCatalogImpl implements PlaneCatalog {

    private Map<PlaneType, PlaneInformation> planeMap = new HashMap<>();

    public PlaneCatalogImpl() {
        planeMap.put(PlaneType.BOEING, new PlaneInformation(PlaneType.BOEING, 2, 5, 12));
        planeMap.put(PlaneType.AIRBUS, new PlaneInformation(PlaneType.AIRBUS, 1, 1, 9));
        planeMap.put(PlaneType.EMBRAER, new PlaneInformation(PlaneType.EMBRAER, 0, 1, 4));
        planeMap.put(PlaneType.BOMBARDIER, new PlaneInformation(PlaneType.BOMBARDIER, 0, 2, 5));
    }

    @Override
    public int getNumberSeatsFirstClass(PlaneType planeType) {
        return planeMap.get(planeType).getNumberSeatsFirstClass();
    }

    @Override
    public int getNumberSeatsBusinessClass(PlaneType planeType) {
        return planeMap.get(planeType).getNumberSeatsBusinessClass();
    }

    @Override
    public int getNumberSeatsEconomyClass(PlaneType planeType) {
        return planeMap.get(planeType).getNumberSeatsEconomyClass();
    }

    @Override
    public int getNumberOfTotalSeats(PlaneType planeType) {
        return planeMap.get(planeType).getNumberSeatsFirstClass() +
                planeMap.get(planeType).getNumberSeatsBusinessClass() +
                planeMap.get(planeType).getNumberSeatsEconomyClass();
    }
}
