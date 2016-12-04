package CabService;

public class Ride {

    private Long startTime;
    private Long endTime;
    private Integer Distance;
    private CarType carType;

    public Ride(long startTime, CarType carType) {
        this.startTime = startTime;
        this.carType = carType;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }
}
