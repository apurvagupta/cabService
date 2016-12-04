package cabService;

import java.util.Date;

public class Driver {
    private Ride ride;

    public void startRide(Ride ride){
        this.ride = ride;
    }

    public Double endRide(Cab cabAssigned, CabService cabService, Location endLocation){
        cabAssigned.setAvailability(Availability.FREE);
        ride.setEndTime(new Date().getTime());
        ride.setEndLocation(endLocation);
        return cabService.priceCalculator(ride);
    }

}
