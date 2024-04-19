package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingsAccountTest {

private User testUser;
	
	@BeforeEach
	void Initialize()
	{
		if(testUser != null)
		{
			return;
		}
		if(User.isNewUser("testguy"))
		{
			testUser = new User("testguy");
		}
		else
		{
			testUser = new User("testguy", "testguy");
		}
	}
	
	@BeforeEach
	void ResetDay()
	{
		testUser.savingsAccount.newDay();
	}
	
	@Test
	void depositValidAmount() {
		int startingBalance = testUser.savingsAccount.getBalance();
		testUser.savingsAccount.deposit(50);
		int newBalance = testUser.savingsAccount.getBalance();
		assertEquals(newBalance, startingBalance + 50);
	}
	
	@Test
	void depositMaxAmount() {
		int startingBalance = testUser.savingsAccount.getBalance();
		testUser.savingsAccount.deposit(SavingsAccount.MaxDepositPerDay);
		int newBalance = testUser.savingsAccount.getBalance();
		assertEquals(newBalance, startingBalance + SavingsAccount.MaxDepositPerDay);
	}
	
	@Test
	void depositOverMaxAmountFailsSingleTransaction() {
		int startingBalance = testUser.savingsAccount.getBalance();
		testUser.savingsAccount.deposit(SavingsAccount.MaxDepositPerDay + 1);
		int newBalance = testUser.savingsAccount.getBalance();
		assertEquals(newBalance, startingBalance);
	}
	
	@Test
	void depositOverMaxAmountFailsMultipleTransactions() {
		int startingBalance = testUser.savingsAccount.getBalance();
		testUser.savingsAccount.deposit(SavingsAccount.MaxDepositPerDay/2);
		testUser.savingsAccount.deposit(SavingsAccount.MaxDepositPerDay/2);
		testUser.savingsAccount.deposit(1);
		int newBalance = testUser.savingsAccount.getBalance();
		assertEquals(newBalance, startingBalance + SavingsAccount.MaxDepositPerDay);
	}

}
