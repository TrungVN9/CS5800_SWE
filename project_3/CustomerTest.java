import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.Resource.DuplicateResourceException;
import Exceptions.Validation.ValidationException;
import Exceptions.Authentication.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    // ---------------- SIGN UP TESTS ----------------

    @Test
    @DisplayName("Should create account successfully when valid signup data is provided")
    void signUp_WithValidData_ShouldCreateAccount() {
        // BUILD
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "StrongPass123!";
        String occupation = "Software Engineer";
        double salary = 75000.0;

        // OPERATE
        boolean result = customer.signUp(name, email, password, occupation, salary);

        // CHECK
        assertAll("Valid signup assertions",
            () -> assertTrue(result),
            () -> assertEquals(name, customer.getName()),
            () -> assertEquals(email, customer.getEmail()),
            () -> assertNotEquals(password, customer.getPasswordHash(), "Password should be securely hashed"),
            () -> assertEquals(occupation, customer.getOccupation()),
            () -> assertEquals(salary, customer.getSalary())
        );
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when email already exists")
    void signUp_WithExistingEmail_ShouldThrowDuplicateResourceException() {
        // BUILD
        String email = "existing@example.com";

        // OPERATE & CHECK
        assertThrows(DuplicateResourceException.class, () -> 
            customer.signUp("John Doe", email, "Password123!", "Engineer", 80000.0)
        );
    }

    @Test
    @DisplayName("Should throw ValidationException when email format is invalid")
    void signUp_WithInvalidEmail_ShouldThrowValidationException() {
        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> 
            customer.signUp("John Doe", "invalid-email", "Password123!", "Engineer", 80000.0)
        );
    }

    @Test
    @DisplayName("Should throw ValidationException when password is weak")
    void signUp_WithWeakPassword_ShouldThrowValidationException() {
        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> 
            customer.signUp("John Doe", "john@example.com", "weak", "Engineer", 80000.0)
        );
    }

    // ---------------- SIGN IN TESTS ----------------

    @Test
    @DisplayName("Should authenticate successfully with valid credentials")
    void signIn_WithValidCredentials_ShouldAuthenticate() {
        // BUILD
        String email = "john@example.com";
        String password = "ValidPass123!";

        // OPERATE
        boolean result = customer.signIn(email, password);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should throw AuthenticationException when invalid credentials are used")
    void signIn_WithInvalidCredentials_ShouldThrowAuthenticationException() {
        // OPERATE & CHECK
        assertThrows(AuthenticationException.class, () -> 
            customer.signIn("wrong@example.com", "WrongPass123!")
        );
    }

    // ---------------- PROFILE UPDATE TESTS ----------------

    @Test
    @DisplayName("Should update customer profile successfully with valid data")
    void updateProfile_WithValidData_ShouldUpdateSuccessfully() {
        // BUILD
        String newName = "John Updated";
        String newOccupation = "Senior Engineer";
        double newSalary = 85000.0;

        // OPERATE
        boolean result = customer.updateProfile(newName, newOccupation, newSalary);

        // CHECK
        assertAll("Profile update assertions",
            () -> assertTrue(result),
            () -> assertEquals(newName, customer.getName()),
            () -> assertEquals(newOccupation, customer.getOccupation()),
            () -> assertEquals(newSalary, customer.getSalary())
        );
    }

    @Test
    @DisplayName("Should throw ValidationException when salary is negative")
    void updateProfile_WithInvalidSalary_ShouldThrowValidationException() {
        // OPERATE & CHECK
        assertThrows(ValidationException.class, () -> 
            customer.updateProfile("John Doe", "Engineer", -5000.0)
        );
    }

    // ---------------- DASHBOARD VIEW TESTS ----------------

    @Test
    @DisplayName("Should return a populated Dashboard object when viewing dashboard")
    void viewDashboard_ShouldReturnDashboardData() {
        // OPERATE
        Dashboard dashboard = customer.viewDashboard();

        // CHECK
        assertAll("Dashboard data assertions",
            () -> assertNotNull(dashboard),
            () -> assertNotNull(dashboard.getTotals()),
            () -> assertNotNull(dashboard.getCharts())
        );
    }
}
