package LoyaltyStatues;

public class PlatinumStatus implements LoyaltyStatus{
    @Override
    public String getStatusName() {
        return "Platinum";
    }

    @Override
    public double applyDiscount(double total) {
        //Apply 15% discount
        return total * 0.85;
    }
}
