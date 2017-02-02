package automationFramework;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.jna.platform.FileUtils;

public class screenshotCapture {
	@Before
    public void setUp(){
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        baseUrl = "https://www.airbnb.com/";  
    }
	
	@Test
	public void Screenshot_Capture() throws InterruptedException { //Capture entire page screenshot and then store it to destination 
		WebDriver driver = new FirefoxDriver();
		drive File Object screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		FileUtils.copyFile(screenshot, new File("C:\Users\Mansi\Desktop\report\screenshot.jpg")); 
		System.out.print("Screenshot is captured and stored in your C:\Users\Mansi\Desktop\report Folder"); 
	}
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
