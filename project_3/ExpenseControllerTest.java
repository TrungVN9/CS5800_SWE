import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseControllerTest {

    private ExpenseController expenseController;
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
        expenseController = new ExpenseController(expenseService);
    }

    @Test
    void testCreateExpense_SuccessfulResponse() {
        // BUILD
        ExpenseRequest request = new ExpenseRequest("A001", 80, LocalDate.now(), "C001", "Lunch");

        // OPERATE
        Response response = expenseController.createExpense(request);

        // CHECK
        assertEquals(201, response.getStatus());
        Expense expense = (Expense) response.getData();
        assertEquals("Lunch", expense.getNote());
    }

    @Test
    void testUpdateExpense_SuccessfulResponse() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 50, LocalDate.now(), "C001", "Groceries");
        ExpenseRequest updateRequest = new ExpenseRequest("A001", 75, LocalDate.now(), "C002", "Updated Groceries");

        // OPERATE
        Response response = expenseController.updateExpense(expense.getId(), updateRequest);

        // CHECK
        assertEquals(200, response.getStatus());
    }

    @Test
    void testDeleteExpense_SuccessfulResponse() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 30, LocalDate.now(), "C001", "Snacks");

        // OPERATE
        Response response = expenseController.deleteExpense(expense.getId());

        // CHECK
        assertEquals(200, response.getStatus());
        assertEquals("Expense deleted successfully", response.getMessage());
    }

    @Test
    void testGetExpense_ReturnsExpense() {
        // BUILD
        Expense expense = expenseService.createExpense("A001", 120, LocalDate.now(), "C001", "Bills");

        // OPERATE
        Response response = expenseController.getExpense(expense.getId());

        // CHECK
        assertEquals(200, response.getStatus());
        Expense fetched = (Expense) response.getData();
        assertEquals("Bills", fetched.getNote());
    }

    @Test
    void testListExpenses_ReturnsExpenseList() {
        // BUILD
        expenseService.createExpense("A001", 100, LocalDate.now(), "C001", "Dinner");
        expenseService.createExpense("A001", 50, LocalDate.now(), "C002", "Snacks");

        // OPERATE
        Response response = expenseController.listExpenses("A001", null);

        // CHECK
        assertEquals(200, response.getStatus());
        List<Expense> expenses = (List<Expense>) response.getData();
        assertEquals(2, expenses.size());
    }

    @Test
    void testListExpenses_WithFilter() {
        // BUILD
        expenseService.createExpense("A001", 100, LocalDate.of(2025, 1, 10), "Food", "Lunch");
        expenseService.createExpense("A001", 80, LocalDate.of(2025, 2, 5), "Transport", "Bus");
        ExpenseFilter filter = new ExpenseFilter("Food", new DateRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31)));

        // OPERATE
        Response response = expenseController.listExpenses("A001", filter);

        // CHECK
        assertEquals(200, response.getStatus());
        List<Expense> filtered = (List<Expense>) response.getData();
        assertEquals(1, filtered.size());
        assertEquals("Lunch", filtered.get(0).getNote());
    }
}
