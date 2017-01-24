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
public class Driver_deleteRequestIO_NC {

	@Test
	public void first_path (){ // (1,2,3,4,14,13)
		
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
	
	@Test
	public void second_path (){ // (1,2,5,6,7,8,9,12,13)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student2203"+line+"123"+line+"student2204"+line+"123"+line+"adabiyat"+line+"adabiyat"+line+"adabiyat"+line+"adabiyat"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem2");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem2");
		driver.addUserIO(admin, 3); // create first student
		driver.addUserIO(admin, 3); // create second student
		Student student2203 = (Student) myLibrary.findUser("student2203");
		Student student2204 = (Student) myLibrary.findUser("student2204");
	    driver.addResourceIO(admin, 1); // add book with name "adabiyat"
	    driver.borrowIO(student2203, 1); // first student borrows first
	    driver.borrowIO(student2204, 1); // second adds request for "adabiyat"
	    driver.deleteRequestIO(student2204); // second student deleted request
		assertThat(outContent.toString(), containsString("The request was withdrawn successfully!"));
	}
	
	@Test
	public void third_path (){ // (1,2,5,6,7,8,9,10,8,11,13)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student3203"+line+"123"+line+"student3204"+line+"123"+line+"riazi"+line+"shimi"+line+"shimi"+line+"shimi"+line+"riazi"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem2");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem2");
		driver.addUserIO(admin, 3); // create first student
		driver.addUserIO(admin, 3); // create second student
		Student student3203 = (Student) myLibrary.findUser("student3203");
		Student student3204 = (Student) myLibrary.findUser("student3204");
	    driver.addResourceIO(admin, 1); // add book with name "riazi"
	    driver.addResourceIO(admin, 1); // add book with name "shimi"
	    driver.borrowIO(student3203, 1); // first student borrows "shimi"
	    driver.borrowIO(student3204, 1); // second adds request for "shimi"
	    driver.deleteRequestIO(student3204); // second student deleted request
		assertThat(outContent.toString(), containsString("The transaction was not completed!"));
	}

}
