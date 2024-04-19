package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckingAccountTest {
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
		testUser.checkingAccount.newDay();
	}
	
	@Test
	void depositValidAmount() {
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.checkingAccount.deposit(50);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance + 50);
	}
	
	@Test
	void depositMaxAmount() {
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.checkingAccount.deposit(CheckingAccount.MaxDepositPerDay);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance + CheckingAccount.MaxDepositPerDay);
	}
	
	@Test
	void depositOverMaxAmountFailsSingleTransaction() {
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.checkingAccount.deposit(CheckingAccount.MaxDepositPerDay + 1);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance);
	}
	
	@Test
	void depositOverMaxAmountFailsMultipleTransactions() {
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.checkingAccount.deposit(CheckingAccount.MaxDepositPerDay/2);
		testUser.checkingAccount.deposit(CheckingAccount.MaxDepositPerDay/2);
		testUser.checkingAccount.deposit(1);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance + CheckingAccount.MaxDepositPerDay);
	}
	
	@Test
	void overdraftFails() {
		EmptyCheckings();
		testUser.checkingAccount.deposit(100);
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.checkingAccount.withdraw(101);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance);
	}
	
	private void EmptyCheckings()
	{
		int balance = testUser.checkingAccount.getBalance();
		
		int withdrawsNeeded = balance/CheckingAccount.MaxWithdrawPerDay;
		for(int i = 0; i < withdrawsNeeded; i++)
		{
			testUser.checkingAccount.newDay();
			testUser.checkingAccount.withdraw(CheckingAccount.MaxWithdrawPerDay);
		}
		testUser.checkingAccount.newDay();
		testUser.checkingAccount.withdraw(testUser.checkingAccount.getBalance());
	}

}
