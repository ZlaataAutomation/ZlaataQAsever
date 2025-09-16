package stepDef;


import context.TestContext;
import pages.LoginPage;
import io.cucumber.java.en.Given;

public class LoginStepDef {
	TestContext testContext;
	LoginPage login;

	

	public LoginStepDef(TestContext context) {
		testContext = context;
		login = testContext.getPageObjectManager().getLoginPage();
		
	}


	@Given("User going to login in zlaata application {string}")
	public void user_going_to_login_in_zlaata_application(String string) throws InterruptedException {
		
	login.userLogin();
		
} 
		

	@Given("FirstBuy200 Coupon is displaying")
	public void first_buy200_coupon_is_displaying() {

	login.verifyFirstBuy();


		

	}

	@Given("User left phone_number field Empty")
	public void user_left_phone_number_field_empty() {
		
try {
	login.numberFieldEmpty();
} catch (Exception e) {
	System.out.println("💥 Step failed: " + e.getMessage());
    e.printStackTrace();
}
	}


	@Given("User entered phone_number with less than {int} digits")
	public void user_entered_phone_number_with_less_than_digits(Integer int1) {
		try {
			login.numberLessthan10Digit();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}

	@Given("User entered phone_number with more than {int} digits")
	public void user_entered_phone_number_with_more_than_digits(Integer int1) {
		try {
			login.numberMOrethan10();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}

	@Given("User entered phone_number with non-numeric characters")
	public void user_entered_phone_number_with_non_numeric_characters() {
		try {
			login.numberWithNonNumeric();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
		
	}



	@Given("User entered the New phone_number")
	public void user_entered_the_new_phone_number() {
		try {
			login.newNumber();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
		
		
	}



	@Given("User entered phone_number starting with {double}")
	public void user_entered_phone_number_starting_with(Double double1) {
		try {
			login.numberWith123();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}

	@Given("User entered phone_number with Special symbol")
	public void user_entered_phone_number_with_special_symbol() {
		try {
			login.numberSpecialSymbol();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}


	@Given("User entered phone_number along with space")
	public void user_entered_phone_number_along_with_space() {
		try {
			login.numberwithSpaces();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}


	@Given("User clicks on google link")
	public void user_clicks_on_google_link() {
		
			login.mailLogin();
	}


	@Given("User clicks on facebook link")
	public void user_clicks_on_facebook_link() {
		
					login.faceBookLink();
		
		
	}













}




