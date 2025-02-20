package ca.uqam.mgl7230.tp1.model.passenger;

public class FirstClassPassenger extends Passenger {

    public FirstClassPassenger(String passport, String name, int age, int millagePoints) {
        super(passport, name, age, millagePoints);
    }

    @Override
    public PassengerClass getType() {
        return PassengerClass.FIRST_CLASS;
    }
}
