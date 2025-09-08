package hw1;

public class CommissionEmployee extends Employee {
    private double commissionRate;
    private double grossSales;

    public CommissionEmployee(String fn, String ln, String ssn, double commissionRate, double grossSales) {
        super(fn, ln, ssn);
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
    }

    // Setters and getters
    public void setCommissionRate(double commissionRate) {
        if (commissionRate < 0.0) {
            throw new IllegalArgumentException("Commission Rate must be positive");
        }
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
    
    public void setGrossSales(double grossSales) {
        if (grossSales < 0.0) {
            throw new IllegalArgumentException("Gross Sales must be positive");
        }
        this.grossSales = grossSales;
    }

    public double getGrossSales() {
        return grossSales;
    }
}
