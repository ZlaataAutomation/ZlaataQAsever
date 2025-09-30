package stepDef;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import context.TestContext;
import io.cucumber.java.en.Given;
import pages.HomePage;
import pages.ProductDetailsPage;

public class ProductDetailsStepDef {
	TestContext testContext;
	ProductDetailsPage pDP;
	WebDriver driver;
	HomePage home;


	public ProductDetailsStepDef(TestContext context) {
		testContext = context;
		pDP = testContext.getPageObjectManager().getProductDetailsPage();
		testContext = context;
		home=testContext.getPageObjectManager().getHomePage();

	}

	@Given("User Verifies Display of Product Price on Product details page #excel need to update")
	public void user_verifies_display_of_product_price_on_product_details_page_excel_need_to_update() {
		home.homeLaunch();
		pDP.productPrice();
	}

	@Given("User Verifies Discount price calculating  on Product details page #excel need to update")
	public void user_verifies_discount_price_calculating_on_product_details_page_excel_need_to_update() {
		home.homeLaunch();
		pDP.discountPercentageCalculation();
	}


	@Given("User Verifies images are chaneable using arrow button")
	public void user_verifies_images_are_chaneable_using_arrow_button() {
		home.homeLaunch();
		pDP.productImageChage();
	}

	@Given("User Verifies Wishlist Button Functionality on Product details page #excel need to update")
	public void user_verifies_wishlist_button_functionality_on_product_details_page_excel_need_to_update() {

		pDP.wishList();
	}


	@Given("User Verifies {string} Functionality")
	public void user_verifies_functionality(String string) {
		home.homeLaunch();
		pDP.verifyBestPriceCalculation();
	}

	@Given("User Verifies Color Selection Functionality")
	public void user_verifies_color_selection_functionality() throws InterruptedException {
		home.homeLaunch();
		pDP.verifyColorOptions();
	}

	@Given("User Verifies Color Section Dropdown Arrow")
	public void user_verifies_color_section_dropdown_arrow() {
		home.homeLaunch();
		pDP.colordropDown();
	}


	@Given("User Verifies Size Chart Availability")
	public void user_verifies_size_chart_availability() {
		home.homeLaunch();
		pDP.sizeChart(Hooks.getScenario());
	}

	@Given("User Verifies Size Selection Functionality")
	public void user_verifies_size_selection_functionality() {
		home.homeLaunch();
		pDP.verifySizeOptions();
	}

	//
	//	@Given("User Verifies {string} Section")
	//	public void user_verifies_section(String string) {
	//		pDP.askUsAnything(Hooks.getScenario());
	//	}

	@Given("User verifies that the category name is displayed on the Product Details Page")
	public void user_verifies_that_the_category_name_is_displayed_on_the_product_details_page() {

		home.homeLaunch();
		pDP.categoryName();


	}




	@Given("User Verifies {string} Button Functionality on Product details page #excel need to update")
	public void user_verifies_button_functionality_on_product_details_page_excel_need_to_update(String string) {
		home.homeLaunch();
		pDP.addToCartButton();
	}


	@Given("User Verifies Buy Now  Functionality on Product details page")
	public void user_verifies_buy_now_functionality_on_product_details_page() {
		home.homeLaunch();
		pDP.buyNow(Hooks.getScenario());
	}


	@Given("User Verifies Delivery Pincode Textbox Functionality")
	public void user_verifies_delivery_pincode_textbox_functionality() {
		home.homeLaunch();
		pDP.verifyPincode();
	}

	@Given("User Verifies {string} Section Availability")
	public void user_verifies_section_availability(String string) {
		home.homeLaunch();
		pDP.tryAlongSection(Hooks.getScenario());
	}

	@Given("User Verifies Selection of {string} Product")
	public void user_verifies_selection_of_product(String string) {
		home.homeLaunch();
		pDP.tryAlongProducts(Hooks.getScenario());
	}

	@Given("User Verifies Eye Icon Click on {string} Product")
	public void user_verifies_eye_icon_click_on_product(String string) {
		home.homeLaunch();
		pDP.quickViewIconTryAlong(Hooks.getScenario());
	}



	@Given("User on Product details page")
	public void user_on_product_details_page() {
		home.homeLaunch();
		pDP.viewMoreButton();
	}



	@Given("User Verifies Dropdown Arrows for Sections \\(Product Description, Composition & Care, etc.)")
	public void user_verifies_dropdown_arrows_for_sections_product_description_composition_care_etc() {
		home.homeLaunch();
		pDP.productDescriptionDropDDown();
	}


	@Given("User Verifies {string} Link in Return & Exchange Section")
	public void user_verifies_link_in_return_exchange_section(String string) {
		home.homeLaunch();
		pDP.returnAndExchangeLink();
	}

	@Given("User Verifies {string} Button Clickability on Product details page #excel need to update")
	public void user_verifies_button_clickability_on_product_details_page_excel_need_to_update(String string) {
		home.homeLaunch();
		pDP.reviewViewAll();
	}

	@Given("User Verifies Review Calculation on Product details page #excel need to update")
	public void user_verifies_review_calculation_on_product_details_page_excel_need_to_update() {
		home.homeLaunch();
		pDP.reviewCalculation();
	}


	@Given("User Verifies Write a Review Button Clickability on Product details page #excel need to update")
	public void user_verifies_write_a_review_button_clickability_on_product_details_page_excel_need_to_update() {
		home.homeLaunch();
		pDP.reviewButtonclickable();
	}





	@Given("User Verifies Display of {string} Section on Product details page #excel need to update")
	public void user_verifies_display_of_section_on_product_details_page_excel_need_to_update(String string) {
		home.homeLaunch();
		pDP.moreForYouSection();
	}


	@Given("User Verifies Display of Recently Viewed Section")
	public void user_verifies_display_of_recently_viewed_section() {

		pDP.recentlyViewed();
	}




	@Given("User Verifies Display of {string} Section")
	public void user_verifies_display_of_section(String string) {
		home.homeLaunch();
		pDP.suggestedForYou();
	}



	@Given("User Verifies that on Review Popup without Enter All Data Click on Submit")
	public void user_verifies_that_on_review_popup_without_enter_all_data_click_on_submit() {
		home.homeLaunch();
		pDP.ReviewPopupWithoutEnterAllDataClickOnS();
	}

		@Given("User on Product details page and gives a review")
	public void user_on_product_details_page_and_gives_a_review() {
			home.homeLaunch();
			pDP.ReviewPopupEnterAllData();
		
	}





























































































}