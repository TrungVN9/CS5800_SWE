package hw4.Payments;

import hw4.Notifications.Channel;

public class BitcoinPayment extends Payment {

    public BitcoinPayment(Channel channel) {
        super(channel);
    }

    @Override
    public String toString() {
        return "Payment via Bitcoin with " + channel.sendNotification();
    }

}
