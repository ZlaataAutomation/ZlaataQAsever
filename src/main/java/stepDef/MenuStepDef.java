package stepDef;

import context.TestContext;
import pages.HomePage;
import pages.Menus;

import io.cucumber.java.en.Given;

public class MenuStepDef {

	TestContext testContext;
	Menus menu;
	HomePage home;

	public MenuStepDef(TestContext context) {
		testContext = context;
		menu = testContext.getPageObjectManager().getMenus();
		testContext = context;
		home=testContext.getPageObjectManager().getHomePage();
	}





	@Given("User clicks on home page header menu")
	public void user_clicks_on_home_page_header_menu() {
		home.homeLaunch();
		menu.clickHome();
	}


	@Given("User clicks on newAriivals header")
	public void user_clicks_on_new_ariivals_header() {
		home.homeLaunch();
		menu.clickNewArrival();
	}

	@Given("User clicks on new arrivals suggestion")
	public void user_clicks_on_new_arrivals_suggestion() {
		home.homeLaunch();
		menu.newArrivalSuggestion();
	}


	@Given("User clicks on sale menu")
	public void user_clicks_on_sale_menu() {
		home.homeLaunch();
		menu.saleMenu();
	}



	@Given("User clicks on boss lady suggestions")
	public void user_clicks_on_boss_lady_suggestions() {
		home.homeLaunch();
		menu.bossLady();
	}



	@Given("User clicks on get update")
	public void user_clicks_on_get_update() {
		home.homeLaunch();
		menu.getUpdates();
		
	}



	@Given("User clicks on shop category")
	public void user_clicks_on_shop_category() {
		home.homeLaunch();
		menu.shopCategory();
	}


	@Given("User clicks on shop collection")
	public void user_clicks_on_shop_collection() {
		home.homeLaunch();
		menu.shopCollection();
	}



	@Given("User clicks on shop styles")
	public void user_clicks_on_shop_styles() {
		home.homeLaunch();
		menu.shopStyles();
	}

	@Given("User clicks on Pop shop")
	public void user_clicks_on_pop_shop() {
		home.homeLaunch();
		menu.PopShop();
	}
















}
