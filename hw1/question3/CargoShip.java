package hw1.question3;

public class CargoShip extends Ship {
    private int cargoCapacity;

    public CargoShip(String shipName, String yearBuilt, int cargoCapacity) {
        super(shipName, yearBuilt);
        this.cargoCapacity = cargoCapacity;
    }

    // Setter and getter
    public void setCargoCapacity(int capacity) {
        this.cargoCapacity = capacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void print() {
        System.out.println("[Cargo Ship] " + toString());
        System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
    }
}
