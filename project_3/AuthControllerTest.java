import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;
    
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    @DisplayName("Should return success response when sign up is successful")
    void testSignUpSuccess() {
        // BUILD
        SignUpRequest request = new SignUpRequest(
            "John Doe",
            "john@example.com",
            "password123",
            "Engineer",
            75000
        );
        
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "hashed_password",
            "Engineer",
            new Money(75000)
        );
        
        when(authService.signUp(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            request.getOccupation(),
            request.getSalary()
        )).thenReturn(customer);
        
        // OPERATE
        Response<CustomerDto> response = authController.signUp(request);
        
        // CHECK
        assertTrue(response.isSuccess());
        assertEquals("Account created successfully", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("john@example.com", response.getData().getEmail());
        assertEquals("John Doe", response.getData().getName());
    }

    @Test
    @DisplayName("Should return failure response when request is null")
    void testSignUpNullRequest() {
        // BUILD
        SignUpRequest nullRequest = null;
        
        // OPERATE
        Response<CustomerDto> response = authController.signUp(nullRequest);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Request cannot be null", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return failure response when validation fails")
    void testSignUpValidationError() {
        // BUILD
        SignUpRequest request = new SignUpRequest(
            "John Doe",
            "john@example.com",
            "short",
            "Engineer",
            75000
        );
        
        when(authService.signUp(
            anyString(), anyString(), anyString(), anyString(), anyDouble()
        )).thenThrow(new IllegalArgumentException("Password must be at least 8 characters"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signUp(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertTrue(response.getMessage().contains("Validation error"));
        assertTrue(response.getMessage().contains("Password must be at least 8 characters"));
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return failure response when email already exists")
    void testSignUpEmailExists() {
        // BUILD
        SignUpRequest request = new SignUpRequest(
            "John Doe",
            "john@example.com",
            "password123",
            "Engineer",
            75000
        );
        
        when(authService.signUp(
            anyString(), anyString(), anyString(), anyString(), anyDouble()
        )).thenThrow(new IllegalStateException("Email already registered"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signUp(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Email already registered", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return generic error for unexpected exceptions")
    void testSignUpUnexpectedException() {
        // BUILD
        SignUpRequest request = new SignUpRequest(
            "John Doe",
            "john@example.com",
            "password123",
            "Engineer",
            75000
        );
        
        when(authService.signUp(
            anyString(), anyString(), anyString(), anyString(), anyDouble()
        )).thenThrow(new RuntimeException("Database error"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signUp(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("An error occurred during sign up", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return success response when sign in is successful")
    void testSignInSuccess() {
        // BUILD
        SignInRequest request = new SignInRequest("john@example.com", "password123");
        
        Customer customer = new Customer(
            "John Doe",
            new Email("john@example.com"),
            "hashed_password",
            "Engineer",
            new Money(75000)
        );
        
        when(authService.signIn(request.getEmail(), request.getPassword())).thenReturn(customer);
        
        // OPERATE
        Response<CustomerDto> response = authController.signIn(request);
        
        // CHECK
        assertTrue(response.isSuccess());
        assertEquals("Signed in successfully", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("john@example.com", response.getData().getEmail());
    }

    @Test
    @DisplayName("Should return failure response when sign in request is null")
    void testSignInNullRequest() {
        // BUILD
        SignInRequest nullRequest = null;
        
        // OPERATE
        Response<CustomerDto> response = authController.signIn(nullRequest);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Request cannot be null", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return failure response when credentials are invalid")
    void testSignInInvalidCredentials() {
        // BUILD
        SignInRequest request = new SignInRequest("john@example.com", "wrongpassword");
        
        when(authService.signIn(anyString(), anyString()))
            .thenThrow(new SecurityException("Invalid email or password"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signIn(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Invalid email or password", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return failure response for validation error during sign in")
    void testSignInValidationError() {
        // BUILD
        SignInRequest request = new SignInRequest("", "password123");
        
        when(authService.signIn(anyString(), anyString()))
            .thenThrow(new IllegalArgumentException("Email and password are required"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signIn(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertTrue(response.getMessage().contains("Validation error"));
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return generic error for unexpected sign in exceptions")
    void testSignInUnexpectedException() {
        // BUILD
        SignInRequest request = new SignInRequest("john@example.com", "password123");
        
        when(authService.signIn(anyString(), anyString()))
            .thenThrow(new RuntimeException("Database connection failed"));
        
        // OPERATE
        Response<CustomerDto> response = authController.signIn(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("An error occurred during sign in", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("Should return success response when password change is successful")
    void testChangePasswordSuccess() {
        // BUILD
        ChangePasswordRequest request = new ChangePasswordRequest(
            "cust123",
            "oldpass123",
            "newpass123"
        );
        
        doNothing().when(authService).changePassword(
            request.getCustomerId(),
            request.getOldPassword(),
            request.getNewPassword()
        );
        
        // OPERATE
        Response<String> response = authController.changePassword(request);
        
        // CHECK
        assertTrue(response.isSuccess());
        assertEquals("Password changed successfully", response.getMessage());
    }

    @Test
    @DisplayName("Should return failure response when change password request is null")
    void testChangePasswordNullRequest() {
        // BUILD
        ChangePasswordRequest nullRequest = null;
        
        // OPERATE
        Response<String> response = authController.changePassword(nullRequest);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Request cannot be null", response.getMessage());
    }

    @Test
    @DisplayName("Should return failure response when old password is incorrect")
    void testChangePasswordIncorrectOldPassword() {
        // BUILD
        ChangePasswordRequest request = new ChangePasswordRequest(
            "cust123",
            "wrongold",
            "newpass123"
        );
        
        doThrow(new SecurityException("Old password is incorrect"))
            .when(authService).changePassword(anyString(), anyString(), anyString());
        
        // OPERATE
        Response<String> response = authController.changePassword(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Old password is incorrect", response.getMessage());
    }

    @Test
    @DisplayName("Should return failure response when customer not found")
    void testChangePasswordCustomerNotFound() {
        // BUILD
        ChangePasswordRequest request = new ChangePasswordRequest(
            "nonexistent",
            "oldpass123",
            "newpass123"
        );
        
        doThrow(new IllegalStateException("Customer not found"))
            .when(authService).changePassword(anyString(), anyString(), anyString());
        
        // OPERATE
        Response<String> response = authController.changePassword(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    @DisplayName("Should return failure response for validation error during password change")
    void testChangePasswordValidationError() {
        // BUILD
        ChangePasswordRequest request = new ChangePasswordRequest(
            "cust123",
            "oldpass123",
            "short"
        );
        
        doThrow(new IllegalArgumentException("Password must be at least 8 characters"))
            .when(authService).changePassword(anyString(), anyString(), anyString());
        
        // OPERATE
        Response<String> response = authController.changePassword(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertTrue(response.getMessage().contains("Validation error"));
        assertTrue(response.getMessage().contains("Password must be at least 8 characters"));
    }

    @Test
    @DisplayName("Should return generic error for unexpected password change exceptions")
    void testChangePasswordUnexpectedException() {
        // BUILD
        ChangePasswordRequest request = new ChangePasswordRequest(
            "cust123",
            "oldpass123",
            "newpass123"
        );
        
        doThrow(new RuntimeException("System error"))
            .when(authService).changePassword(anyString(), anyString(), anyString());
        
        // OPERATE
        Response<String> response = authController.changePassword(request);
        
        // CHECK
        assertFalse(response.isSuccess());
        assertEquals("An error occurred while changing password", response.getMessage());
    }
}