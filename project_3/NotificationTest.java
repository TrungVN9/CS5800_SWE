import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {
    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification("N1", "U1", NotificationType.ALERT, "Payment due soon", new Date(), NotificationStatus.UNREAD);
    }

    @Test
    void testMarkAsRead_ValidFlow() {

        // OPERATE
        notification.markAsRead();
        // CHECK
        assertEquals(NotificationStatus.READ, notification.getStatus());
    }

    @Test
    void testMarkAsRead_AlreadyRead() {
        notification.markAsRead();
        Exception ex = assertThrows(IllegalStateException.class, () -> notification.markAsRead());
        assertEquals("Notification already marked as READ.", ex.getMessage());
    }

    @Test
    void testArchive_ValidFlow() {
        // OPERATE
        notification.archive();
        // CHECK
        assertEquals(NotificationStatus.ARCHIVED, notification.getStatus());
    }

    @Test
    void testArchive_AlreadyArchived() {
        notification.archive();
        Exception ex = assertThrows(IllegalStateException.class, () -> notification.archive());
        assertEquals("Notification already archived.", ex.getMessage());
    }

    @Test
    void testConstructor_InvalidMessage() {
        Exception ex = assertThrows(IllegalArgumentException.class, 
            () -> new Notification("N2", "U2", NotificationType.INFO, "", new Date(), NotificationStatus.UNREAD));
        assertEquals("Message cannot be empty.", ex.getMessage());
    }
}
