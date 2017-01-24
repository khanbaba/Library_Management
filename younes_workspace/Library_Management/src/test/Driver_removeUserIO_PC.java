package test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.createMock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import main.Admin;
import main.Constants;
import main.Driver;
import main.Library;
import main.LibraryUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest ({Driver.class, Scanner.class})
public class Driver_removeUserIO_PC {

	@Test
	public void P1_T_P2_F (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "abcd"+line+"1256"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
	    driver.removeUserIO(admin);
	    
		assertThat(outContent.toString(), containsString("Give the correct integer input..."));
		assertThat(outContent.toString(), containsString("User 1256 was not removed!"));
	}
	
	@Test
	public void P1_F_P2_T (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "ali"+line+"123"+line+"14100183"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
	    
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
		driver.addUserIO(admin, 3);
	    driver.removeUserIO(admin);
	    
		assertThat(outContent.toString(), containsString("User 14100183 has been successfully removed!"));
	}

}
