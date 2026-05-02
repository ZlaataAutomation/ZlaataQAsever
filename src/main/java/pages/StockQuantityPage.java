package pages;

import java.time.Duration;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.StockQuantityObjRepo;
import utils.Common;

public class StockQuantityPage extends StockQuantityObjRepo{
	public StockQuantityPage(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}
	
	public void adminLogin() {
		Common.waitForElement(2);
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	    type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
	    type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
	    click(adminLogin);
	    System.out.println("✅ Admin Login Successfull");
	    
	}
	
	public void verifyAndUpdateStock() throws InterruptedException {
		adminLogin();
	     Common.waitForElement(2);
	        click(searchMenu);
	        type(searchMenu, "Track Inventory");
	        click(clickTrackInvetory);
	        System.out.println("✅ Selected Track Inventory");
	        Common.waitForElement(2);
	    
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // ✅ Step 1: Click "Product Colour Name / SKU"
	        WebElement productColorDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[contains(text(),'Product Colour Name')]")
	        ));
	        productColorDropdown.click();

	        System.out.println("✅ Clicked Product Colour Name");
	        Common.waitForElement(2);
	        // ✅ Step 2: Enter value in search box
	        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[contains(@class,'select2-search__field')]")
	        ));

	        searchBox.sendKeys("Test By Auto1");
	        Common.waitForElement(2);
	        searchBox.sendKeys(Keys.ENTER);

	        System.out.println("✅ Entered product in search");

	        // 🔄 Small wait for result load
	        Thread.sleep(2000);

	        // ✅ Step 3: Get stock input field
	        WebElement stockInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[contains(@class,'quantity_increment')]")
	        ));

	        String stockValueStr = stockInput.getAttribute("value").trim();
	        int stockValue = stockValueStr.isEmpty() ? 0 : Integer.parseInt(stockValueStr);

	        System.out.println("📦 Current Stock: " + stockValue);

	        // ✅ Step 4: Condition
	        if (stockValue == 0) {

	            System.out.println("⚠ Stock is 0 → Updating to 1");

	            stockInput.clear();
	            stockInput.sendKeys("1");

	            // ✅ Click tick mark
	            WebElement tickBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//i[contains(@class,'la-check-circle')]")
	            ));

	            js.executeScript("arguments[0].click();", tickBtn);

	            System.out.println("✅ Stock updated to 1 and saved");
	            
	            // Clear cache
	    	    Common.waitForElement(3);
	    	    click(clearCatchButton);
	    	    System.out.println("✅ Cleared cache");

	        } else {
	            System.out.println("✅ Stock already available → No action needed");
	        }
		
	}
	 public void userLoginApp() {
		 LoginPage home = new LoginPage(driver);
		    home.userLogin();
		}
	 
	 public void deleteAllProductsFromCart() {
		    // Open cart
		    driver.findElement(By.xpath("//button[contains(@class,'Cls_cart_btn') and contains(@class,'header_cta_btn')]")).click();
		    Common.waitForElement(2);

		    // ✅ STEP 1: Check if cart is already empty
		    try {
		        if (driver.findElement(By.xpath("//h5[contains(text(),'Your bag is empty')]")).isDisplayed()) {
		            System.out.println("🛍️ Cart already empty. No delete action needed.");
		            return; // Stop method immediately
		        }
		    } catch (NoSuchElementException ignored) {
		        // Cart is NOT empty, proceed to delete
		    }

		    // ✅ STEP 2: Delete products one by one
		    while (true) {
		        try {
		            WebElement deleteBtn = driver.findElement(By.xpath("//div[@title='Delete']"));
		            deleteBtn.click();
		            System.out.println("🗑️ Product deleted");
		            Common.waitForElement(1); 
		        } catch (NoSuchElementException e) {
		            System.out.println("✅ No more products to delete.");
		            break;
		        } catch (Exception e) {
		            System.out.println("⚠️ Error while deleting: " + e.getMessage());
		            break;
		        }
		    }

		    // ✅ STEP 3: Final confirmation
		    try {
		        if (driver.findElement(By.xpath("//h5[contains(text(),'Your bag is empty')]")).isDisplayed()) {
		            System.out.println("🛍️ Cart is empty, Continue Shopping displayed.");
		        }
		    } catch (NoSuchElementException e) {
		        System.out.println("ℹ️ Bag is not empty message not found.");
		    }
		}
	// Fetch from Excel
	String productName ="Test By Auto1";
	

	public void addProductToCartAndPlacedTheOrder() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    String CYAN = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String GREEN = "\u001B[32m";
	    String RED = "\u001B[31m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";

	    System.out.println(CYAN + line + RESET);
	    System.out.println(GREEN + "🚀 Starting Order Placement Flow..." + RESET);
	    System.out.println(CYAN + line + RESET);

	    userLoginApp();
	    
	   deleteAllProductsFromCart();

	    // ✅ Search product
	    System.out.println(YELLOW + "🔍 Searching for product: " + productName + RESET);
	    Common.waitForElement(3);
	    wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
	    searchIcon.click();
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(userSearchBox));
	    userSearchBox.clear();
	    userSearchBox.sendKeys(productName);
	    userSearchBox.sendKeys(Keys.ENTER);
	    Common.waitForElement(3);
	

	    // ---------------- STEP 2: CLICK PRODUCT ----------------
	    WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(@class,'product_list_name') and contains(text(),'" + productName + "')]")
	    ));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", productLink);
	    js.executeScript("arguments[0].click();", productLink);

	    System.out.println("✅ Clicked product");
	    Common.waitForElement(2);
//	    wait.until(ExpectedConditions.elementToBeClickable(addToBag));
//	    click(addToBag);
//	    System.out.println(GREEN + "✅ Clicked 'Add To Bag'" + RESET);
//
//	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
	    click(addToCartBtn);
	    System.out.println(GREEN + "✅ Added product to cart" + RESET);

	    js.executeScript("window.scrollBy(0, -500);");
	    
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(bagIcon));
	    click(bagIcon);
	    System.out.println(GREEN + "✅ Opened cart" + RESET);


	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
	    click(continueBtn);
	    System.out.println(GREEN + "✅ Clicked Continue Button" + RESET);
	    
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
	    click(continueBtn);
	    System.out.println(GREEN + "✅ Clicked Address Page Continue Button" + RESET);
	    
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(selectNetBank));
	    click(selectNetBank);
	    System.out.println(GREEN + "✅ Select Netbanking" + RESET);
	    
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
	    click(placeOrderBtn);
	    System.out.println(GREEN + "✅ Clicked Place Order" + RESET);
	    
	    Thread.sleep(5000);    
	 // ✅ 1. Switch to Razorpay iframe (you already have this)
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
	            By.xpath("//iframe[contains(@name,'razorpay') or contains(@id,'razorpay') or contains(@src,'razorpay')]")
	    ));
	    System.out.println("✅ Switched to Razorpay iframe");
//
//	    // ✅ 2. Click Continue button
//	    wait.until(ExpectedConditions.elementToBeClickable(
//	            By.xpath("//button[contains(.,'Continue')]")
//	    )).click();
//	    System.out.println("✅ Continue clicked");
////
//	    // ✅ 3. Click Skip OTP
//	    wait.until(ExpectedConditions.elementToBeClickable(
//	            By.xpath("//button[contains(text(),'Skip OTP')]")
//	    )).click();
//	    System.out.println("✅ Skipped OTP");
//
//	    // ✅ 4. Enter Pincode
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(
//	            By.id("zipcode")
//	    )).sendKeys("560001");
//
//	    // ✅ 5. Enter City auto-filled → skip  
//	    // ✅ 6. Enter Name
//	    driver.findElement(By.id("name")).sendKeys("Saroj Test");
//
//	    // ✅ 7. Enter House / Building
//	    driver.findElement(By.id("line1")).sendKeys("Bangalore");
//
//	    // ✅ 8. Enter Area / Street
//	    driver.findElement(By.id("line2")).sendKeys("bjvhcgfchvbjkn");
//
//	    
//	    // ✅ 9. Click Continue (Address Submit)
//	    Common.waitForElement(3);
//	    wait.until(ExpectedConditions.elementToBeClickable(
//	            By.xpath("//button[contains(.,'Continue') and @name='new_shipping_address_cta']")
//	    )).click();
//
//	    System.out.println("✅ Address submitted successfully");
	    
	    
	    

	    // ✅ 3. Select Netbanking option
	    Common.waitForElement(4);
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[@data-testid='Netbanking']")
	    )).click();

	    // ✅ 4. Select HDFC Bank
	    Common.waitForElement(2);
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//div[@role='button' and .//span[contains(text(),'HDFC Bank')]])[1]")
	    )).click();

	    // ⬅️ Optional: Switch back to main page after selecting
	    driver.switchTo().defaultContent();

	   

	    // Switch to Razorpay window
	    String mainWindow = driver.getWindowHandle();
	    Thread.sleep(3000);
	    Set<String> allWindows = driver.getWindowHandles();
	    for (String window : allWindows) {
	        if (!window.equals(mainWindow)) {
	            driver.switchTo().window(window);
	            System.out.println(GREEN + "✅ Switched to Razorpay window" + RESET);
	            break;
	        }
	    }

	    // ✅ Click Success button
	    WebElement successBtn = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//button[@data-val='S' and normalize-space(text())='Success']")
	    ));
	    successBtn.click();
	    System.out.println(GREEN + "💳 Payment Success clicked" + RESET);

	    Thread.sleep(5000);
	    driver.switchTo().window(mainWindow);
	    System.out.println(GREEN + "🔙 Switched back to main window" + RESET);

	    // ✅ Confirm order
	    Thread.sleep(9000);
	    try {
	        WebElement confirmMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h5[@class='checkout_success_heading' and normalize-space()='Order Confirmed']")
	        ));

	        if (confirmMsg.isDisplayed()) {
	            System.out.println(GREEN + "🎉 Order Confirmed Successfully!" + RESET);

	            wait.until(ExpectedConditions.elementToBeClickable(viewOrderDetails));
	            click(viewOrderDetails);
	            System.out.println(GREEN + "🧾 Clicked View Order Details" + RESET);
	            
	    	    WebElement cancelBtn = driver.findElement(By.xpath("//button[@class='prod_cancel_btn']"));
	    	    if (cancelBtn.isDisplayed()) {
	    	        System.out.println("❌ Cancel Button: Displayed ✅");
	    	    }
	    	    
	    	             
			    		       
	        } else {
	            System.out.println(RED + "❌ Order confirmation message not visible" + RESET);
	            Assert.fail("⏰ Order confirmation message not found within timeout");
	        }

	    } catch (TimeoutException e) {
	        System.out.println(RED + "⏰ Order confirmation message not found within timeout" + RESET);
	        Assert.fail("⏰ Order confirmation message not found within timeout");
	    }

	   
	}


	
	public void verifyStockReducedToZero() throws InterruptedException {
		Common.waitForElement(2);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // ---------------- STEP 1: SEARCH PRODUCT ----------------
	    WebElement productColorDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(text(),'Product Colour Name')]")
	    ));
	    productColorDropdown.click();

	    Common.waitForElement(2);

	    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[contains(@class,'select2-search__field')]")
	    ));

	    searchBox.clear();
	    searchBox.sendKeys("Test By Auto1");
	    Common.waitForElement(2);

	    searchBox.sendKeys(Keys.ENTER);

	    Thread.sleep(2000);

	    // ---------------- STEP 2: GET STOCK ----------------
	    WebElement stockInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[contains(@class,'quantity_increment')]")
	    ));

	    String stockValueStr = stockInput.getAttribute("value").trim();
	    int stockValue = stockValueStr.isEmpty() ? 0 : Integer.parseInt(stockValueStr);

	    System.out.println("📦 Current Stock After Order: " + stockValue);

	    // ---------------- STEP 3: VALIDATION ----------------
	    if (stockValue == 0) {
	        System.out.println("✅ Stock reduced successfully to 0 → PASS");
	    } else {
	        Assert.fail("❌ Stock NOT reduced correctly. Expected: 0 | Actual: " + stockValue);
	    }
	}
	
	
	
	public void launchHomepage(String page) {

        HomePage home = new HomePage(driver);
        home.homeLaunch();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h2[normalize-space()='" + page + "']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

        System.out.println("Clicked " + page + " Home Page Banner");
    }
	
	public void logionWithlaunchHomepage(String page) {

		userLoginApp();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h2[normalize-space()='" + page + "']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

        System.out.println("Clicked " + page + " Home Page Banner");
    }
	public void verifyOutOfStockProductFlow() throws InterruptedException {

	    String YELLOW = "\u001B[33m";
	    String RESET  = "\u001B[0m";

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // ---------------- STEP 1: SEARCH PRODUCT ----------------
	    System.out.println(YELLOW + "🔍 Searching for product: " + productName + RESET);

	    Common.waitForElement(2);

	    wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();

	    wait.until(ExpectedConditions.visibilityOf(userSearchBox));
	    userSearchBox.clear();
	    userSearchBox.sendKeys(productName);
	    userSearchBox.sendKeys(Keys.ENTER);

	    Common.waitForElement(3);

	    // ---------------- STEP 2: CLICK PRODUCT ----------------
	    WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(@class,'product_list_name') and contains(text(),'" + productName + "')]")
	    ));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", productLink);
	    js.executeScript("arguments[0].click();", productLink);

	    System.out.println("✅ Clicked product");
	    Common.waitForElement(2);
	    // ---------------- STEP 3: VERIFY NOTIFY BUTTON ----------------
	    WebElement notifyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//button[contains(@class,'cls_add_notify')]")
	    ));

	    String notifyText = notifyBtn.getText().trim();

	    if (notifyText.toLowerCase().contains("notify")) {
	        System.out.println("✅ 'Notify Me' button is displayed");
	    } else {
	        Assert.fail("❌ Notify button NOT displayed properly");
	    }

	    // ---------------- STEP 4: VERIFY SIZE DISABLED ----------------
	    WebElement sizeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'prod_size_name') and contains(@class,'disabled')]")
	    ));

	    String sizeClass = sizeElement.getAttribute("class");

	    if (sizeClass.contains("disabled")) {
	        System.out.println("✅ Size is disabled");
	    } else {
	        Assert.fail("❌ Size is NOT disabled");
	    }

	 

	    System.out.println("🎉 OUT OF STOCK VALIDATION PASSED");
	}
	
	
	public void verifyAndUpdateStockForSoldOut() throws InterruptedException {
		adminLogin();
	     Common.waitForElement(2);
	        click(searchMenu);
	        type(searchMenu, "Track Inventory");
	        click(clickTrackInvetory);
	        System.out.println("✅ Selected Track Inventory");
	        Common.waitForElement(2);
	    
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // ✅ Step 1: Click "Product Colour Name / SKU"
	        WebElement productColorDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[contains(text(),'Product Colour Name')]")
	        ));
	        productColorDropdown.click();

	        System.out.println("✅ Clicked Product Colour Name");
	        Common.waitForElement(2);
	        // ✅ Step 2: Enter value in search box
	        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[contains(@class,'select2-search__field')]")
	        ));

	        searchBox.sendKeys("Test By Auto2");
	        Common.waitForElement(2);
	        searchBox.sendKeys(Keys.ENTER);

	        System.out.println("✅ Entered product in search");

	        // 🔄 Small wait for result load
	        Thread.sleep(2000);

	     // ✅ Get stock input field
	        WebElement stockInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("(//input[contains(@class,'quantity_increment')])[3]")
	        ));

	        String stockValueStr = stockInput.getAttribute("value").trim();
	        int stockValue = stockValueStr.isEmpty() ? 0 : Integer.parseInt(stockValueStr);

	        System.out.println("📦 Current Stock: " + stockValue);

	        // ✅ Condition
	        if (stockValue > 0) {

	            System.out.println("⚠ Stock > 0 → Updating to 0");

	            stockInput.clear();
	            stockInput.sendKeys("0");

	            // ✅ Click tick mark
	            WebElement tickBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("(//i[contains(@class,'la-check-circle')])[3]")
	            ));

	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tickBtn);

	            System.out.println("✅ Stock updated to 0 and saved");

	            // ✅ Optional: Clear cache
	            Common.waitForElement(3);
	            click(clearCatchButton);
	            System.out.println("✅ Cleared cache");

	        } else {
	            System.out.println("✅ Stock already 0 → No action needed");
	        }
		
	}
	String productName2 ="Test By Auto2";
	public void verifAlmostSoldOutProductFlow() throws InterruptedException {

	    String YELLOW = "\u001B[33m";
	    String RESET  = "\u001B[0m";

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // ---------------- STEP 1: SEARCH PRODUCT ----------------
	    System.out.println(YELLOW + "🔍 Searching for product: " + productName2 + RESET);

	    Common.waitForElement(2);

	    wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();

	    wait.until(ExpectedConditions.visibilityOf(userSearchBox));
	    userSearchBox.clear();
	    userSearchBox.sendKeys(productName2);
	    userSearchBox.sendKeys(Keys.ENTER);

	    Common.waitForElement(3);

	    // ---------------- STEP 2: CLICK PRODUCT ----------------
	    WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(@class,'product_list_name') and contains(text(),'" + productName2 + "')]")
	    ));

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", productLink);
	    js.executeScript("arguments[0].click();", productLink);

	    System.out.println("✅ Clicked product");
	    Common.waitForElement(2);
	 // ---------------- STEP 3: VERIFY "ONLY FEW LEFT" MESSAGE ----------------
	    WebElement fewLeftMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'prod_hurryUp')]")
	    ));

	    String msgText = fewLeftMsg.getText().trim();

	    if (msgText.equalsIgnoreCase("Only Few Left")) {
	        System.out.println("✅ 'Only Few Left' message is displayed");
	    } else {
	        Assert.fail("❌ 'Only Few Left' message NOT displayed. Found: " + msgText);
	    }


	    // ---------------- STEP 4: VERIFY ADD TO CART BUTTON ----------------
	    WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//button[contains(@class,'prod_add_cart_btn')]")
	    ));

	    if (addToCartBtn.isDisplayed() && addToCartBtn.isEnabled()) {
	        System.out.println("✅ Add to Cart button is visible & enabled");
	    } else {
	        Assert.fail("❌ Add to Cart button is NOT enabled/visible");
	    }


	    // ---------------- STEP 5: VERIFY BUY NOW BUTTON ----------------
	    WebElement buyNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//button[contains(@class,'prod_buy_now_btn')]")
	    ));

	    if (buyNowBtn.isDisplayed() && buyNowBtn.isEnabled()) {
	        System.out.println("✅ Buy Now button is visible & enabled");
	    } else {
	        Assert.fail("❌ Buy Now button is NOT enabled/visible");
	    }


	    System.out.println("🎉 FEW LEFT STOCK VALIDATION PASSED");
	}
	
//TC-01
	public void validateStockQuantity() throws InterruptedException {
		
		verifyAndUpdateStock();
		
		addProductToCartAndPlacedTheOrder();
		
		verifyStockReducedToZero();
		
		launchHomepage("ZLAATA INDIA");

		verifyOutOfStockProductFlow();
		
	}
	
	public void validateAlmostSoldOut() throws InterruptedException {
		
		verifyAndUpdateStockForSoldOut();
		
		logionWithlaunchHomepage("ZLAATA INDIA");
		
		verifAlmostSoldOutProductFlow();
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
