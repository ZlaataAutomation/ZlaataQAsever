package stepDef;

import java.util.concurrent.TimeoutException;

import context.TestContext;
import pages.NegativeSignupPages;
import io.cucumber.java.en.Given;


public class NegativeStepDef {
	TestContext testContext;
	NegativeSignupPages negsignup;
	public NegativeStepDef(TestContext context) {
		testContext = context;
		negsignup = testContext.getPageObjectManager().getNegativeSignupPages();

	}
	
	@Given("User Signup with all valid data")
	public void user_signup_with_all_valid_data() throws TimeoutException {

		negsignup.signUp();
	}

		@Given("User left name field Empty")
		public void user_left_name_field_empty() {
			try {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userNumber();
				negsignup.contbtn();
				
			} catch (Exception e) {
				  ExceptionTracker.capture(e); // Capture the exact exception
			        throw e; // re-throw so test still fails
			}
			
		}
		
			@Given("User left phone number field Empty")
			public void user_left_phone_number_field_empty() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.contbtn();
			}

		

			@Given("User entered name with less than three characters")
			public void user_entered_name_with_less_than_three_characters() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.nameLessthan3Char();
				negsignup.userNumber();
				negsignup.contbtn();
			}
		

			@Given("User entered phone number with less than {int} digits")
			public void user_entered_phone_number_with_less_than_digits(Integer int1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numberLessthan10Digit();
				negsignup.contbtn();
			}

		

			@Given("User entered email with less than {int} characters")
			public void user_entered_email_with_less_than_characters(Integer int1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.userNumber();
				negsignup.mailIdlessthan3char();
				negsignup.contbtn();
			}

		

			@Given("User entered name with more than {int} characters")
			public void user_entered_name_with_more_than_characters(Integer int1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.nameMorethan50Char();
				negsignup.userNumber();
				negsignup.contbtn();
			    
			}
		

			@Given("User entered phone number with more than {int} digits")
			public void user_entered_phone_number_with_more_than_digits(Integer int1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numbermorethan10();
				negsignup.contbtn();
			}

		

			@Given("User entered email with more than {int} characters")
			public void user_entered_email_with_more_than_characters(Integer int1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.userNumber();
				negsignup.mailIdmorethan50char();
				negsignup.contbtn();
			}
		

			@Given("User entered email in an invalid format")
			public void user_entered_email_in_an_invalid_format() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.userNumber();
				negsignup.invalidmailId();
				negsignup.contbtn();
			}

		

			@Given("User entered phone number with non-numeric characters")
			public void user_entered_phone_number_with_non_numeric_characters() throws TimeoutException {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numberNonNumeric();
				negsignup.contbtn();
			}

		

			@Given("User entered name with special characters")
			public void user_entered_name_with_special_characters() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.namewithSpecialChar();
				negsignup.userNumber();
				negsignup.contbtn();
			}



		

			@Given("User entered the same phone number as twice")
			public void user_entered_the_same_phone_number_as_twice() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numberTwice();
				negsignup.contbtn();
			}

		

			@Given("User entered phone number starting with {double}")
			public void user_entered_phone_number_starting_with(Double double1) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numberstaringwith123();
				negsignup.contbtn();
			}
			


		

			@Given("User entered name with numeric characters")
			public void user_entered_name_with_numeric_characters() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.nameWithNUmeric();
				negsignup.numberstaringwith123();
				negsignup.contbtn();
			}

	

			@Given("User entered phone number with invalid characters \\(e.g., alphabets or symbols)")
			public void user_entered_phone_number_with_invalid_characters_e_g_alphabets_or_symbols() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.numberwithspecialsymbolandChar();
				negsignup.contbtn();
			}
				
			

	
			@Given("User entered email with invalid domain \\(e.g., missing {string} or incorrect domain)")
			public void user_entered_email_with_invalid_domain_e_g_missing_or_incorrect_domain(String string) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.userNumber();
				negsignup.invalidDomainMail();
				negsignup.contbtn();
			}


		

			@Given("User entered email with multiple {string} symbols")
			public void user_entered_email_with_multiple_symbols(String string) {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.userName();
				negsignup.userNumber();
				negsignup.mailwithMultipleSpecialSymbol();
				negsignup.contbtn();
			    
			}

		

			@Given("User entered name with mixed case and special characters")
			public void user_entered_name_with_mixed_case_and_special_characters() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.nameWithMixedChar();
				negsignup.userNumber();
				negsignup.contbtn();
			}

		

			@Given("User left all required fields empty \\(email is optional)")
			public void user_left_all_required_fields_empty_email_is_optional() {
				negsignup.launchZltV7();
				negsignup.ClickProfileIcon();
				negsignup.signupbutton();
				negsignup.contbtn();
			}
			
				@Given("User enters invalid OTP while login")
				public void user_enters_invalid_otp_while_login() {
					negsignup.launchZltV7();
					negsignup.ClickProfileIcon();
					negsignup.signupbutton();
					negsignup.userName();
//					negsignup.userNumber();
					negsignup.invalidOTP();
					negsignup.verifyButton();
					
				}
				
					

						@Given("User entering same mail id which is already registered")
						public void user_entering_same_mail_id_which_is_already_registered() {
							negsignup.launchZltV7();
							negsignup.ClickProfileIcon();
							negsignup.signupbutton();
							negsignup.userName();
							negsignup.userNumber();
							negsignup.sameMail();
							negsignup.contbtn();
						}









}
