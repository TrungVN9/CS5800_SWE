package Payments.OnlinePayments;
// Create Bridge Implementation --- PaypalPayment ---
public class PaypalPaymentMethod implements OnlinePaymentMethod {
    @Override
    public String onlinePaymentType() {
        return "Payment via " + this.getClass().getSimpleName();
    }

}
