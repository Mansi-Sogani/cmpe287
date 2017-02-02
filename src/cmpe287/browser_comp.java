package automationFramework;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChromeCheck {

	@Before
    public void setUp(){
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        baseUrl = "https://www.airbnb.com/";  
    }
	
	@Test
	public void test() throws InterruptedException {
		driver.get(baseUrl);
		System.out.println("Successfully opened the website https://www.airbnb.com/");
		 
		//Wait for 10 Sec
		Thread.sleep(100);
		
	    // Close the driver
	    driver.quit();
		//fail("Not yet implemented");
	}
	@After
    public void closureActivities(){
        driver.quit();
    }
}