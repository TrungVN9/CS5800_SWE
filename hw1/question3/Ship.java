package hw1.question3;

public class Ship {
    private String shipName;
    private String yearBuilt;

    // Static array to hold ship objects
    public static Ship[] ships = new Ship[3];
    private static int count = 0;

    // Constructor 
    public Ship(String shipName, String yearBuilt) {
        this.shipName = shipName;
        this.yearBuilt = yearBuilt;

        if (count < ships.length) {
            ships[count++] = this;
        }
        else {
            System.out.println("Ship array is full. Cannot add more ships.");
        }
    }

    // Setter getter for attributes
    public void setShipName(String name) {
        this.shipName = name;
    }

    public String getShipName() {
        return shipName;
    }

    // Setter getter for year Built
    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void print() {
        System.out.println("Ship Name: " + getShipName());
        System.out.println("Ship was built: " + getYearBuilt());
    }
    
    @Override
    public String toString() {
        return "Ship Name: " + shipName + ", Year Built: " + yearBuilt;
    }
}
