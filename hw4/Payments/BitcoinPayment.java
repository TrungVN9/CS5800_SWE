package hw4.Payments;

import hw4.Notifications;
import hw4.Notifications.Channel;
import hw4.Payments.Payment;
import hw4.Payments.PaymentMethod;

public class BitcoinPayment extends Payment {
    private PaymentMethod paymentMethod;

    public BitcoinPayment(Channel channel, PaymentMethod paymentMethod) {
        super(channel);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void pay(double amount) {
        paymentMethod.processPayment(amount);
        channel.sendNotification("Bitcoin payment of $" + amount + " processed.");
    }

}
