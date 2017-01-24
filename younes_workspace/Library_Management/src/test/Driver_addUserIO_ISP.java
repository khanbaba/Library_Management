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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Driver_addUserIO_ISP {

	@Test
	public void user_already_exists (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin"+line+"123"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
	    driver.addUserIO(admin, 2);
		assertThat(outContent.toString(), containsString("New user could not be created."));
	}
	
	@Test (expected=NullPointerException.class)
	public void null_admin_input (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "admin12"+line+"12523"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = null;
		Driver driver = new Driver ("sharif");
	    driver.addUserIO(admin, 5);
	}

}
