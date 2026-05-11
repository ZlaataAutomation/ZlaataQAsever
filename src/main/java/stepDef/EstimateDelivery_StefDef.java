package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.Estimatdelivery_Page;

public class EstimateDelivery_StefDef {

	TestContext testContext;
	Estimatdelivery_Page estimate;

	public EstimateDelivery_StefDef(TestContext context) {
		testContext = context;
		estimate = testContext.getPageObjectManager().getEstimatdelivery_Page();
	}



	
	@Given("the user verifies that Estimate Delivery  functionality is working fine")
	public void the_user_verifies_that_estimate_delivery_functionality_is_working_fine() {
		estimate.estimateDeliveryForSingleProduct();		    
	}


		@Given("the user verifies that Estimate Delivery  functionality is working fine two Product")
	public void the_user_verifies_that_estimate_delivery_functionality_is_working_fine_two_product() {
		estimate.estimateDeliveryFoTwoProduct();
	}





}
