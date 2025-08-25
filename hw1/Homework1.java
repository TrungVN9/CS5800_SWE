package hw1;

public class Homework1 {
    public static void main(String[] args) {
    
        System.out.println("Hello, World!");
    
    }
}

// Employee class
class Employee{
    // Attributes first name, last name and social security number
    String firstName;
    String lastName;
    String ssn;

    // Method to set and get first name
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    // Method to set and get last name
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
}

// SalariedEmployee class
class SalariedEmployee extends Employee{
    // Attribute weekly salary
    double weeklySalary;

    // Method to set and get weekly salary
    public void setWeeklySalary(double weeklySalary){
        this.weeklySalary = weeklySalary;
    }
}

// HourlyEmployee class
class HourlyEmployee extends Employee{
    // Attributes wage and number of hours worked
    double wage;
    double hours;
}

// CommissionEmployee class
class CommissionEmployee extends Employee{
    // Attributes commission rate and gross sales
    double commissionRate;
    double grossSales;
}

// BaseEmployee class
class BaseEmployee extends Employee{
    // Attributes base salary
    double baseSalary;
}
