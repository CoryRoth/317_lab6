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
				fr.write("Next Bill: " + "\n");
				fr.write("Due Date: " + "\n");
				fr.write("Bill History: " + "\n");
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
		// TODO Deal with Errors
		List<String> usedUserNames = getAllAccountUsernames();
		List<String> userNameAndPasswords = getAllPasswordsFromUsernames(usedUserNames);

		String accountNumber = getAccountNumber(username);

		String Username_Password = username + " " + password;
		if (userNameAndPasswords.contains(Username_Password)) {
			SignedInFileName = MakeFileName(username, accountNumber);
			return 1;
		}
		// Else return bad
		return 0;

	}

	private String MakeFileName(String username, String accountNumber) {
		return "Utilities/" + username + "_" + accountNumber + "_" + ".txt";
	}

	private String getAccountNumber(String username) {
		File folder = new File("Utilities/");
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(username)) {
					return listOfFiles[i].getName().split("_")[1];
				}
			}
		}
		return null;
	}

	private List<String> getAllPasswordsFromUsernames(List<String> usedUserNames) {
		// TODO Deal with Errors
		File folder = new File("Utilities/");

		File[] listOfFiles = folder.listFiles();
		List<String> AccountNumbersList = new ArrayList<>();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					for (String username : usedUserNames) {
						if (listOfFiles[i].getName().contains(username)) {
							String password = null;
							try {
								for (String line : Files.readAllLines(
										Paths.get("Utilities/" + listOfFiles[i].getName()), StandardCharsets.UTF_8)) {
									if (line.contains("Password")) {
										password = line.split(" ")[1];
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							AccountNumbersList.add(username + " " + password);
						}
					}
				}
			}
		}
		return AccountNumbersList;
	}

	public int SignIn(int accountNumber, String password) {
		// TODO Deal with Errors
		List<String> usedUserNames = getAllAccountUsernames();
		List<String> userNameAndPasswords = getAllPasswordsFromUsernames(usedUserNames);

		String accountNum = String.format("%06d", accountNumber);
		String userName = getUserName(accountNum);

		String Username_Password = userName + " " + password;
		if (userNameAndPasswords.contains(Username_Password)) {
			SignedInFileName = MakeFileName(userName, accountNum);
			return 1;
		}

		// Else return bad
		return 0;

	}

	private String getUserName(String accountNumber) {
		File folder = new File("Utilities/");
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(accountNumber)) {
					return listOfFiles[i].getName().split("_")[0];
				}
			}
		}
		return null;
	}

	public void SignOut() {
		SignedInFileName = null;
	}

	public String checkBillHistory() {
		String CurrentBill = null;
		try {
			for (String line : Files.readAllLines(Paths.get(SignedInFileName), StandardCharsets.UTF_8)) {
				if (line.contains("Bill History")) {
					CurrentBill = line;
				} else if (line.contains("Payed")) {
					CurrentBill = CurrentBill + " " + line;
				} else {
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CurrentBill;
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

	public String payBill(User user) {
		// Get Current Bill Balance
		int CurrentBill = 0;
		try {
			for (String line : Files.readAllLines(Paths.get(SignedInFileName), StandardCharsets.UTF_8)) {
				if (line.contains("Next Bill")) {
					CurrentBill = Integer.valueOf(line.split(" ")[2]);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (CurrentBill == 0) {
			return "There is no Outstanding Bill for you to pay";
		}
		// TODO Check if Balance is able to pay
		user.checkingAccount.payBill(CurrentBill);
		List<String> newLines = new ArrayList<>();
		String CurrentBillOutput = null;
		try {
			for (String line : Files.readAllLines(Paths.get(SignedInFileName), StandardCharsets.UTF_8)) {
				if (line.contains("Next Bill")) {
					newLines.add("Next Bill: ");
					CurrentBillOutput = line.split(" ")[2];
				} else if (line.contains("Due Date")) {
					newLines.add("Due Date: ");
					CurrentBillOutput = CurrentBillOutput + " " + line.split(" ")[2];
				} else if (line.contains("Bill History")) {
					newLines.add(line);
					newLines.add("Payed " + CurrentBillOutput);
				} else {
					newLines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(Paths.get(SignedInFileName), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CurrentBillOutput;
	}
}
