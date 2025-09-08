package hw1;

public class VendorInvoice implements Payable{
    private String vendorName;
    private String invoiceNumber;
    private double amountDue;

    public VendorInvoice(String vendorName, String invoiceNumber, double amountDue) {
        this.vendorName = vendorName;
        this.invoiceNumber = invoiceNumber;
        this.amountDue = amountDue;
    }

    public void setVendorName(String name) {
        this.vendorName = name;
    }

    public String getVendorName() {
        return vendorName;
    }
    
    public void setInvoiceNumber(String invoice) {
        this.invoiceNumber = invoice;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setAmountDue(double due) {
        if (due < 0) {
            throw new IllegalArgumentException("Amount due must be positive!");
        }
        this.amountDue = due;
    }

    public double getAmountDue() {
        return amountDue;
    }
    
    @Override
    public double calculatePayment() {
        return amountDue > 0 ? amountDue : 0.0;
    }

    @Override
    public String getPayeeName() {
        return vendorName;
    }

    @Override
    public void print() {
        System.out.println("\nVendor Name: " + getPayeeName());
        System.out.println("Invoice Number: " + getInvoiceNumber());
        System.out.println("Amount Due: $" + getAmountDue());
        System.out.println("Total Payment: $" + calculatePayment());
    }
}
