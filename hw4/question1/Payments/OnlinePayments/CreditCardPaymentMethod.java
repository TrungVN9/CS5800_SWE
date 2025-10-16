package Payments.OnlinePayments;
// Create Bridge Implementation --- CreditCardPayment ---
public class CreditCardPaymentMethod implements OnlinePaymentMethod {
    @Override
    public String onlinePaymentType() {
        return "Payment via " + this.getClass().getSimpleName();
    }

}
