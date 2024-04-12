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

	public CheckingAccount(String pathToFile) {
		this.pathToFile = pathToFile;
		this.balance = 0;
	}

	public CheckingAccount(int balance, String pathToFile) {
		this.balance = balance;
		this.pathToFile = pathToFile;
	}

	public void deposit(int value) {
		// TODO CHECK FOR VALID INPUT
		// TODO check for max deposit
		this.balance += value;

		// Write to file
		WriteBalance();

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
		this.balance -= value;
		WriteBalance();

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
