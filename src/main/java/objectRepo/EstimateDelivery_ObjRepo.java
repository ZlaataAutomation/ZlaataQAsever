	package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class EstimateDelivery_ObjRepo extends BasePage {

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

	@FindBy( id = "delivery-pincode")
	protected WebElement pincodeTextBox;

	@FindBy(xpath = "//button[@class='delivery_date_estimation_btn']")
	protected WebElement checkButton;

	@FindBy(xpath = "//span[@class='delivery_estimation_date']")
	protected WebElement estimateDate;

	@FindBy(xpath = "//div[@class='prod_category_wrap']")
	protected WebElement category;


	@FindBy(xpath = "//a[normalize-space()='Set Key']")
	protected WebElement clickOnSetkey;

	@FindBy(xpath = "//input[@name='set_value']")
	protected WebElement setvalue;

	@FindBy(xpath = "//input[@role='searchbox']")
	protected WebElement searchBoxdropdown;

	@FindBy(xpath = "//i[@class='las la-edit']")
	protected WebElement editItemButton;

	@FindBy(xpath = "//input[@name='set_value[estimate_delivery_date]']")
	protected WebElement estimateDeliveryDateDays;


	@FindBy(xpath = "//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")
	protected WebElement zlaataIndiaShopButton;

	@FindBy(xpath = "//a[normalize-space()='Category Name']")
	protected WebElement categoryMenu;

	@FindBy(xpath = "//input[@name='custom_delivery_date']")
	protected WebElement categoryDeliveryDate;

	@FindBy(xpath = "//input[@name='name']")
	protected WebElement categoryNameTextBox;

	@FindBy(xpath = "//a[normalize-space()='Product Detail Name']")
	protected WebElement productDetailNameTextBox;	


	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']//input[@role='searchbox']")
	protected WebElement productDetailsTextBox;

	@FindBy(xpath = "//input[@name='name']")
	protected WebElement PrdouctMainName;

	@FindBy(xpath = "//a[normalize-space()='Status']")
	protected WebElement  statusButton;

	@FindBy(xpath = "//li[contains(@class,'select2-results__option') and normalize-space()='Active']")
	protected WebElement activeButton;


	@FindBy(xpath = "//a[normalize-space()='Product Name']")
	protected WebElement productNameMenu;
	
	@FindBy(xpath = "//input[@name='collections']")
	protected WebElement collectionNameTextBox;
	
	@FindBy(id="searchBtn")
	protected WebElement clickOnSearchBar;
	
	@FindBy(xpath = "//input[@id='globalSearchInput']")
	protected WebElement searchBoxPlaceholder;
	
	@FindBy(xpath = "//button[@class='prod_buy_now_btn btn___2 Cls_Buy_now_To_Cart']")
	protected WebElement buyNowButton;
	
	@FindBy(xpath = "//div[@class='estimate__delivery__text']")
	protected WebElement checkoutEstimateDateText;
	
	@FindBy(xpath = "//button[contains(@class,'place_order_btn Cls_place_order btn___2')]")
	protected WebElement continueButtonOnChekcoutpage;
	
	@FindBy(xpath = "//span[@class='estimated_delivery_date Cls_delivery_date']")
	protected WebElement estimateddeliveryDateInAddressandPaymentPages;
	
	@FindBy(xpath = "//span[@class='estimated_delivery_date_status']")
	protected WebElement  estimateddeliveryInMyOrderPage;
	
	@FindBy(xpath = "//input[@id='payment_type_COD']")
	protected WebElement paymentCod;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2']")
	protected WebElement placeOrderButton;
	
	@FindBy(xpath = "//h5[@class='checkout_success_heading']")
	protected WebElement orderConfirmedMessage;
	
	@FindBy(xpath = "//a[@class='view_details_btn']")
	protected WebElement viewOrderDetailsPage;
	
	@FindBy(xpath = "//a[normalize-space()='Brand Type']")
	protected WebElement brandMenu;
	
	@FindBy(xpath = "//li[@id='select2-filter_brandType-result-rpau-1']")
	protected WebElement firstBrandOption;
	
	@FindBy(xpath = "//*[@class='prod_list_sortby_btn']")
	protected WebElement sortByButton;
	
	@FindBy(xpath = "//li[@data-category_checked_id='Price Low to High']")
	protected WebElement priceLowToHigh;
	
	@FindBy(xpath = "//input[@name='set_value[estimate_pickup_date]']")
	protected WebElement  estimatePickupDate;
	
	@FindBy(xpath = "//input[@name='set_value[estimate_exchange_delivery_date]']")
	protected WebElement estimateExchangeDeliveryDate;
	
	
	@FindBy(xpath = "//button[@class='header_cta_btn account_icon_btn ']")
	protected WebElement profileIcon;
	
	@FindBy(xpath = "//h2[contains(@class,'account_tabs_text') and contains(text(),'My Orders')]")
	protected WebElement myOrder;
	
	
	

}
	
	
	
	



