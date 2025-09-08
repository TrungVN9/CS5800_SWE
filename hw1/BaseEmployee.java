package hw1;

public class BaseEmployee extends Employee{
    private double baseSalary;

    public BaseEmployee(String fn, String ln, String ssn, double baseSalary) {
        super(fn, ln, ssn);
        this.baseSalary = baseSalary;
    }
    
    // Setter and getter
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base Salary must be positive");
        }
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
