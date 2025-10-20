import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BudgetTest {

    private Budget budget;

    @BeforeEach
    void setUp() {
        // Assume Budget constructor accepts a total limit and an alert threshold percentage (e.g., 80%)
        budget = new Budget(1000.0, 0.8);
    }

    @Test
    @DisplayName("Should return positive remaining amount when within budget")
    void calculateRemaining_WithinBudget_ShouldReturnPositiveAmount() {
        // BUILD
        double totalSpent = 600.0;

        // OPERATE
        double remaining = budget.calculateRemaining(totalSpent);

        // CHECK
        assertTrue(remaining > 0, "Remaining amount should be positive when under budget");
        assertEquals(400.0, remaining, 0.001, "Remaining amount should equal 1000 - 600 = 400");
    }

    @Test
    @DisplayName("Should return negative remaining amount when over budget")
    void calculateRemaining_OverBudget_ShouldReturnNegativeAmount() {
        // BUILD
        double totalSpent = 1200.0;

        // OPERATE
        double remaining = budget.calculateRemaining(totalSpent);

        // CHECK
        assertTrue(remaining < 0, "Remaining amount should be negative when over budget");
        assertEquals(-200.0, remaining, 0.001, "Remaining amount should equal 1000 - 1200 = -200");
    }

    @Test
    @DisplayName("Should not trigger alert when spending is below threshold")
    void checkThreshold_UnderThreshold_ShouldNotTriggerAlert() {
        // BUILD
        double totalSpent = 700.0; // 70% of 1000

        // OPERATE
        boolean alert = budget.checkThreshold(totalSpent);

        // CHECK
        assertFalse(alert, "Alert should not trigger when spending is below 80% threshold");
    }

    @Test
    @DisplayName("Should trigger alert when spending exceeds threshold")
    void checkThreshold_OverThreshold_ShouldTriggerAlert() {
        // BUILD
        double totalSpent = 900.0; // 90% of 1000

        // OPERATE
        boolean alert = budget.checkThreshold(totalSpent);

        // CHECK
        assertTrue(alert, "Alert should trigger when spending exceeds 80% threshold");
    }

    @Test
    @DisplayName("Should trigger alert when spending is exactly at threshold")
    void checkThreshold_AtExactThreshold_ShouldTriggerAlert() {
        // BUILD
        double totalSpent = 800.0; // exactly 80% of 1000

        // OPERATE
        boolean alert = budget.checkThreshold(totalSpent);

        // CHECK
        assertTrue(alert, "Alert should trigger when spending equals 80% threshold");
    }

    // --- Optional edge test for robustness ---
    @Test
    @DisplayName("Should handle zero spending correctly")
    void calculateRemaining_ZeroSpending_ShouldReturnFullBudget() {
        // BUILD
        double totalSpent = 0.0;

        // OPERATE
        double remaining = budget.calculateRemaining(totalSpent);

        // CHECK
        assertEquals(1000.0, remaining, 0.001, "Remaining should equal full budget when no spending occurs");
    }
}
