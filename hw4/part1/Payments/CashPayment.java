package hw4.part1.Payments;
// Create Bridge Implementation --- CashPayment ---
public class CashPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
