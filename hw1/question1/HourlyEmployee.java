/**
 *
 * @author trungvong
 */

package hw1.question1;

// Derived class HourlyEmployee
public class HourlyEmployee extends Employee {
    private double wage;
    private double hoursWorked;

    // Constructor
    public HourlyEmployee(String fn, String ln, String ssn, double wage, double hoursWorked) {
        super(fn, ln, ssn);
        this.wage = wage;
        this.hoursWorked = hoursWorked;
    }

    // Setters and getters methods
    public void setWage(double wage) {
        if (wage < 0.0) {
            throw new IllegalArgumentException("Wage must be >= 0.0");
        }
        this.wage = wage;
    }

    public double getWage() {
        return wage;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0.0) {
            throw new IllegalArgumentException("Hours worked must be >= 0.0");
        }
        this.hoursWorked = hoursWorked;
    }
    public double getHoursWorked() {
        return hoursWorked;
    }
}
