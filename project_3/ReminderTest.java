import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReminderTest {
    @Test
    @DisplayName("Should create reminder with all required fields")
    void testCreateReminder() {
        // BUILD
        String customerId = "cust123";
        String billName = "Electricity Bill";
        Money amount = new Money(150);
        LocalDate dueDate = LocalDate.now().plusDays(7);
        String note = "Monthly utility payment";

        // OPERATE
        Reminder reminder = new Reminder(customerId, billName, amount, dueDate, note);

        // CHECK
        assertNotNull(reminder.getReminderId());
        assertEquals(customerId, reminder.getCustomerId());
        assertEquals(billName, reminder.getBillName());
        assertEquals(amount, reminder.getAmount());
        assertEquals(dueDate, reminder.getDueDate());
        assertEquals(note, reminder.getNote());
        assertEquals(ReminderStatus.PENDING, reminder.getStatus());
        assertNotNull(reminder.getCreatedAt());
        assertNotNull(reminder.getUpdatedAt());
    }

    @Test
    @DisplayName("Should create reminder without note")
    void testCreateReminderWithoutNote() {
        // BUILD
        String customerId = "cust123";
        String billName = "Water Bill";
        Money amount = new Money(50);
        LocalDate dueDate = LocalDate.now().plusDays(5);

        // OPERATE
        Reminder reminder = new Reminder(customerId, billName, amount, dueDate, null);

        // CHECK
        assertNull(reminder.getNote());
    }

    @Test
    @DisplayName("Should throw exception when customer ID is null")
    void testNullCustomerId() {
        // BUILD
        String nullCustomerId = null;

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder(nullCustomerId, "Bill", new Money(100), LocalDate.now().plusDays(7), "note"));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when customer ID is empty")
    void testEmptyCustomerId() {
        // BUILD
        String emptyCustomerId = "   ";

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder(emptyCustomerId, "Bill", new Money(100), LocalDate.now().plusDays(7), "note"));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when bill name is null")
    void testNullBillName() {
        // BUILD
        String nullBillName = null;

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", nullBillName, new Money(100), LocalDate.now().plusDays(7), "note"));
        assertEquals("Bill name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when bill name is empty")
    void testEmptyBillName() {
        // BUILD
        String emptyBillName = "   ";

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", emptyBillName, new Money(100), LocalDate.now().plusDays(7), "note"));
        assertEquals("Bill name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when amount is null")
    void testNullAmount() {
        // BUILD
        Money nullAmount = null;

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", "Bill", nullAmount, LocalDate.now().plusDays(7), "note"));
        assertEquals("Amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when amount is zero")
    void testZeroAmount() {
        // BUILD
        Money zeroAmount = new Money(0);

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", "Bill", zeroAmount, LocalDate.now().plusDays(7), "note"));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when amount is negative")
    void testNegativeAmount() {
        // BUILD & OPERATE & CHECK
        assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", "Bill", new Money(-100), LocalDate.now().plusDays(7), "note"));
    }

    @Test
    @DisplayName("Should throw exception when due date is null")
    void testNullDueDate() {
        // BUILD
        LocalDate nullDueDate = null;

        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reminder("cust123", "Bill", new Money(100), nullDueDate, "note"));
        assertEquals("Due date cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow past due date during creation")
    void testPastDueDate() {
        // BUILD
        LocalDate pastDate = LocalDate.now().minusDays(1);

        // OPERATE
        Reminder reminder = new Reminder("cust123", "Bill", new Money(100), pastDate, "note");

        // CHECK
        assertEquals(pastDate, reminder.getDueDate());
    }

    @Test
    @DisplayName("Should mark reminder as paid")
    void testMarkAsPaid() {
        // BUILD
        Reminder reminder = new Reminder("cust123", "Bill", new Money(100), LocalDate.now().plusDays(7), "note");

        // OPERATE
        reminder.markAsPaid();

        // CHECK
        assertEquals(ReminderStatus.PAID, reminder.getStatus());
    }

    @Test
    @DisplayName("Should throw exception when marking already paid reminder")
    void testMarkAlreadyPaidReminder() {
        // BUILD
        Reminder reminder = new Reminder("cust123", "Bill", new Money(100), LocalDate.now().plusDays(7), "note");
        reminder.markAsPaid();

        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> reminder.markAsPaid());
        assertEquals("Reminder is already marked as paid", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when marking cancelled reminder as paid")
    void testMarkCancelledReminderAsPaid() {
        // BUILD
        Reminder reminder = new Reminder("cust123", "Bill", new Money(100), LocalDate.now().plusDays(7), "note");
        reminder.cancel();

        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> reminder.markAsPaid());
        assertEquals("Cannot mark cancelled reminder as paid", exception.getMessage());
    }

    @Test
    @DisplayName("Should snooze reminder to new date")
    void testSnoozeReminder() {
        // BUILD
        Reminder reminder = new Reminder("cust123", "Bill", new Money(100), LocalDate.now().plusDays(7), "note");
        LocalDate newDueDate = LocalDate.now().plusDays(14);

        // OPERATE
        reminder.snooze(newDueDate);

        // CHECK
        assertEquals(newDueDate, reminder.getDueDate());
        assertEquals(ReminderStatus.PENDING, reminder.getStatus());
    }
}