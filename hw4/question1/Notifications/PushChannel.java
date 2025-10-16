package Notifications;
// Create Bridge Implementation --- PushChannel ---
public class PushChannel implements Channel {
    @Override
    public String sendNotification() {
        return "Push Notification";
    }
}
