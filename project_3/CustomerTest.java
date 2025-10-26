import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("Should create customer with all required fields")
    void testCreateCustomer() {
        // BUILD
        String name = "John Doe";
        Email email = new Email("john@example.com");
        String passwordHash = "hashed_password";
        String occupation = "Engineer";
        Money salary = new Money(75000);
        
        // OPERATE
        Customer customer = new Customer(name, email, passwordHash, occupation, salary);
        
        // CHECK
        assertNotNull(customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertEquals(email, customer.getEmail());
        assertEquals("hashed_password", customer.getPasswordHash());
        assertEquals("Engineer", customer.getOccupation());
        assertEquals(salary, customer.getSalary());
        assertNotNull(customer.getCreatedAt());
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void testNullNameThrowsException() {
        // BUILD
        String nullName = null;
        Email email = new Email("john@example.com");
        String passwordHash = "hashed_password";
        
        // OPERATE & CHECK
        assertThrows(NullPointerException.class, () -> 
            new Customer(nullName, email, passwordHash, "Engineer", new Money(75000))
        );
    }

    @Test
    @DisplayName("Should throw exception when email is null")
    void testNullEmailThrowsException() {
        // BUILD
        Email nullEmail = null;
        
        // OPERATE & CHECK
        assertThrows(NullPointerException.class, () -> 
            new Customer("John Doe", nullEmail, "hash", "Engineer", new Money(75000))
        );
    }

    @Test
    @DisplayName("Should update profile with new values")
    void testUpdateProfile() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "hash",
            "Engineer",
            new Money(75000)
        );
        
        String newName = "Jane Doe";
        Email newEmail = new Email("jane@example.com");
        String newOccupation = "Manager";
        Money newSalary = new Money(90000);
        
        // OPERATE
        customer.updateProfile(newName, newEmail, newOccupation, newSalary);
        
        // CHECK
        assertEquals("Jane Doe", customer.getName());
        assertEquals(newEmail, customer.getEmail());
        assertEquals("Manager", customer.getOccupation());
        assertEquals(newSalary, customer.getSalary());
    }

    @Test
    @DisplayName("Should keep old values when updating with nulls")
    void testUpdateProfileWithNulls() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "hash",
            "Engineer",
            new Money(75000)
        );
        String originalName = customer.getName();
        Email originalEmail = customer.getEmail();
        
        // OPERATE
        customer.updateProfile(null, null, "Manager", null);
        
        // CHECK
        assertEquals(originalName, customer.getName());
        assertEquals(originalEmail, customer.getEmail());
        assertEquals("Manager", customer.getOccupation());
    }

    @Test
    @DisplayName("Should change password successfully")
    void testChangePassword() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "old_hash",
            "Engineer",
            new Money(75000)
        );
        String newPasswordHash = "new_hash";
        
        // OPERATE
        customer.changePassword(newPasswordHash);
        
        // CHECK
        assertEquals("new_hash", customer.getPasswordHash());
    }

    @Test
    @DisplayName("Should throw exception when changing to null password")
    void testChangePasswordWithNull() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "old_hash",
            "Engineer",
            new Money(75000)
        );
        
        // OPERATE & CHECK
        assertThrows(IllegalArgumentException.class, () -> 
            customer.changePassword(null)
        );
    }

    @Test
    @DisplayName("Should throw exception when changing to empty password")
    void testChangePasswordWithEmpty() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "old_hash",
            "Engineer",
            new Money(75000)
        );
        
        // OPERATE & CHECK
        assertThrows(IllegalArgumentException.class, () -> 
            customer.changePassword("   ")
        );
    }
}
