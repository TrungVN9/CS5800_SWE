import com.expensetracker.model.Category;
import com.expensetracker.repository.ICategoryRepository;
import com.expensetracker.repository.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private CategoryService categoryService;
    private ICategoryRepository categoryRepo;
    private IAccountRepository accountRepo;

    @BeforeEach
    void setUp() {
        // BUILD
        categoryRepo = Mockito.mock(ICategoryRepository.class);
        accountRepo = Mockito.mock(IAccountRepository.class);
        categoryService = new CategoryService(categoryRepo, accountRepo);
    }

    @Test
    void testCreateCategory_ShouldReturnNewCategory_WhenValidInput() {
        // BUILD
        String accountId = "A001";
        String name = "Food";
        String type = "Expense";
        BigDecimal limit = new BigDecimal("500");

        Category category = new Category("C001", accountId, name, type, limit);
        when(accountRepo.existsById(accountId)).thenReturn(true);
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // OPERATE
        Category result = categoryService.createCategory(accountId, name, type, limit);

        // CHECK
        assertNotNull(result);
        assertEquals("Food", result.getName());
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    @Test
    void testCreateCategory_ShouldThrowException_WhenAccountNotFound() {
        // BUILD
        when(accountRepo.existsById("A999")).thenReturn(false);

        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            categoryService.createCategory("A999", "Travel", "Expense", new BigDecimal("200"))
        );
        assertEquals("Account not found for ID: A999", ex.getMessage());
    }

    @Test
    void testUpdateCategory_ShouldUpdateExistingCategory() {
        // BUILD
        Category existing = new Category("C001", "A001", "Food", "Expense", new BigDecimal("300"));
        when(categoryRepo.findById("C001")).thenReturn(Optional.of(existing));

        // OPERATE
        categoryService.updateCategory("C001", "Groceries", "Expense", new BigDecimal("400"));

        // CHECK
        verify(categoryRepo).save(existing);
        assertEquals("Groceries", existing.getName());
        assertEquals(new BigDecimal("400"), existing.getBudgetLimit());
    }

    @Test
    void testUpdateCategory_ShouldThrowException_WhenCategoryNotFound() {
        // BUILD
        when(categoryRepo.findById("C999")).thenReturn(Optional.empty());

        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            categoryService.updateCategory("C999", "Bills", "Expense", new BigDecimal("150"))
        );
        assertEquals("Category not found for ID: C999", ex.getMessage());
    }

    @Test
    void testDeleteCategory_ShouldCallRepositoryDelete() {
        // BUILD
        when(categoryRepo.existsById("C001")).thenReturn(true);

        // OPERATE
        categoryService.deleteCategory("C001");

        // CHECK
        verify(categoryRepo).delete("C001");
    }

    @Test
    void testDeleteCategory_ShouldThrowException_WhenCategoryNotFound() {
        // BUILD
        when(categoryRepo.existsById("C999")).thenReturn(false);

        // OPERATE & CHECK
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            categoryService.deleteCategory("C999")
        );
        assertEquals("Category not found for ID: C999", ex.getMessage());
    }

    @Test
    void testGetCategoriesByAccount_ShouldReturnListOfCategories() {
        // BUILD
        String accountId = "A001";
        List<Category> categories = Arrays.asList(
            new Category("C001", accountId, "Food", "Expense", new BigDecimal("500")),
            new Category("C002", accountId, "Transport", "Expense", new BigDecimal("300"))
        );
        when(categoryRepo.findByAccountId(accountId)).thenReturn(categories);

        // OPERATE
        List<Category> result = categoryService.getCategoriesByAccount(accountId);

        // CHECK
        assertEquals(2, result.size());
        assertEquals("Food", result.get(0).getName());
        verify(categoryRepo, times(1)).findByAccountId(accountId);
    }
}
