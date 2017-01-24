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
public class Driver_addResourceIO_ISP {

	@Test
	public void invalid_type (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "abcd"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
	    driver.addResourceIO(admin, 4);
		assertThat(outContent.toString(), containsString("The resource could not be added."));
	}
	
	@Test (expected=NullPointerException.class)
	public void null_admin_input (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = null;
		Driver driver = new Driver ("sharif");
	    driver.addResourceIO(admin, 1);
	}

}
