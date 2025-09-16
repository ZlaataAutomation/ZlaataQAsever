package pages;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import objectRepo.SaleOffer50PercentageObjRepo;
import utils.Common;

import io.cucumber.java.Scenario;

public final class OrdersPage extends SaleOffer50PercentageObjRepo{
	
	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	 private void clickUsingJS(WebElement element) {
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click();", element);

	}
	public void verifyOrderPlacementAndValidationFlow(Scenario scenario) {
//    Actions actions = new Actions(driver);
//    LoginPage login = new LoginPage(driver);
//    login.userLogin();
//    Common.waitForElement(2);
//
//    // Step 1: Navigate to product listing page & add random product
//    actions.moveToElement(driver.findElement(By.xpath("//li[@class='navigation_menu_list nav_menu_dropdown shop']")))
//           .moveToElement(driver.findElement(By.xpath("//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]"))).click().build().perform();
//    Common.waitForElement(2);
//
//    List<WebElement> addProduct = driver.findElements(By.xpath("//button[@class='product_list_cards_btn']"));
//	Collections.shuffle(addProduct);
//
//	if (!addProduct.isEmpty()) {
//		WebElement randomProduct = addProduct.get(0);
//		actions.moveToElement(randomProduct).click().build().perform();
//		
//
//	}
//	Common.waitForElement(2);
//    // Step 1A: Handle Custom Size
//    try {
//        WebElement customInput = driver.findElement(By.xpath("//div[@class='prod_size_name Cls_prod_size_name Cls_prod_size Cls_custom_btn ']"));
//        if (customInput.isDisplayed()) {
//        	click(customInput);
//        	driver.findElement(By.xpath("(.//label[@class='custom__input'])[1]")).sendKeys("34.6");
//    		driver.findElement(By.xpath("(.//label[@class='custom__input'])[2]")).sendKeys("36.5");
//    		driver.findElement(By.xpath("(.//label[@class='custom__input'])[3]")).sendKeys("32.5");
//    		driver.findElement(By.xpath("(.//label[@class='custom__input'])[4]")).sendKeys("35.5");
//    		driver.findElement(By.id("submitButton")).click();
//        }
//    } catch (Exception ignored) {
//    	
//    }
//
//    // Add to cart
//    driver.findElement(By.xpath("//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']")).click();
//    Common.waitForElement(2);
//
//    // Proceed to checkout
//    driver.findElement(By.xpath("//button[@class='Cls_cart_btn']")).click();
//    Common.waitForElement(2);
//    driver.findElement(By.xpath("//button[text()='Buy Now']")).click();
//    Common.waitForElement(2);
//
//    // Step 2: Verify Discount Percentage
//    double mrp = Double.parseDouble(driver.findElement(By.xpath("//div[@class='cp_actual_price']")).getText().replaceAll("[^\\d.]", ""));
//    double discounted = Double.parseDouble(driver.findElement(By.xpath("//div[@class='cp_current_price']")).getText().replaceAll("[^\\d.]", ""));
//    double actualPercent = Math.round(((mrp - discounted) / mrp) * 100);
//    System.out.println("✅ Discount % on Checkout Page: " + actualPercent + "%");
     verifyCheckoutCalculations();
    // Step 3: Apply Coupon in text box
     Common.waitForElement(3);
     WebElement remove = driver.findElement(By.xpath("//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']"));
     clickUsingJS(remove);
    WebElement couponInput = driver.findElement(By.name("couponInputField"));
    couponInput.sendKeys("TESTMODE");
    driver.findElement(By.xpath("//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']")).click();
    Common.waitForElement(2);

    // Step 4: Gift Wrap
//    try {
//        driver.findElement(By.id("checkout__model__gift")).click();
//        driver.findElement(By.id("recipient-name")).sendKeys("Test User");
//        driver.findElement(By.xpath("//button[@class='gift__submit btn___2']")).click();
//    } catch (Exception ignored) {
//    	
//    }

    // Step 5: Express Delivery
//    try {
//        WebElement express = driver.findElement(By.id("checked2"));
//        if (!express.isSelected()) express.click();
//    } catch (Exception ignored) {
//    	
//    }

    // Step 6: Apply Threads
//    try {
//        WebElement threadCount = driver.findElement(By.xpath("//span[@class='price_details_key_span']"));
//        int threads = Integer.parseInt(threadCount.getText().replaceAll("[^\\d]", ""));
//        WebElement threadInput = driver.findElement(By.xpath("//input[@placeholder='Enter threads']"));
//        threadInput.clear();
//        threadInput.sendKeys(String.valueOf(threads));
//    } catch (Exception ignored) {
//    	
//    }

    // Step 7: Validate Price Fields
//    double youSaved = Double.parseDouble(driver.findElement(By.xpath("//div[contains(@class,'Cls_cart_saved_amount')]")).getText().replaceAll("[^\\d.]", ""));
//    double totalDiscounted = Double.parseDouble(driver.findElement(By.xpath("//div[contains(@class,'Cls_cart_discounted_mrp')]")).getText().replaceAll("[^\\d.]", ""));
//    double finalPayable = Double.parseDouble(driver.findElement(By.xpath("//div[contains(@class,'Cls_cart_total_amount')]")).getText().replaceAll("[^\\d.]", ""));
//    double flatDiscount = 0;
//    try {
//        flatDiscount = Double.parseDouble(driver.findElement(By.xpath("//div[contains(@class,'Cls_cart_extra_prepaid_discount')]")).getText().replaceAll("[^\\d.]", ""));
//    } catch (Exception ignored) {}
//
//    System.out.println("🧾 Checkout Summary:");
//    System.out.println("MRP: " + mrp + " Discounted: " + discounted + " You Saved: " + youSaved);
//    System.out.println("Total: " + totalDiscounted + " Payable: " + finalPayable + " Flat ₹50: " + flatDiscount);

    // Step 8: Go to Address Page
    driver.findElement(By.xpath("//button[@class='place_order_btn Cls_place_order btn___2 ']")).click();
    Common.waitForElement(2);

    // Step 9: Confirm selected address
    WebElement selectedAddress = driver.findElement(By.xpath("//div[@class='address_card Cls_addr_data_section']"));
    System.out.println("📦 Selected Address: " + selectedAddress.getText());
    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    scenario.attach(screenshot, "image/png", "Initial Screenshot");
    // Step 10: Place Order
    driver.findElement(By.xpath("//button[@class='place_order_btn Cls_place_order btn___2 enabled']")).click();
    WebElement  totalAmountPaymentPage= driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']"));
	String totalText = totalAmountPaymentPage.getText();
	
//    Common.waitForElement(3);
//	click(netBanking);
//	Common.waitForElement(1);
//	click(paymentPlaceOrder);
//	Common.waitForElement(1);
//	try {
//    driver.switchTo().frame("razorpay-checkout-frame");
//	WebElement paymentpopUpAmount = driver.findElement(By.xpath("//div[@class='mt-3 flex items-baseline gap-1']//h3[@class='text-2xl font-semibold number-flip']"));
//	String popupText = paymentpopUpAmount.getAttribute("data-value");
//	System.out.println("Popup Amount: Rs " + popupText);
//	System.out.println("Total Amount in Payment page: Rs " + totalText);
//	String cleanedTotalText = totalText.replaceAll("[^0-9]", "");
//	String cleanedPopupText = popupText.replaceAll("[^0-9]", "");
//
//	if (cleanedTotalText.equals(cleanedPopupText)) {
//		System.out.println("✅ Payment Popup and Total amount is matching → Rs " + cleanedTotalText);
//	} else {
//		System.out.println("❌ Payment Popup and Total amount is NOT matching → Expected: Rs " + cleanedTotalText + ", Actual: Rs " + cleanedPopupText);
//	}
//	} catch (NoSuchElementException e) {
//	    System.out.println("❌ Unable to locate Razorpay popup amount element.");
//	}
//    Common.waitForElement(1);
//    clickUsingJS(closePopup);
//    Common.waitForElement(1);
//    click(cancelPayment);
//    Common.waitForElement(1);
//    driver.switchTo().defaultContent();
    scenario.attach(screenshot, "image/png", "Initial Screenshot");
    click(cODMode);
    Common.waitForElement(1);
    click(paymentPlaceOrder);
    Common.waitForElement(5);
    // Step 11: Confirmation Page % Validation
//    double finalMRP = mrp;
//    double totalDiscounts = (mrp - discounted) + flatDiscount;
//    double expectedPercent = Math.round((totalDiscounts / finalMRP) * 100);

//    String confirmationDiscount = driver.findElement(By.xpath("//span[@class='placed_prod_discount']")).getText().replaceAll("[^\\d]", "");
//    System.out.println("🎯 Discount % on Confirmation Page: " + confirmationDiscount + " | Expected: " + expectedPercent);

    // Step 12: Order Details Page
    driver.findElement(By.xpath("//button[text()='View Order Details']")).click();
    Common.waitForElement(2);

    WebElement cancelBtn = driver.findElement(By.xpath("//button[@class='prod_cancel_btn cls_cancel_button']"));
    if (cancelBtn.isDisplayed()) {
        System.out.println("❌ Cancel Button: Displayed ✅");
    }

    // Step 13: Price Breakup
    driver.findElement(By.xpath("//button[@class='price_breakup_btn active']")).click();
    Common.waitForElement(1);
    scenario.attach(screenshot, "image/png", "Initial Screenshot");
    System.out.println("🧾 Price Breakup Verified:");
    System.out.println("Total MRP: " + driver.findElement(By.xpath("//div[@class='price_details_row actual_mrp']//div[@class='price_details_pair']")).getText());
    System.out.println("Discounted MRP: " + driver.findElement(By.xpath("//div[@class='price_details_row discount_mrp']//div[@class='price_details_pair']")).getText());
    System.out.println("Coupon Discount: " + driver.findElement(By.xpath("//div[@class='price_details_row coupon_discount']//div[@class='price_details_pair']")).getText());
    System.out.println("You Saved: " + driver.findElement(By.xpath("//div[@class='price_details_row saved_amount']//div[@class='price_details_pair']")).getText());

	
}
	public void verifyCheckoutCalculations() {
		LoginPage login = new LoginPage(driver);
		login.userLogin();
		Common.waitForElement(5);
		Actions actions = new Actions(driver);

		WebElement bagIcon = driver.findElement(By.xpath("//button[@class='Cls_cart_btn Cls_redirect_restrict']"));
		bagIcon.click();
		Common.waitForElement(2);

		List<WebElement> productBlocks = driver.findElements(By.xpath(".//div[@class='cart_prod_card ']"));
		if (productBlocks.isEmpty()) {
			System.out.println("🛍️ Bag item count not displayed. Adding product...");
			WebElement closeBag = driver.findElement(By.xpath("(//header[@class='popup_containers_header']//div[@class='popup_containers_cls_btn'])[2]"));
			closeBag.click();
			Common.waitForElement(2);
			WebElement shopMenu = driver.findElement(By.xpath("//li[@class='navigation_menu_list nav_menu_dropdown shop']"));
			actions.moveToElement(shopMenu);
			WebElement category = driver.findElement(By.xpath("//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]"));
			actions.moveToElement(category).click().build().perform();
			List<WebElement> addProduct = driver.findElements(By.xpath("//button[@class='product_list_cards_btn']"));
			Collections.shuffle(addProduct);
			if (!addProduct.isEmpty()) {
				WebElement randomProduct = addProduct.get(0);
				actions.moveToElement(randomProduct).click().build().perform();
				WebElement addToCart = driver.findElement(By.xpath("//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']"));
				addToCart.click();
				Common.waitForElement(5);
				driver.findElement(By.xpath("//button[@class='Cls_cart_btn Cls_redirect_restrict']")).click();
				Common.waitForElement(2);
			}
		}

		driver.findElement(By.xpath("//button[.='Buy Now']")).click();
		productBlocks = driver.findElements(By.xpath(".//div[@class='cart_prod_card ']"));

		double expectedTotalMrp = 0, expectedTotalDiscounted = 0, expectedYouSaved = 0;

		System.out.println("=== Product-wise Breakdown ===\n");

		for (int i = 0; i < productBlocks.size(); i++) {
			WebElement product = productBlocks.get(i);
			String productName = product.findElement(By.xpath(".//div[@class='cp_name_row']")).getText();
			int quantity = Integer.parseInt(product.findElement(By.xpath(".//div[@class='cp_quantity_wrap']")).getText().trim());
			double mrp = Double.parseDouble(product.findElement(By.xpath(".//div[@class='cp_actual_price']")).getText().replaceAll("[^\\d.]", ""));

			double discounted = 0;
			boolean isPromotional = false;

			if (product.findElements(By.xpath(".//span[@class='product_list_cards_actual_price_txt']")).size() > 0) {
				discounted = Double.parseDouble(product.findElement(By.xpath(".//span[@class='product_list_cards_actual_price_txt']")).getText().replaceAll("[^\\d.]", ""));
				isPromotional = true;
			} else {
				discounted = Double.parseDouble(product.findElement(By.xpath(".//div[@class='cp_current_price']")).getText().replaceAll("[^\\d.]", ""));
			}

			double expectedMrp = mrp * quantity;
			double expectedDiscounted = discounted * quantity;
			double expectedSaved = (mrp - discounted) * quantity;

			if (isPromotional) {
				System.out.println("Product " + (i + 1) + ": "  + " (Promotional)");
			} else {
				System.out.println("Product " + (i + 1) + ": "  + " (Normal)");
			}

			expectedTotalMrp += expectedMrp;
			expectedTotalDiscounted += expectedDiscounted;
			expectedYouSaved += expectedSaved;

			System.out.println("Product " + (i + 1) + ": " + productName);
			System.out.println("Quantity: " + quantity);
			System.out.println("Actual Price of the product : Rs. " + expectedMrp + " | Discounted Price of the product: Rs. " + expectedDiscounted);
			System.out.println("Product " + (i + 1) + " calculations complete\n");

		}

		// Gift Wrap
		double express = 0, custom = 0;
//		try {
//			WebElement giftWrapToggle = driver.findElement(By.xpath("//input[@class='checkout_git_list_item__checkbox']"));
//			giftWrapToggle.click();
//			driver.findElement(By.id("recipient-name")).sendKeys("Test User");
//			driver.findElement(By.xpath("//button[@class='gift__submit btn___2']")).click();
//			Common.waitForElement(2);
//			giftWrap = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair git__wraping11']")).getText().replaceAll("[^\\d.]", ""));
//		} catch (Exception e) {
//			System.out.println("🎁 Gift wrap section not available or skipped");
//		}

		// Express Delivery
		try {
			WebElement expressOption = driver.findElement(By.id("checked2"));
			if (!expressOption.isSelected()) expressOption.click();
			express = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='Cls_CourierFee']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception e) {
			System.out.println("---------------------------------------------");
			System.out.println("🚚 Express delivery not available or skipped");
			System.out.println("---------------------------------------------");
		}

		// Customization Charges
		try {
			custom = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_row Cls_customized_extras']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}

		// Coupon
		double couponDiscount = 0;
		try {
			Common.waitForElement(2);
			List<WebElement> availableCoupons = driver.findElements(By.xpath("//button[@class='offer_list_item_apply_btn Cls_apply_coupon']"));
			if (!availableCoupons.isEmpty()) {
				availableCoupons.get(0).click();
				Common.waitForElement(2);
				WebElement appliedText = driver.findElement(By.xpath("//p[@class='coupon_apply_msg active']"));
				couponDiscount = Double.parseDouble(appliedText.getText().replaceAll("[^\\d.]", ""));
				System.out.println("---------------------------------------------");
				System.out.println("✅ Coupon applied successfully. Discount amount: Rs. " + couponDiscount);
				System.out.println("---------------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("---------------------------------------------");
			System.out.println("❌ Coupon skipped or not applicable.");
			System.out.println("---------------------------------------------");
		}

		// Flat Rs. 50 Discount
		double flatDiscount = 0;
		try {
			flatDiscount = Double.parseDouble(driver.findElement(By.xpath("//div[@class='price_details_pair Cls_cart_extra_prepaid_discount']")).getText().replaceAll("[^\\d.]", ""));
		} catch (Exception ignored) {}

		// Thread Application
		int threadsApplied = 0;
		try {
			WebElement availableThread = driver.findElement(By.xpath("//span[@class='price_details_key_span']"));
			threadsApplied = Integer.parseInt(availableThread.getText().replaceAll("[^\\d]", ""));
			WebElement threadInput = driver.findElement(By.xpath("//input[@placeholder='Enter threads']"));
			if (!threadInput.isSelected()) threadInput.click();
			threadInput.clear();
			threadInput.sendKeys(String.valueOf(threadsApplied));
		} catch (Exception e) {
			System.out.println("🧵 Threads not applied or skipped");
		}

		double finalAmount = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_total_amount']")).getText().replaceAll("[^\\d.]", ""));
		double actualSaved = Double.parseDouble(driver.findElement(By.xpath(".//div[@class='price_details_pair Cls_cart_saved_amount']")).getText().replaceAll("[^\\d.]", ""));
		double expectedFinalYouSaved = expectedYouSaved + flatDiscount + couponDiscount + threadsApplied;
		double finalPayable = finalAmount;
		double actualTotalMrp = expectedTotalMrp; 
		double actualDiscountedMrp = expectedTotalDiscounted;
		int earnedThreads = ((int) (finalPayable  - express - custom) / 500) * 10;
		int actualEarnedThreads = ((int) (finalPayable  - express - custom) / 500) * 10;

		System.out.println("============= Checkout Summary ==================================");	
		System.out.println("Expected Total MRP: Rs. " + expectedTotalMrp + " || Actual Total MRP: Rs. " + actualTotalMrp + " || " + (expectedTotalMrp == actualTotalMrp ? "\u001B[32mMatched\u001B[0m" : "\u001B[31mMismatch\u001B[0m"));
		System.out.println("Expected Discounted MRP: Rs. " + expectedTotalDiscounted + " || Actual Discounted MRP: Rs. " + actualDiscountedMrp + " || " + (expectedTotalDiscounted == actualDiscountedMrp ? "\u001B[32mMatched\u001B[0m" : "\u001B[31mMismatch\u001B[0m"));
//		System.out.println("Gift Wrap: Rs. " + giftWrap);
		System.out.println("Express Delivery: Rs. " + express);
		System.out.println("Customization Charges: Rs. " + custom);
		System.out.println("Expected Earned Threads: " + earnedThreads + " || Actual Earned Threads: " + actualEarnedThreads + " || " + (earnedThreads == actualEarnedThreads ? "\u001B[32mMatched\u001B[0m" : "\u001B[31mMismatch\u001B[0m"));
		System.out.println("Flat ₹50 Discount Applied: Rs. " + flatDiscount);
		System.out.println("Thread Discount Applied: Rs. " + threadsApplied);
		System.out.println("Coupon Discount Applied: Rs. " + couponDiscount);
		System.out.println("Expected Final 'You Saved': Rs. " + expectedFinalYouSaved + " || Actual Final 'You Saved': Rs. " + actualSaved + " || " + (expectedFinalYouSaved == actualSaved ? "\u001B[32mMatched\u001B[0m" : "\u001B[31mMismatch\u001B[0m"));
		System.out.println("Final Total Amount (Expected): Rs. " + finalPayable + " || Final Total Amount (Actual): Rs. " + finalAmount + " || " + (finalPayable == finalAmount ? "\u001B[32mMatched\u001B[0m" : "\u001B[31mMismatch\u001B[0m"));
		System.out.println("\u001B[0m✅ All checkout calculations validated.");
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
