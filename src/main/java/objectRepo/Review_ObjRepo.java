package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class Review_ObjRepo extends BasePage {
	
	public void waitFor(WebElement el) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(el));
    }
    public void type(WebElement el, String value) {
        waitFor(el); el.clear(); el.sendKeys(value);
    }
    public void click(WebElement el) {
        waitFor(el); el.click();
    }
    
    @FindBy(name = "email")
	protected WebElement adminEmail;
	
	@FindBy(id = "password")
	protected WebElement adminPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	protected WebElement adminLogin;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement mailValidationMessage;
	
	  @FindBy(xpath = "//input[@id='menuSearch']")  
	    protected WebElement searchMenu;
	  
	  @FindBy(xpath = "//a[contains(normalize-space(),'Product Reviews')]")  
	    protected WebElement clickProductReview;
	  
	  @FindBy(xpath = "//i[@class='fa fa-refresh']") 
	    protected WebElement clearCatchButton;
	  
	  @FindBy(xpath = "//button[@id='searchBtn']")
		protected WebElement searchIcon;
		
		@FindBy(xpath = "//input[@id='globalSearchInput']")
		protected WebElement userSearchBox;
		
		 @FindBy(xpath="//button[contains(@class,'account_icon_btn')]")
		    protected WebElement myProfileIcon;
		 
		 @FindBy(xpath="(//h2[normalize-space()='My Orders'])[1]")
		    protected WebElement myOrdersBtn;
		 
		 @FindBy(xpath="//label[contains(@class,'order-status-btn') and @data-filter='Pending Reviews']")
		    protected WebElement pendingReview;
    
}

