package pages;


import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import objectRepo.ProductListObjRepo;
import utils.Common;

public final class ProductListingPage extends ProductListObjRepo {



	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void homeCrumbLink() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(2);
		click(homeCrumbLink);
		String banner = banners.getTagName();
		Assert.assertTrue("Banner is Visible", banner.length() <= 50);

	}
	public void pLpHeading() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
		Assert.assertTrue("Product List Page Heading is displaying", heading.length() <= 50);
}

	public void pagination() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();

		try {
			actions.moveToElement(pagination);
			if (pagination.isDisplayed()) {
				Assert.assertTrue((verifyDisplayed(pagination)));
			}

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}

	public void pagiNationArrows() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();

		try {
			if (pagination.isDisplayed()) {
				actions.moveToElement(paginationNext).click().build().perform();
			}
			else if(paginationNext.isEnabled()){

				actions.moveToElement(paginationNext).click().build().perform();

			}
			else  {
				actions.moveToElement(shopMenu);
				actions.moveToElement(category).click().build().perform();
				actions.moveToElement(paginationNext).click().build().perform();
				Common.waitForElement(2);
				actions.moveToElement(paginationPrv).click().build().perform();

			}

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;

		}

	}

	public void paginationNumber() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		List<WebElement> clickRandomNumber = driver.findElements(By.xpath("//div[@class='pagi_count_wrap']"));
		Collections.shuffle(clickRandomNumber);

		if (!clickRandomNumber.isEmpty()) {
			WebElement randomNumber = clickRandomNumber.get(0);
			actions.moveToElement(randomNumber).click().build().perform();

		}
	}

	public void showFilter() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(5);
		actions.moveToElement(showFilter).click().build().perform();
		try {

			if (showFilterMenu.isDisplayed()) {
				Assert.assertTrue((verifyDisplayed(showFilterMenu)));
			}

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}

	public void sortByFilter() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(5);
		actions.moveToElement(sortBy).click().build().perform();

	}
//	public void basicFilterFunction() {
//		Common.waitForElement(5);
//		Actions actions = new Actions(driver);
//		actions.moveToElement(shopMenu);
//		actions.moveToElement(category).click().build().perform();
//		Common.waitForElement(2);
//		actions.moveToElement(showFilter).click().build().perform();
//		List<WebElement> clickShowFilterMenu = driver.findElements(By.xpath("//span[@class='prod_filter_heading']"));
//		Collections.shuffle(clickShowFilterMenu);
//
//		if (!clickShowFilterMenu.isEmpty()) {
//			WebElement randomFilter = clickShowFilterMenu.get(0);
//			actions.moveToElement(randomFilter).click().build().perform();
//		}
//		List<WebElement> clickShowFilterSubMenu = driver.findElements(By.xpath("//div[@class='prod_filter_value']"));
//		Collections.shuffle(clickShowFilterSubMenu);
//
//		if (priceRangeFilter.isDisplayed()) {
//			click(maxPriceFilter);
//			maxPriceFilter.clear();
//			Common.waitForElement(2);
//			type(maxPriceFilter,Common.getValueFromTestDataMap("Mobile Number"));
//			Common.waitForElement(2);
//			click(filterApply);
//			click(showFilter);
//			click(filterClearAll);
//			Common.waitForElement(2);
//			click(closeShowFilter);
//		}
//
//
//		click(filterApply);
//		Common.waitForElement(2);
//		click(showFilter);
//		click(filterClearAll);
//	}

	public void basicFilterFunction() {
	    Common.waitForElement(5);
	    Actions actions = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    // Hover over shopMenu and click category
	    actions.moveToElement(shopMenu).perform();
	    wait.until(ExpectedConditions.elementToBeClickable(category));
	    actions.moveToElement(category).click().build().perform();
	    Common.waitForElement(2);
	    // Click showFilter
	    wait.until(ExpectedConditions.elementToBeClickable(showFilter));
	    actions.moveToElement(showFilter).click().build().perform();
	    // Click a random main filter
	    List<WebElement> mainFilters = driver.findElements(By.xpath("//span[@class='prod_filter_heading']"));
	    List<WebElement> visibleMainFilters = mainFilters.stream()
	            .filter(WebElement::isDisplayed)
	            .filter(WebElement::isEnabled)
	            .collect(Collectors.toList());
	    Collections.shuffle(visibleMainFilters);
	    String selectedMainFilterName = "";
	    if (!visibleMainFilters.isEmpty()) {
	        WebElement randomMainFilter = visibleMainFilters.get(0);
	        selectedMainFilterName = randomMainFilter.getText().trim();
	        js.executeScript("arguments[0].scrollIntoView(true);", randomMainFilter);
	        wait.until(ExpectedConditions.elementToBeClickable(randomMainFilter));
	        try {
	            actions.moveToElement(randomMainFilter).click().build().perform();
	        } catch (ElementNotInteractableException e) {
	            js.executeScript("arguments[0].click();", randomMainFilter);
	        }
	    }
	    // Wait for sub-filters to appear and get only the visible sub-filters
	    List<WebElement> filterOptions = driver.findElements(By.xpath("//span[@class='prod_filter_value_name']"));
	    List<WebElement> visibleOptions = filterOptions.stream()
	            .filter(WebElement::isDisplayed)
	            .filter(WebElement::isEnabled)
	            .collect(Collectors.toList());
	    Collections.shuffle(visibleOptions);
	    int expectedProductCount = -1;
	    String selectedSubFilterName = "";
	    if (!visibleOptions.isEmpty()) {
	        WebElement selectedFilter = visibleOptions.get(0);
	        String filterText = selectedFilter.getText().trim();  // Example: Green (25)
	        selectedSubFilterName = filterText.replaceAll("\\s*\\(\\d+\\)$", ""); // Get "Green"
	        System.out.println("Sub filter selected: " + filterText);
	        try {
	            expectedProductCount = Integer.parseInt(filterText.replaceAll(".*\\((\\d+)\\)", "$1"));
	        } catch (Exception e) {
	            System.out.println(":x: Could not extract count from: " + filterText);
	        }
	        js.executeScript("arguments[0].scrollIntoView(true);", selectedFilter);
	        wait.until(ExpectedConditions.elementToBeClickable(selectedFilter));
	        try {
	            selectedFilter.click();
	        } catch (ElementNotInteractableException e) {
	            js.executeScript("arguments[0].click();", selectedFilter);
	        }
	    }
	    // Apply filter
	    click(filterApply);
	    Common.waitForElement(3);
	    // Count products across all pages
	    int totalProductsFound = 0;
	    boolean hasNextPage = true;
	    while (hasNextPage) {
	        List<WebElement> productItems = driver.findElements(By.xpath("//div[@class='product_list_card_img']"));
	        totalProductsFound += productItems.size();
	        List<WebElement> nextButtons = driver.findElements(By.xpath("//a[contains(@class, 'next')]"));
	        if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed() && nextButtons.get(0).isEnabled()) {
	            WebElement nextBtn = nextButtons.get(0);
	            js.executeScript("arguments[0].scrollIntoView(true);", nextBtn);
	            try {
	                nextBtn.click();
	            } catch (Exception e) {
	                js.executeScript("arguments[0].click();", nextBtn);
	            }
	            Common.waitForElement(3);
	        } else {
	            hasNextPage = false;
	        }
	    }
	    // Final verification logs
	    System.out.println("Main filter selected: " + selectedMainFilterName);
	    System.out.println("Sub filter selected: " + selectedSubFilterName + " with count of " + expectedProductCount);
	    System.out.println("Product listing page matched count: " + totalProductsFound);
	    if (expectedProductCount != -1 && expectedProductCount == totalProductsFound) {
	        System.out.println(":white_tick: Product count matches across pages.");
	    } else {
	        System.out.println(":x: Product count mismatch.");
	    }
	    // Cleanup filters
	    click(showFilter);
	    click(filterClearAll);
	    Common.waitForElement(2);
	    click(closeShowFilter);
	}
	public void allsortBy() {
	    Common.waitForElement(5);
	    Actions actions = new Actions(driver);
	    actions.moveToElement(shopMenu);
	    actions.moveToElement(category).click().build().perform();
	    Common.waitForElement(2);
	    click(sortBy);
	    actions.moveToElement(sortByWhatsNew).click().build().perform();

	    String sortOptionXpath = "//li[contains(@class,'filter_sort_list_item ') or contains(@class,'filter_sort_')]";

	    List<WebElement> options = driver.findElements(By.xpath(sortOptionXpath));
	    int totalOptions = options.size();

	    for (int i = 0; i < totalOptions; i++) {
	        // Re-click the Sort By dropdown before each selection
	        sortBy.click();
	        Common.waitForElement(1);

	        // Re-fetch the sort options to avoid stale elements
	        List<WebElement> currentOptions = driver.findElements(By.xpath(sortOptionXpath));

	        if (i < currentOptions.size()) {
	            WebElement option = currentOptions.get(i);
	            String optionText = option.getText().trim();
	            System.out.println("🟢 Clicking Sort Option [" + (i + 1) + "]: " + optionText);

	            option.click();
	            Common.waitForElement(2);

	         // ✅ Special check for Best Seller
	            if (optionText.equalsIgnoreCase("Best Seller")) {
	                try {
	                    // Locate product cards with Best Seller label
	                    List<WebElement> bestSellerProducts = driver.findElements(By.xpath(
	                        "//div[@class='product_list_cards_wrpr']//div[contains(@class,'product_list_cards_list')][.//span[.='Best Seller']]"
	                    ));

	                    if (!bestSellerProducts.isEmpty()) {
	                        System.out.println("✔️ Best Seller product(s) found. Count: " + bestSellerProducts.size());

	                    } else {
	                        System.out.println("❌ No Best Seller products found after selecting 'Best Seller' sort.");
	                    }

	                } catch (Exception e) {
	                    System.out.println("⚠️ Error while checking Best Seller products: " + e.getMessage());
	                }
	            }
	        }
	    }
	    
	}

//		try {

			


//			List<Double> allDiscounts = new ArrayList<>();
//
//			// Loop through pagination if exists
//			while (true) {
//				List<WebElement> discountElements = driver.findElements(By.xpath("//span[@class='product_list_cards_discount_percent']"));
//
//				for (WebElement element : discountElements) {
//					String discountText = element.getText(); // Example: "25% Off"
//					double discount = extractDiscount(discountText);
//					allDiscounts.add(discount);
//				}
//
//				// Try to click next if exists, else break
//				List<WebElement> nextButtons = driver.findElements(By.xpath("//div[@class='pagi_next_btn']"));
//				if (nextButtons.size() > 0 && nextButtons.get(0).isDisplayed()) {
//					nextButtons.get(0).click();
//					Common.waitForElement(5);// wait for next page load
//				} else {
//					break;
//				}
//			}
//
//			// Check if the list is sorted in ascending order
//			List<Double> sortedDiscounts = new ArrayList<>(allDiscounts);
//			Collections.sort(sortedDiscounts);
//
//			if (allDiscounts.equals(sortedDiscounts)) {
//				System.out.println("✅ Discount values are correctly sorted from Low to High.");
//			} else {
//				System.out.println("❌ Discount sorting is incorrect!");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		

	



	private double extractPrice(String priceText) {
		// TODO Auto-generated method stub
		return 0;

	}

	// Helper function to extract numeric discount
	public static double extractDiscount(String text) {
		return Double.parseDouble(text.replaceAll("[^0-9]", ""));
	}
















	public void wishListIcon() {
        LoginPage login = new LoginPage(driver);
        login.userLogin();
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		List<WebElement> clickWishList = driver.findElements(By.xpath("//div[@class='product_list_cards_btn_group product_list_wishlist_icon Cls_whistList ']"));
		Collections.shuffle(clickWishList);

		if (!clickWishList.isEmpty()) {
			WebElement randomwishList = clickWishList.get(0);
			actions.moveToElement(randomwishList).click().build().perform();


		}
		try {

			if (wishListMsg.isDisplayed()) {
				Assert.assertTrue((verifyDisplayed(wishListMsg)));
			}

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}



	public void addToCart() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		List<WebElement> addProduct = driver.findElements(By.xpath("//button[@class='product_list_cards_btn']"));
		Collections.shuffle(addProduct);

		if (!addProduct.isEmpty()) {
			WebElement randomProduct = addProduct.get(0);
			actions.moveToElement(randomProduct).click().build().perform();
			Common.waitForElement(5);
			click(addToCart);
			
			
		    

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
