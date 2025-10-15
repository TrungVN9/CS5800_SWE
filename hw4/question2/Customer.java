import LoyaltyStatues.LoyaltyStatus;

public class Customer {
    private String customerName;
    private LoyaltyStatus loyaltyStatus;

    public Customer(String customerName, LoyaltyStatus loyaltyStatus) {
        this.customerName = customerName;
        this.loyaltyStatus = loyaltyStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(LoyaltyStatus loyaltyStatus){
        this.loyaltyStatus = loyaltyStatus;
    }
}
