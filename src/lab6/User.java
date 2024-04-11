package lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User {

	static final int MaxDepositPerDay = 5000;
	static final int MaxWithdrawPerDay = 500;
	static final int MaxTransferPerDay = 100;


	public CheckingAccount checkingAccount;
	public SavingsAccount savingsAccount;
	private String pathToFile;
	
	//New User
	public User(String username) {
		this.pathToFile = username;
		this.checkingAccount = new CheckingAccount(username);
		this.savingsAccount = new SavingsAccount();
		createUserFiles(pathToFile);
	}
	

	//Old user
	public User(String username,String pathToFile) {
	
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + username +".txt"), StandardCharsets.UTF_8)) {
			    if (line.contains("Checking Balance")) {
			    	//Get Balance
			    	String value= line.split(" ")[2];
			    	int balance=Integer.valueOf(value);
			    	this.checkingAccount= new CheckingAccount(balance,username);
			    }
			    if (line.contains("Savings Balance")) {
			    	//Get Balance 
			    	int balance=0;
			    	this.savingsAccount= new SavingsAccount();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void createUserFiles(String filename) {
		try {
			File myObj = new File("Users/" + filename +".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				FileWriter fr = new FileWriter(myObj, true);
				fr.write("Checking Balance: " + 0 + "\n");
				fr.write("Savings Balance: " + 0 + "\n");
				fr.close();
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
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
