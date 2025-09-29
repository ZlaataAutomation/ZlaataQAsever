package pages;

import java.io.IOException;
import java.time.Duration;
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
	    Common.waitForElement(3);
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
            searchTextBox.sendKeys(Keys.ENTER);
            System.out.println("✅ Searched for Category: " + categoryName);

            // ✅ Verify category visible in table
            By categoryLocator = By.xpath("//span[@title='" + categoryName + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoryLocator));
            System.out.println("✅ Category is visible in Admin panel: " + categoryName);
            
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
    		type(categorySearchTextBox,"Category");
    		categorySearchTextBox.sendKeys(Keys.ENTER);
    		System.out.println("Typed 'Category Name' & pressed Enter");
    		Common.waitForElement(2);
    		waitFor(categoryId);
    		click(categoryId);
    		System.out.println("Clicked Catagory Id Type");
    		Common.waitForElement(2);
    		waitFor(categorySearchTextBox);
    		type(categorySearchTextBox,categoryName);
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
		
	

        System.out.println("🎉 All categories verification completed successfully!");
    }
		
    public void verifyCatagoriesInUserApp(String filePath) throws IOException, InterruptedException {
        switchToWindow(1);
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Common.waitForElement(3);
        List<Map<String, Object>> products = ExcelXLSReader.readProductsWithMultipleListing(filePath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        ExtentTest test = ExtentManager.getExtentReports().createTest("Verify Categories in User App");
        ExtentManager.setTest(test);

        for (Map<String, Object> product : products) {
            String category = (String) product.get("Category Name");

            if (category == null || category.trim().isEmpty()) {
                System.out.println("⚠ Skipping empty category");
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
		    Common.waitForElement(4);
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





	 public void verifyCollectionsInUserApp(String filePath) throws IOException, InterruptedException {
		    switchToWindow(1);
		    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		    Common.waitForElement(3);

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

		        // ✅ Hover Shop menu
		        WebElement shopMenu = wait.until(ExpectedConditions
		                .visibilityOfElementLocated(By.xpath("//span[@class='navigation_menu_txt'][normalize-space()='Shop']")));
		        actions.moveToElement(shopMenu).perform();

		        // ✅ Wait for dropdown
		        List<WebElement> dropdownLinks = wait.until(ExpectedConditions
		                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='nav_drop_down_box_category active']//ul/li/a")));

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
		    Common.waitForElement(5);
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
	        switchToWindow(1);
	        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
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
	        Common.waitForElement(5);
	        
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
		    switchToWindow(1);
		    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
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
			        Common.waitForElement(5);
			        
			    }
	
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

				        // Click SKU menu
				        wait.until(ExpectedConditions.elementToBeClickable(clickSKU)).click();

				        // Wait for search boxes
				        List<WebElement> searchBoxes = wait.until(d -> {
				            List<WebElement> elements = d.findElements(By.xpath("//input[@id='text-filter-sku']"));
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
	
	// Method to verify that the Search Keyboard Product 
			public void verifySearchKeyboardProductInUserApp(String filePath) throws InterruptedException {
			    // Get the expected first product from test data
			    String expectedSearchProduct = Common.getValueFromTestData("ExpectedMicroPageFirstProduct"); 

			    // Scroll a bit to make products visible
			    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,340);");
			    Common.waitForElement(2);

			    // Wait for the product to appear in product cards
			    FluentWait<WebDriver> wait = new FluentWait<>(driver)
			            .withTimeout(Duration.ofMinutes(10))
			            .pollingEvery(Duration.ofSeconds(3))
			            .ignoring(NoSuchElementException.class)
			            .ignoring(StaleElementReferenceException.class);

			    WebElement card = wait.until(d -> {
			        driver.navigate().refresh();
			        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

			        List<WebElement> elements = d.findElements(By.xpath(
			                "//div[@id='cls_newproduct_sec_dev']/div[contains(@class,'product_list_cards_list')][1]//h2[@class='product_list_cards_heading']"
			            ));

			            if (!elements.isEmpty() && elements.get(0).getText().trim().equals(expectedSearchProduct)) {
			                return elements.get(0);
			            }
			            return null;
			    });

			    if (card != null && card.isDisplayed()) {
			        System.out.println("✅ Product '" + expectedSearchProduct + "' is visible in User App for MicroPage in First Position ");
			    } else {
			        throw new RuntimeException("❌ Product '" + expectedSearchProduct + "' not found in User App for MicroPage: " );
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
		        Common.waitForElement(5);
		        
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
			    switchToWindow(1);
			    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
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
		        Common.waitForElement(5);
		        
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
			    switchToWindow(1);
			    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
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
