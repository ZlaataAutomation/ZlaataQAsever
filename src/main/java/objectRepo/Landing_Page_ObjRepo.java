package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class Landing_Page_ObjRepo extends BasePage {
	
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

	 @FindBy(xpath = "//input[@id='menuSearch']")  
	    protected WebElement searchProductSortMenu;
	 
	 @FindBy(xpath = "//a[normalize-space()='Zl Menu']")  
	    protected WebElement clickZlMenu;
	    
	    @FindBy(xpath = "//span[@class='ladda-label']")
	    protected WebElement addZlMenu;
	    
	    @FindBy(xpath="//span[@data-value='save_and_back']")
	    protected WebElement saveButton;
	    
	    @FindBy(xpath = "//i[@class='fa fa-refresh']") 
	    protected WebElement clearCatchButton;
}
