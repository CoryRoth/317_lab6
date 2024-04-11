package lab6;

public class CheckingAccount {

	static final int MaxDepositPerDay = 5000;
	static final int MaxWithdrawPerDay = 500;
	
	private int balance;
	private int accountNumber;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public CheckingAccount() {
		this.balance=0;
	}

	public void deposit(int value) {
		
		//TODO CHECK FOR VALID INPUT
		//TODO check for max deposit
		this.balance += value;

	}
	
	public void withdraw (int value) {
		
		//TODO CHECK FOR VALID INPUT
		//TODO check for max deposit
		this.balance -= value;
		
	}
	
	public int getBalance() {
		return this.balance;
	}

}
