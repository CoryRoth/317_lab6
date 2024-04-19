package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
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
	void isNewUserFalseWhenUserExistsTest() {
		new User("coryroth");
		boolean userIsNew = User.isNewUser("coryroth");
		assertFalse(userIsNew);
	}
	
	@Test
	void isNewUserTrueWhenUserDoesNotExistTest()
	{
		boolean userIsNew = User.isNewUser("NotAUsedUserName");
		assertTrue(userIsNew);
	}
	
	@Test
	void transferValidAmountToSavings() {
		testUser.checkingAccount.deposit(200);
		int startingBalance = testUser.savingsAccount.getBalance();
		testUser.transfer(50, true);
		int newBalance = testUser.savingsAccount.getBalance();
		assertEquals(newBalance, startingBalance + 50);
	}
	
	@Test
	void transferValidAmountToCheckings() {
		testUser.savingsAccount.deposit(200);
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.transfer(50, false);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance + 50);
	}
	
	@Test
	void transferMaxAmountToChecking() {
		testUser.savingsAccount.deposit(SavingsAccount.MaxTransferPerDay);
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.transfer(SavingsAccount.MaxTransferPerDay, false);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance + SavingsAccount.MaxTransferPerDay);
	}
	
	@Test
	void transferOverMaxToCheckingFails() {
		testUser.savingsAccount.deposit(SavingsAccount.MaxTransferPerDay + 5);
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.transfer(SavingsAccount.MaxTransferPerDay + 1, false);
		int newBalance = testUser.checkingAccount.getBalance();
		assertEquals(newBalance, startingBalance);
	}
	
	@Test
	void transferFromCheckingOverdraftFails() {
		EmptyCheckings();
		testUser.checkingAccount.deposit(100);
		int startingBalance = testUser.checkingAccount.getBalance();
		testUser.transfer(101, true);
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
