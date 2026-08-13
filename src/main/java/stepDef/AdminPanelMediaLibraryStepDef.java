package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.AdminPanelMediaLibrary;

public class AdminPanelMediaLibraryStepDef {

    TestContext testContext;
    AdminPanelMediaLibrary adminPanelMediaLibrary;
    private String updatedBrandType;

    public AdminPanelMediaLibraryStepDef(TestContext context) {
        testContext = context;
        adminPanelMediaLibrary = testContext.getPageObjectManager().getAdminPanelMediaLibrary();
    }

    @Given("user navigates to Dresses page in UI")
    public void user_navigates_to_dresses_page_in_ui() {
    	adminPanelMediaLibrary.executeMediaLibraryImageUploadFlow();
    }

    @When("user selects a random product and captures the product name")
    public void user_selects_a_random_product_and_captures_the_product_name() {
 
    }

    @When("admin uploads a new image to the selected product in Media Library")
    public void admin_uploads_a_new_image_to_the_selected_product_in_media_library() {

    }
    
    @Then("verify the uploaded product image is visible for the same product in UI")
    public void verify_the_uploaded_product_image_is_visible_for_the_same_product_in_ui() {

    }
    
 //TC_UI_Zlaata_ML_02  

    @Given("user navigates to Media Library module")
    public void user_navigates_to_media_library_module() {
    	updatedBrandType = adminPanelMediaLibrary.executeBrandTypeUpdateFlow();
        System.out.println("✅ Orchestrator complete: Brand type updated and verified — " + updatedBrandType);
    }

    @When("user updates the brand type for a product and saves the changes")
    public void user_updates_the_brand_type_for_a_product_and_saves_the_changes() {
        
    }

    @Then("verify that the updated brand type is reflected on the corresponding product page in Admin Panel")
    public void verify_that_the_updated_brand_type_is_reflected_on_the_corresponding_product_page_in_admin_panel() {
    
    }

 
}