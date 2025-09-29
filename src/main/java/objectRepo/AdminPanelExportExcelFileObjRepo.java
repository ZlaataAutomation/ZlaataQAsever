package objectRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public abstract class AdminPanelExportExcelFileObjRepo extends BasePage {
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
    
	
	//New Added Product Export Check
    @FindBy(xpath = "//div[normalize-space()='Active products.']")
    protected WebElement activeProductButton;
    
    @FindBy(xpath = "//button[@data-type='new_products']")
    protected WebElement exportBtn1;
    
    @FindBy(xpath = "//input[@name='products.created_at']")
    protected WebElement createdAtBox;
    
    @FindBy(xpath = "//input[@name='products.created_at']")
    protected WebElement dateInput;
    
    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Apply'])[2]")
    protected WebElement calendarApplyBtn1;
    
    @FindBy(xpath = "//button[normalize-space()='Generate']")
    protected WebElement generateBtn1;
    
    @FindBy(xpath = "(//a[contains(text(),'View All')])[1]")
    protected WebElement viewAllBtn;
  
    @FindBy(xpath = "//button[normalize-space()='Export-btn']")
    protected WebElement exportBtn2;
  
    @FindBy(xpath = "//input[@name='created_at']")
    protected WebElement createdAtBox1;
    
    @FindBy(xpath = "//input[@name='created_at']")
    protected WebElement dateInput1;
    
    @FindBy(xpath = "//button[normalize-space()='Apply']")
    protected WebElement calendarApplyBtn2;
    
    @FindBy(xpath = "//button[normalize-space()='generate']")
    protected WebElement generateBtn2;
    
    @FindBy(xpath = "//input[@id='menuSearch']")
    protected WebElement searchProductMenu;
  
    @FindBy(xpath = "//a[normalize-space()='Export Histories']")
    protected WebElement clickExportHistories;
    
  //Active Products
    @FindBy(xpath = "(//button[contains(@class,'product-export-trigger') and text()[normalize-space()='Export']])[2]")
    protected WebElement exportBtnActiveProducts1;
    
    @FindBy(xpath = "//a[text()='View All' and contains(@href,'/admin/product?status=1')]")
    protected WebElement viewAllBtnActiveProducts;
    
  //Sold Out Products
    @FindBy(xpath = "//button[@data-type='sold_out_products']")
    protected WebElement exportBtnSoldOutProducts1;
    
    @FindBy(xpath = "(//a[contains(text(),'View All')])[2]")
    protected WebElement viewAllBtnSoldOutProducts;
    
    @FindBy(xpath = "//button[normalize-space()='Generate']")
    protected WebElement generateBtnSoldOut2;
    
    
  //Inactive Products
    @FindBy(xpath = "//button[@data-type='inactive_products'][normalize-space()='Export']")
    protected WebElement exportBtnInactiveProducts1;
    
    @FindBy(xpath = "(//a[contains(text(),'View All')])[4]")
    protected WebElement viewAllBtnInactiveProducts;
    
    
    

}
