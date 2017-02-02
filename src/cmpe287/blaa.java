package automationFramework;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Register {

	private WebDriver driver;
	private String baseUrl;	
	private Properties testData;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Register.class);

    @Before
    public void setUp() throws IOException{
    	testData = new Properties();
        testData.load(new FileInputStream("testdata/register-credentials.properties"));
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        baseUrl = "https://www.airbnb.com/signup_login";        
    }
    
	@Test
    public void testValid(){
        
		int validTests = Integer.parseInt(testData.getProperty("validTests"));
		while(validTests > 0){
			driver.get(baseUrl);
			driver.findElement(By.id("signup_first_name")).sendKeys(testData.getProperty("fV"+validTests));
			driver.findElement(By.id("signup_last_name")).sendKeys(testData.getProperty("lV"+validTests));
			driver.findElement(By.xpath(".//*[@id='inputEmail']/input[1]")).sendKeys(testData.getProperty("eV"+validTests));
			driver.findElement(By.id("user_password")).sendKeys(testData.getProperty("pV"+validTests));
			driver.findElement(By.id("user_birthday_month")).sendKeys(testData.getProperty("mV"+validTests));
			driver.findElement(By.id("user_birthday_day")).sendKeys(testData.getProperty("dV"+validTests));
			driver.findElement(By.id("user_birthday_year")).sendKeys(testData.getProperty("yV"+validTests));
			driver.findElement(By.xpath(".//*[@id='user_new']/div[2]/label[2]")).click();	
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.findElement(By.xpath(".//*[@id='user_new']/div[2]/button")).click();
			log.debug(driver.getTitle());        			
			try{
    			assertTrue(titlePage.equals("Welcome to Airbnb"));
    		}catch(AssertionError ae){
    			log.error("Error: Unable to register "+ validTests);
    		}
			validTests--;
		}
                
    }  
	
	@Test
    public void testInValid(){
        
		int invalidTests = Integer.parseInt(testData.getProperty("invalidTests"));
		while(invalidTests > 0){
			driver.get(baseUrl);

			driver.findElement(By.id("signup_first_name")).sendKeys(testData.getProperty("fV"+invalidTests));
			driver.findElement(By.id("signup_last_name")).sendKeys(testData.getProperty("lV"+invalidTests));
			driver.findElement(By.xpath(".//*[@id='inputEmail']/input[1]")).sendKeys(testData.getProperty("eV"+invalidTests));
			driver.findElement(By.id("user_password")).sendKeys(testData.getProperty("pV"+invalidTests));
			driver.findElement(By.id("user_birthday_month")).sendKeys(testData.getProperty("mV"+invalidTests));
			driver.findElement(By.id("user_birthday_day")).sendKeys(testData.getProperty("dV"+invalidTests));
			driver.findElement(By.id("user_birthday_year")).sendKeys(testData.getProperty("yV"+invalidTests));
			driver.findElement(By.xpath(".//*[@id='user_new']/div[2]/label[2]")).click();						

			WebElement fErrorElement  = driver.findElement(By.id("signup_first_name-error"));
			WebElement lErrorElement  = driver.findElement(By.id("signup_last_name-error"));
			WebElement eErrorElement  = driver.findElement(By.xpath(".//*[@id='inputEmail']/input[1]"));
			WebElement pErrorElement  = driver.findElement(By.id("user_password-error"));
			WebElement mErrorElement  = driver.findElement(By.id("user_birthday_month-error"));
			WebElement dErrorElement  = driver.findElement(By.id("user_birthday_day-error"));
			WebElement yErrorElement  = driver.findElement(By.id("user_birthday_year-error"));
						
			driver.findElement(By.id(".//*[@id='user_new']/div[2]/label[2]")).click();
			WebElement mainErrorElement  = driver.findElement(By.id(".//*[@id='register_fail']/div[2]/label[2]"));
			log.debug(driver.getTitle());
			
			try{
				assertFalse(fErrorElement.getAttribute("class").contains("hidden"));
				assertFalse(leErrorElement.getAttribute("class").contains("hidden"));
				assertFalse(eErrorElement.getAttribute("class").contains("hidden"));
				assertFalse(pErrorElement.getAttribute("class").contains("hidden"));								
				assertFalse(mErrorElement.getAttribute("class").contains("hidden"));
				assertFalse(dErrorElement.getAttribute("class").contains("hidden"));								
				assertFalse(yErrorElement.getAttribute("class").contains("hidden"));
				
				assertFalse(mainErrorElement.getAttribute("class").contains("hidden"));
				assertTrue(mainErrorElement.isDisplayed());
				
			}catch(AssertionError e){
				log.error("Error:Invalid credentials accepted on register page..!!!!! testcase no: " + invalidTests);
			}
			invalidTests--;
		}   
    }	
		
	@After
    public void closureActivities(){
        driver.quit();
    }
}