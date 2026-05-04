package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class AccountSettingsObjRepo  extends BasePage {
	
	
	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button")
	protected WebElement submit;
	
	@FindBy(xpath = "//button[@class='header_cta_btn account_icon_btn ']")
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
	
	@FindBy(xpath = "//a[@class='account_sidebar_user_profile_link_acc_settings']")
	protected WebElement myProfileicon;
	
	@FindBy(xpath = "//button[@class='as_save_changes_btn cls_account_save']")
	protected WebElement savechangesButton;
	
	@FindBy(xpath = "//input[@id='fname']")
	protected WebElement accountSettingsName;
	
	@FindBy(xpath = "//span[@class='error__msg name_error_msg active']")
	protected WebElement nameTextBoxValidation;
	
	@FindBy(xpath = "//span[@class='error__msg date_error_msg active']")
	protected WebElement dateOfbirthTextBoxValidation;
	
	@FindBy(xpath = "//span[@class='error__msg gender_error_msg active']")
	protected WebElement genderTextBoxValidation;
	
	@FindBy(xpath = "//span[@class='error__msg email_error_msg active']")
	protected WebElement emailIdTextBoxValidation;
	
	@FindBy(id = "cls_emailID")
	protected WebElement accountSettingsEmailId;

	@FindBy(xpath = "//input[@id='account_setting_dob']")
	protected WebElement dateOfBirthTextBox;
	
	@FindBy(id = "userNumber")
	protected WebElement loginNumber;
	
	@FindBy(xpath = "//button[@class='send_otp_btn btn___2']")
	protected WebElement sendotp;
	
	@FindBy(xpath = "//form[@class='digit-group login_otp_input_form']")
	protected WebElement otpEnterTextBox;
	
	@FindBy(id = "digit-1")
	protected WebElement enterotp;
	
	@FindBy(xpath = "//button[@class='verify__otp_btn btn___2']")
	protected WebElement verifyotp;
	
	@FindBy(xpath = "//button[@class='verification-button verified']")
	protected WebElement verifiedTextInPhoneNumberTextBox;
	
	@FindBy(id = "cls_emailID")
	protected WebElement emailInput;
	
	@FindBy(id ="email_verification")
	protected WebElement verifyBtn;
	

	@FindBy(xpath = "//button[@class='verify__otp_btn btn___2']")
	protected WebElement verifyOTPButton;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement validationOfsameId;
	
	
	@FindBy(xpath = "//input[@id='male']")
	protected WebElement maleRadioButton;

	@FindBy(xpath = "//input[@id='female']")
	protected WebElement femaleRadioButton;

	@FindBy(xpath = "//input[@id='others']")
	protected WebElement othersRadioButton;
	
	@FindBy(xpath = "//a[normalize-space()='Contact']")
	protected WebElement contactMenuButton;
	
	@FindBy(xpath = "//input[@role='searchbox']")
	protected WebElement searchBoxdropdown;
	
	 @FindBy(xpath = "//i[@class='las la-edit']")
	    protected WebElement editItemButton;
	 
	 @FindBy(id = "mobileNumber")
	 protected WebElement accountMobileNumber;
	
	
	@FindBy(xpath = "//input[@name='name']")
	protected WebElement adminpanelcustomername;
	
	@FindBy(xpath = "//input[@name='email']")
	protected WebElement adminpanelcustomeremail;
	
	@FindBy(xpath = "//input[@type='number' and @name='contact']")
	protected WebElement adminpanelcustomeraccountPhonenumber;
	
	@FindBy(xpath = "//a[normalize-space()='Set Key']")
	protected WebElement clickOnSetkey;
	
	@FindBy(xpath = "//input[@name='set_value']")
	protected WebElement setvalue;
	
	
	
	
	
}


