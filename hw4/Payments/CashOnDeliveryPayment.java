package hw4.Payments;

import hw4.Notifications.Channel;

public class CashOnDeliveryPayment extends Payment {

    public CashOnDeliveryPayment(Channel channel) {
        super(channel);
    }

    @Override
    public String toString() {
        return "Payment via Cash On Delivery with " + channel.sendNotification();
    }

}
