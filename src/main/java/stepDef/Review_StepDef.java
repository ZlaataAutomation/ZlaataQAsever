package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.Review_Page;

public class Review_StepDef {
	TestContext testContext;
	Review_Page review;
	
	public Review_StepDef(TestContext context) {
		testContext = context;
		review = testContext.getPageObjectManager().getReview_Page();
	}
	
	


		@Given("the user submits a review for a product")
		public void the_user_submits_a_review_for_a_product() {
		    review.validateUserReview();
		}
		@When("the admin reviews and approves the submitted review")
		public void the_admin_reviews_and_approves_the_submitted_review() {
		   
		}
		@Then("the approved review should be displayed in the user app")
		public void the_approved_review_should_be_displayed_in_the_user_app() {
		  
		}

			@Given("the user submits a My Order review for a product")
			public void the_user_submits_a_my_order_review_for_a_product() {
				review.validateMyOrderReview();
			}
			@When("the admin reviews and approves the submitted my order review")
			public void the_admin_reviews_and_approves_the_submitted_my_order_review() {
			   
			}
			@Then("the approved my order review should be displayed in the user app")
			public void the_approved_my_order_review_should_be_displayed_in_the_user_app() {
		
			}








}
