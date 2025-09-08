package hw1.question3;

public class Homework3 {
    // Driver
    public static void main(String[] args) {
        // Demonstrate the classes has a static array of Ship size of 3
        Ship.ships[0] = new Ship("ABC", "1999");

        Ship.ships[1] = new CargoShip("XYZ", "2005", 5000);

        Ship.ships[2] = new CruiseShip("MNO", "2010", 300);
        
        //  Print details of each ship in the array
        for (Ship ship : Ship.ships) {
            ship.print();
            System.out.println(); // Blank line
        }
    }
}
