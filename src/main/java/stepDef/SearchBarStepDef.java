package stepDef;


import java.util.concurrent.TimeoutException;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchSectionPage;
import utils.Common;

public class SearchBarStepDef {

	TestContext testContext;
	SearchSectionPage search;
	LoginPage login;
	HomePage home;


	public  SearchBarStepDef(TestContext context) {
		testContext = context;
		search = testContext.getPageObjectManager().getSearchSectionPage();
		login = testContext.getPageObjectManager().getLoginPage();
		home = testContext.getPageObjectManager().getHomePage();

	}

	@Given("User verifies that they can click on the search bar.")
	public void user_verifies_that_they_can_click_on_the_search_bar() {
		home.homeLaunch();
		search.searchbarClikable();

	}

	@Given("User focuses on the search bar and verifies the display of TRENDING and Related Products headings.")
	public void user_focuses_on_the_search_bar_and_verifies_the_display_of_trending_and_related_products_headings()
	{
		home.homeLaunch();

		search.TrendingAndRelatedHeading();


	}

	@Given("User verifies that all TRENDINGS section options are clickable.")
	public void user_verifies_that_all_trendings_section_options_are_clickable() throws InterruptedException, TimeoutException {
		home.homeLaunch();
		search.clickAllTrendingProductsAndVerify();

	}

	@Given("User enters a valid search keyword and verifies redirection to the correct page")
	public void user_enters_a_valid_search_keyword_and_verifies_redirection_to_the_correct_page() {
		home.homeLaunch();
		search.searchKeyWordRedirectToCorrectpage();

	}

	@Given("User verifies that Related Queries are displayed under the search results")
	public void user_verifies_that_related_queries_are_displayed_under_the_search_results() throws InterruptedException {
		home.homeLaunch();

		search.validateRelatedQueriesAndHeadings();
	}

	@Given("User enters a random keyword and verifies navigation to the Search Suggestions page.")
	public void user_enters_a_random_keyword_and_verifies_navigation_to_the_search_suggestions_page() 
	{
		home.homeLaunch();
		try {
			search.verifySearchSuggestionHeading();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("User verifies that the Search History section is displayed in the search bar.")
	public void user_verifies_that_the_search_history_section_is_displayed_in_the_search_bar() 
	{
		home.homeLaunch();
		search.verifySearchHistoryDisplaying();

	}
	@Given("User verifies the ability to clear individual keywords from search history.")
	public void user_verifies_the_ability_to_clear_individual_keywords_from_search_history() {
		home.homeLaunch();
		search.verifysearchHistoryKeyworddisplayAnduserabletoDelete();

	}


	@Given("User pastes a product name into the search bar and verifies the correct product appears in suggestions.")
	public void user_pastes_a_product_name_into_the_search_bar_and_verifies_the_correct_product_appears_in_suggestions() {
		home.homeLaunch();
		search.enterProductNameExactlyRedirectToProduct();

	}
	@Given("User verifies that the Recently Viewed section is displayed in the search bar.")
	public void user_verifies_that_the_recently_viewed_section_is_displayed_in_the_search_bar() {
		home.homeLaunch();
		search.recentlyViewProductAppears();

	}





























}