package LoyaltyStatues;

public class GoldStatus implements LoyaltyStatus {
    @Override
    public String getStatusName() {
        return "Gold";
    }

    @Override
    public double applyDiscount(double total) {
        //Apply 10% discount
        return total * 0.9;
    }
}
