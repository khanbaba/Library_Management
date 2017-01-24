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
public class LoginIO_Test_Statement_Based {

	@Test
	public void Null_User() { // True Predicate: {45}, False Predicates: {}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "IncorrectUserName"+line+"1234"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("The username or password was not correct!\n"));
		
	}
	
	@Test
	public void Incorrect_Pass() { // True Predicate: {49}, False Predicates: {45}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"1234"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("The username or password entered was not correct!"));
		
	}
	
	@Test
	public void Incorrect_Type() { // True Predicate: {57}, False Predicates: {45, 49}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line; // this is for an Admin Account
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY); // incorrect type.
		
		assertThat(outContent.toString(), containsString("The username or password was not correct!"));
		
	}
	
	@Test
	public void Admin_User() { // True Predicate: {69, 75, 77, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 107, 111, 115, 119, 123, 126, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"salam"+line+"9"+line+"n"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("admin still Logged in..."));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 1  --> True
	@Test
	public void Admin_User1() { // True Predicate: {69, 75, 91, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 95, 99, 103, 107, 111, 115, 119, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"1"+line+"admin2"+line+"123"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
 	//input == 2  --> True
	@Test
	public void Admin_User2() { // True Predicate: {69, 75, 95, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 99, 103, 107, 111, 115, 119, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"2"+line+"faculty2"+line+"123"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
 	//input == 3  --> True
	@Test
	public void Admin_User3() { // True Predicate: {69, 75, 99, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 103, 107, 111, 115, 119, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"3"+line+"student2"+line+"123"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The new userID is:"));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}		
	
	//input == 4 --> True
	@Test
	public void Admin_User4() { // True Predicate: {69, 75, 103, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 107, 111, 115, 119, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"4"+line+"Art of Software Testing"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	//input == 5 --> True
	@Test
	public void Admin_User5() { // True Predicate: {69, 75, 107, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 111, 115, 119, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"5"+line+"CE40689"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));		
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	//input == 6 --> True
	@Test
	public void Admin_User6() { // True Predicate: {69, 75, 111, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 107, 115, 119, 123, 135}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	//input == 7 --> True
	@Test
	public void Admin_User7() { // True Predicate: {69, 75, 115, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 107, 111, 119, 123, 135}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	//input == 8 --> True
	@Test
	public void Admin_User8() { // True Predicate: {69, 75, 119, 123, 126}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 107, 111, 115, 123, 135}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"8\r\n"+line+"15"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	//default --> True
	@Test
	public void Admin_User9() { // True Predicate: {69, 75, 123, 126, 135}, False Predicates: {45, 49, 57, 75, 77, 91, 95, 99, 103, 107, 111, 115, 119, 123}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line+"85"+line+"9"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.ADMIN);
		
		assertThat(outContent.toString(), containsString("Welcome admin!"));
		assertThat(outContent.toString(), containsString("Give the correct input"));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	
	@Test
	public void Faculty_User() { // True Predicate: {155, 157, 195, 198}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 179, 183, 187, 191, 195, 198 207}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"salam"+line+"7"+line+"n"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("Give the correct input"));
		assertThat(outContent.toString(), containsString("faculty Still Logged in..."));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 1 --> True
	@Test
	public void Faculty_User1() { // True Predicate: {155, 195, 198, 167}, False Predicates: {45, 49, 57, 69, 155, 157, 171, 175, 179, 183, 187, 191, 195, 207}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"1"+line+"NotAvailableResource"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("There is no resource with this name or ID."));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 2 --> True
	@Test
	public void Faculty_User2() { // True Predicate: {155, 195, 198, 171}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 175, 179, 183, 187, 191, 195, 207}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 3 --> True
	@Test
	public void Faculty_User3() { // True Predicate: {155, 195, 198, 175}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 179, 183, 187, 191, 195, 207}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}

	// input == 4 --> True
	@Test
	public void Faculty_User4() { // True Predicate: {155, 195, 198, 179}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 183, 187, 191, 195, 207}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 5 --> True
	@Test
	public void Faculty_User5() { // True Predicate: {155, 195, 198, 183}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 179, 187, 191, 195, 207}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// input == 6 --> True
	@Test
	public void Faculty_User6() { // True Predicate: {155, 195, 198, 187}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 179, 183, 191, 195, 207}
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
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}

	// input == 8 --> True
	@Test
	public void Faculty_User7() { // True Predicate: {155, 195, 198, 191}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 179, 183, 187, 195, 207}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"8"+line+"software"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("The resource was not renewed!"));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
	// default --> True
	@Test
	public void Faculty_User8() { // True Predicate: {155, 195, 198, 207}, False Predicates: {45, 49, 57, 69, 155, 157, 167, 171, 175, 179, 183, 187, 191, 195}
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "faculty"+line+"123"+line+"67"+line+"7"+line+"y"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	   
		Driver driver = new Driver ("sharif");
		driver.loginIO(Constants.FACULTY);
		
		assertThat(outContent.toString(), containsString("Welcome faculty!"));
		assertThat(outContent.toString(), containsString("Give the correct input"));
		assertThat(outContent.toString(), containsString("Thanks... logging out!"));
	}
	
}
