package ca.uqam.mgl7230.tp1.model.plane;

public class PlaneInformation {

    private int numberSeatsFirstClass;
    private int numberSeatsBusinessClass;
    private int numberSeatsEconomyClass;
    private PlaneType planeType;

    public PlaneInformation(PlaneType planeType, int numberSeatsFirstClass, int numberSeatsBusinessClass, int numberSeatsEconomyClass) {
        this.numberSeatsFirstClass = numberSeatsFirstClass;
        this.numberSeatsBusinessClass = numberSeatsBusinessClass;
        this.numberSeatsEconomyClass = numberSeatsEconomyClass;
        this.planeType = planeType;
    }

    public int getNumberSeatsFirstClass() {
        return numberSeatsFirstClass;
    }

    public int getNumberSeatsBusinessClass() {
        return numberSeatsBusinessClass;
    }

    public int getNumberSeatsEconomyClass() {
        return numberSeatsEconomyClass;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }
}
