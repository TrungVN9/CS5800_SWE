package hw4.part1.Payments;

import hw4.part1.Notifications.Channel;

public abstract class Payment {
    protected Channel channel;

    public Payment(Channel channel) {
        this.channel = channel;
    }

    public abstract void pay(double amount);
}