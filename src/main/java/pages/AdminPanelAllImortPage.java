package pages;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import manager.FileReaderManager;
import objectRepo.AdminPanelAllImportObjRepo;
import stepDef.ExtentManager;
import utils.Common;
import utils.ExcelXLSReader;

public class AdminPanelAllImortPage extends AdminPanelAllImportObjRepo{
	
	public AdminPanelAllImortPage(WebDriver driver) 
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
	
//Import Categories
	
	public void uploadTheCategoriesImport(String filePath) {
    	Common.waitForElement(2);
        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
        
        System.out.println("✅ Redirected to Admin product Catagories page");
 
        Common.waitForElement(2);
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
	    Common.waitForElement(5);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successful click Clear Catch Button");
		
	}
	
    public void verifyCategoriesInAdmin(String filePath) throws IOException {
        Common.waitForElement(2);

        // ✅ Read Excel → Filter only non-empty Categories
        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
            .stream()
            .filter(product -> {
                Object categoryObj = product.get("Category Name");  // <-- Excel column name
                return categoryObj != null && !categoryObj.toString().trim().isEmpty();
            })
            .collect(Collectors.toList());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        for (Map<String, Object> product : products) {
            String categoryName = (String) product.get("Category Name");

            driver.navigate().refresh();
            Common.waitForElement(3);
            wait.until(ExpectedConditions.elementToBeClickable(categoriesNameButton)).click();
            System.out.println("✅ Clicked Categories button");
            wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
            searchTextBox.clear();
            searchTextBox.sendKeys(categoryName);
            Common.waitForElement(2);
            searchTextBox.sendKeys(Keys.ENTER);
            System.out.println("✅ Searched for Category: " + categoryName);

            // ✅ Verify category visible in table
            By categoryLocator = By.xpath("//span[normalize-space()='" + categoryName + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoryLocator));
            System.out.println("✅ Category is visible in Admin panel: " + categoryName);
            
            // Click on Search Box for Product Sort
//    		Common.waitForElement(3);
//    		click(searchProductSortMenu);
//    		waitFor(searchProductSortMenu);
//    		type(searchProductSortMenu, "Product Sorts");
//    		System.out.println("Typed 'Product Sorts");
//    		Common.waitForElement(2);
//    		waitFor(clickProductSort);
//    		click(clickProductSort);
//    		System.out.println("Selected Product Sorts");
//    		Common.waitForElement(2);
//    		waitFor(addProductSort);
//    		click(addProductSort);
//    		System.out.println("Clicked add product Sort");
//    		Common.waitForElement(2);
//    		waitFor(categoryType);
//    		click(categoryType);
//    		System.out.println("Clicked Category Type");
//    		Common.waitForElement(2);
//    		waitFor(categorySearchTextBox);
//    		type(categorySearchTextBox,"Category");
//    		categorySearchTextBox.sendKeys(Keys.ENTER);
//    		System.out.println("Typed 'Category Name' & pressed Enter");
//    		Common.waitForElement(2);
//    		waitFor(categoryId);
//    		click(categoryId);
//    		System.out.println("Clicked Catagory Id Type");
//    		Common.waitForElement(2);
//    		waitFor(categorySearchTextBox);
//    		type(categorySearchTextBox,categoryName);
//    		categorySearchTextBox.sendKeys(Keys.ENTER);
//    		System.out.println("Typed 'Category id' & pressed Enter");
//    		Common.waitForElement(2);
//            waitFor(saveButton);
//            saveButton.click();
//            System.out.println("✅ Excel uploaded successfully");
    		
        }
        
    
      //Clear Catch
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
		
	

        System.out.println("🎉 All categories verification completed successfully!");
    }
    
    public void verifyCategoryInZlMenu(String filePath) throws IOException {

        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        for (Map<String, Object> product : products) {

            String category = (String) product.get("Category Name");

            if (category == null || category.trim().isEmpty()) {
                System.out.println("⚠ Skipping empty category");
                continue;
            }

            System.out.println("➡ Processing Category: " + category);

            try {

                // ✅ Step 1: Open Zl Menu
                Common.waitForElement(2);
                click(searchProductSortMenu);
                type(searchProductSortMenu, "Zl Menu");
                click(clickZlMenu);
                System.out.println("✅ Selected Zl Menu");
                Common.waitForElement(2);

                // ✅ Step 2: Click Header
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@href,'zl-menu?location=1')]"))).click();

                // ✅ Step 3: Select Brand → Zlaata India
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[@filter-name='brand_type']//a[contains(text(),'Brand')]"))).click();

                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@parameter='brand_type' and contains(text(),'Zlaata India')]"))).click();

                System.out.println("✅ Brand Selected");

                // ✅ Step 4: Select Status → Active
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[@filter-name='status']//a[contains(text(),'Status')]"))).click();

                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@parameter='status' and contains(text(),'Active')]"))).click();

                System.out.println("✅ Status Selected");
                Common.waitForElement(2);

                // ✅ Step 5: Click Edit button for "Shop Line"
                WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[.//span[normalize-space()='Shop'] and .//span[normalize-space()='Zlaata India'] and .//span[normalize-space()='header']]//a[contains(@class,'btn-edit')]")));

                editBtn.click();
                System.out.println("✅ Clicked Edit for Shop Line");
                Common.waitForElement(2);

                // ✅ Step 6: Click Link Menu Tab
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.id("link_tab_nav"))).click();

                // ✅ Step 7: Wait for container
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("link-menu-container")));

                // ✅ Step 8: Click Select2 Category Field
                WebElement selectBox = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[contains(@class,'select2-selection--multiple')])[1]")));

                selectBox.click();

                // ✅ Step 9: Type Category Name
                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//textarea[contains(@class,'select2-search__field')]")));

                searchBox.sendKeys(category);
                Thread.sleep(1000); // small wait for dropdown

                // ✅ Step 10: Press Enter
                searchBox.sendKeys(Keys.ENTER);

                System.out.println("✅ Category Entered: " + category);
                
                Common.waitForElement(2);
              waitFor(saveButton);
              saveButton.click();
              System.out.println("✅ Excel uploaded successfully");
      		        
      
        //Clear Catch
  	    Common.waitForElement(3);
  	    waitFor(clearCatchButton);
  	    click(clearCatchButton);
  	    System.out.println("✅ Successfull click Clear Catch Button");
  		
  	

          System.out.println("🎉 All categories verification completed successfully!");

            } catch (Exception e) {
                System.out.println("❌ Failed for Category: " + category);
                e.printStackTrace();
            }
        }
    }
		
    public void verifyCatagoriesInUserApp(String filePath) throws IOException, InterruptedException {
    	  HomePage home = new HomePage(driver);
	        home.homeLaunch();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//h2[normalize-space()='ZLAATA INDIA']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
	        ));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

	        System.out.println("Clicked ZLAATA INDIA Home Page Banner");
        Common.waitForElement(3);
        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

        Actions actions = new Actions(driver);

        ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Categories in User App");
        ExtentManager.setTest(test);

        for (Map<String, Object> product : products) {
            String category = (String) product.get("Category Name");

            if (category == null || category.trim().isEmpty()) {
                System.out.println("⚠ Skipping empty category");
                continue;
            }

            // Hover on Shop
    	    WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	            By.xpath("//span[contains(@class,'header_nav_link') and normalize-space()='Shop']")
    	    ));
    	    actions.moveToElement(shopMenu).perform();

            // ✅ Get dropdown links
            List<WebElement> dropdownLinks = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.xpath("//h5[text()='CATEGORIES']/following-sibling::a")));

            boolean found = false;
            for (WebElement link : dropdownLinks) {
                if (link.getText().trim().equalsIgnoreCase(category.trim())) {
                    found = true;

                    // ✅ Verify link
                    Assert.assertTrue("❌ Category not visible: " + category, link.isDisplayed());
                    System.out.println("✅ Category visible in dropdown: " + category);
                    test.pass("Category visible: " + category);

                    // ✅ Click category
                    link.click();
                    System.out.println("✅ Navigated to Category: " + category);

                    // 🔄 WAIT + REFRESH here until products show
                    int timeoutMinutes = 5;
                    int refreshInterval = 5; // seconds
                    boolean productsFound = false;
                    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

                    while (System.currentTimeMillis() < endTime) {
                        try {
                            List<WebElement> productsInCollection = driver.findElements(By.xpath("//div[contains(@class,'prod_listing_card')]"));

                            if (!productsInCollection.isEmpty()) {
                                productsFound = true;
                                break;
                            }
                        } catch (Exception ignored) {}

                        driver.navigate().refresh();
                        Common.waitForElement(3);
                        Thread.sleep(refreshInterval * 1000);
                    }

                    // ✅ Final check
                    if (productsFound) {
                        System.out.println("✅ Products available under Category: " + category);
                        test.pass("Products found in Category: " + category);
                    } else {
                        System.err.println("❌ No products found in Category '" + category + "' within " + timeoutMinutes + " minutes.");
                        test.fail("No products found in Category: " + category);
                    }

                   break; // stop dropdown loop
                }
            }

            if (!found) {
                System.err.println("❌ Category not found in dropdown: " + category);
                test.fail("Category not found: " + category);
            }
        }

        ExtentManager.getExtentReports().flush();
    }
		
//Import Collection
 
	
	 public void bulkBploadCollectionExcel(String filePath) {
	        Common.waitForElement(2);
	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        System.out.println("✅ Redirected to Admin product collection page");
	        Common.waitForElement(2);
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
	        Common.waitForElement(2);
		    Common.waitForElement(5);
		    waitFor(clearCatchButton);
		    click(clearCatchButton);
		    System.out.println("✅ Successfull click Clear Catch Button");
		    
	        
	    }
	
	 public void verifyCollectionsInAdmin(String filePath) throws IOException {
		    Common.waitForElement(2);

		    // Read Excel → Filter only non-empty collection names
		    driver.navigate().refresh();
		    List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
		        .stream()
		        .filter(product -> {
		            Object collectionObj = product.get("Collections");  // <-- Excel column
		            return collectionObj != null && !collectionObj.toString().trim().isEmpty();
		        })
		        .collect(Collectors.toList());
		    
		    Common.waitForElement(2);
		    waitFor(clickStatus);
		    click(clickStatus);

		    // Select Status -> Active
		    waitFor(statusActiveOption);
		    click(statusActiveOption);
		    System.out.println("✅ Selected Active status");

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		    for (Map<String, Object> product : products) {
		    	String collectionNameInexcel = (String) product.get("Title");
		        String collectionName = (String) product.get("Collections");

		        // ✅ Navigate to Product Collection section
		        wait.until(ExpectedConditions.elementToBeClickable(collectionButton)).click();
		        System.out.println("✅ Clicked Collection button");

		        wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
		        searchTextBox.clear();
		        searchTextBox.sendKeys(collectionName);
		        Common.waitForElement(2);
		        searchTextBox.sendKeys(Keys.ENTER);

		        System.out.println("✅ Searched for Collection: " + collectionName);

		        // ✅ Wait until collection is visible in table
		        By collectionLocator = By.xpath("//span[@title='" + collectionNameInexcel + "']");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(collectionLocator));

		        System.out.println("✅ Collection is visible in Admin panel: " + collectionName);
		        
		     // Click Edit button
			    Common.waitForElement(2);
			    waitFor(editButton);
		        click(editButton);
		        System.out.println("✅ Clicked  editbutton");
		        Common.waitForElement(3);
			    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1800);");
			    Common.waitForElement(2);
			    waitFor(menuButton);
			    click(menuButton);
			    System.out.println("✅ Clicked Collection button");
			    Common.waitForElement(2);
			    waitFor(menuSearchBox);
			    type(menuSearchBox, "Shop");
			    menuSearchBox.sendKeys(Keys.ENTER);
			    System.out.println("✅ Successfull  set ShopMenu");
		        Common.waitForElement(2);
		        waitFor(saveButton);
		        saveButton.click();
			    System.out.println("✅ successful saved");
		        

		    }
		 
		    // ✅ Clear Cache
		    Common.waitForElement(2);
		    waitFor(clearCatchButton);
		    click(clearCatchButton);
		    System.out.println("✅ Successfully clicked Clear Cache Button");
		    Common.waitForElement(2);
		}

	 public void verifyCollectionInZlMenu(String filePath) throws IOException {

		   List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    Actions actions = new Actions(driver);

		    ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Collections in User App");
		    ExtentManager.setTest(test);

		    for (Map<String, Object> product : products) {
		        String collection = (String) product.get("Title");

		        if (collection == null || collection.trim().isEmpty()) {
		            System.out.println("⚠ Skipping empty collection");
		            continue;
		        }
	            System.out.println("➡ Processing Category: " + collection);
		    
	            try {

	                // ✅ Step 1: Open Zl Menu
	                Common.waitForElement(2);
	                click(searchProductSortMenu);
	                type(searchProductSortMenu, "Zl Menu");
	                click(clickZlMenu);
	                System.out.println("✅ Selected Zl Menu");
	                Common.waitForElement(2);

	                // ✅ Step 2: Click Header
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//a[contains(@href,'zl-menu?location=1')]"))).click();

	                // ✅ Step 3: Select Brand → Zlaata India
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//li[@filter-name='brand_type']//a[contains(text(),'Brand')]"))).click();

	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//a[@parameter='brand_type' and contains(text(),'Zlaata India')]"))).click();

	                System.out.println("✅ Brand Selected");

	                // ✅ Step 4: Select Status → Active
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//li[@filter-name='status']//a[contains(text(),'Status')]"))).click();

	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//a[@parameter='status' and contains(text(),'Active')]"))).click();

	                System.out.println("✅ Status Selected");
	                Common.waitForElement(2);

	                // ✅ Step 5: Click Edit button for "Shop Line"
	                WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//tr[.//span[normalize-space()='Shop'] and .//span[normalize-space()='Zlaata India'] and .//span[normalize-space()='header']]//a[contains(@class,'btn-edit')]")));

	                editBtn.click();
	                System.out.println("✅ Clicked Edit for Shop Line");
	                Common.waitForElement(2);

	                // ✅ Step 6: Click Link Menu Tab
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.id("link_tab_nav"))).click();

	                // ✅ Step 7: Wait for container
	                wait.until(ExpectedConditions.visibilityOfElementLocated(
	                        By.id("link-menu-container")));

	                // ✅ Step 8: Click Select2 Category Field
	                WebElement selectBox = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("(//span[contains(@class,'select2-selection--multiple')])[2]")));

	                selectBox.click();

	                // ✅ Step 9: Type Category Name
	                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//textarea[contains(@class,'select2-search__field')]")));

	                searchBox.sendKeys(collection);
	                Thread.sleep(1000); // small wait for dropdown

	                // ✅ Step 10: Press Enter
	                searchBox.sendKeys(Keys.ENTER);

	                System.out.println("✅ collection Entered: " + collection);
	                
	                Common.waitForElement(2);
	              waitFor(saveButton);
	              saveButton.click();
	              System.out.println("✅ Excel uploaded successfully");
	      		        
	      
	        //Clear Catch
	  	    Common.waitForElement(3);
	  	    waitFor(clearCatchButton);
	  	    click(clearCatchButton);
	  	    System.out.println("✅ Successfull click Clear Catch Button");
	  		
	  	

	          System.out.println("🎉 All categories verification completed successfully!");

	            } catch (Exception e) {
	                System.out.println("❌ Failed for collection: " + collection);
	                e.printStackTrace();
	            }
	            System.out.println("🎉 All collections verification completed successfully!");
		    }
	        }
	    



	 public void verifyCollectionsInUserApp(String filePath) throws IOException, InterruptedException {
		 HomePage home = new HomePage(driver);
	        home.homeLaunch();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//h2[normalize-space()='ZLAATA INDIA']/following-sibling::span[contains(@class,'landing_page_link_btn')]")
	        ));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);

	        System.out.println("Clicked ZLAATA INDIA Home Page Banner");
		    Common.waitForElement(3);

		    List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

		    Actions actions = new Actions(driver);

		    ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Collections in User App");
		    ExtentManager.setTest(test);

		    for (Map<String, Object> product : products) {
		        String collection = (String) product.get("Title");

		        if (collection == null || collection.trim().isEmpty()) {
		            System.out.println("⚠ Skipping empty collection");
		            continue;
		        }

		        // ✅ Hover Shop menu
		        WebElement shopMenu = wait.until(ExpectedConditions
		                .visibilityOfElementLocated(By.xpath("//span[contains(@class,'header_nav_link') and normalize-space()='Shop']")));
		        actions.moveToElement(shopMenu).perform();

		        // ✅ Wait for dropdown
		        List<WebElement> dropdownLinks = wait.until(ExpectedConditions
		                .visibilityOfAllElementsLocatedBy(By.xpath("//h5[text()='COLLECTIONS']/following-sibling::a")));

		        boolean found = false;
		        for (WebElement link : dropdownLinks) {
		            String linkText = link.getText().trim();
		            if (linkText.equalsIgnoreCase(collection.trim())) {
		                found = true;

		                // ✅ Verify visible
		                Assert.assertTrue("❌ Collection not visible: " + collection, link.isDisplayed());
		                System.out.println("✅ Collection visible in dropdown: " + collection);
		                test.pass("Collection visible: " + collection);

		                // ✅ Click
		                link.click();
		                System.out.println("✅ Navigated to collection: " + collection);
		                
		                // 🔄 WAIT + REFRESH here until products show
	                    int timeoutMinutes = 5;
	                    int refreshInterval = 5; // seconds
	                    boolean productsFound = false;
	                    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	                    while (System.currentTimeMillis() < endTime) {
	                        try {
	                            List<WebElement> productsInCollection = driver.findElements(By.xpath("//div[contains(@class,'prod_listing_card')]"));

	                            if (!productsInCollection.isEmpty()) {
	                                productsFound = true;
	                                break;
	                            }
	                        } catch (Exception ignored) {}

	                        driver.navigate().refresh();
	                        Common.waitForElement(3);
	                        Thread.sleep(refreshInterval * 1000);
	                    }

	                    //✅ Final check
	                    if (productsFound) {
	                        System.out.println("✅ Products available under collection: " + collection);
	                        test.pass("Products found in collection: " + collection);
	                    } else {
	                        System.err.println("❌ No products found in collection '" + collection + "' within " + timeoutMinutes + " minutes.");
	                        test.fail("No products found in collection: " + collection);
	                    }

	                    break; // stop dropdown loop
	                }
	            }

	            if (!found) {
	                System.err.println("❌ collection not found in dropdown: " + collection);
	                test.fail("collection not found: " + collection);
	            }
	        }

	        ExtentManager.getExtentReports().flush();
	    }
	 
	 
	 
	 //Import Product Style
	 
	 public void uploadTheProductStyleImport(String filePath) {
	    	Common.waitForElement(2);
	        driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	        
	        System.out.println("✅ Redirected to Admin product Style page");
	 
	        Common.waitForElement(2);
	        waitFor(importButton);
	        click(importButton);
	        System.out.println("✅ Clicked Import Button");
	        Common.waitForElement(2);
	        waitFor(uploadExcelButton);
	        uploadExcelButton.sendKeys(filePath);
	        System.out.println("✅ Uploaded file: " + filePath);
	        Common.waitForElement(3);
	        waitFor(submitButton);
	        submitButton.click();
	        System.out.println("✅ Excel uploaded successfully");
		    Common.waitForElement(8);
		    waitFor(clearCatchButton);
		    click(clearCatchButton);
		    System.out.println("✅ Successful click Clear Catch Button");
			
		}
		
	    public void verifyProductStyleInAdmin(String filePath) throws IOException {
	        Common.waitForElement(2);

	        // ✅ Read Excel → Filter only non-empty Categories
	        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
	            .stream()
	            .filter(product -> {
	                Object categoryObj = product.get("Styles");  // <-- Excel column name
	                return categoryObj != null && !categoryObj.toString().trim().isEmpty();
	            })
	            .collect(Collectors.toList());

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        for (Map<String, Object> product : products) {
	            String productStyleName = (String) product.get("Styles");

	            driver.navigate().refresh();
	            Common.waitForElement(3);
	            wait.until(ExpectedConditions.elementToBeClickable(styleName)).click();
	            System.out.println("✅ Clicked Style button");
	            wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
	            searchTextBox.clear();
	            searchTextBox.sendKeys(productStyleName);
	            searchTextBox.sendKeys(Keys.ENTER);
	            System.out.println("✅ Searched for product Style Name: " + productStyleName);

	            // ✅ Verify category visible in table
	            By productStyleNameLocator = By.xpath("//span[@title='" + productStyleName + "']");
	            wait.until(ExpectedConditions.visibilityOfElementLocated(productStyleNameLocator));
	            System.out.println("✅ Product Style Name is visible in Admin panel: " + productStyleName);
	            
	            // Click on Search Box for Product Sort
	    		Common.waitForElement(3);
	    		click(searchProductSortMenu);
	    		waitFor(searchProductSortMenu);
	    		type(searchProductSortMenu, "Product Sorts");
	    		System.out.println("Typed 'Product Sorts");
	    		Common.waitForElement(2);
	    		waitFor(clickProductSort);
	    		click(clickProductSort);
	    		System.out.println("Selected Product Sorts");
	    		Common.waitForElement(2);
	    		waitFor(addProductSort);
	    		click(addProductSort);
	    		System.out.println("Clicked add product Sort");
	    		Common.waitForElement(2);
	    		waitFor(categoryType);
	    		click(categoryType);
	    		System.out.println("Clicked Category Type");
	    		Common.waitForElement(2);
	    		waitFor(categorySearchTextBox);
	    		type(categorySearchTextBox,"Product Style");
	    		categorySearchTextBox.sendKeys(Keys.ENTER);
	    		System.out.println("Typed 'product Style Name ' & pressed Enter");
	    		Common.waitForElement(2);
	    		waitFor(categoryId);
	    		click(categoryId);
	    		System.out.println("Clicked Catagory Id Type");
	    		Common.waitForElement(2);
	    		waitFor(categorySearchTextBox);
	    		type(categorySearchTextBox,productStyleName);
	    		categorySearchTextBox.sendKeys(Keys.ENTER);
	    		System.out.println("Typed 'Category id' & pressed Enter");
	    		Common.waitForElement(2);
	            waitFor(saveButton);
	            saveButton.click();
	            System.out.println("✅ Excel uploaded successfully");
	    		
	        }
	        
	    
	      //Clear Catch
		    Common.waitForElement(2);
		    waitFor(clearCatchButton);
		    click(clearCatchButton);
		    System.out.println("✅ Successfull click Clear Catch Button");
			
		

	        System.out.println("🎉 All product StyleName verification completed successfully!");
	    }
	    public void verifyProductStyleInUserApp(String filePath) throws IOException, InterruptedException {
	    	HomePage home = new HomePage(driver);
			home.homeLaunch();
	        Common.waitForElement(3);
	        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        Actions actions = new Actions(driver);

	        ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Product Style in User App");
	        ExtentManager.setTest(test);

	        for (Map<String, Object> product : products) {
	            String productStyle = (String) product.get("Styles");

	            if (productStyle == null || productStyle.trim().isEmpty()) {
	                System.out.println("⚠ Skipping empty Styles");
	                continue;
	            }

	            // ✅ Hover Shop menu
	            WebElement shopMenu = wait.until(ExpectedConditions
	                    .visibilityOfElementLocated(By.xpath("//span[@class='navigation_menu_txt'][normalize-space()='Shop']")));
	            actions.moveToElement(shopMenu).perform();

	            // ✅ Get dropdown links
	            List<WebElement> dropdownLinks = wait.until(ExpectedConditions
	                    .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='nav_drop_down_box_category active']//ul/li/a")));

	            boolean found = false;
	            for (WebElement link : dropdownLinks) {
	                if (link.getText().trim().equalsIgnoreCase(productStyle.trim())) {
	                    found = true;

	                    // ✅ Verify link
	                    Assert.assertTrue("❌ Styles not visible: " + productStyle, link.isDisplayed());
	                    System.out.println("✅ Styles visible in dropdown: " + productStyle);
	                    test.pass("product Style visible: " + productStyle);

	                    // ✅ Click category
	                    link.click();
	                    System.out.println("✅ Navigated to product Style: " + productStyle);

	                    // 🔄 WAIT + REFRESH here until products show
	                    int timeoutMinutes = 5;
	                    int refreshInterval = 5; // seconds
	                    boolean productsFound = false;
	                    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	                    while (System.currentTimeMillis() < endTime) {
	                        try {
	                            List<WebElement> productsInCollection = driver.findElements(By.xpath("//h2[@class='product_list_cards_heading']"));

	                            if (!productsInCollection.isEmpty()) {
	                                productsFound = true;
	                                break;
	                            }
	                        } catch (Exception ignored) {}

	                        driver.navigate().refresh();
	                        Common.waitForElement(3);
	                        Thread.sleep(refreshInterval * 1000);
	                    }

	                    // ✅ Final check
	                    if (productsFound) {
	                        System.out.println("✅ Products available under productStyle: " + productStyle);
	                        test.pass("Products found in productStyle: " + productStyle);
	                    } else {
	                        System.err.println("❌ No products found in productStyle '" + productStyle + "' within " + timeoutMinutes + " minutes.");
	                        test.fail("No products found in productStyle: " + productStyle);
	                    }

	                    break; // stop dropdown loop
	                }
	            }

	            if (!found) {
	                System.err.println("❌ Category not found in dropdown: " + productStyle);
	                test.fail("Category not found: " + productStyle);
	            }
	        }

	        ExtentManager.getExtentReports().flush();
	    }
	 
	 
	
	
//Import All Product  
		public void ImportTheProductExcel(String excelPath) {
			Common.waitForElement(2);
		    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
		    System.out.println("✅ Successful redirect to Adimn Product page");
		    
		    Common.waitForElement(2);
		    waitFor(importButton);
	        click(importButton);
	        System.out.println("✅ Clicked  Importbutton");
		    Common.waitForElement(2);
		    waitFor(uploadExcelButton);
		    uploadExcelButton.sendKeys(excelPath);
		    System.out.println("✅ successful product added");
	        Common.waitForElement(2);
	        waitFor(submitButton);
	        submitButton.click();
		    System.out.println("✅ successful saved");
	        System.out.println("✅ Excel uploaded successfully");
	        Common.waitForElement(8);
	        
	    }
		
		public void verifyProductsInAdmin(String filePath) throws IOException {
			Common.waitForElement(2);
			driver.navigate().refresh();
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
	
	
//Import Search keyboard Product  
				public void ImportSearchKeyboardProductExcel(String excelPath) {
					Common.waitForElement(2);
				    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
				    System.out.println("✅ Successful redirect to Adimn Product page");
				    
				    Common.waitForElement(2);
				    waitFor(importButton);
			        click(importButton);
			        System.out.println("✅ Clicked  Importbutton");
				    Common.waitForElement(2);
				    waitFor(uploadExcelButton);
				    uploadExcelButton.sendKeys(excelPath);
				    System.out.println("✅ successful product added");
			        Common.waitForElement(2);
			        waitFor(submitButton);
			        submitButton.click();
				    System.out.println("✅ successful saved");
			        System.out.println("✅ Excel uploaded successfully");
			        Common.waitForElement(8);
			        
			    }
	
				String productName;
	public void verifySearchKeyboardProductsInAdmin(String filePath) throws IOException {
					Common.waitForElement(2);
					driver.navigate().refresh();
					List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
						    .stream()
						    .filter(product -> {
						        Object skuObj = product.get("sku");
						        return skuObj != null && !skuObj.toString().trim().isEmpty();
						    })
						    .collect(Collectors.toList());
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				    
				    for (Map<String, Object> product : products) {
				        String sku = (String) product.get("sku");

				        // Click collection menu
				        wait.until(ExpectedConditions.elementToBeClickable(clickSKU)).click();

				        // Search collection
				        wait.until(ExpectedConditions.elementToBeClickable(adminSearchBox));
				        adminSearchBox.clear();
				        adminSearchBox.sendKeys(sku);
				        adminSearchBox.sendKeys(Keys.ENTER);

				        System.out.println("✅ Searched for SKU: " + sku);
				       

				        // Wait for SKU to appear in table
				        WebElement skuElement=  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='" + sku + "']")));
				        System.out.println("✅ SKU is visible in Admin panel: " + sku);
				        
				     // Copy product name
				        productName = skuElement.findElement(
				        	    By.xpath("./ancestor::tr//td[2]/span[@title]") // find span with title in 2nd column of same row
				        	).getAttribute("title");
				        	System.out.println("✅ Product Name: " + productName);
				        	
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
	
	// Method to verify that the Search Keyboard Product 
			public void verifySearchKeyboardProductInUserApp(String filePath) throws InterruptedException, IOException {
				// ✅ Step 1: Open User App
				HomePage home = new HomePage(driver);
				home.homeLaunch();
			    Common.waitForElement(3);

			    // ✅ Step 2: Read expected product from Excel
			    List<Map<String, Object>> excelProducts = ExcelXLSReader.readProductsWithMultipleListing(filePath);
			    String expectedSearchKeyword = excelProducts.get(0).get("keywords").toString().trim();
			    System.out.println("🔍 Searching for product from Excel: " + expectedSearchKeyword);

			    // ✅ Step 3: Search with WebDriverWait
			    Common.waitForElement(3);
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			    wait.until(ExpectedConditions.elementToBeClickable(searchBox));
			    searchBox.clear();
			    searchBox.sendKeys(expectedSearchKeyword);

			    // ✅ Step 4: Wait for the search result using WebDriverWait
			    WebElement collectionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//div[normalize-space(text())='" + productName + "']")
			    ));

			    // ✅ Step 5: Verify and Click
			    if (collectionElement != null && collectionElement.isDisplayed()) {
			        System.out.println("✅ Product '" + productName + "' found in search results.");
			        collectionElement.click(); // 👉 Clicking matched product
			    } else {
			        throw new RuntimeException("❌ Product '" + productName + "' not found in search results.");
			    }
			}

			
//Import Search keyboard Collection  
			public void ImportSearchKeyboardCollectionExcel(String excelPath) {
				Common.waitForElement(2);
			    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
			    System.out.println("✅ Successful redirect to Adimn Product page");
			    
			    Common.waitForElement(2);
			    waitFor(importButton);
		        click(importButton);
		        System.out.println("✅ Clicked  Importbutton");
			    Common.waitForElement(2);
			    waitFor(uploadExcelButton);
			    uploadExcelButton.sendKeys(excelPath);
			    System.out.println("✅ successful product added");
		        Common.waitForElement(2);
		        waitFor(submitButton);
		        submitButton.click();
			    System.out.println("✅ successful saved");
		        System.out.println("✅ Excel uploaded successfully");
		        Common.waitForElement(8);
		        
		    }

		public void verifySearchKeyboardCollectionInAdmin(String filePath) throws IOException {
			    Common.waitForElement(2);
			    driver.navigate().refresh();
			    List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
			            .stream()
			            .filter(product -> {
			                Object collectionObj = product.get("Name");
			                return collectionObj != null && !collectionObj.toString().trim().isEmpty();
			            })
			            .collect(Collectors.toList());

			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			    for (Map<String, Object> product : products) {
			        String collectionName = (String) product.get("Name");

			        // Click collection menu
			        wait.until(ExpectedConditions.elementToBeClickable(clickedCollection)).click();

			        // Search collection
			        wait.until(ExpectedConditions.elementToBeClickable(collectionSearchBox));
			        collectionSearchBox.clear();
			        collectionSearchBox.sendKeys(collectionName);
			        collectionSearchBox.sendKeys(Keys.ENTER);

			        System.out.println("✅ Searched for Collection: " + collectionName);

			        // Verify collection is visible in table
			        wait.until(ExpectedConditions.visibilityOfElementLocated(
			                By.xpath("//span[@title='" + collectionName + "']")
			        ));
			        System.out.println("✅ Collection is visible in Admin panel: " + collectionName);

			        // Optional: click outside
			        wait.until(ExpectedConditions.elementToBeClickable(clickBlankSpace)).click();
			    }

			    // Clear cache
			    Common.waitForElement(2);
			    waitFor(clearCatchButton);
			    click(clearCatchButton);
			    System.out.println("✅ Successfully clicked Clear Cache Button");
			    Common.waitForElement(2);
			}

// Method to verify that the Search Keyboard Product 
			public void verifySearchKeyboardCollectionInUserApp(String filePath) throws InterruptedException, IOException {
			    // ✅ Step 1: Open User App
				HomePage home = new HomePage(driver);
				home.homeLaunch();
			    Common.waitForElement(3);

			    // ✅ Step 2: Read expected product from Excel
			    List<Map<String, Object>> excelProducts = ExcelXLSReader.readProductsWithMultipleListing(filePath);
			    String expectedSearchKeyword = excelProducts.get(0).get("Keywords").toString().trim();
			    String expectedCollectionName = excelProducts.get(0).get("Name").toString().trim();

			    System.out.println("🔍 Searching for product from Excel: " + expectedSearchKeyword);

			    // ✅ Step 3: Search with WebDriverWait
			    Common.waitForElement(3);
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			    wait.until(ExpectedConditions.elementToBeClickable(searchBox));
			    searchBox.clear();
			    searchBox.sendKeys(expectedSearchKeyword);

			    // ✅ Step 4: Wait for the search result using WebDriverWait
			    WebElement collectionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//div[normalize-space(text())='" + expectedCollectionName + "']")
			    ));

			    // ✅ Step 5: Verify and Click
			    if (collectionElement != null && collectionElement.isDisplayed()) {
			        System.out.println("✅ Collection '" + expectedCollectionName + "' found in search results.");
			        collectionElement.click(); // 👉 Clicking matched product
			    } else {
			        throw new RuntimeException("❌ Collection '" + expectedCollectionName + "' not found in search results.");
			    }
			}
			

//Import Search keyboard Style  
			public void ImportSearchKeyboardStyleExcel(String excelPath) {
				Common.waitForElement(2);
			    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
			    System.out.println("✅ Successful redirect to Adimn Search Keyword Style page");
			    
			    Common.waitForElement(2);
			    waitFor(importButton);
		        click(importButton);
		        System.out.println("✅ Clicked  Importbutton");
			    Common.waitForElement(2);
			    waitFor(uploadExcelButton);
			    uploadExcelButton.sendKeys(excelPath);
			    System.out.println("✅ successful product added");
		        Common.waitForElement(2);
		        waitFor(submitButton);
		        submitButton.click();
			    System.out.println("✅ successful saved");
		        System.out.println("✅ Excel uploaded successfully");
		        Common.waitForElement(8);
		        
		    }

			public void verifySearchKeyboardStyleInAdmin(String filePath) throws IOException {
			    Common.waitForElement(2);
			    driver.navigate().refresh();
			    List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath)
			            .stream()
			            .filter(product -> {
			                Object styleObj = product.get("Name");
			                return styleObj != null && !styleObj.toString().trim().isEmpty();
			            })
			            .collect(Collectors.toList());

			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			    for (Map<String, Object> product : products) {
			        String styleName = (String) product.get("Name");

			        // Click Style menu
			        wait.until(ExpectedConditions.elementToBeClickable(clickedStyle)).click();

			        // Search collection
			        wait.until(ExpectedConditions.elementToBeClickable(collectionSearchBox));
			        collectionSearchBox.clear();
			        collectionSearchBox.sendKeys(styleName);
			        collectionSearchBox.sendKeys(Keys.ENTER);

			        System.out.println("✅ Searched for Style: " + styleName);

			        // Verify collection is visible in table
			        wait.until(ExpectedConditions.visibilityOfElementLocated(
			                By.xpath("//span[@title='" + styleName + "']")
			        ));
			        System.out.println("✅ Style is visible in Admin panel: " + styleName);

			        // Optional: click outside
			        wait.until(ExpectedConditions.elementToBeClickable(clickBlankSpace)).click();
			    }

			    // Clear cache
			    Common.waitForElement(2);
			    waitFor(clearCatchButton);
			    click(clearCatchButton);
			    System.out.println("✅ Successfully clicked Clear Cache Button");
			    Common.waitForElement(2);
			}

// Method to verify that the Search Keyboard Product 
			public void verifySearchKeyboardStyleInUserApp(String filePath) throws InterruptedException, IOException {
			    // ✅ Step 1: Open User App
				HomePage home = new HomePage(driver);
				home.homeLaunch();
			    Common.waitForElement(3);

			    // ✅ Step 2: Read expected product from Excel
			    List<Map<String, Object>> excelProducts = ExcelXLSReader.readProductsWithMultipleListing(filePath);
			    String expectedSearchKeyword = excelProducts.get(0).get("Keywords").toString().trim();
			    String expectedCollectionName = excelProducts.get(0).get("Name").toString().trim();

			    System.out.println("🔍 Searching for product from Excel: " + expectedSearchKeyword);

			    // ✅ Step 3: Search with WebDriverWait
			    Common.waitForElement(3);
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			    wait.until(ExpectedConditions.elementToBeClickable(searchBox));
			    searchBox.clear();
			    searchBox.sendKeys(expectedSearchKeyword);

			    // ✅ Step 4: Wait for the search result using WebDriverWait
			    WebElement collectionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
			            By.xpath("//div[normalize-space(text())='" + expectedCollectionName + "']")
			    ));

			    // ✅ Step 5: Verify and Click
			    if (collectionElement != null && collectionElement.isDisplayed()) {
			        System.out.println("✅ Style '" + expectedCollectionName + "' found in search results.");
			        collectionElement.click(); // 👉 Clicking matched product
			    } else {
			        throw new RuntimeException("❌ Style '" + expectedCollectionName + "' not found in search results.");
			    }
			}
	
	
			
///track inventories for one sku two size
///
			public void VerifyImporttrackinventories(String filePath) throws IOException {

			    // -------------------- INITIAL SETUP --------------------
			    Common.waitForElement(2);
			    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
			    System.out.println("Redirected to Track Inventory page");

			    // -------------------- READ DATA FROM EXCEL --------------------
			    List<Map<String, Object>> excelProducts =
			            ExcelXLSReader.readProductsWithMultipleListing(filePath);

			    String expectedSku = excelProducts.get(0).get("SKU").toString().trim();

			    System.out.println("\n==============================");
			    System.out.println("SKU: " + expectedSku);

			    Map<String, Map<String, String>> expectedSizeData = new HashMap<>();

			    for (Map<String, Object> row : excelProducts) {

			        String topSize = row.get("Top Size").toString().trim();
			        String topQty  = row.get("Top Quantity").toString().trim();

			        String bottomSize = row.get("Bottom Size").toString().trim();
			        String bottomQty  = row.get("Bottom Quantity").toString().trim();

			        expectedSizeData
			                .computeIfAbsent("TOP", k -> new HashMap<>())
			                .put(topSize, topQty);

			        expectedSizeData
			                .computeIfAbsent("BOTTOM", k -> new HashMap<>())
			                .put(bottomSize, bottomQty);

			        System.out.println("TOP    → " + topSize + " = " + topQty);
			        System.out.println("BOTTOM → " + bottomSize + " = " + bottomQty);
			    }
			    System.out.println("==============================");

			    // -------------------- SEARCH SKU --------------------
			    trackinventoriesSearchbardropdown.click();
			    Common.waitForElement(2);
			    trackinventoriesSearchbar.sendKeys(expectedSku);
			    Common.waitForElement(2);
			    trackinventoriesSearchbar.sendKeys(Keys.ENTER);
			    Common.waitForElement(3);

			    // ====================================================
			    // UI INVENTORY DATA BEFORE IMPORT
			    // ====================================================
			    System.out.println("\n==============================");
			    System.out.println("UI INVENTORY DATA (BEFORE IMPORT)");
			    System.out.println("==============================");

			    List<WebElement> beforeImportBlocks = driver.findElements(
			            By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
			    );

			    for (WebElement block : beforeImportBlocks) {

			        String type = block.findElement(
			                By.xpath(".//span[contains(@class,'increment-btn')]")
			        ).getAttribute("data-type").trim().toUpperCase();

			        String size = block.findElement(
			                By.xpath(".//p[contains(@class,'size-field')]")
			        ).getText().trim();

			        String qty = block.findElement(
			                By.xpath(".//input[@type='number']")
			        ).getAttribute("value").trim();

			        System.out.println("UI DATA → " + type + " | Size: " + size + " | Qty: " + qty);
			    }
			    

			 // ================= UPDATE EXCEL QUANTITY =================
			    ExcelXLSReader.updateRandomQuantityOnly(filePath);

			    // ================= READ EXCEL AFTER UPDATE =================
			    excelProducts = ExcelXLSReader.readProductsWithMultipleListing(filePath);
			    expectedSizeData.clear();

			    System.out.println("\n==============================");
			    System.out.println("EXCEL DATA (AFTER RUNTIME UPDATE)");
			    System.out.println("==============================");

			    for (Map<String, Object> row : excelProducts) {

			        String topSize = row.get("Top Size").toString().trim();
			        String topQty  = row.get("Top Quantity").toString().trim();

			        String bottomSize = row.get("Bottom Size").toString().trim();
			        String bottomQty  = row.get("Bottom Quantity").toString().trim();

			        expectedSizeData
			                .computeIfAbsent("TOP", k -> new HashMap<>())
			                .put(topSize, topQty);

			        expectedSizeData
			                .computeIfAbsent("BOTTOM", k -> new HashMap<>())
			                .put(bottomSize, bottomQty);

			        // 🔹 PRINT UPDATED EXCEL DATA
			        System.out.println("TOP    → " + topSize + " = " + topQty);
			        System.out.println("BOTTOM → " + bottomSize + " = " + bottomQty);
			    }

			    System.out.println("==============================");

			    

			    // -------------------- IMPORT EXCEL --------------------
			    click(importButton);
			    System.out.println("Clicked Import button");

			    Common.waitForElement(2);
			    uploadExcelButtonTrack.sendKeys(filePath);
			    System.out.println("Excel file uploaded");

			    Common.waitForElement(2);
			    submitButton.click();
			    System.out.println("Import submitted");

			    Common.waitForElement(8);
			    driver.navigate().refresh();
			    Common.waitForElement(3);

			    // ====================================================
			    // UI INVENTORY DATA AFTER IMPORT
			    // ====================================================
			    Map<String, Map<String, String>> actualSizeData = new HashMap<>();

			    System.out.println("\n==============================");
			    System.out.println("UI INVENTORY DATA (AFTER IMPORT)");
			    System.out.println("==============================");

			    List<WebElement> sizeBlocks = driver.findElements(
			            By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
			    );

			    for (WebElement block : sizeBlocks) {

			        String type = block.findElement(
			                By.xpath(".//span[contains(@class,'increment-btn')]")
			        ).getAttribute("data-type").trim().toUpperCase();

			        String size = block.findElement(
			                By.xpath(".//p[contains(@class,'size-field')]")
			        ).getText().trim();

			        String qty = block.findElement(
			                By.xpath(".//input[@type='number']")
			        ).getAttribute("value").trim();

			        actualSizeData
			                .computeIfAbsent(type, k -> new HashMap<>())
			                .put(size, qty);

			        System.out.println("UI DATA → " + type + " | Size: " + size + " | Qty: " + qty);
			    }
			    System.out.println("==============================");

			    // -------------------- VERIFY EXCEL vs UI --------------------
			    List<String> failures = new ArrayList<>();

			    for (String type : expectedSizeData.keySet()) {

			        Map<String, String> expectedMap = expectedSizeData.get(type);
			        Map<String, String> actualMap = actualSizeData.get(type);

			        for (String size : expectedMap.keySet()) {

			            if (actualMap == null || !actualMap.containsKey(size)) {
			                failures.add(type + " size not found in UI → " + size);
			                continue;
			            }

			            String expectedQty = expectedMap.get(size);
			            String actualQty = actualMap.get(size);

			            if (!expectedQty.equals(actualQty)) {
			                failures.add(type + " qty mismatch → Size: " + size
			                        + " | Expected: " + expectedQty
			                        + " | Actual: " + actualQty);
			            } else {
			                System.out.println("VERIFIED → "
			                        + type + " | Size: " + size + " | Qty: " + actualQty);
			            }
			        }
			    }

			    // -------------------- FINAL RESULT --------------------
			    if (!failures.isEmpty()) {
			        System.err.println("\n========== INVENTORY VERIFICATION FAILED ==========");
			        failures.forEach(System.err::println);
			        Assert.fail("Inventory mismatch found after Excel import");
			    } else {
			        System.out.println("\n========== INVENTORY VERIFICATION PASSED ==========");
			    }
			}


//track inventories for two sku one size
			public void VerifyImportTrackInventoryMultipleStages(String filePath) throws IOException {

			    Common.waitForElement(2);
			    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
			    System.out.println("Redirected to Track Inventory page");

			    // ---------------- READ EXCEL (INITIAL) ----------------
			    List<Map<String, Object>> excelProducts =
			            ExcelXLSReader.readProductsWithMultipleListing(filePath);

			    // Group rows by SKU
			    Map<String, List<Map<String, Object>>> productsBySku = new LinkedHashMap<>();
			    for (Map<String, Object> row : excelProducts) {
			        String sku = row.get("SKU").toString().trim();
			        productsBySku.computeIfAbsent(sku, k -> new ArrayList<>()).add(row);
			    }

			    // =====================================================
			    // STAGE 1: EXPECTED + UI BEFORE IMPORT (PRINT ONCE)
			    // =====================================================
			    for (String sku : productsBySku.keySet()) {

			        System.out.println("\n================================================");
			        System.out.println("PROCESSING SKU → " + sku);
			        System.out.println("================================================");

			        List<Map<String, Object>> skuRows = productsBySku.get(sku);

			        // Print EXPECTED from Excel
			        for (Map<String, Object> row : skuRows) {

			            String topSize = row.get("Top Size") != null ? row.get("Top Size").toString().trim() : "";
			            String topQty  = row.get("Top Quantity") != null ? row.get("Top Quantity").toString().trim() : "";

			            String bottomSize = row.get("Bottom Size") != null ? row.get("Bottom Size").toString().trim() : "";
			            String bottomQty  = row.get("Bottom Quantity") != null ? row.get("Bottom Quantity").toString().trim() : "";

			            if (!topSize.isEmpty() && !topQty.isEmpty()) {
			                System.out.println("EXPECTED → TOP | Size: " + topSize + " | Qty: " + topQty);
			            }

			            if (!topSize.equalsIgnoreCase("Regular")
			                    && !bottomSize.isEmpty()
			                    && !bottomQty.isEmpty()) {

			                System.out.println("EXPECTED → BOTTOM | Size: " + bottomSize + " | Qty: " + bottomQty);
			            }
			        }

			        // Search SKU in UI
			        trackinventoriesSearchbardropdown.click();
			        Common.waitForElement(2);
			        trackinventoriesSearchbar.clear();
			        trackinventoriesSearchbar.sendKeys(sku);
			        Common.waitForElement(2);
			        trackinventoriesSearchbar.sendKeys(Keys.ENTER);
			        Common.waitForElement(3);

			        // Print UI BEFORE IMPORT
			        System.out.println("\n--- UI INVENTORY BEFORE IMPORT ---");
			        List<WebElement> blocks = driver.findElements(
			                By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
			        );

			        for (WebElement block : blocks) {
			            String type = block.findElement(
			                    By.xpath(".//span[contains(@class,'increment-btn')]")
			            ).getAttribute("data-type").trim().toUpperCase();

			            String size = block.findElement(
			                    By.xpath(".//p[contains(@class,'size-field')]")
			            ).getText().trim();

			            String qty = block.findElement(
			                    By.xpath(".//input[@type='number']")
			            ).getAttribute("value").trim();

			            System.out.println("UI BEFORE IMPORT → " + type + " | Size: " + size + " | Qty: " + qty);
			        }

			        click(removeButton);
			    }

			    // =====================================================
			    // STAGE 2: UPDATE EXCEL (RUNTIME)
			    // =====================================================
			    Map<String, Map<String, String>> updatedExcelData =
			            ExcelXLSReader.updateQuantityInExcel(filePath);

			    System.out.println("\n==============================");
			    System.out.println("EXCEL DATA (AFTER UPDATE)");
			    System.out.println("==============================");

			    updatedExcelData.forEach((key, sizeMap) ->
			            sizeMap.forEach((size, qty) ->
			                    System.out.println(key + " → " + size + " = " + qty)
			            )
			    );

			    System.out.println("==============================");

			    // =====================================================
			    // STAGE 3: READ UPDATED EXCEL (EXPECTED MAP)
			    // =====================================================
			    List<Map<String, Object>> updatedExcelProducts =
			            ExcelXLSReader.readProductsWithMultipleListing(filePath);

			    Map<String, Map<String, Map<String, String>>> expectedDataMap = new LinkedHashMap<>();

			    for (Map<String, Object> row : updatedExcelProducts) {

			        String sku = row.get("SKU").toString().trim();

			        String topSize = row.get("Top Size") != null ? row.get("Top Size").toString().trim() : "";
			        String topQty  = row.get("Top Quantity") != null ? row.get("Top Quantity").toString().trim() : "";

			        String bottomSize = row.get("Bottom Size") != null ? row.get("Bottom Size").toString().trim() : "";
			        String bottomQty  = row.get("Bottom Quantity") != null ? row.get("Bottom Quantity").toString().trim() : "";

			        Map<String, Map<String, String>> skuExpected =
			                expectedDataMap.computeIfAbsent(sku, k -> new HashMap<>());

			        if (!topSize.isEmpty() && !topQty.isEmpty()) {
			            skuExpected.computeIfAbsent("TOP", k -> new HashMap<>())
			                    .put(topSize, topQty);
			        }

			        if (!topSize.equalsIgnoreCase("Regular")
			                && !bottomSize.isEmpty()
			                && !bottomQty.isEmpty()) {

			            skuExpected.computeIfAbsent("BOTTOM", k -> new HashMap<>())
			                    .put(bottomSize, bottomQty);
			        }
			    }

			    // =====================================================
			    // STAGE 4: IMPORT UPDATED EXCEL
			    // =====================================================
			    click(importButton);
			    Common.waitForElement(2);
			    uploadExcelButtonTrack.sendKeys(filePath);
			    Common.waitForElement(2);
			    submitButton.click();
			    System.out.println("Import submitted");

			    Common.waitForElement(8);
			    driver.navigate().refresh();
			    Common.waitForElement(3);

			    // =====================================================
			    // STAGE 5: VERIFY UI AFTER IMPORT
			    // =====================================================
			    for (String sku : expectedDataMap.keySet()) {

			        System.out.println("\n===============================");
			        System.out.println("VERIFY SKU AFTER IMPORT → " + sku);
			        System.out.println("===============================");

			        trackinventoriesSearchbardropdown.click();
			        Common.waitForElement(2);
			        trackinventoriesSearchbar.clear();
			        trackinventoriesSearchbar.sendKeys(sku);
			        Common.waitForElement(2);
			        trackinventoriesSearchbar.sendKeys(Keys.ENTER);
			        Common.waitForElement(3);

			        List<WebElement> blocks = driver.findElements(
			                By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
			        );

			        Map<String, Map<String, String>> actualSizeData = new HashMap<>();

			        for (WebElement block : blocks) {

			            String type = block.findElement(
			                    By.xpath(".//span[contains(@class,'increment-btn')]")
			            ).getAttribute("data-type").trim().toUpperCase();

			            String size = block.findElement(
			                    By.xpath(".//p[contains(@class,'size-field')]")
			            ).getText().trim();

			            String qty = block.findElement(
			                    By.xpath(".//input[@type='number']")
			            ).getAttribute("value").trim();

			            actualSizeData.computeIfAbsent(type, k -> new HashMap<>())
			                    .put(size, qty);

			            System.out.println("UI AFTER → " + type + " | Size: " + size + " | Qty: " + qty);
			        }

			        click(removeButton);

			        // ---------------- ASSERT ----------------
			        Map<String, Map<String, String>> expectedSizeData = expectedDataMap.get(sku);
			        List<String> failures = new ArrayList<>();

			        for (String type : expectedSizeData.keySet()) {

			            Map<String, String> expSizes = expectedSizeData.get(type);
			            Map<String, String> actSizes = actualSizeData.get(type);

			            if (actSizes == null) {
			                failures.add(sku + " | Missing section → " + type);
			                continue;
			            }

			            for (String size : expSizes.keySet()) {
			                String expQty = expSizes.get(size);
			                String actQty = actSizes.get(size);

			                if (!expQty.equals(actQty)) {
			                    failures.add(sku + " | " + type +
			                            " | Size: " + size +
			                            " | Expected: " + expQty +
			                            " | Actual: " + actQty);
			                } else {
			                    System.out.println("VERIFIED ✔ " + sku +
			                            " | " + type +
			                            " | Size: " + size +
			                            " | Qty: " + actQty);
			                }
			            }
			        }

			        if (!failures.isEmpty()) {
			            failures.forEach(System.err::println);
			            Assert.fail("Inventory mismatch for SKU → " + sku);
			        } else {
			            System.out.println(":white_check_mark: INVENTORY VERIFIED FOR SKU → " + sku);
			        }
			    }
			}

	// track inventories after add new size reflecting in application
			public void VerifyImportTrackInventoryaddNewsizereflectinginapplication(String filePath) throws IOException {

			    // -------------------- OPEN TRACK INVENTORY PAGE --------------------
			    Common.waitForElement(2);
			    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
			    System.out.println("Redirected to Track Inventory page");

			    // -------------------- READ SKU FROM EXCEL --------------------
			    List<Map<String, Object>> excelProducts =
			            ExcelXLSReader.readProductsWithMultipleListing(filePath);

			    String expectedSku = excelProducts.get(0).get("SKU").toString().trim();

			    System.out.println("\n==============================");
			    System.out.println("SKU FROM EXCEL → " + expectedSku);
			    System.out.println("==============================");

			    // -------------------- OPEN APPLICATION (FRONTEND) --------------------
			    HomePage home = new HomePage(driver);
			    home.homeLaunch();
			    Common.waitForElement(5); // wait for banner / video

			    // -------------------- CLICK SEARCH BAR (DIV) --------------------
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			    WebElement searchBarContainer = wait.until(
			            ExpectedConditions.elementToBeClickable(
			                    By.xpath("//div[@class='navigation_search_input_box']")
			            )
			    );
			    searchBarContainer.click();
			    System.out.println("Search bar container clicked");

			    // -------------------- ENTER SKU IN INPUT --------------------
			    WebElement searchInput = wait.until(
			            ExpectedConditions.visibilityOfElementLocated(
			                    By.xpath("//div[@class='navigation_search_input_box']//input")
			            )
			    );

			    searchInput.sendKeys(expectedSku);
			    System.out.println("Entered SKU → " + expectedSku);

			    searchInput.sendKeys(Keys.ENTER);
			    System.out.println("Pressed ENTER");

			    Common.waitForElement(4);

			    // -------------------- RESULT CONFIRMATION --------------------
			    System.out.println("Search results loaded successfully for SKU → " + expectedSku);
			    WebElement productImage = wait.until(
		                ExpectedConditions.elementToBeClickable(
		                        By.xpath("(//div[contains(@class,'product_list_cards_img_box')]//a)[1]")
		                )
		        );

		        productImage.click();
		        System.out.println("Clicked on product listing image");

		        // Optional: verify PDP loaded
		        wait.until(ExpectedConditions.urlContains("product-detail"));
		        System.out.println("Navigated to Product Detail Page");
			
		            // ---------- TOP Sizes ----------
		            List<WebElement> topSizes = driver.findElements(By.cssSelector(".Cls_prod_size_name"));
		            if (!topSizes.isEmpty()) {
		                System.out.println("TOP Sizes:");
		                for (WebElement size : topSizes) {
		                    System.out.println(size.getText());
		                }
		            }

		            // ---------- BOTTOM Sizes ----------
		            List<WebElement> bottomSizes = driver.findElements(By.cssSelector(".Cls_prod_size_name_bottom"));
		            if (!bottomSizes.isEmpty()) {
		                System.out.println("\nBOTTOM Sizes:");
		                for (WebElement size : bottomSizes) {
		                    System.out.println(size.getText());
		                }
		            }

		            if (topSizes.isEmpty() && bottomSizes.isEmpty()) {
		                System.out.println("No sizes available for this product.");
		            }
		        

		           driver.get(Common.getValueFromTestDataMap("ExcelPath"));	
				    System.out.println("Redirected to Track Inventory page");
				    
				    Map<String, Map<String, String>> expectedSizeData = new HashMap<>();

				    for (Map<String, Object> row : excelProducts) {

				        String topSize = row.get("Top Size").toString().trim();
				        String topQty  = row.get("Top Quantity").toString().trim();

				        String bottomSize = row.get("Bottom Size").toString().trim();
				        String bottomQty  = row.get("Bottom Quantity").toString().trim();

				        expectedSizeData
				                .computeIfAbsent("TOP", k -> new HashMap<>())
				                .put(topSize, topQty);

				        expectedSizeData
				                .computeIfAbsent("BOTTOM", k -> new HashMap<>())
				                .put(bottomSize, bottomQty);

				        System.out.println("TOP    → " + topSize + " = " + topQty);
				        System.out.println("BOTTOM → " + bottomSize + " = " + bottomQty);
				    }
				    System.out.println("==============================");

				    // -------------------- SEARCH SKU --------------------
				    trackinventoriesSearchbardropdown.click();
				    Common.waitForElement(2);
				    trackinventoriesSearchbar.sendKeys(expectedSku);
				    Common.waitForElement(2);
				    trackinventoriesSearchbar.sendKeys(Keys.ENTER);
				    Common.waitForElement(3);

				    // ====================================================
				    // UI INVENTORY DATA BEFORE IMPORT
				    // ====================================================
				    System.out.println("\n==============================");
				    System.out.println("UI INVENTORY DATA (BEFORE IMPORT)");
				    System.out.println("==============================");

				    List<WebElement> beforeImportBlocks = driver.findElements(
				            By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
				    );

				    for (WebElement block : beforeImportBlocks) {

				        String type = block.findElement(
				                By.xpath(".//span[contains(@class,'increment-btn')]")
				        ).getAttribute("data-type").trim().toUpperCase();

				        String size = block.findElement(
				                By.xpath(".//p[contains(@class,'size-field')]")
				        ).getText().trim();

				        String qty = block.findElement(
				                By.xpath(".//input[@type='number']")
				        ).getAttribute("value").trim();

				        System.out.println("UI DATA → " + type + " | Size: " + size + " | Qty: " + qty);
				    }

				    // -------------------- IMPORT EXCEL --------------------
				    click(importButton);
				    System.out.println("Clicked Import button");

				    Common.waitForElement(2);
				    uploadExcelButtonTrack.sendKeys(filePath);
				    System.out.println("Excel file uploaded");

				    Common.waitForElement(2);
				    submitButton.click();
				    System.out.println("Import submitted");

				    Common.waitForElement(8);
				    driver.navigate().refresh();
				    Common.waitForElement(3);

				    // ====================================================
				    // UI INVENTORY DATA AFTER IMPORT
				    // ====================================================
				    Map<String, Map<String, String>> actualSizeData = new HashMap<>();

				    System.out.println("\n==============================");
				    System.out.println("UI INVENTORY DATA (AFTER IMPORT)");
				    System.out.println("==============================");

				    List<WebElement> sizeBlocks = driver.findElements(
				            By.xpath("//span[contains(@class,'increment-btn')]/ancestor::div[@class='text-center']")
				    );

				    for (WebElement block : sizeBlocks) {

				        String type = block.findElement(
				                By.xpath(".//span[contains(@class,'increment-btn')]")
				        ).getAttribute("data-type").trim().toUpperCase();

				        String size = block.findElement(
				                By.xpath(".//p[contains(@class,'size-field')]")
				        ).getText().trim();

				        String qty = block.findElement(
				                By.xpath(".//input[@type='number']")
				        ).getAttribute("value").trim();

				        actualSizeData
				                .computeIfAbsent(type, k -> new HashMap<>())
				                .put(size, qty);

				        System.out.println("UI DATA → " + type + " | Size: " + size + " | Qty: " + qty);
				    }
				    System.out.println("==============================");
				    
				    home.homeLaunch();
				    Common.waitForElement(5); // wait for banner / video

				    // -------------------- CLICK SEARCH BAR (DIV) --------------------

				    WebElement searchBarContainer1 = wait.until(
				            ExpectedConditions.elementToBeClickable(
				                    By.xpath("//div[@class='navigation_search_input_box']")
				            )
				    );
				    searchBarContainer1.click();
				    System.out.println("Search bar container clicked");

				    // -------------------- ENTER SKU IN INPUT --------------------
				    WebElement searchInput1 = wait.until(
				            ExpectedConditions.visibilityOfElementLocated(
				                    By.xpath("//div[@class='navigation_search_input_box']//input")
				            )
				    );

				    searchInput1.sendKeys(expectedSku);
				    System.out.println("Entered SKU → " + expectedSku);

				    searchInput1.sendKeys(Keys.ENTER);
				    System.out.println("Pressed ENTER");

				    Common.waitForElement(4);

				    // -------------------- RESULT CONFIRMATION --------------------
				    System.out.println("Search results loaded successfully for SKU → " + expectedSku);
				    WebElement productImage1 = wait.until(
			                ExpectedConditions.elementToBeClickable(
			                        By.xpath("(//div[contains(@class,'product_list_cards_img_box')]//a)[1]")
			                )
			        );

			        productImage1.click();
			        System.out.println("Clicked on product listing image");

			        // Optional: verify PDP loaded
			        wait.until(ExpectedConditions.urlContains("product-detail"));
			        System.out.println("Navigated to Product Detail Page");
				
			            // ---------- TOP Sizes ----------
			            List<WebElement> topSizes1 = driver.findElements(By.cssSelector(".Cls_prod_size_name"));
			            if (!topSizes1.isEmpty()) {
			                System.out.println("TOP Sizes:");
			                for (WebElement size : topSizes1) {
			                    System.out.println(size.getText());
			                }
			            }

			            // ---------- BOTTOM Sizes ----------
			            List<WebElement> bottomSizes1 = driver.findElements(By.cssSelector(".Cls_prod_size_name_bottom"));
			            if (!bottomSizes1.isEmpty()) {
			                System.out.println("\nBOTTOM Sizes:");
			                for (WebElement size : bottomSizes1) {
			                    System.out.println(size.getText());
			                }
			            }

			            if (topSizes1.isEmpty() && bottomSizes1.isEmpty()) {
			                System.out.println("No sizes available for this product.");
			            }
			        
				    
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
