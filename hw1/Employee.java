package hw1;

// Abstract Employee class
public abstract class Employee {
    // Set immutable final attributes first name, last name and social security number 
    private final String firstName;
    private final String lastName;
    private final String ssn;

    // Constructor
    public Employee(String firstName, String lastName, String ssn) {
        if (firstName == null || lastName == null || ssn == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    // No need to have setters as defined in the constructor they are final
    // This is just to meet the requirements of the assignment
    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    // public void setSsn(String ssn){
    //     this.ssn = ssn;
    // }

    // // Method to set and get last name
    // public void setLastName(String lastName){
    //     this.lastName = lastName;
    // }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Method to set and get social security number
    public String getSsn() {
        return ssn;
    }
}

// HourlyEmployee class
// class HourlyEmployee extends Employee{
//     // Attributes wage and number of hours worked
//     double wage;
//     double workedHours;

//     // Method to set and get wage
//     public void setWage(double wage){
//         this.wage = wage;
//     }
//     public double getWage(){
//         return wage;
//     }

//     // Method to set and get hours worked
//     public void setWorkedHours(double workedHours){
//         this.workedHours = workedHours;
//     }

//     public double getWorkedHours(){
//         return workedHours;
//     }
// }

// // CommissionEmployee class
// class CommissionEmployee extends Employee{
//     // Attributes commission rate and gross sales
//     double commissionRate;
//     double grossSales;

//     // Method to set and get commission rate
//     public void setCommissionRate(double commissionRate){
//         this.commissionRate = commissionRate;
//     }
//     public double getCommissionRate(){
//         return commissionRate;
//     }

//     // Method to set and get gross sales
//     public void setGrossSales(double grossSales){
//         this.grossSales = grossSales;
//     }
//     public double getGrossSales(){
//         return grossSales;
//     }
// }

// // BaseEmployee class
// class BaseEmployee extends Employee{
//     // Attributes base salary
//     double baseSalary;

//     // Method to set and get base salary
//     public void setBaseSalary(double baseSalary){
//         this.baseSalary = baseSalary;
//     }
//     public double getBaseSalary(){
//         return baseSalary;
//     }
// }
