package stepDef;

import java.io.IOException;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AdminPanelPage;
import utils.Common;


public class AdminPanelStepDef {
	
	TestContext testContext;
	AdminPanelPage admin;
	private String capturedSku;


	public AdminPanelStepDef(TestContext context) {
		testContext = context;
		admin = testContext.getPageObjectManager().getAdminPanelPage();

	}
//TC-01, Zlaata India Home Page Banner
		@Given("I upload a banner for Zlaata India in the admin panel")
		public void i_upload_a_banner_for_zlaata_india_in_the_admin_panel() {
			admin.verifyZlaataIndiaHomePageBanner();
		}

		@Then("I should see the uploaded banner displayed in the Zlaata India home page banner section")
		public void i_should_see_the_uploaded_banner_displayed_in_the_zlaata_india_home_page_banner_section() {
		 
		}
//TC-02, Boss Lady Home Page Banner
			@Given("I upload a banner for Boss Lady in the admin panel")
			public void i_upload_a_banner_for_boss_lady_in_the_admin_panel() {
			  admin.verifyBossLadyHomePageBanner();
			}
			@Then("I should see the uploaded banner displayed in the Boss Lady home page banner section")
			public void i_should_see_the_uploaded_banner_displayed_in_the_boss_lady_home_page_banner_section() {
			 
			}
			

//TC-3
	
	// Verify New Arrivals
			@Given("the admin adds this product to the New Arrivals section")
			public void the_admin_adds_this_product_to_the_new_arrivals_section() throws InterruptedException {
		admin.validateNewArrivalSuccessfullyAdded();
			}
			@Given("the admin sorts this product to the first position in New Arrivals")
			public void the_admin_sorts_this_product_to_the_first_position_in_new_arrivals() {
			
			}
			@Then("the product should appear in the New Arrivals section on the user application")
			public void the_product_should_appear_in_the_new_arrivals_section_on_the_user_application() {
			
			}



	
//TC-04
	// Category
				@Given("I upload the Zlaata India category banner with image in admin panel")
				public void i_upload_the_zlaata_india_category_banner_with_image_in_admin_panel() {
		admin.validateZlaataIndiaCategories();
				}
				@Then("I should see Zlaata India the updated banner in the user application")
				public void i_should_see_zlaata_india_the_updated_banner_in_the_user_application() {
			
				}
//TC-05  Categories Boss Lady
				
					@Given("I upload the Boss Lady category banner with image in admin panel")
					public void i_upload_the_boss_lady_category_banner_with_image_in_admin_panel() {
				admin.validateBossLadyCategories();
					}
					@Then("I should see Boss Lady  the updated banner in the user application")
					public void i_should_see_boss_lady_the_updated_banner_in_the_user_application() {
		
					}
//TC-06,  Collection
					
						@Given("I upload the Zlaata India Collection banner with image in admin panel")
						public void i_upload_the_zlaata_india_collection_banner_with_image_in_admin_panel() {
						  
						}
						@Then("I should see Zlaata India the updated Collection banner in the user application")
						public void i_should_see_zlaata_india_the_updated_collection_banner_in_the_user_application() {
						 admin.validatezlaataIndiaHomePageCollection();
						}
//TC-07, Influencer Pointer					
	
		@Given("I upload the influencer banner and map the product in the admin panel")
		public void i_upload_the_influencer_banner_and_map_the_product_in_the_admin_panel() throws InterruptedException {
		admin.validateZlaataIndiaHomePageInfluencerPointer();					
		}
		@Then("I should see the product influencer pointer displayed on the homepage")
		public void i_should_see_the_product_influencer_pointer_displayed_on_the_homepage() {
							    
		}



				





//Special Timer Product
		
	
			@When("I upload the special product excel {string}")
			public void i_upload_the_special_product_excel(String filePath) throws InterruptedException {
				String excelPath = System.getProperty("user.dir") + "/src/test/resources/Bulk Excel File/SpecialTimerProduct/" + filePath;
			    admin.uploadTheSpecialTimerProductExcel(excelPath);
			 
			}

			@Then("I verify products in Admin panel with {string}")
			public void i_verify_products_in_admin_panel_with(String filePath) throws IOException {
				String excelPath = System.getProperty("user.dir") + "/src/test/resources/Bulk Excel File/SpecialTimerProduct/" + filePath;
			    admin.verifySpecialProductsinAdmin(excelPath);
			}

				@Then("verify products from {string} should be visible in user app")
				public void verify_products_from_should_be_visible_in_user_app(String filePath) throws IOException {
					String excelPath = System.getProperty("user.dir") + "/src/test/resources/Bulk Excel File/SpecialTimerProduct/" + filePath;
				   admin.verifyProductsUserApp(excelPath);
				 
				}


	



					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
	


}
