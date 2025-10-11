package hw4.Payments;

public class BitcoinPaymentMethod implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Bitcoin payment of $" + amount);
    }

}
