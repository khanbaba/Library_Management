package test;

import static org.junit.Assert.*;

import main.Library;
import main.LibraryUser;

import org.junit.Test;

public class Library_findUser_ISP {
	
	@Test
	public void Users_Contains_name () {
		Library lib = Library.getInstance("sharif");
		LibraryUser user = lib.findUser("admin");
		assertNotNull(user);
	}
	
	@Test
	public void name_Is_Null () {
		Library lib = Library.getInstance("sharif");
		String name = null;
		LibraryUser user = lib.findUser(name);
		assertNull (user);
	}

}
