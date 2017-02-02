package automationFramework;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class navigation {

	@Test
	public void test() {
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("http://www.airbnb.com"); //To navigate back (Same as clicking on browser back button) 
		driver.navigate().back(); //To navigate forward (Same as clicking on browser forward button) 
		driver.navigate().forward(); 
	}
	@After
    public void closureActivities(){
        driver.quit();
    }
}
