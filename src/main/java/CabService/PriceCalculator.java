package cabService;


public class PriceCalculator {

    private int PRICE_FOR_TIME =1;
    private int PRICE_FOR_KM =2;

    public Double priceForRide(Ride ride){
        long extraCharge = 0;
        if(ride.getCarType().equals(CarType.PINK)) extraCharge = 5;
        return  Math.floor(ride.getEndTime() - ride.getStartTime() * PRICE_FOR_TIME + ride.getDistance()*PRICE_FOR_KM +extraCharge);
    }
}
