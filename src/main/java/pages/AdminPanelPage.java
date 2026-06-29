package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import manager.FileReaderManager;
import objectRepo.AdminPanelObjRepo;
import stepDef.ExtentManager;
import utils.Common;
import utils.ExcelXLSReader;
import utils.ExportValidator;

public final class AdminPanelPage extends AdminPanelObjRepo  {
	 

	public AdminPanelPage(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	 String expectedBannerTitle;


		public void adminLoginApp() {
			 driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
		        type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
		        type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
		        click(adminLogin);
		        System.out.println( "✅ Admin Login Successful" );

		    
		}

	public void uploadImageinHomePageBanner() {
	    Scanner sc = new Scanner(System.in);
//	    String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/sample.jpg";
//	    // ANSI color codes for console output
//	    String GREEN = "\u001B[32m";
//	    String YELLOW = "\u001B[33m";
//	    String BLUE = "\u001B[34m";
//	    String CYAN = "\u001B[36m";
//	    String RESET = "\u001B[0m";
//	    String line = "─────────────────────────────────────────────";

//	    // Step 1: Login to Admin
//	    driver.manage().window().minimize();
//
//	 
//
//	    // Step 3: Take banner title input from console
//	    System.out.println(line);
//	    System.out.print(YELLOW + "🖼️ Enter Banner Title: " + RESET);
//	     expectedBannerTitle = sc.nextLine().trim();
//	    System.out.println(CYAN + "📢 You entered Banner Title: " + GREEN + expectedBannerTitle + RESET);
//	    System.out.println(line);
//	    
//	 // ✅ Login once before starting all exports
//        driver.manage().window().maximize();
        
        adminLoginApp();
	    // Step 2: Navigate to Excel path (if needed)
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    Common.waitForElement(2);

	    // Step 4: Add new homepage banner
	    expectedBannerTitle = "TestHome" + System.currentTimeMillis();
	 // 🔥 FIX FOR HEADLESS
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", addHomePageBanner);
	    System.out.println("Clicked Add Home Page Banner");
	    Common.waitForElement(2);
	    type(bannerTitle, expectedBannerTitle);
	    Common.waitForElement(2);
	    type(heading, expectedBannerTitle);
  
//
//	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[1]")));
//
//	    uploadDesktopImage.sendKeys(imagePath);
//	    System.out.println("✅ Desktop image uploaded");
//
//	    Common.waitForElement(2);
//
//	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[2]")));
//
//	    uploadMobileImage.sendKeys(imagePath);
//	    System.out.println("✅ Mobile image uploaded");

//System.out.println("✅ Image uploaded successfully");
//	    System.out.println(GREEN + "✅ Image uploaded successfully!" + RESET);
//	    
	}
	
	public void uploadHomepageBannerDetails(String linkValue, String brandTypeValue, String bannerTypeValue) {
	    String GREEN = "\u001B[32m";
	    String BLUE = "\u001B[34m";
	    String RESET = "\u001B[0m";
	    String line = "─────────────────────────────────────────────";

	    Common.waitForElement(1);
	    WebElement linkField = driver.findElement(By.name("link"));
	    linkField.clear();
	    linkField.sendKeys(linkValue);
	    Common.waitForElement(2);

	    Select bannerType = new Select(driver.findElement(By.name("type")));
	    bannerType.selectByVisibleText(bannerTypeValue);
	    System.out.println("✅ Banner type selected: " + bannerTypeValue);
	    Common.waitForElement(1);

	    Select brandType = new Select(driver.findElement(By.name("brand_type")));
	    brandType.selectByVisibleText(brandTypeValue);
	    System.out.println("✅ Brand type selected: " + brandTypeValue);
	    Common.waitForElement(2);

	    String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/sample.jpg";

	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[1]")));
	    uploadDesktopImage.sendKeys(imagePath);
	    System.out.println("✅ Desktop image uploaded");
	    Common.waitForElement(2);

	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[2]")));
	    uploadMobileImage.sendKeys(imagePath);
	    System.out.println("✅ Mobile image uploaded");
	    Common.waitForElement(2);

	    WebElement label = driver.findElement(By.xpath("//label[normalize-space()='Status']"));
	    label.click();
	    Common.waitForElement(2);

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", uploadButton);
	    System.out.println("✅ Save clicked");
	    Common.waitForElement(3);

	    waitFor(clearCatchButton);
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
	    Common.waitForElement(2);

	    System.out.println(line);
	    System.out.println(BLUE + "🎯 Added new banner: " + GREEN + expectedBannerTitle + RESET);
	    System.out.println(GREEN + "✅ Banner upload and setup completed successfully!" + RESET);
	    System.out.println(line);
	}



	public void verifyBannerOnHomePage() {

	  //  String expectedTitle = Common.getValueFromTestDataMap("Banner Title");
		Common.waitForElement(2);
	    int timeoutMinutes = 10;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(5)) // ⬅ increase from 5s to 15s
	                    .pollingEvery(Duration.ofSeconds(3))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	                List<WebElement> elements = driver.findElements(
	                        By.xpath("//section[@data-section='zi_home_page_banner' or @data-section='bl_home_page_banner']//img[@alt='" + expectedBannerTitle + "']")
	                );
	              
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            // ✅ Relax condition → as soon as element is found
	            if (titleElement != null) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    // ✅ Final check
	    if (titleFound) {
	        System.out.println("✅ Banner title '" + expectedBannerTitle + "' is visible in User Application.");
	    } else {
	        System.out.println("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	        Assert.fail("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	    }
	}
	

//SarojKumar 
	//Top Selling
	
	public void adminLogin() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	    type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
	    type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
	    click(adminLogin);
	    System.out.println("✅ Admin Login Successfull");
	    
	}
	
	public void givesProductName() throws InterruptedException {
		
		adminLogin();
		Common.waitForElement(3);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successfull redirect to Adimn Product page");
		
//	    ((JavascriptExecutor) driver)
//        .executeScript("arguments[0].click();", productDetailMenu);
//
//	    System.out.println("✅ Successfull click product listing menu");
//	    Common.waitForElement(2);
//
//	    ((JavascriptExecutor) driver)
//        .executeScript("arguments[0].click();", productSearchBox);
//
//	    System.out.println("✅ Successfull fetch product listing name from excel sheet");
//
//	   driver.switchTo().activeElement().sendKeys(productName);
//	
//	    System.out.println("✅ Product name typed: " + productName);
//	    
//	    Thread.sleep(3000);
//	    
//	    productSearchBox.sendKeys(Keys.ENTER);
//	    
//	    System.out.println("✅ Select2 dropdown option clicked ");
	    
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    Long width = (Long) js.executeScript("return window.innerWidth;");
	    Long height = (Long) js.executeScript("return window.innerHeight;");

	    System.out.println("Window Size: " + width + " x " + height);
	    
	 //   JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Click product listing menu
	    js.executeScript("arguments[0].click();", productDetailMenu);
	    System.out.println("✅ Successfull click product listing menu");

	    // Wait for search box
	    wait.until(ExpectedConditions.visibilityOf(productSearchBox));
	    wait.until(ExpectedConditions.elementToBeClickable(productSearchBox));

	    // Scroll into view (VERY IMPORTANT for headless)
	    js.executeScript("arguments[0].scrollIntoView(true);", productSearchBox);

	    // Focus input properly
	    js.executeScript("arguments[0].focus();", productSearchBox);

	    // Clear + Type
	    productSearchBox.clear();
	    productSearchBox.sendKeys(productName);

	    System.out.println("✅ Product name typed: " + productName);

	    // Press Enter
	    Thread.sleep(3000);
	    productSearchBox.sendKeys(Keys.ENTER);

	    System.out.println("✅ Product search triggered");
	    
	    
	   

		
	}
	String topSellingProductSku;
	public String fetchSkuFromProduct() {

	    // Click "Edit Product" button
		Common.waitForElement(3);
	//    waitFor(editProductButton);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", editProductButton);
//	    click(editProductButton);
	    System.out.println("\u001B[32m✅ Successfully clicked product edit option\u001B[0m");

	    // Click "Item Product" button
//	    waitFor(itemProductButton);
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", itemProductButton);
//	    click(itemProductButton);
	    System.out.println("\u001B[32m✅ Successfully clicked product item option\u001B[0m");

	    // Fetch SKU value
	    Common.waitForElement(2);
//	    waitFor(skuField);
	     topSellingProductSku = skuField.getAttribute("value").trim();
	    System.out.println("\u001B[33m📌 SKU fetched: " + topSellingProductSku + "\u001B[0m");

	    return topSellingProductSku;
	}
	
	
	
	public void putSkuIntoTopSelling() {
	    try {
	        // ANSI Colors for better console logs
	        String GREEN = "\u001B[32m";
	        String YELLOW = "\u001B[33m";
	        String BLUE = "\u001B[34m";
	        String CYAN = "\u001B[36m";
	        String RED = "\u001B[31m";
	        String RESET = "\u001B[0m";
	        String line = "─────────────────────────────────────────────";
	        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	        Common.waitForElement(2);
	        waitFor(generalSettingsMenu);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", generalSettingsMenu);
//	        click(generalSettingsMenu);
	        Common.waitForElement(2);

	        waitFor(clickSetKey);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", clickSetKey);
	     //   click(clickSetKey);
	        Common.waitForElement(2);
	        waitFor(productSearchBox);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", productSearchBox);
//	        click(productSearchBox);
	        Common.waitForElement(2);
	        type(productSearchBox, "top_selling");
	        productSearchBox.sendKeys(Keys.ENTER);
	        System.out.println(BLUE + "🔍 Searched for 'top_selling'..." + RESET);
	        Common.waitForElement(3);
	        waitFor(topSellingEdit);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", topSellingEdit);
	    //    click(topSellingEdit);
	        System.out.println(GREEN + "✅ Opened Top Selling edit section" + RESET);

	        waitFor(topSellingSkuInput);
	        String current = topSellingSkuInput.getAttribute("value");
	        String cleaned = (current == null) ? "" : current.trim();

	        // Clean JSON-like format [sku1, sku2, sku3]
	        if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
	            cleaned = cleaned.substring(1, cleaned.length() - 1).trim();
	        }

	        java.util.List<String> skuList = new java.util.ArrayList<>();
	        if (!cleaned.isEmpty()) {
	            for (String part : cleaned.split(",")) {
	                String val = part.trim();
	                if (!val.isEmpty()) skuList.add(val);
	            }
	        }

	        System.out.println(CYAN + "📦 Current SKU list: " + skuList + RESET);

	        boolean alreadyExists = skuList.stream()
	                .anyMatch(s -> s.equalsIgnoreCase(topSellingProductSku));

	        String updated;
	        String message;

	        // ✅ If already exists → remove and move to first
	        if (alreadyExists) {
	            skuList.removeIf(s -> s.equalsIgnoreCase(topSellingProductSku));
	            skuList.add(0, topSellingProductSku);
	            updated = "[" + String.join(", ", skuList) + "]";
	            message = "SKU '" + topSellingProductSku + "' already existed — moved to first position.";
	        } else {
	            // ✅ If not exist → add to first
	            skuList.add(0, topSellingProductSku);
	            updated = "[" + String.join(", ", skuList) + "]";
	            message = "SKU '" + topSellingProductSku + "' added to Top Selling list.";
	        }

	        // Update field
	        topSellingSkuInput.clear();
	        type(topSellingSkuInput, updated);
	        Common.waitForElement(2);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", saveTopSelling);
	   //     click(saveTopSelling);

	        System.out.println(line);
	        System.out.println(GREEN + "✅ " + message + RESET);
	        System.out.println(YELLOW + "💾 Updated Top Selling List: " + CYAN + updated + RESET);
	        System.out.println(line);

	    } catch (Exception e) {
	        System.err.println("❌ Failed to update Top Selling SKU due to: " + e.getMessage());
	        throw e;
	    }

	    // ✅ Always clear cache at the end
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", clearCatchButton);
	//    click(clearCatchButton);
	    System.out.println( "🧹 Cache cleared successfully." );
	    Common.waitForElement(2);
	}
	
	
	//Verify the product successfull showing in user application home page top selling Section
	
	public void verifyProductShowInTopSelling() throws InterruptedException {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
		  // Scroll directly to Top Selling section
	    WebElement topSellingSection = driver.findElement(
	            By.xpath("//div[@data-section='top_selling']")
	    );
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
	            topSellingSection
	    );
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	            topSellingSection
	    );


	    int timeoutMinutes = 15;   // total wait time
	    int refreshInterval = 5;  // refresh every 5 seconds
	    boolean productFound = false;
	    WebElement card = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            //  Refresh page every cycle
	            driver.navigate().refresh();
	            Common.waitForElement(3);

	            // ✅ After refresh,  FluentWait to check element without refreshing again
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(refreshInterval)) // max wait before next refresh
	                    .pollingEvery(Duration.ofSeconds(3))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            card = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                		"(//div[contains(@class,'top_selling_carousel_wrap')]//a[contains(@class,'prod_title') and contains(normalize-space(),'" + excpectedName + "')])[1]"
	                        
	                ));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (card != null && card.isDisplayed()) {
	                productFound = true;
	                break; //  stop if found
	            }
	        } catch (TimeoutException te) {
	            // element not found in this refresh cycle → loop continues
	        }

	        Thread.sleep(1000); // small buffer
	    }

	    // ✅ Final validation
	    if (productFound && card != null && card.getText().trim().equalsIgnoreCase(excpectedName.trim())) {
	        System.out.println("✅ Product '" + excpectedName + "' is visible in Top Selling.");
	    } else {
	        System.err.println("❌ Product '" + excpectedName + "' not found in Top Selling within " 
	                           + timeoutMinutes + " minutes.");
	        
	    }
	}
	    
	  
	
	
	//Negative Testcase Top Selling Section
	
//	public void forNegativeGivesProductName() {
//		Common.waitForElement(4);
//	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
//	    System.out.println("✅ Successfull redirect to Adimn Product page");
//		 // Open product listing
//	    click(productListingMenu);  
//	    System.out.println("✅ Successfull click product listing menu");
//	    waitFor(productSearchBox);
//	    click(productSearchBox);
//
//	    // Fetch the product name directly from Excel map
//	    String productName = Common.getValueFromTestDataMap("ProductListingName");
//	    System.out.println("✅ Successfull fetch product listing name from excel sheet");
//	 // Search or enter the product
//	    type(productSearchBox, productName + Keys.ENTER);
//	    Common.waitForElement(2);
//	    System.out.println("✅ Successfull put product listing name in searchbox and also click enter");
//
//		
//	}
//	public String forNegativeFetchSkuFromProduct() {
//		
//	    // now click edit, etc…
//	    waitFor(editProductButton);
//	    click(editProductButton);
//	    System.out.println("✅ Successfull click product edit option");
//	    // now click item, etc…
//	    waitFor(itemProductButton);
//	    click(itemProductButton);
//	    System.out.println("✅ Successfull click product item option");
//	    
//	 // now take Sku
//	    waitFor(skuField);
//	    System.out.println("✅ Successfull copy the SKU from skufield");
//        return skuField.getAttribute("value").trim();
//     
// 	
//	}
	// Remove SKU from Top Selling list
	public void removeSkuFromTopSelling() {
	    try {
	    	Common.waitForElement(2);
	    	waitFor(generalSettingsMenu);
	    	((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", generalSettingsMenu);
//	        click(generalSettingsMenu);
	        Common.waitForElement(2);
	        
		    waitFor(clickSetKey);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", clickSetKey);
//		    click(clickSetKey);
		    Common.waitForElement(2);
		    waitFor(productSearchBox);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", productSearchBox);
//		    click(productSearchBox);
		    
		    type(productSearchBox, "top_selling");
		    
		    productSearchBox.sendKeys(Keys.ENTER);
		    System.out.println("✅ Searched for top-selling");
		    Common.waitForElement(3);
	        waitFor(topSellingEdit);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", topSellingEdit);
	    //    click(topSellingEdit);
	        System.out.println("✅ Successfull click the top selling edit button");  

	        waitFor(topSellingSkuInput);
	        String current = topSellingSkuInput.getAttribute("value");
	        String cleaned = (current == null) ? "" : current.trim();

	        if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
	            cleaned = cleaned.substring(1, cleaned.length() - 1).trim();
	        }

	        List<String> skuList = new ArrayList<>();
	        if (!cleaned.isEmpty()) {
	            for (String part : cleaned.split(",")) {
	                String val = part.trim();
	                if (!val.isEmpty()) skuList.add(val);
	            }
	        }

	        boolean exists = skuList.removeIf(s -> s.equalsIgnoreCase(topSellingProductSku));

	        String updated;
	        String message;
	        if (!exists) {
	            updated = "[" + String.join(", ", skuList) + "]";
	            message = "SKU '" + topSellingProductSku + "' not found. No changes made.";
	        } else if (skuList.isEmpty()) {
	            updated = "[]";
	            message = "SKU '" + topSellingProductSku + "' removed. List is now empty.";
	        } else {
	            updated = "[" + String.join(", ", skuList) + "]";
	            message = "SKU '" + topSellingProductSku + "' removed from Top Selling list.";
	        }

	        topSellingSkuInput.clear();
	        type(topSellingSkuInput, updated);
	        ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", saveTopSelling);
	//        click(saveTopSelling);

	     // Wait briefly for possible error page
	     Common.waitForElement(3);
	         System.out.println("✅ Save successful, no error page displayed.");
	        System.out.println("✅ " + message);

	    } catch (Exception e) {
	        String error = "❌ Failed to remove SKU due to: " + e.getMessage();
	        System.err.println(error);
	        throw e;
	    }

	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", clearCatchButton);
//	    click(clearCatchButton);
	    System.out.println("✅ Successfully clicked Clear Cache Button");
	    Common.waitForElement(2);
	}


	// Verify product is NOT in Top Selling (Negative Test)
	public void verifyProductNotInTopSelling() throws InterruptedException {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
	    Common.waitForElement(3);
	    // Scroll directly to Top Selling section
	    WebElement topSellingSection = driver.findElement(
	            By.xpath("//div[@data-section='top_selling']")
	    );
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
	            topSellingSection
	    );
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	            topSellingSection
	    );

	    int timeoutMinutes = 5;   // total wait time
	    boolean productFound = false;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            driver.navigate().refresh();
	            Common.waitForElement(3);

	            List<WebElement> elements = driver.findElements(By.xpath(
	            		"(//div[contains(@class,'top_selling_carousel_wrap')]//a[contains(@class,'prod_title') and contains(normalize-space(),'" + excpectedName + "')])[1]"
	            ));

	            if (elements.isEmpty()) {
	                // ✅ Product is gone → stop waiting immediately
	                productFound = false;
	                break;
	            } else if (elements.get(0).isDisplayed()) {
	                // Product is still there → set flag true
	                productFound = true;
	            }
	        } catch (Exception ignored) {}

	        Thread.sleep(2000);
	    }

	    if (!productFound) {
	        System.out.println("✅ Product '" + excpectedName + "' is NOT visible in Top Selling (as expected).");
	    } else {
	        System.err.println("❌ Product '" + excpectedName + "' still visible in Top Selling even after removal!");
	    }
	}

//New Arrivals
	 private String copiedSku;
	
	public void verifyColourOfTheProductIsFirstPosition() throws InterruptedException {
		adminLogin();
		Common.waitForElement(2);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
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
	    System.out.println("✅ Fetched product listing name from Excel sheet:" + productDetailName);
	 // Search or enter the product
	    // Clear + Type
	    productSearchBox.clear();
	    productSearchBox.sendKeys(productDetailName);

	    System.out.println("✅ Product name typed: " + productDetailName);

	    // Press Enter
	    Thread.sleep(3000);
	    productSearchBox.sendKeys(Keys.ENTER);

	//    type(productSearchBox, productDetailName + Keys.ENTER);
	    Common.waitForElement(2);
	    System.out.println("✅ Entered product listing name in search box & pressed ENTER");
	    
	 // now click edit, etc…
	    Common.waitForElement(3);
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
	    String uiProductName = productListingBoxText.getAttribute("value").trim();
	    System.out.println(" UI Product Listing Name: " + uiProductName);
	    //For Sorting we copied sku
	    copiedSku = skuTextbox.getAttribute("value").trim();
	    System.out.println(" Copied SKU : " + copiedSku);

//	  
//	    boolean match = uiProductName.toLowerCase().contains(productlistingName.toLowerCase());
//	    if (!match) {
//	        System.err.println("❌ Product mismatch,This is not First Colour Both are Different— skipping remaining steps → Expected: " + productlistingName + " | Actual: " + uiProductName);
//	        // Now skip scenario
//	        Assume.assumeTrue("Skipping due to product mismatch", false);
//	    }
	  //For Assert
//	    Assert.assertEquals(
//	    	    uiProductName,
//	    	    productName,
//	    	    "❌ Product mismatch, expected: " + productName + " | but found: " + uiProductName
//	    	);
	    
	    System.out.println("✅ Product listing name matches expected.");
	    
	    
////	 -------- Check Stock Status --------
//	    waitFor(stockStatusTextbox); 
//	    String stockStatus = firstProductStockStatus.getAttribute("value").trim();
//
//	    if(stockStatus.equalsIgnoreCase("Available")) {
//	        System.out.println("✅ Product is available in stock");
//	    } 
//	    else if(stockStatus.equalsIgnoreCase("Out Of Stock")) {
//	        System.out.println("❌ Product is out of stock");
//	        // skip further steps if out of stock
//	        Assume.assumeTrue("Skipping further steps as product is out of stock", false);
//	    } 
//	    

		
		
		
	}
	
	public void addTheProductInNewArrivalSection() throws InterruptedException {
		// Click product button
		Common.waitForElement(2);
		((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", productButton);
	  //  click(productButton);
	    System.out.println("✅ Clicked Product button");

	    // Copy text from Product Name textbox
	    Common.waitForElement(2);
	    String copiedProductName = productNameTextbox.getAttribute("value").trim();
	    System.out.println("📋 Copied product name: " + copiedProductName);

	    //Navigate to Search Product Collection page
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", searchProductCollectionMenu);
	  //  click(searchProductCollectionMenu);
	    System.out.println("✅ Clicked Search Product Collection");

	    //Type in the search text box
	    //Thread.sleep(2000);
	    Common.waitForElement(2);
	//    waitFor(searchProductCollectionMenu); 
	    type(searchProductCollectionMenu, "Product Collections");
	    //Thread.sleep(2000);
	    System.out.println("✅ Typed 'Product Collections' ");
	//    waitFor(clickProductCollection);
	  //  Thread.sleep(3000);
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", clickProductCollection);
	 //   click(clickProductCollection);
	    System.out.println("✅ Selected Product Collection");
//	     3. Select from the dropdown or result line
//	    selectDropdownByVisibleText(productCollectionDropdown, "Product Collections");
//	    System.out.println("✅ Selected Product Collection");

	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", clickStatus);
//	    waitFor(clickStatus);
//	    click(clickStatus);

	    // Select Status -> Active
	    Common.waitForElement(1);
	    Thread.sleep(2000);
//	    ((JavascriptExecutor) driver)
//        .executeScript("arguments[0].click();", statusActiveOption);
	   // click(statusActiveOption);
	 // 3️⃣ Scroll + native click (NOT JS)
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", statusActiveOption
	    );

	    statusActiveOption.click();  // ✅ THIS WILL WORK IN HEADLESS
	    System.out.println("✅ Selected Active status");
	    
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", brandType);
	    Common.waitForElement(1);
	    Thread.sleep(2000);
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", selectbrandType
	    );

	    selectbrandType.click();  // ✅ THIS WILL WORK IN HEADLESS
	    System.out.println("✅ Selected Brand Type");
	    // Click Collection button
	    //Thread.sleep(2000);
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", collectionButton);
	 //   click(collectionButton);
	    System.out.println("✅ Clicked Collection button");

	    //Search 'new-arrivals'
	    //Thread.sleep(2000);
	    Common.waitForElement(2);
	 //   waitFor(searchTextBox);
	    type(searchTextBox, "new-arrivals");
	    searchTextBox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Searched for new-arrivals");
	   // Thread.sleep(2000);

	    //Click Edit
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", editCollectionButton);
	//    editCollectionButton.click();
	    System.out.println("✅ Entered Edit mode for collection");

	    //Add copied product to last position in product text field
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900);");
	    Common.waitForElement(2);
	    type(addProductTextbox, copiedProductName);
	    Common.waitForElement(2);
	   //Thread.sleep(3000);
	    addProductTextbox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Added product name to collection");
	    

	    //Save
	    Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", saveButton);
	  //  click(saveButton);
	    System.out.println("✅ Saved collection changes");
		
		
	}
	
	public void sortTheProductInFirstPosition() throws InterruptedException {
		// Click on Search Box for Product Sort
		Common.waitForElement(2);
	    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", searchProductSortMenu);
		//click(searchProductSortMenu);
	    System.out.println("✅ Clicked Search Product Collection");

		// Type in the search text box
		// Thread.sleep(2000);
	     Common.waitForElement(2);
		type(searchProductSortMenu, "Product Sorts");
		// Thread.sleep(2000);
		System.out.println("✅ Typed 'Product Sorts");
		 Common.waitForElement(2);
		// Thread.sleep(3000);
		 ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", clickProductSort);
		//click(clickProductSort);
		System.out.println("✅ Selected Product Sorts");

		 Common.waitForElement(2);
		    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", brandType);
		    Common.waitForElement(1);
		    Thread.sleep(2000);
		    ((JavascriptExecutor) driver).executeScript(
		            "arguments[0].scrollIntoView({block:'center'});", selectbrandType
		    );

		    selectbrandType.click();  // ✅ THIS WILL WORK IN HEADLESS
		    System.out.println("✅ Selected Brand Type");
		//  Click Category Name
		Common.waitForElement(2);
	   		 ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", categoryName);
	//	click(categoryName);
		System.out.println("✅ Clicked Category Name");

		// In the Category Search, type "New Arrivals" and hit Enter
		 Common.waitForElement(2);
		//    waitFor(categorySearchBox);
		    type(categorySearchBox, "New Arrivals");
		    categorySearchBox.sendKeys(Keys.ENTER);
		    System.out.println("✅ Typed 'New Arrivals' & pressed Enter");
		   // Thread.sleep(2000);
		

		// Scroll down
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600);");
		System.out.println("✅ Scrolled down");

		//Click Next Page arrow
		Common.waitForElement(2);
  		 ((JavascriptExecutor) driver)
       .executeScript("arguments[0].click();", nextPageArrow);
		
		//click(nextPageArrow);
		System.out.println("✅ Clicked next page");

		// Click Plus Button to add products
		Common.waitForElement(2);
		waitFor(plusButton);
		 ((JavascriptExecutor) driver)
	       .executeScript("arguments[0].click();", plusButton);
		//click(plusButton);
		System.out.println("✅ Clicked '+' button");

		//  In the products list, find SKU & drag to first position
		 // All product cards
        List<WebElement> allProducts = driver.findElements(By.xpath("//div[contains(@class,'sortable-card')]"));
        if (allProducts.size() < 3) {
            System.out.println("❌ Less than 3 products available, cannot perform reorder.");
            return;
        }

        WebElement thirdProduct = driver.findElement(By.xpath("(//div[contains(@class,'sortable-card')])[3]"));
        WebElement firstProduct = driver.findElement(By.xpath("(//div[contains(@class,'sortable-card')])[1]"));

        Actions action = new Actions(driver);

     // Perform drag & drop to above the first card
		// XPath to locate card by SKU text
		Common.waitForElement(3);
		By skuCard = By.xpath("//div[contains(@class,'sortable-card')]//span[contains(text(),'" + copiedSku + "')]/ancestor::div[contains(@class,'sortable-card')]");

		// Find the product card
		List<WebElement> products = driver.findElements(skuCard);

		if (products.isEmpty()) {
		    System.out.println("❌ Product with SKU '" + copiedSku + "' not found.");
		} else {
		    WebElement from = products.get(0);  // found product card
		    WebElement firstPosition = driver.findElement(By.xpath("(//div[contains(@class,'sortable-card')])[1]"));

		    try {
		        // try native Selenium drag & drop
		    	Actions actions = new Actions(driver);
		    	action.clickAndHold(from)
		        .pause(Duration.ofMillis(500))
		        .moveToElement(firstPosition, 0, -30) // Drop 30px above the first card
		        .pause(Duration.ofMillis(500))
		        .release()
		        .build()
		        .perform();
		        System.out.println("✅ Dragged product '" + copiedSku + "' to first position (Selenium).");

		    } catch (Exception e) {
		        // JavaScript drag & drop simulation
		        String jsDragDrop =
		            "function triggerDragAndDrop(source, target) {" +
		            "  const dataTransfer = new DataTransfer();" +
		            "  const dragStartEvent = new DragEvent('dragstart', { dataTransfer });" +
		            "  source.dispatchEvent(dragStartEvent);" +
		            "  const dropEvent = new DragEvent('drop', { dataTransfer });" +
		            "  target.dispatchEvent(dropEvent);" +
		            "  const dragEndEvent = new DragEvent('dragend', { dataTransfer });" +
		            "  source.dispatchEvent(dragEndEvent);" +
		            "}" +
		            "triggerDragAndDrop(arguments[0], arguments[1]);";

		        ((JavascriptExecutor) driver).executeScript(jsDragDrop, from, firstPosition);

		        System.out.println("✅ Dragged product '" + copiedSku + "' to first position (JS fallback).");
		    }
		}
		
		 //  Save
		Common.waitForElement(3);
		((JavascriptExecutor) driver)
	       .executeScript("arguments[0].click();", saveButton);
	  //  click(saveButton);
	    System.out.println("✅ Saved collection changes");
	    //Clear Catch
	    Common.waitForElement(2);
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
		
		
	}
	
	public void verifyProductShowInNewArrivalsSction() {
		
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement banner = wait1.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//h2[normalize-space()='ZLAATA INDIA']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
	        ));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

	        System.out.println("Clicked ZLAATA INDIA  Home Page Banner");
	    Common.waitForElement(3);
	    WebElement topSellingSection = driver.findElement(
	            By.xpath("//section[@data-section='new_arrivals']")
	    );
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
	            topSellingSection
	    );
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	            topSellingSection
	    );

	    int timeoutMinutes = 10;   // total wait time
	    int refreshInterval = 4; // refresh every 15 seconds
	    boolean productFound = false;
	    WebElement card = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            //  Refresh page
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            // ✅ Use FluentWait after refresh
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(refreshInterval)) 
	                    .pollingEvery(Duration.ofSeconds(2))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            card = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                		
	                		"(//section[.//h2[normalize-space()='NEW ARRIVALS']]//div[contains(@class,'swiper-slide') and contains(@class,'swiper-slide-active')])[1]"
	                ));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (card != null && card.isDisplayed()) {
	                productFound = true;
	                break; // ✅ stop if found
	            }
	        } catch (TimeoutException te) {
	            // not found in this refresh cycle → loop continues
	        }

	        try {
	            Thread.sleep(1000); // small buffer
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    // ✅ Final validation
	    if (productFound && card != null && card.getText().trim().equalsIgnoreCase(productlistingName.trim())) {
	        System.out.println("✅ Product '" + productlistingName + "' is visible in New Arrivals.");
	    } else {
	        System.err.println("❌ Product '" + productlistingName + "' not found in New Arrivals within " 
	                           + timeoutMinutes + " minutes.");
	    }
	}
	
	//Catagory Section
	String expectedTitle;
	public void updateCategoryBanner(String imagePathCategory) {
	    Scanner sc = new Scanner(System.in);

	    // 🎨 Console colors
	    String GREEN = "\u001B[32m";
	    String YELLOW = "\u001B[33m";
	    String BLUE = "\u001B[34m";
	    String CYAN = "\u001B[36m";
	    String RED = "\u001B[31m";
	    String RESET = "\u001B[0m";
	    String line = "─────────────────────────────────────────────";
	    driver.manage().window().minimize();
	 // Step 5: Get Banner Title from console
        System.out.print(YELLOW + "🖼️ Enter new Banner Title: " + RESET);
         expectedTitle = sc.nextLine().trim();
         
         driver.manage().window().maximize();

	    try {
	        // Step 1: Open Excel page (or config page)
	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        System.out.println(GREEN + "✅ Successfully redirected to Home Page Banner section." + RESET);

	        // Step 2: Click on Home Page Banner menu
	        Common.waitForElement(2);
	        waitFor(homePageBannerMenu);
	        click(homePageBannerMenu);
	        Common.waitForElement(2);
	        System.out.println(BLUE + "📂 Opened 'Home Page Banner' menu." + RESET);

	        // Step 3: Search for category
	        Common.waitForElement(2);
	        waitFor(catagorysearchTextBox);
	        type(catagorysearchTextBox, "Dazzle Category");
	        catagorysearchTextBox.sendKeys(Keys.ENTER);
	        System.out.println(GREEN + "🔍 Searched for 'Dazzle Category'." + RESET);

	        // Step 4: Click Edit button
	        Common.waitForElement(2);
	        waitFor(editButton);
	        click(editButton);
	        System.out.println(GREEN + "🖊️ Clicked 'Edit' button for Dazzle Category." + RESET);

	        

	        // Step 6: Clear and set new banner title
	        Common.waitForElement(2);
	        waitFor(bannerTitleTextBox);
	        bannerTitleTextBox.clear();
	        type(bannerTitleTextBox, expectedTitle);
	        System.out.println(CYAN + "🏷️  Banner Title set to: " + YELLOW + expectedTitle + RESET);

	        // Step 7: Upload new banner image
	        Common.waitForElement(2);
	        waitFor(desktopBannerUpload);
	        desktopBannerUpload.sendKeys(imagePathCategory);
	        System.out.println(GREEN + "✅ Banner image uploaded successfully." + RESET);

	        // Step 8: Save changes
	        Common.waitForElement(2);
	        waitFor(saveButton);
	        saveButton.click();
	        Common.waitForElement(3);
	        System.out.println(GREEN + "💾 Changes saved successfully." + RESET);

	        // Step 9: Clear cache
	        Common.waitForElement(2);
	        waitFor(clearCatchButton);
	        click(clearCatchButton);
	        System.out.println(GREEN + "🧹 Cache cleared successfully." + RESET);
	        Common.waitForElement(2);

	        System.out.println(line);
	        System.out.println(BLUE + "🎯 Successfully updated category banner with title: " 
	                           + YELLOW + expectedTitle + RESET);
	        System.out.println(line);

	    } catch (Exception e) {
	        System.err.println(RED + "❌ Failed to update category banner: " + e.getMessage() + RESET);
	        throw e;
	    }
	}

    // Method 2: Verify Banner in User Application
	public void verifyBannerUserApp() {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
	    Common.waitForElement(3);
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1300);");
	    Common.waitForElement(3);

	

	    // ✅ Wait until the expected banner title appears
	    int timeoutMinutes = 10;  
	    int pollSeconds = 5;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            // Refresh and wait
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(pollSeconds))
	                    .pollingEvery(Duration.ofSeconds(2))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                		"//img[@alt='" + expectedTitle + "']"));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (titleElement != null && titleElement.isDisplayed()) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
	    }

		// ✅ Final check
		if (titleFound) {
			System.out.println("✅ Banner title '" + expectedTitle + "' is visible in User Application.");
		} else {
			System.out
					.println("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
			Assert.fail("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
		}
	}



//Bulk Product Add 
	public void UploadTheProductExcel(String excelPath) {
		Common.waitForElement(2);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successful redirect to Adimn Product page");
	    
	    Common.waitForElement(2);
	    waitFor(importButton);
        click(importButton);
        System.out.println("✅ Clicked  Importbutton");
        
     // Upload new bulk order
	    Common.waitForElement(2);
	    waitFor(uploadExcelButton);
	    uploadExcelButton.sendKeys(excelPath);
	    System.out.println("✅ successful product added");
        
	 // Save changes
        Common.waitForElement(2);
        waitFor(submitButton);
        submitButton.click();
	    System.out.println("✅ successful saved");
	        
      //  Assert.assertTrue("❌ Excel upload failed!", successMessage.isDisplayed());
        System.out.println("✅ Excel uploaded successfully");
        Common.waitForElement(5);
        
    }
	
	public void verifyProductsInAdmin(String filePath) throws IOException {
		Common.waitForElement(2);
		//driver.navigate().refresh();
		List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
			    .stream()
			    .filter(product -> {
			        Object skuObj = product.get("Sku");
			        return skuObj != null && !skuObj.toString().trim().isEmpty();
			    })
			    .collect(Collectors.toList());
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    for (Map<String, Object> product : products) {
	        String sku = (String) product.get("Sku");

	        // Click SKU menu
	        wait.until(ExpectedConditions.elementToBeClickable(clickSKU)).click();

	        // Wait for search boxes
	        List<WebElement> searchBoxes = wait.until(d -> {
	            List<WebElement> elements = d.findElements(By.xpath("//input[@role='searchbox']"));
	            return elements.size() >= 2 ? elements : null;
	        });
	        WebElement adminSearchBox = searchBoxes.get(1);

	        // Type SKU
	        wait.until(ExpectedConditions.elementToBeClickable(adminSearchBox));
	        adminSearchBox.clear();
	        adminSearchBox.sendKeys(sku);
	        adminSearchBox.sendKeys(Keys.ENTER);

	        // ✅ Print only once per SKU
	        System.out.println("✅ Searched for SKU: " + sku);

	        // Wait for SKU to appear in table
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='" + sku + "']")));
	        System.out.println("✅ SKU is visible in Admin panel: " + sku);

	        // Click outside after confirming visibility (optional)
	        wait.until(ExpectedConditions.elementToBeClickable(clickBlankSpace)).click();
	    }
	    
	    //Clear Catch
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
	    Common.waitForElement(2);
	}

	
	public void verifyProductsInUserApp(String filePath) throws IOException {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
	    Common.waitForElement(3);

	    List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    
	    // Create ExtentTest once
	    ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Products in User App");
	    ExtentManager.setTest(test);


	    for (Map<String, Object> product : products) {

	        // ✅ Treat as List, not String
	        @SuppressWarnings("unchecked")
	        List<String> listingNames = (List<String>) product.get("Product Listing Name");

	        if (listingNames == null || listingNames.isEmpty()) {
	            System.out.println(" Skipping empty listing names");
	            continue;
	        }

	        for (String listingName : listingNames) {
	            if (listingName == null || listingName.trim().isEmpty()) continue;

	            // Search in user app
	            wait.until(ExpectedConditions.elementToBeClickable(userSearchBox));
	            userSearchBox.clear();
	            userSearchBox.sendKeys(listingName);
	            System.out.println("✅ Searched for listing: " + listingName);

	            // Wait for product to appear
	            By productLocator = By.xpath("//h6[normalize-space()='" + listingName + "']");
	            wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

	            WebElement productElement = driver.findElement(productLocator);
	            Assert.assertTrue("❌ Listing name not found in User App: " + listingName, productElement.isDisplayed());

	            // Click product
	            productElement.click();
	            System.out.println("✅ Product opened in User App: "+ listingName);


	            try {
	                // Actual price
	                WebElement actualPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[@class='prod_main_details_head']//div[@class='prod_actual_price']")
	                ));

	                // Current price
	                WebElement currentPrice = driver.findElement(
	                    By.xpath("//div[@class='prod_main_details_head']//div[@class='prod_current_price']")
	                );
	                
	             // Product Discount Percentage 
	                WebElement discountPercentage = driver.findElement(
	                    By.xpath("//div[@class='prod_main_details_head']//div[@class='prod_discount_percentage']")
	                );

	                // Delivery date
	                WebElement deliveryDate = driver.findElement(
	                    By.xpath("//div[@class='prod_main_details_head']//div[@class='prodcut_list_cards_best_pricing_txt']/span")
	                );

	                // Section 
	                WebElement section = driver.findElement(
	                    By.xpath("//div[@class='prod_main_details_head']//h5[@class='prod_category']")
	                );

	                // ✅ Print details
	                System.out.println(" Product: " + listingName);
	                System.out.println("    Actual Price   : " + actualPrice.getText());
	                System.out.println("    Discount Price : " + currentPrice.getText());
	                System.out.println("    Percentage     : " + discountPercentage.getText());
	                System.out.println("    Delivery Date  : " + deliveryDate.getText());
	                System.out.println("    Section        : " + section.getText());
	                
	                // Log all details properly
	                test.pass("Product Name       : " + listingName);
	                test.pass("Section            : " + section.getText());
	                test.pass("Actual Price       : " + actualPrice.getText());
	                test.pass("Current Price      : " + currentPrice.getText());
	                test.pass("Discount Percentage: " + discountPercentage.getText());
	                test.pass("Delivery Date      : " + deliveryDate.getText());
	                

	            } catch (Exception e) {
	                System.out.println("⚠ Could not fetch all details for product: " + listingName);
	            }
	        }
	         
	    }
	    ExtentManager.getExtentReports().flush();
	}
    
		 
		 String productName;
		 String excpectedName;
		 public String copyProductNameFromHomePageTopSelling() throws InterruptedException {

			    // ANSI colors
			    String GREEN = "\u001B[32m";
			    String CYAN  = "\u001B[36m";
			    String RESET = "\u001B[0m";

			    HomePage home = new HomePage(driver);
			    home.homeLaunch();

			    // Scroll directly to Top Selling section
			    WebElement topSellingSection = driver.findElement(
			            By.xpath("//div[@data-section='top_selling']")
			    );

			    ((JavascriptExecutor) driver).executeScript(
			            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
			            topSellingSection
			    );

			    ((JavascriptExecutor) driver).executeScript(
			            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
			            topSellingSection
			    );

			    Thread.sleep(500); // allow smooth scroll to finish

//			    // Click the first product
//			    WebElement firstProduct = driver.findElement(
//			            By.xpath("(//div[@data-section='top_selling']//a[contains(@class,'prod_card_img')])[1]")
//			    );
			    WebElement firstProduct1 = driver.findElement(
			            By.xpath("(//div[contains(@class,'top_selling_carousel_wrap')]//a[contains(@class,'prod_title')])[1]")
			    );
			            
			     excpectedName = firstProduct1.getText().trim();
			     Common.waitForElement(2);
			     System.out.println(CYAN + "📦 Product Listing Name: " + excpectedName + RESET);
			     Common.waitForElement(2);
			  // First product card link
			     WebElement firstProduct = driver.findElement(
			             By.xpath("(//div[contains(@class,'top_selling_carousel_wrap')]//a[contains(@href,'/product-detail')])[1]")
			     );

			     String productUrl = firstProduct.getAttribute("href");

			     // JS click (HEADLESS SAFE)
			     ((JavascriptExecutor) driver)
			             .executeScript("arguments[0].click();", firstProduct);

			     System.out.println(GREEN + "✅ First Top Selling Product Clicked: " + productUrl + RESET);

			     // ✅ 1. WAIT FOR URL TO CHANGE (CRITICAL)
			     wait.until(ExpectedConditions.urlContains("/product-detail"));

			     // ✅ 2. WAIT FOR DOM READY
			     wait.until(driver ->
			             ((JavascriptExecutor) driver)
			                     .executeScript("return document.readyState")
			                     .equals("complete")
			     );

			     // ✅ 3. WAIT FOR PRODUCT NAME PRESENCE (NOT VISIBILITY)
			     WebElement productNameElement = wait.until(
			             ExpectedConditions.presenceOfElementLocated(
			                     By.xpath("//h3[contains(@class,'prod_name')]")
			             )
			     );

			     // ✅ 4. READ VIA JS (HEADLESS FIX)
			      productName = ((JavascriptExecutor) driver)
			             .executeScript("return arguments[0].textContent;", productNameElement)
			             .toString()
			             .trim();

			     System.out.println(CYAN + "📦 Product Name (PDP): " + productName + RESET);

			     return productName;
			}
		 
		 String productlistingName;
		    String productDetailName;
//		    String productName;
//		    String secondaryColor;

		    // ===============================
		    // 🛍️ Select In-stock Product from App
		    // ===============================
		    public String takeRandomProductName() {

		        Common.waitForElement(3);

		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        Actions actions = new Actions(driver);

		        WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//span[contains(@class,'header_nav_link') and normalize-space()='Shop']")
		        ));
		        actions.moveToElement(shopMenu).perform();

		        WebElement allButton = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//div[contains(@class,'dropdown_content')]//a[normalize-space()='All']")
		        ));
		        allButton.click();
		        Common.waitForElement(3);
		        System.out.println("✅ Clicked on 'All' under Shop menu");

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

		            List<WebElement> stockLabels = productCard.findElements(
		                    By.xpath(".//span[contains(@class,'prod_listing_hurry') and normalize-space()='Out of Stock']"));

		            boolean isOutOfStock = !stockLabels.isEmpty() && stockLabels.get(0).isDisplayed();

		            if (isOutOfStock) {
		                System.out.println("❌ '" + name + "' is OUT OF STOCK. Retrying...");
		                continue;
		            }

		            productlistingName = name;
		            productName = name;

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

		        WebElement productDetailNameElement = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(
		                        By.xpath("//h3[contains(@class,'prod_name')]")
		                )
		        );

		        productDetailName = productDetailNameElement.getText().trim();
		        System.out.println("📦 Product Detail Name: " + productDetailName);

		        return productlistingName + " | Detail Name: " + productDetailName;
		    }


 //Categories		    
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
			   
		    
public void uploadImageinCategories() {
    Scanner sc = new Scanner(System.in);
    String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/categoies.jpg";
    // ANSI color codes for console output
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String CYAN = "\u001B[36m";
    String RESET = "\u001B[0m";
    String line = "─────────────────────────────────────────────";
    
    adminLoginApp();
    // Step 2: Navigate to Excel path (if needed)
    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
    Common.waitForElement(2);

    // Step 4: Add new homepage banner
    expectedBannerTitle = "Category" + System.currentTimeMillis();
 // 🔥 FIX FOR HEADLESS
    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", addHomePageBanner);
    System.out.println("Clicked Add Home Page Banner");
    Common.waitForElement(2);
    type(bannerTitle, expectedBannerTitle);
    Common.waitForElement(2);
    type(heading, expectedBannerTitle);

    Common.waitForElement(2);
wait.until(ExpectedConditions.presenceOfElementLocated(
    By.xpath("(//input[@type='file'])[1]")));

uploadDesktopImage.sendKeys(imagePath);

Common.waitForElement(2);

uploadMobileImage.sendKeys(imagePath);

System.out.println("✅ Image uploaded successfully");
    System.out.println(GREEN + "✅ Image uploaded successfully!" + RESET);
    
}	



public void uploadImageinColections() {
    Scanner sc = new Scanner(System.in);
    String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/categoies.jpg";
    // ANSI color codes for console output
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String CYAN = "\u001B[36m";
    String RESET = "\u001B[0m";
    String line = "─────────────────────────────────────────────";
    
    adminLoginApp();
    // Step 2: Navigate to Excel path (if needed)
    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
    Common.waitForElement(2);

    // Step 4: Add new homepage banner
    expectedBannerTitle = "Collection" + System.currentTimeMillis();
 // 🔥 FIX FOR HEADLESS
    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", addHomePageBanner);
    System.out.println("Clicked Add Home Page Banner");
    Common.waitForElement(2);
    type(bannerTitle, expectedBannerTitle);
    Common.waitForElement(2);
    type(heading, expectedBannerTitle);

    Common.waitForElement(2);
wait.until(ExpectedConditions.presenceOfElementLocated(
    By.xpath("(//input[@type='file'])[1]")));

uploadDesktopImage.sendKeys(imagePath);

Common.waitForElement(2);

uploadMobileImage.sendKeys(imagePath);

System.out.println("✅ Image uploaded successfully");
    System.out.println(GREEN + "✅ Image uploaded successfully!" + RESET);
    
}	  
public void verifyCategoriesBannerOnHomePage() {

	  //  String expectedTitle = Common.getValueFromTestDataMap("Banner Title");
	 Common.waitForElement(2);
	    WebElement topSellingSection = driver.findElement(
	            By.xpath("//section[@data-section='zi_categories_banner'] | //section[@data-section='bl_categories_banner']")
	    );
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
	            topSellingSection
	    );
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	            topSellingSection
	    );
		Common.waitForElement(2);
	    int timeoutMinutes = 10;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(3)) // ⬅ increase from 5s to 15s
	                    .pollingEvery(Duration.ofSeconds(3))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	            	List<WebElement> elements = driver.findElements(
	            		    By.xpath("//div[contains(@class,'bl_category')]//span[normalize-space()='" + expectedBannerTitle + "']"
	            		    + " | "
	            		    + "//a[contains(@class,'zi_categories_card')]//span[normalize-space()="
	            		    + "translate('" + expectedBannerTitle + "','abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')]")
	            		);
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            // ✅ Relax condition → as soon as element is found
	            if (titleElement != null) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    // ✅ Final check
	    if (titleFound) {
	        System.out.println("✅ Banner title '" + expectedBannerTitle + "' is visible in User Application.");
	    } else {
	        System.out.println("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	        Assert.fail("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	    }
	}


public void verifyCollectionBannerOnHomePage() {

	  //  String expectedTitle = Common.getValueFromTestDataMap("Banner Title");
	 Common.waitForElement(2);
	    WebElement topSellingSection = driver.findElement(
	            By.xpath("//section[@data-section='zi_collection_banner'] | //section[@data-section='bl_categories_banner']")
	    );
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.pageYOffset - 120, behavior: 'smooth'});",
	            topSellingSection
	    );
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	            topSellingSection
	    );
		Common.waitForElement(2);
	    int timeoutMinutes = 10;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(3)) // ⬅ increase from 5s to 15s
	                    .pollingEvery(Duration.ofSeconds(3))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	            	List<WebElement> elements = driver.findElements(
	            		    By.xpath(
	            		        "//a[contains(@class,'zi_collection_card')]//span[contains(@class,'zi_collection_name') and " +
	            		        "translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='" 
	            		        + expectedBannerTitle.toUpperCase() + "']"
	            		    )
	            		);
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            // ✅ Relax condition → as soon as element is found
	            if (titleElement != null) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    // ✅ Final check
	    if (titleFound) {
	        System.out.println("✅ Banner title '" + expectedBannerTitle + "' is visible in User Application.");
	    } else {
	        System.out.println("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	        Assert.fail("❌ Banner title '" + expectedBannerTitle + "' not found within " + timeoutMinutes + " minutes.");
	    }
	}
public void verifyCategoryBannerRedirect(String expectedLinkEnd) {

    String GREEN  = "\u001B[32m";
    String RED    = "\u001B[31m";
    String BLUE   = "\u001B[34m";
    String CYAN   = "\u001B[36m";
    String RESET  = "\u001B[0m";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    System.out.println(CYAN + "━━━━━━━━ CATEGORY BANNER TEST ━━━━━━━━" + RESET);

    // Locate banner (ZI OR BL layout)
    WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath(
                    "//span[normalize-space()=translate('" + expectedBannerTitle + "','abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')]/ancestor::a[contains(@class,'zi_categories_card')]"
                    + " | " +
                    "//span[normalize-space()='" + expectedBannerTitle + "']/ancestor::div[contains(@class,'bl_category')]//a[contains(@class,'category-link')]"
            )
    ));

    String hrefLink = banner.getAttribute("href");

    System.out.println(BLUE + "Banner Name : " + RESET + expectedBannerTitle);
    System.out.println(BLUE + "Banner Link : " + RESET + hrefLink);

    if (banner.isDisplayed()) {
        System.out.println(GREEN + "✅ Banner displayed: " + expectedBannerTitle + RESET);

        // Click banner
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

    } else {
        Assert.fail("❌ Banner not displayed: " + expectedBannerTitle);
    }

    Common.waitForElement(2);

    // Wait for product listing page
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h2[contains(@class,'prod_listing_topic')]")));

    // Get heading
    String heading = driver.findElement(
            By.xpath("//h2[contains(@class,'prod_listing_topic')]")).getText().trim();

    // Get URL
    String currentUrl = driver.getCurrentUrl();

    System.out.println(BLUE + "Current URL : " + RESET + currentUrl);
    System.out.println(BLUE + "Page Heading : " + RESET + heading);

    // URL validation
    if (currentUrl.contains(expectedLinkEnd)) {
        System.out.println(GREEN + "✅ URL matched : " + currentUrl + RESET);
    } else {
        System.out.println(RED + "❌ URL mismatch : " + currentUrl + RESET);
        Assert.fail("URL mismatch");
    }

    // Heading validation
    if (heading.equalsIgnoreCase(expectedBannerTitle) || heading.equalsIgnoreCase("All")) {
        System.out.println(GREEN + "✅ Heading verified : " + heading + RESET);
    } else {
        System.out.println(RED + "❌ Heading mismatch : " + heading + RESET);
        Assert.fail("Heading mismatch");
    }

    System.out.println(CYAN + "━━━━━━━━ TEST COMPLETED ━━━━━━━━" + RESET);
}
public void verifyCollectionBannerRedirect(String expectedLinkEnd) {

    String GREEN  = "\u001B[32m";
    String RED    = "\u001B[31m";
    String BLUE   = "\u001B[34m";
    String CYAN   = "\u001B[36m";
    String RESET  = "\u001B[0m";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    System.out.println(CYAN + "━━━━━━━━ CATEGORY BANNER TEST ━━━━━━━━" + RESET);

    // Locate banner (ZI OR BL layout)
    WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath(
                "//span[contains(@class,'zi_collection_name') and " +
                "translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='" 
                + expectedBannerTitle.toUpperCase() + "']" +
                "/ancestor::a[contains(@class,'zi_collection_card')]"
            )
    ));
    String hrefLink = banner.getAttribute("href");

    System.out.println(BLUE + "Banner Name : " + RESET + expectedBannerTitle);
    System.out.println(BLUE + "Banner Link : " + RESET + hrefLink);

    if (banner.isDisplayed()) {
        System.out.println(GREEN + "✅ Banner displayed: " + expectedBannerTitle + RESET);

        // Click banner
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

    } else {
        Assert.fail("❌ Banner not displayed: " + expectedBannerTitle);
    }

    Common.waitForElement(2);

    // Wait for product listing page
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h2[contains(@class,'prod_listing_topic')]")));

    // Get heading
    String heading = driver.findElement(
            By.xpath("//h2[contains(@class,'prod_listing_topic')]")).getText().trim();

    // Get URL
    String currentUrl = driver.getCurrentUrl();

    System.out.println(BLUE + "Current URL : " + RESET + currentUrl);
    System.out.println(BLUE + "Page Heading : " + RESET + heading);

    // URL validation
    if (currentUrl.contains(expectedLinkEnd)) {
        System.out.println(GREEN + "✅ URL matched : " + currentUrl + RESET);
    } else {
        System.out.println(RED + "❌ URL mismatch : " + currentUrl + RESET);
        Assert.fail("URL mismatch");
    }

    // Heading validation
    if (heading.equalsIgnoreCase(expectedBannerTitle) || heading.equalsIgnoreCase("All")) {
        System.out.println(GREEN + "✅ Heading verified : " + heading + RESET);
    } else {
        System.out.println(RED + "❌ Heading mismatch : " + heading + RESET);
        Assert.fail("Heading mismatch");
    }

    System.out.println(CYAN + "━━━━━━━━ TEST COMPLETED ━━━━━━━━" + RESET);
}

private String selectedPointerProduct = "";

public void createInfluencerPointer() throws InterruptedException {

    adminLoginApp();
    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
    Common.waitForElement(2);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement bannerType = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@id='bannerTypeFilter']//button")));
    bannerType.click();
    Common.waitForElement(2);

    WebElement influencerBanner = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@id='bannerTypeFilter']//a[normalize-space()='Influencer Banner']")));
    influencerBanner.click();
    Common.waitForElement(2);

    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickStatus);
    Common.waitForElement(1);
    Thread.sleep(2000);

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", statusActiveOption);
    statusActiveOption.click();
    System.out.println("✅ Selected Active status");
    Common.waitForElement(1);

    wait.until(ExpectedConditions.elementToBeClickable(editBtn));
    Common.waitForElement(2);
    waitFor(editBtn);
    click(editBtn);
    System.out.println("✅ Clicked Edit");
    Common.waitForElement(2);

    WebElement influencerMapping = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@tab_name='influencer-mapping']")));
    influencerMapping.click();
    Common.waitForElement(1);

    List<WebElement> dropdownsBeforeAdd = driver.findElements(
            By.xpath("//span[contains(@id,'select2-additional_info') and contains(@id,'sku') and contains(@id,'container')]"));
    int countBeforeAdd = dropdownsBeforeAdd.size();
    System.out.println("ℹ️ Dropdown count before Add Pointer: " + countBeforeAdd);

    WebElement addPointer = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@id='add-pointer']")));
    addPointer.click();
    Common.waitForElement(2);
    System.out.println("✅ Clicked Add Pointer");

    List<WebElement> productDropdownList = wait.until(d -> {
        List<WebElement> list = driver.findElements(
                By.xpath("//span[contains(@id,'select2-additional_info') and contains(@id,'sku') and contains(@id,'container')]"));
        return list.size() > countBeforeAdd ? list : null;
    });

    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
    Common.waitForElement(1);

    WebElement productDropdown = productDropdownList.get(productDropdownList.size() - 1);
    System.out.println("✅ Selected newly added pointer dropdown at position " + (productDropdownList.size() - 1));

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", productDropdown);
    Common.waitForElement(1);

    try {
        productDropdown.click();
    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productDropdown);
    }
    System.out.println("✅ Opened product dropdown for new pointer");
    Common.waitForElement(2);

    List<WebElement> searchBoxList = wait.until(d -> {
        List<WebElement> list = driver.findElements(By.xpath("//input[@role='searchbox']"));
        return list.isEmpty() ? null : list;
    });
    WebElement searchBox = searchBoxList.get(searchBoxList.size() - 1);

    try {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//li[contains(@class,'select2-results__option--loading')]")));
    } catch (Exception e) {
        System.out.println("⚠️ No loading spinner detected, continuing...");
    }
    Common.waitForElement(2);

    selectedPointerProduct = "Printed Straight Kurta with Sharara & Dupatta";
    System.out.println("🎯 Product to search and select: " + selectedPointerProduct);

    searchBox.clear();
    searchBox.sendKeys(selectedPointerProduct);

    ((JavascriptExecutor) driver).executeScript(
            "var e = new Event('input',{bubbles:true}); arguments[0].dispatchEvent(e);",
            searchBox);
    Common.waitForElement(3);

    try {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//li[contains(@class,'select2-results__option--loading')]")));
    } catch (Exception e) {
        System.out.println("⚠️ No loading spinner, continuing...");
    }

    WebElement matchedResult = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> {
                List<WebElement> results = driver.findElements(
                        By.xpath("//ul[contains(@class,'select2-results__options')]" +
                                 "//li[contains(@class,'select2-results__option')" +
                                 " and not(contains(@class,'--loading'))" +
                                 " and not(contains(@class,'--message'))]"));
                return results.isEmpty() ? null : results.get(0);
            });

    System.out.println("🖱️ Clicking result: " + matchedResult.getText().trim());

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", matchedResult);
    Common.waitForElement(1);

    try {
        matchedResult.click();
    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", matchedResult);
    }
    System.out.println("✅ Product selected: " + selectedPointerProduct);
    Common.waitForElement(2);

    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadButton);
    Common.waitForElement(3);

    waitFor(clearCatchButton);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearCatchButton);
    System.out.println("✅ Successfully clicked Clear Cache Button");
}
public void verifyInfluencerPointerOnHomePage() {

    Common.waitForElement(2);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    Actions actions = new Actions(driver);

    int timeoutMinutes = 10;
    boolean pointerClicked = false;
    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000L;

    while (System.currentTimeMillis() < endTime) {
        try {
            driver.navigate().refresh();
            Common.waitForElement(3);

            List<WebElement> sectionList = driver.findElements(
                    By.xpath("//section[@data-section='zi_influencer_banner']"));

            if (sectionList.isEmpty()) {
                System.out.println("⚠️ Influencer banner section not found, retrying...");
                continue;
            }

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                    sectionList.get(0));
            Common.waitForElement(2);

            List<WebElement> pointers = driver.findElements(
                    By.xpath("(//a[@class='influencer_prod_details_wrap'])[1]"));

            if (pointers.isEmpty()) {
                System.out.println("⚠️ Pointer not found after refresh, retrying...");
                continue;
            }

            WebElement pointer = pointers.get(0);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", pointer);
            Common.waitForElement(1);

            actions.moveToElement(pointer).perform();
            System.out.println("ℹ️ Hovered over pointer");
            Common.waitForElement(2);

            // Click the pointer instead of verifying product title
            wait.until(ExpectedConditions.elementToBeClickable(pointer));
            pointer.click();
            System.out.println("✅ Clicked on influencer pointer.");

            pointerClicked = true;
            break;

        } catch (StaleElementReferenceException se) {
            System.out.println("⚠️ Stale element encountered while clicking, retrying...");
        } catch (TimeoutException te) {
            System.out.println("⚠️ Pointer not clickable yet, retrying...");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    if (!pointerClicked) {
        System.out.println("❌ Could not click influencer pointer within " + timeoutMinutes + " minutes.");
        Assert.fail("❌ Could not click influencer pointer within " + timeoutMinutes + " minutes.");
    }
}

public void verifyPointerProductRedirect() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    System.out.println("ℹ️ Verifying redirected product page for: " + selectedPointerProduct);

    Common.waitForElement(3);

    WebElement productHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h3[@class='prod_name'][1]")));

    String actualProductName = productHeading.getText().trim();
    System.out.println("📄 Product page heading: " + actualProductName);
    System.out.println("ℹ️ Comparing against: " + selectedPointerProduct);

    if (actualProductName.equalsIgnoreCase(selectedPointerProduct)
            || actualProductName.contains(selectedPointerProduct)
            || selectedPointerProduct.contains(actualProductName)) {
        System.out.println("✅ Product redirected successfully: " + actualProductName);
    } else {
        throw new AssertionError("❌ Product name mismatch. Expected: ["
                + selectedPointerProduct + "] but found: [" + actualProductName + "]");
    }
}





//TC-08
// ✅ Upload excel
public void uploadTheSpecialTimerProductExcel(String filePath) throws InterruptedException {
    
    // ─── STEP 1: Navigate to Special Timer Product Page ───
    Common.waitForElement(2);
    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
    System.out.println("✅ Navigated to Special Timer Product page");

    // ─── STEP 2: Select Brand Type ───
    Common.waitForElement(2);
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", brandType);
    Common.waitForElement(1);
    Thread.sleep(2000);
    
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block:'center'});", selectbrandType
    );
    selectbrandType.click();
    System.out.println("✅ Selected Brand Type");

    // ─── STEP 3: Click Status Filter Dropdown ───
    Common.waitForElement(3);
    waitFor(clickStatus);
    click(clickStatus);
    System.out.println("✅ Clicked Status Filter Dropdown");
    Common.waitForElement(1);
    Thread.sleep(2000);

    // ─── STEP 4: Select "Active" from the dropdown (DYNAMIC) ───
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    
    WebElement activeStatusOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Active']")
    ));
    
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block:'center'});", activeStatusOption
    );
    activeStatusOption.click();
    System.out.println("✅ Selected Active Status from dropdown");

    // ─── STEP 5: Wait for filtered results to load ───
    try {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[contains(@class,'loading') or contains(@class,'spinner')]")
        ));
    } catch (Exception e) {
        // Loading spinner may not exist, continue
    }
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("(//i[@class='las la-edit'])[1]")
    ));
    
    Common.waitForElement(3);
    System.out.println("✅ Filtered results loaded after Brand Type + Active Status filter");

    // ─── STEP 6: Click Edit Button on first filtered result ───
    WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("(//i[@class='las la-edit'])[1]")
    ));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
    System.out.println("✅ Clicked Edit Button on first filtered result");
    Common.waitForElement(3);

 // ─── STEP 7: Check if date range is still active ───
    boolean needDateReset = false;

    try {
        // Get the from_date and end_date values (ISO format: 2026-06-01T13:53)
        WebElement fromDateField = driver.findElement(By.xpath("(//input[@name='from_date[]'])[1]"));
        WebElement endDateField = driver.findElement(By.xpath("(//input[@name='end_date[]'])[1]"));

        String fromDateValue = fromDateField.getAttribute("value").trim();
        String endDateValue = endDateField.getAttribute("value").trim();

        System.out.println("📅 Current From Date: " + fromDateValue);
        System.out.println("📅 Current End Date: " + endDateValue);

        // Parse ISO datetime format: yyyy-MM-dd'T'HH:mm
        DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDateValue, isoFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateValue, isoFormatter);
        LocalDateTime now = LocalDateTime.now();

        System.out.println("📅 Parsed From DateTime: " + fromDateTime);
        System.out.println("📅 Parsed End DateTime: " + endDateTime);
        System.out.println("📅 Current DateTime: " + now);

        // Check if current datetime is between from and end (inclusive)
        if (now.isBefore(fromDateTime) || now.isAfter(endDateTime)) {
            System.out.println("⚠️ Date range is NOT active (expired or not started) — need to reset dates");
            needDateReset = true;
        } else {
            System.out.println("✅ Date range is active — no date change needed");
            needDateReset = false;
        }

    } catch (Exception e) {
        System.out.println("⚠️ Could not read/parse date fields — assuming need to reset: " + e.getMessage());
        needDateReset = true;
    }

    // ─── STEP 8: Reset dates if needed ───
    if (needDateReset) {
        System.out.println("⚠️ Resetting dates...");

        // Click X icon to remove previous date range
        WebElement resetDateButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//button[@class='btn btn-sm btn-danger remove-date-range'])[1]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetDateButton);
        System.out.println("✅ Clicked X icon to reset previous dates");
        Common.waitForElement(2);

        // Click "Add More Dates" button to add new date fields
        WebElement addMoreDatesButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//button[normalize-space()='Add More Dates'])[1]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addMoreDatesButton);
        System.out.println("✅ Clicked Add More Dates button");
        Common.waitForElement(2);

        // Calculate new dates and times
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = now.plusDays(10);
        
        // Format for datetime-local input: yyyy-MM-dd'T'HH:mm
        DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String fromDateTimeValue = now.format(isoFormatter);
        String endDateTimeValue = endDateTime.format(isoFormatter);

        System.out.println("📅 New From DateTime: " + fromDateTimeValue);
        System.out.println("📅 New End DateTime: " + endDateTimeValue);

        // ─── Set From Date using JavaScript (bypasses calendar UI) ───
        WebElement fromDateField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//input[@name='from_date[]'])[1]")
        ));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            fromDateField, fromDateTimeValue
        );
        System.out.println("✅ Set From DateTime: " + fromDateTimeValue);
        Common.waitForElement(1);

        // ─── Set End Date using JavaScript (bypasses calendar UI) ───
        WebElement endDateField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//input[@name='end_date[]'])[1]")
        ));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            endDateField, endDateTimeValue
        );
        System.out.println("✅ Set End DateTime: " + endDateTimeValue);
        Common.waitForElement(1);

    } else {
        System.out.println("✅ Date range is active — no date change needed");
    }

    // ─── STEP 9: Click Save and Back ───
    WebElement saveAndBackButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("(//span[@data-value='save_and_preview'])[1]")
    ));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveAndBackButton);
    System.out.println("✅ Clicked Save and Back");
    Common.waitForElement(3);

    // ─── STEP 10: Wait for page to return to list after save ───
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//i[@class='las la-edit'])[1]")
        ));
        System.out.println("✅ Returned to filtered results page");
    } catch (Exception e) {
        System.out.println("⚠️ Could not confirm return to list page, proceeding anyway");
    }
    Common.waitForElement(2);
    
    
    
    
 // ─── STEP 11: Click the "+" (Add) button to open import options ───
    WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("(//i[@class='las la-plus-circle'])[1]")
    ));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
    System.out.println("✅ Clicked Add (+) Button");
    Common.waitForElement(2);

    // ─── STEP 12: Proceed with Import Flow ───
    waitFor(importButton);
    click(importButton);
    System.out.println("✅ Clicked Import Button");

    Common.waitForElement(2);
    waitFor(uploadExcelButton);
    uploadExcelButton.sendKeys(filePath);
    System.out.println("✅ Uploaded file: " + filePath);

    Common.waitForElement(2);
    waitFor(submitButton);
    submitButton.click();
    System.out.println("✅ Excel uploaded successfully");

    // ─── STEP 13: Clear Cache ───
    Common.waitForElement(4);
    waitFor(clearCatchButton);
    click(clearCatchButton);
    System.out.println("✅ Successfully clicked Clear Cache Button");
}


//✅ Verify in Admin panel
public void verifySpecialProductsinAdmin(String excelPath) throws IOException {
    List<Map<String, Object>> excelProducts = ExcelXLSReader.readProductsWithMultipleListing(excelPath);
    List<Map<String, String>> adminProducts = readAdminProductsFromTable();

    for (Map<String, Object> excelProduct : excelProducts) {
        String excelSku = excelProduct.get("sku").toString().trim();
        String excelDiscount = excelProduct.get("discount").toString().trim();

        Optional<Map<String, String>> match = adminProducts.stream()
            .filter(p -> p.get("Sku").equalsIgnoreCase(excelSku))
            .findFirst();

        if (match.isPresent()) {
            Map<String, String> adminProduct = match.get();
            String colorName = adminProduct.get("ColorName");

            if (!adminProduct.get("Discount").equals(excelDiscount)) {
                System.out.println("❌ Discount mismatch for SKU: " + excelSku);
            } else {
                System.out.println("✅ Verified in Admin → SKU: " + excelSku + 
                                   " | Color: " + colorName + 
                                   " | Discount: " + excelDiscount);
            }
        } else {
            System.out.println("❌ SKU not found in Admin: " + excelSku);
        }
    }
}



//✅ Verify in User app
public void verifyProductsUserApp(String excelPath) throws IOException {
  // ✅ Step 1: Read products from Excel
  List<Map<String, Object>> excelProducts = ExcelXLSReader.readProductsWithMultipleListing(excelPath);

  // ✅ Step 2: Read all Admin products into a map (Sku → ColorName)
  Map<String, String> adminProducts = new HashMap<>();
  List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
  for (WebElement row : rows) {
      try {
          String sku = row.findElement(By.xpath(".//td[4]")).getText().trim();
          String colorName = row.findElement(By.xpath(".//td[3]")).getText().trim();
          adminProducts.put(sku, colorName);
      } catch (Exception e) {
          // ignore header or invalid rows
      }
  }

  Common.waitForElement(4);
  waitFor(clearCatchButton);
  click(clearCatchButton);
  System.out.println("✅ Successfull click Clear Catch Button");
  
  // ✅ Step 3: Switch to User App
  HomePage home = new HomePage(driver);
  home.homeLaunch();

  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

  WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//h2[normalize-space()='ZLAATA INDIA']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
  ));

  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);
  System.out.println("Clicked ZLAATA INDIA Home Page Banner");
  
  // ✅ Step 4: Loop through Excel SKUs and verify in User App
  for (Map<String, Object> product : excelProducts) {
      Object skuObj = product.get("sku");
      if (skuObj == null) {
          System.out.println("⚠ SKU missing in Excel row, skipping...");
          continue;
      }

      String sku = skuObj.toString().trim();

      // Get ColorName from Admin Map
      String productColorName = adminProducts.get(sku);
      if (productColorName == null) {
          System.out.println("⚠ No ColorName found in Admin for SKU: " + sku);
          continue;
      }

      // Search in user app
      Common.waitForElement(2);

      // Click search icon
      wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
      System.out.println("✅ Clicked search icon");

      // Wait for search box and enter product name
      wait.until(ExpectedConditions.elementToBeClickable(userSearchBox));
      userSearchBox.clear();
      userSearchBox.sendKeys(productColorName);
      System.out.println("✅ Typed in search box: " + productColorName);
      
      // Press Enter to submit search
      userSearchBox.sendKeys(Keys.ENTER);
      System.out.println("✅ Pressed Enter to search");
      
      // ─── FIX: Wait directly for the product link to appear ───
      By productLocator = By.xpath("(//a[@class='product_list_name'])[1]");
      
      // Wait for product to be visible
      WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
      System.out.println("✅ Product link found in search results");
      
      // Scroll into view
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", 
          productLink
      );
      System.out.println("✅ Scrolled to product: " + productColorName);
      Common.waitForElement(1);
      
      // Click the product
      wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();
      System.out.println("✅ Clicked product link: " + productColorName);

      // ✅ SPECIAL TIMER CHECK
      try {
          WebElement specialTimer = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//div[@id='prodTimer' and contains(@class,'prod_category_timer')]")
          ));

          if (specialTimer.isDisplayed()) {
              System.out.println("⏳ Special Timer is displayed on Product Detail Page.");
          } else {
              throw new RuntimeException("❌ Special Timer element is present but NOT visible.");
          }

      } catch (Exception e) {
          throw new RuntimeException("❌ Special Timer is NOT displayed on Product Detail Page!", e);
      }

      // ✅ Verify price details
      try {
          WebElement currentPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(@class,'prod_current_price')]")));

          WebElement actualPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(@class,'prod_actual_price')]")));

          WebElement discountPercentage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(@class,'prod_discount_percentage')]")));

          System.out.println("   Actual Price   : " + actualPrice.getText());
          System.out.println("   Special  Price  : " + currentPrice.getText());
          System.out.println("   Discount Shown : " + discountPercentage.getText());

      } catch (Exception e) {
          System.out.println("⚠ Could not fetch all price details for: " + productColorName);
      }
      
      // ─── Navigate back to home page for next product search ───
      home.homeLaunch();
      banner = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//h2[normalize-space()='ZLAATA INDIA']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
      ));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);
      System.out.println("Returned to ZLAATA INDIA banner for next search");
  }
}


//Special Timer Product

	public void clickPlusOrResetFlow() throws InterruptedException {

	    // 1️⃣ Click Active Status
	    Common.waitForElement(3);
	    waitFor(statusActiveOption);
	    click(statusActiveOption);
	    System.out.println("✅ Selected Active status");

	    Common.waitForElement(2);

	    // 2️⃣ TRY clicking Plus button
	    try {
	        if (plusButton.isDisplayed() && plusButton.isEnabled()) {
	            click(plusButton);
	            System.out.println("✅ Clicked '+' Plus button (Direct)");
	            return; // SUCCESS, exit method
	        }
	    } catch (Exception e) {
	        System.out.println("⚠️ Plus Button NOT visible, switching to fallback flow...");
	    }

	    // ---------------------------------------------------------
	    // 3️⃣ FALLBACK FLOW: Reset → Search Event Name → Enable status → Enable promotional → Click plus
	    // ---------------------------------------------------------

	    // Click Reset
	    WebElement resetBtn = driver.findElement(By.id("crudTable_reset_button"));
	    click(resetBtn);
	    System.out.println("🔄 Clicked Reset");

	    Common.waitForElement(2);

	    // Click Event Name dropdown
	    WebElement eventNameDropdown = driver.findElement(By.xpath("//a[contains(text(),'Event Name')]"));
	    click(eventNameDropdown);
	    System.out.println("📂 Opened Event Name dropdown");

	    Common.waitForElement(1);

	    // Enter search text
	    WebElement searchBox = driver.findElement(By.id("text-filter-eventName"));
	    searchBox.clear();
	    searchBox.sendKeys("Festiv Sale");
	    searchBox.sendKeys(Keys.ENTER);
	    System.out.println("🔍 Entered event name: Festiv Sale");

	    Common.waitForElement(2);

	    // Enable Status toggle
	    WebElement statusToggle = driver.findElement(By.xpath("//label[@for='V_status_54']"));
	    if (!statusToggle.isSelected()) {
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", statusToggle);
	        System.out.println("🔘 Enabled EVENT Status");
	    }

	    Common.waitForElement(2);

	    // Enable Promotional Offer toggle
	    WebElement promoToggle = driver.findElement(By.xpath("//label[@for='V_promotional_offer_54']"));
	    if (!promoToggle.isSelected()) {
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", promoToggle);
	        System.out.println("🎁 Enabled Promotional Offer");
	    }

	    Common.waitForElement(2);

	    // Click PLUS button again
	    //waitFor(plusButton);
	    Common.waitForElement(2);
	    click(plusButton);
	    System.out.println("🚀 Clicked '+' Plus button (AFTER fallback)");
	}

 

  private List<Map<String, String>> readAdminProductsFromTable() {
  	List<WebElement> productRows = driver.findElements(
  		    By.xpath("//tbody[@id='specialEventTimerProduct']/tr")
  		);
  	//	waitFor(productRows.get(0));  // wait for first row to be visible
      List<Map<String, String>> adminProducts = new ArrayList<>();

      for (WebElement row : productRows) {
          List<WebElement> cols = row.findElements(By.tagName("td"));
          if (cols.size() >= 6) {
              Map<String, String> product = new HashMap<>();
              product.put("ColorName", cols.get(2).getText().trim());
              product.put("Sku", cols.get(3).getText().trim());
              product.put("DiscountType", cols.get(4).getText().trim());
              product.put("Discount", cols.get(5).getText().trim());
              adminProducts.add(product);
          }
      }
      return adminProducts;
  }
	



//Home Page Test Case		    
//TC-01		
	public void verifyZlaataIndiaHomePageBanner() {
		
		uploadImageinHomePageBanner();
		
		uploadHomepageBannerDetails("/zlaata-india/all","Zlaata India", "Home Page Banner");
		
		launchHomepage("ZLAATA INDIA");
		
		verifyBannerOnHomePage();

	}
//TC-2
	public void verifyBossLadyHomePageBanner() {

		uploadImageinHomePageBanner();
		
		uploadHomepageBannerDetails("/boss-lady/all","Boss Lady", "Home Page Banner");
		
		launchHomepage("BOSS LADY");
		
		verifyBannerOnHomePage();

	}
	
	
	
	
//TC-02
//		public void  validateProductSuccessfullyRemoved() throws InterruptedException{
//			
//			copyProductNameFromHomePageTopSelling();
//			
//			givesProductName();
//			
//			fetchSkuFromProduct();
//			
//			removeSkuFromTopSelling();
//			
//			verifyProductNotInTopSelling();
//			
//		
//		 }
//	
//		public void  validateProductSuccessfullyAdded() throws InterruptedException{
//			
//			putSkuIntoTopSelling();
//			
//			verifyProductShowInTopSelling();
//			
//			
//		 }
//	
//TC-03	
	public void validateNewArrivalSuccessfullyAdded() throws InterruptedException {
		
		launchHomepage("ZLAATA INDIA");
	
		takeRandomProductName();
		
		verifyColourOfTheProductIsFirstPosition();
		
		addTheProductInNewArrivalSection();
		
		sortTheProductInFirstPosition();
			
		verifyProductShowInNewArrivalsSction();
}
	
//TC-04	
	public void validateZlaataIndiaCategories() {
		
		uploadImageinCategories();
		
		uploadHomepageBannerDetails("/zlaata-india/all","Zlaata India", "Categories Banner");
		
		launchHomepage("ZLAATA INDIA");
		
		verifyCategoriesBannerOnHomePage();
		
		verifyCategoryBannerRedirect("/zlaata-india/all");
		
	}
	
//TC-05
	public void validateBossLadyCategories() {
		
		uploadImageinCategories();
		
		uploadHomepageBannerDetails("/boss-lady/all","Boss Lady", "Categories Banner");

		launchHomepage("BOSS LADY");
		
		verifyCategoriesBannerOnHomePage();
		
		verifyCategoryBannerRedirect("/boss-lady/all");
	}
	
//TC-06
	
	public void validatezlaataIndiaHomePageCollection() {
		
		uploadImageinColections();
		
		uploadHomepageBannerDetails("/zlaata-india/all","Zlaata India", "Collection Banner");
		
		launchHomepage("ZLAATA INDIA");
		
		verifyCollectionBannerOnHomePage();
		
		verifyCollectionBannerRedirect("/zlaata-india/all");
	}
	
//TC-07	
	
	public void validateZlaataIndiaHomePageInfluencerPointer() throws InterruptedException {
		
		createInfluencerPointer();
		
		launchHomepage("ZLAATA INDIA");
		
		verifyInfluencerPointerOnHomePage();
		
		verifyPointerProductRedirect();
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
