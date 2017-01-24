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
import main.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Driver_returnResourceIO_NC {

	@Test
	public void first_path (){ // (1,2,5,6,7,12,15)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "4"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library lib = Library.getInstance("sharif3");
	    Student student = (Student) lib.users.get(2);
		Driver driver = new Driver ("sharif3");
	    driver.returnResourceIO(student);
		assertThat(outContent.toString(), containsString("The resource was not returned! or it was not found!"));
	}
	
	@Test
	public void second_path (){ // (1,2,5,6,7,12,13,14,15)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "software2"+line+"software2"+line+"1001"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library lib = Library.getInstance("sharif2");
	    Student student = (Student) lib.users.get(2);
		Driver driver = new Driver ("sharif2");
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		
		driver.addResourceIO(admin, 1);
		driver.borrowIO(student, 1);
	    driver.returnResourceIO(student);
		assertThat(outContent.toString(), containsString("The requested resource has been successfully returned!"));
	}
	
	@Test
	public void third_path (){ // (1,2,3,4,6,7,8,9,11,7,12,15)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "software1"+line+"software1"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library lib = Library.getInstance("sharif1");
	    Student student = (Student) lib.users.get(2);
		Driver driver = new Driver ("sharif1");
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		
		driver.addResourceIO(admin, 1);
	    driver.returnResourceIO(student);
		assertThat(outContent.toString(), containsString("The resource was not returned! or it was not found!"));
	}
	
	@Test
	public void forth_path (){ // (1,2,3,4,6,7,8,9,10,15)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "software4"+line+"software4"+line+"software4"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library lib = Library.getInstance("sharif1");
	    Student student = (Student) lib.users.get(2);
		Driver driver = new Driver ("sharif1");
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		
		driver.addResourceIO(admin, 1);
		driver.borrowIO(student, 1);
	    driver.returnResourceIO(student);
		assertThat(outContent.toString(), containsString("The requested resource has been successfully returned!"));
	}

}
