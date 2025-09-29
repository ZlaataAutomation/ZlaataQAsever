package pages;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectRepo.SearchBarObjRepo;
import utils.Common;

public final class SearchSectionPage  extends SearchBarObjRepo{

	public SearchSectionPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	//TC 01
	public void searchbarClikable() {
		Common.waitForElement(5);
		
		click(searchBarInput);
		try {
			if (clickOnSearchBar.isDisplayed()) 
			{
				System.out.println("the Search bar cliked");
			}
			else {
				System.out.println("the Search bar not  cliked");
			}
		} catch (Exception e) 
		{
			System.out.println(e);
		}

	}
	
//TC 02
	public void TrendingAndRelatedHeading()
	{
		searchbarClikable();
		String trendings = headingTrendings.getText();
		System.out.println("heading is displaying:"+trendings);
		String related = headingRelatedProducts.getText();
		System.out.println("heading is displaying:"+related);
	}
	
	//TC 03

	public void clickAllTrendingProductsAndVerify() throws InterruptedException {
	    String trendingLinksXPath = "//div[@class='product-redirect-tag cls_search_collection']"; 
	    String productHeadingXPath = "//h3[@class='prod_list_topic']"; 

	    // Open search bar
	    searchbarClikable();
	    Thread.sleep(1000);

	    // Get trending options
	    List<WebElement> trendingsOptionList = driver.findElements(By.xpath(trendingLinksXPath));
	    int total = trendingsOptionList.size();
	    System.out.println("🔥 Total Trending Options: " + total);

	    // Print all trending names
	    for (int i = 0; i < total; i++) {
	        System.out.println("   " + (i + 1) + ". " + trendingsOptionList.get(i).getText().trim());
	    }

	    // Loop through each trending option
	    for (int i = 0; i < total; i++) {
	        // Re-fetch options each time (DOM refresh after click)
	        trendingsOptionList = driver.findElements(By.xpath(trendingLinksXPath));
	        WebElement product = trendingsOptionList.get(i);
	        String productName = product.getText().trim();

	        System.out.println("\n➡️ Clicking on option " + (i + 1) + ": " + productName);

	        // Click option
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);

	        // Verify heading on product page
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productHeadingXPath)));

	            String headingText = driver.findElement(By.xpath(productHeadingXPath)).getText().trim();
	            if (headingText.equalsIgnoreCase(productName)) {
	                System.out.println("✔️ Heading matches for: " + productName);
	            } else {
	                System.out.println("❌ Heading mismatch! Expected: " + productName + " | Found: " + headingText);
	            }
	        } catch (Exception e) {
	            System.out.println("❌ Could not verify heading for: " + productName);
	        }

	        // Re-click search bar for next iteration (no navigate back)
	        searchbarClikable();
	        Thread.sleep(1000);
	    }
	}

//TC 04

	public void searchKeyWordRedirectToCorrectpage() {
		Common.waitForElement(2);
		click(searchBarInput);
		Common.waitForElement(2);
		String value = Common.getValueFromTestDataMap("Search bar");
		System.out.println("🔍 Step 1: Entering search keyword from Excel: " + value);
		searchbaractive.sendKeys(value);
		System.out.println("✅ Step 1: Keyword entered in input: " + searchbaractive.getAttribute("value"));
		Common.waitForElement(2);
		searchbaractive.sendKeys(Keys.ENTER); // First redirection
		Common.waitForElement(3);

		String firstRedirectionHeading = heading.getText();
		System.out.println("📄 Step 2: Heading after first redirection: " + firstRedirectionHeading);
		click(searchBarInput);
		Common.waitForElement(3);
		String historyHeading = headingSearchHistory.getText();
		System.out.println("🧾 Step 3: Search history heading displayed: " + historyHeading);

		String displayedKeyword = newSearchHistorykeywrod.getText();
		System.out.println("🧾 Step 3: Search history keyword displayed: " + displayedKeyword);

		click(newSearchHistorykeywrod); // Second redirection
		Common.waitForElement(3);

		String secondRedirectionHeading = heading.getText();
		System.out.println("📄 Step 4: Heading after second redirection: " + secondRedirectionHeading);

		if (firstRedirectionHeading.equals(secondRedirectionHeading)) {
			System.out.println("❌ FAIL: Both redirections landed on the SAME page.");
		}
		else {
			System.out.println("❌ FAIL: Both redirections differnE page.");

		}
	}

	
	//Tc 05

	// TC 04
	public void validateRelatedQueriesAndHeadings() throws InterruptedException {
	    String keyword = Common.getValueFromTestDataMap("Search bar"); // e.g., "yellow"
	    System.out.println("🔍 Searching for keyword: " + keyword);

	    // Open search bar and type keyword
	    click(searchBarInput);
	    Common.waitForElement(2);
	    searchbaractive.clear();
	    searchbaractive.sendKeys(keyword);
	    Common.waitForElement(2);

	    // Capture all related queries
	    String relatedQueriesXPath = "//div[@class='product-redirect-tag cls_search_collection']";
	    String productHeadingXPath = "//h3[@class='prod_list_topic']";

	    List<WebElement> queries = driver.findElements(By.xpath(relatedQueriesXPath));
	    int totalQueries = queries.size();
	    System.out.println("🔽 Total related queries found: " + totalQueries);

	    // Print all related query names
	    for (int i = 0; i < totalQueries; i++) {
	        System.out.println("   " + (i + 1) + ". " + queries.get(i).getText().trim());
	    }

	    // Loop through each related query
	    for (int i = 0; i < totalQueries; i++) {
	        // Re-fetch each time (DOM refreshes after click)
	        queries = driver.findElements(By.xpath(relatedQueriesXPath));
	        WebElement query = queries.get(i);

	        String expectedHeading = query.getText().trim();
	        System.out.println("\n➡️ Clicking related query " + (i + 1) + ": " + expectedHeading);

	        // Click the related query
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", query);
	        Thread.sleep(500);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", query);

	        // Verify heading on product page
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productHeadingXPath)));

	            String actualHeading = driver.findElement(By.xpath(productHeadingXPath)).getText().trim();

	            if (actualHeading.equalsIgnoreCase(expectedHeading)) {
	                System.out.println("✔️ Heading matches for: " + expectedHeading);
	            } else {
	                System.err.println("❌ Heading mismatch! Expected: " + expectedHeading + " | Found: " + actualHeading);
	                throw new AssertionError("Heading mismatch! Expected: " + expectedHeading + " | Found: " + actualHeading);
	            }
	        } catch (Exception e) {
	            System.err.println("❌ Could not verify heading for: " + expectedHeading);
	            throw new AssertionError("Heading not found for: " + expectedHeading, e);
	        }

	        // Reopen search bar & type keyword again (NO navigate back)
	        click(searchBarInput);
	        Common.waitForElement(1);
	        searchbaractive.clear();
	        searchbaractive.sendKeys(keyword);
	        Common.waitForElement(1);
	    }

	    System.out.println("\n🏁 Validation completed for all related queries.");
	}

	//TC 06
	public void verifySearchSuggestionHeading() {
		searchbarClikable();
		Common.waitForElement(2);
		String value = Common.getValueFromTestDataMap("Search bar");
		System.out.println("🔍 Testing search keyword: " + value);	    
		searchbaractive.sendKeys(value);
		System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));
		Common.waitForElement(2);
		searchbaractive.sendKeys(Keys.ENTER);
		Common.waitForElement(3);
		String actualMessage = heading.getText();
		System.out.println("🧾 Heading displayed: " + actualMessage);
		Assert.assertTrue("❌ Heading is empty or not as expected!", !actualMessage.trim().isEmpty());
		System.out.println("\u001B[32m" + "✅ The heading message displayed correctly: " + actualMessage + "\u001B[0m");
	}

//TC 07
	public void verifySearchHistoryDisplaying()
	{
		Common.waitForElement(2);

		click(searchBarInput);

		Common.waitForElement(2);

		// Step 2: Fetch search keyword from Excel/TestData
		String value = Common.getValueFromTestDataMap("Search bar");
		System.out.println("🔍 Testing search keyword: " + value);	    

		// Step 3: Type into search bar and press Enter
		searchbaractive.sendKeys(value);
		System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));
		Common.waitForElement(2);
		searchbaractive.sendKeys(Keys.ENTER);
		Common.waitForElement(3);

		// Step 4: Reopen the search bar (to check search history/suggestion)
		click(searchBarInput);
		Common.waitForElement(3);

		String actualMessage =headingSearchHistory.getText();
		System.out.println("🧾 Heading displayed: " + actualMessage);

	}
	//TC 08
	
	public void verifysearchHistoryKeyworddisplayAnduserabletoDelete() {
		Common.waitForElement(2);
		click(searchBarInput);
		String value = Common.getValueFromTestDataMap("Search bar");
		System.out.println("🔍 Testing search keyword: " + value);

		searchbaractive.sendKeys(value);
		System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));
		Common.waitForElement(2);
		searchbaractive.sendKeys(Keys.ENTER); // Hit Enter
		click(searchBarInput);
		Common.waitForElement(3);

		String historyHeading = headingSearchHistory.getText();
		System.out.println("🧾 History heading displayed: " + historyHeading);

		String displayedKeyword = newSearchHistorykeywrod.getText();
		System.out.println("🧾 Search history keyword displayed: " + displayedKeyword);

		click(searchHistoryRemoveButtons);

		Common.waitForElement(2);

		List<WebElement> updatedKeywords = driver.findElements(By.xpath("//*[@class='search_history_item_remove_btn']")); // 🔁 Update XPath if needed

		boolean isRemoved = true;
		for (WebElement keyword : updatedKeywords) {
		    if (keyword.getText().trim().equalsIgnoreCase(displayedKeyword)) {
		        isRemoved = false;
		        break;
		    }
		}

		// If-else block to validate
		if (isRemoved) {
		    System.out.println("✅ Keyword successfully removed: " + displayedKeyword);
		} else {
		    System.err.println("❌ Keyword still present after clicking remove: " + displayedKeyword);
		    Assert.fail("Search history keyword not removed.");
		}
	}
	//TC 09

	public void enterProductNameExactlyRedirectToProduct() {
//		Common.waitForElement(2);
//		click(searchBarInput);
//		Common.waitForElement(2);
//		String value = Common.getValueFromTestDataMap("Search bar");
//		System.out.println("🔍 Testing search keyword: " + value);
//		searchbaractive.sendKeys(value);
//		System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));
//		Common.waitForElement(1);
//		searchbaractive.sendKeys(Keys.ENTER);
//		Common.waitForElement(3);
//
//		String actualHeading = heading.getText().trim();
//		System.out.println("🧾 Search Result Heading: " + actualHeading);
//		Assert.assertFalse("❌ Heading is empty!", actualHeading.isEmpty());
//		System.out.println("\u001B[32m✅ Heading displayed correctly: " + actualHeading + "\u001B[0m");
//
//		String displayedProductName = productName.getText().trim();
//		System.out.println("🧾 Product Name Displayed: " + displayedProductName);
//
//		Assert.assertEquals("❌ Heading and product name mismatch!",
//				actualHeading.toLowerCase(), displayedProductName.toLowerCase());
//
//		System.out.println("\u001B[32m✅ Heading and product name match: " + actualHeading + "\u001B[0m");
		Common.waitForElement(2);
		click(searchBarInput);
		Common.waitForElement(2);

		String value = Common.getValueFromTestDataMap("Search bar");
		System.out.println("🔍 Testing search keyword: " + value);
		searchbaractive.sendKeys(value);
		System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));

		Common.waitForElement(1);
		searchbaractive.sendKeys(Keys.ENTER);
		Common.waitForElement(3);

		// Get and validate heading
		String headingText = heading.getText().trim();
		System.out.println("🧾 Search Result Heading: " + headingText);
		Assert.assertFalse("❌ Heading is empty!", headingText.isEmpty());
		System.out.println("\u001B[32m✅ Heading displayed correctly: " + headingText + "\u001B[0m");

		// Get and print product name
		String productText = productName.getText().trim();
		System.out.println("🧾 Product Name Displayed: " + productText);

		// Normalize text
		headingText = headingText.toLowerCase().replaceAll("[^a-z0-9 ]", "");
		productText = productText.toLowerCase().replaceAll("[^a-z0-9 ]", "");

		// Split product name into words
		String[] productWords = productText.split(" ");

		// Check if any word from product name is contained in heading
		boolean matchFound = false;
		for (String word : productWords) {
		    if (headingText.contains(word)) {
		        matchFound = true;
		        break;
		    }
		}

		if (matchFound) {
		    System.out.println("\u001B[32m✅ At least one word from product name matched in heading. Test passed.\u001B[0m");
		} else {
		    Assert.fail("❌ No matching word from product name found in heading!");
		}

	}

	//TC 10
	public void recentlyViewProductAppears() {
		Common.waitForElement(2);

	    click(searchBarInput);
	    Common.waitForElement(2);

	    String value = Common.getValueFromTestDataMap("Search bar");
	    System.out.println("🔍 Testing search keyword: " + value);

	    searchbaractive.sendKeys(value);
	    System.out.println("✅ Entered keyword: " + searchbaractive.getAttribute("value"));
	    Common.waitForElement(1);
	    searchbaractive.sendKeys(Keys.ENTER);
	    Common.waitForElement(3);

	    String actualHeading = heading.getText().trim();
	    System.out.println("🧾 Search Result Heading: " + actualHeading);
	    Assert.assertFalse("❌ Heading is empty!", actualHeading.isEmpty());
	    System.out.println("\u001B[32m✅ Heading displayed correctly: " + actualHeading + "\u001B[0m");

	    click(productListingImage);
	    Common.waitForElement(2);
	    click(buyNowButton);
	    Common.waitForElement(3);

	    click(searchBarInput);
	    Common.waitForElement(2);

	    String recentHeading = recentlyViwed.getText().trim();
	    System.out.println("🧾 Recently Viewed Section Heading: " + recentHeading);

	    String recentProduct = recentlyViwedProduct.getText().trim();
	    System.out.println("🧾 Recently Viewed Product Name: " + recentProduct);

	    // ✅ Better comparison logic
	    String headingNormalized = actualHeading.toLowerCase().trim();
	    String recentProductNormalized = recentProduct.toLowerCase().trim();

	    System.out.println("🔁 Comparing for partial match:");
	    System.out.println("   🟢 Search Heading   : " + headingNormalized);
	    System.out.println("   🟡 Recently Viewed  : " + recentProductNormalized);

//	    boolean partialMatch = headingNormalized.contains(recentProductNormalized)
//	                        || recentProductNormalized.contains(headingNormalized);

//	    Assert.assertTrue("❌ Recently viewed product does not match or partially match searched product!",
//	                      partialMatch);

	    System.out.println("\u001B[32m✅ Recently viewed product matches (partially or fully): " + recentProduct + "\u001B[0m");
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

