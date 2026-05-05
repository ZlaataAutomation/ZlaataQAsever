package pages;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.ExpressDeliveryObjRepo;
import utils.Common;

import org.openqa.selenium.ElementClickInterceptedException;


public final class ExpressDeliveryPage extends ExpressDeliveryObjRepo {
	
	
	public ExpressDeliveryPage(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	
	public void adminLogin() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
		type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
		type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
		click(adminLogin);
		System.out.println("✅ Admin Login Successfull");

	}
	
	private static final String RESET  = "\u001B[0m";
	private static final String GREEN  = "\u001B[32m";
	private static final String RED    = "\u001B[31m";
	private static final String YELLOW = "\u001B[33m";
	private static final String BLUE   = "\u001B[34m";
	private static final String CYAN   = "\u001B[36m";
	
	
	public void deleteAllProductsFromCart() {

	    System.out.println(CYAN + "========== DELETE ALL PRODUCTS FROM CART ==========" + RESET);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[contains(@class,'Cls_cart_btn')]")));

	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", cartBtn);

	    cartBtn.click();
	    Common.waitForElement(1);

	    boolean isCartEmpty = false;

	    try {
	        isCartEmpty = driver.findElement(
	                By.xpath("//h5[contains(text(),'Your bag is empty')]")
	        ).isDisplayed();
	    } catch (NoSuchElementException e) {
	        isCartEmpty = false;
	    }

	    if (isCartEmpty) {
	        System.out.println(YELLOW + "🛍️ Cart already empty." + RESET);
	        return;
	    }

	    while (true) {

	        List<WebElement> deleteBtns = driver.findElements(
	                By.xpath("//div[@title='Delete']"));

	        if (deleteBtns.isEmpty()) {
	            System.out.println(GREEN + "✅ All products deleted." + RESET);
	            break;
	        }

	        for (int i = 0; i < deleteBtns.size(); i++) {
	            try {

	                deleteBtns = driver.findElements(By.xpath("//div[@title='Delete']"));
	                WebElement deleteBtn = deleteBtns.get(i);

	                ((JavascriptExecutor) driver).executeScript(
	                        "arguments[0].scrollIntoView({block:'center'});", deleteBtn);

	                Common.waitForElement(1);

	                ((JavascriptExecutor) driver).executeScript(
	                        "arguments[0].click();", deleteBtn);

	                System.out.println(GREEN + "🗑️ Product deleted" + RESET);
	                Common.waitForElement(2);

	            } catch (Exception e) {
	                System.out.println(YELLOW + "⚠️ Retry deleting..." + RESET);
	                break;
	            }
	        }
	    }
	}
	String productDetailsName;
	String PDNInAdminPanel;

	private void RandomProduct() {

	    System.out.println(CYAN + "========== RANDOM PRODUCT SELECTION ==========" + RESET);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    Actions actions = new Actions(driver);
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    System.out.println(BLUE + "🔍 Navigating to category..." + RESET);

	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(randomcategory).click().perform();
	    Common.waitForElement(2);

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[@class='prod_listing_card']")
	            )
	    );

	    Assert.assertTrue(RED + "❌ No products found" + RESET, products.size() > 0);

	    Collections.shuffle(products);

	    WebElement selectedProduct = products.get(0);

	    WebElement productLink = selectedProduct.findElement(
	            By.xpath(".//a[contains(@class,'product_list_name')]")
	    );

	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productLink);
	    Common.waitForElement(2);
	    js.executeScript("arguments[0].click();", productLink);

	    Common.waitForElement(2);

	    productDetailsName = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h4[@class='prod_name']")
	            )
	    ).getText();

	    System.out.println(GREEN + "🛍 PDP Product Name: " + productDetailsName + RESET);
	}
	
	public void verifyExpressDeliveryYesAndNo() {

	    System.out.println(CYAN + "========== EXPRESS DELIVERY VALIDATION ==========" + RESET);

	    driver.get(FileReaderManager.getInstance()
	            .getConfigReader()
	            .getApplicationUrl());

	    type(accessCode,
	            FileReaderManager.getInstance()
	                    .getJsonReader()
	                    .getValueFromJson("Access"));

	    click(submit);
	    click(zlaataIndiaShopButton);

	    RandomProduct();

	    boolean expressAvailableInitially = false;

	    try {
	        if (expressDelivery.isDisplayed()) {
	            expressAvailableInitially = true;
	            System.out.println(GREEN + "✅ Initial State = YES" + RESET);
	        }
	    } catch (Exception e) {
	        System.out.println(RED + "❌ Initial State = NO" + RESET);
	    }

	    if (expressAvailableInitially) {

	        adminLogin();
	        Common.waitForElement(2);

	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        Common.waitForElement(2);

	        productDetailMenu.click();
	        Common.waitForElement(2);

	        productSearchBox.sendKeys(productDetailsName);
	        Common.waitForElement(2);

	        productSearchBox.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        editButtonOnProductSectionElement.click();
	        Common.waitForElement(2);

	        itemProductButton.click();
	        Common.waitForElement(2);

	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        List<WebElement> productFields = driver.findElements(
	                By.xpath("//label[text()='Product Detail Name']/following-sibling::input")
	        );

	        boolean isMatched = false;

	        for (WebElement field : productFields) {

	            js.executeScript("arguments[0].scrollIntoView(true);", field);
	            Common.waitForElement(1);

	            PDNInAdminPanel = field.getAttribute("value");

	            System.out.println(BLUE + "Checking Admin Product Name : " + PDNInAdminPanel + RESET);

	            if (PDNInAdminPanel != null &&
	                    PDNInAdminPanel.trim().equalsIgnoreCase(productDetailsName.trim())) {

	                System.out.println(GREEN + "✅ Match Found : " + PDNInAdminPanel + RESET);
	                isMatched = true;
	                break;
	            }
	        }

	        Assert.assertTrue(RED + "❌ Product name not matching in Admin Panel" + RESET, isMatched);

	        WebElement noRadio = driver.findElement(
	                By.xpath("//input[@name='filters[0][delivery_speed]' and @value='0']")
	        );

	        if (!noRadio.isSelected()) {
	            js.executeScript("arguments[0].scrollIntoView(true);", noRadio);
	            Common.waitForElement(1);
	            js.executeScript("arguments[0].click();", noRadio);
	            System.out.println(GREEN + "✅ Changed Express Delivery to NO" + RESET);
	        }

	        WebElement saveButton = driver.findElement(
	                By.xpath("//span[@data-value='save_and_back']")
	        );

	        js.executeScript("arguments[0].click();", saveButton);
	        Common.waitForElement(2);

	        js.executeScript("arguments[0].click();", clearCatchButton);
	        Common.waitForElement(5);

	        driver.get(FileReaderManager.getInstance()
	                .getConfigReader()
	                .getApplicationUrl());

	        click(zlaataIndiaShopButton);
	        click(clickOnSearchBar);

	        searchBoxPlaceholder.sendKeys(productDetailsName);
	        searchBoxPlaceholder.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        driver.findElement(By.xpath("//a[contains(@class,'product_list_name')]")).click();

	        try {
	            if (expressDelivery.isDisplayed()) {
	                Assert.fail(RED + "❌ Express Delivery still visible after selecting NO" + RESET);
	            }
	        } catch (Exception e) {
	            System.out.println(GREEN + "✅ Express Delivery removed successfully" + RESET);
	        }

	        Common.waitForElement(2);

	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        Common.waitForElement(2);

	        productDetailMenu.click();
	        Common.waitForElement(2);

	        productSearchBox.clear();
	        productSearchBox.sendKeys(productDetailsName);
	        Common.waitForElement(2);

	        productSearchBox.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        editButtonOnProductSectionElement.click();
	        Common.waitForElement(2);

	        itemProductButton.click();
	        Common.waitForElement(2);

	        List<WebElement> productFields2 = driver.findElements(
	                By.xpath("//label[text()='Product Detail Name']/following-sibling::input")
	        );

	        boolean isMatched2 = false;

	        for (WebElement field : productFields2) {

	            js.executeScript("arguments[0].scrollIntoView(true);", field);
	            Common.waitForElement(1);

	            PDNInAdminPanel = field.getAttribute("value");

	            System.out.println(BLUE + "Checking Admin Product Name Again : " + PDNInAdminPanel + RESET);

	            if (PDNInAdminPanel != null &&
	                    PDNInAdminPanel.trim().equalsIgnoreCase(productDetailsName.trim())) {

	                System.out.println(GREEN + "✅ Match Found Again : " + PDNInAdminPanel + RESET);
	                isMatched2 = true;
	                break;
	            }
	        }

	        Assert.assertTrue(RED + "❌ Product name not matching in Admin Panel (Second Login)" + RESET, isMatched2);

	        WebElement yesRadio = driver.findElement(
	                By.xpath("//input[@name='filters[0][delivery_speed]' and @value='1']")
	        );

	        if (!yesRadio.isSelected()) {
	            js.executeScript("arguments[0].scrollIntoView(true);", yesRadio);
	            Common.waitForElement(1);
	            js.executeScript("arguments[0].click();", yesRadio);
	            System.out.println(GREEN + "✅ Changed Express Delivery back to YES" + RESET);
	        }

	        WebElement saveButton2 = driver.findElement(
	                By.xpath("//span[@data-value='save_and_back']")
	        );

	        js.executeScript("arguments[0].click();", saveButton2);
	        Common.waitForElement(2);

	        js.executeScript("arguments[0].click();", clearCatchButton);
	        Common.waitForElement(5);

	        driver.get(FileReaderManager.getInstance()
	                .getConfigReader()
	                .getApplicationUrl());

	        click(zlaataIndiaShopButton);
	        click(clickOnSearchBar);

	        searchBoxPlaceholder.clear();
	        searchBoxPlaceholder.sendKeys(productDetailsName);
	        searchBoxPlaceholder.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        WebElement productElement = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//a[contains(@class,'product_list_name')]")
	                )
	        );

	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});",
	                productElement
	        );

	        Common.waitForElement(2);
	        productElement.click();

	        try {
	            if (expressDelivery.isDisplayed()) {
	                String exp = expressDelivery.getText();
	                System.out.println(GREEN + "✅ Express Delivery visible again after YES : " + exp + RESET);
	            } else {
	                Assert.fail(RED + "❌ Express Delivery not visible after changing back to YES" + RESET);
	            }
	        } catch (Exception e) {
	            Assert.fail(RED + "❌ Express Delivery not visible after changing back to YES" + RESET);
	        }

	        buyNowButton.click();
	        Common.waitForElement(2);

	        try {

	            WebElement expressBtn = wait.until(
	                    ExpectedConditions.visibilityOf(expressdeliveryButton)
	            );

	            ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView({block: 'center'});",
	                    expressBtn
	            );

	            Common.waitForElement(2);

	            if (expressBtn.isDisplayed() && expressBtn.isEnabled()) {

	                js.executeScript("arguments[0].click();", expressBtn);
	                System.out.println(GREEN + "✅ Express Delivery button clicked successfully" + RESET);

	                Common.waitForElement(2);

	                String shippingAmount = expressShippingAmount.getText();
	                System.out.println(GREEN + "✅ Express Shipping Amount : " + shippingAmount + RESET);

	            } else {
	                Assert.fail(RED + "❌ Express Delivery button is not clickable" + RESET);
	            }

	        } catch (Exception e) {
	            Assert.fail(RED + "❌ Unable to click Express Delivery button or get shipping amount" + RESET);
	        }
	    }

	    else {

	        adminLogin();
	        Common.waitForElement(2);

	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        Common.waitForElement(2);

	        productDetailMenu.click();
	        Common.waitForElement(2);

	        productSearchBox.sendKeys(productDetailsName);
	        Common.waitForElement(2);

	        productSearchBox.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        editButtonOnProductSectionElement.click();
	        Common.waitForElement(2);

	        itemProductButton.click();
	        Common.waitForElement(2);

	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        List<WebElement> productFields = driver.findElements(
	                By.xpath("//label[text()='Product Detail Name']/following-sibling::input")
	        );

	        boolean isMatched = false;

	        for (WebElement field : productFields) {

	            js.executeScript("arguments[0].scrollIntoView(true);", field);
	            Common.waitForElement(1);

	            PDNInAdminPanel = field.getAttribute("value");

	            System.out.println(BLUE + "Checking Admin Product Name : " + PDNInAdminPanel + RESET);

	            if (PDNInAdminPanel != null &&
	                    PDNInAdminPanel.trim().equalsIgnoreCase(productDetailsName.trim())) {

	                System.out.println(GREEN + "✅ Match Found : " + PDNInAdminPanel + RESET);
	                isMatched = true;
	                break;
	            }
	        }

	        Assert.assertTrue(RED + "❌ Product name not matching in Admin Panel" + RESET, isMatched);

	        WebElement yesRadio = driver.findElement(
	                By.xpath("//input[@name='filters[0][delivery_speed]' and @value='1']")
	        );

	        if (!yesRadio.isSelected()) {
	            js.executeScript("arguments[0].scrollIntoView(true);", yesRadio);
	            Common.waitForElement(1);
	            js.executeScript("arguments[0].click();", yesRadio);
	            System.out.println(GREEN + "✅ Changed Express Delivery to YES" + RESET);
	        }

	        WebElement saveButton = driver.findElement(
	                By.xpath("//span[@data-value='save_and_back']")
	        );

	        js.executeScript("arguments[0].click();", saveButton);
	        Common.waitForElement(2);

	        js.executeScript("arguments[0].click();", clearCatchButton);
	        Common.waitForElement(5);

	        driver.get(FileReaderManager.getInstance()
	                .getConfigReader()
	                .getApplicationUrl());

	        click(zlaataIndiaShopButton);
	        click(clickOnSearchBar);

	        searchBoxPlaceholder.sendKeys(productDetailsName);
	        searchBoxPlaceholder.sendKeys(Keys.ENTER);
	        Common.waitForElement(2);

	        WebElement productElement = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//a[contains(@class,'product_list_name')]")
	                )
	        );

	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});",
	                productElement
	        );

	        Common.waitForElement(2);
	        productElement.click();

	        try {
	            if (expressDelivery.isDisplayed()) {
	                String exp = expressDelivery.getText();
	                System.out.println(GREEN + "✅ Express Delivery Available After Verification : " + exp + RESET);
	            } else {
	                Assert.fail(RED + "❌ Express Delivery option is not available" + RESET);
	            }
	        } catch (Exception e) {
	            Assert.fail(RED + "❌ Express Delivery option is not available" + RESET);
	        }

	        buyNowButton.click();
	        Common.waitForElement(2);

	        try {

	            WebElement expressBtn = wait.until(
	                    ExpectedConditions.visibilityOf(expressdeliveryButton)
	            );

	            ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView({block: 'center'});",
	                    expressBtn
	            );

	            Common.waitForElement(2);

	            if (expressBtn.isDisplayed() && expressBtn.isEnabled()) {

	                js.executeScript("arguments[0].click();", expressBtn);
	                System.out.println(GREEN + "✅ Express Delivery button clicked successfully" + RESET);

	                Common.waitForElement(2);

	                String shippingAmount = expressShippingAmount.getText();
	                System.out.println(GREEN + "✅ Express Shipping Amount : " + shippingAmount + RESET);

	            } else {
	                Assert.fail(RED + "❌ Express Delivery button is not clickable" + RESET);
	            }

	        } catch (Exception e) {
	            Assert.fail(RED + "❌ Unable to click Express Delivery button or get shipping amount" + RESET);
	        }
	    }
	}
	
	
	
	
	public void soryByOptionInPLPForExpressDelivery() {
		
		
		driver.get(FileReaderManager.getInstance()
	            .getConfigReader()
	            .getApplicationUrl());

	    type(accessCode,
	            FileReaderManager.getInstance()
	                    .getJsonReader()
	                    .getValueFromJson("Access"));

	    click(submit);
	    click(zlaataIndiaShopButton);
	    
	    System.out.println(CYAN + "========== RANDOM PRODUCT SELECTION ==========" + RESET);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    Actions actions = new Actions(driver);
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    System.out.println(BLUE + "🔍 Navigating to category..." + RESET);

	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(randomcategory).click().perform();
	    Common.waitForElement(2);

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[@class='prod_listing_card']")
	            )
	    );
	    
	    Assert.assertTrue(RED + "❌ No products found" + RESET, products.size() > 0);

	    sortByButton.click();
	    Common.waitForElement(2);
	 // =========================
	 // CLICK EXPRESS DELIVERY
	 // =========================
	 By expressBy = By.xpath("//li[contains(.,'Express Delivery')]");

	 List<WebElement> expressList = driver.findElements(expressBy);

	 if (expressList.isEmpty()) {
	     Assert.fail("❌ Express Delivery option is NOT available in Sort By");
	 }

	 // wait + click

	 WebElement expressBtn = wait.until(
	         ExpectedConditions.elementToBeClickable(expressList.get(0))
	 );

	 ((JavascriptExecutor) driver).executeScript(
	         "arguments[0].scrollIntoView({block:'center'});", expressBtn
	 );

	 ((JavascriptExecutor) driver).executeScript(
	         "arguments[0].click();", expressBtn
	 );

	 System.out.println("✅ Express Delivery option clicked");

	 // =========================
	 // WAIT FOR PRODUCT REFRESH
	 // =========================
	 wait.until(ExpectedConditions.stalenessOf(products.get(0)));

	 // re-fetch updated products (IMPORTANT)
	 List<WebElement> updatedProducts = wait.until(
	         ExpectedConditions.presenceOfAllElementsLocatedBy(
	                 By.xpath("//div[contains(@class,'prod_listing_card')]")
	         )
	 );

	 // =========================
	 // CLICK FIRST PRODUCT
	 // =========================
	 WebElement selectedProduct = updatedProducts.get(0);

	 WebElement productLink = selectedProduct.findElement(
	         By.xpath(".//a[contains(@class,'product_list_name')]")
	 );

	 wait.until(ExpectedConditions.elementToBeClickable(productLink));
	 productLink.click();
	 
	 
	 try {
		    if (expressDelivery.isDisplayed()) {
		        System.out.println("✅ Express Delivery Available");
		    } else {
		        Assert.fail("❌ Express Delivery NOT Available");
		    }
		} catch (Exception e) {
		    Assert.fail("❌ Express Delivery NOT Available");
		}
	    
	    

	}
	
	
	public void expressDeliveryOption() {

		verifyExpressDeliveryYesAndNo();

	}

	 public void expressDeliveryOptionWorkingfineInSortBySectionInPLP() {
		
		 soryByOptionInPLPForExpressDelivery();
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
