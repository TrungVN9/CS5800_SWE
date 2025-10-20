import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ExpenseTest {

    private Expense expense;
    private Account account;

    @BeforeEach
    void setUp() {
        expense = new Expense();
        account = new Account();
    }

    @Test
    @DisplayName("Should create expense successfully with valid data")
    void createExpense_WithValidData_ShouldCreateSuccessfully() {
        // BUILD
        String description = "Dinner";
        double amount = 60.0;
        String date = "2024-03-15";

        // OPERATE
        Expense createdExpense = account.createExpense(description, amount, date);

        // CHECK
        assertNotNull(createdExpense, "Expense should not be null");
        assertEquals(description, createdExpense.getDescription(), "Description should match input");
        assertEquals(amount, createdExpense.getAmount(), "Amount should match input");
        assertEquals(date, createdExpense.getDate(), "Date should match input");
    }

    @Test
    @DisplayName("Should throw ValidationException when amount is negative")
    void createExpense_WithNegativeAmount_ShouldThrowValidationException() {
        // BUILD
        String description = "Dinner";
        double invalidAmount = -60.0;
        String date = "2024-03-15";

        // OPERATE & CHECK
        ValidationException exception = assertThrows(ValidationException.class,
                () -> account.createExpense(description, invalidAmount, date),
                "Expected ValidationException for negative amount");
        assertTrue(exception.getMessage().contains("amount"), "Exception message should mention amount");
    }

    @Test
    @DisplayName("Should throw ValidationException when date is in the future")
    void createExpense_WithFutureDate_ShouldThrowValidationException() {
        // BUILD
        String description = "Dinner";
        double amount = 60.0;
        String futureDate = "2026-03-15";

        // OPERATE & CHECK
        ValidationException exception = assertThrows(ValidationException.class,
                () -> account.createExpense(description, amount, futureDate),
                "Expected ValidationException for future date");
        assertTrue(exception.getMessage().contains("future"), "Exception message should mention future date");
    }

    @Test
    @DisplayName("Should update existing expense successfully with valid data")
    void editExpense_WithValidData_ShouldUpdateSuccessfully() {
        // BUILD
        Expense existingExpense = account.createExpense("Lunch", 50.0, "2025-06-10");
        String newDescription = "Lunch with friends";
        double newAmount = 75.0;
        String newDate = "2025-06-11";

        // OPERATE
        Expense updatedExpense = account.editExpense(newDescription, newAmount, newDate);

        // CHECK
        assertNotNull(updatedExpense, "Updated expense should not be null");
        assertEquals(newDescription, updatedExpense.getDescription());
        assertEquals(newAmount, updatedExpense.getAmount());
        assertEquals(newDate, updatedExpense.getDate());
    }

    @Test
    @DisplayName("Should delete existing expense successfully")
    void deleteExpense_ExistingExpense_ShouldDeleteSuccessfully() {
        // BUILD
        Expense existingExpense = account.createExpense("Lunch", 100.0, "2025-06-10");

        // OPERATE
        boolean isDeleted = account.deleteExpense(existingExpense);

        // CHECK
        assertTrue(isDeleted, "Expense should be deleted successfully");
        assertFalse(account.getExpenses().contains(existingExpense),
                "Deleted expense should not remain in the account list");
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting nonexistent expense")
    void deleteExpense_NonexistentExpense_ShouldThrowNotFoundException() {
        // BUILD
        Expense nonExistentExpense = new Expense("Breakfast", 50.0, "2025-06-10");

        // OPERATE & CHECK
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> account.deleteExpense(nonExistentExpense),
                "Expected NotFoundException for nonexistent expense");
        assertTrue(exception.getMessage().contains("not found"), "Exception message should mention 'not found'");
    }

    // --- Helper method for DRY principle ---
    private Expense createValidExpense() {
        return account.createExpense("Groceries", 100.0, "2025-03-01");
    }
}
