package hw4.Payments;

import hw4.Notifications.Channel;

public class CashOnDeliveryPayment extends Payment {

    public CashOnDeliveryPayment(Channel channel) {
        super(channel);
    }

    @Override
    public void pay(double amount) {
        String message = "Cash payment of $" + amount + " processed successfully.";
        channel.sendNotification(message);
    }

}
