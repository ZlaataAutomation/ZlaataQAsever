package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import basePage.BasePage;

public abstract class AdminPanelMediaLibraryObjRepo extends BasePage {
	
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
   	
   	@FindBy(xpath = "//input[@id='access_code']") 
   	protected WebElement accessCode;

   	@FindBy(xpath = "//button[normalize-space()='Submit']")       
   	protected WebElement submit;
   	
   	@FindBy(xpath = "(//input[@id='menuSearch'])[1]")
   	protected WebElement menuSearchBox;
   	
   	@FindBy(xpath="//a[normalize-space()='Media Library']")
   	protected WebElement mediaLibraryMenu;
   	
    @FindBy(xpath = "(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[1]")
    protected WebElement zlaataIndiaShopNowButton;
    
    @FindBy(xpath="(//span[contains(@class,'header_nav_link')])[1]")
    protected WebElement zlaataIndiaHeaderLink;
    
    @FindBy(xpath="(//span[contains(@class,'header_nav_link')])[1]")
    protected WebElement zlaataIndiaShopHeaderLink;
    
    @FindBy(xpath="(//a[normalize-space()='dresses'])[1]")
    protected WebElement zlaataIndiaDressesCateogryLink;
    
    @FindBy(xpath="(//a[normalize-space()='All'])[1]")
    protected WebElement zlaataIndiaAllCategoryLink;
    
    @FindBy(xpath="//a[@class='product_list_name']")
    protected WebElement productlistName;
    
    @FindBy(xpath="(//a[normalize-space()='Media Library'])[1]")
    protected WebElement zlaataIndiaMediaLibraryMenuLink;
    
    @FindBy(xpath = "//a[contains(@class,'nav-link') and contains(@class,'dropdown-toggle') and normalize-space()='Product Name']")
    protected WebElement productNameColumnHeader;
    
    @FindBy(xpath = "(//span[contains(@class,'select2-search--dropdown')]//input[@role='searchbox'])[1]")
    protected WebElement productNameSearchBox;
    
    @FindBy(xpath="(//a[normalize-space()='SKU'])[1]")
    protected WebElement skuColumnHHeader;

    @FindBy(xpath="//input[@id='text-filter-sku']")
    protected WebElement skuSearchBoxInMediaLibrary;
    
    @FindBy(xpath="(//span[@class='select2-search select2-search--dropdown']//input[@role='searchbox'])[1]")
    protected WebElement BrandTypeSearchBox;
    
    @FindBy(xpath="//input[@id='text-filter-sku']")
    protected WebElement skuSearchBox;
    
    @FindBy(xpath="(//i[@class='las la-edit'])[1]")
    protected WebElement firstProductEditButton;
    
    @FindBy(xpath="(//button[normalize-space()='Choose File'])[1]")
    protected WebElement chooseFileButton;
    
    @FindBy(xpath="(//label[@for='previewImage'])[1]")
    protected WebElement previewImageLabel;
    
    @FindBy(xpath="//label[@for='mainImage']")
    protected WebElement mainImageLabel;
    
    @FindBy(xpath="//label[@for='thumbnailwImage']")
    protected WebElement thumbnailImageLabel;
    
    @FindBy(xpath="//button[@id='confirmUpload']")
    protected WebElement confirmUploadButton;
    
    @FindBy(xpath="(//button[@type='button'][normalize-space()='No'])[2]")
    protected WebElement noButtonOnConfirmationPopup;
    
    @FindBy(xpath="(//span[@class='la la-ban'])[1]")
    protected WebElement CancelButton;
    
    @FindBy(xpath="//span[@data-value='save_and_back']")
    protected WebElement saveAndBackButton;
    
    @FindBy(xpath="//i[@class='fa fa-refresh']")
    protected WebElement refreshButton;
    
    @FindBy(xpath="(//button[@id='searchBtn'])[1]")
    protected WebElement searchButton;
    
    @FindBy(xpath="(//input[@id='globalSearchInput'])[1]")
    protected WebElement globalSearchInput;
    
    @FindBy(xpath="//a[normalize-space()='Product Detail Name']")
    protected WebElement productPageDetailName;
    
    @FindBy(xpath="//span[@class='select2-search select2-search--dropdown']//input[@role='searchbox']")
    protected WebElement productPageDetailNameSearchBox;
    
    @FindBy(xpath="(//i[@class='las la-edit'])[1]")
    protected WebElement productPageFirstEditButton;
    
    @FindBy(xpath="(//input[@name='name'])[1]")
    protected WebElement productPageNameInput;
    
    @FindBy(xpath="//a[@class='btn btn-default']")
    protected WebElement productPageCancelButton;
    
    @FindBy(xpath="(//select[@class='select2 brand-type form-control form-control-sm select2-hidden-accessible'])[1]")
    protected WebElement mediaLibraryBrandTypeDropdown;
    
    
    
    


}
