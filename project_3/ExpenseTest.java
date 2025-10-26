import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    @DisplayName("Should create expense with all required fields")
    void testCreateExpense() {
        // BUILD
        String accountId = "acc123";
        Money amount = new Money(50.99);
        LocalDate date = LocalDate.now().minusDays(1);
        String categoryId = "cat123";
        String note = "Grocery shopping";
        
        // OPERATE
        Expense expense = new Expense(accountId, amount, date, categoryId, note);
        
        // CHECK
        assertNotNull(expense.getExpenseId());
        assertEquals(accountId, expense.getAccountId());
        assertEquals(amount, expense.getAmount());
        assertEquals(date, expense.getDate());
        assertEquals(categoryId, expense.getCategoryId());
        assertEquals(note, expense.getNote());
        assertNotNull(expense.getCreatedAt());
        assertNotNull(expense.getUpdatedAt());
    }

    @Test
    @DisplayName("Should create expense without note")
    void testCreateExpenseWithoutNote() {
        // BUILD
        String accountId = "acc123";
        Money amount = new Money(25.50);
        LocalDate date = LocalDate.now();
        String categoryId = "cat123";
        
        // OPERATE
        Expense expense = new Expense(accountId, amount, date, categoryId, null);
        
        // CHECK
        assertNull(expense.getNote());
    }

    @Test
    @DisplayName("Should throw exception when account ID is null")
    void testNullAccountId() {
        // BUILD
        String nullAccountId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense(nullAccountId, new Money(50), LocalDate.now(), "cat123", "note")
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when account ID is empty")
    void testEmptyAccountId() {
        // BUILD
        String emptyAccountId = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense(emptyAccountId, new Money(50), LocalDate.now(), "cat123", "note")
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when amount is null")
    void testNullAmount() {
        // BUILD
        Money nullAmount = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense("acc123", nullAmount, LocalDate.now(), "cat123", "note")
        );
        assertEquals("Amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when date is null")
    void testNullDate() {
        // BUILD
        LocalDate nullDate = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense("acc123", new Money(50), nullDate, "cat123", "note")
        );
        assertEquals("Date cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when date is in future")
    void testFutureDate() {
        // BUILD
        LocalDate futureDate = LocalDate.now().plusDays(1);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense("acc123", new Money(50), futureDate, "cat123", "note")
        );
        assertEquals("Expense date cannot be in the future", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow today's date")
    void testTodayDate() {
        // BUILD
        LocalDate today = LocalDate.now();
        
        // OPERATE
        Expense expense = new Expense("acc123", new Money(50), today, "cat123", "note");
        
        // CHECK
        assertEquals(today, expense.getDate());
    }

    @Test
    @DisplayName("Should throw exception when category ID is null")
    void testNullCategoryId() {
        // BUILD
        String nullCategoryId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense("acc123", new Money(50), LocalDate.now(), nullCategoryId, "note")
        );
        assertEquals("Category ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when category ID is empty")
    void testEmptyCategoryId() {
        // BUILD
        String emptyCategoryId = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Expense("acc123", new Money(50), LocalDate.now(), emptyCategoryId, "note")
        );
        assertEquals("Category ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should update expense amount")
    void testUpdateAmount() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        Money newAmount = new Money(75.50);
        
        // OPERATE
        expense.update(newAmount, null, null, null);
        
        // CHECK
        assertEquals(newAmount, expense.getAmount());
    }

    @Test
    @DisplayName("Should update expense date")
    void testUpdateDate() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        LocalDate newDate = LocalDate.now().minusDays(5);
        
        // OPERATE
        expense.update(null, newDate, null, null);
        
        // CHECK
        assertEquals(newDate, expense.getDate());
    }

    @Test
    @DisplayName("Should throw exception when updating to future date")
    void testUpdateToFutureDate() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        LocalDate futureDate = LocalDate.now().plusDays(1);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> expense.update(null, futureDate, null, null)
        );
        assertEquals("Expense date cannot be in the future", exception.getMessage());
    }

    @Test
    @DisplayName("Should update category ID")
    void testUpdateCategoryId() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        String newCategoryId = "cat456";
        
        // OPERATE
        expense.update(null, null, newCategoryId, null);
        
        // CHECK
        assertEquals(newCategoryId, expense.getCategoryId());
    }

    @Test
    @DisplayName("Should update note")
    void testUpdateNote() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "old note");
        String newNote = "updated note";
        
        // OPERATE
        expense.update(null, null, null, newNote);
        
        // CHECK
        assertEquals(newNote, expense.getNote());
    }

    @Test
    @DisplayName("Should update all fields at once")
    void testUpdateAllFields() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        Money newAmount = new Money(100);
        LocalDate newDate = LocalDate.now().minusDays(2);
        String newCategoryId = "cat456";
        String newNote = "new note";
        
        // OPERATE
        expense.update(newAmount, newDate, newCategoryId, newNote);
        
        // CHECK
        assertEquals(newAmount, expense.getAmount());
        assertEquals(newDate, expense.getDate());
        assertEquals(newCategoryId, expense.getCategoryId());
        assertEquals(newNote, expense.getNote());
    }

    @Test
    @DisplayName("Should not update when all parameters are null")
    void testUpdateWithAllNulls() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        Money originalAmount = expense.getAmount();
        LocalDate originalDate = expense.getDate();
        String originalCategoryId = expense.getCategoryId();
        String originalNote = expense.getNote();
        
        // OPERATE
        expense.update(null, null, null, null);
        
        // CHECK
        assertEquals(originalAmount, expense.getAmount());
        assertEquals(originalDate, expense.getDate());
        assertEquals(originalCategoryId, expense.getCategoryId());
        assertEquals(originalNote, expense.getNote());
    }

    @Test
    @DisplayName("Should return true when expense is in date range")
    void testIsInDateRange() {
        // BUILD
        LocalDate expenseDate = LocalDate.of(2024, 6, 15);
        Expense expense = new Expense("acc123", new Money(50), expenseDate, "cat123", "note");
        DateRange dateRange = new DateRange(
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 30)
        );
        
        // OPERATE & CHECK
        assertTrue(expense.isInDateRange(dateRange));
    }

    @Test
    @DisplayName("Should return false when expense is outside date range")
    void testIsNotInDateRange() {
        // BUILD
        LocalDate expenseDate = LocalDate.of(2024, 7, 15);
        Expense expense = new Expense("acc123", new Money(50), expenseDate, "cat123", "note");
        DateRange dateRange = new DateRange(
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 30)
        );
        
        // OPERATE & CHECK
        assertFalse(expense.isInDateRange(dateRange));
    }

    @Test
    @DisplayName("Should throw exception when checking null date range")
    void testIsInDateRangeWithNull() {
        // BUILD
        Expense expense = new Expense("acc123", new Money(50), LocalDate.now(), "cat123", "note");
        DateRange nullRange = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> expense.isInDateRange(nullRange)
        );
        assertEquals("Date range cannot be null", exception.getMessage());
    }
}
