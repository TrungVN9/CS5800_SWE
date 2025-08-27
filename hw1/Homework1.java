package hw1;

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

    // Method to set and get social security number
    public void setSsn(String ssn){
        this.ssn = ssn;
    }
    public String getSsn(){
        return ssn;
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
    public double getWeeklySalary(){
        return weeklySalary;
    }

}

// HourlyEmployee class
class HourlyEmployee extends Employee{
    // Attributes wage and number of hours worked
    double wage;
    double workedHours;

    // Method to set and get wage
    public void setWage(double wage){
        this.wage = wage;
    }
    public double getWage(){
        return wage;
    }

    // Method to set and get hours worked
    public void setWorkedHours(double workedHours){
        this.workedHours = workedHours;
    }

    public double getWorkedHours(){
        return workedHours;
    }
}

// CommissionEmployee class
class CommissionEmployee extends Employee{
    // Attributes commission rate and gross sales
    double commissionRate;
    double grossSales;

    // Method to set and get commission rate
    public void setCommissionRate(double commissionRate){
        this.commissionRate = commissionRate;
    }
    public double getCommissionRate(){
        return commissionRate;
    }

    // Method to set and get gross sales
    public void setGrossSales(double grossSales){
        this.grossSales = grossSales;
    }
    public double getGrossSales(){
        return grossSales;
    }
}

// BaseEmployee class
class BaseEmployee extends Employee{
    // Attributes base salary
    double baseSalary;

    // Method to set and get base salary
    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }
    public double getBaseSalary(){
        return baseSalary;
    }
}
