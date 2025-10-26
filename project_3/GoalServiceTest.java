package com.expensetracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoalServiceTest {

    private IGoalRepository goalRepo;
    private IAccountRepository accountRepo;
    private GoalService goalService;

    @BeforeEach
    void setup() {
        goalRepo = Mockito.mock(IGoalRepository.class);
        accountRepo = Mockito.mock(IAccountRepository.class);
        goalService = new GoalService(goalRepo, accountRepo);
    }

    @Test
    void testCreateGoal_validInput_shouldReturnGoal() {
        // BUILD
        int accountId = 101;
        when(accountRepo.existsById(accountId)).thenReturn(true);
        Goal expectedGoal = new Goal("G001", "Buy Car", BigDecimal.valueOf(15000), LocalDate.now().plusMonths(12));
        when(goalRepo.save(any(Goal.class))).thenReturn(expectedGoal);

        // OPERATE
        Goal result = goalService.createGoal(accountId, "Buy Car", BigDecimal.valueOf(15000), LocalDate.now().plusMonths(12));

        // CHECK
        assertNotNull(result);
        assertEquals("Buy Car", result.getTitle());
        verify(goalRepo, times(1)).save(any(Goal.class));
    }

    @Test
    void testCreateGoal_invalidAccount_shouldThrowException() {
        // BUILD
        when(accountRepo.existsById(999)).thenReturn(false);

        // OPERATE + CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            goalService.createGoal(999, "Save", BigDecimal.valueOf(1000), LocalDate.now().plusDays(30))
        );
        assertEquals("Account not found", ex.getMessage());
    }

    @Test
    void testCreateGoal_negativeTarget_shouldThrowException() {
        // BUILD
        when(accountRepo.existsById(1)).thenReturn(true);

        // OPERATE + CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            goalService.createGoal(1, "Trip", BigDecimal.valueOf(-500), LocalDate.now().plusDays(10))
        );
        assertEquals("Target amount must be positive", ex.getMessage());
    }

    @Test
    void testUpdateGoal_validInput_shouldUpdateGoal() {
        // BUILD
        Goal existingGoal = new Goal("G001", "Old Title", BigDecimal.valueOf(1000), LocalDate.now().plusDays(10));
        when(goalRepo.findById("G001")).thenReturn(Optional.of(existingGoal));

        // OPERATE
        goalService.updateGoal("G001", "New Title", BigDecimal.valueOf(2000), LocalDate.now().plusMonths(2));

        // CHECK
        verify(goalRepo, times(1)).save(existingGoal);
        assertEquals("New Title", existingGoal.getTitle());
        assertEquals(BigDecimal.valueOf(2000), existingGoal.getTargetAmount());
    }

    @Test
    void testUpdateGoal_notFound_shouldThrowException() {
        // BUILD
        when(goalRepo.findById("G999")).thenReturn(Optional.empty());

        // OPERATE + CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            goalService.updateGoal("G999", "Test", BigDecimal.valueOf(1000), LocalDate.now())
        );
        assertEquals("Goal not found", ex.getMessage());
    }

    @Test
    void testAddContribution_valid_shouldAddAmount() {
        // BUILD
        Goal goal = new Goal("G002", "Emergency Fund", BigDecimal.valueOf(5000), LocalDate.now().plusMonths(6));
        goal.setCurrentAmount(BigDecimal.valueOf(2000));
        when(goalRepo.findById("G002")).thenReturn(Optional.of(goal));

        // OPERATE
        goalService.addContribution("G002", BigDecimal.valueOf(500));

        // CHECK
        verify(goalRepo, times(1)).save(goal);
        assertEquals(BigDecimal.valueOf(2500), goal.getCurrentAmount());
    }

    @Test
    void testAddContribution_negativeAmount_shouldThrowException() {
        // BUILD
        Goal goal = new Goal("G002", "Emergency Fund", BigDecimal.valueOf(5000), LocalDate.now().plusMonths(6));
        when(goalRepo.findById("G002")).thenReturn(Optional.of(goal));

        // OPERATE + CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            goalService.addContribution("G002", BigDecimal.valueOf(-100))
        );
        assertEquals("Contribution must be positive", ex.getMessage());
    }

    @Test
    void testGetGoalProgress_shouldReturnPercentage() {
        // BUILD
        Goal goal = new Goal("G010", "Vacation", BigDecimal.valueOf(1000), LocalDate.now());
        goal.setCurrentAmount(BigDecimal.valueOf(250));
        when(goalRepo.findById("G010")).thenReturn(Optional.of(goal));

        // OPERATE
        BigDecimal result = goalService.getGoalProgress("G010");

        // CHECK
        assertEquals(BigDecimal.valueOf(25.0), result);
    }

    @Test
    void testCheckGoalCompletion_shouldReturnTrue() {
        // BUILD
        Goal goal = new Goal("G020", "Laptop", BigDecimal.valueOf(1500), LocalDate.now());
        goal.setCurrentAmount(BigDecimal.valueOf(1500));
        when(goalRepo.findById("G020")).thenReturn(Optional.of(goal));

        // OPERATE
        boolean completed = goalService.checkGoalCompletion("G020");

        // CHECK
        assertTrue(completed);
    }
}
