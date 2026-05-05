package stepDef;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.ExpressDeliveryPage;

public class ExpressDeliveryStepDef {


	TestContext testContext;
	ExpressDeliveryPage  express;

	public ExpressDeliveryStepDef(TestContext context) {
		{
			testContext = context;
			express = testContext.getPageObjectManager().getExpressDeliveryPage();
		}
	}



	@Given("the user verifies that Express delivery functionality is working fine")
	public void the_user_verifies_that_express_delivery_functionality_is_working_fine() {

		express.verifyExpressDeliveryYesAndNo();
	}



		@Given("the user verifies Express Delivery functionality in the Product Listing page Sort By section")
	public void the_user_verifies_express_delivery_functionality_in_the_product_listing_page_sort_by_section() {
			
			express.expressDeliveryOptionWorkingfineInSortBySectionInPLP();
		
	}





}


