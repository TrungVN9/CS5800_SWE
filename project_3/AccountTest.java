import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    // --- Helper Methods ---
    private Expense createExpense(String name, double amount) {
        return new Expense(name, amount);
    }

    private Budget createBudget(String name, double limit) {
        return new Budget(name, limit);
    }

    private Goal createGoal(String name, double target) {
        return new Goal(name, target);
    }

    // --- Expense Tests ---
    @Test
    void getExpenses_ShouldReturnAllExpenses() {
        Expense e1 = createExpense("Groceries", 150.0);
        Expense e2 = createExpense("Utilities", 75.0);
        account.addExpense(e1);
        account.addExpense(e2);

        List<Expense> result = account.getExpenses();

        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertTrue(result.containsAll(List.of(e1, e2))),
            () -> assertThrows(UnsupportedOperationException.class,
                () -> result.add(new Expense("Hack", 10.0)),
                "Expense list should be unmodifiable per UML encapsulation")
        );
    }

    @Test
    void getExpenses_WithDateRange_ShouldFilterCorrectly() {
        Expense jan = new Expense("Groceries", 150.0, "2024-01-10");
        Expense feb = new Expense("Utilities", 75.0, "2024-02-15");
        account.addExpense(jan);
        account.addExpense(feb);

        List<Expense> filtered = account.getExpenses("2024-01-01", "2024-01-31");

        assertAll(
            () -> assertEquals(1, filtered.size()),
            () -> assertTrue(filtered.contains(jan)),
            () -> assertFalse(filtered.contains(feb))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -25.0, -100.5})
    void addExpense_InvalidAmount_ShouldThrow(double invalidAmount) {
        Expense invalid = new Expense("Invalid", invalidAmount);
        assertThrows(IllegalArgumentException.class, () -> account.addExpense(invalid));
    }

    @Test
    void testAddExpense_NullExpense_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> account.addExpense(null));
    }

    // --- Budget Tests ---
    @Test
    void getBudgets_ShouldReturnAllBudgets() {
        Budget b1 = createBudget("Groceries", 500.0);
        Budget b2 = createBudget("Utilities", 200.0);
        account.addBudget(b1);
        account.addBudget(b2);

        List<Budget> result = account.getBudgets();

        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertTrue(result.containsAll(List.of(b1, b2)))
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    void addBudget_InvalidLimit_ShouldThrow(double invalidLimit) {
        Budget invalid = new Budget("InvalidBudget", invalidLimit);
        assertThrows(IllegalArgumentException.class, () -> account.addBudget(invalid));
    }

    // --- Goal Tests ---
    @Test
    void getGoals_ShouldReturnAllGoals() {
        Goal g1 = createGoal("Vacation", 2000.0);
        Goal g2 = createGoal("Emergency", 5000.0);
        account.addGoal(g1);
        account.addGoal(g2);

        List<Goal> result = account.getGoals();

        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertTrue(result.containsAll(List.of(g1, g2)))
        );
    }

    @Test
    void addGoal_NullGoal_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> account.addGoal(null));
    }

    // --- Totals and Edge Cases ---
    @Test
    void calculateTotals_ShouldReturnCorrectSum() {
        account.addExpense(createExpense("Groceries", 150.0));
        account.addExpense(createExpense("Utilities", 75.0));

        double total = account.calculateTotals();
        assertEquals(225.0, total, 0.01);
    }

    @Test
    void calculateTotals_EmptyAccount_ShouldReturnZero() {
        assertEquals(0.0, account.calculateTotals());
    }

    @Test
    void calculateTotals_WithNegativeExpense_ShouldIgnoreInvalid() {
        Expense invalid = createExpense("Refund", -50.0);
        account.addExpense(createExpense("Groceries", 150.0));
        account.addExpense(invalid);

        double total = account.calculateTotals();
        assertEquals(150.0, total, "Negative expenses should be excluded from totals");
    }

    // --- Integrity Tests ---
    @Test
    void expenses_AreStoredIndependentlyFromInputList() {
        Expense exp = createExpense("Coffee", 5.0);
        account.addExpense(exp);
        List<Expense> result = account.getExpenses();

        exp.setAmount(999.0);
        assertNotEquals(999.0, result.get(0).getAmount(),
            "Account should store a copy, not a reference to mutable object");
    }
}
