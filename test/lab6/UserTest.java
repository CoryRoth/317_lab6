package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class UserTest {
	
	@Before
	void SetUpUsers()
	{
		System.out.println("Here");
		if(User.isNewUser("coryroth"))
		{
			new User("coryroth");
		}
	}

	@Test
	void isNewUserFalseWhenUserExistsTest() {
		new User("coryroth");
		boolean userIsNew = User.isNewUser("coryroth");
		assertFalse(userIsNew);
	}

}
