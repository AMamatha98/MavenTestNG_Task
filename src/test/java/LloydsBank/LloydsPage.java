package LloydsBank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class LloydsPage {
   
    public WebDriver driver;

    
    public static final String LLOYDS_LOGO = "//li[@class='top-header-logo']//following-sibling::span[text()='Lloyds Bank Logo']";
    public static final String MENU_ICON = "//span[text()='Menu']";
    public static final String BRANCH_FINDER = "//span[text()='Branch Finder']";
    public static final String ACCEPT_BTN = "//button[@id='accept']";
    public static final String SEARCH_TEXT_FIELD = "//input[@placeholder='Enter your search']";
    public static final String FIRST_RESULT = "(//ol[@class='ResultList']//li)[1]";
    public static final String VIEW_BRANCH_LINK = "(//ol[@class='ResultList']//li)[1]//child::a[text()='View branch ']";
    public static final String PHONE_NO = "//div[contains(text(),'Telephone Banking Phone Number')]//following::div[@class='Phone Phone--main']";

    
    
    @FindBy(xpath = LLOYDS_LOGO)
    private WebElement Logo;
    
    @FindBy(xpath = MENU_ICON)
    private WebElement menu;

    @FindBy(xpath = ACCEPT_BTN)
    public WebElement acceptButton;

    @FindBy(xpath = BRANCH_FINDER) WebElement branchFinderIcon;

    @FindBy(xpath = SEARCH_TEXT_FIELD)
    private WebElement searchField;

    @FindBy(xpath = FIRST_RESULT)
    private WebElement firstResult;

    @FindBy(xpath = VIEW_BRANCH_LINK)
    private WebElement viewBranch;

    @FindBy(xpath = PHONE_NO)
    private WebElement phoneNumber;

    // Constructor to initialize the WebDriver instance
    public LloydsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with elements
    public void clickMenu() {
        menu.click();
    }
    public void ScrollToElement(WebElement element)
    {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickBranchFinder() {
        branchFinderIcon.click();
        acceptButton.click();
    }
    

    public void enterSearchText(String searchText) {
        searchField.sendKeys(searchText);
    }

    public void clickSearch() {
        searchField.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public boolean isFirstResultDisplayed() {
        return firstResult.isDisplayed();
    }
    
    public boolean isLloydsLogoDisplayed() {
        return Logo.isDisplayed();
    }
    

    public void clickViewBranch() {
        viewBranch.click();
    }

    public String getPhoneNumberText() {
        return phoneNumber.getText();
    }
    
    
    
}