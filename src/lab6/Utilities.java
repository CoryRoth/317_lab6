package lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilities {

	private String SignedInAccountNum;
	private String SignedInFileName;

	public Utilities() {

	}

	private List<String> getAllAccountUsernames() {
		File folder = new File("Utilities/");
		File[] listOfFiles = folder.listFiles();
		List<String> AccountNumbersList = new ArrayList<>();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					AccountNumbersList.add(listOfFiles[i].getName().split("_")[0]);
				}
			}
		}
		return AccountNumbersList;
	}

	private List<String> getAllAccountNumbers() {
		File folder = new File("Utilities/");
		File[] listOfFiles = folder.listFiles();
		List<String> AccountNumbersList = new ArrayList<>();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					AccountNumbersList.add(listOfFiles[i].getName().split("_")[1]);
				}
			}
		}
		return AccountNumbersList;
	}

	private void createFileForAccount(String numString, String username, String password) {
		try {
			File myObj = new File("Utilities/" + username + "_" + numString + "_" + ".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				FileWriter fr = new FileWriter(myObj, true);
				fr.write("Password: " + password + "\n");
				fr.write("Bill History: " + "\n");
				fr.write("Next Bill: " + "\n");
				fr.write("Due Date: " + "\n");
				fr.close();
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public String createAccount(String username, String password) {

		List<String> usedAccountNum = getAllAccountNumbers();
		if (usedAccountNum == null) {
			return "NO";
		}
		if (usedAccountNum.size() > 999999) {
			return "NO";
		}
		Boolean UniqueNum = true;
		Random rnd = new Random();
		String numString = null;
		while (UniqueNum) {
			int number = rnd.nextInt(999999);
			System.out.println(number);
			numString = String.format("%06d", number);
			System.out.println(numString);
			Boolean found = false;
			for (String accountNumUsed : usedAccountNum) {
				if (accountNumUsed.equals(numString)) {
					found = true;
				}
			}
			if (!found) {
				UniqueNum = false;
			}
			createFileForAccount(numString, username, password);

		}

		return numString;

	}

	public int SignIn(String username, String password) {
		// TODO
		// ##NEED TO CHECK username in files to find match
		// CHeck password for mach
		// if so get account number
		// set to private vairable
		// return good

		// Else return bad
		return 0;

	}

	public int SignIn(int accountNumber, String password) {
		// TODO
		// NEED TO CHECK account in files to find match
		// CHeck password for mach
		// if so get account number
		// set to private vairable
		// return good

		// Else return bad
		return accountNumber;

	}

	public String checkBillHistory(int accountNumber, int SignedIn) {
		// TODO
		// From File Find
		return null;
	}

	public String checkCurrentBill() {
		String CurrentBill = null;
		try {
			for (String line : Files.readAllLines(Paths.get(SignedInFileName), StandardCharsets.UTF_8)) {
				if (line.contains("Next Bill")) {
					CurrentBill = line;
				} else if (line.contains("Due Date")) {
					CurrentBill = CurrentBill + " " + line;
				} else {
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CurrentBill;
	}

	public String payBill(int accountNumber, User user) {
		// TODO

		// TODO WRITE BILL TO BILL HISTORY
		return null;
	}
}
