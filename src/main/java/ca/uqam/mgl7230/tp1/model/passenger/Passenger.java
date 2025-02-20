package ca.uqam.mgl7230.tp1.model.passenger;

public abstract class Passenger {

    private String passport;
    private String name;
    private int age;
    private PassengerClass type;
    private int millagePoints;

    public Passenger(String passport, String name, int age, int millagePoints) {
        this.passport = passport;
        this.name = name;
        this.age = age;
        this.millagePoints = millagePoints;
    }

    public abstract PassengerClass getType();

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMillagePoints() {
        return millagePoints;
    }
}
