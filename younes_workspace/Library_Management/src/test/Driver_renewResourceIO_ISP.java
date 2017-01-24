package test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.createMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import main.Admin;
import main.Constants;
import main.Driver;
import main.Library;
import main.LibraryResource;
import main.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Driver_renewResourceIO_ISP {

	@Test
	public void borrower_NotNull_ID_Resource_Issued_Before (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student1203"+line+"123"+line+"adabiyat"+line+"adabiyat"+line+"1002"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem");
		driver.addUserIO(admin, 3); // create a student
		Student student1203 = (Student) myLibrary.findUser("student1203");

	    driver.addResourceIO(admin, 1); // add book with name "adabiyat"
	    driver.borrowIO(student1203, 1); // issue the book
	    driver.renewResourceIO(student1203);
	    
		assertThat(outContent.toString(), containsString("The resource was renewed successfully!"));
	}
	
	
	@Test (expected=NullPointerException.class)
	public void borrower_Null_Name_Resource_Not_Issued_Before (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student3203"+line+"123"+line+"software123"+line+"software123"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    
	    Library myLibrary = Library.getInstance("hekmat");	  
		Admin admin = (Admin) myLibrary.users.get(0);
		Driver driver = new Driver("hekmat");
		
		driver.addUserIO(admin, 3); // create a student
		Student student3203 = (Student) myLibrary.findUser("student3203");

	    driver.addResourceIO(admin, 1); // add book
	    student3203 = null;
	    driver.renewResourceIO(student3203);
	    
		assertThat(outContent.toString(), containsString("The resource was not renewed!"));
	}

}
