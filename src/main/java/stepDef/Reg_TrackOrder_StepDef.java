package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.Reg_TrackOrder_Page;

public class Reg_TrackOrder_StepDef {
	TestContext testContext;
	Reg_TrackOrder_Page trackOrder;
	
	public Reg_TrackOrder_StepDef(TestContext context) {
		testContext = context;
		trackOrder = testContext.getPageObjectManager().getReg_TrackOrder_Page();
	}
	
		@Given("User navigates to the Track Order page from Footer and My Account Setting.")
		public void user_navigates_to_the_track_order_page_from_footer_and_my_account_setting() {
		 
		}
		@When("User enters a valid Track ID and clicks on Track Order")
		public void user_enters_a_valid_track_id_and_clicks_on_track_order() {
		  
		}
		@Then("System should successfully display the order details")
		public void system_should_successfully_display_the_order_details() {
		  
		}



	
	
	
}
