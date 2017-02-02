package automationFramework;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.pagefactory.ByChained;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Login {

	@Before
	public void setUp()throws IOException{
	   	testData = new Properties();
	    testData.load(new FileInputStream("testdata/signin-credentials.properties"));
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    baseUrl = " https://www.airbnb.com/login";    
	}

	@Test
	public void test() throws InterruptedException {
	WebDriver driver = new FirefoxDriver();
		
        //Launch the Airbnb Website
	int validTests = Integer.parseInt(testData.getProperty("validTests"));
    	while(validTests > 0){
    		driver.get(baseUrl);
    		driver.manage().window().maximize();
    		WebElement TB_Username = driver.findElement(By.id("signin_email")).sendKeys(testData.getProperty("eV"+validTests));
   	        WebElement TB_Password = driver.findElement(By.id("signin_password")).sendKeys(testData.getProperty("pV"+validTests)); 
   	        driver.findElement(By.id("user-login-btn")).click();
	        String titlePage = driver.getTitle();
    		try{
    			assertTrue(titlePage.equals("Hello | Welcome"));
    		}catch(AssertionError ae){
    			log.error("Error: Valid credentials not accepted in login..!!!!! test-case no "+ validTests);
    		}
    		validTests--;
    }

    @Test
    public void testInValid(){
            
    	int invalidTests = Integer.parseInt(testData.getProperty("invalidTests"));
    	while(invalidTests > 0){
    		driver.get(baseUrl);

    		driver.findElement(By.id("signin_email")).sendKeys(testData.getProperty("eI"+invalidTests));
    		driver.findElement(By.id("signin_password")).sendKeys(testData.getProperty("pI"+invalidTests));
    		driver.findElement(By.id("user-login-btn")).click();   
        		
    		driver.findElement(By.xpath(".//*[@id='signup-modal-content']/div/form[3]/div[3]/label)).getText();
   		         if (InvalidPasswordMessage.equals("Your password must be at least 8 characters. Please try again."))
   		             System.out.println("Invalid Passwod");
   		         else	         
   		             System.out.println("Email or Password is incorrect");
   	    }
    }   
           
    @Test
    public void testResetPasswordLink(){
        driver.get(baseUrl);
        WebElement linkElement = driver.findElement(new ByChained(By.xpath(".//*[@id='signup-modal-content']/div/form[3]/div[4]/a"),By.tagName("a")));
        linkElement.click();
        String titlePage = driver.getTitle();
        log.debug("Title of page: "+titlePage);            
        try{
          	assertTrue(titlePage.equals("Enter the email address associated with your account, and we’ll email you a link to reset your password. | Reset Password "));
        }catch(AssertionError ae){
          	log.error("Error : Forgot password link not working!!!!!");
        }     
    }        		                                  
    	
   	@After
    public void closureActivities(){
        driver.quit();
    }
}