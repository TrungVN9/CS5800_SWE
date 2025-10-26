import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ReminderServiceTest {

    private ReminderService reminderService;
    private InMemoryReminderRepo reminderRepo;
    private InMemoryExpenseRepo expenseRepo;
    private SimpleNotificationSender notificationSender;

    @BeforeEach
    void setUp() {
        reminderRepo = new InMemoryReminderRepo();
        expenseRepo = new InMemoryExpenseRepo();
        notificationSender = new SimpleNotificationSender();
        reminderService = new ReminderService(reminderRepo, expenseRepo, notificationSender);
    }

    @Test
    void testCreateReminderSuccess() {
        // Build
        String customerId = "cust1";

        // Operate
        Reminder reminder = reminderService.createReminder(customerId, "Electric Bill", 120.5, LocalDate.now().plusDays(3), "Pay soon");

        // Check
        assertNotNull(reminder.getId());
        assertEquals("Electric Bill", reminder.getBillName());
        assertEquals(1, reminderRepo.findAll().size());
    }

    @Test
    void testCreateReminderInvalidAmount() {
        // Build & Operate
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                reminderService.createReminder("cust1", "Water Bill", -10, LocalDate.now(), "Invalid"));

        // Check
        assertEquals("Amount must be positive", ex.getMessage());
    }

    @Test
    void testMarkAsPaidAndCreateExpense() {
        // Build
        Reminder reminder = reminderService.createReminder("cust1", "Phone Bill", 80, LocalDate.now(), "Monthly");
        
        // Operate
        reminderService.markAsPaid(reminder.getId(), true);

        // Check
        Reminder updated = reminderRepo.findById(reminder.getId());
        assertTrue(updated.isPaid());
        assertEquals(1, expenseRepo.expenseLog.size());
    }

    @Test
    void testGetDueReminders() {
        // Build
        reminderService.createReminder("cust1", "Internet", 50, LocalDate.now().minusDays(1), "Past due");
        reminderService.createReminder("cust2", "Rent", 500, LocalDate.now().plusDays(5), "Future");

        // Operate
        List<Reminder> dueReminders = reminderService.getDueReminders(LocalDate.now());

        // Check
        assertEquals(1, dueReminders.size());
        assertEquals("Internet", dueReminders.get(0).getBillName());
    }

    @Test
    void testNotifyDueReminders() {
        // Build
        reminderService.createReminder("cust1", "Car Loan", 300, LocalDate.now(), "Due today");

        // Operate
        reminderService.notifyDueReminders();

        // Check
        assertEquals(1, notificationSender.notifications.size());
        assertTrue(notificationSender.notifications.get(0).contains("Car Loan"));
    }
}