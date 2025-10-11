package hw4.Payments;

import hw4.Notifications.Channel;

public class OnlinePaymentNotification extends Payment {
    private PaymentMethod paymentMethod;

    public OnlinePaymentNotification(Channel channel, PaymentMethod paymentMethod) {
        super(channel);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void pay(double amount) {
        paymentMethod.processPayment(amount);
        channel.sendNotification("Online payment of $" + amount + " processed successfully via " + paymentMethod.getClass().getSimpleName());
    }
}
