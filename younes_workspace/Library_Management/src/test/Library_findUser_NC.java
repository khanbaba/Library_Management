package test;

import static org.junit.Assert.*;

import main.Library;
import main.LibraryUser;

import org.junit.Test;

public class Library_findUser_NC {

	@Test
	public void first_path_Test () {
		Library lib = Library.getInstance("sharif");
		LibraryUser user = lib.findUser("admin");
		assertNotNull(user.userName, lib.users.get(0).userName);
	}
	
	@Test
	public void second_path_Test () {
		Library lib = Library.getInstance("sharif");
		lib.users.clear();
		LibraryUser myUser = new LibraryUser();
		myUser.userName = "ali";
		lib.users.add(myUser);
		LibraryUser user = lib.findUser("reza");
		assertNull(user);
	}

}
