package hw4.part1.Notifications;
// Create Bridge Implementation --- SmsChannel ---
public class SmsChannel implements Channel {
    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS] sending: " + message);
    }
}
