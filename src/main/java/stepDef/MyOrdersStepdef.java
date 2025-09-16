package stepDef;


import context.TestContext;
import pages.LoginPage;
import pages.MyOrdersPage;
import io.cucumber.java.en.Given;


public class MyOrdersStepdef {
	TestContext testContext;
	MyOrdersPage myOrders;
	LoginPage login;


	public MyOrdersStepdef(TestContext context) {
		testContext = context;
		myOrders = testContext.getPageObjectManager().getMyOrdersPage();
		testContext = context;
		login = testContext.getPageObjectManager().getLoginPage();

	}
	

		@Given("My orders page smoke testing {string}")
		public void my_orders_page_smoke_testing(String arg) {
			login.userLogin();
	        if (arg.equalsIgnoreCase("Shop All")) {
	        	myOrders.netBanking("Shop All");
	        } else {
	        	myOrders.netBanking();
	        }
		}
}





