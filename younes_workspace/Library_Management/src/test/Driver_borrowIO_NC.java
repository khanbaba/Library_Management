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
public class Driver_borrowIO_NC {

	@Test
	public void first_Path (){ // (1,2,3,7,8)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "4"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library lib = Library.getInstance("sharif");
	    Student student = (Student) lib.users.get(2);
		Driver driver = new Driver ("sharif");
	    driver.borrowIO(student, 1);
		assertThat(outContent.toString(), containsString("There is no resource with this name or ID."));
	}
	
	@Test
	public void second_Path (){ // (1,2,4,5,6,7,8,)
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "software"+line+"software";
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
	    driver.addResourceIO(admin, 1);
	    
	    Library lib = Library.getInstance("sharif");
	    Student student = (Student) lib.users.get(2);
	    driver.borrowIO(student, 1);
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));
		assertThat(outContent.toString(), containsString("2015"));
	}

}
