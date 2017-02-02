package automationFramework;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	@Before
    public void setUp(){
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        baseUrl = "https://www.airbnb.com/signup_login";  
    }
	
	@Test
	public void test() {
		driver.get(baseUrl);
		//create object dropdown 
		//if you want to access any methods in select class, create object and access methods using object.methodname.
		//pass the locator as Select class argument 
		Select dropdown=new
		Select(driver.findElement(By.name("user_birthday_month")));
		//index starts with 0 
		dropdown.selectByIndex(1);
		dropdown.selectByVisibleText("June");
		dropdown.selectByValue("7");
	}
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
