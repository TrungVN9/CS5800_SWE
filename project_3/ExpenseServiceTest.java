import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceTest {

    private ExpenseService expenseService;
    private IExpenseRepository expenseRepo;
    private ICategoryRepository categoryRepo;
    private IAccountRepository accountRepo;

    @BeforeEach
    void setUp() {
        // BUILD
        expenseRepo = new InMemoryExpenseRepository();
        categoryRepo = new InMemoryCategoryRepository();
        accountRepo = new InMemoryAccountRepository();
        expenseService = new ExpenseService(expenseRepo, categoryRepo, accountRepo);
    }

    @Test
    void testCreateExpense_Success() {
        // BUILD
        String accountId = "A001";
        String categoryId = "C001";
        LocalDate date = LocalDate.now();

        // OPERATE
        Expense expense = expenseService.createExpense(accountId, 120.5, date, categoryId, "Dinner");

        // CHECK
        assertNotNull(expense);
        assertEquals(accountId, expense.getAccountId());
        assertEquals("Dinner", expense.getNote());
    }

    @Test
    void testUpdateExpense_ValidExpense() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 50, LocalDate.now(), "C001", "Lunch");

        // OPERATE
        expenseService.updateExpense(expense.getId(), 75.0, LocalDate.now(), "C002", "Updated Lunch");

        // CHECK
        Expense updated = expenseService.getExpenseById(expense.getId());
        assertEquals(75.0, updated.getAmount());
        assertEquals("Updated Lunch", updated.getNote());
    }

    @Test
    void testDeleteExpense_RemovesSuccessfully() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 40, LocalDate.now(), "C001", "Snacks");

        // OPERATE
        expenseService.deleteExpense(expense.getId());

        // CHECK
        assertThrows(IllegalArgumentException.class, () -> expenseService.getExpenseById(expense.getId()));
    }

    @Test
    void testGetExpenseById_ReturnsCorrectExpense() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 100, LocalDate.now(), "C001", "Grocery");

        // OPERATE
        Expense retrieved = expenseService.getExpenseById(expense.getId());

        // CHECK
        assertEquals(expense.getId(), retrieved.getId());
        assertEquals("Grocery", retrieved.getNote());
    }

    @Test
    void testGetExpensesByAccount_ReturnsAll() {
        // BUILD
        expenseService.createExpense("A001", 25, LocalDate.now(), "C001", "Snack");
        expenseService.createExpense("A001", 50, LocalDate.now(), "C002", "Dinner");

        // OPERATE
        List<Expense> expenses = expenseService.getExpensesByAccount("A001");

        // CHECK
        assertEquals(2, expenses.size());
    }

    @Test
    void testFilterExpenses_ByCategoryAndDateRange() {
        // BUILD
        expenseService.createExpense("A001", 100, LocalDate.of(2025, 1, 5), "Food", "Breakfast");
        expenseService.createExpense("A001", 80, LocalDate.of(2025, 2, 10), "Transport", "Bus Ticket");
        DateRange january = new DateRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));

        // OPERATE
        List<Expense> filtered = expenseService.filterExpenses("A001", "Food", january);

        // CHECK
        assertEquals(1, filtered.size());
        assertEquals("Breakfast", filtered.get(0).getNote());
    }

    @Test
    void testCalculateTotal_SumOfExpenses() {
        // BUILD
        expenseService.createExpense("A001", 200, LocalDate.of(2025, 3, 1), "Rent", "March Rent");
        expenseService.createExpense("A001", 100, LocalDate.of(2025, 3, 15), "Utility", "Electricity");
        DateRange march = new DateRange(LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 31));

        // OPERATE
        Money total = expenseService.calculateTotal("A001", march);

        // CHECK
        assertEquals(300.0, total.getAmount());
    }
}
