package cabService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Ride {

    private Long startTime;
    private Long endTime;
    private Integer distance;
    private Location startingLocation;
    private Location endLocation;
    private CarType carType;

    public Ride(long startTime, Location startingLocation, CarType carType) {
        this.startTime = startTime;
        this.startingLocation = startingLocation;
        this.carType = carType;
    }

    public Long getStartTime() {
        return MILLISECONDS.toMinutes(startTime);
    }

    public Long getEndTime() {
        return  MILLISECONDS.toMinutes(endTime);
    }

    public Double getDistance() {
        double x = Math.pow(startingLocation.getX() - endLocation.getX(), 2);
        double y = Math.pow(startingLocation.getY() - endLocation.getY(), 2);
        return  Math.sqrt(x + y);
    }

    public CarType getCarType() {
        return carType;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setEndLocation(Location location) {
        this.endLocation = location;
    }
}
