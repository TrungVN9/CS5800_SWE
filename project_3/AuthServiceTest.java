import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Mock
    private ICustomerRepository customerRepository;
    
    @Mock
    private IAccountRepository accountRepository;
    
    @Mock
    private IPasswordHasher passwordHasher;
    
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(customerRepository, accountRepository, passwordHasher);
    }

    @Test
    @DisplayName("Should sign up new customer successfully")
    void testSignUpSuccess() {
        // BUILD
        String name = "John Doe";
        String email = "john@example.com";
        String password = "password123";
        String occupation = "Engineer";
        double salary = 75000;

        when(customerRepository.findByEmail(any(Email.class))).thenReturn(null);
        when(passwordHasher.hash(password)).thenReturn("hashed_password");
        
        // OPERATE
        Customer result = authService.signUp(name, email, password, occupation, salary);
        
        // CHECK
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(email, result.getEmail().getValue());
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    void testSignUpEmailAlreadyExists() {
        // BUILD
        Customer existingCustomer = new Customer(
            "Jane Doe",
            new Email("jane@example.com"),
            "hash",
            "Designer",
            new Money(60000)
        );
        
        when(customerRepository.findByEmail(any(Email.class))).thenReturn(existingCustomer);
        
        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> authService.signUp("Jane", "jane@example.com", "password123", "Designer", 60000)
        );
        assertEquals("Email already registered", exception.getMessage());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    @DisplayName("Should throw exception when password is too short")
    void testSignUpPasswordTooShort() {
        // BUILD
        String shortPassword = "short";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.signUp("John", "john@example.com", shortPassword, "Engineer", 50000)
        );
        assertEquals("Password must be at least 8 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is null")
    void testSignUpPasswordNull() {
        // BUILD
        String nullPassword = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.signUp("John", "john@example.com", nullPassword, "Engineer", 50000)
        );
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is empty")
    void testSignUpPasswordEmpty() {
        // BUILD
        String emptyPassword = "   ";
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.signUp("John", "john@example.com", emptyPassword, "Engineer", 50000)
        );
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should sign in with valid credentials")
    void testSignInSuccess() {
        // BUILD
        String email = "john@example.com";
        String password = "password123";
        Customer customer = new Customer(
            "John Doe",
            new Email(email),
            "hashed_password",
            "Engineer",
            new Money(75000)
        );
        
        when(customerRepository.findByEmail(any(Email.class))).thenReturn(customer);
        when(passwordHasher.verify(password, "hashed_password")).thenReturn(true);
        
        // OPERATE
        Customer result = authService.signIn(email, password);
        
        // CHECK
        assertNotNull(result);
        assertEquals(customer.getCustomerId(), result.getCustomerId());
        assertEquals(email, result.getEmail().getValue());
    }

    @Test
    @DisplayName("Should throw exception when email not found during sign in")
    void testSignInEmailNotFound() {
        // BUILD
        when(customerRepository.findByEmail(any(Email.class))).thenReturn(null);
        
        // OPERATE & CHECK
        SecurityException exception = assertThrows(
            SecurityException.class,
            () -> authService.signIn("notfound@example.com", "password123")
        );
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is incorrect during sign in")
    void testSignInIncorrectPassword() {
        // BUILD
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "hashed_password",
            "Engineer",
            new Money(75000)
        );
        
        when(customerRepository.findByEmail(any(Email.class))).thenReturn(customer);
        when(passwordHasher.verify("wrongpassword", "hashed_password")).thenReturn(false);
        
        // OPERATE & CHECK
        SecurityException exception = assertThrows(
            SecurityException.class,
            () -> authService.signIn("john@example.com", "wrongpassword")
        );
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when email is null during sign in")
    void testSignInNullEmail() {
        // BUILD
        String nullEmail = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.signIn(nullEmail, "password123")
        );
        assertEquals("Email and password are required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is null during sign in")
    void testSignInNullPassword() {
        // BUILD
        String nullPassword = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.signIn("john@example.com", nullPassword)
        );
        assertEquals("Email and password are required", exception.getMessage());
    }

    @Test
    @DisplayName("Should change password successfully")
    void testChangePasswordSuccess() {
        // BUILD
        String customerId = "cust123";
        String oldPassword = "oldpass123";
        String newPassword = "newpass123";
        Customer customer = new Customer(
            customerId,
            "John Doe",
            new Email("john@example.com"),
            "old_hashed_password",
            "Engineer",
            new Money(75000),
            LocalDateTime.now()
        );
        
        when(customerRepository.findById(customerId)).thenReturn(customer);
        when(passwordHasher.verify(oldPassword, "old_hashed_password")).thenReturn(true);
        when(passwordHasher.hash(newPassword)).thenReturn("new_hashed_password");
        
        // OPERATE
        authService.changePassword(customerId, oldPassword, newPassword);
        
        // CHECK
        verify(customerRepository, times(1)).save(argThat(c -> 
            c.getPasswordHash().equals("new_hashed_password")
        ));
    }

    @Test
    @DisplayName("Should throw exception when customer not found during password change")
    void testChangePasswordCustomerNotFound() {
        // BUILD
        when(customerRepository.findById("nonexistent")).thenReturn(null);
        
        // OPERATE & CHECK
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> authService.changePassword("nonexistent", "oldpass", "newpass123")
        );
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when old password is incorrect")
    void testChangePasswordIncorrectOldPassword() {
        // BUILD
        String customerId = "cust123";
        Customer customer = new Customer(
            customerId,
            "John Doe",
            new Email("john@example.com"),
            "old_hashed_password",
            "Engineer",
            new Money(75000),
            LocalDateTime.now()
        );
        
        when(customerRepository.findById(customerId)).thenReturn(customer);
        when(passwordHasher.verify("wrongold", "old_hashed_password")).thenReturn(false);
        
        // OPERATE & CHECK
        SecurityException exception = assertThrows(
            SecurityException.class,
            () -> authService.changePassword(customerId, "wrongold", "newpass123")
        );
        assertEquals("Old password is incorrect", exception.getMessage());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    @DisplayName("Should throw exception when new password is too short")
    void testChangePasswordNewPasswordTooShort() {
        // BUILD
        String customerId = "cust123";
        Customer customer = new Customer(
            customerId,
            "John Doe",
            new Email("john@example.com"),
            "old_hashed_password",
            "Engineer",
            new Money(75000),
            LocalDateTime.now()
        );
        
        when(customerRepository.findById(customerId)).thenReturn(customer);
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.changePassword(customerId, "oldpass", "short")
        );
        assertEquals("Password must be at least 8 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when customer ID is null")
    void testChangePasswordNullCustomerId() {
        // BUILD
        String nullCustomerId = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.changePassword(nullCustomerId, "oldpass", "newpass123")
        );
        assertEquals("Customer ID is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when new password is null")
    void testChangePasswordNullNewPassword() {
        // BUILD
        String customerId = "cust123";
        String nullPassword = null;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> authService.changePassword(customerId, "oldpass", nullPassword)
        );
        assertEquals("New password cannot be empty", exception.getMessage());
    }
}