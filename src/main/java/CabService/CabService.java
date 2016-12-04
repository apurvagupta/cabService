package cabService;

import java.util.List;

public class CabService {

    private Cabs cabs;

    public CabService(Cabs cabs) {
        this.cabs = cabs;
    }

    public List<Cab> searchCabs(){
        return this.cabs.freeCabs();
    }

    public Cab assignCab(Customer customer) throws Exception {
        Cab shortDistanceCab = findShortDistance(customer, this.cabs.freeCabs());
        if(customer.isPinkChoice())
            shortDistanceCab = findShortDistance(customer, this.cabs.pinkAndFreeCabs());

        shortDistanceCab.setAvailability(Availability.BUSY);
        return shortDistanceCab;
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

    public Double priceCalculator(Ride ride) {
        return new PriceCalculator().priceForRide(ride);
    }
}
