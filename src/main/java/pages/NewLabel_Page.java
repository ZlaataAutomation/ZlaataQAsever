package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.NewLabel_ObjRepo;
import utils.Common;

public class NewLabel_Page extends NewLabel_ObjRepo{
	public NewLabel_Page(WebDriver driver) 
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

	String productlistingName;

	public String takeRandomProductFromAll() {
	
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);
	    Common.waitForElement(1);

	 // Hover on Shop
	    WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[contains(@class,'header_nav_link') and normalize-space()='Shop']")
	    ));
	    actions.moveToElement(shopMenu).perform();

	    // Click All
	    WebElement allButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'dropdown_content')]//a[normalize-space()='dresses']")
	    ));
	    allButton.click();

	    System.out.println("✅ Clicked on 'All' under Shop menu");
	    Common.waitForElement(2);
	    // Collect all product cards
	    List<WebElement> products = wait.until(ExpectedConditions
	            .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'prod_listing_card')]")));

	    if (products.isEmpty()) {
	        System.out.println("⚠️ No products found on listing page!");
	        return null;
	    }

	    Random rand = new Random();
	    int maxAttempts = Math.min(5, products.size());
	    boolean productFound = false;

	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {

	        int randomIndex = rand.nextInt(products.size()) + 1;
	        System.out.println("🎯 Checking random product index: " + randomIndex);

	        WebElement productCard = driver.findElement(
	                By.xpath("(//div[contains(@class,'prod_listing_card')])[" + randomIndex + "]"));

	        String name = productCard.findElement(
	                By.xpath(".//a[contains(@class,'product_list_name')]"))
	                .getText().trim();
//	        List<WebElement> stockLabels = productCard.findElements(
//	                By.xpath(".//span[contains(@class,'prod_listing_hurry') and normalize-space()='Out of Stock']"));
//
//	        boolean isOutOfStock = !stockLabels.isEmpty() && stockLabels.get(0).isDisplayed();
//
//	        if (isOutOfStock) {
//	            System.out.println("❌ '" + name + "' is OUT OF STOCK. Retrying...");
//	            continue;
//	        }

	        // Found in-stock product
	        String  productName = name;

	        WebElement productNameElement = productCard.findElement(
	                By.xpath(".//a[contains(@class,'product_list_name')]"));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productNameElement);

	        productFound = true;
	        System.out.println("✅ Selected random in-stock product: " + productName);
	        break;
	    }

	    if (!productFound) {
	        System.out.println("⚠️ No in-stock product found after trying " + maxAttempts);
	        return null;
	    }
	    // Click ADD TO CART button on PDP
	    
	    productlistingName = driver.findElement(
	            By.xpath("//h3[@class='prod_name']")
	    ).getText().trim();
	    System.out.println("Product Name: " + productlistingName);
	    

	    return productlistingName;
	}
	
	String adminProductName;
	public void updateProductDate() throws InterruptedException {
		adminLogin();
	     Common.waitForElement(2);
	        click(searchMenu);
	        type(searchMenu, "Products");
	        click(clickProductReview);
	        System.out.println("✅ Selected Product Reviews");
	        Common.waitForElement(3);
	    
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        
	        System.out.println("✅ Successful redirect to Adimn Product page");
			 // Open product listing
		      ((JavascriptExecutor) driver)
	          .executeScript("arguments[0].click();", productDetailMenu);
		  //  click(productDetailMenu);  
		    System.out.println("✅ Successful click product listing menu");
		    Common.waitForElement(2);
		      ((JavascriptExecutor) driver)
	          .executeScript("arguments[0].click();", productSearchBox);
		 //   click(productSearchBox);

		    // Fetch the product name directly from Excel map
		//    String productName = Common.getValueFromTestDataMap("ProductListingName");
		    System.out.println("✅ Fetched product listing name:" + productlistingName);
		 // Search or enter the product
		    // Clear + Type
		    productSearchBox.clear();
		    productSearchBox.sendKeys(productlistingName);

		    System.out.println("✅ Product name typed: " + productlistingName);

		    // Press Enter
		    Thread.sleep(3000);
		    productSearchBox.sendKeys(Keys.ENTER);

		//    type(productSearchBox, productDetailName + Keys.ENTER);
		    Common.waitForElement(2);
		    System.out.println("✅ Entered product listing name in search box & pressed ENTER");
		    
		 // now click edit, etc…
		    Common.waitForElement(1);
		  //  waitFor(editProductButton);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", editProductButton);
		  //  click(editProductButton);
		    System.out.println("✅ Clicked product edit option");
		    // now click item, etc…
		  //  waitFor(itemProductButton);
		    Common.waitForElement(2);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", itemProductButton);
		  //  click(itemProductButton);
		    System.out.println("✅  Clicked product item option");
		    
		    // -------- Check that the product text matches Excel name --------
		 //   waitFor(productListingBoxText); 
		    Common.waitForElement(3);
		     adminProductName = productListingBoxText.getAttribute("value").trim();
		    System.out.println("  Product Listing Name: " + adminProductName);
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    String todayDate = LocalDate.now().format(formatter);

		    WebElement fromDate = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@name='filters[0][collection_from_date]']")
		        )
		    );

		    fromDate.clear();
		    fromDate.sendKeys(todayDate);

		    WebElement toDate = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@name='filters[0][collection_to_date]']")
		        )
		    );

		    toDate.clear();
		    toDate.sendKeys(todayDate);

		    System.out.println("✅ Today's date selected: " + todayDate);
		//  Save
			Common.waitForElement(3);
			((JavascriptExecutor) driver)
		       .executeScript("arguments[0].click();", saveButton);
		  //  click(saveButton);
		    System.out.println("✅ Saved collection changes");
		    //Clear Catch
		    Common.waitForElement(5);
		    waitFor(clearCatchButton);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", clearCatchButton);
		//    click(clearCatchButton);
		    System.out.println( "🧹 Cache cleared successfully." );
		    Common.waitForElement(7);
		    waitFor(clearCatchButton);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", clearCatchButton);
		//    click(clearCatchButton);
		    System.out.println( "🧹 Cache cleared successfully." );
		    
		    
		    WebElement productText = wait.until(
		    	    ExpectedConditions.visibilityOfElementLocated(
		    	        By.xpath("//span[normalize-space()='Product']")
		    	    )
		    	);

		    	Assert.assertTrue(
		    	    "❌ 'Product' text is NOT displayed!",
		    	    productText.isDisplayed()
		    	);

		    	System.out.println("✅ 'Product' text is displayed successfully.");
	}
	public void verifyNewLabelListingPage() {

	    String YELLOW = "\u001B[33m";
	    String RESET  = "\u001B[0m";

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // ---------------- STEP 1: SEARCH PRODUCT ----------------
	    System.out.println(YELLOW + "🔍 Searching for product: " + adminProductName + RESET);

	    Common.waitForElement(2);

	    wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();

	    wait.until(ExpectedConditions.visibilityOf(userSearchBox));
	    userSearchBox.clear();
	    userSearchBox.sendKeys(adminProductName);
	    userSearchBox.sendKeys(Keys.ENTER);

	    Common.waitForElement(3);

	    WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[contains(@class,'product_list_name') and contains(text(),'" + adminProductName + "')]")
	    ));

	  
	}
	public void verifyNewLabelInUserApp() throws InterruptedException {

	    // Scroll to load products
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,340);");
	    Common.waitForElement(2);

	    // FLUENT WAIT
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofMinutes(8))
	            .pollingEvery(Duration.ofSeconds(3))
	            .ignoring(NoSuchElementException.class)
	            .ignoring(StaleElementReferenceException.class);

	    WebElement matchedElement = wait.until(d -> {

	        d.navigate().refresh();

	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException ignored) {}

	        // ---------- CHECK NEW LABEL ----------
	        String newLabelXpath = "//span[contains(@class,'sale_tag') and normalize-space()='New']";

	        List<WebElement> newLabels = d.findElements(By.xpath(newLabelXpath));

	        if (!newLabels.isEmpty()) {

	            WebElement newLabel = newLabels.get(0);

	            System.out.println("✅ 'New' Label Found");

	            return newLabel;
	        }

	        System.out.println("⏳ 'New' Label not found. Refreshing again...");

	        return null; // keep waiting
	    });

	    if (matchedElement != null && matchedElement.isDisplayed()) {

	        System.out.println("🎉 FINAL RESULT: PASS");
	        System.out.println("✅ 'New' Label displayed successfully.");

	    } else {

	        throw new RuntimeException("❌ FAILED: 'New' Label not displayed.");
	    }
	}
	
	public void validateNewLabelFunctionality() throws InterruptedException {
		
		launchHomepage("ZLAATA INDIA");
		
		takeRandomProductFromAll();

		updateProductDate();
		
		launchHomepage("ZLAATA INDIA");
		
		verifyNewLabelListingPage();
		
		verifyNewLabelInUserApp();

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
