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

	@Given("I upload an image {string} in admin panel")
	public void i_upload_an_image_in_admin_panel(String imageName) {
		String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/" + imageName;
		admin.uploadImage(imagePath);

	}

	@When("I verify that the homepage first banner is {string}")
	public void i_verify_that_the_homepage_first_banner_is(String expectedBannerTitle) {
		admin.verifyBannerOnHomePage();
	}

	
	// Verify Top Selling
	@Given("admin is logged in")
	public void admin_is_logged_in() {
		admin.adminLogin();

	}

	@When("amdin copies the SKU for Product")
	public void amdin_copies_the_sku_for_product() {
		admin.givesProductName();
		capturedSku = admin.fetchSkuFromProduct();

	}

	@When("admin puts this SKU at first position in Top Selling")
	public void admin_puts_this_sku_at_first_position_in_top_selling() {
		admin.putSkuIntoTopSelling(capturedSku);

	}

	@Then("the product should appear in Top Selling on the user panel")
	public void the_product_should_appear_in_top_selling_on_the_user_panel() throws InterruptedException {
		// pull product name from Excel map
		String productName = Common.getValueFromTestDataMap("ProductListingName");
		admin.verifyProductShowInTopSelling(productName);
	}

	//Top Selling Negative Test
	@When("I remove the product with SKU from Top Selling")
		public void i_remove_the_product_with_sku_from_top_selling() {
		admin.forNegativeGivesProductName();
		capturedSku = admin.forNegativeFetchSkuFromProduct();
		admin.removeSkuFromTopSelling(capturedSku);
		   
		}

		@Then("I should not see product  in Top Selling section on user app")
		public void i_should_not_see_product_in_top_selling_section_on_user_app() throws InterruptedException {
			String productName = Common.getValueFromTestDataMap("ProductListingName");
			admin.verifyProductNotInTopSelling(productName);
		}



	
	// Verify New Arrivals

	@When("the admin verifies the colour of the product at the first position")
	public void the_admin_verifies_the_colour_of_the_product_at_the_first_position() {
		admin.verifyColourOfTheProductIsFirstPosition();

	}

	@When("the admin adds this product to the New Arrivals section")
	public void the_admin_adds_this_product_to_the_new_arrivals_section() throws InterruptedException {
		admin.addTheProductInNewArrivalSection();

	}

	@When("the admin sorts this product to the first position in New Arrivals")
	public void the_admin_sorts_this_product_to_the_first_position_in_new_arrivals() {
		admin.sortTheProductInFirstPosition();

	}

	@Then("the product should appear in the New Arrivals section on the user application")
	public void the_product_should_appear_in_the_new_arrivals_section_on_the_user_application() {
		String productName = Common.getValueFromTestDataMap("ProductListingName");
		admin.verifyProductShowInNewArrivalsSction(productName);

	}
	

	// Catagory

	@When("I update the category banner with image {string}")
	public void iUpdateTheCategoryBanner(String imageName) {
		String imagePathCatagory = System.getProperty("user.dir") + "/src/test/resources/images/" + imageName;
		admin.updateCategoryBanner(imagePathCatagory);
	}

	// Step definition for verifying banner in User Application
	@Then("I should see the updated banner in the user application")
	public void iShouldSeeTheUpdatedBannerInUserApp() {
		admin.verifyBannerUserApp();
	}

//Bulk Product
	
	
		@When("I upload the product excel {string}")
		public void i_upload_the_product_excel(String filePath) {
			String excelPath= System.getProperty("user.dir") + "/src/test/resources/BulkProduct/" + filePath;
			admin.UploadTheProductExcel(excelPath);
		}

		@Then("the products from {string} should be visible in admin panel")
		public void the_products_from_should_be_visible_in_admin_panel(String filePath) throws IOException {
			String excelPath= System.getProperty("user.dir") + "/src/test/resources/BulkProduct/" + filePath;
			admin.verifyProductsInAdmin(excelPath);
		    
		}
		@Then("the products from {string} should be visible in user app")
		public void the_products_from_should_be_visible_in_user_app(String filePath) throws IOException {
			String excelPath= System.getProperty("user.dir") + "/src/test/resources/BulkProduct/" + filePath;
			admin.verifyProductsInUserApp(excelPath);
		
		}







			
				





	
				
	
	
	
	
	


}
