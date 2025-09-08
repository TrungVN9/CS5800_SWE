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

public class Homework2 {
    public static void main(String[] args) {
        // 2 Freelancer objects and 2 Vendor Invoice objects
        Freelancer freelancer1 = new Freelancer("John", "Doe", 50.0, 40);

        Freelancer freelancer2 = new Freelancer("Jane", "Smith", 60.0, 45);

        VendorInvoice vendor1 = new VendorInvoice("ACME Corp", "10001", 500.0);
        
        VendorInvoice vendor2 = new VendorInvoice("Global Supplies", "10002", 750.0);

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
        
        System.out.println("\nTotal Payout: $" + totalPayout);
    }
}