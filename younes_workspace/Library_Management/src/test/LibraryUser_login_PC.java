package test;

import static org.junit.Assert.*;

import main.Library;
import main.LibraryUser;

import org.junit.Test;

public class LibraryUser_login_PC {

	@Test
	public void P_T() {
		Library lib = Library.getInstance("sharif");
		LibraryUser myUser = new LibraryUser();
		myUser.userName = "ali";
		myUser.password = "13";
		assertTrue(myUser.login("ali", "13"));
	}
	
	@Test
	public void P_F() {
		Library lib = Library.getInstance("sharif");
		LibraryUser myUser = new LibraryUser();
		myUser.userName = "ali";
		myUser.password = "13";
		assertFalse(myUser.login("ali", "14"));
	}

}
