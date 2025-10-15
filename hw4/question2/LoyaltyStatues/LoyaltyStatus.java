package LoyaltyStatues;

public interface LoyaltyStatus {
    String getStatusName();

    double applyDiscount(double total);
}
