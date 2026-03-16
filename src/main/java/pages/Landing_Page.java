package pages;

import java.time.Duration;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.Landing_Page_ObjRepo;
import utils.Common;

public class Landing_Page extends Landing_Page_ObjRepo {
	
	public Landing_Page(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}
	
	public void adminLoginApp() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	    type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
	    type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
	    click(adminLogin);
	    System.out.println("✅ Admin Login Successfull");
	    
	}
	String menuName;
	public void fillZLMenuFormForLandingPage(String linkValue, String brandTypeValue) {

	    String GREEN  = "\u001B[32m";
	    String BLUE   = "\u001B[34m";
	    String CYAN   = "\u001B[36m";
	    String RESET  = "\u001B[0m";
	    adminLoginApp();
	    Common.waitForElement(2);
	    // Open Product Sort
        Common.waitForElement(1);
        click(searchProductSortMenu);
        type(searchProductSortMenu, "Zl Menu");
        click(clickZlMenu);
        System.out.println("✅ Selected Zl Menu");
        Common.waitForElement(2);
        click(addZlMenu);
        System.out.println("✅ Clicked add Zl Menu");
        Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    Random random = new Random();
	    int randomDigit = random.nextInt(9999);

	     menuName = "AutoTest" + randomDigit;

	    // Menu Name
	    WebElement menuNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.name("name")));
	    menuNameField.clear();
	    menuNameField.sendKeys(menuName);
	    Common.waitForElement(1);
	    // Link
	    WebElement linkField = driver.findElement(By.name("link"));
	    linkField.clear();
	    linkField.sendKeys(linkValue);
	    Common.waitForElement(1);
	    // Brand Type
	    Select brandType = new Select(driver.findElement(By.name("brand_type")));
	    brandType.selectByVisibleText(brandTypeValue);
	    Common.waitForElement(1);
	    // Location → Landing
	    Select location = new Select(driver.findElement(By.name("location")));
	    location.selectByVisibleText("Landing");
	    Common.waitForElement(2);
	    // Sort By
	    WebElement sortField = driver.findElement(By.name("sort"));
	    sortField.clear();
	    sortField.sendKeys("1");

	    System.out.println(CYAN + "━━━━━━━━ ZL MENU FORM FILLED ━━━━━━━━" + RESET);
	    System.out.println(BLUE + "Menu Name : " + RESET + menuName);
	    System.out.println(BLUE + "Link      : " + RESET + linkValue);
	    System.out.println(BLUE + "Brand Type: " + RESET + brandTypeValue);
	    System.out.println(BLUE + "Location  : " + RESET + "Landing");
	    System.out.println(GREEN + "Sort By   : " + RESET + "1");
	    System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
	    // Save sort
	    Common.waitForElement(2);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        System.out.println("✅ Zl Menu saved for category: " + menuName);
    

    // Clear cache
    Common.waitForElement(4);
    click(clearCatchButton);
    System.out.println("✅ Cleared cache");
    Common.waitForElement(3);
	}

	
	public void verifyLandingPageZlaataIndiaCategoryRedirect(String expectedLinkEnd) {

	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String CYAN   = "\u001B[36m";
	    String BLUE   = "\u001B[34m";
	    String RESET  = "\u001B[0m";
	    Common.waitForElement(1);
	    // Wait for categories section
	    WebElement categorySection = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'brand_categories_card_wrap')]")));
	    System.out.println(GREEN + "✅ Category section visible" + RESET);

	    // Find category by name
	    WebElement category = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'brand_categories_card')]//span[normalize-space()='" 
	            + menuName + "']/parent::a")));

	    String hrefLink = category.getAttribute("href");

	    System.out.println(BLUE + "Category Name : " + RESET + menuName);
	    System.out.println(BLUE + "Category Link : " + RESET + hrefLink);
	    
	    if (category.isDisplayed()) {
	        System.out.println(GREEN + "✅ Category is displayed: " + menuName + RESET);

	        // Click category
	        category.click();

	    } else {
	        Assert.fail("❌ Category not displayed: " + menuName);
	    }

	    Common.waitForElement(2);
	    // Wait for page load
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h2[contains(@class,'prod_listing_topic')]")));

	    // Get heading
	    String heading = driver.findElement(
	            By.xpath("//h2[contains(@class,'prod_listing_topic')]")).getText().trim();

	    // Get current URL
	    String currentUrl = driver.getCurrentUrl();
	    Common.waitForElement(2);
	    // Validate URL
	    if (currentUrl.endsWith(expectedLinkEnd)) {
	        System.out.println(GREEN + "✅ URL matched : " + currentUrl + RESET);
	    } else {
	        System.out.println(RED + "❌ URL mismatch : " + currentUrl + RESET);
	    }

	    // Validate heading
	    if (heading.equalsIgnoreCase("All")) {
	        System.out.println(GREEN + "✅ Heading verified : " + heading + RESET);
	    } else {
	        System.out.println(RED + "❌ Heading mismatch : " + heading + RESET);
	        Assert.fail("❌ Heading mismatch");
	    }

	    System.out.println(CYAN + "━━━━━━━━ TEST COMPLETED ━━━━━━━━" + RESET);
	}
	public void verifyNewCategoryInBrandSection(String expectedBrand) {

	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String CYAN   = "\u001B[36m";
	    String BLUE   = "\u001B[34m";
	    String RESET  = "\u001B[0m";
	    HomePage home = new HomePage(driver);
		home.homeLaunch();
	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    System.out.println(CYAN + "━━━━━━━━ CATEGORY REDIRECT TEST ━━━━━━━━" + RESET);

	    // Click Toggle Menu
	    WebElement toggleMenu = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'toggle_icon')]")));
	    toggleMenu.click();
	    System.out.println(BLUE + "☰ Toggle menu opened" + RESET);
	    Common.waitForElement(5);

	    System.out.println(CYAN + "━━━━ VERIFY NEW CATEGORY IN BRAND SECTION ━━━━" + RESET);

	    String xpath = "//span[normalize-space()='" + expectedBrand + "']" +
	               "/ancestor::div[contains(@class,'brand_categories')]" +
	               "//span[normalize-space()='" + menuName + "']";

	WebElement category = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	    	if(category.isDisplayed()) {
	    	    System.out.println(GREEN + "✅ Category displayed under " + expectedBrand + RESET);
	    	} else {
	    	    System.out.println(RED + "❌ Category not found under " + expectedBrand + RESET);
	    	    Assert.fail("Category not displayed under brand section");
	    	}

	    System.out.println(CYAN + "━━━━━━━━ VERIFICATION COMPLETED ━━━━━━━━" + RESET);
	}
	
//TC-01
	public void verifyLandingPageZlaataIndiaCategory() {
		
		fillZLMenuFormForLandingPage("/zlaata-india/all","Zlaata India");
		
		verifyNewCategoryInBrandSection("ZLAATA INDIA");
		
		verifyLandingPageZlaataIndiaCategoryRedirect("/zlaata-india/all");
	}
	
//TC-02
		public void verifyLandingPageBossLadyCategory() {
			
			fillZLMenuFormForLandingPage("/boss-lady/all","Boss Lady");
			
			verifyNewCategoryInBrandSection("BOSS LADY");
			
			verifyLandingPageZlaataIndiaCategoryRedirect("/boss-lady/all");
		}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WebDriver gmail(String browserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isAt() {
		// TODO Auto-generated method stub
		return false;
	}
}
