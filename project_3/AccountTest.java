import Exceptions.Validation.ValidationException;
import Exceptions.NotFound.NotFoundException;
import Exceptions.BusinessLogic.BusinessLogicException;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    // --- Helper Builders ---
    private Expense buildExpense(String name, double amount) {
        return new Expense(name, amount);
    }

    private Budget buildBudget(String name, double limit) {
        return new Budget(name, limit);
    }

    private Goal buildGoal(String name, double target) {
        return new Goal(name, target);
    }

    // -------------------------------------------------------
    //  EXPENSE TESTS
    // -------------------------------------------------------

    @Test
    @DisplayName("Should return all added expenses safely and unmodifiable")
    void getExpenses_ShouldReturnAllExpenses() {
        // BUILD
        Expense e1 = buildExpense("Groceries", 150.0);
        Expense e2 = buildExpense("Utilities", 75.0);
        account.addExpense(e1);
        account.addExpense(e2);

        // OPERATE
        List<Expense> result = account.getExpenses();

        // CHECK
        assertAll(
            () -> assertEquals(2, result.size(), "All expenses should be retrieved"),
            () -> assertTrue(result.containsAll(List.of(e1, e2))),
            () -> assertThrows(UnsupportedOperationException.class,
                () -> result.add(new Expense("Hack", 10.0)),
                "Expense list should be unmodifiable to preserve encapsulation")
        );
    }

    @Test
    @DisplayName("Should filter expenses correctly within given date range")
    void getExpenses_WithDateRange_ShouldFilterCorrectly() {
        // BUILD
        Expense jan = new Expense("Groceries", 150.0, "2024-01-10");
        Expense feb = new Expense("Utilities", 75.0, "2024-02-15");
        account.addExpense(jan);
        account.addExpense(feb);

        // OPERATE
        List<Expense> filtered = account.getExpenses("2024-01-01", "2024-01-31");

        // CHECK
        assertAll(
            () -> assertEquals(1, filtered.size(), "Only one expense should be in range"),
            () -> assertTrue(filtered.contains(jan)),
            () -> assertFalse(filtered.contains(feb))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -25.0, -100.5})
    @DisplayName("Should throw ValidationException when adding expense with invalid amount")
    void addExpense_InvalidAmount_ShouldThrowValidationException(double invalidAmount) {
        // BUILD
        Expense invalid = buildExpense("InvalidExpense", invalidAmount);

        // OPERATE & CHECK
        ValidationException ex = assertThrows(ValidationException.class,
                () -> account.addExpense(invalid),
                "Expected ValidationException for invalid amount");
        assertTrue(ex.getMessage().contains("amount"), "Exception message should indicate amount error");
    }

    @Test
    @DisplayName("Should throw ValidationException when adding null expense")
    void addExpense_Null_ShouldThrowValidationException() {
        // OPERATE & CHECK
        assertThrows(ValidationException.class,
                () -> account.addExpense(null),
                "Adding null expense should raise validation error");
    }

    @Test
    @DisplayName("Should throw NotFoundException when retrieving expense that does not exist")
    void getExpense_Nonexistent_ShouldThrowNotFoundException() {
        // OPERATE & CHECK
        assertThrows(NotFoundException.class,
                () -> account.getExpenseById("EXP-999"),
                "Expected NotFoundException for nonexistent expense ID");
    }

    // -------------------------------------------------------
    //  BUDGET TESTS
    // -------------------------------------------------------

    @Test
    @DisplayName("Should return all budgets correctly")
    void getBudgets_ShouldReturnAllBudgets() {
        // BUILD
        Budget b1 = buildBudget("Groceries", 500.0);
        Budget b2 = buildBudget("Utilities", 200.0);
        account.addBudget(b1);
        account.addBudget(b2);

        // OPERATE
        List<Budget> result = account.getBudgets();

        // CHECK
        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertTrue(result.containsAll(List.of(b1, b2)))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    @DisplayName("Should throw ValidationException when adding budget with invalid limit")
    void addBudget_InvalidLimit_ShouldThrowValidationException(double invalidLimit) {
        // BUILD
        Budget invalid = buildBudget("InvalidBudget", invalidLimit);

        // OPERATE & CHECK
        assertThrows(ValidationException.class,
                () -> account.addBudget(invalid),
                "Invalid budget limit should trigger ValidationException");
    }

    @Test
    @DisplayName("Should throw BusinessLogicException when exceeding total budget cap")
    void addBudget_ExceedTotalBudgetCap_ShouldThrowBusinessLogicException() {
        // BUILD
        account.addBudget(buildBudget("Main", 10_000.0));

        // OPERATE & CHECK
        assertThrows(BusinessLogicException.class,
                () -> account.addBudget(buildBudget("Extra", 15_000.0)),
                "Adding a new budget exceeding account cap should trigger logic exception");
    }

    // -------------------------------------------------------
    //  GOAL TESTS
    // -------------------------------------------------------

    @Test
    @DisplayName("Should return all goals correctly")
    void getGoals_ShouldReturnAllGoals() {
        // BUILD
        Goal g1 = buildGoal("Vacation", 2000.0);
        Goal g2 = buildGoal("Emergency", 5000.0);
        account.addGoal(g1);
        account.addGoal(g2);

        // OPERATE
        List<Goal> result = account.getGoals();

        // CHECK
        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertTrue(result.containsAll(List.of(g1, g2)))
        );
    }

    @Test
    @DisplayName("Should throw ValidationException when adding null goal")
    void addGoal_Null_ShouldThrowValidationException() {
        // OPERATE & CHECK
        assertThrows(ValidationException.class,
                () -> account.addGoal(null),
                "Adding null goal should trigger validation error");
    }

    // -------------------------------------------------------
    //  TOTAL CALCULATIONS & EDGE CASES
    // -------------------------------------------------------

    @Test
    @DisplayName("Should calculate total expenses correctly")
    void calculateTotals_ShouldReturnCorrectSum() {
        // BUILD
        account.addExpense(buildExpense("Groceries", 150.0));
        account.addExpense(buildExpense("Utilities", 75.0));

        // OPERATE
        double total = account.calculateTotals();

        // CHECK
        assertEquals(225.0, total, 0.001);
    }

    @Test
    @DisplayName("Should return zero when account has no expenses")
    void calculateTotals_EmptyAccount_ShouldReturnZero() {
        // OPERATE
        double total = account.calculateTotals();

        // CHECK
        assertEquals(0.0, total);
    }

    @Test
    @DisplayName("Should skip negative expenses in total calculation")
    void calculateTotals_WithNegativeExpense_ShouldIgnoreInvalid() {
        // BUILD
        account.addExpense(buildExpense("Groceries", 150.0));
        Expense invalid = buildExpense("Refund", -50.0);

        // OPERATE
        account.addExpense(invalid);
        double total = account.calculateTotals();

        // CHECK
        assertEquals(150.0, total, "Negative expenses should be excluded");
    }

    // -------------------------------------------------------
    //  INTEGRITY TESTS
    // -------------------------------------------------------

    @Test
    @DisplayName("Should ensure Account stores copies of expenses, not references")
    void expenses_AreStoredIndependentlyFromInputList() {
        // BUILD
        Expense exp = buildExpense("Coffee", 5.0);
        account.addExpense(exp);

        // OPERATE
        List<Expense> result = account.getExpenses();
        exp.setAmount(999.0);

        // CHECK
        assertNotEquals(999.0, result.get(0).getAmount(),
            "Modifying original expense should not affect stored data");
    }
}
