package hw4.Notifications;
// Create Bridge Implementation --- EmailChannel ---
public class EmailChannel implements Channel {
    @Override
    public String sendNotification() {
        return "Email Notification";
    }
}