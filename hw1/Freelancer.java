package hw1;

public class Freelancer implements Payable {
    private String firstName;
    private String lastName;
    private double hourlyRate;
    private double hoursWorked;

    public Freelancer(String fn, String ln, double hourlyRate, double hoursWorked) {
        this.firstName = fn;
        this.lastName = ln;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Setters and getters
    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public String getLastName() {
        return lastName;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0.0) {
            throw new IllegalArgumentException("Rate must be positive");
        }
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0.0) {
            throw new IllegalArgumentException("Hours must be positive");
        }
        this.hoursWorked = hoursWorked;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public double calculatePayment() {
        double overtime = hoursWorked - 40 > 0 ? hoursWorked - 40 : 0.0;
        double regularHours = hoursWorked - overtime;

        return hourlyRate * regularHours + (overtime * 1.5 * hourlyRate);
    }

    @Override
    public String getPayeeName() {
        return firstName + " " + lastName;
    }

    @Override
    public void print(){
        System.out.println("\nFreelancer Name: " + getPayeeName());
        System.out.println("Hourly Rate: $" + getHourlyRate());
        System.out.println("Hours Worked: " + getHoursWorked());
        System.out.println("Total Payment: $" + calculatePayment());
    }
}
