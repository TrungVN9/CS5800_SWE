import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("Should create money with default currency")
    void testCreateMoneyWithDefaultCurrency() {
        // BUILD
        double amount = 100.50;
        
        // OPERATE
        Money money = new Money(amount);
        
        // CHECK
        assertEquals(100.50, money.getAmount(), 0.001);
        assertEquals("USD", money.getCurrency());
    }

    @Test
    @DisplayName("Should create money with custom currency")
    void testCreateMoneyWithCustomCurrency() {
        // BUILD
        double amount = 500.0;
        String currency = "EUR";
        
        // OPERATE
        Money money = new Money(amount, currency);
        
        // CHECK
        assertEquals(500.0, money.getAmount(), 0.001);
        assertEquals("EUR", money.getCurrency());
    }

    @Test
    @DisplayName("Should throw exception for negative amount")
    void testNegativeAmountThrowsException() {
        // BUILD
        double negativeAmount = -50.0;
        
        // OPERATE & CHECK
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Money(negativeAmount)
        );
        assertEquals("Amount cannot be negative", exception.getMessage());
    }

    @Test
    @DisplayName("Should accept zero amount")
    void testZeroAmountIsValid() {
        // BUILD
        double zeroAmount = 0.0;
        
        // OPERATE
        Money money = new Money(zeroAmount);
        
        // CHECK
        assertEquals(0.0, money.getAmount(), 0.001);
    }

    @Test
    @DisplayName("Should return true for equal money objects")
    void testMoneyEquality() {
        // BUILD
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(100.0, "USD");
        
        // OPERATE & CHECK
        assertTrue(money1.equals(money2));
    }

    @Test
    @DisplayName("Should return false for different amounts")
    void testMoneyInequalityDifferentAmounts() {
        // BUILD
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(200.0, "USD");
        
        // OPERATE & CHECK
        assertFalse(money1.equals(money2));
    }

    @Test
    @DisplayName("Should return false for different currencies")
    void testMoneyInequalityDifferentCurrencies() {
        // BUILD
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(100.0, "EUR");
        
        // OPERATE & CHECK
        assertFalse(money1.equals(money2));
    }
}