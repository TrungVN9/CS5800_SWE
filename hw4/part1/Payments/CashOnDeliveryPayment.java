package hw4.part1.Payments;

import hw4.part1.Notifications.Channel;

public class CashOnDeliveryPayment extends Payment {
    private PaymentMethod paymentMethod;

    public CashOnDeliveryPayment(Channel channel, PaymentMethod paymentMethod) {
        super(channel);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void pay(double amount) {
        paymentMethod.processPayment(amount);
        channel.sendNotification("Cash payment of $" + amount + " processed successfully via " + paymentMethod.getClass().getSimpleName());
    }

}
