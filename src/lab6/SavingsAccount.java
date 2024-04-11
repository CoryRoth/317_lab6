package lab6;

public class SavingsAccount {

	static final int MaxDepositPerDay = 5000;
	static final int MaxTransferPerDay = 100;
	
	private int balance;
	private int accountNumber;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public SavingsAccount() {
		this.balance=0;
	}
	public SavingsAccount(int balance) {
		this.balance=balance;
	}

	public void deposit(int value) {
		
		//TODO CHECK FOR VALID INPUT
		//TODO check for max deposit
		this.balance += value;

	}
	
	public int getBalance() {
		return this.balance;
	}

}
