package pages;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import basePage.DropDown;
import manager.FileReaderManager;
import objectRepo.MyOrdersObjRepository;
import utils.Common;







public final class MyOrdersPage extends MyOrdersObjRepository{

	static WebDriver driver;


	public MyOrdersPage(WebDriver driver) {
		MyOrdersPage.driver = driver;
		PageFactory.initElements(MyOrdersPage.driver, this);
	}

	@Override
	public WebDriver gmail(String browserName) {
		return null;
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {

		return false;
	}

	@Override
	protected boolean isAt() {
		return this.wait.until((d) -> this.shopMenu.isDisplayed());

	}
	public void scrollToElementUsingJSE(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}


	public void randomProducttoCart(WebElement element) {
		List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//div[@class='product_list_cards_list ']"));
		Collections.shuffle(clickRandomProduct);

		if (!clickRandomProduct.isEmpty()) {
			WebElement randomProduct = clickRandomProduct.get(0);
			Actions actions = new Actions(driver);
			actions.moveToElement(randomProduct).click().build().perform();
			Common.waitForElement(1);
			click(element);
		}
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allwindowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList<String>();
			System.out.println(list.size());
			list.addAll(allwindowHandles);
			driver.switchTo().window(list.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException");
		}
	}
	
	
	
	public void netBanking(String...strings) {
		Actions actions = new Actions(driver);
		Common.waitForElement(5);
		actions.moveToElement(shopMenu).perform();
		if (strings.length > 0) {
//			actions.moveToElement(shopAll);
			Common.waitForElement(10);
			click(shopAll);
		} else {
			
			actions.moveToElement(shopMenu).perform();
			click(shopAll);
		}
		try {
	            Common.waitForElement(2);
	            randomProducttoCart(addToCartProduct);
				
	          
			
		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
		click(bagIcon);
		click(viewBag);
		
		
		try {
			if(strings.length>0) {
				actions.moveToElement(viewMore);
				click(viewMore);
				click(availableCoupon);
			}
			else {
				actions.moveToElement(availableCoupon1);
				click(availableCoupon1);
			}
		}

		catch (NoSuchElementException e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
		
		click(placeOrder);
		Common.waitForElement(3);
		click(checkOutPlaceOrder);
		Common.waitForElement(2);
		click(netBanking);
		Common.waitForElement(1);
		click(paymentPlaceOrder);
		Common.waitForElement(2);
		driver.switchTo().frame(0);
		Common.waitForElement(1);
		click(bankSelection);
		Common.waitForElement(3);
		switchToWindow(1);
		Common.waitForElement(1);
		click(successButton);
		switchToWindow(0);
		Common.waitForElement(5);
		click(viewOrderDetails);
		click(orderCancel);
		Common.waitForElement(1);
		click(orderCancelReason);
		click(orderCancelContinue);
		Common.waitForElement(2);
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
		Common.waitForElement(2);
		type(adminEmail,FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
		Common.waitForElement(2);
		type(adminPassword,FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
		Common.waitForElement(2);
		click(adminLogin);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
		Common.waitForElement(2);
		click(paymentRefund);
		selectDropDown(DropDown.INDEX,paymentRefund, 4);
		Common.waitForElement(1);
		actions.moveToElement(descriptionBox);
		Common.waitForElement(1);
		click(descriptionBox);
		Common.waitForElement(3);
		type(descriptionBox,Common.getValueFromTestDataMap("Street"));
		click(saveandBack);
		Common.waitForElement(2);
		switchToWindow(0);
	}
	

	

//	public void productList() {
//		
//        Common.waitForElement(3);
//		click(shopMenu);
//		click(shopAll);
//		Common.waitForElement(1);
//		randomProducttoCart(addToCartProduct);
//		Common.waitForElement(2);
//		click(bagIcon);
//		click(viewBag);
//
//	}
//
//
//	public void checkout(String... strings)  {
//		try {
//			if(strings.length>0) {
//				Common.waitForElement(3);
//				click(viewMore);
//				Common.waitForElement(2);
//				click(availableCoupon);
//			}
//			else {
//				click(availableCoupon1);
//			}
//		}
//
//		catch (NoSuchElementException e) {
//			System.out.println("Caught an exception: " + e.getMessage());
//			// Throwing a new exception
//			throw new NoSuchElementException("A NoSuchElementException exception occurred", e);
//		}
//
//		Common.waitForElement(1);
//		click(placeOrder);
//	}
//
//	public void paymentPageNetBanking() {
//
//		Common.waitForElement(3);
//		click(checkOutPlaceOrder);
//		Common.waitForElement(2);
//		click(netBanking);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
//		Common.waitForElement(2);
//		driver.switchTo().frame(0);
//		Common.waitForElement(1);
//		click(bankSelection);
//		Common.waitForElement(3);
//		switchToWindow(1);
//		Common.waitForElement(1);
//		click(successButton);
//		switchToWindow(0);
//		Common.waitForElement(5);
//		click(viewOrderDetails);
//		click(orderCancel);
//		Common.waitForElement(1);
//		click(orderCancelReason);
//		click(orderCancelContinue);
//
//	}
//	public void paymentRefund() {
//		Common.waitForElement(2);
//		driver.switchTo().newWindow(WindowType.TAB);
//		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
//		Common.waitForElement(2);
//		type(adminEmail,FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
//		Common.waitForElement(2);
//		type(adminPassword,FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
//		Common.waitForElement(2);
//		click(adminLogin);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(2);
//		click(paymentRefund);
//		selectDropDown(DropDown.INDEX,paymentRefund, 4);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(2);
//		switchToWindow(0);
//	}
//
	public void paymentRefundforPrepaidCancelOrders() {

		Common.waitForElement(2);
		switchToWindow(1);
		driver.navigate().to("https://adminzltav7.testingserver8.com/admin/cancel-order");
		Common.waitForElement(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
		Common.waitForElement(5);
		click(descriptionBox);
		type(descriptionBox,Common.getValueFromTestDataMap("Address"));
		Common.waitForElement(2);
		click(paymentRefund);
		selectDropDown(DropDown.INDEX,paymentRefund, 4);
		Common.waitForElement(1);
		click(saveandBack);
		Common.waitForElement(2);
		switchToWindow(0);
		
	}
//
//	public void orderPlacewithDebitcard(String...strings) {
//
//		Common.waitForElement(3);
//		click(checkOutPlaceOrder);
//		Common.waitForElement(2);
//		click(debitCard);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
//		Common.waitForElement(2);
//		driver.switchTo().frame(0);
//		Common.waitForElement(2);
//		type(debitCardNumber,Common.getValueFromTestDataMap("DebitcardNumber"));
//		type(debitCardExpiry,Common.getValueFromTestDataMap("DebitcardExpiry"));
//		type(debitCardCvv,Common.getValueFromTestDataMap("DebitcardCvv"));
//		type(debitCardName,Common.getValueFromTestDataMap("DebitcardName"));
//		Common.waitForElement(1);
//		click(debitCardcontinueButton);
//		Common.waitForElement(2);
//		click(debitCardCurrency);
//		Common.waitForElement(5);
//		click(debitCardpayButton);
//		Common.waitForElement(5);
//		click(debitCardSecure);
//		Common.waitForElement(5);
//		click(debitcardOTP);
//		type(debitcardOTP,Common.getValueFromTestDataMap("DebitcardOTP"));
//		Common.waitForElement(5);
//		click(otpContinue);
//		Common.waitForElement(15);
//		switchToWindow(1);
//		switchToWindow(0);
//		Common.waitForElement(5);
//		click(viewOrderDetails);
//		Common.waitForElement(1);
//		click(orderCancel);
//		Common.waitForElement(1);
//		click(orderCancelReason);
//		click(orderCancelContinue);
//		Common.waitForElement(1);
//		attachFullPageScreenshot(driver);
//
//
//	}
//
//	public void orderPlacewithCreditcard(String...strings) {
//
//
//		Common.waitForElement(3);
//		click(checkOutPlaceOrder);
//		Common.waitForElement(2);
//		click(creditCard);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
//		Common.waitForElement(2);
//		driver.switchTo().frame(0);
//		Common.waitForElement(2);
//		type(debitCardNumber,Common.getValueFromTestDataMap("DebitcardNumber"));
//		type(debitCardExpiry,Common.getValueFromTestDataMap("DebitcardExpiry"));
//		type(debitCardCvv,Common.getValueFromTestDataMap("DebitcardCvv"));
//		type(debitCardName,Common.getValueFromTestDataMap("DebitcardName"));
//		Common.waitForElement(1);
//		click(debitCardcontinueButton);
//		Common.waitForElement(2);
//		click(debitCardCurrency);
//		Common.waitForElement(5);
//		click(debitCardpayButton);
//		Common.waitForElement(5);
//		click(debitCardSecure);
//		Common.waitForElement(5);
//		click(debitcardOTP);
//		type(debitcardOTP,Common.getValueFromTestDataMap("DebitcardOTP"));
//		Common.waitForElement(5);
//		click(otpContinue);
//		Common.waitForElement(15);
//		switchToWindow(1);
//		switchToWindow(0);
//		Common.waitForElement(5);
//		click(viewOrderDetails);
//		click(orderCancel);
//		Common.waitForElement(1);
//		click(orderCancelReason);
//		click(orderCancelContinue);
//		Common.waitForElement(1);
//		attachFullPageScreenshot(driver);
//
//
//	}
//	public void orderPlacewithUPI(String...strings) {
//
//		Common.waitForElement(3);
//		click(checkOutPlaceOrder);
//		Common.waitForElement(2);
//		click(upi);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
//		Common.waitForElement(2);
//		driver.switchTo().frame(0);
//		Common.waitForElement(2);
//		type(upiId,Common.getValueFromTestDataMap("UPI Id"));
//		Common.waitForElement(2);
//		click(pay);
//		driver.switchTo().defaultContent();
//		Common.waitForElement(5);
//		click(viewOrderDetails);
//		Common.waitForElement(1);
//		click(orderCancel);
//		Common.waitForElement(1);
//		click(orderCancelReason);
//		click(orderCancelContinue);
//		Common.waitForElement(1);
//		attachFullPageScreenshot(driver);
//
//
//	}
//	public void placeOrderCOD(String...strings) {
//
//		Common.waitForElement(5);
//		click(checkOutPlaceOrder);
//		Common.waitForElement(2);
//		click(cod);
//		Common.waitForElement(1);
//		click(paymentPlaceOrder);
//
//	}
//
//
//
//	public void adminPanelOrderDelivery() {
//		Common.waitForElement(2);
//		switchToWindow(1);
//		driver.navigate().to("https://adminzltav7.testingserver8.com/admin/order");
//		Common.waitForElement(2);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(1);
//		click(selectOrderStatus);
//		selectDropDown(DropDown.INDEX,selectOrderStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(1);
//		//Order out for delivery
//		click(selectOrderStatus);
//		selectDropDown(DropDown.INDEX,selectOrderStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(1);
//		//order delivered
//		click(selectOrderStatus);
//		selectDropDown(DropDown.INDEX,selectOrderStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(5);
//		switchToWindow(0);
//		Common.waitForElement(5);
//		click(profile);
//		Common.waitForElement(1);
//		click(profileMyOrders);
//		Common.waitForElement(2);
//		click(orderDetailsButton);
//		Common.waitForElement(2);
//		click(downloadInvoice);
//
//	}
//
//
//	public void exchangeCancel() {
//
//		Common.waitForElement(2);
//		click(exchangeButton);
//		Common.waitForElement(2);
//		click(exchangeReason);
//		Common.waitForElement(2);
//		click(exchangeContinueButton);
//		try {
//			if(exchangeButton.isDisplayed()) {
//				Common.waitForElement(2);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(2);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(2);
//				click(myOrders);
//
//			}
//			else {
//				click(closeexchangepopup);
//				Common.waitForElement(1);
//				click(myOrders);
//				Common.waitForElement(1);
//				click(filterOrders);
//				Common.waitForElement(1);
//				click(deliveredOrderFilter);
//				click(filterApply);
//				Common.waitForElement(1);
//				click(newProductExchange);
//				click(exchangeReason);
//				Common.waitForElement(1);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(2);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(2);
//				click(myOrders);
//			}
//
//		}
//		catch (NoSuchElementException e) {
//			System.out.println("Caught an exception: " + e.getMessage());
//			// Throwing a new exception
//			throw new NoSuchElementException("A NoSuchElementException exception occurred", e);
//		}
//	}
//
//	public void exchangeCancelSecondTime() {
//		Common.waitForElement(2);
//		click(exchangeButton);
//		Common.waitForElement(2);
//		click(exchangeReason);
//		Common.waitForElement(2);
//		click(exchangeContinueButton);
//		try {
//			if(exchangeButton.isDisplayed()) {
//				Common.waitForElement(2);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(2);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(2);
//				click(myOrders);
//
//			}
//			else {
//				click(closeexchangepopup);
//				Common.waitForElement(1);
//				click(myOrders);
//				Common.waitForElement(1);
//				click(filterOrders);
//				Common.waitForElement(1);
//				click(deliveredOrderFilter);
//				click(filterApply);
//				Common.waitForElement(1);
//				click(newProductExchange);
//				click(exchangeReason);
//				Common.waitForElement(1);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(2);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(2);
//				click(myOrders);
//			}
//
//		}
//		catch (NoSuchElementException e) {
//			System.out.println("Caught an exception: " + e.getMessage());
//			// Throwing a new exception
//			throw new NoSuchElementException("A NoSuchElementException exception occurred", e);
//		}
//
//
//	}
//	public void exchangeCancelThirdTime() {
//		Common.waitForElement(2);
//		click(exchangeButton);
//		Common.waitForElement(2);
//		click(exchangeReason);
//		Common.waitForElement(2);
//		click(exchangeContinueButton);
//		try {
//			if(exchangeButton.isDisplayed()) {
//				Common.waitForElement(2);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(3);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(5);
//				click(myOrders);
//			}
//			else {
//				click(closeexchangepopup);
//				Common.waitForElement(1);
//				click(myOrders);
//				Common.waitForElement(1);
//				click(filterOrders);
//				Common.waitForElement(1);
//				click(deliveredOrderFilter);
//				click(filterApply);
//				Common.waitForElement(1);
//				click(newProductExchange);
//				click(exchangeReason);
//				Common.waitForElement(1);
//				click(exchangeItem);
//				Common.waitForElement(2);
//				click(exchangePlaceOrder);
//				Common.waitForElement(2);
//				click(exchangeAddressPagePlaceOrder);
//				Common.waitForElement(2);
//				click(viewOrderDetails);
//				Common.waitForElement(2);
//				click(exchangeCancel);
//				Common.waitForElement(3);
//				click(myOrders);
//
//
//			}
//
//		}
//		catch (NoSuchElementException e) {
//			System.out.println("Caught an exception: " + e.getMessage());
//			// Throwing a new exception
//			throw new NoSuchElementException("A NoSuchElementException exception occurred", e);
//		}
//	}
//	public void exchangeProcess() {
//
//
//		try {
//			boolean exchangeButtonFound = false;
//			int currentPage = 1;
//			int totalPages = 50; 
//			while (currentPage <= totalPages) {
//
//				try {
//
//					if (exchangeButton != null) {
//						System.out.println("Exchange button found on page " + currentPage);
//
//					}
//					click(exchangeButton);
//					Common.waitForElement(2);
//					click(exchangeReason);
//					Common.waitForElement(2);
//					click(exchangeContinueButton);
//					Common.waitForElement(2);
//					click(exchangeItem);
//					Common.waitForElement(3);
//					click(exchangePlaceOrder);
//					Common.waitForElement(2);
//					click(AddNewAdddress);
//					Common.waitForElement(3);
//					type(nameAddress,Common.getValueFromTestDataMap("Name Address"));
//					type(phoneNumberAddress,FileReaderManager.getInstance().getJsonReader().getValueFromJson("Number"));
//					type(mailAddress,Common.getValueFromTestDataMap("Email Id Address"));
//					type(addressDetails,Common.getValueFromTestDataMap("Address"));
//					type(addressStreet,Common.getValueFromTestDataMap("Street"));
//					type(pinCode,Common.getValueFromTestDataMap("Pincode"));
//					type(localityTown,Common.getValueFromTestDataMap("Locality"));
//					Common.waitForElement(3);
//					click(saveAddress);
//					Common.waitForElement(3);
//					click(exchangeAddressPagePlaceOrder);
//					Common.waitForElement(2);
//					click(viewOrderDetails);
//					Common.waitForElement(2);
//					click(myOrders);
//					Common.waitForElement(2);
//					switchToWindow(1);
//					Common.waitForElement(2);
//					driver.navigate().to("https://adminzltav7.testingserver8.com/admin/exchange-order");
//					Common.waitForElement(2);
//					((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//					Common.waitForElement(1);
//					click(exchangeAcceptStatus);
//					selectDropDown(DropDown.INDEX,exchangeAcceptStatus, 3);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(1) ;
//					click(editOrders);
//					Common.waitForElement(1);
//					click(exchangeStatus);
//					selectDropDown(DropDown.INDEX,exchangeStatus, 1);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(exchangeStatus);
//					selectDropDown(DropDown.INDEX,exchangeStatus, 1);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(exchangeStatus);
//					selectDropDown(DropDown.INDEX,exchangeStatus, 1);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(exchangeStatus);
//					selectDropDown(DropDown.INDEX,exchangeStatus, 1);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(exchangeStatus);
//					selectDropDown(DropDown.INDEX,exchangeStatus, 1);
//					Common.waitForElement(1);
//					click(saveandBack);
//					Common.waitForElement(5);
//					switchToWindow(0);
//					Common.waitForElement(2);
//					driver.navigate().refresh();
//					exchangeButtonFound =true;
//					break;
//				} catch (Exception e) {
//					// Button not found on this page, proceed to the next page
//				}
//				// Click on the next page button (assuming there's a "Next" button to navigate pages)
//				try {
//					click(pagiNextButton);
//					currentPage++;
//
//				} catch (Exception e) {
//					// If there is no "Next" button or an error occurs, break the loop
//					break;
//				}
//			}
//			if (!exchangeButtonFound) {
//				System.out.println("Exchange button not found on any page.");
//			}
//
//		} finally {
//
//		}
//	}
//
//
//
//
//	public void exchangesecondTime() {
//
//		Common.waitForElement(1);
//		click(exchangeButton);
//		Common.waitForElement(1);
//		click(exchangeReason);
//		Common.waitForElement(1);
//		click(exchangeContinueButton);
//		Common.waitForElement(1);
//		click(exchangeItem);
//		Common.waitForElement(3);
//		click(exchangePlaceOrder);
//		Common.waitForElement(2);
//		click(exchangeAddressPagePlaceOrder);
//		Common.waitForElement(2);
//		click(viewOrderDetails);
//		Common.waitForElement(2);
//		click(myOrders);
//		Common.waitForElement(2);
//		switchToWindow(1);
//		Common.waitForElement(2);
//		driver.navigate().to("https://adminzltav7.testingserver8.com/admin/exchange-order");
//		Common.waitForElement(2);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(1);
//		click(exchangeAcceptStatus);
//		selectDropDown(DropDown.INDEX,exchangeAcceptStatus, 3);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1) ;
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,exchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,exchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,exchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,exchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,exchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(5);
//		switchToWindow(0);
//		Common.waitForElement(2);
//		driver.navigate().refresh();
//
//
//	}
//
//	public void exchangeThirdTime() {
//
//		Common.waitForElement(1);
//		click(exchangeButton);
//		Common.waitForElement(1);
//		click(exchangeReason);
//		Common.waitForElement(1);
//		click(exchangeContinueButton);
//		Common.waitForElement(1);
//		click(exchangeItem);
//		Common.waitForElement(3);
//		click(exchangePlaceOrder);
//		Common.waitForElement(2);
//		click(exchangeAddressPagePlaceOrder);
//		Common.waitForElement(2);
//		click(viewOrderDetails);
//		Common.waitForElement(2);
//		click(myOrders);
//		Common.waitForElement(2);
//		switchToWindow(1);
//		Common.waitForElement(2);
//		driver.navigate().to("https://adminzltav7.testingserver8.com/admin/exchange-order");
//		Common.waitForElement(2);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//		Common.waitForElement(1);
//		click(exchangeAcceptStatus);
//		selectDropDown(DropDown.INDEX,exchangeAcceptStatus, 3);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1) ;
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,ThirdtimeExchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,ThirdtimeExchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,ThirdtimeExchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,ThirdtimeExchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(1);
//		click(editOrders);
//		Common.waitForElement(1);
//		click(exchangeStatus);
//		selectDropDown(DropDown.INDEX,ThirdtimeExchangedProductStatus, 1);
//		Common.waitForElement(1);
//		click(saveandBack);
//		Common.waitForElement(2);
//		switchToWindow(0);
//		Common.waitForElement(2);
//		driver.navigate().refresh();
//		Common.waitForElement(1);
//		attachFullPageScreenshot(driver);
//		Common.waitForElement(1);
//		click(myOrders);
//	}
//
//
//
//
//
//	public void returncancel() {
//		Common.waitForElement(1);
//		click(filterOrders);
//		click(deliveredOrderFilter);
//		click(filterApply);
//		try {
//			boolean ReturnButtonFound = false;
//			int currentPage = 1;
//			int totalPages = 50; 
//			while (currentPage <= totalPages) {
//
//				try {
//
//					if (returnButton != null) {
//						System.out.println("Return button found on page " + currentPage);
//
//					}
//
//					Common.waitForElement(2);
//					click(returnButton);
//					Common.waitForElement(1);
//					click(returnReason);
//					Common.waitForElement(2);
//					click(returncontinueButton);
//					Common.waitForElement(3);
//					click(confirmReturn);
//					Common.waitForElement(3);
//					click(viewOrderDetails);
//					Common.waitForElement(2);
//					click(returnCancel);
//					ReturnButtonFound =true;
//					break;
//				} catch (Exception e) {
//					// Button not found on this page, proceed to the next page
//				}
//				// Click on the next page button (assuming there's a "Next" button to navigate pages)
//				try {
//					click(pagiNextButton);
//					currentPage++;
//
//				} catch (Exception e) {
//					// If there is no "Next" button or an error occurs, break the loop
//					break;
//				}
//			}
//			if (!ReturnButtonFound) {
//				System.out.println("Return button not found on any page.");
//			}
//
//		} finally {
//
//		}
//
//
//	}
//	public void returncancelSecondTime() {
//
//		try {
//			boolean ReturnButtonFound = false;
//			int currentPage = 1;
//			int totalPages = 50; 
//			while (currentPage <= totalPages) {
//
//				try {
//
//					if (returnButton != null) {
//						System.out.println("Return button found on page " + currentPage);
//
//					}
//
//					Common.waitForElement(2);
//					click(returnButton);
//					Common.waitForElement(1);
//					click(returnReason);
//					Common.waitForElement(2);
//					click(returncontinueButton);
//					Common.waitForElement(3);
//					click(confirmReturn);
//					Common.waitForElement(3);
//					click(viewOrderDetails);
//					Common.waitForElement(2);
//					click(returnCancel);
//					ReturnButtonFound =true;
//					break;
//				} catch (Exception e) {
//					// Button not found on this page, proceed to the next page
//				}
//				// Click on the next page button (assuming there's a "Next" button to navigate pages)
//				try {
//					click(pagiNextButton);
//					currentPage++;
//
//				} catch (Exception e) {
//					// If there is no "Next" button or an error occurs, break the loop
//					break;
//				}
//			}
//			if (!ReturnButtonFound) {
//				System.out.println("Return button not found on any page.");
//			}
//
//		} finally {
//
//		}
//
//
//
//	}
//	public void returncancelThirdTime() {
//
//		try {
//			boolean ReturnButtonFound = false;
//			int currentPage = 1;
//			int totalPages = 50; 
//			while (currentPage <= totalPages) {
//
//				try {
//
//					if (returnButton != null) {
//						System.out.println("Return button found on page " + currentPage);
//
//					}
//
//					Common.waitForElement(2);
//					click(returnButton);
//					Common.waitForElement(1);
//					click(returnReason);
//					Common.waitForElement(2);
//					click(returncontinueButton);
//					Common.waitForElement(3);
//					click(confirmReturn);
//					Common.waitForElement(3);
//					click(viewOrderDetails);
//					Common.waitForElement(2);
//					click(returnCancel);
//					ReturnButtonFound =true;
//					break;
//				} catch (Exception e) {
//					// Button not found on this page, proceed to the next page
//				}
//				// Click on the next page button (assuming there's a "Next" button to navigate pages)
//				try {
//					click(pagiNextButton);
//					currentPage++;
//
//				} catch (Exception e) {
//					// If there is no "Next" button or an error occurs, break the loop
//					break;
//				}
//			}
//			if (!ReturnButtonFound) {
//				System.out.println("Return button not found on any page.");
//			}
//
//		} finally {
//
//		}
//
//
//	}
//
//	public void returnProcess() {
//
//
//		try {
//			boolean ReturnButtonFound = false;
//			int currentPage = 1;
//			int totalPages = 50; 
//			while (currentPage <= totalPages) {
//
//				try {
//
//					if (returnButton != null) {
//						System.out.println("Return button found on page " + currentPage);
//
//					}
//					Common.waitForElement(2);
//					click(returnButton);
//					Common.waitForElement(2);
//					click(returnReason);
//					Common.waitForElement(2);
//					click(returncontinueButton);
//					Common.waitForElement(3);
//					click(confirmReturn);
//					Common.waitForElement(3);
//					click(viewOrderDetails);
//					Common.waitForElement(2);
//					switchToWindow(1);
//					Common.waitForElement(5);
//					driver.navigate().to("https://adminzltav7.testingserver8.com/admin/return-order");
//					Common.waitForElement(2);
//					((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOrders);
//					Common.waitForElement(2);
//					click(returnAcceptStatus);
//					selectDropDown(DropDown.INDEX,returnAcceptStatus, 3);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(returnStatus);
//					selectDropDown(DropDown.INDEX,returnStatus, 1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(returnStatus);
//					selectDropDown(DropDown.INDEX,returnStatus, 1);
//					click(saveandBack);
//					Common.waitForElement(1);
//					click(editOrders);
//					Common.waitForElement(1);
//					click(returnStatus);
//					selectDropDown(DropDown.INDEX,returnStatus, 1);
//					Common.waitForElement(1);
//					click(paymentRefund);
//					selectDropDown(DropDown.INDEX,paymentRefund, 4);
//					Common.waitForElement(1);
//					type(refundTransId,FileReaderManager.getInstance().getJsonReader().getValueFromJson("Number"));
//					click(saveandBack);
//					Common.waitForElement(3);
//					switchToWindow(0);
//					Common.waitForElement(2);
//					driver.navigate().refresh();
//					Common.waitForElement(1);
//					attachFullPageScreenshot(driver);
//					ReturnButtonFound =true;
//					break;
//				} catch (Exception e) {
//					// Button not found on this page, proceed to the next page
//				}
//				// Click on the next page button (assuming there's a "Next" button to navigate pages)
//				try {
//					click(pagiNextButton);
//					currentPage++;
//
//				} catch (Exception e) {
//					// If there is no "Next" button or an error occurs, break the loop
//					break;
//				}
//			}
//			if (!ReturnButtonFound) {
//				System.out.println("Return button not found on any page.");
//			}
//
//		} finally {
//
//		}
//	}
//
//	public  void gmail() {
//
//		driver.switchTo().newWindow(WindowType.TAB);
//		driver.navigate().to("https://mail.google.com");
//		Common.waitForElement(1);
//		type(gmailId, FileReaderManager.getInstance().getJsonReader().getValueFromJson("GmailId"));
//		Common.waitForElement(1);
//		click(nextButton);
//		Common.waitForElement(1);
//		type(gmailPassWord, FileReaderManager.getInstance().getJsonReader().getValueFromJson("GmailPassWord"));
//		Common.waitForElement(1);
//		click(passwordNext);
//		Common.waitForElement(10);
//		attachFullPageScreenshot(driver);
//		click(orderConfirmMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(orderVerficationforSS);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		Common.waitForElement(5);
//		click(orderShippedMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(orderVerficationforSS);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		Common.waitForElement(5);
//		click(orderDeliveredMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(orderVerficationforSS);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		Common.waitForElement(5);
//		click(exchangeRequestMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(exchangeShippedMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(exchangeDeliveredMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(exchangeCancelMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(returnRequestedMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(orderVerficationforSS);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(refundCreditedMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(refundCreditedSS);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(returnCancelMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//		click(orderCancelMail);
//		Common.waitForElement(5);
//		scrollToElementUsingJSE(ssVerification);
//		attachFullPageScreenshot(driver);
//		driver.navigate().back();
//
//
//	}
}



















