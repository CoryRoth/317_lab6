package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void isNewUserFalseWhenUserExistsTest() {
		new User("coryroth");
		boolean userIsNew = User.isNewUser("coryroth");
		assertFalse(userIsNew);
	}
	
	@Test
	void isNewUserTrueWhenUserDoesNotExistTest()
	{
		boolean userIsNew = User.isNewUser("NotAUsedUserName");
		assertTrue(userIsNew);
	}
}
