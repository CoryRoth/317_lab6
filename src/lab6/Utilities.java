package lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utilities {

	private String SignedInAccountNum;

	public Utilities() {

	}
	
	
	private String[] getAllAccountUsernames() {
		// TODO
		String[] output = { "test", "test" };
		return output;
	}

	private String[] getAllAccountNumbers() {
		// TODO
		String[] output = { "test", "test" };
		return output;
	}

	private void createFileForAccount(String numString, Boolean checking, String username, String password) {
		if (checking) {
			try {
				File myObj = new File("Accounts/" + username + "_" + numString + "_" + "checking.txt");
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					FileWriter fr = new FileWriter(myObj, true);
					fr.write("Password: " + password + "\n");
					fr.close();
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		} else {
			try {
				File myObj = new File("Accounts/" + username + "_" + numString + "_" + "saving.txt");
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					FileWriter fr = new FileWriter(myObj, true);
					fr.write("Password: " + password + "\n");
					fr.close();
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

	}

	public String createAccount(String username, String password) {
		String[] usedAccountNum = getAllAccountNumbers();
		if (usedAccountNum == null) {
			return "NO";
		}
		if (usedAccountNum.length > 999999) {
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
			createFileForAccount(numString, true, username, password);
			createFileForAccount(numString, false, username, password);
		}

		return numString;

	}

	public int SignIn(String username, String password) {

		//##NEED TO CHECK username in files to find match
			//CHeck password for mach
				// if so get account number
				// set to private vairable
				// return good
		
		//Else return bad
		return 0;

	}

	public int SignIn(int accountNumber, String password) {
		
		//##NEED TO CHECK account in files to find match
		//CHeck password for mach
			// if so get account number
			// set to private vairable
			// return good
	
		//Else return bad
		return accountNumber;

	}

	public String checkBillHistory(int accountNumber, int SignedIn) {
		return null;
	}

	public String checkBillPayment() {
		return null;
	}

	// TODO WRITE TO File

}
