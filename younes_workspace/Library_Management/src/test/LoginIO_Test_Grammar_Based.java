package test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import main.Constants;
import main.Driver;

import org.easymock.EasyMock;
import org.easymock.internal.matchers.Contains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.*;

import org.powermock.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;


@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class LoginIO_Test_Grammar_Based {

	@Test
	public void Admin_Incorrect_Pass() { // covered: { DIGIT, CHAR, STR, UNIT, B::= STR Enter STR Enter A}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"+line+"123456789"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("The username or password was not correct!\n"));
		
	}
	
	@Test
	public void Admin_Menu_Create_New_Admin() { // covered: { B::=STR Enter STR Enter E, 1 Enter H, H, 9 Enter STR Enter E, 9 Enter STR Enter A
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"1"+line+"admin2"+line+"123"+line+"9"+line+"n"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));
		
	}
	
	@Test
	public void Admin_Menu_Create_New_Faculty() { // covered: {2 Enter I, I}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"2"+line+"faculty1"+line+"123"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));
		
	}
	
	@Test
	public void Admin_Menu_Create_New_Student() { // covered: {3 Enter J, J}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"3"+line+"student1"+line+"123"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));
		
	}
	
	@Test
	public void Admin_Menu_Add_New_Book() { // covered: {4 Enter K, K}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"4"+line+"Software Testing"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));
		
	}
	
	@Test
	public void Admin_Menu_Add_New_Course_Pack() { // covered: {5 Enter L, L}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"5"+line+"Ce40485"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));
		
	}
	
	@Test
	public void Admin_Menu_Add_New_Magazine() {// covered: {6 Enter M, M}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"6"+line+"Software Engineering Magazine"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));
		
	}
	
	@Test
	public void Admin_Menu_Remove_User_Account() { // covered: {7 Enter N, N}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"7"+line+"15"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("User 15 was not removed!"));
		
	}
	
	@Test (expected=NullPointerException.class) // this line is for pitclipse
	public void Admin_Menu_Remove_Resource() { // covered: {8 Enter O, O}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"4"+line+"software testing"+line+"8"+line+"software testing"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource is successfully removed!"));
		
	}
	
	// FACULTY MENU
	
	@Test
	public void Faculty_Menu_Incorrect_Login() { // covered: {C::=STR Enter STR Enter A}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty56"+line+"123"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("The username or password was not correct!"));
		
	}
	
	@Test
	public void Faculty_Menu_Borrow_Resource() { // covered: {1 Enter P, P, C::=STR Enter STR Enter F, 7 Enter STR Enter A, 7 Enter STR Enter F}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"1"+line+"NotAvailableResource"+line+"7"+line+"n"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("There is no resource with this name or ID."));
		
	}
	
	@Test
	public void Faculty_Menu_Return_Resource() { // covered: {2 Enter Q, Q}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"2"+line+"NotAvailableResource"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("The resource was not returned! or it was not found!"));
		
	}
	
	@Test
	public void Faculty_Menu_Delete_Request() { // covered: {3 Enter R, R}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"3"+line+"NotAvailableRequest"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("The transaction was not completed!"));
		
	}
	
	@Test
	public void Faculty_Menu_View_Issued_Books() { // covered: {4 Enter S F, S}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"4"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("There are no resources issued!"));
		
	}
	
	@Test
	public void Faculty_Menu_View_Pending_Request() { // covered: {5 Enter U F, U}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"5"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("There are no requests pending!"));
		
	}
	
	@Test
	public void Faculty_Menu_View_Fines() { // covered: {6 Enter V F, V}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"6"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("There are no fines."));
		
	}
	
	@Test
	public void Faculty_Menu_Renew_Resource() { // covered: {8 Enter W, W}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"8"+line+"NotAvailableResource"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("The resource was not renewed!"));
		
	}
	
	
	// STUDENT MENU
	
	@Test
	public void Student_Incorrect_Login() { // covered: {G, D::=STR Enter STR Enter G}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student854"+line+"123"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.STUDENT);
		
		assertThat(outContent.toString(), containsString("The username or password was not correct!"));
		
	}
	
	@Test
	public void Student_Menu_Borrow_Resource() { // covered: {G, D::=STR Enter STR Enter G}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student"+line+"123"+line+"1"+line+"NotAvailableResource"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.STUDENT);
		
		assertThat(outContent.toString(), containsString("Welcome student!"));
		assertThat(outContent.toString(), containsString("There is no resource with this name or ID."));
		
	}
	
}
