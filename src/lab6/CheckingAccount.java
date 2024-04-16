package lab6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CheckingAccount {

	static final int MaxDepositPerDay = 5000;
	static final int MaxWithdrawPerDay = 500;

	private int balance;
	private String pathToFile;
	private int BalanceForDay;
	private int WithdrawForDay;

	public CheckingAccount(String pathToFile) {
		this.pathToFile = pathToFile;
		this.balance = 0;
		this.BalanceForDay = 0;
		this.WithdrawForDay = 0;
	}

	public CheckingAccount(int balance, String pathToFile) {
		this.balance = balance;
		this.pathToFile = pathToFile;
		this.BalanceForDay = getBalanceForDay();
		this.WithdrawForDay = getWithdrawForDay();
	}

	private int getWithdrawForDay() {
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("CheckingDay Withdraw")) {
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
				if (line.contains("CheckingDay Balance")) {
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

	public void deposit(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO check for max deposit
		if (BalanceForDay + value <= MaxDepositPerDay) {
			this.balance += value;

			// Write to file
			WriteBalance(value, false);
		} else {
			System.out.println("Cannot Deposit, Hit Limit on Deposit For Day");
		}

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
			this.BalanceForDay = 0;
			this.WithdrawForDay = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void WriteBalance(int value, Boolean withdraw) {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("Checking Balance")) {
					newLines.add("Checking Balance: " + balance);
				} else if (line.contains("CheckingDay Balance") && !withdraw) {
					String value2 = line.split(" ")[2];
					int balance2 = Integer.valueOf(value2);
					int output = balance2 + value;
					newLines.add("CheckingDay Balance: " + output);
				} else if (line.contains("CheckingDay Withdraw") && withdraw) {
					String value2 = line.split(" ")[2];
					int balance2 = Integer.valueOf(value2);
					int output = balance2 + value;
					newLines.add("CheckingDay Withdraw: " + output);
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

	private void WriteBalance() {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("Checking Balance")) {
					newLines.add("Checking Balance: " + balance);
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

	public void withdraw(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO check for max deposit
		if (WithdrawForDay + value <= MaxWithdrawPerDay) {
			this.balance -= value;
			WriteBalance(value, true);
		} else {
			System.out.println("Cannot Withdraw, Hit Limit on Deposit For Day");
		}

	}

	public void transfer(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO DO NOT UPDATE MAX BALANCE
		this.balance += value;
		WriteBalance();

	}

	public void payBill(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO DO NOT UPDATE MAX Withdraw
		this.balance -= value;
		WriteBalance();

	}

	public int getBalance() {
		return this.balance;
	}

}
