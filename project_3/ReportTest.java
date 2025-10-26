package com.expensetracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private Report report;
    private DateRange validRange;
    private ReportSummary summary;

    @BeforeEach
    void setUp() {
        // BUILD: valid setup for each test
        validRange = new DateRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        summary = new ReportSummary(1000.0, 400.0, 600.0);
        report = new Report("R001", "A001", validRange, summary, LocalDateTime.now());
    }

    @Test
    void testShouldBuildValidReport_WhenGivenValidInputs() {
        // OPERATE & CHECK
        assertEquals("R001", report.getReportId());
        assertEquals("A001", report.getAccountId());
        assertEquals(validRange, report.getDateRange());
        assertEquals(summary, report.getContent());
        assertNotNull(report.getGeneratedAt());
    }

    @Test
    void testGetContent_ShouldReturnSameReportSummary() {
        // OPERATE
        ReportSummary result = report.getContent();

        // CHECK
        assertSame(summary, result, "getContent() should return the same ReportSummary instance");
    }

    @Test
    void testShouldThrowException_WhenReportIdIsNull() {
        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Report(null, "A001", validRange, summary, LocalDateTime.now())
        );
        assertEquals("Report ID cannot be null or empty", ex.getMessage());
    }

    @Test
    void testShouldThrowException_WhenAccountIdIsEmpty() {
        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Report("R002", "", validRange, summary, LocalDateTime.now())
        );
        assertEquals("Account ID cannot be null or empty", ex.getMessage());
    }

    @Test
    void testShouldThrowException_WhenDateRangeInvalid() {
        // BUILD
        DateRange invalidRange = new DateRange(LocalDate.of(2025, 2, 1), LocalDate.of(2025, 1, 1));

        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Report("R003", "A001", invalidRange, summary, LocalDateTime.now())
        );
        assertEquals("Invalid date range: start date must be before end date", ex.getMessage());
    }

    @Test
    void testShouldThrowException_WhenSummaryIsNull() {
        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Report("R004", "A001", validRange, null, LocalDateTime.now())
        );
        assertEquals("Report summary cannot be null", ex.getMessage());
    }

    @Test
    void testShouldThrowException_WhenGeneratedAtIsInFuture() {
        // BUILD
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);

        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Report("R005", "A001", validRange, summary, futureTime)
        );
        assertEquals("Generated date cannot be in the future", ex.getMessage());
    }
}
