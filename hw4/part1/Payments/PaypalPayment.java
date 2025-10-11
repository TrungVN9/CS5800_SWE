package hw4.part1.Payments;
// Create Bridge Implementation --- PaypalPayment ---
public class PaypalPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }

}
