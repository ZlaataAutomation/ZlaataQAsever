package stepDef;

import context.TestContext;
import pages.OrdersPage;

import io.cucumber.java.en.Given;

public class OrderStepDef {
	TestContext testContext;
	OrdersPage order;



	public OrderStepDef(TestContext context) {
		testContext = context;
		order = testContext.getPageObjectManager().getOrdersPage();
		

	}

		@Given("User placing an order and verifing calculations including check out page")
		public void user_placing_an_order_and_verifing_calculations_including_check_out_page() {
		    order.verifyOrderPlacementAndValidationFlow(Hooks.getScenario());
		}



	

}
