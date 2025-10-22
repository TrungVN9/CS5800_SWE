import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.Validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ReminderTest {

    private Reminder reminder;
    private Account account;

    @BeforeEach
    void setUp() {
        // BUILD
        account = new Account();
        reminder = new Reminder("Electricity Bill", 120.0, LocalDate.now().plusDays(5));
    }

    @Test
    @DisplayName("Should create reminder successfully with valid data")
    void createReminder_WithValidData_ShouldCreateSuccessfully() {
        // BUILD
        String description = "Internet Bill";
        double amount = 80.0;
        LocalDate dueDate = LocalDate.now().plusDays(10);

        // OPERATE
        Reminder newReminder = account.createReminder(description, amount, dueDate);

        // CHECK
        assertNotNull(newReminder, "Reminder should be created successfully");
        assertEquals(description, newReminder.getDescription());
        assertEquals(amount, newReminder.getAmount());
        assertEquals(dueDate, newReminder.getDueDate());
        assertFalse(newReminder.isPaid(), "Newly created reminder should not be marked as paid");
    }

    @Test
    @DisplayName("Should throw ValidationException when due date is in the past")
    void createReminder_WithPastDueDate_ShouldThrowValidationException() {
        // BUILD
        String description = "Phone Bill";
        double amount = 50.0;
        LocalDate pastDate = LocalDate.now().minusDays(3);

        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> {
            account.createReminder(description, amount, pastDate);
        }, "Should throw ValidationException when due date is before today");
    }

    @Test
    @DisplayName("Should trigger reminder notification when due date is approaching")
    void sendReminder_DueDateApproaching_ShouldTriggerNotification() {
        // BUILD
        Reminder reminder = new Reminder("Water Bill", 40.0, LocalDate.now().plusDays(2));

        // OPERATE
        String notification = reminder.sendReminder();

        // CHECK
        assertNotNull(notification);
        assertTrue(notification.contains("approaching") || notification.contains("due soon"),
            "Notification should mention that due date is approaching");
    }

    @Test
    @DisplayName("Should mark reminder as paid and create expense in account")
    void markAsPaid_ValidBill_ShouldUpdateStatusAndCreateExpense() {
        // BUILD
        Reminder reminder = account.createReminder("Credit Card Payment", 300.0, LocalDate.now().plusDays(1));

        // OPERATE
        reminder.markAsPaid(account);

        // CHECK
        assertTrue(reminder.isPaid(), "Reminder should be marked as paid after successful payment");
        assertEquals(1, account.getExpenses().size(), "Account should record one expense after marking reminder as paid");
        Expense recordedExpense = account.getExpenses().get(0);
        assertEquals("Credit Card Payment", recordedExpense.getDescription());
        assertEquals(300.0, recordedExpense.getAmount());
    }

    // Optional edge case
    @Test
    @DisplayName("Should handle zero or negative amount reminder validation")
    void createReminder_WithInvalidAmount_ShouldThrowValidationException() {
        // BUILD
        String description = "Invalid Reminder";
        double invalidAmount = -100.0;
        LocalDate dueDate = LocalDate.now().plusDays(5);

        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> {
            account.createReminder(description, invalidAmount, dueDate);
        }, "Should not allow creating reminder with negative or zero amount");
    }
}
