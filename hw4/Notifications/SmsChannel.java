package hw4.Notifications;
// Create Bridge Implementation --- SmsChannel ---
public class SmsChannel implements Channel {
    @Override
    public String sendNotification() {
        return "SMS Notification";
    }
}
