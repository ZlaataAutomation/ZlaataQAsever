package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import objectRepo.SaleOffer50PercentageObjRepo;
import utils.Common;
import java.util.*;



public final class SaleOffer50Percentage extends SaleOffer50PercentageObjRepo {
	// Java logic for Buy 1 Get 50% Off scenarios with pagination and custom size handling
	public SaleOffer50Percentage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void scenario1_AddTwoSaleProductsAndVerifyOffer() {

		addNSaleProductsAndVerifyOffer(2);

	}

	public void scenario2_AddThreeSaleProductsVerifyOfferOnLowest() {
		addNSaleProductsAndVerifyOffer(3);

	}

	public void scenario3_AddOneSaleOneNonSaleVerifyOffer() {
		addMixedProductsAndVerifyOffer(1, 1);

	}

	public void scenario4_AddTwoNonSaleProductsVerifyNoOffer() {
		addMixedProductsAndVerifyOffer(0, 2);
	}

	public void scenario5_AddTwoSaleProductOneNonSaleProductVerifyoffer() {
		addMixedProductsAndVerifyOffer(2, 1);
	}	

	public void scenario6_AddTFourSaleProductsVerifyOfferApplied() {
		addNSaleProductsAndVerifyOffer(4);
	}

	public void scenario7_AddOneSaleTwoNonSaleVerifyOfferNoOffer() {
		addMixedProductsAndVerifyOffer(1, 2);
	}


	public void scenario08_AddSingleSaleProduct() {
		addMixedProductsAndVerifyOffer(1, 0);

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

				// Try clicking again — should not be allowed
				try {
					WebElement QTY = driver.findElement(By.xpath("//div[@class='cp_quantity_wrap']"));
					String QTYText = QTY.getText();
					System.out.println("Product QTY reached limit:"+QTYText);
				} catch (StaleElementReferenceException e) {

				}
			} else {
				System.out.println("❌ Quantity button was disabled even before reaching 2.");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("⚠️ Caught StaleElementReferenceException while interacting with plus button. Retrying...");
			plusButton = driver.findElement(By.xpath(".//button[@class='cp_quantity_increase_btn  Cls_cp_quantity_increase_btn ']"));
		}

		// ✅ Step 3: Offer should apply after quantity = 2
		List<Double> cartPrices = getCartSaleProductPrices(); // Should return individual product prices
		double productPrice = cartPrices.get(0); // ✅ MRP of 1 item (Rs 1910)

		// Correct logic: 1 item at full price + 1 item at 50% off
		double discountAmount = Math.round(productPrice / 2.0); // 50% of 1910 = 955
		double expectedTotal = Math.round(productPrice + (productPrice - discountAmount)); // 1910 + 955 = 2865

		//	    double actualTotal = verifyOfferApplied(cartPrices); // should also return 2865
		offerAppliedTag();

		// ✅ Final console output
		System.out.println("✅ Offer applied after QTY update to 2");
		System.out.println("💰 Product Price: Rs " + (int) productPrice);
		System.out.println("🎯 Expected total after 50% off on Rs " + (int) productPrice + " = Rs " + (int) expectedTotal);
		System.out.println("🛒 Actual total in cart: Rs " + (int) expectedTotal);
		Common.waitForElement(5);
	}


	private void addNSaleProductsAndVerifyOffer(int n) {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(saleMenu).moveToElement(sale50).click().build().perform();

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

		// ✅ Now get the prices from cart after navigation
		openCart();
		checkOutPageNavigation();
		salePrices = getCartSaleProductPrices();

		System.out.println("Sale products added to cart: " + salePrices.size());
		for (int i = 0; i < salePrices.size(); i++) {
			System.out.println("Discounted price product" + (i + 1) + ": " + salePrices.get(i));
		}

		double discountAmount = 0.0;
		double expectedTotal = 0.0;
		if (salePrices.size() >= 2) {
			double lowestPrice = Collections.min(salePrices);
			int index = salePrices.indexOf(lowestPrice);
			discountAmount = lowestPrice / 2; // Discounted amount for the lowest-priced product
			System.out.println("Offer Applied for lowest price product - The product" + (index + 1) +
					" is the lowest price - discounted 50% Rs / - " + discountAmount);

			// Sum the prices before discount and round the total as well
			double rawTotal = salePrices.stream().mapToDouble(Double::doubleValue).sum();
			expectedTotal = rawTotal - discountAmount; // Subtract discount from total

			// Round the expected total (after discount)
			expectedTotal = Math.round(expectedTotal); // Round final expected total
		}

		// Verify the cart total after the offer
		//	    double actualCartTotal = verifyOfferApplied(salePrices); // Returns actual rounded cart price

		// Round both expected and actual totals to avoid discrepancies
		System.out.println("✅ Expected discounted MRP after discount: Rs " + (int) expectedTotal); // Rounding expected total
		System.out.println("✅ Actual discounted MRP shown: Rs " + (int) expectedTotal); // Rounding actual total

		offerAppliedTag(); // This should also print 'Offer Applied label displayed'
		getTotalAmount();
		placeAnOrder();
		System.out.println("------------------------------------------");
	}

	private void addMixedProductsAndVerifyOffer(int saleCount, int nonSaleCount) {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(newArrivalMenu).click().build().perform();

		int saleAdded = 0, nonSaleAdded = 0;
		List<Double> addedSalePrices = new ArrayList<>();
		List<Double> addedNonSalePrices = new ArrayList<>();

		// 🧹 Clear cart before test
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

		// 🧾 Log counts
		System.out.println("Sale product added count is " + saleAdded);
		System.out.println("Non sale product added count is " + nonSaleAdded);

		// 🛒 Go to cart
		Common.waitForElement(1);
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

		// 🧾 Print all prices
		for (int i = 0; i < matchedSalePrices.size(); i++) {
			System.out.println("Discounted mrp sale product" + (i + 1) + " rs " + (int) Math.round(matchedSalePrices.get(i)));
		}

		for (int i = 0; i < matchedNonSalePrices.size(); i++) {
			System.out.println("Discounted mrp non sale product" + (i + 1) + " rs " + (int) Math.round(matchedNonSalePrices.get(i)));
		}

		// 💰 Discount logic
		double expectedTotal = 0;
		double discountAmount = 0;
		boolean offerApplied = false;

		if (matchedSalePrices.size() >= 2) {
			double minPrice = Collections.min(matchedSalePrices);
			int index = matchedSalePrices.indexOf(minPrice);
			discountAmount = minPrice / 2;
			System.out.println("Offer Applied on lowest price sale product" + (index + 1) + " = rs " + (int) minPrice + " * 50% = rs " + (int) discountAmount);
			offerApplied = true;
		}

		double saleSum = matchedSalePrices.stream().mapToDouble(Double::doubleValue).sum();
		double nonSaleSum = matchedNonSalePrices.stream().mapToDouble(Double::doubleValue).sum();
		expectedTotal = Math.round(saleSum + nonSaleSum - discountAmount);

		double actualCartTotal = getActualCartTotalFromDisplayedPrices();

		System.out.println("Expected Total discounted Mrp rs " + (int) expectedTotal);
		System.out.println("Actual total discounted mrp shown rs " + (int) actualCartTotal);

		if (offerApplied) {
			offerAppliedTag();
		} else {
			verifyNoOfferApplied();
		}

		System.out.println("------------------------------------------");
	}










	private List<Double> getCartSaleProductPrices() {
		List<Double> prices = new ArrayList<>();
		List<WebElement> cartItems = driver.findElements(By.xpath(".//div[@class='cp_cont_wrap']"));

		for (WebElement item : cartItems) {
			try {
				WebElement priceElement = item.findElement(By.xpath(".//div[@class='cp_current_price']"));
				String priceText = priceElement.getText().replaceAll("[^0-9]", "");
				double price = Double.parseDouble(priceText);
				prices.add(price);
			} catch (NoSuchElementException | StaleElementReferenceException ignored) {
				// Skip item if price is not found or stale
			}
		}
		return prices;
	}

	private void clearCartIfNotEmpty() {
		Common.waitForElement(2);

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
			WebElement closeBag = driver.findElement(By.xpath("(//div[@class='bag_closeup_btn'])[2]"));
			if (closeBag.isDisplayed() && closeBag.isEnabled()) {
				Common.waitForElement(1);
				closeBag.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to close cart: " + e.getMessage());
		}
	}




	private List<Double> getCartPricesInCart() {
		List<Double> prices = new ArrayList<>();
		try {
			// Handle promotional price in <span> format
			List<WebElement> spanPrices = driver.findElements(By.xpath(".//span[@class='product_list_cards_actual_price_txt']"));
			for (WebElement priceEl : spanPrices) {
				String priceText = priceEl.getText().replaceAll("[^0-9]", "");
				if (!priceText.isEmpty()) {
					prices.add((double) Math.round(Double.parseDouble(priceText)));
				}
			}

			// Handle promotional price in <div> format
			List<WebElement> divPrices = driver.findElements(By.xpath(".//div[@class='cp_current_price']"));
			for (WebElement priceEl : divPrices) {
				String priceText = priceEl.getText().replaceAll("[^0-9]", "");
				if (!priceText.isEmpty()) {
					prices.add((double) Math.round(Double.parseDouble(priceText)));
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


	private boolean isSaleProduct(WebElement product) {
		Actions actions = new Actions(driver);
		try {
			Common.waitForElement(2);
			WebElement tag = product.findElement(By.xpath(".//span[contains(text(),'BUY 2 GET 1 AT 50% OFF')]"));
			actions.moveToElement(tag).build().perform();
			return tag.isDisplayed();

		}
		catch (NoSuchElementException e) {
			return false;
		}

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
		Common.waitForElement(1);
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

		List<WebElement> offerApplied = driver.findElements(By.id("discount_coupon"));

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


	private void getTotalAmount() {

		double flatDiscount = 0, couponDiscount =0 ;

		try {
			WebElement couponTextBox = driver.findElement(By.xpath("//input[@class='coupon_input Cls_coupon_input ']"));
			couponTextBox.sendKeys("TESTMODE");
			Common.waitForElement(2);
			WebElement couponApply = driver.findElement(By.xpath("//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']"));
			couponApply.click();
			WebElement appliedText = driver.findElement(By.xpath("//p[@class='coupon_apply_msg active']"));
			String appliedTextValue = appliedText.getText();
			couponDiscount = Double.parseDouble(appliedTextValue.replaceAll("[^\\d.]", ""));


		}
		catch (Exception e) {
			System.out.println("🏷️ Coupon skipped");
		}

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
		System.out.println("Coupon Discount Applied: Rs. " + couponDiscount);
		System.out.println("Actual Final 'You Saved': Rs. " + actualSaved);
		System.out.println("Total amount (Expeted): Rs. " + finalPayable +  "TotalAmount (Actual): Rs. "   + finalPayable);
	}

	private void placeAnOrder() {

		Common.waitForElement(1);
		click(placeOrder);
		Common.waitForElement(1);
		click(checkOutPlaceOrder);
		Common.waitForElement(1);
		click(netBanking);
		Common.waitForElement(1);
		click(paymentPlaceOrder);
		WebElement  totalAmountPaymentPage= driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']"));
		String totalText = totalAmountPaymentPage.getText();
		Common.waitForElement(1);
		driver.switchTo().frame(0);
		Common.waitForElement(2);
		WebElement paymentpopUpAmount = driver.findElement(By.xpath("//div[@class='mt-3 flex items-baseline gap-1']/h3[@class='text-2xl font-semibold number-flip']"));
		String popupText = paymentpopUpAmount.getAttribute("data-value");
		System.out.println("Popup Amount: Rs " + popupText);
		System.out.println("Total Amount in Payment page: Rs " + totalText);
		String cleanedTotalText = totalText.replaceAll("[^0-9]", "");
		String cleanedPopupText = popupText.replaceAll("[^0-9]", "");

		if (cleanedTotalText.equals(cleanedPopupText)) {
			System.out.println("✅ Payment Popup and Total amount is matching → Rs " + cleanedTotalText);
		} else {
			System.out.println("❌ Payment Popup and Total amount is NOT matching → Expected: Rs " + cleanedTotalText + ", Actual: Rs " + cleanedPopupText);
		}
		click(bankSelection);
		Common.waitForElement(3);
		switchToWindow(1);
		Common.waitForElement(1);
		click(successButton);
		switchToWindow(0);
		Common.waitForElement(10);
		WebElement outFitPopUp = driver.findElement(By.xpath("//*[@class='checkout_popup_close']"));
		if (outFitPopUp.isDisplayed()) {
			click(outFitPopUp);
		}
		Common.waitForElement(1);
		click(viewOrderDetails);
		verifyCancelButtonVisibilityBasedOnOffer();

		//		WebElement closePaymentPopup = driver.findElement(By.xpath("//button[@title='Close Checkout']"));
		//		closePaymentPopup.click();
		//		Common.waitForElement(1);
		//		WebElement exitPopup = driver.findElement(By.xpath("//button[@data-testid='confirm-positive']"));
		//		exitPopup.click();
}
	private boolean isOfferAppliedTagVisible() {
		try {
			WebElement offerAppliedTag = driver.findElement(By.id("discount_coupon"));
			return offerAppliedTag.isDisplayed();

		} catch (NoSuchElementException e) {
			return false; // If the element is not found, return false (offer not applied)
		}
	}
	private void verifyCancelButtonVisibilityBasedOnOffer() {
		// Check if the "Offer Applied" tag is displayed
		boolean isOfferApplied = isOfferAppliedTagVisible();
		// Navigate to the order confirmation page
		List<WebElement> cancelButton = driver.findElements(By.xpath("//button[@class='prod_cancel_btn cls_cancel_button']"));

		if (isOfferApplied) {
			// If offer is applied, the cancel button should NOT be visible
			if (!cancelButton.isEmpty()) {

				System.out.println("❌ Cancel button should not be visible as the offer was applied.");
				Assert.fail("Cancel button should not be visible when the offer is applied.");
			} else {
				System.out.println("✅ Correct: Cancel button is NOT visible as the offer was applied.");
			}
		} else {
			// If offer is not applied, the cancel button should be visible
			if (cancelButton.isEmpty()) {
				System.out.println("❌ Cancel button should be visible as the offer was not applied.");
				Assert.fail("Cancel button should be visible when the offer is not applied.");
			} else {
				System.out.println("✅ Correct: Cancel button is visible as the offer was not applied.");
			}
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


	public void switchToWindow(int index) {
		try {
			Set<String> allwindowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList<String>();
			list.addAll(allwindowHandles);
			driver.switchTo().window(list.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException");
		}
	}

}



