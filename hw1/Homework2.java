package hw1;

/* 
    Design a Payable interface with methods:
        double calculatePayment() -- returns the amount to be paid this period
        String getPayeeName() -- returns the name of the person or entity being paid
*/

interface Payable{
    double calculatePayment();
    String getPayeeName();
}

// Design a Freelancer class that implements Payable
class Freelancer implements Payable{
    private String firstName;
    private String lastName;
    private double hourlyRate;
    private double hoursWorked;

    // Setter and getter for firstName
    public void setFirstName(String fn){
        this.firstName = fn;
    }

    public String getFirstName(){
        return firstName;
    }

    // Setter and getter for lastName
    public void setLastName(String ln){
        this.lastName = ln;
    }
    
    public String getLastName(){
        return lastName;
    }

    //Setter and getter for hoursly rate
    public void setHourlyRate(double rate){
        // Validate that hourly rate is positive
        if (rate <= 0){
            throw new IllegalArgumentException("Hourly rate must be positive!");
        }
        this.hourlyRate = rate;
    }
    public double getHourslyRate(){
        return hourlyRate;
    }

    //Setter and getter for hours Worked
    public void setHoursWorked(double hrs){
        // Validate that hrs must be positive
        if (hrs <= 0){
            throw new IllegalArgumentException("Hours work must be positive!");
        }
        this.hoursWorked = hrs;
    }

    public double getHoursWorked(){
        return hoursWorked;
    }

    @Override 
    public double calculatePayment(){
        double overtime = hoursWorked - 40 ? hoursWorked - 40 : 0;
        double regularHours = hoursWorked - overtime;

        return (regularHours * hourlyRate) + (overtime * hourlyRate * 1.5);
    }
    @Override 
    public String getPayeeName(){
        return firstName + " " + lastName;
    }

    public void print(){
        System.out.println("Freelancer Name: " + getPayeeName());
        System.out.println("Hourly Rate: $" + getHourslyRate());
        System.out.println("Hours Worked: " + getHoursWorked());
        System.out.println("Total Payment: $" + calculatePayment());
    }

}