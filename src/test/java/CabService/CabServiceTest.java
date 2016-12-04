package cabService;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;


public class CabServiceTest {

    @Rule
    public ExpectedException expectedException= ExpectedException.none();
    @Test
    public void shouldAssignCabToCustomer() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location cabLocation = new Location(0.05, 0.04);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1);

        CabService cabService = new CabService(cabs);
        Cab cab = cabService.assignCab(customer);
        Assert.assertEquals(cab, cab1);

    }

    @Test
    public void shouldNotAssignCabToCustomerIfNoCabAvailable() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location cabLocation = new Location(0.05, 0.04);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.BUSY, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1);

        CabService cabService = new CabService(cabs);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("No Cab Available");

        cabService.assignCab(customer);


    }

    @Test
    public void shouldAssignCabToCustomerWhichIsNearest() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location cabLocation = new Location(0.05, 0.04);
        Location cabLocation2 = new Location(0.01, 0.02);
        Location cabLocation3 = new Location(0.00, 0.00);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.BUSY, CarType.NORMAL);
        Cab cab2 = new Cab(cabLocation2, Availability.FREE, CarType.NORMAL);
        Cab cab3 = new Cab(cabLocation3, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1, cab2, cab3);

        CabService cabService = new CabService(cabs);

        Cab cab = cabService.assignCab(customer);
        Assert.assertEquals(cab, cab2);
    }

    @Test
    public void shouldNotAssignCabToCustomerWhichIsAssignedAlready() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location customerLocation1 = new Location(0.01, 0.02);

        Location cabLocation = new Location(0.05, 0.04);
        Location cabLocation2 = new Location(0.01, 0.02);
        Location cabLocation3 = new Location(0.00, 0.00);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));
        Customer customer1 = new Customer(customerLocation1, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.BUSY, CarType.NORMAL);
        Cab cab2 = new Cab(cabLocation2, Availability.FREE, CarType.NORMAL);
        Cab cab3 = new Cab(cabLocation3, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1, cab2, cab3);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Assert.assertEquals(cabAssigned, cab2);

        Cab cab = cabService.assignCab(customer1);
        Assert.assertNotEquals(cab,cab2);
        Assert.assertEquals(cab,cab3);
    }


    @Test
    public void shouldAssignOnlyPinkCabIfPinkCabIsSelectedOtherwiseNoCab() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location customerLocation1 = new Location(0.01, 0.02);

        Location cabLocation = new Location(0.05, 0.04);
        Location cabLocation2 = new Location(0.01, 0.02);
        Location cabLocation3 = new Location(0.00, 0.00);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));
        Customer customer1 = new Customer(customerLocation1, new CarChoice(CarType.PINK));

        Cab cab1 = new Cab(cabLocation, Availability.BUSY, CarType.PINK);
        Cab cab2 = new Cab(cabLocation2, Availability.FREE, CarType.PINK);
        Cab cab3 = new Cab(cabLocation3, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1, cab2, cab3);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Assert.assertEquals(cabAssigned, cab2);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("No Cab Available");
        cabService.assignCab(customer1);
    }

    @Test
    public void shouldAssignOnlyPinkCabIfPinkCabIsSelected() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location customerLocation1 = new Location(0.01, 0.02);

        Location cabLocation = new Location(0.05, 0.04);
        Location cabLocation2 = new Location(0.01, 0.02);
        Location cabLocation3 = new Location(0.00, 0.00);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));
        Customer customer1 = new Customer(customerLocation1, new CarChoice(CarType.PINK));

        Cab cab1 = new Cab(cabLocation, Availability.BUSY, CarType.PINK);
        Cab cab2 = new Cab(cabLocation2, Availability.FREE, CarType.NORMAL);
        Cab cab3 = new Cab(cabLocation3, Availability.FREE, CarType.PINK);
        Cabs cabs = new Cabs(cab1, cab2, cab3);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Assert.assertEquals(cabAssigned, cab2);

        Cab cab = cabService.assignCab(customer1);
        Assert.assertNotEquals(cab,cab2);
        Assert.assertEquals(cab,cab3);
    }

    @Test
    public void shouldAssignReleasedCabsToCustomer() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);
        Location customerLocation1 = new Location(0.01, 0.02);

        Location cabLocation = new Location(0.05, 0.04);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));
        Customer customer1 = new Customer(customerLocation1, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Driver driver = new Driver();
        driver.startRide(new Ride(new Date().getTime(), customerLocation, cab1.getCarType()));
        Assert.assertEquals(cabAssigned, cab1);

        Location endLocation = new Location(0.10, 0.11);
        driver.endRide(cabAssigned, cabService, endLocation);

        Cab cab = cabService.assignCab(customer1);
        Assert.assertEquals(cab,cab1);
    }

    @Test
    public void shouldCalculatePriceWhenEndRide() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);

        Location cabLocation = new Location(0.05, 0.04);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Driver driver = new Driver();
        driver.startRide(new Ride(new Date().getTime(), customerLocation, cab1.getCarType()));

        Assert.assertEquals(cabAssigned, cab1);

        Location endLocation = new Location(0.50, 0.50);
        Double priceForTrip = driver.endRide(cabAssigned, cabService, endLocation);


        Assert.assertEquals(priceForTrip, new Double(1.0));

    }

    @Test
    public void shouldCalculatePriceWhenRideIsFromPinkCab() throws Exception {
        Location customerLocation = new Location(0.03, 0.04);

        Location cabLocation = new Location(0.05, 0.04);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.FREE, CarType.PINK);
        Cabs cabs = new Cabs(cab1);

        CabService cabService = new CabService(cabs);

        Cab cabAssigned = cabService.assignCab(customer);
        Driver driver = new Driver();
        driver.startRide(new Ride(new Date().getTime(), customerLocation, cab1.getCarType()));

        Assert.assertEquals(cabAssigned, cab1);

        Location endLocation = new Location(0.50, 0.50);
        Double priceForTrip = driver.endRide(cabAssigned, cabService, endLocation);


        Assert.assertEquals(priceForTrip, new Double(6.0));

    }


}
