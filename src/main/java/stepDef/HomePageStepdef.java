package stepDef;



import java.util.concurrent.TimeoutException;

import context.TestContext;
import pages.HomePage;
import io.cucumber.java.en.Given;


public class HomePageStepdef {


	TestContext testContext;
	HomePage home;

	public HomePageStepdef(TestContext context) {
		testContext = context;
		home = testContext.getPageObjectManager().getHomePage();
	}


	@Given("User going to click available banners in home page")
	public void user_going_to_click_available_banners_in_home_page() {
		home.homeLaunch();
		home.bannerClick();
	}


	@Given("User clicks on forward and backward button at home page banner section")
	public void user_clicks_on_forward_and_backward_button_at_home_page_banner_section() {
		home.forAndbackButton();


	}

	@Given("User clicks on pause button")
	public void user_clicks_on_pause_button() {
		home.verifyPauseButton();
	}


	@Given("User verifying top selling section")
	public void user_verifying_top_selling_section() throws TimeoutException {
		home.topSelling();

	}



	@Given("User clicks on product image at tope selling section")
	public void user_clicks_on_product_image_at_tope_selling_section() {
		home.topSellingProduct();

	}




	@Given("User clicks on forward and backward arrows on  new arrivals")
	public void user_clicks_on_forward_and_backward_arrows_on_new_arrivals() throws TimeoutException {

		home.newArrivalArrows();

	}


	@Given("User clicks on product image at new arrival section")
	public void user_clicks_on_product_image_at_new_arrival_section() {
		home.newArivalProductImg();

	}

	@Given("User clicks on home page quick view icon")
	public void user_clicks_on_home_page_quick_view_icon() {
		home.quickView();
	}


	//	@Given("User clicks on inspired by images")
	//	public void user_clicks_on_inspired_by_images() {
	//		home.inspiredBy();
	//
	//	}


	@Given("User clicks on See More button .")
	public void user_clicks_on_see_more_button() {
		home.seeMore();
	}






	@Given("User submitting feedback")
	public void user_submitting_feedback() {
		home.feedBack();
	}



	@Given("User clicks on whats app icon")
	public void user_clicks_on_whats_app_icon() {
		home.whatsApp();
	}

	@Given("User veridying feature on section")
	public void user_veridying_feature_on_section() {
		home.featureOn();

	}


	@Given("User verifying all the available headings in home page")
	public void user_verifying_all_the_available_headings_in_home_page() {
		home.allsectionHomePage();
	}




	@Given("the user verifies that the logo is available on the Home Page")
	public void the_user_verifies_that_the_logo_is_available_on_the_home_page() {
		home.logoDisplay();
	}



	@Given("the user verifies that the Thread banner is available on the Home Page")
	public void the_user_verifies_that_the_thread_banner_is_available_on_the_home_page() {
		home.threadBanner();
	}

	@Given("the user verifies that the Category section is available on the Home Page")
	public void the_user_verifies_that_the_category_section_is_available_on_the_home_page() {
		home.categorySection();
	}


	@Given("the user verifies that the Monsoon Banner section is available on the Home Page")
	public void the_user_verifies_that_the_monsoon_banner_section_is_available_on_the_home_page() {
		home.verifyMonsoonBanner();
	}


















}
