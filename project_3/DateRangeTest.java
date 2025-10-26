import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DateRangeTest {

    @Test
    @DisplayName("Should calculate correct number of days for week")
    void testGetDaysForWeek() {
        // BUILD
        DateRange dateRange = new DateRange(
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 7)
        );
        
        // OPERATE
        int days = dateRange.getDays();
        
        // CHECK
        assertEquals(7, days);
    }

    @Test
    @DisplayName("Should calculate 1 day for same date range")
    void testGetDaysForSameDate() {
        // BUILD
        LocalDate date = LocalDate.of(2024, 6, 15);
        DateRange dateRange = new DateRange(date, date);
        
        // OPERATE
        int days = dateRange.getDays();
        
        // CHECK
        assertEquals(1, days);
    }

    @Test
    @DisplayName("Should return true for equal date ranges")
    void testDateRangeEquality() {
        // BUILD
        DateRange range1 = new DateRange(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 12, 31)
        );
        DateRange range2 = new DateRange(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 12, 31)
        );
        
        // OPERATE & CHECK
        assertTrue(range1.equals(range2));
        assertEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    @DisplayName("Should return false for different date ranges")
    void testDateRangeInequality() {
        // BUILD
        DateRange range1 = new DateRange(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 6, 30)
        );
        DateRange range2 = new DateRange(
            LocalDate.of(2024, 7, 1),
            LocalDate.of(2024, 12, 31)
        );
        
        // OPERATE & CHECK
        assertFalse(range1.equals(range2));
    }
}
