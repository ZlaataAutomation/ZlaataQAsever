package basePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebDriverService {

	/**
	 * This method will enter the value in the given text field
	 * 
	 * @param ele  - The Webelement (text field) in which the data to be entered
	 * @param data - The data to be sent to the webelement
	 * @throws ElementNotVisibleException *
	 */
	public void type(WebElement ele, String data);

	/**
	 * This method will click the element and take snap
	 * @param ele - The Webelement (button/link/element) to be clicked
	 */
	public void click(WebElement ele);

	/**
	 * This method will get the text of the element
	 * 
	 * @param ele - The Webelement (button/link/element) in which text to be
	 *            retrieved
	 */
	/**
	 * This method will select the drop down visible text
	 * 
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param value The value to be selected (visibletext) from the dropdown
	 */
	/*
	 * public void selectDropDownUsingVisibleText(WebElement ele, String value) ;
	 * 
	 *//**
		 * This method will select the drop down using value
		 * 
		 * @param ele   - The Webelement (dropdown) to be selected
		 * @param value The value attribute to be selected from the dropdown
		 */
	/*
	 * public void selectDropDownUsingValue(WebElement ele, String value) ;
	 * 
	 *//**
		 * This method will select the drop down using index
		 * 
		 * @param ele   - The Webelement (dropdown) to be selected
		 * @param index The index to be selected from the dropdown
		 *//*
			 * public void selectDropDownUsingIndex(WebElement ele, int index) ;
			 */

	/**
	 * This method will verify browser actual title with expected
	 * 
	 * @param title - The expected title of the browser
	 */
	public boolean verifyExactTitle(String expectedTitle);

	/**
	 * This method will verify browser actual title with expected text using
	 * contains
	 * 
	 * @param title - The expected title of the browser
	 */
	public boolean verifyPartialTitle(String expectedTitle);

	/**
	 * This method will verify exact given text with actual text on the given
	 * element
	 *
	 * @param ele          - The Webelement in which the text to be need to be
	 *                     verified
	 * @param expectedText - The expected text to be verified
	 * @return
	 */
	public boolean verifyExactText(WebElement ele, String expectedText);

	/**
	 * This method will verify given text contains actual text on the given element
	 * 
	 * @param ele          - The Webelement in which the text to be need to be
	 *                     verified
	 * @param expectedText - The expected text to be verified
	 */
	public void verifyPartialText(WebElement ele, String expectedText);

	/**
	 * This method will verify exact given attribute's value with actual value on
	 * the given element
	 * 
	 * @param ele       - The Webelement in which the attribute value to be need to
	 *                  be verified
	 * @param attribute - The attribute to be checked (like value, href etc)
	 * @param value     - The value of the attribute
	 */
	public void verifyExactAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify partial given attribute's value with actual value on
	 * the given element
	 * 
	 * @param ele       - The Webelement in which the attribute value to be need to
	 *                  be verified
	 * @param attribute - The attribute to be checked (like value, href etc)
	 * @param value     - The value of the attribute
	 */
	public void verifyPartialAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify if the element (Radio button, Checkbox) is selected
	 * 
	 * @param ele - The Webelement (Radio button, Checkbox) to be verified
	 */
	public void verifySelected(WebElement ele);

	/**
	 * This method will verify if the element is visible in the DOM
	 * 
	 * @param ele - The Webelement to be checked
	 * @return
	 */
	public boolean verifyDisplayed(WebElement ele);

	/**
	 * This method will switch to the Window of interest
	 * 
	 * @param index The window index to be switched to. 0 -> first window
	 */
	public void switchToWindow(int index);
	
	public WebDriver gmail(String browserName);
}

