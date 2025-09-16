package pages;


import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import manager.FileReaderManager;
import objectRepo.HomePageObjRepo;
import utils.Common;

public final class HomePage extends HomePageObjRepo {


	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}
	public void homeLaunch() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
//				type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
//				click(submit);
		//popup();


	}
	public void popup() {
		try {
			WebElement popUp = driver.findElement(By.xpath("//button[@class='close-btn']"));
			Common.waitForElement(5);

			if (popUp.isDisplayed()) {
				popUp.click();
			}

		} catch (Exception e) {

		}

	}
	public void scrollToElementUsingJSE(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	public void scrollUsingJSWindow() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, 1800);");

	}

	public void bannerClick() {
		homeLaunch();
		Common.waitForElement(5);
		click(banners);
		//		WebElement bannerRedirection = driver.findElement(By.xpath("//h3[@class='prod_list_topic']"));
		//		if (bannerRedirection.isDisplayed()) {
		//			String pageHeading = bannerRedirection.getText();
		//			System.out.println("Banner Redirected sucessfull: " +pageHeading);
		//			Assert.assertTrue(verifyDisplayed(bannerRedirection));
		//
		//		}
	}
	public void forAndbackButton() {
		homeLaunch();
		try {
			Actions action = new Actions(driver);
			WebElement bannerNxtBtn = driver.findElement(By.xpath("//*[@class='carousel_banner_prev_btn']"));
			WebElement bannerPrevBtn = driver.findElement(By.xpath("//*[@class='carousel_banner_next_btn']"));

			int maxClicks = 5; // Define the maximum number of clicks

			// Click next button
			for (int i = 0; i < maxClicks; i++) {
				if (bannerNxtBtn.isDisplayed()) {
					action.moveToElement(bannerNxtBtn).click().build().perform();
					Common.waitForElement(1); 
				} else {
					break;
				}
			}

			// Click previous button
			for (int i = 0; i < maxClicks; i++) {
				if (bannerPrevBtn.isDisplayed()) {
					action.moveToElement(bannerPrevBtn).click().build().perform();
					Common.waitForElement(1); 
				} else {
					break;
				}
			}

		} 
		catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}

	public void verifyPauseButton() {
		homeLaunch();
		try {
			WebElement pauseButton = driver.findElement(By.xpath("//div[@class='carousel_cta']"));
			WebElement playButton = driver.findElement(By.xpath("//*[@class='carousel_pause']"));

			// Wait 20 seconds to allow the banner to start moving
			Common.waitForElement(20);
			System.out.println("Banner is moving initially.");

			// Click the pause button and wait to verify if it stops
			pauseButton.click();
			Common.waitForElement(5);
			System.out.println("Banner stopped after pause.");

			// Click the play button and wait to verify if it starts again
			playButton.click();
			Common.waitForElement(15);
			System.out.println("Banner started moving after play.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void topSelling() throws TimeoutException {
		homeLaunch();
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
			Common.waitForElement(2);
			// Now wait for and click the forward button
			WebElement forwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@class='swiper-button-next top_selling_swiper_next']")));
			action.moveToElement(forwardButton).click().perform();
			System.out.println("Top Selling forward button clicked.");

			// Now wait for and click the backward button
			WebElement backwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@class='swiper-button-prev top_selling_swiper_prev']")));
			action.moveToElement(backwardButton).click().perform();
			System.out.println("Top Selling backward button clicked.");

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}

	public void topSellingProduct() {
		homeLaunch();

		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
			Common.waitForElement(2);

			List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//div[@class='product_img']"));
			if (clickRandomProduct.isEmpty()) {
				System.out.println("No products found in the Top Selling section.");
				return;
			}

			String productName = driver.findElement(By.xpath(".//div[@class='products_contents']")).getText().trim();
			System.out.println("Top selling product Content: " + productName);
			WebElement randomProduct = clickRandomProduct.get(0);
			Collections.shuffle(clickRandomProduct);
			Actions actions = new Actions(driver);
			actions.moveToElement(randomProduct).click().build().perform();

			Common.waitForElement(2);
			WebElement productDetailsPrdContent = driver.findElement(By.xpath("//div[@class='prod_main_details_head']"));
			String productDetailContent = productDetailsPrdContent.getText().trim();
			System.out.println("Product details product content: " + productDetailContent);

			// Check if productDetailContent contains productName (partial match)
			if (productDetailContent.contains(productName)) {
				System.out.println("Both Contents are matching.");
			} else {
				System.out.println("Both Contents are not matching.");
			}

		} catch (Exception e) {
			System.out.println("Exception in topSellingProduct: " + e.getMessage());
			throw e;
		}
	}


//	public void showMore() {
//		homeLaunch();
//		Actions action = new Actions(driver);
//		Common.waitForElement(2);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2300);");
//		Common.waitForElement(2);
//		action.moveToElement(showMore).click().build().perform();
//		WebElement showMorePage = driver.findElement(By.xpath("//h3[@class='prod_list_topic']"));
//		if (showMorePage.isDisplayed()) {
//			String pageHeading = showMorePage.getText();
//			System.out.println("Show More button Redirected sucessfull: " +pageHeading);
//			Assert.assertTrue(verifyDisplayed(showMorePage));
//
//
//		}
//
//	}
	
	public void newArrivalArrows() {
	    homeLaunch(); // Open home page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Locators
	    By heading = By.xpath("//h2[text()='New Arrivals']");
	    By forwardBtn = By.xpath("//h2[text()='New Arrivals']/following::div[contains(@class,'swiper-button-next')][1]");
	    By backwardBtn = By.xpath("//h2[text()='New Arrivals']/following::div[contains(@class,'swiper-button-prev')][1]");

	    try {
	        // Scroll the New Arrivals heading to the middle of the viewport
	        WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", headingElement);
	        Common.waitForElement(2); // Small wait for lazy-loaded content

	        // Click the forward arrow
	        WebElement forwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(forwardBtn));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", forwardButton);
	        System.out.println("✅ New Arrival forward button clicked.");
	        Common.waitForElement(2);

	        // Click the backward arrow
	        WebElement backwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(backwardBtn));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backwardButton);
	        System.out.println("✅ New Arrival backward button clicked.");
	        Common.waitForElement(2);

	    } catch (Exception e) {
	        System.out.println("❌ Caught an exception: " + e.getMessage());
	        NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException occurred in New Arrivals arrows");
	        e1.initCause(e);
	        throw e1;
	    }
	}

	public void newArivalProductImg() {
		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2700);");
		try {

			List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//div[@class='new_arrival_card_list ']")); 
			if (clickRandomProduct.isEmpty()) {
				System.out.println("No products found in New arrivals section.");
				return;
			}

			String productName = driver.findElement(By.xpath(".//div[@class='products_contents']")).getText().trim();
			System.out.println("New arrivals product Content: " + productName);
			WebElement randomProduct = clickRandomProduct.get(0);
			Collections.shuffle(clickRandomProduct);
			Actions actions = new Actions(driver);
			actions.moveToElement(randomProduct).click().build().perform();

			Common.waitForElement(2);
			WebElement productDetailsPrdContent = driver.findElement(By.xpath("//div[@class='prod_main_details_head']"));
			String productDetailContent = productDetailsPrdContent.getText().trim();
			System.out.println("Product details product content: " + productDetailContent);

			// Check if productDetailContent contains productName (partial match)
			if (productDetailContent.contains(productName)) {
				System.out.println("Both Contents are matching.");
			} else {
				System.out.println("Both Contents are not matching.");
			}

		} catch (Exception e) {
			System.out.println("Exception in New arrivals Product: " + e.getMessage());
			throw e;
		}
	}



	public void quickView() {
		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2500);");
		try {

			List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//div[@class='products_quick_view_box Cls_quickview_btn']"));
			if (clickRandomProduct.isEmpty()) {
				System.out.println("Quick view Not found in New arrivals section.");
				return;
			}

			String productName = driver.findElement(By.xpath(".//div[@class='products_contents']")).getText().trim();
			System.out.println("New arrivals product Content: " + productName);
			WebElement randomProduct = clickRandomProduct.get(0);
			Collections.shuffle(clickRandomProduct);
			Actions actions = new Actions(driver);
			actions.moveToElement(randomProduct).click().build().perform();

			Common.waitForElement(3);
			WebElement quickViewPrdContent = driver.findElement(By.xpath("//div[@class='qv_prod_details_cont']"));
			String quickViewContent = quickViewPrdContent.getText().trim();
			System.out.println("Quick view product content: " + quickViewContent);

			// Check if productDetailContent contains productName (partial match)
			if (quickViewContent.contains(productName)) {
				System.out.println("Both Contents are matching.");
			} else {
				System.out.println("Both Contents are not matching.");
			}

		} catch (Exception e) {
			System.out.println("Exception in topSellingProduct: " + e.getMessage());
			throw e;
		}
	}

	public void inspiredBy() {	
		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2600);");
		List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//div[@class='inspired_slide_img']"));
		Collections.shuffle(clickRandomProduct);
		Common.waitForElement(15);


		if (!clickRandomProduct.isEmpty()) {
			WebElement randomProduct = clickRandomProduct.get(0);
			Actions actions = new Actions(driver);
			actions.moveToElement(randomProduct).click().build().perform();
			WebElement inspiredByRedirection = driver.findElement(By.xpath("//div[@class='prod_main_details_head']"));
			if (inspiredByRedirection.isDisplayed()) {
				String details = inspiredByRedirection.getText();
				System.out.println("Inspired by Redirected sucessfull: " +details);
				Assert.assertTrue(verifyDisplayed(inspiredByRedirection));

			}
		}
	}

//	public void happy() {
//
//		homeLaunch();
//		Actions action = new Actions(driver);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//		try {
//			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 6200);");
//			Common.waitForElement(2);
//			// Now wait for and click the forward button
//			WebElement forwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.xpath("//div[@class='swiper-button-next testimonial_swiper_next']")));
//			action.moveToElement(forwardButton).click().perform();
//			System.out.println("Happy customer forward button clicked.");
//
//			// Now wait for and click the backward button
//			WebElement backwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.xpath("//div[@class='swiper-button-prev testimonial_swiper_prev']")));
//			action.moveToElement(backwardButton).click().perform();
//			System.out.println("Happy customer backward button clicked.");
//
//		} catch (Exception e) {
//			System.out.println("Caught an exception: " + e.getMessage());
//			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
//			e1.initCause(e);
//			throw e1;
//		}
//	}
//
//	public void happyQuickView() {
//		homeLaunch();
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 6200);");
//		try {
//
//			List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//a[@class='testimonial_cards_quick_view']"));
//			if (clickRandomProduct.isEmpty()) {
//				System.out.println("No Quick view found in Happy customer section.");
//				return;
//			}
//
//			String customerContent = driver.findElement(By.xpath(".//p[@class='testimonial_cards_disc']")).getText().trim().toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
//			System.out.println("Customer Content: " + customerContent);
//			WebElement randomProduct = clickRandomProduct.get(0);
//			Collections.shuffle(clickRandomProduct);
//			Actions actions = new Actions(driver);
//			actions.moveToElement(randomProduct).click().build().perform();
//
//			Common.waitForElement(2);
//			WebElement reviewContent = driver.findElement(By.xpath("//div[@class='customer_review_card']//p"));
//			String reviewDeatilsContent = reviewContent.getText().trim().toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
//			System.out.println("Review details content: " + reviewDeatilsContent);
//
//			// Check if productDetailContent contains productName (partial match)
//			if (reviewDeatilsContent.contains(customerContent)) {
//				System.out.println("Both Contents are matching.");
//			} else {
//				System.out.println("Both Contents are not matching.");
//			}
//
//		} catch (Exception e) {
//			System.out.println("Exception in Happy customer section: " + e.getMessage());
//			throw e;
//		}
//	}

	public void feedBack() {
		Actions action = new Actions(driver);
		homeLaunch();
		Common.waitForElement(2);
		click(feedBack);
		click(feedletsDoIT);
		Common.waitForElement(1);
		RandomMailId();
		action.moveToElement(continueFeed).click().build().perform();
		clickUsingJavaScript(feedCollectionYES);
		clickUsingJavaScript(feedNextButton);
		clickUsingJavaScript(feedSearchingYES);
		clickUsingJavaScript(feedNextButton);
		clickUsingJavaScript(feedStruggle2);
		clickUsingJavaScript(feedNextButton);
		clickUsingJavaScript(feedStarButton);
		clickUsingJavaScript(feedFinalContinue);
		try {
			WebElement feedBackCopy = driver.findElement(By.id("copyButton"));

			if (feedBackCopy.isDisplayed()) {
				clickUsingJavaScript(feedBackCopy);
				System.out.println("Feed Back copy button is clicked.");

			}

		} 
		catch (Exception e) {
			System.err.println(e);
		}

		String feedBackCoupon = feedBackformText.getText();
		System.out.println("Feed Back form submitted: " + feedBackCoupon);

	}

	public void backtoTop() {
		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 6200);");
		Common.waitForElement(1);
		WebElement backTop = driver.findElement(By.xpath("//div[@class='bottom_icons_box']"));
		try {
			if (backTop.isDisplayed()) {
				click(backTop);
				System.out.println("The Back to top button is clicked");
			}
		} catch (Exception e) {
			System.err.println(e);
		}




	}
	public void whatsApp() {
		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 6200);");
		Common.waitForElement(1);
		WebElement whatsApp = driver.findElement(By.id("whatsappIcon"));
		try {
			if (whatsApp.isDisplayed()) {
				click(whatsApp);
				System.out.println("The Whats app button is clicked");
				Common.waitForElement(2);
				String whatsAppNumber = driver.findElement(By.xpath("(//*[contains(text(),'7305380625')])[2]")).getText();;
				System.out.println("Whats App number displayed :" + whatsAppNumber);
			}
		} catch (Exception e) {
			System.err.println(e);
		}


	}
	//	public void featureOn() {
	//
	//		homeLaunch();
	//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 7000);");
	//		
	//		try {
	//
	//			List<WebElement> clickRandomFeatureOn = driver.findElements(By.xpath("//div[@class='featured__slider__main']//div[@class='swiper-slide']"));
	//			if (clickRandomFeatureOn.isEmpty()) {
	//				System.out.println("No Feature on section found. ");
	//				return;
	//			}
	//
	//			WebElement randomFeature = clickRandomFeatureOn.get(0);
	//			Collections.shuffle(clickRandomFeatureOn);
	//			clickUsingJavaScript(randomFeature);
	//			Common.waitForElement(5);
	//			WebElement FeatureOnRedirection = driver.findElement(By.xpath("//h3[@class='prod_list_topic']"));
	//			if (FeatureOnRedirection.isDisplayed()) {
	//				String pageHeading = FeatureOnRedirection.getText();
	//				System.out.println("Feature On Redirected sucessfull: " + pageHeading);
	//				Assert.assertTrue(verifyDisplayed(FeatureOnRedirection));
	// }
	//			else {
	//				Common.waitForElement(5);
	//				WebElement feature = driver.findElement(By.xpath("//h2[contains(text(),'Feature On')]"));
	//				String featureOn =feature.getText();
	//				System.out.println("The feature on redirecting on the same page " + featureOn);
	//				Assert.assertTrue(verifyDisplayed(feature));
	//			}
	//
	//
	//		} catch (Exception e) {
	//			System.err.println("Exception in Feature On Section : " + e.getMessage());
	//			throw e;
	//		}
	//
	//
	//	}
	public void featureOn() {

		homeLaunch();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 7000);");

		try {

			List<WebElement> clickRandomFeatureOn = driver.findElements(By.xpath("//div[@class='featured__slider__main']//div[@class='swiper-slide']"));
			if (clickRandomFeatureOn.isEmpty()) {
				System.out.println("No Feature on section found. ");
				return;
			}

			Collections.shuffle(clickRandomFeatureOn);
			WebElement randomFeature = clickRandomFeatureOn.get(0);
			clickUsingJavaScript(randomFeature);
			Common.waitForElement(5);

			List<WebElement> featureOnRedirection = driver.findElements(By.xpath("//h3[@class='prod_list_topic']"));
			if (!featureOnRedirection.isEmpty() && featureOnRedirection.get(0).isDisplayed()) {
				String pageHeading = featureOnRedirection.get(0).getText();
				System.out.println("Feature On Redirected successfully: " + pageHeading);
				Assert.assertTrue(verifyDisplayed(featureOnRedirection.get(0)));
			}
			else {
				List<WebElement> feature = driver.findElements(By.xpath("//h2[contains(text(),'Feature On')]"));
				if (!feature.isEmpty() && feature.get(0).isDisplayed()) {
					String featureOn = feature.get(0).getText();
					System.out.println("The feature on redirecting on the same page: " + featureOn);
					Assert.assertTrue(verifyDisplayed(feature.get(0)));
				} else {
					System.out.println("Neither of the expected pages were found.");
				}

				WebElement featureOnNxtBtn = driver.findElement(By.xpath("//*[@class='swiper-button-next featured_next_btn']"));
				if (featureOnNxtBtn.isDisplayed()) {
					clickUsingJavaScript(featureOnNxtBtn);
					System.out.println("Feature on Next Button clicked");	
				}
				WebElement featureOnBackBtn = driver.findElement(By.xpath("//*[@class='swiper-button-prev featured_prev_btn']"));
				if (featureOnBackBtn.isDisplayed()) {
					clickUsingJavaScript(featureOnBackBtn);
					System.out.println("Feature on Back Button clicked");

				}
			}
		}

		catch (Exception e) {
			System.out.println("Exception in featureOn: " + e.getMessage());
			throw e;
		}
	}



	public void allsectionHomePage() {
		homeLaunch();
		try {

			List<WebElement> elements = driver.findElements(By.xpath("//h2"));
			for (WebElement element : elements) {
				System.out.println(element.getText());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {

			List<WebElement> elements = driver.findElements(By.xpath("//h3"));
			for (WebElement element : elements) {
				System.out.println(element.getText());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}



	}



	public  void RandomMailId() {
		// Step 1: Generate a random email
		String randomEmail = generateRandomEmail();

		try {

			WebElement emailInput = driver.findElement(By.id("feedback_email"));
			emailInput.sendKeys(randomEmail);
			// Optionally print or use email later
			System.out.println("Random email entered: " + randomEmail);

		} finally {
			// Close the browser after a short delay for demo purposes
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Utility method to generate random email
	private static String generateRandomEmail() {
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder email = new StringBuilder();
		Random rnd = new Random();
		int length = 8;

		for (int i = 0; i < length; i++) {
			email.append(chars.charAt(rnd.nextInt(chars.length())));
		}

		return email.toString() + "@example.com";
	}



	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
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


