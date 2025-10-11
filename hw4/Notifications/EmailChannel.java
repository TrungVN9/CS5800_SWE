package hw4.Notifications;
// Create Bridge Implementation --- EmailChannel ---
public class EmailChannel implements Channel {
    @Override
    public void sendNotification(String message) {
        System.out.println("[Email] sending: " + message);
    }
}