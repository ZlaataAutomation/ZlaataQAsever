package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.NewLabel_Page;

public class NewLabel_StepDef {
	TestContext testContext;
	NewLabel_Page newlabel;
	
	public NewLabel_StepDef(TestContext context) {
		testContext = context;
		newlabel = testContext.getPageObjectManager().getNewLabel_Page();
	}

		@Given("the admin update current date for a product")
		public void the_admin_update_current_date_for_a_product() throws InterruptedException {
		    newlabel.validateNewLabelFunctionality();
		}
		@When("the new label should display in that product in user app.")
		public void the_new_label_should_display_in_that_product_in_user_app() {
		   
		}



	
}
