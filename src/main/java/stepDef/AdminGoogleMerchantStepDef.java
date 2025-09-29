package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.AdminGoogleMerchantPage;
import pages.AdminPanelExportExcelFileMatchPage;

public class AdminGoogleMerchantStepDef {
	
	private String capturedSku;
	TestContext testContext;
	AdminGoogleMerchantPage adminGoogle;
	


	public AdminGoogleMerchantStepDef(TestContext context) {
		testContext = context;
		adminGoogle = testContext.getPageObjectManager().getAdminGoogleMerchantPage();
	}
	
	
		@When("I select a product and get its SKU")
		public void i_select_a_product_and_get_its_sku() {
			capturedSku = adminGoogle.searchProductAndCopySku();
			
		}

		@When("I enable Google Merchant and push the product")
		public void i_enable_google_merchant_and_push_the_product() {
			adminGoogle.enableGoogleMerchantAndPushTheProduct(capturedSku);
		}
		@Then("I verify the product status is and success message appears")
		public void i_verify_the_product_status_is_and_success_message_appears() {
			adminGoogle.productStatusPublishedMessage(capturedSku);
		}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
