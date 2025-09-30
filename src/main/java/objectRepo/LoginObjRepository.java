

package objectRepo;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import basePage.BasePage;

public abstract class LoginObjRepository extends BasePage {
	
	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submit;
	
	@FindBy(xpath = "//img[@alt='Cancel']")
	protected WebElement closepopup;
	
//	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup']")
//	protected WebElement profile;
	
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup ']")
	protected WebElement profile;
	
	@FindBy(id = "userNumber")
	protected WebElement loginNumber;
	
	@FindBy(xpath = "//button[@class='send_otp_btn btn___2']")
	protected WebElement sendotp;
	
	@FindBy(xpath = "//form[@class='digit-group login_otp_input_form']")
	protected WebElement otpEnterTextBox;
	
	@FindBy(id ="err_otp")
	protected WebElement validationForWrongOTP;
	
	@FindBy(id="otpContainer")
	protected WebElement otpEnterAfterTimefinish;
	
	@FindBy(xpath = "//button[@class='login_resend_otp_btn active']")
	protected WebElement resendOtpButton;
	
	@FindBy(id = "digit-1")
	protected WebElement enterotp;
	
	@FindBy(xpath = "//button[@onclick='submitOTP()']")
	protected WebElement verifyotp;
	
	@FindBy(xpath = "//div[@class='login_coupon_code_wrap']")
	protected WebElement firstBuyCode;
	
	@FindBy(id = "google-login-link")
	protected WebElement mailIcon;
	
	@FindBy(id = "facebook-login-link")
	protected WebElement faceBookIcon;

	@FindBy(id = "identifierId")
	protected WebElement emailId;
	
	@FindBy(id = "identifierNext")
	protected WebElement nextButton;
	
	@FindBy(xpath = "//span[@class='error__msg error_ph login phone_error_msg active']")
	protected WebElement validationMessage;
	
	@FindBy(xpath = "//span[@class='error__msg error_ph login phone_error_msg active']")
	protected WebElement userNotRegisterValidationMessage;
	
	@FindBy(id = "identifierId")
	protected WebElement entermailID;
	
	@FindBy(id ="identifierNext")
	protected WebElement emailPageNxtButton;
	
	@FindBy(xpath = "//input[@type='password']")
	protected WebElement enterEmailPassword;
	
	@FindBy(xpath = "//span[.='Next']")
	protected WebElement emailPasswordNxtPage;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement mailLoginValidationMessage;
	
	@FindBy(id = "email")
	protected WebElement enterFaceBookNumber;
	
	@FindBy(id ="pass")
	protected WebElement enterFaceBookPasswrod;
	
	@FindBy(id = "loginbutton")
	protected WebElement clickOnFaceBookLoginButton;
	
	@FindBy(xpath = "//span[contains(text(),'Continue as Priya')]")
	protected WebElement clickOnContinueButton;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement facebookLoginValidationMessage;
	
   @FindBy(xpath = "//span[@class='x1lliihq x193iq5w x6ikm8r x10wlt62 xlyipyv xuxw1ft']")
   protected WebElement captchaContinueButton;
	
   @FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup ']")
   protected WebElement profileicon;
   
   @FindBy(xpath = "//a[@class='account_sidebar_user_profile_link_acc_settings']")
   protected WebElement myProfiletext;
	
	
	
	

}
