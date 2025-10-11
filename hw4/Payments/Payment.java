package hw4.Payments;

import hw4.Notifications.Channel;

public abstract class Payment {
    protected Channel channel;

    public Payment(Channel channel) {
        this.channel = channel;
    }

    public abstract void pay(double amount);
}