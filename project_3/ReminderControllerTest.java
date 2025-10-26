import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ReminderControllerTest {
    private ReminderController controller;
    private ReminderService service;
    private InMemoryReminderRepo repo;
    private InMemoryExpenseRepo expRepo;
    private SimpleNotificationSender notifier;

    @BeforeEach
    void setUp() {
        repo = new InMemoryReminderRepo();
        expRepo = new InMemoryExpenseRepo();
        notifier = new SimpleNotificationSender();
        service = new ReminderService(repo, expRepo, notifier);
        controller = new ReminderController(service);
    }

    @Test
    void testCreateReminderSuccess() {
        // BUILD
        String customerId = "cust1";

        // OPERATE
        Response response = controller.createReminder(customerId, "Water Bill", 70, LocalDate.now().plusDays(2), "Monthly payment");

        // CHECK
        assertEquals(201, response.getStatus());
        Reminder reminder = (Reminder) response.getData();
        assertEquals("Water Bill", reminder.getBillName());
    }

    @Test
    void testCreateReminderFailure() {
        // BUILD + OPERATE
        Response response = controller.createReminder("cust1", "", 100, LocalDate.now(), "Missing name");

        // CHECK
        assertEquals(400, response.getStatus());
        assertTrue(((String) response.getData()).contains("Bill name"));
    }

    @Test
    void testMarkAsPaid() {
        // BUILD
        Reminder reminder = service.createReminder("cust1", "Gas Bill", 40, LocalDate.now(), "test");

        // OPERATE
        Response response = controller.markAsPaid(reminder.getId(), true);

        // CHECK
        assertEquals(200, response.getStatus());
        assertEquals("Reminder marked as paid", response.getData());
    }

    @Test
    void testListReminders() {
        // BUILD
        service.createReminder("cust1", "Insurance", 150, LocalDate.now(), "Due soon");

        // OPERATE
        Response response = controller.listReminders();

        // CHECK
        assertEquals(200, response.getStatus());
        assertTrue(((List<?>) response.getData()).size() >= 1);
    }
}