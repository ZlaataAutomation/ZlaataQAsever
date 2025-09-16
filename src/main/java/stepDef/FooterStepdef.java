package stepDef;
import context.TestContext;
import io.cucumber.java.en.Given;
import pages.FooterPage;
import pages.HomePage;

public class FooterStepdef  {


	TestContext testContext;
	FooterPage footer;
	HomePage home;


	public FooterStepdef(TestContext context) {
		testContext = context;
		footer = testContext.getPageObjectManager().getFooterPage();
		home = testContext.getPageObjectManager().getHomePage();
	}

	@Given("User going to click Shop All footer links")
	public void user_going_to_click_shop_all_footer_links() {
		home.homeLaunch();	
		footer.footerShopAllLinks();
	}


	@Given("User verifies the payment logo is visible in footer")
	public void user_verifies_the_payment_logo_is_visible_in_footer() {
		home.homeLaunch();	
		footer.paymentMethods();

		
	}




	//
	//	@Given("User verifies Contact Us details are visible in footer")
	//	public void user_verifies_contact_us_details_are_visible_in_footer() {
	//		home.homeLaunch();
	//		footer.contactUS();
	//	}
	//
	//

	//	@Given("User verifies the Zlaata logo is visible in footer")
	//	public void user_verifies_the_zlaata_logo_is_visible_in_footer() {
	//		home.homeLaunch();
	//		footer.zlaataLogo();
	//	}



	@Given("User verifies social media icons are visible in footer")
	public void user_verifies_social_media_icons_are_visible_in_footer() {
		home.homeLaunch();
		footer.socialMedia();
	}


	@Given("User verifies Zlaata email ID is visible in footer")
	public void user_verifies_zlaata_email_id_is_visible_in_footer() {
		home.homeLaunch();
		footer.copyRights();
	}


	@Given("User enters a new valid email ID in the newsletter subscription field")
	public void user_enters_a_new_valid_email_id_in_the_newsletter_subscription_field() {
		home.homeLaunch();
		footer.footerSubscribe();
	}


	@Given("User enters an already subscribed email ID in the newsletter subscription field")
	public void user_enters_an_already_subscribed_email_id_in_the_newsletter_subscription_field() {
		home.homeLaunch();
		footer.footerSubscribeAlready();
	}


	@Given("User enters an invalid email ID in the newsletter subscription field")
	public void user_enters_an_invalid_email_id_in_the_newsletter_subscription_field() {
		home.homeLaunch();
		footer.invalidMail();
	}










}
