import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ReportControllerTest {

    private ReportService reportService;
    private ReportController controller;
    private Report mockReport;

    @BeforeEach
    void setUp() {
        reportService = Mockito.mock(ReportService.class);
        controller = new ReportController(reportService);
        mockReport = new Report("R1", "A1", "Monthly Expense", new Date(), new Date(), "Summary data...");
    }

    // BUILD - OPERATE - CHECK

    @Test
    void testGenerateReport_Success() {
        // BUILD
        DateRange range = new DateRange(new Date(), new Date());
        when(reportService.generateReport("A1", range)).thenReturn(mockReport);

        // OPERATE
        Response response = controller.generateReport(new ReportRequest("A1", range));

        // CHECK
        assertEquals(200, response.getStatus());
        assertNotNull(response.getBody());
        verify(reportService, times(1)).generateReport("A1", range);
    }

    @Test
    void testGenerateReport_InvalidAccountId() {
        // BUILD
        DateRange range = new DateRange(new Date(), new Date());
        when(reportService.generateReport("", range))
            .thenThrow(new IllegalArgumentException("Account ID cannot be empty."));

        // OPERATE
        Response response = controller.generateReport(new ReportRequest("", range));

        // CHECK
        assertEquals(400, response.getStatus());
        assertEquals("Account ID cannot be empty.", response.getBody());
    }

    @Test
    void testGetReport_Success() {
        // BUILD
        when(reportService.getReportById("R1")).thenReturn(mockReport);

        // OPERATE
        Response response = controller.getReport("R1");

        // CHECK
        assertEquals(200, response.getStatus());
        assertEquals(mockReport, response.getBody());
    }

    @Test
    void testGetReport_NotFound() {
        // BUILD
        when(reportService.getReportById("BAD"))
            .thenThrow(new NoSuchElementException("Report with ID BAD not found."));

        // OPERATE
        Response response = controller.getReport("BAD");

        // CHECK
        assertEquals(404, response.getStatus());
        assertEquals("Report with ID BAD not found.", response.getBody());
    }

    @Test
    void testExportToCSV_Success() {
        // BUILD
        when(reportService.exportReportToCSV("R1")).thenReturn("csv-bytes".getBytes());

        // OPERATE
        Response response = controller.exportToCSV("R1");

        // CHECK
        assertEquals(200, response.getStatus());
        assertTrue(((byte[]) response.getBody()).length > 0);
    }

    @Test
    void testExportToPDF_Success() {
        // BUILD
        when(reportService.exportReportToPDF("R1")).thenReturn("pdf-bytes".getBytes());

        // OPERATE
        Response response = controller.exportToPDF("R1");

        // CHECK
        assertEquals(200, response.getStatus());
        assertTrue(((byte[]) response.getBody()).length > 0);
    }

    @Test
    void testExportToPDF_NotFound() {
        // BUILD
        when(reportService.exportReportToPDF("X"))
            .thenThrow(new NoSuchElementException("Report with ID X not found."));

        // OPERATE
        Response response = controller.exportToPDF("X");

        // CHECK
        assertEquals(404, response.getStatus());
        assertEquals("Report with ID X not found.", response.getBody());
    }
}
