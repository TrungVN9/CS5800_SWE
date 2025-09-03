package hw1;

class Ship {
    private String shipName;
    private String yearBuilt;

    // Constructor 
    public Ship() {
        
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
}

class CruiseShip extends Ship {

}

public class Homework3 {
    // Driver
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
