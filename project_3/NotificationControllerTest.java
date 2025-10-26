import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationControllerTest {

    private NotificationController controller;
    private NotificationService service;
    private NotificationServiceTest.InMemoryNotificationRepository repo;
    private NotificationServiceTest.InMemoryNotificationSender sender;

    @BeforeEach
    void setUp() {
        repo = new NotificationServiceTest.InMemoryNotificationRepository();
        sender = new NotificationServiceTest.InMemoryNotificationSender();
        service = new NotificationService(repo, sender);
        controller = new NotificationController(service);
    }

    @Test
    void testListNotifications_ShouldReturnRecipientNotifications() {
        // BUILD
        Notification n1 = service.createNotification("userA", "INFO", "Msg1");
        Notification n2 = service.createNotification("userA", "INFO", "Msg2");
        service.createNotification("userB", "INFO", "Msg3");

        // OPERATE
        Response response = controller.listNotifications("userA");

        // CHECK
        assertEquals(200, response.getStatus());
        List<Notification> result = (List<Notification>) response.getBody();
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(n -> n.getRecipientId().equals("userA")));
    }

    @Test
    void testMarkAsRead_ShouldReturnSuccessResponse() {
        // BUILD
        Notification n = service.createNotification("userA", "INFO", "Read test");

        // OPERATE
        Response response = controller.markAsRead(n.getId());

        // CHECK
        assertEquals(200, response.getStatus());
        assertTrue(repo.findById(n.getId()).isRead());
    }

    @Test
    void testArchiveNotification_ShouldReturnSuccessResponse() {
        // BUILD
        Notification n = service.createNotification("userA", "INFO", "Archive test");

        // OPERATE
        Response response = controller.archiveNotification(n.getId());

        // CHECK
        assertEquals(200, response.getStatus());
        assertFalse(repo.exists(n.getId()));
    }
}
