package lab6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount {

	static final int MaxDepositPerDay = 5000;
	static final int MaxTransferPerDay = 100;

	private int balance;
	private String pathToFile;
	private int BalanceForDay;
	private int TransferForDay;

	// New Account
	public SavingsAccount(String pathToFile) {
		this.pathToFile = pathToFile;
		this.balance = 0;
		this.BalanceForDay = 0;
		this.TransferForDay = 0;
	}

	// Old Account
	public SavingsAccount(int balance, String pathToFile) {
		this.balance = balance;
		this.pathToFile = pathToFile;
		this.BalanceForDay = getBalanceForDay();
		this.TransferForDay = getTransferForDay();
	}

	private int getTransferForDay() {
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("TransferDay Amount")) {
					// Get Balance
					String value = line.split(" ")[2];
					int balance = Integer.valueOf(value);
					return balance;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int getBalanceForDay() {
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("SavingsDay Balance")) {
					// Get Balance
					String value = line.split(" ")[2];
					int balance = Integer.valueOf(value);
					return balance;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void newDay() {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("CheckingDay Balance")) {
					newLines.add("CheckingDay Balance: " + 0);
				} else if (line.contains("CheckingDay Withdraw")) {
					newLines.add("CheckingDay Withdraw: " + 0);
				} else if (line.contains("SavingsDay Balance")) {
					newLines.add("SavingsDay Balance: " + 0);
				} else if (line.contains("TransferDay Amount")) {
					newLines.add("TransferDay Amount: " + 0);
				} else {
					newLines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get("Users/" + pathToFile + ".txt"), newLines, StandardCharsets.UTF_8);
			BalanceForDay = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deposit(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO check for max deposit
		if (BalanceForDay + value <= MaxDepositPerDay) {
			this.balance += value;
			BalanceForDay += value;

			// Write to file
			WriteBalance(value);
		} else {
			System.out.println("Cannot Deposit, Hit Limit on Deposit For Day");
		}

	}

	private void WriteBalance(int value) {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("Savings Balance")) {
					newLines.add("Savings Balance: " + balance);
				} else if (line.contains("SavingsDay Balance")) {
					String value2 = line.split(" ")[2];
					int balance2 = Integer.valueOf(value2);
					int output = balance2 + value;
					newLines.add("SavingsDay Balance: " + output);
				} else {
					newLines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get("Users/" + pathToFile + ".txt"), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getBalance() {
		return this.balance;
	}

	public int transfer(int amount) {

		if (TransferForDay + amount <= MaxTransferPerDay) {
			if (balance - amount < 0) {
				return -1;
			}
			this.balance += amount;
			TransferForDay += amount;
			WriteBalance(amount, true);
			return 0;
		} else {
			return -2;
		}

	}

	// Balance for Transfer
	private void WriteBalance(int amount, Boolean transfer) {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("Savings Balance")) {
					newLines.add("Savings Balance: " + balance);
				} else if (line.contains("TransferDay Amount")) {
					String value2 = line.split(" ")[2];
					int balance2 = Integer.valueOf(value2);
					int output = balance2 + amount;
					newLines.add("TransferDay Amount: " + output);
				} else {
					newLines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get("Users/" + pathToFile + ".txt"), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
