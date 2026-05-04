package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.AccountSettingsObjRepo;
import utils.Common;

public  final class AccountSettingsPage extends AccountSettingsObjRepo {


	public AccountSettingsPage(WebDriver driver) 
		{
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		}
	


	public void homeLunch() {

		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
		click(submit);
		//        popup();
	}
	
	
	public static String generatedUserNumber = "";

	public void signUp() throws TimeoutException {
		
	
		// Open profile and signup
		click(profile);
		click(signupButton);
		// Enter name
		type(name, FileReaderManager.getInstance().getJsonReader().getValueFromJson("UserName"));
		// Try entering phone number, retry once if validation fails
		userNumber();
		click(continueButton);
		otp.sendKeys(FileReaderManager.getInstance().getJsonReader().getValueFromJson("OTP"));
		click(verify);
		System.out.println("✅ OTP entered and verified");
	}
	
	public void userNumber() {
		Common.waitForElement(1);
		String userNumber = generate10DigitNumber();
		type(number, userNumber);
	}

	
	private static Random rnd = new Random();

	public static String generate10DigitNumber() {
		// Generate first digit between 6-9
		int firstDigit = 6 + rnd.nextInt(4); // 6,7,8,9

		// Generate remaining 9 digits
		long remaining = (long) (rnd.nextDouble() * 1_000_000_000L); // 0 to 999999999

		// Combine first digit and remaining digits ensuring exactly 10 digits
		String number = firstDigit + String.format("%09d", remaining);

		System.out.println("Generated user number: " + number);
		return number;
	}

	 
	
	
	
	public void allValidationMessage() throws TimeoutException {

	    String GREEN = "\u001B[32m";
	    String RED = "\u001B[31m";
	    String YELLOW = "\u001B[33m";
	    String BLUE = "\u001B[34m";
	    String RESET = "\u001B[0m";

	    homeLunch();
	    signUp();

	    Common.waitForElement(3);
	    profile.click();
	    myProfileicon.click();

	    System.out.println(BLUE + "========== ACCOUNT SETTINGS VALIDATION STARTED ==========" + RESET);

	    // =====================================================
	    // 1. Mandatory Field Validation
	    // =====================================================

	    System.out.println(YELLOW + "----- MANDATORY FIELD VALIDATION -----" + RESET);

	    accountSettingsName.clear();
	    savechangesButton.click();
	    Common.waitForElement(2);

	    // Name Validation
	    String actualNameValidation = nameTextBoxValidation.getText();
	    String expectedNameValidation = "Name should be between 3 and 50 characters.";

	    if (actualNameValidation.equals(expectedNameValidation)) {
	        System.out.println(GREEN + "✅ Name validation displayed correctly: "
	                + actualNameValidation + RESET);
	    } else {
	        System.out.println(RED + "❌ Name validation mismatch. Expected: "
	                + expectedNameValidation + " | Found: " + actualNameValidation + RESET);
	    }

	    // DOB Validation
	    String actualDobValidation = dateOfbirthTextBoxValidation.getText();
	    String expectedDobValidation = "Please enter a valid date of birth.";

	    if (actualDobValidation.equals(expectedDobValidation)) {
	        System.out.println(GREEN + "✅ DOB validation displayed correctly: "
	                + actualDobValidation + RESET);
	    } else {
	        System.out.println(RED + "❌ DOB validation mismatch. Expected: "
	                + expectedDobValidation + " | Found: " + actualDobValidation + RESET);
	    }

	    // Gender Validation
	    String actualGenderValidation = genderTextBoxValidation.getText();
	    String expectedGenderValidation = "Please select a gender.";

	    if (actualGenderValidation.equals(expectedGenderValidation)) {
	        System.out.println(GREEN + "✅ Gender validation displayed correctly: "
	                + actualGenderValidation + RESET);
	    } else {
	        System.out.println(RED + "❌ Gender validation mismatch. Expected: "
	                + expectedGenderValidation + " | Found: " + actualGenderValidation + RESET);
	    }

	    // Email Validation
	    String actualEmailValidation = emailIdTextBoxValidation.getText();
	    String expectedEmailValidation = "Please enter a valid email address.";

	    if (actualEmailValidation.equals(expectedEmailValidation)) {
	        System.out.println(GREEN + "✅ Email validation displayed correctly: "
	                + actualEmailValidation + RESET);
	    } else {
	        System.out.println(RED + "❌ Email validation mismatch. Expected: "
	                + expectedEmailValidation + " | Found: " + actualEmailValidation + RESET);
	    }

	    // =====================================================
	    // 2. Name Character Limit Validation
	    // =====================================================

	    System.out.println(BLUE + "========== NAME CHARACTER LIMIT VALIDATION ==========" + RESET);

	    String excelData = Common.getValueFromTestDataMap("UserName");
	    type(accountSettingsName, excelData);

	    String uiData = accountSettingsName.getAttribute("value");

	    System.out.println(YELLOW + "Excel Data Entered      : " + excelData);
	    System.out.println("Excel Data Length       : " + excelData.length());
	    System.out.println("Application Saved Data  : " + uiData);
	    System.out.println("Application Data Length : " + uiData.length() + RESET);

	    if (uiData.length() <= 50) {
	        System.out.println(GREEN + "✅ Name field accepted only 50 characters" + RESET);
	    } else {
	        System.out.println(RED + "❌ Name field exceeded more than 50 characters" + RESET);
	    }

	    // =====================================================
	    // 3. Email Character Limit Validation
	    // =====================================================

	    System.out.println(BLUE + "========== EMAIL CHARACTER LIMIT VALIDATION ==========" + RESET);

	    String excelData1 = Common.getValueFromTestDataMap("Email Id");
	    type(accountSettingsEmailId, excelData1);

	    String uiData1 = accountSettingsEmailId.getAttribute("value");

	    System.out.println(YELLOW + "Excel Data Entered      : " + excelData1);
	    System.out.println("Excel Data Length       : " + excelData1.length());
	    System.out.println("Application Saved Data  : " + uiData1);
	    System.out.println("Application Data Length : " + uiData1.length() + RESET);

	    if (uiData1.length() <= 50) {
	        System.out.println(GREEN + "✅ Email field accepted only 50 characters" + RESET);
	    } else {
	        System.out.println(RED + "❌ Email field exceeded more than 50 characters" + RESET);
	    }

	    // =====================================================
	    // 4. Invalid Name Validation
	    // =====================================================

	    System.out.println(BLUE + "========== INVALID NAME VALIDATION ==========" + RESET);

	    String excelData2 = Common.getValueFromTestDataMap("Account setting name");
	    accountSettingsName.clear();
	    type(accountSettingsName, excelData2);
	    savechangesButton.click();
	    Common.waitForElement(2);

	    String actualMessage = nameTextBoxValidation.getText();
	    String uiData2 = accountSettingsName.getAttribute("value");

	    Assert.assertTrue(
	            "Name should be between 3 and 50 characters.",
	            actualMessage.equals("Name should be between 3 and 50 characters.")
	    );

	    System.out.println(YELLOW + "📥 Excel Data: " + excelData2
	            + " | Length: " + excelData2.length() + RESET);

	    System.out.println(BLUE + "📤 Application UI Data: " + uiData2
	            + " | Length: " + uiData2.length() + RESET);

	    System.out.println(GREEN + "📤 Validation Message: "
	            + actualMessage + RESET);

	    // =====================================================
	    // 5. Invalid Email Validation
	    // =====================================================

	    System.out.println(BLUE + "========== INVALID EMAIL VALIDATION ==========" + RESET);

	    String excelDatamail = Common.getValueFromTestDataMap("account setting email id");
	    accountSettingsEmailId.clear();
	    type(accountSettingsEmailId, excelDatamail);
	    savechangesButton.click();
	    Common.waitForElement(2);

	    String actualMessagefromail = emailIdTextBoxValidation.getText();
	    String uiDatamail = accountSettingsEmailId.getAttribute("value");

	    Assert.assertTrue(
	            "Please enter a valid email address.",
	            actualMessagefromail.equals("Please enter a valid email address.")
	    );

	    System.out.println(YELLOW + "📥 Excel Data: " + excelDatamail
	            + " | Length: " + excelDatamail.length() + RESET);

	    System.out.println(BLUE + "📤 Application UI Data: " + uiDatamail
	            + " | Length: " + uiDatamail.length() + RESET);

	    System.out.println(GREEN + "📤 Validation Message: "
	            + actualMessagefromail + RESET);

	    // =====================================================
	    // 6. Mixed Character Name Validation
	    // =====================================================

	    System.out.println(BLUE + "========== MIXED CHARACTER NAME VALIDATION ==========" + RESET);

	    String value = Common.getValueFromTestDataMap("Account setting name With Mixed Char");

	    System.out.println(YELLOW + "📥 Excel Data: " + value
	            + " | Length: " + value.length() + RESET);

	    accountSettingsName.clear();
	    type(accountSettingsName, value);

	    savechangesButton.click();
	    Common.waitForElement(2);

	    String actualMessageforname = nameTextBoxValidation.getText();

	    Assert.assertTrue(
	            "Name should be between 3 and 50 characters.",
	            actualMessageforname.equals("Name should be between 3 and 50 characters.")
	    );

	    String enteredName = accountSettingsName.getAttribute("value");

	    System.out.println(BLUE + "📤 Application UI Data: " + enteredName
	            + " | Length: " + enteredName.length() + RESET);

	    System.out.println(GREEN + "🛑 Name Validation Message Displayed: "
	            + actualMessageforname + RESET);
	    
	    
	    
	 // =====================================================
	 // 1. Space Validation for Name and Email
	 // =====================================================

	    System.out.println(BLUE + "========== SPACE VALIDATION FOR NAME & EMAIL ==========" + RESET);

	 // Hardcoded values
	 String nameSpaceValue = "     ";
	 String emailSpaceValue = "     ";

	 // Name field - only spaces
	 accountSettingsName.clear();
	 type(accountSettingsName, nameSpaceValue);

	 System.out.println(YELLOW + "📥 Name Entered Data: '" + nameSpaceValue
	         + "' | Length: " + nameSpaceValue.length() + RESET);

	 // Email field - only spaces
	 accountSettingsEmailId.clear();
	 type(accountSettingsEmailId, emailSpaceValue);

	 System.out.println(YELLOW + "📥 Email Entered Data: '" + emailSpaceValue
	         + "' | Length: " + emailSpaceValue.length() + RESET);

	 // Click Save Changes
	 savechangesButton.click();
	 Common.waitForElement(2);

	 // Name validation message
	 String actualNameSpaceValidation = nameTextBoxValidation.getText();
	 String expectedNameSpaceValidation = "Name should be between 3 and 50 characters.";

	 System.out.println(BLUE + "📤 Name Validation Message: "
	         + actualNameSpaceValidation + RESET);

	 if (actualNameSpaceValidation.equals(expectedNameSpaceValidation)) {
	     System.out.println(GREEN + "✅ Name field does not accept only spaces" + RESET);
	 } else {
	     System.out.println(RED + "❌ Name field space validation mismatch. Expected: "
	             + expectedNameSpaceValidation + " | Found: " + actualNameSpaceValidation + RESET);
	 }

	 // Email validation message
	 String actualEmailSpaceValidation = emailIdTextBoxValidation.getText();
	 String expectedEmailSpaceValidation = "Please enter a valid email address.";

	 System.out.println(BLUE + "📤 Email Validation Message: "
	         + actualEmailSpaceValidation + RESET);

	 if (actualEmailSpaceValidation.equals(expectedEmailSpaceValidation)) {
	     System.out.println(GREEN + "✅ Email field does not accept only spaces" + RESET);
	 } else {
	     System.out.println(RED + "❌ Email field space validation mismatch. Expected: "
	             + expectedEmailSpaceValidation + " | Found: " + actualEmailSpaceValidation + RESET);
	 }	 
	
	 System.out.println(BLUE + "========== MIDDLE SPACE VALIDATION FOR NAME & EMAIL ==========" + RESET);

	// ================= NAME FIELD =================

	// Hardcoded Name value (two spaces in middle)
	String nameValue = "Gowtham  Kumar";
	accountSettingsName.clear();
	type(accountSettingsName, nameValue);

	System.out.println(YELLOW + "📥 Name Entered Data: " + nameValue
	        + " | Length: " + nameValue.length() + RESET);

	// ================= EMAIL FIELD =================

	// Hardcoded Email value (space in middle)
	String emailValue = "gowtham kumar@gmail.com";
	accountSettingsEmailId.clear();
	type(accountSettingsEmailId, emailValue);

	System.out.println(YELLOW + "📥 Email Entered Data: " + emailValue
	        + " | Length: " + emailValue.length() + RESET);

	// Click Save Changes
	savechangesButton.click();
	Common.waitForElement(2);

	// ================= VERIFY NAME =================

	String savedNameValue = accountSettingsName.getAttribute("value");

	System.out.println(BLUE + "📤 Application Saved Name: " + savedNameValue
	        + " | Length: " + savedNameValue.length() + RESET);

	/*
	Expected:
	Input  = Gowtham  Kumar
	Output = Gowtham Kumar

	Only one middle space should be accepted
	*/

	if (savedNameValue.equals("Gowtham Kumar")) {
	    System.out.println(GREEN + "✅ Name field accepts only one space in middle correctly" + RESET);
	} else {
	    System.out.println(RED + "❌ Name field middle space validation failed" + RESET);
	}

	// ================= VERIFY EMAIL =================

	String savedEmailValue = accountSettingsEmailId.getAttribute("value");

	System.out.println(BLUE + "📤 Application Saved Email: " + savedEmailValue
	        + " | Length: " + savedEmailValue.length() + RESET);

	/*
	Expected:
	Email should not accept middle space
	Check whether space is accepted or removed
	*/

	if (savedEmailValue.contains(" ")) {
	    System.out.println(RED + "❌ Email field accepted space in middle" + RESET);
	} else {
	    System.out.println(GREEN + "✅ Email field does not accept space in middle" + RESET);
	}
	    System.out.println(BLUE + "========== VALIDATION COMPLETED SUCCESSFULLY ==========" + RESET);
	}
	
//	 public void userLogin() {
//	    	
//		 
//	    	Common.waitForElement(1);
//	    	click(profile);
//	    	loginNumber.sendKeys("9976875232");
//	        Common.waitForElement(1);
//	        click(sendotp);
//	      type(enterotp, FileReaderManager.getInstance().getJsonReader().getValueFromJson("OTP"));
//	        click(verifyotp);
//	        Common.waitForElement(3); // small buffer
//	            System.out.println("\u001B[32m✅ Login successful\u001B[0m");
//	       
//	         // ❌ Red color
////	           System.out.println("\u001B[31m❌ Login failed: OTP verification or redirection failed\u001B[0m");
//	 }
	
	
	public void userLogin() {

	    String GREEN = "\u001B[32m";
	    String RED = "\u001B[31m";
	    String BLUE = "\u001B[34m";
	    String RESET = "\u001B[0m";

//	    homeLunch();

	    Common.waitForElement(1);

	    click(profile);

	    // Use same generated signup number
	    loginNumber.clear();
	    loginNumber.sendKeys("7630817544");

	    System.out.println(BLUE + "📱 Login using generated number: "
	            + generatedUserNumber + RESET);

	    Common.waitForElement(1);

	    click(sendotp);

	    type(enterotp,
	            FileReaderManager.getInstance()
	                    .getJsonReader()
	                    .getValueFromJson("OTP"));

	    click(verifyotp);

	    Common.waitForElement(3);

	    if (!generatedUserNumber.isEmpty()) {
	        System.out.println(GREEN + "✅ Login successful using same generated number"
	                + RESET);
	    } else {
	        System.out.println(RED + "❌ Generated user number is empty"
	                + RESET);
	    }
	}
	 public void dateofBirthTextBox() {

		    String GREEN  = "\u001B[32m";
		    String RED    = "\u001B[31m";
		    String YELLOW = "\u001B[33m";
		    String BLUE   = "\u001B[34m";
		    String RESET  = "\u001B[0m";

//		    homeLunch();
//		    userLogin();

		    profile.click();
		    myProfileicon.click();

		    System.out.println(BLUE + "========== DATE OF BIRTH CALENDAR VALIDATION ==========" + RESET);

		    // Click Date of Birth calendar field
		    click(dateOfBirthTextBox);
		    Common.waitForElement(2);

		    // Verify calendar opened
		    if (dateOfBirthTextBox.isDisplayed()) {
		        System.out.println(GREEN + "✅ Date of Birth calendar opened successfully" + RESET);
		    } else {
		        System.out.println(RED + "❌ Date of Birth calendar did not open" + RESET);
		    }

	

		    // Get max allowed DOB date from UI
		    String actualMaxDate = dateOfBirthTextBox.getAttribute("max");

		    // Dynamic expected date = Today - 10 years
		    LocalDate today = LocalDate.now();
		    LocalDate expectedDate = today.minusYears(10);
		    String expectedMaxDate = expectedDate.toString();

		    System.out.println(BLUE + "📅 UI Maximum Allowed DOB Date : "
		            + actualMaxDate + RESET);

		    System.out.println(YELLOW + "📅 Expected Maximum DOB Date   : "
		            + expectedMaxDate + RESET);

		    // Validation
		    if (actualMaxDate.equals(expectedMaxDate)) {
		        System.out.println(GREEN + "✅ Today date is disabled correctly (Only 10+ age allowed)" + RESET);
		    } else {
		        System.out.println(RED + "❌ Today button/current date restriction failed" + RESET);
		        System.out.println(RED + "Expected: " + expectedMaxDate
		                + " | Found: " + actualMaxDate + RESET);
		    }

		    System.out.println(BLUE + "========== DOB VALIDATION COMPLETED ==========" + RESET);
		}
	 public void verifiedTextInvisbelInPhoneNumberTextBox() {

		 String GREEN  = "\u001B[32m";
		    String RED    = "\u001B[31m";
		    String YELLOW = "\u001B[33m";
		    String BLUE   = "\u001B[34m";
		    String RESET  = "\u001B[0m";

//		  homeLunch();
		  
		    profile.click();
		    myProfileicon.click();

		    System.out.println(BLUE + "========== PHONE NUMBER VERIFICATION VALIDATION ==========" + RESET);

		    String verifiedText = verifiedTextInPhoneNumberTextBox.getText();

		    System.out.println(YELLOW + "📱 Verification Text Found: "
		            + verifiedText + RESET);

		    if (verifiedText.equalsIgnoreCase("Verified")) {
		        System.out.println(GREEN + "✅ Phone number is automatically verified successfully" + RESET);
		    } else {
		        System.out.println(RED + "❌ Phone number is not automatically verified" + RESET);
		        System.out.println(RED + "Expected: Verified | Found: "
		                + verifiedText + RESET);
		    }

		    System.out.println(BLUE + "========== PHONE NUMBER VALIDATION COMPLETED ==========" + RESET);
		}
	// Add this variable at class level (outside all methods)

	 public static String lastGeneratedEmail = "";

	 public void verifythatuserabletosubscripforemailId() {

	     String GREEN  = "\u001B[32m";
	     String RED    = "\u001B[31m";
	     String YELLOW = "\u001B[33m";
	     String BLUE   = "\u001B[34m";
	     String RESET  = "\u001B[0m";

//	     homeLunch();
//	     userLogin();
	     profile.click();
	     myProfileicon.click();
	     
	

	  System.out.println(BLUE + "========== VERIFY ALREADY TAKEN EMAIL VALIDATION ==========" + RESET);

	  // Example hardcoded already verified email
	  String alreadyTakenEmail = "charile30072000@gmail.com";

	  emailInput.clear();
	  emailInput.sendKeys(alreadyTakenEmail);

	  System.out.println(YELLOW + "📥 Re-entering Same Email: "
	          + alreadyTakenEmail + RESET);

	  // Click Verify Button
	  verifyBtn.click();
	  Common.waitForElement(2);

	  // Get validation message
	  String actualTakenMessage = validationOfsameId.getText();
	  String expectedTakenMessage = "The email id has already been taken.";

	  System.out.println(BLUE + "📤 Validation Message Found: "
	          + actualTakenMessage + RESET);

	  // Validation check
	  if (actualTakenMessage.equals(expectedTakenMessage)) {
	      System.out.println(GREEN + "✅ Already taken email validation displayed correctly" + RESET);
	  } else {
	      System.out.println(RED + "❌ Already taken email validation mismatch" + RESET);
	      System.out.println(RED + "Expected: " + expectedTakenMessage
	              + " | Found: " + actualTakenMessage + RESET);
	  }

	     System.out.println(BLUE + "========== EMAIL SUBSCRIPTION VALIDATION ==========" + RESET);

	     // =====================================================
	     // Step 1 : Invalid Email Validation
	     // =====================================================

	     String invalidEmail = "gowtham@@gmail";

	     emailInput.clear();
	     emailInput.sendKeys(invalidEmail);

	     System.out.println(YELLOW + "📥 Invalid Email Entered: "
	             + invalidEmail + RESET);

	     savechangesButton.click();
	     Common.waitForElement(2);

	     List<WebElement> errorList = driver.findElements(By.id("errorMsg"));

	     if (!errorList.isEmpty() && errorList.get(0).isDisplayed()) {
	         System.out.println(GREEN + "✅ Invalid email validation displayed successfully" + RESET);
	         System.out.println(GREEN + "📤 Error Message: "
	                 + errorList.get(0).getText() + RESET);
	     } else {
	         System.out.println(RED + "❌ Invalid email validation not displayed" + RESET);
	     }

	     // =====================================================
	     // Step 2 : Valid Email Verification
	     // =====================================================

	     int maxRetries = 5;
	     String validEmail = "";

	     for (int i = 0; i < maxRetries; i++) {

	         emailInput.clear();

	         String email = generateRandomEmail1();

	         System.out.println(YELLOW + "📥 Trying Valid Email: "
	                 + email + RESET);

	         emailInput.sendKeys(email);
	         verifyBtn.click();
	         Common.waitForElement(2);

	         List<WebElement> validationCheck =
	                 driver.findElements(By.id("errorMsg"));

	         if (!validationCheck.isEmpty()
	                 && validationCheck.get(0).isDisplayed()) {

	             System.out.println(RED + "❌ Error shown. Retrying..." + RESET);
	             continue;

	         } else {

	             validEmail = email;
	             lastGeneratedEmail = email;

	             System.out.println(GREEN + "✅ Valid Email Accepted: "
	                     + validEmail + RESET);

	             break;
	         }
	     }

	     if (validEmail.isEmpty()) {
	         throw new RuntimeException(
	                 RED + "❌ Failed to generate valid email after "
	                         + maxRetries + " attempts!" + RESET);
	     }

	     // =====================================================
	     // Step 3 : OTP Verification
	     // =====================================================

	     System.out.println(BLUE + "🔐 Entering OTP for: "
	             + validEmail + RESET);

	     type(enterotp,
	             FileReaderManager.getInstance()
	                     .getJsonReader()
	                     .getValueFromJson("OTP"));

	     click(verifyOTPButton);

	     System.out.println(GREEN + "🎉 SUCCESS: Email Verified → "
	             + validEmail + RESET);

	     // =====================================================
	     // Step 4 : Verify Email is Verified After Refresh
	     // =====================================================

	     driver.navigate().refresh();
	     Common.waitForElement(2);

	    
	     profile.click();
	     myProfileicon.click();

	     System.out.println(BLUE + "========== EMAIL VERIFIED TEXT VALIDATION ==========" + RESET);

	     // Verify button text check (same like phone number verified text)
	     String verifyButtonText = verifiedTextInPhoneNumberTextBox.getText();

	     System.out.println(YELLOW + "📧 Email Verify Button Text Found: "
	             + verifyButtonText + RESET);

	     if (verifyButtonText.equalsIgnoreCase("Verified")) {
	         System.out.println(GREEN + "✅ Email is automatically verified successfully" + RESET);
	     } else {
	         System.out.println(RED + "❌ Email is not verified" + RESET);
	         System.out.println(RED + "Expected: Verified | Found: "
	                 + verifyButtonText + RESET);
	     }

	     System.out.println(BLUE + "========== EMAIL VERIFIED VALIDATION COMPLETED ==========" + RESET);
	 }
	     
	     
		public void verifyNameEditLimitAndCompareAdminVsApplication() {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		    // ================= ADMIN =================
//		    AdminPanelPage admin = new AdminPanelPage(driver);
//		    admin.adminLogin();

		    String baseUrl = driver.getCurrentUrl().split("/admin")[0];
		    driver.get(baseUrl + "/admin/general-setting");

		    clickOnSetkey.click();
		    searchBoxdropdown.sendKeys("accnt_name");
		    searchBoxdropdown.sendKeys(Keys.ENTER);
		    editItemButton.click();

		    String adminValueText = setvalue.getAttribute("value");

		    if (adminValueText == null || adminValueText.isEmpty()) {
		        Assert.fail("❌ Admin value is empty");
		    }

		    int adminValue = Integer.parseInt(adminValueText);
		    System.out.println("Admin Allowed Count: " + adminValue);

		    // ================= USER =================

		    Common.waitForElement(2);
		    homeLunch();

//		    Common.waitForElement(3);   // ✅ ADDED LOGIN WAIT
//		    userLogin();
		    
		    

		    Common.waitForElement(5);   // ✅ ADDED AFTER LOGIN STABILIZATION

		    wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
		    wait.until(ExpectedConditions.elementToBeClickable(myProfileicon)).click();

		    WebElement nameField = wait.until(
		            ExpectedConditions.visibilityOf(accountSettingsName)
		    );

		    // ================= INITIAL NAME =================
		    String initialName = nameField.getAttribute("value");
		    System.out.println("Initial Name: " + initialName);

		    int actualEditCount = 0;

		    // ================= EDIT LOOP =================
		    for (int i = 1; i <= adminValue; i++) {

		        String newName = generateRandomName();

		        nameField.click();
		        nameField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		        nameField.sendKeys(newName);

		        savechangesButton.click();

Common.waitForElement(5);
		        wait.until(ExpectedConditions.visibilityOf(accountSettingsName));
		        nameField = accountSettingsName;

		        String updatedName = nameField.getAttribute("value");

		        System.out.println("After Edit " + i + ": " + updatedName);

		        actualEditCount++;
		    }

		    // ================= FINAL VALIDATION =================
		    System.out.println("Application Edit Count: " + actualEditCount);

		    if (actualEditCount == adminValue) {
		        System.out.println("✅ Edit count matches admin limit");
		    } else {
		        Assert.fail("❌ Edit count mismatch");
		    }
		}
		public String generateRandomName() {

		    String[] names = {
		        "Gowtham", "Arun", "Karthik", "Vijay", "Rahul",
		        "Suresh", "Manoj", "Ajay", "Rakesh", "Naveen"
		    };

		    Random random = new Random();

		    return names[random.nextInt(names.length)];
		}

//	 
	 public void verifyDOBEditLimitAndCompareAdminVsApplication() {

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

//		    // ================= ADMIN =================
//		    AdminPanelPage admin = new AdminPanelPage(driver);
//		    admin.adminLogin();

//		    String baseUrl = driver.getCurrentUrl().split("/admin")[0];
//		    driver.get(baseUrl + "/admin/general-setting");
		    

		    driver.get(Common.getValueFromTestDataMap("ExcelPath"));

		    clickOnSetkey.click();
		    searchBoxdropdown.sendKeys("acct_date_of_bith");
		    searchBoxdropdown.sendKeys(Keys.ENTER);
		    editItemButton.click();

		    String adminValueText = setvalue.getAttribute("value");

		    if (adminValueText == null || adminValueText.isEmpty()) {
		        Assert.fail("❌ Admin DOB value is empty");
		    }

		    int adminValue = Integer.parseInt(adminValueText);
		    System.out.println("Admin DOB Edit Allowed Count: " + adminValue);

//		    // ================= USER =================
		    Common.waitForElement(2);
		    homeLunch();
//
//		    Common.waitForElement(3);
//		    userLogin();

		    Common.waitForElement(5);

		    wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
		    wait.until(ExpectedConditions.elementToBeClickable(myProfileicon)).click();

		    WebElement dobField = wait.until(
		            ExpectedConditions.visibilityOf(dateOfBirthTextBox)
		    );

		    String initialDOB = dobField.getAttribute("value");
		    System.out.println("Initial DOB: " + initialDOB);

		    int actualEditCount = 0;

		    // ================= EDIT LOOP =================
		    for (int i = 1; i <= adminValue; i++) {

		        // ✅ FIX: DD-MM-YYYY format
		        int day = (i % 28) + 1;
		        int month = (i % 12) + 1;
		        int year = 1990 + (i % 30);

		        String newDOB = String.format("%02d-%02d-%04d", day, month, year);

		        dobField = wait.until(ExpectedConditions.visibilityOf(dateOfBirthTextBox));
		        dobField.click();

		        dobField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		        dobField.sendKeys(newDOB);

		        savechangesButton.click();

		        Common.waitForElement(5);

		        dobField = wait.until(ExpectedConditions.visibilityOf(dateOfBirthTextBox));
		        String updatedDOB = dobField.getAttribute("value");

		        System.out.println("After DOB Edit " + i + ": " + updatedDOB);

		        actualEditCount++;
		    }

		    // ================= FINAL VALIDATION =================
		    System.out.println("Application DOB Edit Count: " + actualEditCount);

		    if (actualEditCount == adminValue) {
		        System.out.println("✅ DOB edit count matches admin limit");
		    } else {
		        Assert.fail("❌ DOB edit count mismatch");
		    }
		}
		
	 
		
		
		public void verifyAllGenderRadioButtonsClickable() {

		    String GREEN  = "\u001B[32m";
		    String RED    = "\u001B[31m";
		    String BLUE   = "\u001B[34m";
		    String RESET  = "\u001B[0m";

//		    homeLunch();
//		    userLogin();
 Common.waitForElement(5);
		    profile.click();
		    myProfileicon.click();

		    System.out.println(BLUE + "========== GENDER RADIO BUTTON VALIDATION ==========" + RESET);

		    // =====================================================
		    // Male Radio Button
		    // =====================================================

		    click(maleRadioButton);
		    Common.waitForElement(1);

		    if (maleRadioButton.isSelected()) {
		        System.out.println(GREEN + "✅ Male radio button clicked successfully" + RESET);
		    } else {
		        System.out.println(RED + "❌ Male radio button click failed" + RESET);
		    }

		    // =====================================================
		    // Female Radio Button
		    // =====================================================

		    click(femaleRadioButton);
		    Common.waitForElement(1);

		    if (femaleRadioButton.isSelected()) {
		        System.out.println(GREEN + "✅ Female radio button clicked successfully" + RESET);
		    } else {
		        System.out.println(RED + "❌ Female radio button click failed" + RESET);
		    }

		    // =====================================================
		    // Others Radio Button
		    // =====================================================

		    click(othersRadioButton);
		    Common.waitForElement(1);

		    if (othersRadioButton.isSelected()) {
		        System.out.println(GREEN + "✅ Others radio button clicked successfully" + RESET);
		    } else {
		        System.out.println(RED + "❌ Others radio button click failed" + RESET);
		    }

		    System.out.println(BLUE + "========== GENDER RADIO VALIDATION COMPLETED ==========" + RESET);
		}
		
		 String storedPhoneNumber ="";
		public void enterallvaliddataAndVerifyInadminPanel() {

		    String GREEN  = "\u001B[32m";
		    String RED    = "\u001B[31m";
		    String YELLOW = "\u001B[33m";
		    String BLUE   = "\u001B[34m";
		    String RESET  = "\u001B[0m";

//		    homeLunch();
//		    userLogin();

		    profile.click();
		    myProfileicon.click();
		    System.out.println(BLUE + "========== RANDOM DOB & GENDER SAVE VALIDATION ==========" + RESET);

		 // =====================================================
		 // RANDOM DOB GENERATION (Above 10 years)
		 // =====================================================

		 LocalDate today = LocalDate.now();
		 LocalDate maxDate = today.minusYears(10);

		 Random random = new Random();

		 int year = 1990 + random.nextInt(maxDate.getYear() - 1990 + 1);
		 int month = 1 + random.nextInt(12);
		 int day = 1 + random.nextInt(28);

		 LocalDate randomDob = LocalDate.of(year, month, day);

		 if (randomDob.isAfter(maxDate)) {
		     randomDob = maxDate;
		 }

		 String dobValue = randomDob.toString();

		 // =====================================================
		 // FIX: DO NOT USE sendKeys FOR DATE INPUT
		 // =====================================================

		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].value='" + dobValue + "';", dateOfBirthTextBox);

		 System.out.println(YELLOW + "📅 Random DOB Set via JS: " + dobValue + RESET);

		    // =====================================================
		    // RANDOM GENDER SELECTION
		    // =====================================================

		    String selectedGender = "";

		    int genderChoice = random.nextInt(3);

		    if (genderChoice == 0) {
		        click(maleRadioButton);
		        selectedGender = "Male";
		    } else if (genderChoice == 1) {
		        click(femaleRadioButton);
		        selectedGender = "Female";
		    } else {
		        click(othersRadioButton);
		        selectedGender = "Others";
		    }

		    System.out.println(YELLOW + "👤 Random Gender Selected: " + selectedGender + RESET);

		    // =====================================================
		    // SAVE BUTTON CLICK
		    // =====================================================
		    Common.waitForElement(3);

		    savechangesButton.click();
		    Common.waitForElement(3);

		    System.out.println(BLUE + "💾 Save Changes Button Clicked" + RESET);

		    // =====================================================
		 // =====================================================
		 // STORE ACCOUNT SETTINGS DATA (APPLICATION)
		 // =====================================================

		 String storedName = accountSettingsName.getAttribute("value");
		 String storedEmail = adminpanelcustomeremail.getAttribute("value");
		 String storedDob = dateOfBirthTextBox.getAttribute("value");
		 String storedPhoneNumber = accountMobileNumber.getAttribute("value");

		 String storedGender = "";

		 if (maleRadioButton.isSelected()) {
		     storedGender = "Male";
		 } else if (femaleRadioButton.isSelected()) {
		     storedGender = "Female";
		 } else if (othersRadioButton.isSelected()) {
		     storedGender = "Others";
		 }

		 // =====================================================
		 // PRINT APP DATA
		 // =====================================================

		 System.out.println(GREEN + "========== STORED ACCOUNT SETTINGS DATA ==========" + RESET);

		 System.out.println("📌 Name   : " + storedName);
		 System.out.println("📌 Email  : " + storedEmail);
		 System.out.println("📌 DOB    : " + storedDob);
		 System.out.println("📌 Gender : " + storedGender);
		 System.out.println("📌 Phone  : " + storedPhoneNumber);

		 System.out.println(GREEN + "===================================================" + RESET);

		 // =====================================================
		 // APP VALIDATION
		 // =====================================================

		 if (storedDob.equals(dobValue) && storedGender.equals(selectedGender)) {
		     System.out.println(GREEN + "✅ Data saved successfully in Account Settings" + RESET);
		 } else {
		     System.out.println(RED + "❌ Data mismatch after saving (APP SIDE)" + RESET);
		     System.out.println("Expected DOB    : " + dobValue + " | Found: " + storedDob);
		     System.out.println("Expected Gender : " + selectedGender + " | Found: " + storedGender);
		 }

		 System.out.println(BLUE + "========== METHOD COMPLETED ==========" + RESET);


		 // =====================================================
		 // ADMIN LOGIN
		 // =====================================================

		 AdminPanelPage admin = new AdminPanelPage(driver);
		 admin.adminLogin();

		 System.out.println(GREEN + "✅ Admin login successful" + RESET);

		 // Navigate to admin customer page
		 String baseUrl = driver.getCurrentUrl().split("/admin")[0];
		 driver.get(baseUrl + "/admin/customer");

		 Common.waitForElement(2);

		 System.out.println(BLUE + "========== ADMIN CUSTOMER SEARCH VALIDATION ==========" + RESET);

		 // =====================================================
		 // ADMIN SEARCH
		 // =====================================================

		 click(contactMenuButton);
		 Common.waitForElement(2);

		 System.out.println(YELLOW + "📥 Searching Phone in Admin: " + storedPhoneNumber + RESET);

		 searchBoxdropdown.click();
		 searchBoxdropdown.clear();
		 searchBoxdropdown.sendKeys(storedPhoneNumber);
		 searchBoxdropdown.sendKeys(Keys.ENTER);

		 Common.waitForElement(3);

		 // click edit/open record
		 editItemButton.click();
		 Common.waitForElement(2);

		 // =====================================================
		 // ADMIN DATA CAPTURE
		 // =====================================================

		 String adminName = adminpanelcustomername.getAttribute("value");
		 String adminEmail = adminpanelcustomeremail.getAttribute("value");
		 String adminPhone = adminpanelcustomeraccountPhonenumber.getAttribute("value");

		 // =====================================================
		 // PRINT ADMIN DATA
		 // =====================================================

		 System.out.println(GREEN + "========== ADMIN DATA ==========" + RESET);

		 System.out.println("📌 Name   : " + adminName);
//		 System.out.println("📌 Email  : " + adminEmail);
		 System.out.println("📌 Phone  : " + adminPhone);

		 System.out.println(GREEN + "================================" + RESET);

		 // =====================================================
		 // FINAL VALIDATION (APP vs ADMIN)
		 // =====================================================

		 if (storedName.equals(adminName)
		         && storedEmail.equals(adminEmail)
		         && storedPhoneNumber.equals(adminPhone)) {

		     System.out.println(GREEN + "🎉 SUCCESS: APP and ADMIN data MATCHED" + RESET);

		 } else {

		     System.out.println(RED + "❌ DATA MISMATCH BETWEEN APP & ADMIN" + RESET);

		     System.out.println("APP Name   : " + storedName + " | ADMIN Name   : " + adminName);
		     System.out.println("APP Email  : " + storedEmail + " | ADMIN Email  : " + adminEmail);
		     System.out.println("APP Phone  : " + storedPhoneNumber + " | ADMIN Phone  : " + adminPhone);
		 }
			    }
//		public void nameanddateOfbirthTextBoxusercanableeditarenotonadminpanelcount() {
//
//		    // ================= ADMIN =================
//		    AdminPanelPage admin = new AdminPanelPage(driver);
//		    admin.adminLogin();
//
//		    String baseUrl = driver.getCurrentUrl().split("/admin")[0];
//		    driver.get(baseUrl + "/admin/general-setting");
//
//		    clickOnSetkey.click();
//		    Common.waitForElement(2);
//
//		    searchBoxdropdown.sendKeys("accnt_name");
//		    searchBoxdropdown.sendKeys(Keys.ENTER);
//		    Common.waitForElement(2);
//
//		    editItemButton.click();
//
//		    String value = setvalue.getAttribute("value");
//
//		    if (value == null || value.isEmpty()) {
//		        Assert.fail("❌ Admin value is empty");
//		    }
//
//		    int adminValue = Integer.parseInt(value);
//
//		    // ================= USER =================
//		    homeLunch();
//		    userLogin();
//
//		    profile.click();
//		    myProfileicon.click();
//		    
//		    Common.waitForElement(2);
//
//		    // Assume 1 already used in signup
//		    int usedEdits = 1;
//		    int remainingEdits = adminValue - usedEdits;
//
//		    System.out.println("Remaining edits: " + remainingEdits);
//
//		    // ================= ONLY ONE EDIT =================
//		    if (remainingEdits > 0) {
//
//		        String randomName = generateRandomName();
//
//		        accountSettingsName.clear();
//		        Common.waitForElement(2);
//		        accountSettingsName.sendKeys(randomName);
//		        savechangesButton.click();
//
//		        System.out.println("✅ First edit saved");
//
//		    } else {
//		        Assert.fail("❌ No edits allowed");
//		    }
//
//		    // ================= TRY SECOND EDIT (SHOULD FAIL) =================
//		    boolean isEditable = true;
//
//		    try {
//		        accountSettingsName.clear();
//		        accountSettingsName.sendKeys(generateRandomName());
//		        savechangesButton.click();
//		    } catch (Exception e) {
//		        isEditable = false;
//		    }
//
//		    if (isEditable) {
//		        Assert.fail("❌ User able to edit again (limit not working)");
//		    } else {
//		        System.out.println("✅ Edit blocked after limit");
//		    }
//		}
		
	
		
		
		
		private static String generateRandomEmail1() {

		    String prefix = "ranjith";
		    String digits = "0123456789";

		    Random rnd = new Random();
		    StringBuilder email = new StringBuilder(prefix);

		    // Generate 4 random digits
		    for (int i = 0; i < 4; i++) {
		        email.append(digits.charAt(
		                rnd.nextInt(digits.length())));
		    }

		    email.append("@gmail.com");

		    lastGeneratedEmail = email.toString();

		    return lastGeneratedEmail;
		}


		public static String getLastGeneratedEmail1() {
		    return lastGeneratedEmail;
		}
@Override
public boolean verifyExactText(WebElement ele, String expectedText) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public WebDriver gmail(String browserName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected boolean isAt() {
	// TODO Auto-generated method stub
	return false;

}
}