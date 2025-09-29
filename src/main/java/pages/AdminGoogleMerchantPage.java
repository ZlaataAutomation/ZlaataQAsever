package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.AdminGoogleMerchantObjRepo;
import utils.Common;

public class AdminGoogleMerchantPage extends AdminGoogleMerchantObjRepo {
	
	public AdminGoogleMerchantPage(WebDriver driver) 
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
	
	
	
	public String searchProductAndCopySku(){
		Common.waitForElement(3);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successfull redirect to Adimn Product page");
	 // Open product Details
	    click(productDetailsMenu);  
	    System.out.println("✅ Successfull click product Details menu");
	    waitFor(productSearchBox);
	    click(productSearchBox);
	    
	 // Fetch the product name directly from Excel map
	    String productName = Common.getValueFromTestDataMap("ProductListingName");
	    System.out.println("✅ Successfull fetch product Details name from excel sheet");
	 // Search or enter the product
	    type(productSearchBox, productName + Keys.ENTER);
	    Common.waitForElement(2);
	    System.out.println("✅ Successfull put product Details name in searchbox and also click enter");
	    
	 // now click edit, etc…
	    waitFor(editProductButton);
	    click(editProductButton);
	    System.out.println("✅ Successfull click product edit option");
	    // now click item, etc…
	    Common.waitForElement(2);
	    waitFor(itemProductButton);
	    click(itemProductButton);
	    System.out.println("✅ Successfull click product item option");
	    
	    //Click Google Merchant Yes Button
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400);");
	    Common.waitForElement(2);
	    waitFor(yesBtn);
	    click(yesBtn);
	    System.out.println("✅ Successfull click Google Merchant Yes option");
	    
	 // now take Sku
	    waitFor(skuField);
	    System.out.println("✅ Successfull copy the SKU from skufield");
        return skuField.getAttribute("value").trim();
        
	}
	
	public void enableGoogleMerchantAndPushTheProduct(String sku) {
		// Open Export Histories
        Common.waitForElement(2);
        click(searchProductCollectionMenu);
        type(searchProductCollectionMenu, "Google Merchants");
        click(clickGoogleMerchant);
        System.out.println("✅ Selected Google  Merchants");
        
     // 2. Enter copied SKU
        itemIdBox.click();
        itemIdBox.clear();
        itemIdBox.sendKeys(sku);
        itemIdBox.sendKeys(Keys.ENTER);
        
        String expectedProductName = Common.getValueFromTestDataMap("ProductListingName");
     // ✅ Wait until product name is visible in the row
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[normalize-space()='" + expectedProductName + "']")
        ));
      
        if (!productName.isDisplayed()) {
            throw new AssertionError("Product name not visible for SKU: " + sku);
        }
        Common.waitForElement(2);
	    waitFor(checkBox);
	    click(checkBox);
	    System.out.println("✅ Successfull click Select Option ");
        
         // 5. Click push button
	    Common.waitForElement(2);
	    waitFor(pushBtn);
	    click(pushBtn);
	    Common.waitForElement(2);
	    waitFor(yesPush);
	    click(yesPush);
	    System.out.println("✅ Successfull Pushed ");
	    
	    Common.waitForElement(5);
	    waitFor(clickOk);
	    click(clickOk);
	    System.out.println("✅ Successfull Click Ok Button ");  
         
   }      
		
	

	
  public void productStatusPublishedMessage(String sku) {
		
		boolean published = false;

		for (int i = 0; i < 20; i++) { // retry max 10 times
		    try {
		        // Wait 2 sec and sync
		        Common.waitForElement(2);
		        waitFor(syncAllBtn);
		        click(syncAllBtn);

		        Common.waitForElement(2);
		        waitFor(yesSyncAll);
		        click(yesSyncAll);

		        Common.waitForElement(5);
		        waitFor(clickOk);
		        click(clickOk);
		        System.out.println("✅ Successfully clicked Sync All → Yes → OK");

		        // 🔍 Now check the status for this SKU
		        WebElement statusBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//td[div/span[text()='" + sku + "']]/preceding-sibling::td[3]//span[contains(@class,'badge')]")
		        ));

		        String statusText = statusBadge.getText().trim();
		        System.out.println("ℹ️ Current status for SKU " + sku + " = " + statusText);

		        if (statusText.equalsIgnoreCase("Published")) {
		            published = true;
		            System.out.println("🎉 Product " + sku + " published successfully.");
		            break;
		        }

		    } catch (Exception e) {
		        System.out.println("⚠️ Retry sync attempt " + (i + 1));
		    }
		}

		if (!published) {
		    throw new AssertionError("❌ Product did not reach Published state within timeout for SKU: " + sku);
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
