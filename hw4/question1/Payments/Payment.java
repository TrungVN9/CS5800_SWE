package Payments;

import Notifications.Channel;

public abstract class Payment {
    protected Channel channel;

    public Payment(Channel channel) {
        this.channel = channel;
    }
    
    public String toString() {
        return "Payment via " + this.getClass().getSimpleName() + " with " + channel.sendNotification();
    }
}