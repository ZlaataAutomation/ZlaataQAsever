package stepDef;

import java.util.concurrent.TimeoutException;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.AccountSettingsPage;


public   class AccountSettingsStepDef  {



	TestContext testContext;
	AccountSettingsPage  account;


	public AccountSettingsStepDef(TestContext context) {
		testContext = context;
		account =testContext.getPageObjectManager().getAccountSettingsPage();
	}

		@Given("the user is on Account Settings")
	public void the_user_is_on_account_settings() throws TimeoutException {
			account.allValidationMessage();
			account.dateofBirthTextBox();
			account.verifiedTextInvisbelInPhoneNumberTextBox();
			account.verifythatuserabletosubscripforemailId();
			account.verifyAllGenderRadioButtonsClickable();
			account.enterallvaliddataAndVerifyInadminPanel();
			account.verifyNameEditLimitAndCompareAdminVsApplication();
			account.verifyDOBEditLimitAndCompareAdminVsApplication();

	}



















}
