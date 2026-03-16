package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.Landing_Page;

public class Landing_Page_StepDef {
	TestContext testContext;
	Landing_Page land;
	
	public Landing_Page_StepDef(TestContext context) {
		testContext = context;
		land = testContext.getPageObjectManager().getLanding_Page();
	}

		@Given("Admin creates a new category in the ZL Menu section of the Admin Panel")
		public void admin_creates_a_new_category_in_the_zl_menu_section_of_the_admin_panel() {
		   land.verifyLandingPageZlaataIndiaCategory();
		}
		@Then("The created category should be displayed under the Zlaata India category section on the App Landing Page")
		public void the_created_category_should_be_displayed_under_the_zlaata_india_category_section_on_the_app_landing_page() {
	
		}

			@Given("Admin creates a new Boss Lady category in the ZL Menu section of the Admin Panel")
			public void admin_creates_a_new_boss_lady_category_in_the_zl_menu_section_of_the_admin_panel() {
			   land.verifyLandingPageBossLadyCategory();
			}
			@Then("The created category should be displayed under the Boss Lady category section on the App Landing Page")
			public void the_created_category_should_be_displayed_under_the_boss_lady_category_section_on_the_app_landing_page() {
			
			}



	
	
	
	
	
}
