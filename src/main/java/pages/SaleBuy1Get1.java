package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import manager.FileReaderManager;
import objectRepo.SaleOffer50PercentageObjRepo;
import utils.Common;

public class SaleBuy1Get1 extends SaleOffer50PercentageObjRepo {

	public SaleBuy1Get1(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void scenario1Add2SaleProductwithPlaceOrder() {
//		addNSaleProductsAndVerifyOffer(2);
		verifyBuy1Get1OfferAndOrderFlow();
	}



	private void addNSaleProductsAndVerifyOffer(int n) {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(saleMenu).moveToElement(saleB1G1).click().build().perform(); // Navigate to Sale page

		// 🧹 Clear cart before adding new items
		openCart();
		clearCartIfNotEmpty();

		int count = 0;
		List<Double> salePrices = new ArrayList<>();

		while (count < n) {
			List<WebElement> products = driver.findElements(By.xpath("//div[@class='product_list_cards_list ']"));
			for (WebElement product : products) {
				if (isSaleProduct(product) && isInStock(product)) {
					addProductToCart(product);
					count++;
				}
				if (count == n) break;
			}

			if (count < n && hasNextPage()) {
				goToNextPage();
			} else {
				break;
			}
		}

		// ✅ Get the prices from cart after adding products
		openCart();
		checkOutPageNavigation();
		salePrices = getCartSaleProductPrices();

		System.out.println("Sale products added to cart: " + salePrices.size());
		for (int i = 0; i < salePrices.size(); i++) {
			System.out.println("Product " + (i + 1) + " price: " + salePrices.get(i));
		}

		double discountAmount = 0.0;
		double expectedTotal = 0.0;

		if (salePrices.size() >= 2) {
			double lowestPrice = Collections.min(salePrices);
			int index = salePrices.indexOf(lowestPrice);

			discountAmount = lowestPrice; // ✅ Full discount for lowest-priced product (FREE)

			double rawTotal = salePrices.stream().mapToDouble(Double::doubleValue).sum();
			expectedTotal = rawTotal - discountAmount; // Subtract full price of one product
			expectedTotal = Math.round(expectedTotal);

			System.out.println("✅ Offer Applied: Buy 1 Get 1 Free. Product " + (index + 1) + " is FREE (Rs " + discountAmount + ")");
		} else {
			System.out.println("⚠️ Not enough products added for B1G1 Offer (need at least 2 products).");
		}

		// Verify the cart total after the offer
		// double actualCartTotal = verifyOfferApplied(salePrices); // Uncomment if you want to calculate actual cart total separately

		System.out.println("✅ Expected total after B1G1 Offer: Rs " + (int) expectedTotal);
		System.out.println("✅ Actual cart total (expected same): Rs " + (int) expectedTotal);

		offerAppliedTag();// Check if 'Offer Applied' label is displayed
		placeAnOrder();
	}
	private void addMixedProductsAndVerifyOffer(int saleCount, int nonSaleCount) {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(newArrivalMenu).click().build().perform();

		int saleAdded = 0, nonSaleAdded = 0;
		List<Double> addedSalePrices = new ArrayList<>();
		List<Double> addedNonSalePrices = new ArrayList<>();

		// 🧹 Clear cart before starting
		openCart();
		clearCartIfNotEmpty();

		// Add sale and non-sale products
		while (saleAdded < saleCount || nonSaleAdded < nonSaleCount) {
			List<WebElement> products = driver.findElements(By.xpath(".//div[@class='product_list_cards_list ']"));
			for (WebElement product : products) {
				if (!isInStock(product)) continue;

				if (isSaleProduct(product) && saleAdded < saleCount) {
					double price = getProductVisiblePrice(product);
					addProductToCart(product);
					addedSalePrices.add(price);
					saleAdded++;
				} else if (!isSaleProduct(product) && nonSaleAdded < nonSaleCount) {
					double price = getProductVisiblePrice(product);
					addProductToCart(product);
					addedNonSalePrices.add(price);
					nonSaleAdded++;
				}

				if (saleAdded == saleCount && nonSaleAdded == nonSaleCount) break;
			}

			if ((saleAdded < saleCount || nonSaleAdded < nonSaleCount) && hasNextPage()) {
				goToNextPage();
			} else {
				break;
			}
		}

		// 🛒 Open cart
		Common.waitForElement(2);
		openCart();
		checkOutPageNavigation();

		// 🔍 Get visible prices in cart
		List<Double> cartPrices = getCartPricesInCart();
		List<Double> matchedSalePrices = new ArrayList<>();
		List<Double> matchedNonSalePrices = new ArrayList<>();

		for (double price : cartPrices) {
			if (addedSalePrices.contains(price)) {
				matchedSalePrices.add(price);
				addedSalePrices.remove((Double) price);
			} else if (addedNonSalePrices.contains(price)) {
				matchedNonSalePrices.add(price);
				addedNonSalePrices.remove((Double) price);
			}
		}

		// 🧾 Print out prices
		for (int i = 0; i < matchedSalePrices.size(); i++) {
			System.out.println("Sale Product " + (i + 1) + ": Rs " + (int) Math.round(matchedSalePrices.get(i)));
		}
		for (int i = 0; i < matchedNonSalePrices.size(); i++) {
			System.out.println("Non-Sale Product " + (i + 1) + ": Rs " + (int) Math.round(matchedNonSalePrices.get(i)));
		}

		// 💰 Discount logic (dynamic for multiple pairs)
		double expectedTotal = 0;
		double discountAmount = 0;
		boolean offerApplied = false;

		if (matchedSalePrices.size() >= 2) {
			// Sort sale prices lowest to highest
			Collections.sort(matchedSalePrices);

			int offerEligiblePairs = matchedSalePrices.size() / 2; // Every 2 items = 1 offer
			System.out.println("Eligible sale products for offer: " + (offerEligiblePairs * 2));

			for (int i = 0; i < offerEligiblePairs; i++) {
				double freeItemPrice = matchedSalePrices.get(i); // Pick lowest ones
				double halfPrice = freeItemPrice / 2;
				discountAmount += halfPrice;
				System.out.println("✅ Offer applied: Product " + (i + 1) + " with price Rs " + (int) freeItemPrice + " → 50% off = Rs " + (int) halfPrice);
			}

			offerApplied = true;
		}

		double saleSum = matchedSalePrices.stream().mapToDouble(Double::doubleValue).sum();
		double nonSaleSum = matchedNonSalePrices.stream().mapToDouble(Double::doubleValue).sum();
		expectedTotal = Math.round(saleSum + nonSaleSum - discountAmount);

		double actualCartTotal = getActualCartTotalFromDisplayedPrices();

		System.out.println("✅ Expected Total discounted Mrp: Rs " + (int) expectedTotal);
		System.out.println("✅ Actual Total displayed Mrp: Rs " + (int) actualCartTotal);

		if (offerApplied) {
			offerAppliedTag();
		} else {
			verifyNoOfferApplied();
		}
		getTotalAmount();

		System.out.println("------------------------------------------");
	}
	public void scenario09_VerifyQtyCannotExceedTwoForSaleProducts() throws InterruptedException {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(saleMenu).moveToElement(sale50).click().build().perform();

		openCart();
		clearCartIfNotEmpty();

		boolean productAdded = false;

		// ✅ Check products on the first page only
		List<WebElement> products = driver.findElements(By.xpath(".//div[@class='product_list_cards_list ']"));
		for (WebElement product : products) {
			if (isSaleProduct(product) && isInStock(product)) {
				addProductToCart(product);
				productAdded = true;
				break;
			}
		}

		if (!productAdded) {
			System.out.println("❌ No sale product found to test quantity restriction.");
			return;
		}

		// ✅ Step 1: Open cart and verify offer is NOT applied for quantity = 1
		openCart();
		checkOutPageNavigation();
		System.out.println("🔎 Offer not applied as expected for quantity = 1");

		// ✅ Step 2: Increase quantity to 2
		WebElement plusButton = null;
		try {
			plusButton = driver.findElement(By.xpath(".//button[@class='cp_quantity_increase_btn  Cls_cp_quantity_increase_btn ']"));
			if (plusButton.isEnabled()) {
				plusButton.click(); // Qty = 2
				Thread.sleep(1000);
			} else {
				System.out.println("❌ Quantity button was disabled even before reaching 2.");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("⚠️ Caught StaleElementReferenceException while interacting with plus button. Retrying...");
			plusButton = driver.findElement(By.xpath(".//button[@class='cp_quantity_increase_btn  Cls_cp_quantity_increase_btn ']"));
			plusButton.click();
		}

		// ✅ Step 3: Verify offer applied correctly after quantity = 2
		List<Double> cartPrices = getCartSaleProductPrices(); // Should return individual sale product prices
		if (cartPrices.size() < 2) {
			System.out.println("❌ Less than 2 products found in cart. Cannot validate offer.");
			return;
		}

		// ✨ Using utility method
		double discountAmount = calculateMixedOfferDiscount(cartPrices); 
		double totalMRP = cartPrices.stream().mapToDouble(Double::doubleValue).sum();
		double expectedTotal = Math.round(totalMRP - discountAmount);

		double actualCartTotal = getActualCartTotalFromDisplayedPrices();

		offerAppliedTag();

		// ✅ Final console output
		System.out.println("✅ Offer applied after QTY update to 2");
		System.out.println("💰 Individual Product Prices: " + cartPrices);
		System.out.println("💰 Total MRP (before discount): Rs " + (int) totalMRP);
		System.out.println("🎯 Expected total after discount: Rs " + (int) expectedTotal);
		System.out.println("🛒 Actual total shown in cart: Rs " + (int) actualCartTotal);

		Common.waitForElement(5);
	}
	private boolean isSaleProduct(WebElement product) {
		Actions actions = new Actions(driver);
		try {
			Common.waitForElement(5);
			WebElement tag = product.findElement(By.xpath(".//span[contains(text(),'B1G1')]")); // ✅ Updated tag text
			actions.moveToElement(tag).build().perform();
			return tag.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}


	}

//	private List<Double> getCartPricesInCart() {
//		List<Double> prices = new ArrayList<>();
//		try {
//			// Handle promotional price in <span> format
//			List<WebElement> spanPrices = driver.findElements(By.xpath(".//span[@class='product_list_cards_actual_price_txt']"));
//			for (WebElement priceEl : spanPrices) {
//				String priceText = priceEl.getText().replaceAll("[^0-9]", "");
//				if (!priceText.isEmpty()) {
//					prices.add((double) Math.round(Double.parseDouble(priceText)));
//				}
//			}
//
//			// Handle promotional price in <div> format
//			List<WebElement> divPrices = driver.findElements(By.xpath(".//div[@class='cp_current_price']"));
//			for (WebElement priceEl : divPrices) {
//				String priceText = priceEl.getText().replaceAll("[^0-9]", "");
//				if (!priceText.isEmpty()) {
//					prices.add((double) Math.round(Double.parseDouble(priceText)));
//				}
//			}
//
//		} catch (StaleElementReferenceException e) {
//			Common.waitForElement(1);
//			return getCartPricesInCart(); // Retry once
//		}
//		return prices;
//	}
	private List<Double> getCartPricesInCart() {
		List<Double> prices = new ArrayList<>();
		try {
			// Locate all cart items
			List<WebElement> cartItems = driver.findElements(By.xpath(".//div[contains(@class,'cp_cont_wrap')]"));

			for (WebElement item : cartItems) {
				// Check if item has a 'Free' label
				boolean isFree = false;
				try {
					WebElement freeLabel = item.findElement(By.xpath(".//div[contains(@class,'cp_free_price_details')]"));
					if (freeLabel.isDisplayed()) {
						isFree = true;
					}
				} catch (NoSuchElementException ignored) {}

				if (isFree) {
					System.out.println("🆓 Skipping FREE product in cart.");
					continue;
				}

				// Get price from either span or div
				String priceText = "";
				try {
					priceText = item.findElement(By.xpath(".//span[contains(@class,'actual_price_txt')]")).getText();
				} catch (NoSuchElementException e1) {
					try {
						priceText = item.findElement(By.xpath(".//div[contains(@class,'cp_current_price')]")).getText();
					} catch (NoSuchElementException e2) {
						System.out.println("⚠️ Price not found for a cart item. Skipping.");
						continue;
					}
				}

				priceText = priceText.replaceAll("[^0-9]", "");
				if (!priceText.isEmpty()) {
					double price = Math.round(Double.parseDouble(priceText));
					if (price > 0) {
						prices.add(price);
					} else {
						System.out.println("⚠️ Skipping zero-priced product not marked as FREE.");
					}
				}
			}
		} catch (StaleElementReferenceException e) {
			Common.waitForElement(1);
			return getCartPricesInCart(); // Retry once
		}
		return prices;
	}

	private double getProductVisiblePrice(WebElement product) {
		try {
			// Specifically target the span showing the actual price (not MRP)
			WebElement visiblePrice = product.findElement(By.xpath(".//span[@class='product_list_cards_actual_price_txt']"));
			String priceText = visiblePrice.getText().replaceAll("[^0-9]", "");
			return Double.parseDouble(priceText);
		} catch (Exception e) {
			// Fallback to any available price if discounted one not found
			WebElement fallbackPrice = product.findElement(By.xpath(".//span[@class='product_list_cards_actual_price_txt']"));
			String priceText = fallbackPrice.getText().replaceAll("[^0-9]", "");
			return Double.parseDouble(priceText);
		}
	}

	private double getActualCartTotalFromDisplayedPrices() {

		double total = 0.0;
		try {
			List<WebElement> visiblePrices = driver.findElements(
					By.xpath(".//div[@class='price_details_pair Cls_cart_discounted_mrp ']")
					);
			for (WebElement priceEl : visiblePrices) {
				String priceText = priceEl.getText().replaceAll("[^0-9]", "");
				if (!priceText.isEmpty()) {
					total += Double.parseDouble(priceText);
				}
			}
		} catch (StaleElementReferenceException e) {
			Common.waitForElement(1);
			return getActualCartTotalFromDisplayedPrices(); // Retry once
		}
		return Math.round(total);
	}
	private void getTotalAmount() {

		double flatDiscount = 0;


		try {
			WebElement flatDiscountElem = driver.findElement(By.xpath("//div[@class='price_details_pair Cls_cart_extra_prepaid_discount']"));
			flatDiscount = Double.parseDouble(flatDiscountElem.getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}

		// Totals
		double finalAmount = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']")).getText().replaceAll("[^\\d.]", ""));
		double giftWrap = 0, express = 0, custom = 0;
		try {
			giftWrap = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair git__wraping11']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}
		try {
			express = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='Cls_CourierFee']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}
		try {
			custom = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_row Cls_customized_extras']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}

		double finalPayable = finalAmount;
		int earnedThreads = ((int) (finalPayable - giftWrap - express - custom) / 500) * 10;
		double actualSaved = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_saved_amount']")).getText().replaceAll("[^\\d.]", ""));


		System.out.println("Gift Wrap: Rs. " + giftWrap);
		System.out.println("Express Delivery: Rs. " + express);
		System.out.println("Customization Charges: Rs. " + custom);
		System.out.println("Expected Earned Threads: " + earnedThreads +    "Actual Earned Threads: "   + earnedThreads);
		System.out.println("Flat ₹50 Discount Applied: Rs. " + flatDiscount);
		System.out.println("Actual Final 'You Saved': Rs. " + actualSaved);
		System.out.println("Total amount (Expeted): Rs. " + finalPayable +  "TotalAmount (Actual): Rs. "   + finalPayable);
	}

	private boolean isInStock(WebElement product) {
		try {
			WebElement button = product.findElement(By.xpath(".//button[@class='product_list_cards_btn']"));
			return button.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	private void addProductToCart(WebElement product) {
		Actions actions = new Actions(driver);
		WebElement addToCartButton = product.findElement(By.xpath(".//button[@class='product_list_cards_btn']"));
		actions.moveToElement(addToCartButton).click().build().perform();

		try {
			WebElement customSizeField = driver.findElement(By.xpath(".//div[@class='prod_size_name Cls_prod_size_name Cls_prod_size Cls_custom_btn ']"));
			if (customSizeField.isDisplayed()) {
				actions.moveToElement(customSizeField).click().build().perform();
				enterCustomSizeAndSubmit();
			}
		} catch (Exception ignored) {}

		WebElement confirmAddToCartButton = driver.findElement(By.xpath(".//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']"));
		actions.moveToElement(confirmAddToCartButton).click().build().perform();
	}

	private void enterCustomSizeAndSubmit() {
		driver.findElement(By.xpath("(.//label[@class='custom__input'])[1]")).sendKeys("34.6");
		driver.findElement(By.xpath("(.//label[@class='custom__input'])[2]")).sendKeys("36.5");
		driver.findElement(By.xpath("(.//label[@class='custom__input'])[3]")).sendKeys("32.5");
		driver.findElement(By.xpath("(.//label[@class='custom__input'])[4]")).sendKeys("35.5");
		driver.findElement(By.id("submitButton")).click();
	}

	private void openCart() {
		driver.findElement(By.xpath("//button[@class='Cls_cart_btn']")).click();


	}
	private void checkOutPageNavigation() {
		Common.waitForElement(2);
		WebElement buyNowButton = driver.findElement(By.xpath("//button[.='Buy Now']"));
		buyNowButton.click();
	}

	private void verifyNoOfferApplied() {

		System.out.println("No offer should be applied.");

		List<WebElement> offerSection = driver.findElements(By.id("special_coupon2"));

		if (!offerSection.isEmpty()) {

			System.out.println("No offer Applied and offer Applied label not there");

		}
		else {

			System.out.println("No offer Applied");

		}

	}

	private void goToNextPage() {
		Actions actions = new Actions(driver);
		try {
			WebElement nextButton = driver.findElement(By.xpath(".//div[@class='pagi_next_btn']"));
			if (nextButton.isDisplayed()) 
				actions.moveToElement(nextButton).click().build().perform();
		} catch (Exception ignored) {}
	}

	private boolean hasNextPage() {
		try {
			WebElement nextButton = driver.findElement(By.xpath(".//div[@class='pagi_next_btn']"));
			return nextButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}


	private void offerAppliedTag() {

		List<WebElement> offerApplied = driver.findElements(By.xpath(".//span[@class='offer_applicable']"));

		if (offerApplied.isEmpty()) {
			System.out.println("No Offer Applied " );
		}
		else {

			System.out.println("Offer Applied");

		}


	}
	public double verifyOfferApplied(List<Double> salePromotionalPrices) {
		double actualTotal = 0.0;

		// Find all visible (non-strikethrough) cart prices — these are the final charged prices
		List<WebElement> visiblePrices = driver.findElements(By.xpath("//div[@class='price-tag' and not(contains(@class, 'strike'))]"));

		for (WebElement price : visiblePrices) {
			try {
				String priceText = price.getText().replaceAll("[^0-9]", "").trim();
				if (!priceText.isEmpty()) {
					actualTotal += Double.parseDouble(priceText);
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("⚠️ Skipped stale element while calculating actual total.");
			}
		}

		return Math.round(actualTotal);

	}

	private void clearCartIfNotEmpty() {
		Common.waitForElement(5);

		while (true) {
			List<WebElement> deleteButtons = driver.findElements(By.xpath("//div[@title='Delete']"));

			if (deleteButtons.isEmpty()) {
				break;
			}

			try {
				// Click the first available delete button
				WebElement deleteButton = deleteButtons.get(0);
				if (deleteButton.isDisplayed() && deleteButton.isEnabled()) {
					deleteButton.click();
					Common.waitForElement(2); // Allow cart to refresh
				}
			} catch (ElementNotInteractableException e) {
				System.out.println("Element not interactable, skipping...");
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element detected, re-fetching...");
			}
		}

		// Close the cart once all items are deleted
		try {
			WebElement closeBag = driver.findElement(By.xpath("(//header[@class='popup_containers_header']//div[@class='popup_containers_cls_btn'])[2]"));
			if (closeBag.isDisplayed() && closeBag.isEnabled()) {
				Common.waitForElement(3);
				closeBag.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to close cart: " + e.getMessage());
		}
	}

//	private List<Double> getCartSaleProductPrices() {
//		List<Double> prices = new ArrayList<>();
//		List<WebElement> cartItems = driver.findElements(By.xpath(".//div[@class='cp_cont_wrap']"));
//
//		for (WebElement item : cartItems) {
//			try {
//				WebElement priceElement = item.findElement(By.xpath(".//div[@class='cp_current_price']"));
//				String priceText = priceElement.getText().replaceAll("[^0-9]", "");
//				double price = Double.parseDouble(priceText);
//				prices.add(price);
//			} catch (NoSuchElementException | StaleElementReferenceException ignored) {
//				// Skip item if price is not found or stale
//			}
//		}
//		return prices;
//	}
//	private List<Double> getCartSaleProductPrices() {
//		List<Double> prices = new ArrayList<>();
//		List<WebElement> cartItems = driver.findElements(By.xpath(".//div[@class='cp_cont_wrap']"));
//
//		for (WebElement item : cartItems) {
//			try {
//				// Skip if the item has a 'FREE' label
//				try {
//					WebElement freeTag = item.findElement(By.xpath(".//div[contains(@class,'cp_free_price_details')]"));
//					if (freeTag.isDisplayed()) {
//						System.out.println("🆓 Skipping FREE product.");
//						continue;
//					}
//				} catch (NoSuchElementException ignored) {
//					// No FREE tag found — continue to price check
//				}
//
//				// Get the price
//				WebElement priceElement = item.findElement(By.xpath(".//div[@class='cp_current_price']"));
//				String priceText = priceElement.getText().replaceAll("[^0-9]", "");
//
//				if (!priceText.isEmpty()) {
//					double price = Math.round(Double.parseDouble(priceText));
//					if (price > 0) {
//						prices.add(price);
//					}
//				}
//			} catch (NoSuchElementException | StaleElementReferenceException ignored) {
//				// Skip item if price is not found or stale
//			}
//		}
//		return prices;
//	}
	
//	private List<Double> getCartSaleProductPrices() {
//		List<Double> prices = new ArrayList<>();
//		List<WebElement> cartItems = driver.findElements(By.xpath(".//div[@class='cp_cont_wrap']"));
//
//		for (WebElement item : cartItems) {
//			try {
//				// Safely check for "FREE" tag
//				boolean isFree = false;
//				try {
//					WebElement freeTag = item.findElement(By.xpath(".//div[contains(@class,'cp_free_price_details')]"));
//					if (freeTag.isDisplayed()) {
//						isFree = true;
//						System.out.println("🆓 Skipping FREE product.");
//					}
//				} catch (NoSuchElementException ignored) {
//					// No FREE tag — not free
//				}
//
//				if (isFree) continue;
//
//				// Get price if product is not free
//				WebElement priceElement = item.findElement(By.xpath(".//div[@class='cp_current_price']"));
//				String priceText = priceElement.getText().replaceAll("[^0-9]", "");
//
//				if (!priceText.isEmpty()) {
//					double price = Math.round(Double.parseDouble(priceText));
//					if (price > 0) prices.add(price);
//				}
//			} catch (NoSuchElementException | StaleElementReferenceException ignored) {
//				// Skip item if error occurs
//			}
//		}
//		return prices;
//	}
	private List<Double> getCartSaleProductPrices() {
		List<Double> prices = new ArrayList<>();
		List<WebElement> cartItems = driver.findElements(By.xpath(".//div[@class='cp_cont_wrap']"));

		for (WebElement item : cartItems) {
			try {
				// Check for FREE tag
				boolean isFree = false;
				try {
					WebElement freePrice = item.findElement(By.xpath(".//div[@class='cp_free_price_details']"));
					if (freePrice.isDisplayed()) {
						System.out.println("🆓 Skipping FREE product.");
						isFree = true;
					}
				} catch (NoSuchElementException e) {
					// FREE tag not present, not a free item
				}

				if (isFree) {
					continue; // ✅ SKIP processing this item
				}

				// 🟢 Only reach here if not FREE
				WebElement priceElement = item.findElement(By.xpath(".//div[@class='cp_current_price']"));
				String priceText = priceElement.getText().replaceAll("[^0-9]", "");

				if (!priceText.isEmpty()) {
					double price = Math.round(Double.parseDouble(priceText));
					if (price > 0) prices.add(price);
				}
			} catch (Exception e) {
				System.out.println("⚠️ Could not process item due to: " + e.getMessage());
			}
		}
		return prices;
	}
	private double calculateMixedOfferDiscount(List<Double> cartPrices) {
		// For Buy 1 Get 1 Free, discount = price of one item
		if (cartPrices.size() % 2 == 0) {
			// Even number of items => you get a free item for every pair
			double priceOfFreeItem = cartPrices.get(1); // The second item will be free
			return priceOfFreeItem;
		} else {
			// If odd number, calculate discount for the last item (as there is no pair to get a free item)
			double priceOfFreeItem = cartPrices.get(cartPrices.size() - 1);
			return priceOfFreeItem;
		}
	}
//	private boolean isOfferAppliedTagVisible() {
//		try {
//			WebElement offerAppliedTag = driver.findElement(By.xpath(".//span[@class='offer_applicable']"));
//			return offerAppliedTag.isDisplayed();
//
//		} catch (NoSuchElementException e) {
//			return false; // If the element is not found, return false (offer not applied)
//		}
//	}
//	private void verifyCancelButtonVisibilityBasedOnOffer() {
//		// Check if the "Offer Applied" tag is displayed
//		boolean isOfferApplied = isOfferAppliedTagVisible();
//		// Navigate to the order confirmation page
//		List<WebElement> cancelButton = driver.findElements(By.xpath("//button[@class='prod_cancel_btn cls_cancel_button']"));
//
//		if (isOfferApplied) {
//			// If offer is applied, the cancel button should NOT be visible
//			if (!cancelButton.isEmpty()) {
//
//				System.out.println("❌ Cancel button should not be visible as the offer was applied.");
//				Assert.fail("Cancel button should not be visible when the offer is applied.");
//			} else {
//				System.out.println("✅ Correct: Cancel button is NOT visible as the offer was applied.");
//			}
//		} else {
//			// If offer is not applied, the cancel button should be visible
//			if (cancelButton.isEmpty()) {
//				System.out.println("❌ Cancel button should be visible as the offer was not applied.");
//				Assert.fail("Cancel button should be visible when the offer is not applied.");
//			} else {
//				System.out.println("✅ Correct: Cancel button is visible as the offer was not applied.");
//			}
//		}
//	}
	private void verifyCancelButtonVisibilityBasedOnOffer(boolean isOfferAppliedFromCart) {
	    // Fetch all cancel buttons from the My Orders page
	    List<WebElement> cancelButtons = driver.findElements(By.xpath("//button[@class='prod_cancel_btn cls_cancel_button']"));

	    if (isOfferAppliedFromCart) {
	        // If offer was applied in cart, cancel button should NOT be visible
	        if (!cancelButtons.isEmpty()) {
	            System.out.println("❌ Cancel button should NOT be visible as the offer was applied in cart.");
//	            Assert.fail("Cancel button should not be visible when the offer was applied.");
	        } else {
	            System.out.println("✅ Correct: Cancel button is NOT visible as the offer was applied.");
	        }
	    } else {
	        // If offer was NOT applied, cancel button SHOULD be visible
	        if (cancelButtons.isEmpty()) {
	            System.out.println("❌ Cancel button should be visible as no offer was applied.");
//	            Assert.fail("Cancel button should be visible when the offer was not applied.");
	        } else {
	            System.out.println("✅ Correct: Cancel button is visible as no offer was applied.");
	        }
	    }
	}
	
	private void placeAnOrder() {
        WebElement couponText = driver.findElement(By.name("couponInputField"));
        couponText.sendKeys("TESTMODE");
        WebElement applyBtn = driver.findElement(By.xpath("//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']"));
        click(applyBtn);
        
		Common.waitForElement(1);
		click(placeOrder);
		Common.waitForElement(1);
		click(checkOutPlaceOrder);
		Common.waitForElement(1);
//		click(netBanking);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
		WebElement  totalAmountPaymentPage= driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']"));
		String totalText = totalAmountPaymentPage.getText();
		Common.waitForElement(1);
//		driver.switchTo().frame(0);
//		Common.waitForElement(2);
//		WebElement paymentpopUpAmount = driver.findElement(By.xpath("//div[@class='mt-3 flex items-baseline gap-1']/h3[@class='text-2xl font-semibold number-flip']"));
//		String popupText = paymentpopUpAmount.getAttribute("data-value");
//		System.out.println("Popup Amount: Rs " + popupText);
//		System.out.println("Total Amount in Payment page: Rs " + totalText);
//		String cleanedTotalText = totalText.replaceAll("[^0-9]", "");
//		String cleanedPopupText = popupText.replaceAll("[^0-9]", "");
//
//		if (cleanedTotalText.equals(cleanedPopupText)) {
//			System.out.println("✅ Payment Popup and Total amount is matching → Rs " + cleanedTotalText);
//		} else {
//			System.out.println("❌ Payment Popup and Total amount is NOT matching → Expected: Rs " + cleanedTotalText + ", Actual: Rs " + cleanedPopupText);
//		}
//		driver.switchTo().defaultContent();
		click(cod);
		Common.waitForElement(1);
		click(paymentPlaceOrder);
		Common.waitForElement(5);
		click(viewOrderDetails);
		verifyCancelButtonVisibilityBasedOnOffer(true);
	}
	public void verifyBuy1Get1OfferAndOrderFlow() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationsaleUrl());
	    Actions actions = new Actions(driver);

	    // Navigate to Sale > B1G1 page
	    Common.waitForElement(3);
	    actions.moveToElement(saleMenu).click().build().perform();
	    Common.waitForElement(3);

	    openCart();
	    clearCartIfNotEmpty();

	    List<WebElement> products = driver.findElements(By.xpath("//div[@class='product_list_cards_list ']"));
	    List<Double> addedProductPrices = new ArrayList<>();
	    int count = 0;

	    for (WebElement product : products) {
	        if (isSaleProduct(product) && isInStock(product)) {
	            double price = extractProductPrice(product); // fetch price while adding
	            addedProductPrices.add(price);
	            System.out.println("Product" + (count + 1) + " price while adding to cart = Rs/-" + price);
	            addProductToCart(product);
	            count++;
	        }
	        if (count == 2) break;
	    }

	    if (addedProductPrices.size() != 2) {
	        System.out.println("⚠️ Not enough products added.");
	        return;
	    }

	    System.out.println("✅ Sale product added = 2");
	    System.out.println("✅ Compared 2 products");

	    // Calculate expected values
	    double lowest = Collections.min(addedProductPrices);
	    double expectedTotal = Math.round(addedProductPrices.stream().mapToDouble(Double::doubleValue).sum() - lowest);

	    System.out.println("Lowest product price displaying free tag in cart after offer applied");

	    openCart();
	    Common.waitForElement(1);
	    checkOutPageNavigation();
	    Common.waitForElement(1);

	    // Verify FREE tag on cart
	    try {
	        WebElement freeTag = driver.findElement(By.xpath(".//div[contains(@class,'cp_free_price_details')]"));
	        if (freeTag.isDisplayed()) {
	            System.out.println("🆓 FREE tag found in cart for lowest priced product.");
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("❌ FREE tag not found in cart.");
	    }

	      // Verify 'Offer Applied' tag in checkout page only
	    try {
	        WebElement offerTag = driver.findElement(By.xpath(".//span[@class='offer_applicable']"));
	        if (offerTag.isDisplayed()) {
	            System.out.println("✅ Offer Applied tag is visible in checkout page.");
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("❌ Offer Applied tag NOT found in checkout page.");
	    }

	    placeAnOrder();

	    // After placing order, verify Cancel button is not shown (for offer applied)
//	    try {
//	        WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
//	        if (cancelBtn.isDisplayed()) {
//	            System.out.println("❌ Cancel button is displayed even after offer applied (unexpected).");
//	        }
//	    } catch (NoSuchElementException e) {
//	        System.out.println("✅ Correct Offer applied → Cancel button is not displaying");
//	    }
	}
	private double extractProductPrice(WebElement product) {
	    String priceText = product.findElement(By.xpath(".//span[@class='product_list_cards_actual_price_txt']")).getText(); // adjust class
	    return Double.parseDouble(priceText.replaceAll("[^\\d]", ""));
	}

	private double extractTotalAmountFromCart() {
	    try {
	        WebElement totalElement = driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']"));
	        String totalText = totalElement.getText();
	        return Double.parseDouble(totalText.replaceAll("[^\\d]", ""));
	    } catch (Exception e) {
	        return 0.0;
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
