package cabService;


public class Customer {

    private Location location;
    private CarChoice choice;


    public Customer(Location location, CarChoice carChoice) {
        this.location = location;
        this.choice = carChoice;
    }

    public Double locationX() {
        return location.getX();
    }

    public Double locationY() {
        return location.getY();
    }

    public boolean isPinkChoice() {
        return choice.isPinkChoice();
    }
}
