package objectRepo;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;

public abstract class SignupObjRepository extends BasePage {
	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button")
	protected WebElement submit;
	
	@FindBy(xpath = "//img[@alt='Cancel']")
	protected WebElement closepopup;
	
//	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup']")
//	protected WebElement profile;
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup ']")
	protected WebElement profile;
	
	@FindBy(xpath = "//button[@class='signup_box_btn']")
	protected WebElement signupButton;

	@FindBy(name = "userName")
	protected WebElement name;
	
	@FindBy(name = "userNumber")
	protected WebElement number;
	
	@FindBy(id = "registerBtn")
	protected WebElement continueButton;
	
	@FindBy(xpath = "(//input[@class='loginOtpInputBox'])[1]")
	protected WebElement otp;
	
	@FindBy(xpath = "//button[@class='verify__otp_btn btn___2']	")
	protected WebElement verify;
	
	@FindBy(id ="err_name")
	protected WebElement validationMsgName;
	
	@FindBy(name ="userMail")
	protected WebElement signUpmail;
	
//	@FindBy(id ="err_contact")
//	protected WebElement validationMsgNumber;
	
	@FindBy(xpath = "//span[@class='error__msg phone_error_msg active']")
	protected WebElement validationMsgNumber;
	
	@FindBy(id ="err_email")
	protected WebElement validationMsgMail;
//	
//	@FindBy(id="err_contact")
//	protected WebElement alreadyUsedNumber;
	
	
	
	@FindBy(id="err_otp")
	protected WebElement phoneNumberOtp;
	
	@FindBy(xpath = "//input[@class='loginOtpInputBox']")
	protected List <WebElement> otpenterBox;
	
	
	
}