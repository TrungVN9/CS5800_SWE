package hw4.Payments;
// Create Bridge Implementation --- CreditCardPayment ---
public class CreditCardPaymentMethod implements OnlinePaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }

}
