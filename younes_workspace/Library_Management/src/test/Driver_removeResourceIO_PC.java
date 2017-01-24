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
public class Driver_removeResourceIO_PC {

	@Test
	public void P1_T_P2_T_P3_T_P4_T (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "software"+line+"software"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
		driver.addResourceIO(admin, 1);
	    driver.removeResourceIO(admin);
		assertThat(outContent.toString(), containsString("The resource has been added successfully"));
		assertThat(outContent.toString(), containsString("The resource is successfully removed!"));
	}
	
	@Test
	public void P1_F_P2_F_P3_F_P4_DontCare (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "4"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
	    driver.removeResourceIO(admin);
		assertThat(outContent.toString(), containsString("The resource is not found/removed"));
	}

}
