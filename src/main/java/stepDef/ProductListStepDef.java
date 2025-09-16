package stepDef;

import context.TestContext;
import pages.HomePage;
import pages.ProductListingPage;

import io.cucumber.java.en.Given;


public class ProductListStepDef {

	TestContext testContext;
	ProductListingPage pLp;
    HomePage home;


	public ProductListStepDef(TestContext context) {
		testContext = context;
		pLp = testContext.getPageObjectManager().getProductListingPage();
		testContext = context;
		home=testContext.getPageObjectManager().getHomePage();

	}


	@Given("User clicks on home text after landing product listing page")
	public void user_clicks_on_home_text_after_landing_product_listing_page() {
		home.homeLaunch();
		pLp.homeCrumbLink();
	}

	@Given("User verifies weather the landed page heading is same as link")
	public void user_verifies_weather_the_landed_page_heading_is_same_as_link() {
		home.homeLaunch();
		pLp.pLpHeading();;
	}


	@Given("User verifies pagination")
	public void user_verifies_pagination() {
		home.homeLaunch();
		pLp.pagination();
	}

	@Given("User clicks on pagination forward and backward buttons")
	public void user_clicks_on_pagination_forward_and_backward_buttons() {
		home.homeLaunch();
		pLp.pagiNationArrows();
	}

	@Given("User clicks on available page number in product listing page")
	public void user_clicks_on_available_page_number_in_product_listing_page() {
		home.homeLaunch();
		pLp.paginationNumber();
	}

	@Given("User clicks on show filter button")
	public void user_clicks_on_show_filter_button() {
		home.homeLaunch();
		pLp.showFilter();
	}

		@Given("User clicks on sort by option")
	public void user_clicks_on_sort_by_option() {
			home.homeLaunch();
			pLp.sortByFilter();
	}


	@Given("User verifies basic filters are working")
	public void user_verifies_basic_filters_are_working() {
		home.homeLaunch();
		pLp.basicFilterFunction();
	}


	@Given("User verifies sort by filters")
	public void user_verifies_sort_by_filters() {
		home.homeLaunch();
		pLp.allsortBy();;
		
	}


	@Given("User clicks on wish list icon")
	public void user_clicks_on_wish_list_icon() {
		home.homeLaunch();
		pLp.wishListIcon();
	}


	@Given("User clicks on add to cart button")
	public void user_clicks_on_add_to_cart_button() {
		home.homeLaunch();
		pLp.addToCart();
	}
}






















