package hw1;

// Derived class SalariedEmployee

public class SalariedEmployee extends Employee {
    // Attribute weekly salary
    private double weeklySalary;

    // Constructor 
    public SalariedEmployee(String firstName, String lastName, String ssn, double weeklySalary){
        super(firstName, lastName, ssn); // call Employee constructor
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");
        }
        
        this.weeklySalary = weeklySalary;
    }

    // Method to set and get weekly salary
    public void setWeeklySalary(double weeklySalary) {
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");
        }
        this.weeklySalary = weeklySalary;
    }
    
    public double getWeeklySalary(){
        return weeklySalary;
    }

}
