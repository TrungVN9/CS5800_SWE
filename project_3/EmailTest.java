import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Should create valid email")
    void testCreateValidEmail() {
        // BUILD
        String validEmail = "test@example.com";
        
        // OPERATE
        Email email = new Email(validEmail);
        
        // CHECK
        assertEquals("test@example.com", email.getValue());
    }

    @Test
    @DisplayName("Should convert email to lowercase")
    void testEmailConvertedToLowercase() {
        // BUILD
        String mixedCaseEmail = "Test@Example.COM";
        
        // OPERATE
        Email email = new Email(mixedCaseEmail);
        
        // CHECK
        assertEquals("test@example.com", email.getValue());
    }

    @Test
    @DisplayName("Should throw exception for null email")
    void testNullEmailThrowsException() {
        // BUILD
        String nullEmail = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(nullEmail)
        );
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty email")
    void testEmptyEmailThrowsException() {
        // BUILD
        String emptyEmail = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(emptyEmail)
        );
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for invalid email format")
    void testInvalidEmailFormatThrowsException() {
        // BUILD
        String invalidEmail = "notanemail";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(invalidEmail)
        );
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    @DisplayName("Should return true for equal emails")
    void testEmailEquality() {
        // BUILD
        Email email1 = new Email("test@example.com");
        Email email2 = new Email("test@example.com");
        
        // OPERATE & CHECK
        assertTrue(email1.equals(email2));
    }

    @Test
    @DisplayName("Should return false for different emails")
    void testEmailInequality() {
        // BUILD
        Email email1 = new Email("test1@example.com");
        Email email2 = new Email("test2@example.com");
        
        // OPERATE & CHECK
        assertFalse(email1.equals(email2));
    }
}
