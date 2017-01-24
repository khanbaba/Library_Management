package test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.createMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import main.Admin;
import main.Constants;
import main.Driver;
import main.Library;
import main.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Borrowe_viewFines_ISP {

	@Test
	public void fines_NotNull_Size_Zero_Or_Less () {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem");	    
		Student student1203 = (Student) myLibrary.findUser("student");
	   
	    student1203.viewFines();
		assertThat(outContent.toString(), containsString("There are no fines."));
	}
	
	@Test (expected=NullPointerException.class)
	public void fines_Null_Size_Bigger_Than_Zero () {
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "student1203"+line+"123"+line+"adabiyat"+line+"shimi"+line+"adabiyat"+line+"shimi"+line+"shimi"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    Library myLibrary = Library.getInstance("moallem");	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("moallem");
		driver.addUserIO(admin, 3); // create a student
		Student student1203 = (Student) myLibrary.findUser("student1203");

	    driver.addResourceIO(admin, 1); // add book with name "adabiyat"
	    driver.addResourceIO(admin, 1);
	    driver.borrowIO(student1203, 1); // issue the book adabiyat
	    driver.borrowIO(student1203, 1); // issue the book shimi
	    
	    // change time needed
	    
	    student1203.fines = null;
	    student1203.viewFines();
		assertThat(outContent.toString(), containsString("Total fine = Rs."));
	}

}
