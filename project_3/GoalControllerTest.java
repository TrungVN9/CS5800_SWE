package com.expensetracker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoalControllerTest {

    private GoalService goalService;
    private GoalController goalController;

    @BeforeEach
    void setup() {
        goalService = Mockito.mock(GoalService.class);
        goalController = new GoalController(goalService);
    }

    @Test
    @Display("_valid_shouldReturnCreatedResponse")
    void testCreateGoal() {
        // BUILD
        Goal goal = new Goal("G001", "New Car", BigDecimal.valueOf(20000), LocalDate.now().plusYears(1));
        GoalRequest request = new GoalRequest(1, "New Car", BigDecimal.valueOf(20000), LocalDate.now().plusYears(1));
        when(goalService.createGoal(anyInt(), anyString(), any(), any())).thenReturn(goal);

        // OPERATE
        Response response = goalController.createGoal(request);

        // CHECK
        assertEquals(201, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("New Car"));
        verify(goalService, times(1)).createGoal(1, "New Car", BigDecimal.valueOf(20000), request.getDeadline());
    }

    @Test
    @Display("_missingTitle_shouldReturnBadRequest")
    void testCreateGoal() {
        // BUILD
        GoalRequest request = new GoalRequest(1, "", BigDecimal.valueOf(1000), LocalDate.now());

        // OPERATE
        Response response = goalController.createGoal(request);

        // CHECK
        assertEquals(400, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Missing required fields"));
    }

    @Test
    @Display("_valid_shouldReturnOk")
    void testUpdateGoal() {
        // BUILD
        UpdateGoalRequest request = new UpdateGoalRequest("Updated", BigDecimal.valueOf(3000), LocalDate.now().plusDays(60));

        // OPERATE
        Response response = goalController.updateGoal("G001", request);

        // CHECK
        verify(goalService).updateGoal("G001", "Updated", BigDecimal.valueOf(3000), request.getDeadline());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Display("_valid_shouldReturnOk")
    void testAddContribution() {
        // BUILD
        ContributionRequest request = new ContributionRequest(BigDecimal.valueOf(500));

        // OPERATE
        Response response = goalController.addContribution("G002", request);

        // CHECK
        verify(goalService).addContribution("G002", BigDecimal.valueOf(500));
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Display("_valid_shouldReturnProgress")
    void testGetGoalProgress() {
        // BUILD
        when(goalService.getGoalProgress("G003")).thenReturn(BigDecimal.valueOf(75));

        // OPERATE
        Response response = goalController.getGoalProgress("G003");

        // CHECK
        assertEquals(200, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(75), response.getBody());
    }

    @Test
    @Display("_valid_shouldReturnGoalsList")
    void testListGoals() {
        // BUILD
        List<Goal> goals = List.of(new Goal("G001", "Trip", BigDecimal.valueOf(1000), LocalDate.now()));
        when(goalService.getGoalsByAccount(1)).thenReturn(goals);

        // OPERATE
        Response response = goalController.listGoals(1);

        // CHECK
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Trip"));
    }
}
