package cabService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Location customerLocation = new Location(0.03, 0.04);
        Location cabLocation = new Location(0.05, 0.04);
        Location cabLocation2 = new Location(0.01, 0.02);
        Location cabLocation3 = new Location(0.00, 0.00);

        Customer customer = new Customer(customerLocation, new CarChoice(CarType.NORMAL));

        Cab cab1 = new Cab(cabLocation, Availability.FREE, CarType.NORMAL);
        Cab cab2 = new Cab(cabLocation2, Availability.FREE, CarType.NORMAL);
        Cab cab3 = new Cab(cabLocation3, Availability.FREE, CarType.NORMAL);
        Cabs cabs = new Cabs(cab1, cab2, cab3);

        CabService cabService = new CabService(cabs);

        try {
            cabService.assignCab(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().write(cabService.searchCabs().toString());
    }
}
