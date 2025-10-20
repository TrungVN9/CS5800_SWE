import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    private Goal goal;

    @BeforeEach
    void setUp() {
        // BUILD
        goal = new Goal("Emergency Fund", 5000.0);
    }

    @Test
    @DisplayName("Should calculate correct progress percentage when below target")
    void trackProgress_BelowTarget_ShouldCalculateCorrectPercentage() {
        // BUILD
        double currentSavings = 2000.0;

        // OPERATE
        double progress = goal.trackProgress(currentSavings);

        // CHECK
        assertEquals(40.0, progress, 0.01, "Progress should be (2000 / 5000) * 100 = 40%");
        assertFalse(goal.isCompleted(), "Goal should not be marked as completed below target");
    }

    @Test
    @DisplayName("Should mark goal as completed when reaching target")
    void trackProgress_ReachingTarget_ShouldMarkAsCompleted() {
        // BUILD
        double currentSavings = 5000.0;

        // OPERATE
        double progress = goal.trackProgress(currentSavings);

        // CHECK
        assertEquals(100.0, progress, 0.01, "Progress should be 100% when target is reached");
        assertTrue(goal.isCompleted(), "Goal should be marked as completed when target is met");
    }

    @Test
    @DisplayName("Should send positive notification when goal is on track")
    void notifyGoalStatus_OnTrack_ShouldSendPositiveNotification() {
        // BUILD
        double currentSavings = 3000.0;
        goal.trackProgress(currentSavings);

        // OPERATE
        String notification = goal.notifyGoalStatus();

        // CHECK
        assertNotNull(notification);
        assertTrue(notification.contains("on track"), 
            "Notification should indicate goal is on track");
    }

    @Test
    @DisplayName("Should send warning notification when goal is behind schedule")
    void notifyGoalStatus_BehindSchedule_ShouldSendWarningNotification() {
        // BUILD
        double currentSavings = 500.0;
        goal.trackProgress(currentSavings);

        // OPERATE
        String notification = goal.notifyGoalStatus();

        // CHECK
        assertNotNull(notification);
        assertTrue(notification.contains("behind schedule") || notification.contains("warning"),
            "Notification should warn user about falling behind");
    }

    // --- Optional Edge Case ---
    @Test
    @DisplayName("Should handle zero target amount safely")
    void trackProgress_WithZeroTarget_ShouldReturnZeroProgress() {
        // BUILD
        Goal invalidGoal = new Goal("Invalid Goal", 0.0);

        // OPERATE
        double progress = invalidGoal.trackProgress(1000.0);

        // CHECK
        assertEquals(0.0, progress, 0.01, "Progress should default to 0 when goal target is zero");
        assertFalse(invalidGoal.isCompleted(), "Goal with zero target should not be marked completed");
    }
}
