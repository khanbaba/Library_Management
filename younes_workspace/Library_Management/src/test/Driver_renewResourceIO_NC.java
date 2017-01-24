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
public class Driver_renewResourceIO_NC {

	@Test
	public void first_path (){ // (1,2,3,4,5,11)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student1203"+line+"123"+line+"adabiyat"+line+"adabiyat"+line+"1001"+line;
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
	
	@Test
	public void second_path (){ // (1,2,3,4,6,11)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student2203"+line+"123"+line+"adabiyat"+line+"adabiyat"+line+"526548"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem1");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem1");
		driver.addUserIO(admin, 3); // create a student
		Student student2203 = (Student) myLibrary.findUser("student2203");

	    driver.addResourceIO(admin, 1); // add book with name "adabiyat"
	    driver.borrowIO(student2203, 1); // issue the book
	    
	    driver.renewResourceIO(student2203);
	    
		assertThat(outContent.toString(), containsString("The resource was not renewed!"));
	}
	
	@Test
	public void third_path (){ // (1,2,3,4,)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student3203"+line+"123"+line+"software123"+line+"software123"+line+"1004"+line+"software123";
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    
	    Library myLibrary = Library.getInstance("hekmat");	  
		Admin admin = (Admin) myLibrary.users.get(0);
		Driver driver = new Driver("hekmat");
		
		driver.addUserIO(admin, 3); // create a student
		Student student3203 = (Student) myLibrary.findUser("student3203");

	    driver.addResourceIO(admin, 2); // add course pack
	    driver.addResourceIO(admin, 1); // add book
	    driver.borrowIO(student3203, 1); // issue the book
	    driver.renewResourceIO(student3203);
	    
		assertThat(outContent.toString(), containsString("The request was withdrawn successfully!"));
	}

}
