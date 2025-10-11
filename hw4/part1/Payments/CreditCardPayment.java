package hw4.part1.Payments;
// Create Bridge Implementation --- CreditCardPayment ---
public class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }

}
