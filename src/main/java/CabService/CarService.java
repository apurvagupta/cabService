package CabService;

import java.util.Date;
import java.util.List;

public class CarService {

    private Cabs cabs;
    private Ride ride;

    public CarService(Cabs cabs) {
        this.cabs = cabs;
    }

    public Cab assignCab(Customer customer) throws Exception {
        Cab shortDistanceCab = findShortDistance(customer, this.cabs.freeCabs());;
        if(customer.isPinkChoice())
            shortDistanceCab = findShortDistance(customer, this.cabs.pinkAndFreeCabs());

        shortDistanceCab.setAvailability(Availability.BUSY);
        return shortDistanceCab;
    }

    public void releaseCab(Cab cabAssigned){
        cabAssigned.setAvailability(Availability.FREE);
    }

    private Cab findShortDistance(Customer customer, List<Cab> cabs) throws Exception {
        if(cabs.isEmpty())
            throw new Exception("No Cab Available");

        double x = Math.pow(cabs.get(0).locationX() - customer.locationX(), 2);
        double y = Math.pow(cabs.get(0).locationY() - customer.locationY(), 2);
        Double min = Math.sqrt(x + y);

        Cab minCab = cabs.get(0);

        for (Cab cab : cabs) {
            x = Math.pow(cab.locationX() - customer.locationX(), 2);
            y = Math.pow(cab.locationY() - customer.locationY(), 2);
            double distance = Math.sqrt(x + y);
            if(min> distance) minCab = cab;
        }
        return minCab;
    }
    public void startRide(CarType carType){
         this.ride = new Ride(new Date().getTime(), carType);
    }
}
