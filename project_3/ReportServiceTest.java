import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    private IReportGenerator reportGenerator;
    private IReportRepository reportRepo;
    private IReportExporter reportExporter;
    private ReportService reportService;

    private Report mockReport;

    @BeforeEach
    void setUp() {
        reportGenerator = Mockito.mock(IReportGenerator.class);
        reportRepo = Mockito.mock(IReportRepository.class);
        reportExporter = Mockito.mock(IReportExporter.class);
        reportService = new ReportService(reportGenerator, reportRepo, reportExporter);

        mockReport = new Report("R1", "A1", "Monthly Expense", new Date(), new Date(), "Summary data...");
    }

    // BUILD - OPERATE - CHECK

    @Test
    void testGenerateReport_ValidInputs() {
        // BUILD
        DateRange range = new DateRange(new Date(), new Date());
        when(reportGenerator.generate("A1", range)).thenReturn(mockReport);

        // OPERATE
        Report result = reportService.generateReport("A1", range);

        // CHECK
        assertNotNull(result);
        assertEquals("R1", result.getReportId());
        verify(reportRepo, times(1)).save(result);
    }

    @Test
    void testGenerateReport_InvalidAccountId() {
        // OPERATE + CHECK
        Exception ex = assertThrows(IllegalArgumentException.class, 
            () -> reportService.generateReport("", new DateRange(new Date(), new Date())));
        assertEquals("Account ID cannot be empty.", ex.getMessage());
    }

    @Test
    void testGetReportById_Found() {
        // BUILD
        when(reportRepo.findById("R1")).thenReturn(mockReport);

        // OPERATE
        Report found = reportService.getReportById("R1");

        // CHECK
        assertNotNull(found);
        assertEquals("Monthly Expense", found.getTitle());
    }

    @Test
    void testGetReportById_NotFound() {
        // BUILD
        when(reportRepo.findById("R999")).thenReturn(null);

        // OPERATE + CHECK
        Exception ex = assertThrows(NoSuchElementException.class, 
            () -> reportService.getReportById("R999"));
        assertEquals("Report with ID R999 not found.", ex.getMessage());
    }

    @Test
    void testExportReportToCSV_Valid() {
        // BUILD
        when(reportRepo.findById("R1")).thenReturn(mockReport);
        when(reportExporter.exportToCSV(mockReport)).thenReturn("csv-data".getBytes());

        // OPERATE
        byte[] csv = reportService.exportReportToCSV("R1");

        // CHECK
        assertNotNull(csv);
        assertTrue(csv.length > 0);
    }

    @Test
    void testExportReportToCSV_ReportNotFound() {
        // BUILD
        when(reportRepo.findById("R404")).thenReturn(null);

        // OPERATE + CHECK
        Exception ex = assertThrows(NoSuchElementException.class, 
            () -> reportService.exportReportToCSV("R404"));
        assertEquals("Report with ID R404 not found.", ex.getMessage());
    }

    @Test
    void testExportReportToPDF_Valid() {
        // BUILD
        when(reportRepo.findById("R1")).thenReturn(mockReport);
        when(reportExporter.exportToPDF(mockReport)).thenReturn("pdf-data".getBytes());

        // OPERATE
        byte[] pdf = reportService.exportReportToPDF("R1");

        // CHECK
        assertNotNull(pdf);
        assertTrue(pdf.length > 0);
    }

    @Test
    void testExportReportToPDF_ReportNotFound() {
        // BUILD
        when(reportRepo.findById("BAD")).thenReturn(null);

        // OPERATE + CHECK
        Exception ex = assertThrows(NoSuchElementException.class, 
            () -> reportService.exportReportToPDF("BAD"));
        assertEquals("Report with ID BAD not found.", ex.getMessage());
    }
}
