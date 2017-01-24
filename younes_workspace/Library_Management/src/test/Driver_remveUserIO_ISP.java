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
public class Driver_remveUserIO_ISP {

	@Test
	public void admin_NotNull_user_Exists_id_NotNeg (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "ali"+line+"123"+line+"14100182"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = new Admin("admin1", "1231", Constants.ADMIN, true);
		Driver driver = new Driver ("sharif");
		driver.addUserIO(admin, 3);
	    driver.removeUserIO(admin);
	    
		assertThat(outContent.toString(), containsString("User 14100182 has been successfully removed!"));
	}
	
	@Test (expected=NullPointerException.class)
	public void admin_Null_user_NotExists_id_Neg (){
		
		System mockSystem = createMock (System.class);
		String line = System.getProperty("line.separator");
		String data = "abcd"+line+"-10"+line;
		mockSystem.setIn(new ByteArrayInputStream(data.getBytes()));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		
		Admin admin = null;
		Driver driver = new Driver ("sharif");
	    driver.removeUserIO(admin);
	}

}
