package ca.uqam.mgl7230.tp1.model.passenger;

public class EconomyClassPassenger extends Passenger {

    public EconomyClassPassenger(String passport, String name, int age, int millagePoints) {
        super(passport, name, age, millagePoints);
    }

    @Override
    public PassengerClass getType() {
        return PassengerClass.ECONOMY_CLASS;
    }
}
