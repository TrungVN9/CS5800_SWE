import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    @DisplayName("Should create active account with generated ID")
    void testCreateAccount() {
        // BUILD
        String customerId = "cust123";
        
        // OPERATE
        Account account = new Account(customerId);
        
        // CHECK
        assertNotNull(account.getAccountId());
        assertEquals("cust123", account.getCustomerId());
        assertTrue(account.isActive());
        assertNotNull(account.getCreatedDate());
    }

    @Test
    @DisplayName("Should throw exception when customer ID is null")
    void testNullCustomerIdThrowsException() {
        // BUILD
        String nullCustomerId = null;
        
        // OPERATE & CHECK
        assertThrows(NullPointerException.class, () -> 
            new Account(nullCustomerId)
        );
    }

    @Test
    @DisplayName("Should deactivate account")
    void testDeactivateAccount() {
        // BUILD
        Account account = new Account("cust123");
        assertTrue(account.isActive());
        
        // OPERATE
        account.deactivate();
        
        // CHECK
        assertFalse(account.isActive());
    }

    @Test
    @DisplayName("Should activate account")
    void testActivateAccount() {
        // BUILD
        Account account = new Account(
            "acc123",
            "cust123",
            LocalDateTime.now(),
            false
        );
        assertFalse(account.isActive());
        
        // OPERATE
        account.activate();
        
        // CHECK
        assertTrue(account.isActive());
    }

    @Test
    @DisplayName("Should handle multiple deactivations")
    void testMultipleDeactivations() {
        // BUILD
        Account account = new Account("cust123");
        
        // OPERATE
        account.deactivate();
        account.deactivate();
        
        // CHECK
        assertFalse(account.isActive());
    }
}