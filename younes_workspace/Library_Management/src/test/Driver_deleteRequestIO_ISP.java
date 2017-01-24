package test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.createMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import main.Admin;
import main.Constants;
import main.Driver;
import main.Library;
import main.LibraryUser;
import main.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Driver_deleteRequestIO_ISP {

	@Test
	public void borrower_NotNull_ID_RequestExists (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student1203"+line+"123"+line+"student1204"+line+"123"+line+"adabiyat"+line+"adabiyat"+line+"adabiyat"+line+"1001"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem");
		driver.addUserIO(admin, 3); // create first student
		driver.addUserIO(admin, 3); // create second student
		Student student1203 = (Student) myLibrary.findUser("student1203");
		Student student1204 = (Student) myLibrary.findUser("student1204");
	    driver.addResourceIO(admin, 1); // add book with name "adabiyat"
	    driver.borrowIO(student1203, 1); // first student borrows the book
	    driver.borrowIO(student1204, 1); // second student adds request
	    driver.deleteRequestIO(student1204); // second student deleted request
		assertThat(outContent.toString(), containsString("The request was withdrawn successfully!"));
	}
	
	@Test (expected=NullPointerException.class)
	public void borrower_Null_Name_Request_NotExists (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "adabiyat"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

		Driver driver = new Driver ("moallem2");

		Student student2203 = null;
	    driver.deleteRequestIO(student2203);
	}

}
