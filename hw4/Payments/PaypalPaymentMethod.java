package hw4.Payments;
// Create Bridge Implementation --- PaypalPayment ---
public class PaypalPaymentMethod implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }

}
