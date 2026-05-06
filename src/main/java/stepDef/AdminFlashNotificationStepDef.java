package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.AdminFlashNotification;

public class AdminFlashNotificationStepDef {
	
	TestContext testContext;
	AdminFlashNotification adminFlashNotifications;
	private String addedNotificationName;
	private String deletedNotificationName;



	public AdminFlashNotificationStepDef(TestContext context) {
		testContext = context;
		adminFlashNotifications = testContext.getPageObjectManager().getAdminFlashNotification();
	}	
	
// TC_UI_Zlaata_FN_01
	@When("add flash notification for {string}")
	public void add_flash_notification_for(String pageType) throws InterruptedException {
	    adminFlashNotifications.navigateToFlashNotificationModule();
	    adminFlashNotifications.filterByLandingPage();
	    adminFlashNotifications.filterByActiveStatus();
	    adminFlashNotifications.disableFirstActiveLandingPageNotification();
	    adminFlashNotifications.clearFilters();
	    adminFlashNotifications.clickAddFlashNotification();
	    // try to save empty form first
	    adminFlashNotifications.trySaveEmptyForm();
	}

	@Then("verify error message is displayed")
	public void verify_error_message_is_displayed() throws InterruptedException {
	    boolean errorsDisplayed = adminFlashNotifications.verifyValidationErrorsDisplayed();
	    if (!errorsDisplayed) {
	        throw new AssertionError("No validation error messages were displayed when submitting empty form");
	    }
	    System.out.println("✅ Validation errors correctly displayed for empty form submission");
	}

	@And("fill and save flash notification for {string}")
	public void fill_and_save_flash_notification_for(String pageType) throws InterruptedException {
	    addedNotificationName = adminFlashNotifications.fillFlashNotificationForm();
	    adminFlashNotifications.enableDisplayToggleForFirstRecord();
	}

	@Then("verify notification is visible on {string} UI")
	public void verify_notification_is_visible_on_ui(String pageType) throws InterruptedException {
	    adminFlashNotifications.navigateToLandingPageUI();
	    String expectedDescription = adminFlashNotifications.getSavedDescription();
	    System.out.println("Verifying flash notification: " + expectedDescription);
	    boolean isVisible = adminFlashNotifications.verifyFlashNotificationOnUI(expectedDescription);
	    if (!isVisible) {
	        throw new AssertionError("Flash notification '" + expectedDescription
	            + "' was NOT found on the " + pageType + " UI");
	    }
	    System.out.println("✅ Flash notification verified successfully on " + pageType + " UI");
	}
	
	// TC_UI_Zlaata_FN_02 — combined Zlaata India + Boss Lady random selection
	@When("add flash notification for random brand home page")
	public void add_flash_notification_for_random_brand_home_page() throws InterruptedException {
	    adminFlashNotifications.addFlashNotificationForRandomBrand();
	    System.out.println("✅ Flash notification added for brand: "
	        + adminFlashNotifications.getRandomBrandForTC02());
	}

	@Then("verify notification is visible on random brand UI")
	public void verify_notification_is_visible_on_random_brand_ui() throws InterruptedException {
	    adminFlashNotifications.navigateToRandomBrandUI();
	    boolean isVisible = adminFlashNotifications.verifyFlashNotificationOnRandomBrandUI();
	    if (!isVisible) {
	        throw new AssertionError("Flash notification '"
	            + adminFlashNotifications.getSavedDescription()
	            + "' was NOT found on "
	            + adminFlashNotifications.getRandomBrandForTC02() + " UI");
	    }
	    System.out.println("✅ Flash notification verified successfully on "
	        + adminFlashNotifications.getRandomBrandForTC02() + " UI");
	}	
   
	    
	    
// TC_UI_Zlaata_FN_04
	    @Given("a flash notification already exists")
	    public void a_flash_notification_already_exists() throws InterruptedException {
	        adminFlashNotifications.verifyFlashNotificationAlreadyExists1();
	        System.out.println("✅ Verified active flash notification exists for brand: "
	            + adminFlashNotifications.getSelectedBrandType());
	    }

	    @When("edit the flash notification")
	    public void edit_the_flash_notification() throws InterruptedException {
	        adminFlashNotifications.editFirstFlashNotificationForSelectedBrand();
	        System.out.println("✅ Flash notification edited for brand: "
	            + adminFlashNotifications.getSelectedBrandType());
	    }

	    @Then("verify updated notification is visible in UI")
	    public void verify_updated_notification_is_visible_in_ui() throws InterruptedException {
	        adminFlashNotifications.navigateToSelectedBrandUI();
	        boolean isVisible = adminFlashNotifications.verifyUpdatedFlashNotificationOnUI();
	        if (!isVisible) {
	            throw new AssertionError("Updated flash notification '"
	                + adminFlashNotifications.getSavedDescription()
	                + "' was NOT found on " + adminFlashNotifications.getSelectedBrandType() + " UI");
	        }
	        System.out.println("✅ Updated flash notification verified successfully on "
	            + adminFlashNotifications.getSelectedBrandType() + " UI");
	    }
	    
// TC_UI_Zlaata_FN_04
	    @When("add scheduled flash notification")
	    public void add_scheduled_flash_notification() throws InterruptedException {
	        adminFlashNotifications.navigateToFlashNotificationModule();
	        adminFlashNotifications.filterByLandingPage();
	        adminFlashNotifications.filterByActiveStatus();
	        adminFlashNotifications.disableFirstActiveLandingPageNotification();
	        adminFlashNotifications.clearFilters();
	        adminFlashNotifications.clickAddFlashNotification();
	        adminFlashNotifications.fillFlashNotificationFormWithSchedule();
	    }

	    @Then("verify notification is NOT visible on UI before toggle")
	    public void verify_notification_is_not_visible_on_ui_before_toggle() throws InterruptedException {
	        boolean notVisible = adminFlashNotifications.verifyFlashNotificationNotVisibleOnUI();
	        if (!notVisible) {
	            throw new AssertionError("Scheduled flash notification was already visible on UI before display toggle was enabled — FAIL");
	        }
	        System.out.println("✅ Scheduled notification correctly NOT visible before toggle — PASS");
	    }

	    @Then("verify notification is visible on UI after toggle")
	    public void verify_notification_is_visible_on_ui_after_toggle() throws InterruptedException {
	        boolean isVisible = adminFlashNotifications.verifyFlashNotificationVisibleAfterStartTime();
	        if (!isVisible) {
	            throw new AssertionError("Scheduled flash notification was NOT visible on UI after enabling display toggle — FAIL");
	        }
	        System.out.println("✅ Scheduled notification correctly visible after toggle — PASS");
	    }
	

}
