package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class AdminGoogleMerchantObjRepo extends BasePage {
	
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
    //Google Merchant
	
	@FindBy(xpath = "//a[normalize-space()='Product Detail Name']")
	protected WebElement productDetailsMenu;
	
    @FindBy(xpath = "(//input[@role='searchbox'])[1]") 
    protected WebElement productSearchBox;
    
    @FindBy(xpath = "//i[@class='las la-edit']")
    protected WebElement editProductButton;
    
    @FindBy(xpath = "//a[normalize-space()='Item']")
    protected WebElement itemProductButton;
    
    @FindBy(xpath = "(//input[@name='filters[0][sku]'])[1]") 
    protected WebElement skuField;
    
    @FindBy(xpath = "//input[@id='is_googlemerchant_0_1']") 
    protected WebElement yesBtn;
  
    @FindBy(xpath="//input[@id='menuSearch']")
    protected WebElement searchProductCollectionMenu;
    
    @FindBy(xpath="//a[normalize-space()='Google Merchants']")
    protected WebElement clickGoogleMerchant;
    
    @FindBy(xpath="//input[@id='sku']")
    protected WebElement itemIdBox;
    
    @FindBy(xpath="//input[@type='checkbox']")
    protected WebElement checkBox;
    
    @FindBy(xpath="//button[@id='pushSelectedBtn']")
    protected WebElement pushBtn;
    
    @FindBy(xpath="//button[normalize-space()='Yes, Push']")
    protected WebElement yesPush;
    
    @FindBy(xpath="//button[normalize-space()='OK']")
    protected WebElement clickOk;
    
    @FindBy(xpath="//button[@id='syncAllBtn']")
    protected WebElement syncAllBtn;
    
    @FindBy(xpath="//button[normalize-space()='Yes, Sync All']")
    protected WebElement yesSyncAll;
    
   
  
  
  
  
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
