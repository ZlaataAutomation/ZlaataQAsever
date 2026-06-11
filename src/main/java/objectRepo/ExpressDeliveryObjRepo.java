package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class ExpressDeliveryObjRepo extends BasePage {
	
	
	 
    @FindBy(xpath = "//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")
	   protected WebElement zlaataIndiaShopButton;
    
    @FindBy(xpath = "//div[@class='header_nav_item has_dropdown']")
	protected WebElement shopMenu;
    
    @FindBy(xpath = "//a[normalize-space()='All']")
	protected WebElement category;
	

    
	@FindBy(xpath = "//a[normalize-space()='dresses']")
	protected WebElement  randomcategory;
	
	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submit;
	
	@FindBy(xpath = "//div[@class='prod_delivery_wrap express_delivery']")
	protected WebElement expressDelivery;
	
	
	   @FindBy(xpath = "//a[@class='btn btn-sm btn-success clear-storage-btn btn-edit']")
	    protected WebElement editButtonOnProductSectionElement;
	
	   @FindBy(xpath = "//label[text()='Product Detail Name']/following-sibling::input")
	   protected WebElement productDetailName;
	   
	   @FindBy(xpath = "//input[@name='filters[0][delivery_speed]' and @value='1']")
	   protected WebElement expressDeliveryButton;
    

		@FindBy(id="searchBtn")
		protected WebElement clickOnSearchBar;
		
		@FindBy(xpath = "//input[@id='globalSearchInput']")
		protected WebElement searchBoxPlaceholder;
		
		
		
		
		@FindBy(xpath = "//button[@class='prod_buy_now_btn btn___2 Cls_Buy_now_To_Cart']")
		protected WebElement buyNowButton;
		
		@FindBy(xpath = "//input[@id='delivery_type_2']")
		protected WebElement expressdeliveryButton;
		
		@FindBy(xpath = "//div[@class='Cls_CourierFee']")
		protected WebElement expressShippingAmount;
    
		  
	    @FindBy(xpath = "//i[@class='fa fa-refresh']") 
	    protected WebElement clearCatchButton;
	    
	    @FindBy(xpath = "//a[normalize-space()='Product Detail Name']")
		protected WebElement productDetailMenu;
		
	    @FindBy(xpath = "(//input[@role='searchbox'])[1]") 
	    protected WebElement productSearchBox;
	    
	    
	    
	    
	    @FindBy(xpath = "//a[normalize-space()='Item']")
	    protected WebElement itemProductButton;
	    
	    
		
		@FindBy(name = "email")
		protected WebElement adminEmail;
		
		@FindBy(id = "password")
		protected WebElement adminPassword;
		
		@FindBy(xpath = "//button[@type='submit']")
		protected WebElement adminLogin;
		
		@FindBy(xpath = "//*[@aria-label='Open filters']")
		protected WebElement filterButton;
		
		@FindBy(xpath = "//span[normalize-space()='DELIVERY']")
		protected WebElement deliveryFilterButton;
		
		
		@FindBy(id = "delivery_true")
		protected WebElement  expressDeliveryButtonInFilter;
		
		@FindBy(xpath = "//button[@class='btn___2 popup_containers_footerbtn_apply Cls_apply_filter']")
		protected WebElement applyButton;
		
		@FindBy(xpath = "//li[normalize-space()='Express Delivery']")
		protected WebElement expressList;
    

}
