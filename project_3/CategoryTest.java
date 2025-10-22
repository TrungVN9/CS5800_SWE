import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CategoryTest {

    private Account account;

    @BeforeEach
    void setUp() {
        // BUILD
        account = new Account();
    }

    @Test
    @DisplayName("Should create category successfully when data is valid")
    void addCategory_WithValidData_ShouldCreateSuccessfully() {
        // BUILD
        String categoryName = "Groceries";
        String description = "Daily grocery and food items";

        // OPERATE
        Category category = account.addCategory(categoryName, description);

        // CHECK
        assertNotNull(category, "Category should be created successfully");
        assertEquals(categoryName, category.getName());
        assertEquals(description, category.getDescription());
        assertTrue(account.getCategories().contains(category), "Account should contain the new category");
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when adding category with same name twice")
    void addCategory_WithDuplicateName_ShouldThrowDuplicateException() {
        // BUILD
        String categoryName = "Utilities";
        account.addCategory(categoryName, "Monthly utility bills");

        // OPERATE & CHECK
        assertThrows(DuplicateResourceException.class, () -> {
            account.addCategory(categoryName, "Duplicate category entry");
        }, "Adding duplicate category name should throw DuplicateResourceException");
    }

    @Test
    @DisplayName("Should update category successfully with valid data")
    void updateCategory_WithValidData_ShouldUpdateSuccessfully() {
        // BUILD
        Category category = account.addCategory("Transport", "Public and private transport expenses");

        String newName = "Transportation";
        String newDescription = "All transportation-related expenses";

        // OPERATE
        boolean result = account.updateCategory(category, newName, newDescription);

        // CHECK
        assertTrue(result, "Category should be updated successfully");
        assertEquals(newName, category.getName());
        assertEquals(newDescription, category.getDescription());
    }

    @Test
    @DisplayName("Should delete category successfully when it has no linked expenses")
    void deleteCategory_WithNoLinkedExpenses_ShouldDeleteSuccessfully() {
        // BUILD
        Category category = account.addCategory("Subscriptions", "Monthly entertainment subscriptions");

        // OPERATE
        boolean result = account.deleteCategory(category);

        // CHECK
        assertTrue(result, "Category should be deleted successfully");
        assertFalse(account.getCategories().contains(category), "Deleted category should not exist in account");
    }

    @Test
    @DisplayName("Should throw DependencyException when deleting category with linked expenses")
    void deleteCategory_WithLinkedExpenses_ShouldThrowDependencyException() {
        // BUILD
        Category category = account.addCategory("Dining", "Restaurant and cafe expenses");
        Expense expense = new Expense("Dinner with friends", 75.0, "2025-03-10");
        expense.setCategory(category);
        account.addExpense(expense);

        // OPERATE & CHECK
        assertThrows(DependencyException.class, () -> {
            account.deleteCategory(category);
        }, "Deleting a category with linked expenses should throw DependencyException");
    }

    // Optional edge test: invalid name
    @Test
    @DisplayName("Should throw ValidationException when adding category with empty name")
    void addCategory_WithEmptyName_ShouldThrowValidationException() {
        // BUILD
        String invalidName = "";
        String description = "Invalid category with no name";

        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> {
            account.addCategory(invalidName, description);
        }, "Empty category name should not be allowed");
    }
}
