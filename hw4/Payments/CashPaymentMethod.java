package hw4.Payments;
// Create Bridge Implementation --- CashPayment ---
public class CashPaymentMethod implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
