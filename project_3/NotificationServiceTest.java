import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private INotificationRepository repo;
    private INotificationSender sender;
    private NotificationService service;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(INotificationRepository.class);
        sender = Mockito.mock(INotificationSender.class);
        service = new NotificationService(repo, sender);
    }

    // BUILD - OPERATE - CHECK

    @Test
    void testCreateNotification_ValidInput() {
        // BUILD
        when(repo.save(any(Notification.class))).thenReturn(null);

        // OPERATE
        Notification n = service.createNotification("U1", NotificationType.ALERT, "Low balance");

        // CHECK
        assertNotNull(n);
        assertEquals("U1", n.getRecipientId());
        assertEquals(NotificationStatus.UNREAD, n.getStatus());
        verify(repo, times(1)).save(any(Notification.class));
    }

    @Test
    void testCreateNotification_InvalidInput() {
        Exception ex = assertThrows(IllegalArgumentException.class, 
            () -> service.createNotification("", NotificationType.INFO, "Message"));
        assertEquals("Recipient ID cannot be empty.", ex.getMessage());
    }

    @Test
    void testSendNotification_Success() {
        Notification mockNotif = new Notification("N1", "U1", NotificationType.INFO, "Test", new Date(), NotificationStatus.UNREAD);
        when(repo.findById("N1")).thenReturn(mockNotif);

        service.sendNotification("N1");

        verify(sender, times(1)).send(mockNotif);
        verify(repo, times(1)).update(mockNotif);
    }

    @Test
    void testSendNotification_NotFound() {
        when(repo.findById("X")).thenReturn(null);
        Exception ex = assertThrows(NoSuchElementException.class, () -> service.sendNotification("X"));
        assertEquals("Notification with ID X not found.", ex.getMessage());
    }

    @Test
    void testGetNotificationsByRecipient() {
        List<Notification> mockList = List.of(
            new Notification("N1", "U1", NotificationType.ALERT, "Msg1", new Date(), NotificationStatus.UNREAD)
        );
        when(repo.findByRecipient("U1")).thenReturn(mockList);

        List<Notification> result = service.getNotificationsByRecipient("U1");

        assertEquals(1, result.size());
        assertEquals("Msg1", result.get(0).getMessage());
    }

    @Test
    void testMarkAsRead_Valid() {
        Notification n = new Notification("N1", "U1", NotificationType.ALERT, "Msg", new Date(), NotificationStatus.UNREAD);
        when(repo.findById("N1")).thenReturn(n);

        service.markAsRead("N1");

        assertEquals(NotificationStatus.READ, n.getStatus());
        verify(repo, times(1)).update(n);
    }

    @Test
    void testMarkAsRead_InvalidId() {
        when(repo.findById("BAD")).thenReturn(null);
        Exception ex = assertThrows(NoSuchElementException.class, () -> service.markAsRead("BAD"));
        assertEquals("Notification with ID BAD not found.", ex.getMessage());
    }

    @Test
    void testArchiveNotification_Valid() {
        Notification n = new Notification("N1", "U1", NotificationType.ALERT, "Msg", new Date(), NotificationStatus.UNREAD);
        when(repo.findById("N1")).thenReturn(n);

        service.archiveNotification("N1");

        assertEquals(NotificationStatus.ARCHIVED, n.getStatus());
        verify(repo, times(1)).update(n);
    }

    @Test
    void testArchiveNotification_AlreadyArchived() {
        Notification n = new Notification("N1", "U1", NotificationType.ALERT, "Msg", new Date(), NotificationStatus.ARCHIVED);
        when(repo.findById("N1")).thenReturn(n);

        Exception ex = assertThrows(IllegalStateException.class, () -> service.archiveNotification("N1"));
        assertEquals("Notification already archived.", ex.getMessage());
    }
}
