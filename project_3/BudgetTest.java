import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    @Test
    @DisplayName("Should create budget with all required fields")
    void testCreateBudget() {
        // BUILD
        String accountId = "acc123";
        String categoryId = "cat123";
        Money limit = new Money(1000);
        BudgetPeriod period = BudgetPeriod.MONTHLY;
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        
        // OPERATE
        Budget budget = new Budget(accountId, categoryId, limit, period, startDate);
        
        // CHECK
        assertNotNull(budget.getBudgetId());
        assertEquals(accountId, budget.getAccountId());
        assertEquals(categoryId, budget.getCategoryId());
        assertEquals(limit, budget.getLimit());
        assertEquals(period, budget.getPeriod());
        assertEquals(startDate, budget.getStartDate());
        assertNotNull(budget.getEndDate());
        assertTrue(budget.isActive());
        assertNotNull(budget.getCreatedAt());
    }

    @Test
    @DisplayName("Should calculate end date for daily budget")
    void testDailyBudgetEndDate() {
        // BUILD
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        
        // OPERATE
        Budget budget = new Budget("acc123", "cat123", new Money(100), BudgetPeriod.DAILY, startDate);
        
        // CHECK
        assertEquals(startDate, budget.getEndDate());
    }

    @Test
    @DisplayName("Should calculate end date for weekly budget")
    void testWeeklyBudgetEndDate() {
        // BUILD
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate expectedEndDate = LocalDate.of(2024, 6, 7);
        
        // OPERATE
        Budget budget = new Budget("acc123", "cat123", new Money(500), BudgetPeriod.WEEKLY, startDate);
        
        // CHECK
        assertEquals(expectedEndDate, budget.getEndDate());
    }

    @Test
    @DisplayName("Should calculate end date for monthly budget")
    void testMonthlyBudgetEndDate() {
        // BUILD
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate expectedEndDate = LocalDate.of(2024, 6, 30);
        
        // OPERATE
        Budget budget = new Budget("acc123", "cat123", new Money(2000), BudgetPeriod.MONTHLY, startDate);
        
        // CHECK
        assertEquals(expectedEndDate, budget.getEndDate());
    }

    @Test
    @DisplayName("Should calculate end date for quarterly budget")
    void testQuarterlyBudgetEndDate() {
        // BUILD
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate expectedEndDate = LocalDate.of(2024, 3, 31);
        
        // OPERATE
        Budget budget = new Budget("acc123", "cat123", new Money(6000), BudgetPeriod.QUARTERLY, startDate);
        
        // CHECK
        assertEquals(expectedEndDate, budget.getEndDate());
    }

    @Test
    @DisplayName("Should calculate end date for yearly budget")
    void testYearlyBudgetEndDate() {
        // BUILD
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate expectedEndDate = LocalDate.of(2024, 12, 31);
        
        // OPERATE
        Budget budget = new Budget("acc123", "cat123", new Money(24000), BudgetPeriod.YEARLY, startDate);
        
        // CHECK
        assertEquals(expectedEndDate, budget.getEndDate());
    }

    @Test
    @DisplayName("Should throw exception when account ID is null")
    void testNullAccountId() {
        // BUILD
        String nullAccountId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget(nullAccountId, "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now())
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when category ID is null")
    void testNullCategoryId() {
        // BUILD
        String nullCategoryId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", nullCategoryId, new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now())
        );
        assertEquals("Category ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when limit is null")
    void testNullLimit() {
        // BUILD
        Money nullLimit = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", "cat123", nullLimit, BudgetPeriod.MONTHLY, LocalDate.now())
        );
        assertEquals("Budget limit cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when limit is zero")
    void testZeroLimit() {
        // BUILD
        Money zeroLimit = new Money(0);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", "cat123", zeroLimit, BudgetPeriod.MONTHLY, LocalDate.now())
        );
        assertEquals("Budget limit must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when limit is negative")
    void testNegativeLimit() {
        // BUILD & OPERATE & CHECK
        assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", "cat123", new Money(-100), BudgetPeriod.MONTHLY, LocalDate.now())
        );
    }

    @Test
    @DisplayName("Should throw exception when period is null")
    void testNullPeriod() {
        // BUILD
        BudgetPeriod nullPeriod = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", "cat123", new Money(1000), nullPeriod, LocalDate.now())
        );
        assertEquals("Budget period cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when start date is null")
    void testNullStartDate() {
        // BUILD
        LocalDate nullStartDate = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, nullStartDate)
        );
        assertEquals("Start date cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate remaining amount correctly")
    void testCalculateRemaining() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(300);
        
        // OPERATE
        Money remaining = budget.calculateRemaining(spent);
        
        // CHECK
        assertEquals(700, remaining.getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should return zero when spent equals limit")
    void testCalculateRemainingWhenFullySpent() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1000);
        
        // OPERATE
        Money remaining = budget.calculateRemaining(spent);
        
        // CHECK
        assertEquals(0, remaining.getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should return zero when spent exceeds limit")
    void testCalculateRemainingWhenOverspent() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1200);
        
        // OPERATE
        Money remaining = budget.calculateRemaining(spent);
        
        // CHECK
        assertEquals(0, remaining.getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should throw exception when spent amount is null")
    void testCalculateRemainingWithNullSpent() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money nullSpent = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> budget.calculateRemaining(nullSpent)
        );
        assertEquals("Spent amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when currency mismatch in calculateRemaining")
    void testCalculateRemainingCurrencyMismatch() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000, "USD"), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(300, "EUR");
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> budget.calculateRemaining(spent)
        );
        assertTrue(exception.getMessage().contains("Currency mismatch"));
    }

    @Test
    @DisplayName("Should return true when threshold is exceeded")
    void testIsOverThreshold() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(850);
        
        // OPERATE & CHECK
        assertTrue(budget.isOverThreshold(spent, 0.80));
    }

    @Test
    @DisplayName("Should return false when threshold is not exceeded")
    void testIsNotOverThreshold() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(700);
        
        // OPERATE & CHECK
        assertFalse(budget.isOverThreshold(spent, 0.80));
    }

    @Test
    @DisplayName("Should return true when spent equals threshold")
    void testIsOverThresholdExactly() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(800);
        
        // OPERATE & CHECK
        assertTrue(budget.isOverThreshold(spent, 0.80));
    }

    @Test
    @DisplayName("Should throw exception when threshold is negative")
    void testIsOverThresholdNegative() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(500);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> budget.isOverThreshold(spent, -0.1)
        );
        assertEquals("Threshold must be between 0 and 1", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when threshold is greater than 1")
    void testIsOverThresholdGreaterThanOne() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(500);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> budget.isOverThreshold(spent, 1.5)
        );
        assertEquals("Threshold must be between 0 and 1", exception.getMessage());
    }

    @Test
    @DisplayName("Should accept threshold of 0")
    void testIsOverThresholdZero() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1);
        
        // OPERATE & CHECK
        assertTrue(budget.isOverThreshold(spent, 0.0));
    }

    @Test
    @DisplayName("Should accept threshold of 1")
    void testIsOverThresholdOne() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1000);
        
        // OPERATE & CHECK
        assertTrue(budget.isOverThreshold(spent, 1.0));
    }

    @Test
    @DisplayName("Should return true when over budget")
    void testIsOverBudget() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1100);
        
        // OPERATE & CHECK
        assertTrue(budget.isOverBudget(spent));
    }

    @Test
    @DisplayName("Should return false when under budget")
    void testIsNotOverBudget() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(900);
        
        // OPERATE & CHECK
        assertFalse(budget.isOverBudget(spent));
    }

    @Test
    @DisplayName("Should return false when spent equals budget")
    void testIsNotOverBudgetWhenEqual() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        Money spent = new Money(1000);
        
        // OPERATE & CHECK
        assertFalse(budget.isOverBudget(spent));
    }

    @Test
    @DisplayName("Should throw exception when spent is null in isOverBudget")
    void testIsOverBudgetWithNullSpent() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> budget.isOverBudget(null)
        );
        assertEquals("Spent amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should deactivate budget")
    void testDeactivateBudget() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        assertTrue(budget.isActive());
        
        // OPERATE
        budget.deactivate();
        
        // CHECK
        assertFalse(budget.isActive());
    }

    @Test
    @DisplayName("Should handle multiple deactivations")
    void testMultipleDeactivations() {
        // BUILD
        Budget budget = new Budget("acc123", "cat123", new Money(1000), BudgetPeriod.MONTHLY, LocalDate.now());
        
        // OPERATE
        budget.deactivate();
        budget.deactivate();
        
        // CHECK
        assertFalse(budget.isActive());
    }
}
