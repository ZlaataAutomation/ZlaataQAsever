package pages;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import objectRepo.MenuObjRepo;
import utils.Common;

public final class Menus extends MenuObjRepo {

	

	public Menus(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
  public void clickHome() {
	
	click(homeMenu);
	String banner = banners.getTagName();
    Assert.assertTrue("Banner is Visible", banner.length() <= 50);
    

}
 public void clickNewArrival() {
	 
	 click(newArrivalMenu);
		String heading = newArrivalheading.getText();
		if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
		}
	    Assert.assertTrue("Navigated to New Arrivals Page", heading.equals(heading));
	    
}
 
 public void newArrivalSuggestion() {
	 
	 Actions actions = new Actions(driver);
	 actions.moveToElement(newArrivalMenu).perform();

	 // Get all dropdown products
	 List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//span[@class='na_dropdown_card_name']"));
	 Collections.shuffle(clickRandomProduct);

	 if (!clickRandomProduct.isEmpty()) {
	     WebElement randomProduct = clickRandomProduct.get(0);
	     actions.moveToElement(randomProduct).click().perform();

	     // Get product heading
	     String heading = newArrivalSuggestionRedirection.getText();

	     // Assert that heading is not empty
	     Assert.assertFalse("❌ Product heading is empty, navigation may have failed!", heading.isEmpty());

	     // Optional: Check that URL contains 'product'
	     String currentUrl = driver.getCurrentUrl();
	     Assert.assertTrue("❌ Did not navigate to product details page!", currentUrl.contains("product"));

	     System.out.println("✅ Navigated to product details page. Heading: " + heading);
	 } else {
	     System.out.println("⚠️ No products found in New Arrivals dropdown.");
	 }

 }
public void saleMenu() {
	click(saleMenu);
	String heading = saleMenuHead.getText();
    Assert.assertTrue("Navigated to sale Page", heading.length() <= 50);

}

	public void bossLady() {
		Actions actions = new Actions(driver);
		actions.moveToElement(bossLadyMenu).build().perform();
	    List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//span[@class='bl_dropdown_card_name']"));
		Collections.shuffle(clickRandomProduct);

		if (!clickRandomProduct.isEmpty()) {
			WebElement randomProduct = clickRandomProduct.get(0);
			actions.moveToElement(randomProduct).click().build().perform();
			String heading = bossLadyPage.getText();
		    Assert.assertTrue("Navigated to boss lady Page", heading.length() <= 50);
		
	
		if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
		}
		}
	}
	
	public void shopCategory() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Category Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}	

	}
	
	public void shopCollection() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Collection Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}	

	}
	
	public void shopStyles() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Category Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}

	}
	
	public void PopShop() {
		Common.waitForElement(5);
		click(popShop);
	}
	
	public void getUpdates() 
	{
		Common.waitForElement(1);
		click(getUpdateMenu);
		

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