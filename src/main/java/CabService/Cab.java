package cabService;


import java.io.Serializable;

public class Cab implements Serializable {

    private Location location;
    private Availability availability;
    private CarType carType;

    public Cab(Location location, Availability availability, CarType carType) {
        this.location = location;
        this.availability = availability;
        this.carType = carType;
    }

    public CarType getCarType() {
        return carType;
    }

    public Double locationX() {
        return location.getX();
    }

    public Double locationY() {
        return location.getY();
    }

    public Availability getAvailability() {
        return availability;
    }

    public Availability setAvailability(Availability availability) {
        return this.availability = availability;
    }

    @Override
    public String toString(){
        return locationX() +" "+ locationY()+" "+ carType + " " + availability;
    }
}
