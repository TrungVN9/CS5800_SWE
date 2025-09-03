package hw1;
import java.util.ArrayList;

/* 
    Design a Payable interface with methods:
        double calculatePayment() -- returns the amount to be paid this period
        String getPayeeName() -- returns the name of the person or entity being paid
*/

interface Payable{
    double calculatePayment();
    String getPayeeName();
    void print();
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
        double overtime = (hoursWorked - 40) > 0 ? hoursWorked - 40 : 0.0;
        double regularHours = hoursWorked - overtime;

        return (regularHours * hourlyRate) + (overtime * hourlyRate * 1.5);
    }
    @Override 
    public String getPayeeName(){
        return firstName + " " + lastName;
    }

    @Override
    public void print(){
        System.out.println("Freelancer Name: " + getPayeeName());
        System.out.println("Hourly Rate: $" + getHourslyRate());
        System.out.println("Hours Worked: " + getHoursWorked());
        System.out.println("Total Payment: $" + calculatePayment());
    }
}

// Design a Vendorinvoice class that implements Payable
class VendorInvoice implements Payable{
    private String name;
    private String invoiceNumber;
    private double amountDue;

    // Setter/ getter for name
    public void setVendorName(String name){
        this.name = name;
    }
    public String getVendorName(){
        return name;
    }

    //Setter/ getter for invoice number
    public void setInvoice(String invoice){
        this.invoiceNumber = invoice;
    }

    public String getInvoiceNumber(){
        return invoiceNumber;
    }

    //Setter getter for amount due
    public void setAmountDue(double due){
        if (due < 0 ){
            throw new IllegalArgumentException("Amount due must be positive!");
        }
        this.amountDue = due;
    }
    public double getAmountDue(){
        return amountDue;
    }

    @Override
    public double calculatePayment(){
        return amountDue > 0 ? amountDue : 0.0;
    }

    @Override
    public String getPayeeName(){
        return name;
    }

    @Override
    public void print(){
        System.out.println("Vendor Invoice Name: " + getVendorName());
        System.out.println("Invoice Number: " + getInvoiceNumber());
        System.out.println("Payment: $" + calculatePayment());
    }
}

public class Homework2 {
    public static void main(String[] args) {
        // 2 Freelancer objects and 2 Vendor Invoice objects
        Freelancer freelancer1 = new Freelancer();
        freelancer1.setFirstName("John");
        freelancer1.setLastName("Doe");
        freelancer1.setHourlyRate(50.0);
        freelancer1.setHoursWorked(40);

        Freelancer freelancer2 = new Freelancer();
        freelancer2.setFirstName("Jane");
        freelancer2.setLastName("Smith");
        freelancer2.setHourlyRate(60.0);
        freelancer2.setHoursWorked(41);
        
        VendorInvoice vendor1 = new VendorInvoice();
        vendor1.setVendorName("My Company");
        vendor1.setInvoice("10001");
        vendor1.setAmountDue(500.0);

        VendorInvoice vendor2 = new VendorInvoice();
        vendor2.setVendorName("Your Company");
        vendor2.setInvoice("10002");
        vendor2.setAmountDue(750.0);

        //Create an ArrayList<Payable> and populate it with
        ArrayList<Payable> payables = new ArrayList<>();
        payables.add(freelancer1);
        payables.add(freelancer2);
        payables.add(vendor1);
        payables.add(vendor2);
        
        double totalPayout = 0.0;

        // Iterate through the list, calculate and print
        for (Payable p: payables){
            p.print();
            totalPayout += p.calculatePayment();
        }
        
        System.out.println("Total Payout: $" + totalPayout);
    }
}