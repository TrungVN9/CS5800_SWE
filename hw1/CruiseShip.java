package hw1;

public class CruiseShip extends Ship{
    private int maxPassengers;

    public CruiseShip(String shipName, String yearBuilt, int maxPassengers){
        super(shipName, yearBuilt);
        this.maxPassengers = maxPassengers;
    }
    
    // Setter and getter
    public void setMaxPassengers(int passenger) {
        this.maxPassengers = passenger;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public void print() {
        System.out.println("[Cruise Ship] " + toString());
        System.out.println("Cruise Ship capacity: " + maxPassengers);
    }
}
