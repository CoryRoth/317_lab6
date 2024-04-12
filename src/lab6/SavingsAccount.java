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

	// New Account
	public SavingsAccount(String pathToFile) {
		this.pathToFile = pathToFile;
		this.balance = 0;
	}

	// Old Account
	public SavingsAccount(int balance, String pathToFile) {
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

	public int getBalance() {
		return this.balance;
	}

	public void transfer(int amount) {
		// TODO CHECK FOR VALID INPUT
		// TODO DO NOT UPDATE MAX BALANCE
		this.balance += amount;
		WriteBalance();

	}

	private void WriteBalance() {
		List<String> newLines = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get("Users/" + pathToFile + ".txt"), StandardCharsets.UTF_8)) {
				if (line.contains("Savings Balance")) {
					newLines.add("Savings Balance: " + balance);
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
