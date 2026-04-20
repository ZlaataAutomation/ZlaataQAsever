package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class StockQuantityObjRepo extends BasePage {
	
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
	    protected WebElement searchMenu;
	  
	  @FindBy(xpath = "//a[normalize-space()='Track Inventory']")  
	    protected WebElement clickTrackInvetory;
	  
	  @FindBy(xpath = "//span[@class='ladda-label']")
	    protected WebElement addProductSort;
	
	  @FindBy(xpath = "//i[@class='fa fa-refresh']") 
	    protected WebElement clearCatchButton;
	  
	  @FindBy(xpath = "//button[@onclick='submitOTP()']")
		protected WebElement verifyotp;
		@FindBy(xpath = "//button[contains(@class,'account_icon_btn')]")
		protected WebElement profile;
		
		@FindBy(id = "userNumber")
		protected WebElement loginNumber;
		
		@FindBy(xpath = "//button[@class='send_otp_btn btn___2']")
		protected WebElement sendotp;
		
		@FindBy(xpath = "//form[@class='digit-group login_otp_input_form']")
		protected WebElement otpEnterTextBox;
		
		@FindBy(id = "digit-1")
		protected WebElement enterotp;
		
		@FindBy(xpath = "//button[@id='searchBtn']")
		protected WebElement searchIcon;
		
		@FindBy(xpath = "//input[@id='globalSearchInput']")
		protected WebElement userSearchBox;
		@FindBy(xpath = "//button[contains(@class,'prod_add_to_cart') and contains(@class,'ClsSingleCart')]")
		protected WebElement addToBag;
		
		    
		@FindBy(xpath = "//button[contains(@class,'prod_add_cart_btn') and normalize-space()='Add to cart']")
		protected WebElement addToCartBtn;
		
		@FindBy(xpath = "//button[contains(@class,'Cls_cart_btn') and contains(@class,'header_cta_btn')]")
		protected WebElement bagIcon;
		@FindBy(xpath = "//button[contains(@class,'place_order_btn') and normalize-space(text())='Continue']")
		protected WebElement continueBtn;
		
		@FindBy(xpath = "//button[contains(@class,'place_order_btn') and normalize-space(text())='Place order']")
		protected WebElement placeOrderBtn;
		
		
		@FindBy(xpath = "(//input[@id='net-banking'])[1]")
		protected WebElement selectNetBank;
		
		@FindBy(xpath = "//div[@data-value='HDFC']//span[normalize-space(text())='HDFC Bank']")
		protected WebElement selectHdfcBank;
		
		@FindBy(xpath = "//a[contains(@class,'view_details_btn') and contains(text(),'View Order Details')]")
		protected WebElement viewOrderDetails;
		
		 @FindBy(xpath = "//input[@id='menuSearch']")  
		 protected WebElement searchProductSortMenu;
		
		 @FindBy(xpath = "(//a[@class='nav-link active'])[1]")  
		 protected WebElement clickOrders;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
