import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Exceptions.BusinessLogic.InsufficientFundsException;
import Exceptions.Validation.ValidationException;

public class ExpenseTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }
    // ============= CREATE EXPENSE ==================
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
        assertThrows(ValidationException.class,
                () -> account.createExpense("Dinner", -50, "2024-03-15"));
    }

    @Test
    @DisplayName("Should throw ValidationException when date is in the future")
    void createExpense_WithFutureDate_ShouldThrowValidationException() {
        assertThrows(ValidationException.class,
                () -> account.createExpense("Lunch", 20, "2026-05-15"));
    }

    @Test
    @DisplayName("Should throw InsufficientFundsException when amount exceeds balance")
    void createExpense_OverBalance_ShouldThrowInsufficientFundsException() {
        assertThrows(InsufficientFundsException.class,
                () -> account.createExpense("Vacation", 2000, "2024-03-15"));
    }
    
    // ========== DELETE EXPENSE =============

    @Test
    @DisplayName("Should delete existing expense successfully")
    void deleteExpense_ExistingExpense_ShouldDeleteSuccessfully() {
        Expense exp = account.createExpense("Groceries", 100, "2024-03-15");
        assertTrue(account.deleteExpense(exp));
    }


    @Test
    @DisplayName("Should throw NotFoundException when deleting nonexistent expense")
    void deleteExpense_NonexistentExpense_ShouldThrowNotFoundException() {
        Expense dummy = new Expense("Fake", 100, "2024-03-15");
        assertThrows(NotFoundException.class,
                () -> account.deleteExpense(dummy));
    }
    
}
