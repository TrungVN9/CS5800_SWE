import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Should create category with all required fields")
    void testCreateCategory() {
        // BUILD
        String accountId = "acc123";
        String name = "Groceries";
        CategoryType type = CategoryType.EXPENSE;
        Money budgetLimit = new Money(500);
        
        // OPERATE
        Category category = new Category(accountId, name, type, budgetLimit);
        
        // CHECK
        assertNotNull(category.getCategoryId());
        assertEquals(accountId, category.getAccountId());
        assertEquals(name, category.getName());
        assertEquals(type, category.getType());
        assertEquals(budgetLimit, category.getBudgetLimit());
        assertTrue(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
    }

    @Test
    @DisplayName("Should create category without budget limit")
    void testCreateCategoryWithoutBudgetLimit() {
        // BUILD
        String accountId = "acc123";
        String name = "Entertainment";
        CategoryType type = CategoryType.EXPENSE;
        
        // OPERATE
        Category category = new Category(accountId, name, type, null);
        
        // CHECK
        assertNull(category.getBudgetLimit());
    }

    @Test
    @DisplayName("Should throw exception when account ID is null")
    void testNullAccountId() {
        // BUILD
        String nullAccountId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Category(nullAccountId, "Food", CategoryType.EXPENSE, new Money(500))
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when account ID is empty")
    void testEmptyAccountId() {
        // BUILD
        String emptyAccountId = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Category(emptyAccountId, "Food", CategoryType.EXPENSE, new Money(500))
        );
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void testNullName() {
        // BUILD
        String nullName = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Category("acc123", nullName, CategoryType.EXPENSE, new Money(500))
        );
        assertEquals("Category name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void testEmptyName() {
        // BUILD
        String emptyName = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Category("acc123", emptyName, CategoryType.EXPENSE, new Money(500))
        );
        assertEquals("Category name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when type is null")
    void testNullType() {
        // BUILD
        CategoryType nullType = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Category("acc123", "Food", nullType, new Money(500))
        );
        assertEquals("Category type cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should update category name")
    void testUpdateName() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        String newName = "Groceries";
        LocalDateTime originalUpdatedAt = category.getUpdatedAt();
        
        // OPERATE
        category.update(newName, null, null);
        
        // CHECK
        assertEquals(newName, category.getName());
        assertTrue(category.getUpdatedAt().isAfter(originalUpdatedAt) || 
                   category.getUpdatedAt().isEqual(originalUpdatedAt));
    }

    @Test
    @DisplayName("Should update category type")
    void testUpdateType() {
        // BUILD
        Category category = new Category("acc123", "Salary", CategoryType.INCOME, null);
        CategoryType newType = CategoryType.SAVINGS;
        
        // OPERATE
        category.update(null, newType, null);
        
        // CHECK
        assertEquals(newType, category.getType());
    }

    @Test
    @DisplayName("Should update budget limit")
    void testUpdateBudgetLimit() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        Money newLimit = new Money(750);
        
        // OPERATE
        category.update(null, null, newLimit);
        
        // CHECK
        assertEquals(newLimit, category.getBudgetLimit());
    }

    @Test
    @DisplayName("Should update all fields at once")
    void testUpdateAllFields() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        String newName = "Dining";
        CategoryType newType = CategoryType.INCOME;
        Money newLimit = new Money(1000);
        
        // OPERATE
        category.update(newName, newType, newLimit);
        
        // CHECK
        assertEquals(newName, category.getName());
        assertEquals(newType, category.getType());
        assertEquals(newLimit, category.getBudgetLimit());
    }

    @Test
    @DisplayName("Should not update when all parameters are null")
    void testUpdateWithAllNulls() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        String originalName = category.getName();
        CategoryType originalType = category.getType();
        Money originalLimit = category.getBudgetLimit();
        
        // OPERATE
        category.update(null, null, null);
        
        // CHECK
        assertEquals(originalName, category.getName());
        assertEquals(originalType, category.getType());
        assertEquals(originalLimit, category.getBudgetLimit());
    }

    @Test
    @DisplayName("Should not update name when empty string provided")
    void testUpdateWithEmptyName() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        String originalName = category.getName();
        
        // OPERATE
        category.update("   ", null, null);
        
        // CHECK
        assertEquals(originalName, category.getName());
    }

    @Test
    @DisplayName("Should deactivate category")
    void testDeactivateCategory() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        assertTrue(category.isActive());
        
        // OPERATE
        category.deactivate();
        
        // CHECK
        assertFalse(category.isActive());
    }

    @Test
    @DisplayName("Should activate category")
    void testActivateCategory() {
        // BUILD
        Category category = new Category(
            "cat123", "acc123", "Food", CategoryType.EXPENSE, 
            new Money(500), false, LocalDateTime.now(), LocalDateTime.now()
        );
        assertFalse(category.isActive());
        
        // OPERATE
        category.activate();
        
        // CHECK
        assertTrue(category.isActive());
    }

    @Test
    @DisplayName("Should handle multiple deactivations")
    void testMultipleDeactivations() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        
        // OPERATE
        category.deactivate();
        category.deactivate();
        
        // CHECK
        assertFalse(category.isActive());
    }

    @Test
    @DisplayName("Should handle activation and deactivation toggle")
    void testActivationToggle() {
        // BUILD
        Category category = new Category("acc123", "Food", CategoryType.EXPENSE, new Money(500));
        
        // OPERATE
        category.deactivate();
        assertFalse(category.isActive());
        
        category.activate();
        assertTrue(category.isActive());
        
        category.deactivate();
        assertFalse(category.isActive());
        
        // CHECK - Final state
        assertFalse(category.isActive());
    }
}
