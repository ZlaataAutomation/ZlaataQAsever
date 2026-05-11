package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.EstimateDelivery_ObjRepo;
import utils.Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;

public class Estimatdelivery_Page extends EstimateDelivery_ObjRepo{
	public Estimatdelivery_Page(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
	}

	// ================= COLORS =================

	final String RESET = "\u001B[0m";

	final String RED = "\u001B[31m";
	final String GREEN = "\u001B[32m";
	final String YELLOW = "\u001B[33m";
	final String BLUE = "\u001B[34m";
	final String CYAN = "\u001B[36m";


	public void deleteAllProductsFromCart() {
		AdminEmailVerifyOrderFlowPage delete = new AdminEmailVerifyOrderFlowPage(driver);
		delete.deleteAllProductsFromCart();
	}


	public void adminLogin() {
		Common.waitForElement(2);
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
		type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
		type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
		click(adminLogin);
		System.out.println("✅ Admin Login Successfull");

	}


	public void sampleTest() {
		System.out.println("Test Case Pass");
	}




	public void userLoginApp() {

		LoginPage login = new LoginPage(driver);
		login.userLogin();
	}


	public void takeRandomProductFromAl() {

		//		HomePage home = new HomePage(driver);
		//		home.homeLaunch();
		//
		//		zlaataIndiaShopButton.click();

		userLoginApp();

		deleteAllProductsFromCart();


		Common.waitForElement(2);

		WebDriverWait wait =
				new WebDriverWait(driver,
						Duration.ofSeconds(20));

		Actions actions = new Actions(driver);

		// =========================================================
		// FIRST CATEGORY : DRESSES
		// =========================================================

		WebElement shopMenu =
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(
								By.xpath("//span[contains(@class,'header_nav_link') and translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='shop']")));

		actions.moveToElement(shopMenu).perform();

		WebElement dressesButton =
				wait.until(ExpectedConditions
						.elementToBeClickable(
								By.xpath("//div[contains(@class,'dropdown_content')]//a[normalize-space()='dresses']")));

		dressesButton.click();

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ Clicked on 'Dresses' under Shop Menu"
				+ RESET);

		Common.waitForElement(2);

		// =========================================================
		// FIRST RANDOM PRODUCT
		// =========================================================

		firstProductName =
				selectRandomProductAndStoreAllCategories();

		if (firstProductName != null) {

			productNames.add(firstProductName);

			System.out.println(GREEN
					+ "✅ First Product Stored : "
					+ firstProductName
					+ RESET);
		}

		// =========================================================
		// OPEN HOME AGAIN
		// =========================================================

		driver.get(FileReaderManager.getInstance()
				.getConfigReader()
				.getApplicationUrl());

		click(zlaataIndiaShopButton);

		Common.waitForElement(2);

		// =========================================================
		// SECOND CATEGORY : CO-ORDS
		// =========================================================

		WebElement shopMenu2 =
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(
								By.xpath("//span[contains(@class,'header_nav_link') and normalize-space()='Shop']")));

		actions.moveToElement(shopMenu2).perform();

		Common.waitForElement(2);

		WebElement coOrdsButton =
				wait.until(ExpectedConditions
						.elementToBeClickable(
								By.xpath("//div[contains(@class,'dropdown_content')]//a[normalize-space()='co-ords']")));

		coOrdsButton.click();

		Common.waitForElement(2);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ Clicked on 'Co-Ords' under Shop Menu"
				+ RESET);

		Common.waitForElement(2);

		// =========================================================
		// SECOND RANDOM PRODUCT
		// =========================================================

		secondProductName =
				selectRandomProductAndStoreAllCategories();

		if (secondProductName != null) {

			productNames.add(secondProductName);

			System.out.println(GREEN
					+ "✅ Second Product Stored : "
					+ secondProductName
					+ RESET);
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 FINAL STORED PRODUCTS"
				+ RESET);

		for (String product : productNames) {

			System.out.println(GREEN
					+ "✅ Product : "
					+ product
					+ RESET);
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);
	}

	public String selectRandomProductAndStoreAllCategories() {

		WebDriverWait wait =
				new WebDriverWait(driver,
						Duration.ofSeconds(20));

		List<WebElement> products =
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(
								By.xpath("//div[contains(@class,'prod_listing_card')]")));

		Common.waitForElement(2);

		if (products.isEmpty()) {

			System.out.println(YELLOW
					+ "⚠️ No Products Found"
					+ RESET);

			return null;
		}

		Random random = new Random();

		int maxAttempts =
				Math.min(5, products.size());

		boolean productSelected = false;

		String selectedProductName = "";

		for (int attempt = 1;
				attempt <= maxAttempts;
				attempt++) {

			int randomIndex =
					random.nextInt(products.size()) + 1;

			System.out.println(CYAN
					+ "🎯 Checking Product Index : "
					+ randomIndex
					+ RESET);

			WebElement productCard =
					driver.findElement(
							By.xpath("(//div[contains(@class,'prod_listing_card')])[" + randomIndex + "]"));

			// =========================================================
			// PRODUCT NAME
			// =========================================================

			selectedProductName =
					productCard.findElement(
							By.xpath(".//a[contains(@class,'product_list_name')]"))
					.getText()
					.trim();

			// =========================================================
			// OUT OF STOCK CHECK
			// =========================================================

			List<WebElement> outOfStockLabel =
					productCard.findElements(
							By.xpath(".//span[contains(@class,'prod_listing_hurry') and normalize-space()='Out of Stock']"));

			boolean isOutOfStock =
					!outOfStockLabel.isEmpty()
					&& outOfStockLabel.get(0).isDisplayed();

			if (isOutOfStock) {

				System.out.println(RED
						+ "❌ Product is Out Of Stock : "
						+ selectedProductName
						+ RESET);

				continue;
			}

			// =========================================================
			// CLICK PRODUCT
			// =========================================================

			WebElement productElement =
					productCard.findElement(
							By.xpath(".//a[contains(@class,'product_list_name')]"));

			((JavascriptExecutor) driver)
			.executeScript(
					"arguments[0].click();",
					productElement);

			Common.waitForElement(3);

			productSelected = true;

			System.out.println(GREEN
					+ "✅ Selected Random Product : "
					+ selectedProductName
					+ RESET);

			break;
		}

		if (!productSelected) {

			System.out.println(YELLOW
					+ "⚠️ No In-Stock Product Found"
					+ RESET);

			return null;
		}

		// =========================================================
		// PDP PRODUCT NAME
		// =========================================================

		String pdpProductName =
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(
								By.xpath("//h3[@class='prod_name']")))
				.getText()
				.trim();

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ PDP Product Name : "
				+ pdpProductName
				+ RESET);

		// =========================================================
		// CATEGORY TEXT
		// =========================================================

		String categoryText =
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(
								By.xpath("//div[@class='prod_category_wrap']")))
				.getText()
				.trim();

		System.out.println(GREEN
				+ "✅ PDP Category Text : "
				+ categoryText
				+ RESET);

		// =========================================================
		// STORE ALL PRODUCT CATEGORIES
		// =========================================================

		String[] categories =
				categoryText.split(",");

		List<String> categoryList =
				new ArrayList<>();

		for (String singleCategory : categories) {

			String finalCategory =
					singleCategory.trim();

			categoryList.add(finalCategory);

			System.out.println(CYAN
					+ "📌 Stored Category : "
					+ finalCategory
					+ RESET);
		}

		// =========================================================
		// STORE PRODUCT WISE CATEGORY
		// =========================================================

		productCategoryMap.put(
				pdpProductName,
				categoryList);

		// =========================================================
		// STORE UNIQUE CATEGORY NAMES
		// =========================================================

		for (String singleCategory : categoryList) {

			if (!categoryNames.contains(singleCategory)) {

				categoryNames.add(singleCategory);
			}
		}

		// =========================================================
		// ENTER PINCODE
		// =========================================================

		wait.until(ExpectedConditions
				.visibilityOf(pincodeTextBox));

		pincodeTextBox.clear();

		pincodeTextBox.sendKeys("110001");

		System.out.println(GREEN
				+ "✅ Entered Pincode : 110001"
				+ RESET);

		wait.until(ExpectedConditions
				.elementToBeClickable(checkButton));

		checkButton.click();

		Common.waitForElement(3);

		System.out.println(GREEN
				+ "✅ Clicked Check Button"
				+ RESET);

		// =========================================================
		// GET PDP DELIVERY DATE
		// =========================================================

		String estimatedDateText =
				wait.until(ExpectedConditions
						.visibilityOf(estimateDate))
				.getText()
				.trim();

		System.out.println(GREEN
				+ "✅ PDP Estimated Date : "
				+ estimatedDateText
				+ RESET);

		LocalDate today =
				LocalDate.now();

		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(
						"MMMM d, yyyy",
						Locale.ENGLISH);

		LocalDate estimatedDate =
				LocalDate.parse(
						estimatedDateText,
						formatter);

		long totalDays =
				ChronoUnit.DAYS.between(
						today,
						estimatedDate);

		int applicationDeliveryDays =
				(int) totalDays;

		productPdpDaysMap.put(
				pdpProductName,
				applicationDeliveryDays);

		System.out.println(CYAN
				+ "📌 PDP Delivery Days : "
				+ applicationDeliveryDays
				+ RESET);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		return pdpProductName;
	}

	// =========================================================
	// GLOBAL VARIABLES
	// =========================================================

	String firstProductName = "";
	String secondProductName = "";

	int generalSettingDays = 0;

	List<String> categoryNames = new ArrayList<>();
	List<String> productNames = new ArrayList<>();
	List<String> adminMainProductNames = new ArrayList<>();

	Map<String, Integer> productPdpDaysMap =
			new HashMap<>();

	Map<String, List<String>> productCategoryMap =
			new HashMap<>();

	Map<String, List<Integer>> productCategoryDaysMap =
			new HashMap<>();

	Map<String, List<Integer>> productCollectionDaysMap =
			new HashMap<>();

	Map<String, String> applicationToAdminProductMap =
			new HashMap<>();

	// =========================================================
	// VERIFY ESTIMATE DELIVERY FOR MULTIPLE RANDOM PRODUCTS
	// =========================================================

	public void verifyEstimateDeliveryForMultipleRandomProducts() {

		WebDriverWait wait =
				new WebDriverWait(driver,
						Duration.ofSeconds(20));

		List<Integer> adminDeliveryDaysList =
				new ArrayList<>();

		List<String> highestSourceList =
				new ArrayList<>();

		List<String> adminMainProductNames =
				new ArrayList<>();

		List<Integer> collectionDeliveryDaysList =
				new ArrayList<>();

		// =========================================================
		// STEP 1 : RANDOM PRODUCTS
		// =========================================================

		takeRandomProductFromAl();

		// =========================================================
		// STEP 2 : ADMIN LOGIN
		// =========================================================

		adminLogin();

		Common.waitForElement(2);

		String baseUrl =
				driver.getCurrentUrl()
				.split("/admin")[0];

		// =========================================================
		// STEP 3 : GENERAL SETTINGS
		// =========================================================

		driver.get(baseUrl + "/admin/general-setting");

		wait.until(ExpectedConditions
				.elementToBeClickable(clickOnSetkey));

		clickOnSetkey.click();

		wait.until(ExpectedConditions
				.visibilityOf(searchBoxdropdown));

		searchBoxdropdown.clear();

		searchBoxdropdown.sendKeys("estimate_date");

		searchBoxdropdown.sendKeys(Keys.ENTER);

		Common.waitForElement(2);

		wait.until(ExpectedConditions
				.elementToBeClickable(editItemButton));

		editItemButton.click();

		Common.waitForElement(2);

		String adminDeliveryDays =
				wait.until(ExpectedConditions
						.visibilityOf(estimateDeliveryDateDays))
				.getAttribute("value")
				.trim();

		if (!adminDeliveryDays.isEmpty()) {

			generalSettingDays =
					Integer.parseInt(adminDeliveryDays);

			adminDeliveryDaysList.add(generalSettingDays);

			highestSourceList.add(
					"GENERAL SETTING -> "
							+ generalSettingDays);

			System.out.println(GREEN
					+ "✅ General Setting Delivery Days : "
					+ generalSettingDays
					+ RESET);
		}

		// =========================================================
		// STEP 4 : CATEGORY SECTION
		// =========================================================

		for (String categoryName : categoryNames) {

			try {

				System.out.println(BLUE
						+ "================================================="
						+ RESET);

				System.out.println(CYAN
						+ "🔍 Checking Category : "
						+ categoryName
						+ RESET);

				driver.get(baseUrl + "/admin/categories");

				wait.until(ExpectedConditions
						.elementToBeClickable(categoryMenu));

				categoryMenu.click();

				Common.waitForElement(2);

				searchBoxdropdown.clear();

				searchBoxdropdown.sendKeys(categoryName);

				Common.waitForElement(2);

				searchBoxdropdown.sendKeys(Keys.ENTER);

				Common.waitForElement(3);

				wait.until(ExpectedConditions.invisibilityOfElementLocated(
						By.id("crudTable_processing")));

				List<WebElement> editButtons =
						driver.findElements(
								By.xpath("//a[contains(@class,'edit')]"));

				if (editButtons.isEmpty()) {

					System.out.println(YELLOW
							+ "⚠️ Category Not Available : "
							+ categoryName
							+ RESET);

					continue;
				}

				WebElement editButton =
						wait.until(ExpectedConditions
								.elementToBeClickable(
										By.xpath("(//a[contains(@class,'edit')])[1]")));

				((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].click();",
						editButton);

				Common.waitForElement(2);

				String categoryDeliveryDays =
						wait.until(ExpectedConditions
								.visibilityOf(categoryDeliveryDate))
						.getAttribute("value")
						.trim();

				// =========================================================
				// EMPTY DAYS CHECK
				// =========================================================

				if (categoryDeliveryDays.isEmpty()) {

					System.out.println(YELLOW
							+ "⚠️ No Delivery Days For Category : "
							+ categoryName
							+ RESET);

					continue;
				}

				int categoryDays =
						Integer.parseInt(categoryDeliveryDays);

				adminDeliveryDaysList.add(categoryDays);

				highestSourceList.add(
						"CATEGORY -> "
								+ categoryName
								+ " -> "
								+ categoryDays);

				System.out.println(GREEN
						+ "✅ Category Delivery Days : "
						+ categoryDays
						+ RESET);

				// =========================================================
				// STORE CATEGORY DAYS PRODUCT WISE
				// =========================================================

				for (String productName : productCategoryMap.keySet()) {

					List<String> productCategories =
							productCategoryMap.get(productName);

					if (productCategories != null
							&& productCategories.contains(categoryName)) {

						// CREATE PRODUCT LIST

						productCategoryDaysMap.putIfAbsent(
								productName,
								new ArrayList<>());

						// STORE CATEGORY DAYS

						productCategoryDaysMap
						.get(productName)
						.add(categoryDays);

						System.out.println(GREEN
								+ "✅ Product : "
								+ productName
								+ " | Category : "
								+ categoryName
								+ " | Delivery Days : "
								+ categoryDays
								+ RESET);
					}
				}

			} catch (Exception e) {

				System.out.println(RED
						+ "❌ Failed Category : "
						+ categoryName
						+ RESET);

				System.out.println(RED
						+ "❌ Error : "
						+ e.getMessage()
						+ RESET);
			}
		}
		// =========================================================
		// STEP 5 : PRODUCT SECTION
		// =========================================================

		for (String singleProduct : productNames) {

			try {

				System.out.println(BLUE
						+ "================================================="
						+ RESET);

				System.out.println(CYAN
						+ "🔍 Checking Product : "
						+ singleProduct
						+ RESET);

				driver.get(baseUrl + "/admin/product");

				Common.waitForElement(3);

				wait.until(ExpectedConditions
						.elementToBeClickable(productDetailNameTextBox));

				productDetailNameTextBox.click();

				Common.waitForElement(2);

				productDetailsTextBox.clear();

				Common.waitForElement(1);

				productDetailsTextBox.sendKeys(Keys.CONTROL + "a");

				productDetailsTextBox.sendKeys(Keys.DELETE);

				Common.waitForElement(1);

				productDetailsTextBox.sendKeys(singleProduct);

				Common.waitForElement(2);

				productDetailsTextBox.sendKeys(Keys.ENTER);

				Common.waitForElement(5);

				wait.until(ExpectedConditions.invisibilityOfElementLocated(
						By.id("crudTable_processing")));

				List<WebElement> productEditButtons =
						driver.findElements(
								By.xpath("//a[contains(@class,'edit')]"));

				if (productEditButtons.isEmpty()) {

					System.out.println(YELLOW
							+ "⚠️ Product Not Available In Admin : "
							+ singleProduct
							+ RESET);

					continue;
				}

				WebElement editButton =
						wait.until(ExpectedConditions
								.elementToBeClickable(
										By.xpath("(//a[contains(@class,'edit')])[1]")));

				((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].click();",
						editButton);

				Common.waitForElement(3);

				String productMainName =
						wait.until(ExpectedConditions
								.visibilityOf(PrdouctMainName))
						.getAttribute("value")
						.trim();

				adminMainProductNames.add(productMainName);

				// =========================================================
				// STORE APPLICATION PRODUCT -> ADMIN PRODUCT
				// =========================================================

				applicationToAdminProductMap.put(
						singleProduct,
						productMainName);

				System.out.println(GREEN
						+ "✅ Product Main Name : "
						+ productMainName
						+ RESET);

			} catch (Exception e) {

				System.out.println(YELLOW
						+ "⚠️ Unable To Open Product : "
						+ singleProduct
						+ RESET);

				System.out.println(RED
						+ "❌ Error : "
						+ e.getMessage()
						+ RESET);
			}
		}

		// =========================================================
		// FINAL STORED PRODUCT MAIN NAMES
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 STORED ADMIN PRODUCT MAIN NAMES"
				+ RESET);

		for (String mainProduct : adminMainProductNames) {

			System.out.println(GREEN
					+ "✅ " + mainProduct
					+ RESET);
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		// =========================================================
		// STEP 6 : COLLECTION SECTION
		// =========================================================

		for (String applicationProduct : productNames) {

			try {

				String adminProductName =
						applicationToAdminProductMap.get(applicationProduct);

				System.out.println(BLUE
						+ "================================================="
						+ RESET);

				System.out.println(CYAN
						+ "🔍 Opening Collection For Product : "
						+ adminProductName
						+ RESET);

				driver.get(baseUrl + "/admin/product-collection");

				Common.waitForElement(3);

				wait.until(ExpectedConditions
						.elementToBeClickable(statusButton));

				statusButton.click();

				Common.waitForElement(2);

				wait.until(ExpectedConditions
						.elementToBeClickable(activeButton));

				activeButton.click();

				Common.waitForElement(2);

				wait.until(ExpectedConditions
						.elementToBeClickable(productNameMenu));

				productNameMenu.click();

				Common.waitForElement(2);

				searchBoxdropdown.clear();

				searchBoxdropdown.sendKeys(adminProductName);

				Common.waitForElement(2);

				searchBoxdropdown.sendKeys(Keys.ENTER);

				Common.waitForElement(5);

				System.out.println(GREEN
						+ "✅ Collection Search Done For : "
						+ adminProductName
						+ RESET);

				List<WebElement> collectionButtons =
						driver.findElements(
								By.xpath("//a[contains(@class,'edit')]"));

				if (collectionButtons.isEmpty()) {

					System.out.println(YELLOW
							+ "⚠️ No Collection Available For : "
							+ adminProductName
							+ RESET);

					continue;
				}

				System.out.println(GREEN
						+ "✅ Total Collections Found : "
						+ collectionButtons.size()
						+ RESET);

				for (int i = 1;
						i <= collectionButtons.size();
						i++) {

					try {

						WebElement collectionEditButton =
								wait.until(ExpectedConditions
										.elementToBeClickable(
												By.xpath("(//a[contains(@class,'edit')])[" + i + "]")));

						((JavascriptExecutor) driver)
						.executeScript(
								"arguments[0].click();",
								collectionEditButton);

						Common.waitForElement(3);

						String collectionName =
								wait.until(ExpectedConditions
										.visibilityOf(collectionNameTextBox))
								.getAttribute("value")
								.trim();

						System.out.println(GREEN
								+ "✅ Collection Name : "
								+ collectionName
								+ RESET);

						String collectionDeliveryDate =
								wait.until(ExpectedConditions
										.visibilityOf(categoryDeliveryDate))
								.getAttribute("value")
								.trim();

						if (collectionDeliveryDate.isEmpty()) {

							System.out.println(YELLOW
									+ "⚠️ No Delivery Days For Collection : "
									+ collectionName
									+ RESET);

						} else {

							int collectionDays =
									Integer.parseInt(collectionDeliveryDate);

							collectionDeliveryDaysList.add(collectionDays);

							adminDeliveryDaysList.add(collectionDays);

							highestSourceList.add(
									"COLLECTION -> "
											+ collectionName
											+ " -> "
											+ collectionDays);

							productCollectionDaysMap
							.computeIfAbsent(
									applicationProduct,
									k -> new ArrayList<>())
							.add(collectionDays);

							System.out.println(GREEN
									+ "✅ Collection Delivery Days : "
									+ collectionDays
									+ RESET);
						}

						driver.navigate().back();

						Common.waitForElement(3);

					} catch (Exception e) {

						System.out.println(RED
								+ "❌ Failed Collection Index : "
								+ i
								+ RESET);

						System.out.println(RED
								+ "❌ Error : "
								+ e.getMessage()
								+ RESET);
					}
				}

			} catch (Exception e) {

				System.out.println(RED
						+ "❌ Failed Collection Search"
						+ RESET);

				System.out.println(RED
						+ "❌ Error : "
						+ e.getMessage()
						+ RESET);
			}
		}

		// =========================================================
		// FINAL PRODUCT WISE DELIVERY REPORT
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 FINAL PRODUCT WISE DELIVERY REPORT"
				+ RESET);

		for (String product : productPdpDaysMap.keySet()) {

			System.out.println(BLUE
					+ "================================================="
					+ RESET);

			System.out.println(GREEN
					+ "✅ PRODUCT : "
					+ product
					+ RESET);

			int pdpDays =
					productPdpDaysMap.get(product);

			System.out.println(CYAN
					+ "📌 PDP Delivery Days : "
					+ pdpDays
					+ RESET);

			System.out.println(CYAN
					+ "📌 General Setting Days : "
					+ generalSettingDays
					+ RESET);

			List<Integer> categoryDaysList =
					productCategoryDaysMap.get(product);

			if (categoryDaysList != null) {

				for (Integer catDays : categoryDaysList) {

					System.out.println(CYAN
							+ "📌 Category Delivery Days : "
							+ catDays
							+ RESET);
				}
			}

			List<Integer> collectionDaysList =
					productCollectionDaysMap.get(product);

			if (collectionDaysList != null) {

				for (Integer colDays : collectionDaysList) {

					System.out.println(CYAN
							+ "📌 Collection Delivery Days : "
							+ colDays
							+ RESET);
				}
			}

			// =========================================================
			// ADMIN SIDE HIGHEST DATE
			// =========================================================

			List<Integer> adminDays =
					new ArrayList<>();

			adminDays.add(generalSettingDays);

			if (categoryDaysList != null) {

				adminDays.addAll(categoryDaysList);
			}

			if (collectionDaysList != null) {

				adminDays.addAll(collectionDaysList);
			}

			int adminHighestDays =
					Collections.max(adminDays);

			System.out.println(GREEN
					+ "✅ ADMIN HIGHEST DELIVERY DAYS : "
					+ adminHighestDays
					+ RESET);

			// =========================================================
			// PDP VS ADMIN VALIDATION
			// =========================================================

			if (pdpDays == adminHighestDays) {

				System.out.println(GREEN
						+ "✅ PDP DELIVERY DATE MATCHED WITH ADMIN HIGHEST DELIVERY DAYS"
						+ RESET);

			} else {

				System.out.println(RED
						+ "❌ PDP DELIVERY DATE NOT MATCHED WITH ADMIN HIGHEST DELIVERY DAYS"
						+ RESET);

				System.out.println(RED
						+ "❌ PDP Days : "
						+ pdpDays
						+ RESET);

				System.out.println(RED
						+ "❌ Admin Highest Days : "
						+ adminHighestDays
						+ RESET);
			}

			// =========================================================
			// PRINT HIGHEST SOURCE
			// =========================================================

			if (adminHighestDays == generalSettingDays) {

				System.out.println(YELLOW
						+ "➡️ Highest Source : GENERAL SETTINGS"
						+ RESET);
			}

			if (categoryDaysList != null) {

				for (Integer catDays : categoryDaysList) {

					if (adminHighestDays == catDays) {

						System.out.println(YELLOW
								+ "➡️ Highest Source : CATEGORY"
								+ RESET);
					}
				}
			}

			if (collectionDaysList != null) {

				for (Integer colDays : collectionDaysList) {

					if (adminHighestDays == colDays) {

						System.out.println(YELLOW
								+ "➡️ Highest Source : COLLECTION"
								+ RESET);
					}
				}
			}
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		// =========================================================
		// FINAL OVERALL PRODUCT COMPARISON
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 FINAL PRODUCT HIGHEST DELIVERY COMPARISON"
				+ RESET);

		String highestProductName = "";
		int overallHighestDays = 0;

		for (String product : productPdpDaysMap.keySet()) {

			int pdpDays =
					productPdpDaysMap.get(product);

			List<Integer> categoryDaysList =
					productCategoryDaysMap.get(product);

			List<Integer> collectionDaysList =
					productCollectionDaysMap.get(product);

			List<Integer> allAdminDays =
					new ArrayList<>();

			allAdminDays.add(generalSettingDays);

			if (categoryDaysList != null) {

				allAdminDays.addAll(categoryDaysList);
			}

			if (collectionDaysList != null) {

				allAdminDays.addAll(collectionDaysList);
			}

			int adminHighestDays =
					Collections.max(allAdminDays);

			System.out.println(GREEN
					+ "✅ Product : "
					+ product
					+ RESET);

			System.out.println(CYAN
					+ "📌 PDP Days : "
					+ pdpDays
					+ RESET);

			System.out.println(CYAN
					+ "📌 Admin Highest Days : "
					+ adminHighestDays
					+ RESET);

			// =========================================================
			// STORE OVERALL HIGHEST PRODUCT
			// =========================================================

			if (adminHighestDays > overallHighestDays) {

				overallHighestDays =
						adminHighestDays;

				highestProductName =
						product;
			}
		}

		// =========================================================
		// FINAL HIGHEST PRODUCT RESULT
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ OVERALL HIGHEST DELIVERY PRODUCT : "
				+ highestProductName
				+ RESET);

		System.out.println(GREEN
				+ "✅ OVERALL HIGHEST DELIVERY DAYS : "
				+ overallHighestDays
				+ RESET);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		// =========================================================
		// OVERALL HIGHEST DELIVERY DAYS
		// =========================================================

		int overallHighestDeliveryDays =
				Collections.max(adminDeliveryDaysList);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ OVERALL HIGHEST DELIVERY DAYS : "
				+ overallHighestDeliveryDays
				+ RESET);


		// =========================================================
		// STEP 7 : VERIFY CHECKOUT ESTIMATE DATE
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 VERIFY CHECKOUT ESTIMATE DELIVERY DATE"
				+ RESET);

		boolean firstProductAdded = false;

		for (String product : productPdpDaysMap.keySet()) {

			try {

				System.out.println(BLUE
						+ "================================================="
						+ RESET);

				System.out.println(CYAN
						+ "🔍 Opening Product In Application : "
						+ product
						+ RESET);




				// =========================================================
				// OPEN APPLICATION ONLY FIRST TIME
				// =========================================================

				if (!firstProductAdded) {

					driver.get(FileReaderManager.getInstance()
							.getConfigReader()
							.getApplicationUrl());

					Common.waitForElement(3);

					click(zlaataIndiaShopButton);

					Common.waitForElement(3);
				}

				// =========================================================
				// SEARCH PRODUCT
				// =========================================================

				wait.until(ExpectedConditions
						.elementToBeClickable(clickOnSearchBar));

				clickOnSearchBar.click();

				Common.waitForElement(2);

				wait.until(ExpectedConditions
						.visibilityOf(searchBoxPlaceholder));

				searchBoxPlaceholder.clear();

				Common.waitForElement(1);

				searchBoxPlaceholder.sendKeys(product);

				Common.waitForElement(2);

				searchBoxPlaceholder.sendKeys(Keys.ENTER);

				System.out.println(GREEN
						+ "✅ Product Search Done : "
						+ product
						+ RESET);

				Common.waitForElement(5);

				// =========================================================
				// OPEN PRODUCT
				// =========================================================

				WebElement searchedProduct =
						wait.until(ExpectedConditions
								.elementToBeClickable(
										By.xpath("(//a[contains(@class,'product_list_name')])[1]")));

				((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].click();",
						searchedProduct);

				Common.waitForElement(3);

				System.out.println(GREEN
						+ "✅ Product Opened : "
						+ product
						+ RESET);

				// =========================================================
				// CLICK BUY NOW
				// =========================================================

				wait.until(ExpectedConditions
						.elementToBeClickable(buyNowButton));

				buyNowButton.click();

				Common.waitForElement(5);

				System.out.println(GREEN
						+ "✅ Clicked Buy Now"
						+ RESET);

				// =========================================================
				// FIRST PRODUCT ONLY ADD
				// =========================================================

				if (!firstProductAdded) {

					firstProductAdded = true;

					System.out.println(GREEN
							+ "✅ First Product Added Into Checkout"
							+ RESET);

					continue;
				}

				// =========================================================
				// AFTER SECOND PRODUCT VERIFY DATE
				// =========================================================

				String checkoutEstimateDate =
						wait.until(ExpectedConditions
								.visibilityOf(checkoutEstimateDateText))
						.getText()
						.trim();

				System.out.println(GREEN
						+ "✅ Checkout Estimate Date : "
						+ checkoutEstimateDate
						+ RESET);

				// =========================================================
				// CLEAN DATE TEXT
				// =========================================================

				checkoutEstimateDate =
						checkoutEstimateDate
						.replace("Estimated delivery date:", "")
						.trim();

				// =========================================================
				// ADD CURRENT YEAR
				// =========================================================

				int currentYear =
						LocalDate.now().getYear();

				checkoutEstimateDate =
						checkoutEstimateDate
						+ " "
						+ currentYear;

				// =========================================================
				// CONVERT DATE TO DAYS
				// =========================================================

				LocalDate today =
						LocalDate.now();

				DateTimeFormatter formatter =
						DateTimeFormatter.ofPattern(
								"d MMM yyyy",
								Locale.ENGLISH);

				LocalDate checkoutDate =
						LocalDate.parse(
								checkoutEstimateDate,
								formatter);

				int checkoutDays =
						(int) ChronoUnit.DAYS.between(
								today,
								checkoutDate);

				System.out.println(CYAN
						+ "📌 Checkout Delivery Days : "
						+ checkoutDays
						+ RESET);

				// =========================================================
				// FINAL VALIDATION
				// =========================================================

				System.out.println(CYAN
						+ "📌 OVERALL Admin Highest Days : "
						+ overallHighestDeliveryDays
						+ RESET);

				if (checkoutDays == overallHighestDeliveryDays) {

					System.out.println(GREEN
							+ "✅ CHECKOUT DELIVERY DATE MATCHED WITH OVERALL ADMIN HIGHEST DELIVERY DAYS"
							+ RESET);

				} else {

					System.out.println(RED
							+ "❌ CHECKOUT DELIVERY DATE NOT MATCHED"
							+ RESET);

					System.out.println(RED
							+ "❌ Checkout Days : "
							+ checkoutDays
							+ RESET);

					System.out.println(RED
							+ "❌ Overall Admin Highest Days : "
							+ overallHighestDeliveryDays
							+ RESET);
				}

				break;

			} catch (Exception e) {

				System.out.println(RED
						+ "❌ Failed Checkout Validation For Product : "
						+ product
						+ RESET);

				System.out.println(RED
						+ "❌ Error : "
						+ e.getMessage()
						+ RESET);
			}
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);
	}

	//second Test case 

	public String takeRandomProductFromAll() {

		HomePage home = new HomePage(driver);
		home.homeLaunch();

		zlaataIndiaShopButton.click();

		Common.waitForElement(2);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//
		Actions actions = new Actions(driver);

		// ================= SHOP MENU =================

		WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(@class,'header_nav_link') and translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='shop']")));

		actions.moveToElement(shopMenu).perform();

		// Click Dresses
		WebElement dressesButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'dropdown_content')]//a[normalize-space()='dresses']")));

		dressesButton.click();

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ Clicked on 'Dresses' under Shop Menu"
				+ RESET);

		Common.waitForElement(2);

		// ================= PRODUCT LIST =================

		List<WebElement> products = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//div[contains(@class,'prod_listing_card')]")));

		if (products.isEmpty()) {

			System.out.println(YELLOW
					+ "⚠️ No Products Found on Product Listing Page"
					+ RESET);

			return null;
		}

		Random random = new Random();

		int maxAttempts = Math.min(5, products.size());

		boolean productSelected = false;

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {

			int randomIndex = random.nextInt(products.size()) + 1;

			System.out.println(CYAN
					+ "🎯 Checking Product Index : "
					+ randomIndex
					+ RESET);

			WebElement productCard = driver.findElement(
					By.xpath("(//div[contains(@class,'prod_listing_card')])[" + randomIndex + "]"));

			// Get Product Name
			String selectedProductName = productCard.findElement(
					By.xpath(".//a[contains(@class,'product_list_name')]"))
					.getText()
					.trim();

			// Check Out Of Stock
			List<WebElement> outOfStockLabel = productCard.findElements(
					By.xpath(".//span[contains(@class,'prod_listing_hurry') and normalize-space()='Out of Stock']"));

			boolean isOutOfStock = !outOfStockLabel.isEmpty()
					&& outOfStockLabel.get(0).isDisplayed();

			if (isOutOfStock) {

				System.out.println(RED
						+ "❌ Product is Out Of Stock : "
						+ selectedProductName
						+ RESET);

				continue;
			}

			// Store Product Name
			productName = selectedProductName;

			// Click Product
			WebElement productElement = productCard.findElement(
					By.xpath(".//a[contains(@class,'product_list_name')]"));

			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();",
					productElement);

			productSelected = true;

			System.out.println(GREEN
					+ "✅ Selected Random Product : "
					+ productName
					+ RESET);

			break;
		}

		if (!productSelected) {

			System.out.println(YELLOW
					+ "⚠️ No In-Stock Product Found"
					+ RESET);

			return null;
		}

		// ================= PDP PRODUCT =================

		String pdpProductName = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//h3[@class='prod_name']")))
				.getText()
				.trim();

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ PDP Product Name : "
				+ pdpProductName
				+ RESET);

		// ================= CATEGORY =================

		String categoryText = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='prod_category_wrap']")))
				.getText()
				.trim();

		System.out.println(GREEN
				+ "✅ PDP Category Text : "
				+ categoryText
				+ RESET);

		// Split Multiple Categories
		String[] categories = categoryText.split(",");

		for (String category : categories) {

			String trimmedCategory = category.trim();

			categoryNames.add(trimmedCategory);

			System.out.println(CYAN
					+ "📌 Stored Category : "
					+ trimmedCategory
					+ RESET);
		}

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		return pdpProductName;
	}


	String productName;
	List<String> categoryNames1 = new ArrayList<>();
	String ProductMainNameInadminPaenl;

	public void verifyEstimateDeliveryBasedOnHighestAdminValueforOneProduct() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));



		// ================= STORE ALL DELIVERY DAYS =================

		List<Integer> adminDeliveryDaysList = new ArrayList<>();

		List<String> highestSourceList = new ArrayList<>();

		// =========================================================
		// STEP 1 : OPEN RANDOM PRODUCT
		// =========================================================

		String pdpProductName = takeRandomProductFromAll();

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(GREEN
				+ "✅ Selected Product : "
				+ pdpProductName
				+ RESET);

		// =========================================================
		// STEP 2 : ADMIN LOGIN
		// =========================================================

		adminLogin();

		Common.waitForElement(2);

		String baseUrl = driver.getCurrentUrl().split("/admin")[0];

		System.out.println(GREEN
				+ "✅ Admin Login Successful"
				+ RESET);

		// =========================================================
		// STEP 3 : GENERAL SETTING
		// =========================================================

		driver.get(baseUrl + "/admin/general-setting");

		wait.until(ExpectedConditions.elementToBeClickable(clickOnSetkey));
		clickOnSetkey.click();

		wait.until(ExpectedConditions.visibilityOf(searchBoxdropdown));

		searchBoxdropdown.clear();
		searchBoxdropdown.sendKeys("estimate_date");
		searchBoxdropdown.sendKeys(Keys.ENTER);

		Common.waitForElement(2);

		wait.until(ExpectedConditions.elementToBeClickable(editItemButton));
		editItemButton.click();

		Common.waitForElement(2);

		String adminDeliveryDays = wait.until(ExpectedConditions
				.visibilityOf(estimateDeliveryDateDays))
				.getAttribute("value")
				.trim();

		if (!adminDeliveryDays.isEmpty()) {

			int generalDays = Integer.parseInt(adminDeliveryDays);

			adminDeliveryDaysList.add(generalDays);

			highestSourceList.add("GENERAL SETTING -> " + generalDays);

			System.out.println(GREEN
					+ "✅ General Setting Delivery Days : "
					+ generalDays
					+ RESET);
		}

		// =========================================================
		// STEP 4 : CATEGORY SECTION
		// =========================================================

		for (String categoryName : categoryNames) {

			try {

				System.out.println(BLUE
						+ "================================================="
						+ RESET);

				System.out.println(CYAN
						+ "🔍 Checking Category : "
						+ categoryName
						+ RESET);

				driver.get(baseUrl + "/admin/categories");

				wait.until(ExpectedConditions.elementToBeClickable(categoryMenu));
				categoryMenu.click();

				Common.waitForElement(2);

				searchBoxdropdown.clear();
				searchBoxdropdown.sendKeys(categoryName);

				Common.waitForElement(2);

				searchBoxdropdown.sendKeys(Keys.ENTER);

				Common.waitForElement(3);

				wait.until(ExpectedConditions.elementToBeClickable(editItemButton));
				editItemButton.click();

				Common.waitForElement(2);

				String categoryDeliveryDays = wait.until(ExpectedConditions
						.visibilityOf(categoryDeliveryDate))
						.getAttribute("value")
						.trim();

				if (categoryDeliveryDays.isEmpty()) {

					System.out.println(YELLOW
							+ "⚠️ No Delivery Days for Category : "
							+ categoryName
							+ RESET);

					continue;
				}

				int categoryDays = Integer.parseInt(categoryDeliveryDays);

				adminDeliveryDaysList.add(categoryDays);

				highestSourceList.add("CATEGORY -> "
						+ categoryName + " -> " + categoryDays);

				System.out.println(GREEN
						+ "✅ Category Delivery Days : "
						+ categoryDays
						+ RESET);

			} catch (Exception e) {

				System.out.println(RED
						+ "❌ Failed Category : "
						+ categoryName
						+ RESET);
			}
		}


		System.out.println(BLUE
				+ "================================================="
				+ RESET);


		// =========================================================
		// STEP 5 : PRODUCT SECTION
		//	     =========================================================

		driver.get(baseUrl + "/admin/product");

		Common.waitForElement(2);

		wait.until(ExpectedConditions.elementToBeClickable(productDetailNameTextBox));
		productDetailNameTextBox.click();

		Common.waitForElement(2);

		productDetailsTextBox.clear();
		productDetailsTextBox.sendKeys(productName);

		Common.waitForElement(2);

		productDetailsTextBox.sendKeys(Keys.ENTER);

		Common.waitForElement(3);

		wait.until(ExpectedConditions.elementToBeClickable(editItemButton));
		editItemButton.click();

		Common.waitForElement(2);

		ProductMainNameInadminPaenl = wait.until(ExpectedConditions
				.visibilityOf(PrdouctMainName))
				.getAttribute("value")
				.trim();

		System.out.println(GREEN
				+ "✅ Product Main Name : "
				+ ProductMainNameInadminPaenl
				+ RESET);

		// =========================================================
		// =========================================================
		// STEP 6 : PRODUCT COLLECTION
		// =========================================================

		driver.get(baseUrl + "/admin/product-collection");

		Common.waitForElement(3);

		wait.until(ExpectedConditions.elementToBeClickable(statusButton));
		statusButton.click();

		Common.waitForElement(2);

		wait.until(ExpectedConditions.elementToBeClickable(activeButton));
		activeButton.click();

		Common.waitForElement(3);

		wait.until(ExpectedConditions.elementToBeClickable(productNameMenu));
		productNameMenu.click();

		Common.waitForElement(2);

		searchBoxdropdown.clear();
		searchBoxdropdown.sendKeys(ProductMainNameInadminPaenl);

		Common.waitForElement(2);

		searchBoxdropdown.sendKeys(Keys.ENTER);

		Common.waitForElement(5);

		// ================= CHECK COLLECTION AVAILABLE =================

		List<WebElement> totalCollections = driver.findElements(
				By.xpath("//a[contains(@class,'edit')]"));

		if (totalCollections.isEmpty()) {

			System.out.println(YELLOW
					+ "⚠️ Product Collection Not Available For Product : "
					+ ProductMainNameInadminPaenl
					+ RESET);

		} else {

			System.out.println(GREEN
					+ "✅ Total Collections Found : "
					+ totalCollections.size()
					+ RESET);

			for (int i = 1; i <= totalCollections.size(); i++) {

				try {

					System.out.println(BLUE
							+ "================================================="
							+ RESET);

					System.out.println(CYAN
							+ "🔍 Opening Collection Index : "
							+ i
							+ RESET);

					driver.get(baseUrl + "/admin/product-collection");

					Common.waitForElement(3);

					wait.until(ExpectedConditions.elementToBeClickable(statusButton));
					statusButton.click();

					Common.waitForElement(2);

					wait.until(ExpectedConditions.elementToBeClickable(activeButton));
					activeButton.click();

					Common.waitForElement(3);

					wait.until(ExpectedConditions.elementToBeClickable(productNameMenu));
					productNameMenu.click();

					Common.waitForElement(2);

					searchBoxdropdown.clear();
					searchBoxdropdown.sendKeys(ProductMainNameInadminPaenl);

					Common.waitForElement(2);

					searchBoxdropdown.sendKeys(Keys.ENTER);

					Common.waitForElement(5);

					List<WebElement> editButtons = driver.findElements(
							By.xpath("(//a[contains(@class,'edit')])[" + i + "]"));

					if (editButtons.isEmpty()) {

						System.out.println(YELLOW
								+ "⚠️ Collection Edit Button Not Found For Index : "
								+ i
								+ RESET);

						continue;
					}

					WebElement editButton = wait.until(
							ExpectedConditions.elementToBeClickable(
									By.xpath("(//a[contains(@class,'edit')])[" + i + "]")));

					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView({block:'center'});",
							editButton);

					Common.waitForElement(2);

					((JavascriptExecutor) driver).executeScript(
							"arguments[0].click();",
							editButton);

					Common.waitForElement(3);

					String collectionName = wait.until(ExpectedConditions
							.visibilityOf(collectionNameTextBox))
							.getAttribute("value")
							.trim();

					String collectionDeliveryDays = wait.until(ExpectedConditions
							.visibilityOf(categoryDeliveryDate))
							.getAttribute("value")
							.trim();

					if (collectionDeliveryDays.isEmpty()) {

						System.out.println(YELLOW
								+ "⚠️ No Delivery Days For Collection : "
								+ collectionName
								+ RESET);

						continue;
					}

					int collectionDays = Integer.parseInt(collectionDeliveryDays);

					adminDeliveryDaysList.add(collectionDays);

					highestSourceList.add(
							"COLLECTION -> "
									+ collectionName
									+ " -> "
									+ collectionDays);

					System.out.println(GREEN
							+ "✅ Collection Name : "
							+ collectionName
							+ RESET);

					System.out.println(GREEN
							+ "✅ Collection Delivery Days : "
							+ collectionDays
							+ RESET);

				} catch (Exception e) {

					System.out.println(RED
							+ "❌ Failed Collection Index : "
							+ i
							+ RESET);

					System.out.println(YELLOW
							+ "⚠️ Continuing With Next Collection"
							+ RESET);
				}
			}
		}

		// =========================================================
		// STEP 7 : GET HIGHEST DELIVERY DAYS
		// =========================================================

		int highestAdminDays = Collections.max(adminDeliveryDaysList);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 Highest Admin Delivery Days : "
				+ highestAdminDays
				+ RESET);

		System.out.println(CYAN
				+ "📌 Highest Delivery Source"
				+ RESET);

		for (String source : highestSourceList) {

			String[] splitData = source.split(" -> ");

			int value = Integer.parseInt(
					splitData[splitData.length - 1]);

			if (value == highestAdminDays) {

				System.out.println(GREEN
						+ "✅ " + source
						+ RESET);
			}
		}

		// =========================================================
		// STEP 8 : OPEN APPLICATION AGAIN
		// =========================================================

		driver.get(FileReaderManager.getInstance()
				.getConfigReader()
				.getApplicationUrl());

		click(zlaataIndiaShopButton);

		click(clickOnSearchBar);

		searchBoxPlaceholder.clear();

		searchBoxPlaceholder.sendKeys(productName);

		searchBoxPlaceholder.sendKeys(Keys.ENTER);

		Common.waitForElement(2);

		WebElement productElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//a[contains(@class,'product_list_name')]")));

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});",
				productElement);

		Common.waitForElement(2);

		productElement.click();

		System.out.println(GREEN
				+ "✅ Reopened Product In Application"
				+ RESET);

		// =========================================================
		// STEP 9 : ENTER PINCODE
		// =========================================================

		wait.until(ExpectedConditions.visibilityOf(pincodeTextBox));

		pincodeTextBox.clear();

		pincodeTextBox.sendKeys("110001");

		System.out.println(GREEN
				+ "✅ Entered Pincode : 110001"
				+ RESET);

		wait.until(ExpectedConditions.elementToBeClickable(checkButton));

		checkButton.click();

		Common.waitForElement(2);

		System.out.println(GREEN
				+ "✅ Clicked Check Button"
				+ RESET);

		// =========================================================
		// STEP 10 : PDP DELIVERY DATE
		// =========================================================

		String estimatedDateText = wait.until(
				ExpectedConditions.visibilityOf(estimateDate))
				.getText()
				.trim();

		System.out.println(GREEN
				+ "✅ PDP Estimated Date : "
				+ estimatedDateText
				+ RESET);

		LocalDate today = LocalDate.now();

		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(
						"MMMM d, yyyy",
						Locale.ENGLISH);

		LocalDate estimatedDate =
				LocalDate.parse(
						estimatedDateText,
						formatter);

		long totalDays =
				ChronoUnit.DAYS.between(
						today,
						estimatedDate);

		int applicationDeliveryDays =
				(int) totalDays;

		System.out.println(CYAN
				+ "📌 PDP Delivery Days : "
				+ applicationDeliveryDays
				+ RESET);

		// =========================================================
		// STEP 11 : BUY NOW
		// =========================================================

		wait.until(ExpectedConditions.elementToBeClickable(buyNowButton));

		buyNowButton.click();

		System.out.println(GREEN
				+ "✅ Buy Now Button Clicked"
				+ RESET);

		Common.waitForElement(5);

		// =========================================================
		// STEP 12 : CHECKOUT DELIVERY DATE
		// =========================================================

		String checkoutEstimateText = wait.until(
				ExpectedConditions.visibilityOf(
						checkoutEstimateDateText))
				.getText()
				.trim();

		System.out.println(CYAN
				+ "✅ Checkout Estimate Text : "
				+ checkoutEstimateText
				+ RESET);

		String checkoutDateText =
				checkoutEstimateText
				.replace("Estimated delivery date:", "")
				.trim();

		System.out.println(YELLOW
				+ "📌 Checkout Estimated Date : "
				+ checkoutDateText
				+ RESET);

		int currentYear =
				LocalDate.now().getYear();

		String finalCheckoutDate =
				checkoutDateText
				+ " "
				+ currentYear;

		DateTimeFormatter checkoutFormatter =
				DateTimeFormatter.ofPattern(
						"d MMM yyyy",
						Locale.ENGLISH);

		LocalDate checkoutEstimatedDate =
				LocalDate.parse(
						finalCheckoutDate,
						checkoutFormatter);

		long checkoutDays =
				ChronoUnit.DAYS.between(
						today,
						checkoutEstimatedDate);

		int checkoutDeliveryDays =
				(int) checkoutDays;

		System.out.println(CYAN
				+ "📌 Checkout Delivery Days : "
				+ checkoutDeliveryDays
				+ RESET);

		// =========================================================
		// STEP 13 : FINAL 3-WAY VALIDATION
		// =========================================================

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		System.out.println(CYAN
				+ "📌 FINAL DELIVERY DAYS COMPARISON"
				+ RESET);

		System.out.println(GREEN
				+ "📌 Admin Highest Days     : "
				+ highestAdminDays
				+ RESET);

		System.out.println(GREEN
				+ "📌 PDP Application Days   : "
				+ applicationDeliveryDays
				+ RESET);

		System.out.println(GREEN
				+ "📌 Checkout Delivery Days : "
				+ checkoutDeliveryDays
				+ RESET);

		System.out.println(BLUE
				+ "================================================="
				+ RESET);

		if (applicationDeliveryDays == highestAdminDays
				&& checkoutDeliveryDays == highestAdminDays) {

			System.out.println(GREEN
					+ "✅ FINAL RESULT : ALL 3 VALUES MATCHED"
					+ RESET);

		} else {

			System.out.println(RED
					+ "❌ FINAL RESULT : MISMATCH FOUND"
					+ RESET);

			if (applicationDeliveryDays != highestAdminDays) {

				System.out.println(YELLOW
						+ "⚠️ PDP vs ADMIN NOT MATCHED"
						+ RESET);
			}

			if (checkoutDeliveryDays != highestAdminDays) {

				System.out.println(YELLOW
						+ "⚠️ CHECKOUT vs ADMIN NOT MATCHED"
						+ RESET);
			}
		}
		
		

		System.out.println(BLUE
				+ "================================================="
				+ RESET);
	}



	public void estimateDeliveryForSingleProduct() {


		verifyEstimateDeliveryBasedOnHighestAdminValueforOneProduct();
}
	

	public void estimateDeliveryFoTwoProduct() {




		verifyEstimateDeliveryForMultipleRandomProducts();



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
