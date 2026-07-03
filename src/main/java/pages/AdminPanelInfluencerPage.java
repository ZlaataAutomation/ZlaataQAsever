package pages;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.AdminPanelInfluencerObjRepo;
import utils.Common;

public class AdminPanelInfluencerPage extends AdminPanelInfluencerObjRepo {
	
	public AdminPanelInfluencerPage(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	public void adminLoginApp() {
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
	
	public String takeRandomProductName() {

	    HomePage home = new HomePage(driver);
	    home.homeLaunch();
	    Common.waitForElement(3);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);
	    
	    // ✅ Click on landing page banner if present (same pattern as launchHomepageWithBrand)
	    try {
	        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'landing_page_link_btn')]")
	        ));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);
	        System.out.println("✅ Clicked landing page Shop Now banner");
	        Common.waitForElement(2);
	    } catch (Exception e) {
	        System.out.println("ℹ️ No landing page banner found, continuing...");
	    }

	    // Hover on Shop → Dresses
	    WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[@class='header_nav_link '][normalize-space()='Shop']")));
	    actions.moveToElement(shopMenu).perform();

	    WebElement dressesButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//a[contains(@class,'dropdown_category_link')])[1]")));
	    dressesButton.click();

	    System.out.println("✅ Clicked on 'All' menu");

	    // Get all products
	    List<WebElement> products = wait.until(ExpectedConditions
	            .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'prod_listing_card')]")));

	    if (products.isEmpty()) {
	        System.out.println("⚠️ No products found!");
	        return null;
	    }

	    Random rand = new Random();
	    String productName = null;

	    // Try multiple random products until in-stock found
	    for (int attempt = 1; attempt <= 5; attempt++) {

	        // Re-fetch product count fresh each attempt
	        int productCount = driver.findElements(By.xpath("//div[contains(@class,'prod_listing_card')]")).size();
	        if (productCount == 0) {
	            System.out.println("⚠️ No products found!");
	            return null;
	        }

	        int randomIndex = rand.nextInt(productCount) + 1;
	        System.out.println("🎯 Checking product index: " + randomIndex);

	        // Re-locate name fresh using driver (not from cached element)
	        String name = driver.findElement(
	                By.xpath("(//div[contains(@class,'prod_listing_card')])[" + randomIndex + "]//a[@class='product_list_name']"))
	                .getText().trim();

	        // Re-locate out-of-stock flag fresh using driver (not from cached element)
	        boolean isOutOfStock = !driver.findElements(
	                By.xpath("(//div[contains(@class,'prod_listing_card')])[" + randomIndex + "]//span[@class='prod_listing_hurry'][normalize-space()='Out of Stock']"))
	                .isEmpty();

	        if (isOutOfStock) {
	            System.out.println("❌ OUT OF STOCK → " + name);
	            continue;
	        }

	        productName = name;
	        System.out.println("✅ Selected in-stock product: " + productName);
	        break;
	    }

	    if (productName == null) {
	        System.out.println("⚠️ No in-stock product found after retries");
	        return null;
	    }

	    System.out.println("📌 Final Product Name: " + productName);
	    return productName;
	}
	
	
	String copiedProductName;
	String copiedSku;
	public void setTheProductImageAndStatusInInfluencer(String productListingName, String imagePath) {
	    Common.waitForElement(2);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successful redirect to Admin Product page");

	    // Open product listing
	    waitFor(productListingMenu);
	    click(productListingMenu);
	    System.out.println("✅ Successful click product listing menu");
	    waitFor(productSearchBox);
	    click(productSearchBox);

	    // ✅ Search for the product
	    type(productSearchBox, productListingName);
	    System.out.println("✅ Entered product listing name in search box");
	    Common.waitForElement(2);

	    // ✅ Wait for dropdown result and click the EXACT matching product
	    try {
	        wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//ul[contains(@class,'dropdown-menu')] | //div[contains(@class,'autocomplete')]")));

	        WebElement exactResult = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//li[normalize-space()='" + productListingName + "']")
	        ));
	        
	        click(exactResult);
	        System.out.println("✅ Clicked on EXACT dropdown result for: " + productListingName);
	        
	    } catch (Exception e) {
	        try {
	            WebElement partialResult = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//li[contains(normalize-space(),'" + productListingName + "')]")
	            ));
	            click(partialResult);
	            System.out.println("✅ Clicked on PARTIAL dropdown result for: " + productListingName);
	            
	        } catch (Exception e2) {
	            productSearchBox.sendKeys(Keys.ENTER);
	            System.out.println("⚠️ No dropdown match found, pressed ENTER instead");
	        }
	    }
	    Common.waitForElement(2);
	    
	    // now click edit
	    Common.waitForElement(3);
	    waitFor(editProductButton);
	    click(editProductButton);
	    System.out.println("✅ Clicked product edit option");
	    
	    // Copy text from Product Name textbox
	    copiedProductName = productNameTextbox.getAttribute("value").trim();
	    System.out.println("📋 Copied product name: " + copiedProductName);
	    
	    // now click item
	    Common.waitForElement(3);
	    waitFor(itemProductButton);
	    click(itemProductButton);
	    System.out.println("✅ Clicked product item option");
	    
	    // ✅ Find the exact matching item by product detail name
	    int matchedIndex = -1;
	    int maxItems = 10; // Safety limit to prevent infinite loop
	    
	    for (int i = 0; i < maxItems; i++) {
	        try {
	            // Check if product detail name input exists for this index
	            WebElement detailNameInput = driver.findElement(By.xpath(
	                    "(//input[@name='filters[" + i + "][name]'])[1]"));
	            String detailName = detailNameInput.getAttribute("value").trim();
	            
	            System.out.println("🔍 Checking item [" + (i + 1) + "] detail name: " + detailName);
	            
	            // Check if this matches the copied product listing name
	            if (detailName.equalsIgnoreCase(copiedProductName) || 
	                detailName.equalsIgnoreCase(productListingName)) {
	                
	                matchedIndex = i;
	                System.out.println("✅ Found matching item at index: " + (i + 1));
	                
	                // Copy SKU from the matched item
	                WebElement skuInput = driver.findElement(By.xpath(
	                        "(//input[@name='filters[" + i + "][sku]'])[1]"));
	                copiedSku = skuInput.getAttribute("value").trim();
	                System.out.println("📋 Copied SKU from matched item: " + copiedSku);
	                
	                break;
	            }
	        } catch (NoSuchElementException e) {
	            // No more items found
	            System.out.println("ℹ️ No more items found after index: " + i);
	            break;
	        }
	    }
	    
	    if (matchedIndex == -1) {
	        System.out.println("⚠️ No matching item found for: " + copiedProductName);
	        // Fallback to first item
	        matchedIndex = 0;
	        try {
	            WebElement skuInput = driver.findElement(By.xpath(
	                    "(//input[@name='filters[0][sku]'])[1]"));
	            copiedSku = skuInput.getAttribute("value").trim();
	            System.out.println("📋 Fallback: Copied SKU from first item: " + copiedSku);
	        } catch (Exception e) {
	            System.out.println("❌ Could not copy SKU from first item");
	        }
	    }
	    
	    // ✅ Get product listing input
	    WebElement listingInput = wait.until(ExpectedConditions
	            .presenceOfElementLocated(By.xpath("(//div[label[text()='Product Detail Name']]//input)[1]")));
	    String listingValue = listingInput.getAttribute("value").trim();
	    System.out.println("✅ Matched product listing: " + listingValue);
	    
	    // ✅ Check Influencer Yes
	    if (influencerYes.isSelected()) {
	        System.out.println("ℹ️ Influencer already ON for: " + listingValue);
	    } else {
	        influencerYes.click();
	        System.out.println("✅ Turned ON Influencer for: " + listingValue);
	    }
	    
	    // ✅ Upload image for the MATCHED item
	    Common.waitForElement(3);
	    
	    // Click Choose File button for the matched item (this opens the modal directly)
	    WebElement chooseFileBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	            "(//button[contains(@type,'button')][normalize-space()='Choose File'])[" + (matchedIndex + 1) + "]")));
	    
	    // Scroll to button and use JavaScript click to avoid interception
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", chooseFileBtn);
	    Common.waitForElement(1);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chooseFileBtn);
	    System.out.println("✅ Clicked Choose File button for item index: " + (matchedIndex + 1));
	    
	    Common.waitForElement(2);
	    
	    // Upload new banner image inside the modal
	    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector(".modal-body.modal-scroll-btn")));
	    WebElement fileInput = modal.findElement(By.id("listingImagesDesktopInput"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].classList.remove('d-none');", fileInput);
	    fileInput.sendKeys(imagePath);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].classList.add('d-none');", fileInput);
	    System.out.println("✅ Successful image Upload for item: " + (matchedIndex + 1));
	    
	    Common.waitForElement(2);
	    waitFor(confirmUpload);
	    click(confirmUpload);
	    System.out.println("✅ Clicked confirmUpload Button");
	    
	    // Click Save
	    Common.waitForElement(5);
	    wait.until(ExpectedConditions.elementToBeClickable(saveButton));
	    click(saveButton);
	    System.out.println("✅ Saved product successfully: " + listingValue);
	}
	
	public void addTheProductInProductCollection() {
		click(searchProductCollectionMenu);
		Common.waitForElement(2);
	    waitFor(searchProductCollectionMenu); 
	    type(searchProductCollectionMenu, "Product Collections");
	    System.out.println("✅ Typed 'Product Collections' ");
	    Common.waitForElement(2);
	    waitFor(clickProductCollection);
	    click(clickProductCollection);
	    System.out.println("✅ Selected Product Collection");
	    Common.waitForElement(2);
	    waitFor(clickStatus);
	    click(clickStatus);
	    waitFor(statusActiveOption);
	    click(statusActiveOption);
	    System.out.println("✅ Selected Active status");
	    Common.waitForElement(2);
	    waitFor(collectionButton);
	    click(collectionButton);
	    System.out.println("✅ Clicked Collection button");
	    Common.waitForElement(2);
	    waitFor(searchTextBox);
	    type(searchTextBox, "influencers");
	    searchTextBox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Searched for influencers");
	    Common.waitForElement(2);
	    waitFor(editCollectionButton);
	    editCollectionButton.click();
	    System.out.println("✅ Entered Edit mode for collection");
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400);");
	    waitFor(addProductTextbox);
	    type(addProductTextbox, copiedProductName);
	    Common.waitForElement(2);
	    addProductTextbox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Added product name to collection");
	    //Save
	    waitFor(saveButton);
	    click(saveButton);
	    System.out.println("✅ Saved collection changes");	
	}
		
	public void sortTheProductForIncluencer() {
	    // Open Product Sort
	    driver.navigate().refresh();
	    Common.waitForElement(2);
	    click(searchProductSortMenu);
	    type(searchProductSortMenu, "Product Sorts");
	    click(clickProductSort);
	    System.out.println("✅ Selected Product Sorts");

	    click(categoryType);
	    type(categorySearchTextBox, "Style By");
	    categorySearchTextBox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Collection selected for Product Sort");

	    Common.waitForElement(3);

	    WebElement zlaataBrand = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//span[contains(text(),'Zlaata India')])[1]")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", zlaataBrand);
	    Common.waitForElement(1);

	    WebElement plusIcon = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//i[@class='las la-plus-circle'])[2]")));
	    click(plusIcon);
	    System.out.println("✅ Clicked + icon to expand Zlaata India brand");

	    Common.waitForElement(3);

	    List<WebElement> allProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	            By.xpath("//div[@class='ps-card sortable-card']")));

	    if (allProducts.size() < 2) {
	        System.out.println("❌ Not enough products available to perform reorder.");
	        return;
	    }

	    int productIndex = -1;
	    for (int i = 0; i < allProducts.size(); i++) {
	        try {
	            WebElement card = driver.findElement(By.xpath(
	                    "(//div[@class='ps-card sortable-card'])[" + (i + 1) + "]"));
	            WebElement skuElement = card.findElement(By.xpath(".//span[@class='ps-card__sku']"));
	            String skuValue = skuElement.getText().trim();
	            System.out.println("🔍 Checking card [" + (i + 1) + "] SKU: " + skuValue);

	            if (skuValue.equalsIgnoreCase(copiedSku)) {
	                productIndex = i;
	                System.out.println("✅ Found product card with matching SKU at index: " + (i + 1));
	                break;
	            }
	        } catch (Exception e) {
	            System.out.println("⚠️ Could not read SKU from card [" + (i + 1) + "]");
	        }
	    }

	    if (productIndex == -1) {
	        System.out.println("❌ No product card found with copied SKU: " + copiedSku);
	        return;
	    }

	    if (productIndex == 0) {
	        System.out.println("ℹ️ Product with SKU " + copiedSku + " is already at 1st position");
	    } else {

	        WebElement productToMove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	                "(//div[@class='ps-card sortable-card'])[" + (productIndex + 1) + "]")));
	        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	                "(//div[@class='ps-card sortable-card'])[1]"))); 

	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", productToMove);
	        Common.waitForElement(1);

	        // 🔍 DIAGNOSTIC — run once, read the console/log output to identify the sortable library
	        try {
	            String diagnosticScript =
	                "var card = arguments[0];" +
	                "var container = card.parentNode;" +
	                "var info = {};" +
	                "info.cardDraggableAttr = card.getAttribute('draggable');" +
	                "info.cardOuterClasses = card.className;" +
	                "info.containerClasses = container.className;" +
	                "info.containerId = container.id;" +
	                "info.hasJQueryUI = (typeof jQuery !== 'undefined' && typeof jQuery.fn.sortable !== 'undefined');" +
	                "info.hasSortableJS = (typeof Sortable !== 'undefined');" +
	                "info.jQueryUISortableActive = info.hasJQueryUI ? jQuery(container).hasClass('ui-sortable') : false;" +
	                "return JSON.stringify(info);";

	            String diagnosticResult = (String) ((JavascriptExecutor) driver).executeScript(diagnosticScript, productToMove);
	            System.out.println("🔍 DIAGNOSTIC INFO: " + diagnosticResult);
	        } catch (Exception e) {
	            System.out.println("⚠️ Diagnostic script failed: " + e.getMessage());
	        }

	        boolean dragSuccess = false;

	        // ✅ Method 1: Actions-based drag with incremental moves
	        try {
	            Point sourceLoc = productToMove.getLocation();
	            Point targetLoc = firstProduct.getLocation();
	            int totalXOffset = targetLoc.getX() - sourceLoc.getX();
	            int totalYOffset = targetLoc.getY() - sourceLoc.getY();
	            int steps = 15;
	            int stepX = totalXOffset / steps;
	            int stepY = totalYOffset / steps;

	            Actions actions = new Actions(driver);
	            actions.moveToElement(productToMove).clickAndHold().pause(Duration.ofMillis(300));
	            for (int i = 0; i < steps; i++) {
	                actions.moveByOffset(stepX, stepY).pause(Duration.ofMillis(80));
	            }
	            actions.moveByOffset(0, -5).pause(Duration.ofMillis(300)).release().build().perform();

	            Common.waitForElement(2);
	            dragSuccess = isSkuFirst(copiedSku);
	            if (dragSuccess) {
	                System.out.println("✅ Verified: Product moved via Actions drag");
	            } else {
	                System.out.println("⚠️ Actions drag did not land, trying MouseEvent JS fallback...");
	            }
	        } catch (Exception e) {
	            System.out.println("⚠️ Actions drag failed: " + e.getMessage());
	        }

	        // ✅ Method 2: Synthetic MouseEvent drag
	        if (!dragSuccess) {
	            try {
	                String jsDrag =
	                    "function fireMouseEvent(type, elem, x, y) {" +
	                    "  var event = new MouseEvent(type, {view: window, bubbles: true, cancelable: true, clientX: x, clientY: y, buttons: 1});" +
	                    "  elem.dispatchEvent(event);" +
	                    "}" +
	                    "var source = arguments[0]; var target = arguments[1];" +
	                    "var s = source.getBoundingClientRect(); var t = target.getBoundingClientRect();" +
	                    "var sx = s.left + s.width/2, sy = s.top + s.height/2;" +
	                    "var ex = t.left + t.width/2, ey = t.top + t.height/2;" +
	                    "fireMouseEvent('mousedown', source, sx, sy);" +
	                    "var steps = 20;" +
	                    "for (var i = 1; i <= steps; i++) {" +
	                    "  var x = sx + (ex-sx)*(i/steps), y = sy + (ey-sy)*(i/steps);" +
	                    "  fireMouseEvent('mousemove', document, x, y);" +
	                    "}" +
	                    "fireMouseEvent('mouseup', target, ex, ey);";

	                ((JavascriptExecutor) driver).executeScript(jsDrag, productToMove, firstProduct);
	                Common.waitForElement(2);
	                dragSuccess = isSkuFirst(copiedSku);
	                if (dragSuccess) {
	                    System.out.println("✅ Verified: Product moved via MouseEvent JS");
	                } else {
	                    System.out.println("⚠️ MouseEvent JS drag did not land, trying PointerEvent fallback...");
	                }
	            } catch (Exception e) {
	                System.out.println("⚠️ MouseEvent JS drag failed: " + e.getMessage());
	            }
	        }

	        // ✅ Method 3: Synthetic PointerEvent drag (for libs using Pointer Events)
	        if (!dragSuccess) {
	            try {
	                String jsPointerDrag =
	                    "function firePointerEvent(type, elem, x, y) {" +
	                    "  var event = new PointerEvent(type, {view: window, bubbles: true, cancelable: true, clientX: x, clientY: y, pointerId: 1, pointerType: 'mouse', isPrimary: true, buttons: 1});" +
	                    "  elem.dispatchEvent(event);" +
	                    "}" +
	                    "var source = arguments[0]; var target = arguments[1];" +
	                    "var s = source.getBoundingClientRect(); var t = target.getBoundingClientRect();" +
	                    "var sx = s.left + s.width/2, sy = s.top + s.height/2;" +
	                    "var ex = t.left + t.width/2, ey = t.top + t.height/2;" +
	                    "firePointerEvent('pointerdown', source, sx, sy);" +
	                    "var steps = 20;" +
	                    "for (var i = 1; i <= steps; i++) {" +
	                    "  var x = sx + (ex-sx)*(i/steps), y = sy + (ey-sy)*(i/steps);" +
	                    "  firePointerEvent('pointermove', document, x, y);" +
	                    "}" +
	                    "firePointerEvent('pointerup', target, ex, ey);";

	                ((JavascriptExecutor) driver).executeScript(jsPointerDrag, productToMove, firstProduct);
	                Common.waitForElement(2);
	                dragSuccess = isSkuFirst(copiedSku);
	                if (dragSuccess) {
	                    System.out.println("✅ Verified: Product moved via PointerEvent JS");
	                } else {
	                    System.out.println("⚠️ PointerEvent JS drag did not land, trying native HTML5 DnD fallback...");
	                }
	            } catch (Exception e) {
	                System.out.println("⚠️ PointerEvent JS drag failed: " + e.getMessage());
	            }
	        }

	        // ✅ Method 4: Native HTML5 drag-and-drop (dragstart/dragover/drop with DataTransfer)
	        if (!dragSuccess) {
	            try {
	                String jsHtml5Drag =
	                    "function fireDragEvent(type, elem, x, y, dataTransfer) {" +
	                    "  var event = new DragEvent(type, {view: window, bubbles: true, cancelable: true, clientX: x, clientY: y});" +
	                    "  Object.defineProperty(event, 'dataTransfer', {value: dataTransfer});" +
	                    "  elem.dispatchEvent(event);" +
	                    "}" +
	                    "var source = arguments[0]; var target = arguments[1];" +
	                    "var s = source.getBoundingClientRect(); var t = target.getBoundingClientRect();" +
	                    "var sx = s.left + s.width/2, sy = s.top + s.height/2;" +
	                    "var ex = t.left + t.width/2, ey = t.top + t.height/2;" +
	                    "var dt = new DataTransfer();" +
	                    "fireDragEvent('dragstart', source, sx, sy, dt);" +
	                    "fireDragEvent('dragenter', target, ex, ey, dt);" +
	                    "fireDragEvent('dragover', target, ex, ey, dt);" +
	                    "fireDragEvent('drop', target, ex, ey, dt);" +
	                    "fireDragEvent('dragend', source, ex, ey, dt);";

	                ((JavascriptExecutor) driver).executeScript(jsHtml5Drag, productToMove, firstProduct);
	                Common.waitForElement(2);
	                dragSuccess = isSkuFirst(copiedSku);
	                if (dragSuccess) {
	                    System.out.println("✅ Verified: Product moved via native HTML5 DnD");
	                }
	            } catch (Exception e) {
	                System.out.println("⚠️ Native HTML5 DnD failed: " + e.getMessage());
	            }
	        }

	        if (!dragSuccess) {
	            System.out.println("❌ All drag methods failed");
	            return;
	        }

	        System.out.println("✅ Dragged product with SKU " + copiedSku + " to 1st position");
	    }

	    // ✅ Scroll down to make Save button visible
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
	    Common.waitForElement(2);

	    WebElement saveBackBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[@data-value='save_and_back']")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBackBtn);
	    Common.waitForElement(1);
	    click(saveBackBtn);
	    System.out.println("✅ Clicked Save & Back button");

	    Common.waitForElement(3);
	    Common.waitForElement(2);

	    try {
	        List<WebElement> toasts = driver.findElements(By.xpath("//div[contains(@class,'noty_body')] | //div[contains(@class,'toast')] | //div[contains(@class,'notification')]"));
	        for (WebElement toast : toasts) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", toast);
	        }
	        Common.waitForElement(1);
	    } catch (Exception e) { }

	    WebElement clearCache1 = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//i[@class='fa fa-refresh']")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearCache1);
	    System.out.println("✅ Cleared cache");

	    Common.waitForElement(6);

	    try {
	        List<WebElement> toasts2 = driver.findElements(By.xpath("//div[contains(@class,'noty_body')] | //div[contains(@class,'toast')] | //div[contains(@class,'notification')]"));
	        for (WebElement toast : toasts2) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", toast);
	        }
	        Common.waitForElement(1);
	    } catch (Exception e) { }

	    WebElement clearCache2 = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//i[@class='fa fa-refresh']")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearCache2);
	    System.out.println("✅ Cleared cache");
	    Common.waitForElement(3);

	    System.out.println("🎉 Category sorting completed successfully!");
	}

	// Helper: checks if the card with the given SKU is currently in position 1
	private boolean isSkuFirst(String sku) {
	    try {
	        WebElement firstCard = driver.findElement(By.xpath(
	                "(//div[@class='ps-card sortable-card'])[1]"));
	        String firstSku = firstCard.findElement(
	                By.xpath(".//span[@class='ps-card__sku']")).getText().trim();
	        return firstSku.equalsIgnoreCase(sku);
	    } catch (Exception e) {
	        return false;
	    }
	}	
	
	public void verifyFirstProductInUserAppInfluencer(String productListingName) {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
	    Common.waitForElement(3);
	    
	 // ✅ Click on landing page banner if present (same pattern as launchHomepageWithBrand)
	    try {
	        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'landing_page_link_btn')]")
	        ));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", banner);
	        System.out.println("✅ Clicked landing page Shop Now banner");
	        Common.waitForElement(2);
	    } catch (Exception e) {
	        System.out.println("ℹ️ No landing page banner found, continuing...");
	    }
	    
	    waitFor(clickInfluencer);
	    click(clickInfluencer);
	    System.out.println("✅ Clicked  Influencer ");
	    
  // Scroll a bit to make products visible
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
	    Common.waitForElement(2);

	    // Wait for the product to appear in product cards
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofMinutes(15))
	            .pollingEvery(Duration.ofSeconds(3))
	            .ignoring(NoSuchElementException.class)
	            .ignoring(StaleElementReferenceException.class);

	    WebElement card = wait.until(d -> {
	        driver.navigate().refresh();
	        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

	        List<WebElement> elements = d.findElements(By.xpath(
	                "(//div[@class='prod_listing_card']//a[@class='product_list_name'])[1]"
	            ));

	            if (!elements.isEmpty() && elements.get(0).getText().trim().equals(productListingName)) {
	                return elements.get(0);
	            }
	            return null;
	    });

	    if (card != null && card.isDisplayed()) {
	        System.out.println("✅ Product '" + productListingName + "' is visible in User App for Influencer in First Position ");
	    } else {
	        throw new RuntimeException("❌ Product '" + productListingName + "' not found in User App for Influencer: " );
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
