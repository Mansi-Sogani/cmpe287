package automationFramework;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class verifyTitle {
	@Before
	public void setUp(){
		String title;
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    baseUrl = "hhttps://www.airbnb.com/rooms/283638";  
	}
	
	@Test
	public void getTitlePage() throws InterruptedException
	{	
		driver.get(baseUrl); 
		title = driver.getTitle();
		try{
			assertTrue(titlePage.equals("Unique architecture - Houses for Rent in Oia"));
		}catch(AssertionError ae){
			log.error("Specific Room Page did not load. ");
		}
	}
	
	@After
    public void closureActivities(){
        driver.quit();
    }
}
