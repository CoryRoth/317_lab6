package lab6;

import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		Boolean exit = false;
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		System.out.println("Bank\n Enter your name to login (Ex. Cory Roth would enter coryroth");
		// TODO VALIDATE INPUT
		String name = myObj.next();

		String pathToFile = null;
		Boolean oldUser = User.isNewUser(name);
		User user = null;
		if (oldUser) {
			user = new User(name, pathToFile);
			// Welcome back

		} else {
			user = new User(name);
		}

		while (!exit) {
			// Login

			// Show interface to seelct possibile action
			// Deposit, Withdraw, Transfer, Utilities
			System.out.println(
					"What action would you like to do?\n 0 to Check Balance, 1 for Deposit, 2 for Withdraw, 3 for transfer, 4 to access Utilities Screen, -1 to Exit");

			int choice = myObj.nextInt();
			if (choice == 0) {
				// SHOW Deposit Interface
				BalanceInterface(user);
			}
			else if (choice == 1) {
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

		}
		myObj.close();
	}

	

	private static void BalanceInterface(User user) {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println("Balance\n Enter 1 for Checking, 2 for Savings, -1 to return");
			// TODO IPUT VALIDATION
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Deposit Balance: " + user.checkingAccount.getBalance());
			} else if (choice == 2) {
				System.out.println("Savings Balance: " + user.savingsAccount.getBalance());
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void TransferInterface(User user) {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println(
					"Transfer\n Enter Which Transfer you would like to do 1 for Checking to Savings, 2 for Savings to Checking, -1 to return");
			// TODO INPUT Validation
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Enter How much you would like to Transfer?");
				// TODO INPUT Validation
				int amount = myObj.nextInt();
				user.transfer(amount, true);
			} else if (choice == 2) {
				System.out.println("Enter How much you would like to Transfer?");
				// TODO INPUT Validation
				int amount = myObj.nextInt();
				user.transfer(amount, false);
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void WithrawInterface(User user) {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (!exit) {
			System.out.println("Withdraw\n Enter 1 for Checking, -1 to return");
			// TODO INPUT Validation
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("Enter How much you would like to Withdraw");
				// TODO INPUT Validation
				int amount = myObj.nextInt();
				user.checkingAccount.withdraw(amount);
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void DepositInterface(User user) {
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		Boolean exit = false;
		while (!exit) {
			System.out.println("Deposit\n Enter 1 for Checking, 2 for Savings, -1 to return");
			// TODO INPUT Validation
			int choice = myObj.nextInt();
			if (choice == 1) {
				System.out.println("CHECKING\nEnter How much you would like to deposit");
				// TODO INPUT Validation
				int amount = myObj.nextInt();
				user.checkingAccount.deposit(amount);
			} else if (choice == 2) {
				System.out.println("SAVINGS\nEnter How much you would like to deposit");
				// TODO INPUT Validation
				int amount = myObj.nextInt();
				user.savingsAccount.deposit(amount);
			} else if (choice == -1) {
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

	private static void UtilitesInterface(User user) {
		Utilities ultility = new Utilities();
		 // Create a Scanner object

		Boolean exit = false;
		while (!exit) {
			System.out.println("Enter 1 to SignIn, 2 to Create a new Account, -1 to exit");
			Scanner myObj = new Scanner(System.in);
			// TODO INPUT Validation
			Integer temp = myObj.nextInt(); // Read user input
			if (temp == 1) {
				// Sign in
				System.out.println("Sign in With your username/account number and password, separated by a space");
				// TODO INPUT Validatio?
				String Username = myObj.next();
				String Password = myObj.next();
				int SignedIn;
				try {
					// If accountNumber is entered
					int accountnumber = Integer.valueOf(Username);
					// TODO CHECK SIGN IN
					SignedIn = ultility.SignIn(accountnumber, Password);
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password
							+ " AccountNumber: " + SignedIn);
					exit = false;
					SignInInterface(user);
				} catch (NumberFormatException e) {
					System.out.println("Username: " + Username + "\n" + "Password: " + Password);
					SignedIn = ultility.SignIn(Username, Password);
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password
							+ " AccountNumber: " + SignedIn);
					exit = false;
					SignInInterface(user);
				}

			} else if (temp == 2) {
				// Create Account
				System.out.println("Enter A username and a password, separated by a space");
				// TODO EXCEPTION HANDLING
				String Username = myObj.next();
				String Password = myObj.next();
				String accountNumber = ultility.createAccount(Username, Password);
				System.out.println("Account Created \nUsername: " + Username + " Password: " + Password
						+ " AccountNumber: " + accountNumber);

			} else if (temp == -1) {
				exit = false;
				System.out.println("Exited");
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
		ultility.SignOut();
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
				System.out.println("Signed Out");
				break;
			} else {
				System.out.println("You entered an incorrect Value.");
			}
		}
	}

}
