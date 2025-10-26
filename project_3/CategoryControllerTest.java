import com.expensetracker.model.Category;
import com.expensetracker.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    private CategoryController categoryController;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        // BUILD
        categoryService = Mockito.mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    void testCreateCategory_ShouldReturnSuccessResponse() {
        // BUILD
        Category request = new Category("C001", "A001", "Food", "Expense", new BigDecimal("500"));
        when(categoryService.createCategory("A001", "Food", "Expense", new BigDecimal("500"))).thenReturn(request);

        // OPERATE
        ResponseEntity<?> response = categoryController.createCategory(request);

        // CHECK
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(request, response.getBody());
        verify(categoryService, times(1))
            .createCategory("A001", "Food", "Expense", new BigDecimal("500"));
    }

    @Test
    void testUpdateCategory_ShouldReturnUpdatedResponse() {
        // BUILD
        Category request = new Category("C001", "A001", "Bills", "Expense", new BigDecimal("200"));

        // OPERATE
        ResponseEntity<?> response = categoryController.updateCategory("C001", request);

        // CHECK
        assertEquals(200, response.getStatusCodeValue());
        verify(categoryService).updateCategory("C001", "Bills", "Expense", new BigDecimal("200"));
    }

    @Test
    void testDeleteCategory_ShouldReturnSuccessMessage() {
        // OPERATE
        ResponseEntity<?> response = categoryController.deleteCategory("C001");

        // CHECK
        assertEquals(200, response.getStatusCodeValue());
        verify(categoryService).deleteCategory("C001");
    }

    @Test
    void testListCategories_ShouldReturnAllCategoriesForAccount() {
        // BUILD
        String accountId = "A001";
        List<Category> mockCategories = Arrays.asList(
            new Category("C001", accountId, "Food", "Expense", new BigDecimal("300")),
            new Category("C002", accountId, "Travel", "Expense", new BigDecimal("150"))
        );
        when(categoryService.getCategoriesByAccount(accountId)).thenReturn(mockCategories);

        // OPERATE
        ResponseEntity<?> response = categoryController.listCategories(accountId);

        // CHECK
        assertEquals(200, response.getStatusCodeValue());
        List<Category> categories = (List<Category>) response.getBody();
        assertEquals(2, categories.size());
        verify(categoryService).getCategoriesByAccount(accountId);
    }
}
