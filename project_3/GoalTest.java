import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GoalTest {

    @Test
    @DisplayName("Should create goal with all required fields")
    void testCreateGoal() {
        // BUILD
        String accountId = "acc123";
        String title = "Save for vacation";
        Money targetAmount = new Money(5000);
        LocalDate deadline = LocalDate.now().plusMonths(6);
        
        // OPERATE
        Goal goal = new Goal(accountId, title, targetAmount, deadline);
        
        // CHECK
        assertNotNull(goal.getGoalId());
        assertEquals(accountId, goal.getAccountId());
        assertEquals(title, goal.getTitle());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(0, goal.getSavedAmount().getAmount(), 0.01);
        assertEquals(deadline, goal.getDeadline());
        assertEquals(GoalStatus.ACTIVE, goal.getStatus());
        assertNotNull(goal.getCreatedAt());
        assertNotNull(goal.getUpdatedAt());
    }

    @Test
    @DisplayName("Should throw exception when account ID is null")
    void testNullAccountId() {
        // BUILD
        String nullAccountId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal(nullAccountId, "Save", new Money(1000), LocalDate.now().plusDays(30))
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
            () -> new Goal(emptyAccountId, "Save", new Money(1000), LocalDate.now().plusDays(30))
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when title is null")
    void testNullTitle() {
        // BUILD
        String nullTitle = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", nullTitle, new Money(1000), LocalDate.now().plusDays(30))
        );
        assertEquals("Goal title cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when title is empty")
    void testEmptyTitle() {
        // BUILD
        String emptyTitle = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", emptyTitle, new Money(1000), LocalDate.now().plusDays(30))
        );
        assertEquals("Goal title cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when target amount is null")
    void testNullTargetAmount() {
        // BUILD
        Money nullTarget = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", "Save", nullTarget, LocalDate.now().plusDays(30))
        );
        assertEquals("Target amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when target amount is zero")
    void testZeroTargetAmount() {
        // BUILD
        Money zeroTarget = new Money(0);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", "Save", zeroTarget, LocalDate.now().plusDays(30))
        );
        assertEquals("Target amount must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when target amount is negative")
    void testNegativeTargetAmount() {
        // BUILD & OPERATE & CHECK
        assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", "Save", new Money(-1000), LocalDate.now().plusDays(30))
        );
    }

    @Test
    @DisplayName("Should throw exception when deadline is null")
    void testNullDeadline() {
        // BUILD
        LocalDate nullDeadline = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", "Save", new Money(1000), nullDeadline)
        );
        assertEquals("Deadline cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when deadline is in the past")
    void testPastDeadline() {
        // BUILD
        LocalDate pastDeadline = LocalDate.now().minusDays(1);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Goal("acc123", "Save", new Money(1000), pastDeadline)
        );
        assertEquals("Deadline cannot be in the past", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow today as deadline")
    void testTodayDeadline() {
        // BUILD
        LocalDate today = LocalDate.now();
        
        // OPERATE
        Goal goal = new Goal("acc123", "Save", new Money(1000), today);
        
        // CHECK
        assertEquals(today, goal.getDeadline());
    }

    @Test
    @DisplayName("Should add contribution successfully")
    void testAddContribution() {
        // BUILD
        Goal goal = new Goal("acc123", "Save for car", new Money(10000), LocalDate.now().plusMonths(12));
        Money contribution = new Money(1000);
        
        // OPERATE
        goal.addContribution(contribution);
        
        // CHECK
        assertEquals(1000, goal.getSavedAmount().getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should add multiple contributions")
    void testAddMultipleContributions() {
        // BUILD
        Goal goal = new Goal("acc123", "Save for car", new Money(10000), LocalDate.now().plusMonths(12));
        
        // OPERATE
        goal.addContribution(new Money(1000));
        goal.addContribution(new Money(500));
        goal.addContribution(new Money(750));
        
        // CHECK
        assertEquals(2250, goal.getSavedAmount().getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should auto-complete goal when target is reached")
    void testAutoCompleteWhenTargetReached() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE
        goal.addContribution(new Money(1000));
        
        // CHECK
        assertEquals(GoalStatus.COMPLETED, goal.getStatus());
        assertTrue(goal.isCompleted());
    }

    @Test
    @DisplayName("Should auto-complete goal when contribution exceeds target")
    void testAutoCompleteWhenExceedingTarget() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE
        goal.addContribution(new Money(1500));
        
        // CHECK
        assertEquals(GoalStatus.COMPLETED, goal.getStatus());
        assertEquals(1500, goal.getSavedAmount().getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should throw exception when contribution is null")
    void testNullContribution() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        Money nullContribution = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> goal.addContribution(nullContribution)
        );
        assertEquals("Contribution amount cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when contribution is zero")
    void testZeroContribution() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        Money zeroContribution = new Money(0);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> goal.addContribution(zeroContribution)
        );
        assertEquals("Contribution amount must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when contribution is negative")
    void testNegativeContribution() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE & CHECK
        assertThrows(
            IllegalArgumentException.class,
            () -> goal.addContribution(new Money(-100))
        );
    }

    @Test
    @DisplayName("Should throw exception when adding contribution with currency mismatch")
    void testCurrencyMismatchContribution() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000, "USD"), LocalDate.now().plusDays(30));
        Money contribution = new Money(100, "EUR");
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> goal.addContribution(contribution)
        );
        assertEquals("Currency mismatch", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when adding contribution to completed goal")
    void testAddContributionToCompletedGoal() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1000)); // Complete the goal
        
        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> goal.addContribution(new Money(100))
        );
        assertEquals("Cannot add contribution to completed goal", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when adding contribution to cancelled goal")
    void testAddContributionToCancelledGoal() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.cancel();
        
        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> goal.addContribution(new Money(100))
        );
        assertEquals("Cannot add contribution to cancelled goal", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate progress correctly")
    void testCalculateProgress() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(250));
        
        // OPERATE
        double progress = goal.calculateProgress();
        
        // CHECK
        assertEquals(25.0, progress, 0.01);
    }

    @Test
    @DisplayName("Should return 0 progress for new goal")
    void testCalculateProgressForNewGoal() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE
        double progress = goal.calculateProgress();
        
        // CHECK
        assertEquals(0.0, progress, 0.01);
    }

    @Test
    @DisplayName("Should return 100 progress when goal is completed")
    void testCalculateProgressWhenCompleted() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1000));
        
        // OPERATE
        double progress = goal.calculateProgress();
        
        // CHECK
        assertEquals(100.0, progress, 0.01);
    }

    @Test
    @DisplayName("Should cap progress at 100 when over-contributed")
    void testCalculateProgressWhenOverContributed() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1500));
        
        // OPERATE
        double progress = goal.calculateProgress();
        
        // CHECK
        assertEquals(100.0, progress, 0.01);
    }

    @Test
    @DisplayName("Should return false when goal is not completed")
    void testIsNotCompleted() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(500));
        
        // OPERATE & CHECK
        assertFalse(goal.isCompleted());
    }

    @Test
    @DisplayName("Should return true when goal is completed")
    void testIsCompleted() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1000));
        
        // OPERATE & CHECK
        assertTrue(goal.isCompleted());
    }

    @Test
    @DisplayName("Should return false when goal is not overdue")
    void testIsNotOverdue() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE & CHECK
        assertFalse(goal.isOverdue());
    }

    @Test
    @DisplayName("Should return false when completed goal past deadline")
    void testIsNotOverdueWhenCompleted() {
        // BUILD
        Goal goal = new Goal(
            "goal123", "acc123", "Save", new Money(1000), 
            new Money(1000), LocalDate.now().minusDays(1), GoalStatus.COMPLETED,
            LocalDateTime.now(), LocalDateTime.now()
        );
        
        // OPERATE & CHECK
        assertFalse(goal.isOverdue());
    }

    @Test
    @DisplayName("Should cancel active goal")
    void testCancelGoal() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE
        goal.cancel();
        
        // CHECK
        assertEquals(GoalStatus.CANCELLED, goal.getStatus());
    }

    @Test
    @DisplayName("Should throw exception when cancelling completed goal")
    void testCancelCompletedGoal() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1000));
        
        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> goal.cancel()
        );
        assertEquals("Cannot cancel a completed goal", exception.getMessage());
    }

    @Test
    @DisplayName("Should handle multiple cancellations")
    void testMultipleCancellations() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        
        // OPERATE
        goal.cancel();
        goal.cancel(); // Should not throw exception
        
        // CHECK
        assertEquals(GoalStatus.CANCELLED, goal.getStatus());
    }

    @Test
    @DisplayName("Should calculate remaining amount correctly")
    void testGetRemainingAmount() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(300));
        
        // OPERATE
        Money remaining = goal.getRemainingAmount();
        
        // CHECK
        assertEquals(700, remaining.getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should return zero remaining when goal is completed")
    void testGetRemainingAmountWhenCompleted() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1000));
        
        // OPERATE
        Money remaining = goal.getRemainingAmount();
        
        // CHECK
        assertEquals(0, remaining.getAmount(), 0.01);
    }

    @Test
    @DisplayName("Should return zero remaining when over-contributed")
    void testGetRemainingAmountWhenOverContributed() {
        // BUILD
        Goal goal = new Goal("acc123", "Save", new Money(1000), LocalDate.now().plusDays(30));
        goal.addContribution(new Money(1500));
        
        // OPERATE
        Money remaining = goal.getRemainingAmount();
        
        // CHECK
        assertEquals(0, remaining.getAmount(), 0.01);
    }
}
