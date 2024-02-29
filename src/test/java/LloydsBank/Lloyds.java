package LloydsBank;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import LloydsBank.LloydsPage;
import Utility.ExcelUtilities;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
public class Lloyds extends ExcelUtilities{
	
	private WebDriver driver;
	private LloydsBank.LloydsPage PageObject;
	
	public  String DriverPath="C:\\Workspace\\Lloyds WS\\Drivers\\chromedriver-win64\\";

	public  final int defaultBrowserTimeOut = 15;
	private static Logger logger = LogManager.getLogger(Lloyds.class);
	
	public ExcelUtilities ut=new ExcelUtilities();
	 @BeforeMethod
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\Lloyds WS\\Drivers\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        PageObject = new LloydsPage(driver);
	        PageFactory.initElements(driver, PageObject);
	        driver.manage().timeouts().implicitlyWait(defaultBrowserTimeOut, TimeUnit.SECONDS);
	        driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();
	    }
	 
	 
	@Test(priority = 0, enabled = true)
	public  void LaunchURLandBranchFinder() throws Exception {
		 driver.get("https://www.lloydsbank.com/");

	        if (PageObject.isLloydsLogoDisplayed()) {
	            logger.info("Lloyds Bank Url launched successfully");
	        } else {
	        	logger.info("Lloyds Bank Url not launched");
	        }
	        
				
		PageObject.clickMenu();
		PageObject.ScrollToElement(PageObject.branchFinderIcon);
		PageObject.clickBranchFinder();		
		String code=ut.ReadFromExcel("PostalCode", "PostalCodes", 1,1);
	//	System.out.print(code);
		PageObject.enterSearchText(code);
		PageObject.clickSearch();
		if(PageObject.isFirstResultDisplayed())
		{
			logger.info("the first result out of 10 is visible");
		}
		else
		{
			logger.info("Results are not obtained");

		}
		
		PageObject.clickViewBranch();
		String number=PageObject.getPhoneNumberText();
		
		 logger.info("Phone number from the first resultant of branch finder using postal code obtained is "+" "+ number);
	}
		
	
	@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	
	
}
