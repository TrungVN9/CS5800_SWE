package hw4.Payments;

import hw4.Notifications.Channel;
import hw4.Payments.OnlinePayments.OnlinePaymentMethod;

public class OnlinePaymentNotification extends Payment {
    private final OnlinePaymentMethod paymentMethod;

    public OnlinePaymentNotification(Channel channel, OnlinePaymentMethod paymentMethod) {
        super(channel);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return paymentMethod.onlinePaymentType() + " with " + channel.sendNotification();
    }


}
