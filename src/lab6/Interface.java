package lab6;

import java.util.Random;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		Utilities ultility = new Utilities();
		Boolean exit = false;

		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		while (!exit) {
			System.out.println("Enter 1 to SignIn, 2 to Create a new Account, -1 to exit");


			//TODO ADD CHECK FOR STRING INPUT
			//TODO CHECK FOR FLOAT
			//TODO CHECK FOR NULL
			Integer temp = myObj.nextInt(); // Read user input
			if(temp  == 1) {
				//TODO SIGNIN 
				System.out.println("Sign in With your username/account number and password, separated by a space");

				String Username = myObj.next();
				String Password = myObj.next();
				int SignedIn;
				try {
		            int accountnumber =Integer.valueOf(Username);
		            System.out.println("Integer");
					SignedIn = ultility.SignIn(accountnumber, Password);
					//TODO CHECK SIGN IN
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password + " AccountNumber: " + SignedIn);
					exit = true;
					SignInInterface();
		        }
		        catch (NumberFormatException e) {
		            System.out.println("String");
					SignedIn = ultility.SignIn(Username, Password);
					//TODO CHECK SIGN IN
					System.out.println("Signed in \nUsername: " + Username + " Password: " + Password + " AccountNumber: " + SignedIn);
					exit = true;
					SignInInterface();
		        }
					
				
				
			}
			else if (temp ==2) {
				//TODO CREATE ACCOUNT
				System.out.println("Enter A username and a password, separated by a space");

				//TODO EXCEPTION HANDLING
				String Username = myObj.next();
				String Password = myObj.next();	
				String accountNumber = ultility.createAccount(Username, Password);
				System.out.println("Account Created \nUsername: " + Username + " Password: " + Password + " AccountNumber: " + accountNumber);
				
				
			}
			
			else if (temp == -1) {
				exit = false;
				System.out.println("Exited");
				break;
			}
			else {
				System.out.println("You entered an incorrect Value.");
	
			}
		}
		myObj.close();
	}
	
	private static void SignInInterface() {
		
	}

}
