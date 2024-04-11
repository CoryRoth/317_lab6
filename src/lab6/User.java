package lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class User {

	static final int MaxDepositPerDay = 5000;
	static final int MaxWithdrawPerDay = 500;
	static final int MaxTransferPerDay = 100;


	public CheckingAccount checkingAccount;
	public SavingsAccount savingsAccount;
	private String pathToFile;
	
	//New User
	public User(String username) {
		this.checkingAccount = new CheckingAccount();
		this.savingsAccount = new SavingsAccount();
		this.pathToFile = username;
		createUserFiles(pathToFile);
	}
	
	private void createUserFiles(String filename) {
		try {
			File myObj = new File("Users/" + filename +".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	//Old user
	public User(String username,String pathToFile) {
		
	}
	
	
	public void transfer(int amount) {
		
	}
	
	public void depositChecking(int amount) {
		
	}
	public void depositSaving(int amount) {
		
	}
	
	public void withdraw(int amount) {
		
	}
	
}
