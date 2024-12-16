package CS6371;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TDD_23241233 {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("12345", 100.0);
    }

    // Requirement 1: Account Creation
    @Test
    void testAccountCreationWithPositiveInitialBalance() {
        BankAccount newAccount = new BankAccount("67890", 200.0);
        assertNotNull(newAccount);
        assertEquals(200.0, newAccount.getBalance());
    }

    @Test
    void testAccountCreationWithNegativeInitialBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("67890", -200.0);
        });
        assertEquals("Initial balance must be positive", exception.getMessage());
    }

    @Test
    void testPreventDuplicateAccountCreation() {
        BankAccount newAccount = new BankAccount("12345", 200.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            newAccount = new BankAccount("12345", 200.0);
        });
        assertEquals("Account already exists.", exception.getMessage());
    }

    // Requirement 2: Deposit
    @Test
    void testDepositPositiveAmount() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }

    // Requirement 3: Withdrawal
    @Test
    void testWithdrawValidAmount() {
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
        assertEquals("Withdrawal amount must be positive.", exception.getMessage());
    }

    // Requirement 4: Overdraft Protection
    @Test
    void testWithdrawMoreThanBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertEquals("Insufficient funds.", exception.getMessage());
    }

    // Requirement 5: Balance Inquiry
    @Test
    void testBalanceInquiry() {
        assertEquals(100.0, account.getBalance());
    }
}