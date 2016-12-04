package CabService;

public class CarChoice {
    private CarType carType;

    public CarChoice(CarType carType) {
        this.carType = carType;
    }

    public boolean isPinkChoice() {
        return this.carType.equals(CarType.PINK);
    }
}
