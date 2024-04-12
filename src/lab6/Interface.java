package lab6;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		Boolean exit = false;
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		System.out.println("Bank\n Enter your name to login (Ex. Cory Roth would enter coryroth");

		String name = myObj.next();

		String pathToFile = null;
		// TODO find if new user or old user
		Boolean oldUser = isNewUser(name);
		User user = null;
		if (oldUser) {
			user = new User(name, pathToFile);
			// Welcome back

		} else {
			user = new User(name);
		}

		while (!exit) {
			// Login

			System.out.println(
					"What action would you like to do?\n 0 to Check Balance, 1 for Deposit, 2 for Withdraw, 3 for transfer, 4 to access Utilities Screen, -1 to Exit");

			int choice = myObj.nextInt();

			if (choice == 0) {
				// SHOW Deposit Interface
				BalanceInterface(user);
			}
			if (choice == 1) {
				// SHOW Deposit Interface
				DepositInterface(user);
			} else if (choice == 2) {
				// Show Withdraw Interface
				WithrawInterface(user);
			} else if (choice == 3) {
				// Show Transfer interface
				TransferInterface(user);
			} else if (choice == 4) {
				// Show Utilities interface
				UtilitesInterface(user);
			} else if (choice == -1) {
				// Exit
				exit = true;
				System.out.println("Exited");
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}

			// Show interface to seelct possibile action
			// Deposit, Withdraw, Transfer, Utilities
		}

		// TODO
		// This code is for utilities

		myObj.close();
	}

	private static Boolean isNewUser(String name) {
		File folder = new File("Users/");
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (listOfFiles[i].getName().split(".txt")[0].equals(name)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void BalanceInterface(User user) {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println("Balance\n Enter 1 for Checking, 2 for Savings, -1 to return");
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Deposit Balance: " + user.checkingAccount.getBalance());
			} else if (choice == 2) {
				// TODO
			} else if (choice == -1) {
				myObj.close();
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void TransferInterface(User user) {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println(
					"Transfer\n Enter Which Transfer you would like to do 1 for Checking to Savings, 2 for Savings to Checking, -1 to return");
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Enter How much you would like to Transfer?");
				int amount = myObj.nextInt();
				user.transfer(amount, true);
			} else if (choice == 2) {
				System.out.println("Enter How much you would like to Transfer?");
				int amount = myObj.nextInt();
				user.transfer(amount, false);
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
		myObj.close();
	}

	private static void WithrawInterface(User user) {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println("Withdraw\n Enter 1 for Checking,  -1 to return");
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Enter How much you would like to Withdraw");
				int amount = myObj.nextInt();
				user.checkingAccount.withdraw(amount);
			} else if (choice == 2) {
				// TODO
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void DepositInterface(User user) {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		Boolean exit = false;
		while (!exit) {
			System.out.println("Deposit\n Enter 1 for Checking, 2 for Savings, -1 to return");
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Enter How much you would like to deposit");
				int amount = myObj.nextInt();
				user.checkingAccount.deposit(amount);
			} else if (choice == 2) {
				// TODO
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void UtilitesInterface(User user) {
		Utilities ultility = new Utilities();
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		Boolean exit = false;
		while (!exit) {
			System.out.println("Enter 1 to SignIn, 2 to Create a new Account, -1 to exit");

			// TODO ADD CHECK FOR STRING INPUT
			// TODO CHECK FOR FLOAT
			// TODO CHECK FOR NULL
			Integer temp = myObj.nextInt(); // Read user input
			if (temp == 1) {
				// TODO SIGNIN
				System.out.println("Sign in With your username/account number and password, separated by a space");
				String Username = myObj.next();
				String Password = myObj.next();
				int SignedIn;
				try {
					int accountnumber = Integer.valueOf(Username);
					System.out.println("Integer");
					SignedIn = ultility.SignIn(accountnumber, Password);
					// TODO CHECK SIGN IN
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password
							+ " AccountNumber: " + SignedIn);
					exit = true;
					SignInInterface(user);
				} catch (NumberFormatException e) {
					System.out.println("String");
					SignedIn = ultility.SignIn(Username, Password);
					// TODO CHECK SIGN IN
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password
							+ " AccountNumber: " + SignedIn);
					exit = true;
					SignInInterface(user);
				}

			} else if (temp == 2) {
				// TODO CREATE ACCOUNT
				System.out.println("Enter A username and a password, separated by a space");

				// TODO EXCEPTION HANDLING
				String Username = myObj.next();
				String Password = myObj.next();
				// TODO FIX FUNCTION
				String accountNumber = ultility.createAccount(Username, Password);
				System.out.println("Account Created \nUsername: " + Username + " Password: " + Password
						+ " AccountNumber: " + accountNumber);

			}

			else if (temp == -1) {
				exit = false;
				System.out.println("Exited");
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void SignInInterface(User user) {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		Boolean exit = false;
		while (!exit) {
			System.out.println(
					"Ulilities\n Enter 1 for Bill History, 2 for Check Next Bill, 3 To Pay Bill, -1 to Sign Out");
			int choice = myObj.nextInt();
			if (choice == 1) {
				// TODO
			} else if (choice == 2) {
				// TODO
			} else if (choice == 3) {
				// TODO
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

}
