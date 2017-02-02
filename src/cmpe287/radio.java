package automationFramework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RadioButton {
	@Before
    public void setUp(){
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        baseUrl = "https://www.airbnb.com/host?from_nav=1&link=1";  
    }
	
	@Test
	public void test() {
		driver.get(baseUrl);
        List RadButtonList = driver.findElements(By.name("room"));
        String radioEntirePlaceStatus = ((WebElement)RadButtonList.get(0)).getAttribute("checked");
        String radioPrivateRoomStatus = ((WebElement)RadButtonList.get(1)).getAttribute("checked");
        String radioSharedRoomStatus = ((WebElement)RadButtonList.get(1)).getAttribute("checked");
        ((WebElement) RadButtonList.get(0)).click();
        radioEntirePlaceStatus = ((WebElement)RadButtonList.get(0)).getAttribute("checked");
        System.out.println("Entire Place radio Button value currently is"+radioEntirePlaceStatus);
        ((WebElement) RadButtonList.get(1)).click();
        radioPrivateRoomStatus = ((WebElement)RadButtonList.get(1)).getAttribute("checked");
        System.out.println("Private Room radio Button value currently is"+radioPrivateRoomStatus);
        ((WebElement) RadButtonList.get(2)).click();
        radioSharedRoomStatus = ((WebElement)RadButtonList.get(1)).getAttribute("checked");
        System.out.println("Shared Room radio Button value currently is"+radioSharedRoomStatus);
	}
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}