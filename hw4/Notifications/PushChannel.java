package hw4.Notifications;
// Create Bridge Implementation --- PushChannel ---
public class PushChannel implements Channel {
    @Override
    public void sendNotification(String message) {
        System.out.println("[Push Notification] sending: " + message);
    }
}
