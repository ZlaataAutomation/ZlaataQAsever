package basePage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import manager.FileReaderManager;
import stepDef.Hooks;
import utils.Common;
import utils.DateUtils;






public abstract class BasePage implements WebDriverService {

	public   WebDriver driver;

	protected abstract boolean isAt();

	protected WebDriverWait wait;

	public void checkDropDownSorting(WebElement source) {
		Select sel = new Select(source);
		List<WebElement> options = sel.getOptions();
		List<String> originalDropDownValues = new ArrayList<String>();
		for (WebElement dd : options) {
			originalDropDownValues.add(dd.getText());
		}
		List<String> sortedValues = new ArrayList<String>(originalDropDownValues);
		Collections.sort(sortedValues);
		if (originalDropDownValues.equals(sortedValues)) {
			System.out.println("matched");
		} else {
			System.out.println("not matched");
		}
		originalDropDownValues.clear();
		sortedValues.clear();
	}

	public void type(WebElement ele, String data) {
		try {
			ExpectedConditions.visibilityOf(ele);
			ele.clear();
			ele.sendKeys(data);
		} catch (Exception e) {
		}
	}

	public void click(WebElement ele) {
		try {
			ExpectedConditions.elementToBeClickable(ele);
				ele.click();
		} catch (StaleElementReferenceException e) {
			ele.click();
		}
	}
	
// Written
	public void selectValueFromDropDown(WebElement clickEle, WebElement typeEle, String data,
			WebElement clickAndSelectele) {
		try {
			ExpectedConditions.elementToBeClickable(clickEle);
			clickEle.click();
			ExpectedConditions.visibilityOf(typeEle);
			typeEle.clear();
			typeEle.sendKeys(data);
			ExpectedConditions.elementToBeClickable(clickAndSelectele);
			clickAndSelectele.click();
		} catch (Exception e) {
			System.out.println("Error occured on selectValueFromDropDown ");

		}
	}

	public String getText(WebElement ele) {
		return ele.getText();
	}

	public String getTextUsingAttribute(String attribute, WebElement ele) {
		String text = ele.getAttribute(attribute);
		return text;
	}

	public void selectDropDown(DropDown dropDown, WebElement ele, String value) {
		try {
			WebElement element = highLightElement(ele);
			Select sel = new Select(element);
			switch (dropDown) {
			case VALUE:
				sel.selectByValue(value);
				break;
			case VISIBLETEXT:
				sel.selectByVisibleText(value);
				break;
			default:
				break;
			}
		} catch (NullPointerException exception) {
			System.out.println("Handled because of mandatory check");
		}
	}

	public void selectDropDown(DropDown dropDown, WebElement ele, int index) {
		Select sel = new Select(ele);
		switch (dropDown) {
		case INDEX:
			sel.selectByIndex(index);
			break;
		default:
			break;
		}
	}

	public boolean verifyExactTitle(String expectedTitle) {
		if (driver.getTitle().equals(expectedTitle)) {
			return true;
		}
		return false;
	}

	public boolean verifyPartialTitle(String expectedTitle) {
		if (driver.getTitle().contains(expectedTitle)) {
			System.out.println("title partially matched");
			return true;
		}
		return false;
	}

	

	public void verifyPartialText(WebElement ele, String expectedText) {
		String text = ele.getText();
		if (text.contains(expectedText)) {
			System.out.println("Expected text " + expectedText + " matched with the actual text" + text);
		} else {
			System.out.println("Expected text " + expectedText + " does not match with the actual text" + text);
		}
	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		String actualAtb = ele.getAttribute(attribute);
		if (actualAtb.equals(value)) {
			System.out.println("Attribute is matched exactly");
		} else {
			System.out.println("Attribute is not matched exactly");
		}
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		String actualAtb = ele.getAttribute(attribute);
		if (actualAtb.equals(value)) {
			System.out.println("Attribute is partially matched");
		} else {
			System.out.println("Attribute is not matched");
		}

	}

	public void verifySelected(WebElement ele) {
		if (ele.isSelected()) {
			System.out.println(ele + " is Selected");
		} else {
			System.out.println(ele + " is not Selected");
		}
	}

	public boolean verifyDisplayed(WebElement ele) {
		if (ele.isDisplayed()) {
			return true;
		}
		return false;
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allwindowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList<String>();
			list.addAll(allwindowHandles);
			driver.switchTo().window(list.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException");
		}
	}

	public void actions(WebElement ele, String actionToePerformed) {
		Actions act = new Actions(driver);
		switch (actionToePerformed) {
		case "moveToElement":
			act.moveToElement(ele).perform();
			break;
		case "click":
			act.click(ele).perform();
			break;
		default:
			break;
		}
	}

	public void scrollToElementUsingJSE(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	public void scrollToElementUsingJSEList(List<WebElement> ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	public WebElement highLightElement(WebElement webElement) {
		if (verifyDisplayed(webElement)) {
			scrollToElementUsingJSE(webElement);
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid green'", webElement);
			}
		}
		return webElement;
	}

	public void typeAndTab(WebElement ele, String data) {
		Actions actions = new Actions(driver);
		try {
			ele.clear();
			ele.sendKeys(data);
			actions.sendKeys(Keys.TAB);
			actions.build().perform();
		} catch (Exception e) {
		}
	}

	public void selectDropDown(List<WebElement> webElements, String elementToBeSelected) {
		boolean isElementExists = false;
		for (WebElement ele : webElements) {
			if (ele.getText().equalsIgnoreCase(elementToBeSelected)) {
				ele.click();
				isElementExists = true;
				break;
			}
		}
		if (isElementExists) {
			System.out.println("Element is selected");
		} else {
			throw new RuntimeException("Element not Exists" + elementToBeSelected);
		}
	}

	public void clearAndType(WebElement ele, String data) {
		try {
			ExpectedConditions.visibilityOf(ele);
			ele.sendKeys(Keys.chord(Keys.CONTROL, "a"), data);
		} catch (Exception e) {
			
		}
		}
	

public static void attachFullPageScreenshot(WebDriver driver) {
	boolean takeScreenshotForAll = FileReaderManager.getInstance().getConfigReader()
			.isScreenShotRequiredForAllScenario();
	System.out.println("Scenario Name :: " + Hooks.scenarioName);
	if (takeScreenshotForAll) {
		String[] subStrings = Hooks.scenarioName.split("\\|");
		String tcID = subStrings[0].trim();

		if ((tcID.split("_")[1]).contains("UI")) {
			try {
				// Ensure the driver is augmented to take full page screenshots
				WebDriver augmentedDriver = new Augmenter().augment(driver);

				// This takes a full page screenshot from the driver and saves it to the
				// specified location
				File sourcePath = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
				Common.createDirIfNotExists(filePath);
				File destinationPath = new File(filePath + File.separator + tcID + "-"
						+ DateUtils.getCurrentLocalDateTimeStamp("yyyyMMddHHmmssSSS") + ".png");

				// Copy the taken screenshot from source location to destination location
				FileHandler.copy(sourcePath, destinationPath);

				// Attach the specified screenshot to the test


				System.out.println("Screenshot taken: " + destinationPath.toString());
			} catch (IOException e) {
				throw new RuntimeException("Some exceptions occurred while taking screenshot!!! Please check ", e);
			}
		} else {
			System.out.println("Scenario ID does not contain 'UI'. No screenshot taken.");
		}
	} else {
		System.out.println("Scenario passed!!!! No Screen capture required!!!");
	}
	
}
public static void attachFullPageScreenshot1(WebDriver driver) {
    boolean takeScreenshotForAll = FileReaderManager.getInstance().getConfigReader()
            .isScreenShotRequiredForAllScenario();
    System.out.println("Scenario Name :: " + Hooks.scenarioName);
    
    if (takeScreenshotForAll) {
        String[] subStrings = Hooks.scenarioName.split("\\|");
        String tcID = subStrings[0].trim();

        if ((tcID.split("_")[1]).contains("UI")) {
            try {
                // Ensure the driver is augmented to take full page screenshots
                WebDriver augmentedDriver = new Augmenter().augment(driver);

                // This takes a full page screenshot from the driver and saves it to the specified location
                File sourcePath = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
                String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
                Common.createDirIfNotExists(filePath);
                File destinationPath = new File(filePath + File.separator + tcID + "-"
                        + DateUtils.getCurrentLocalDateTimeStamp("yyyyMMddHHmmssSSS") + ".png");

                // Copy the taken screenshot from source location to destination location
                FileHandler.copy(sourcePath, destinationPath);

                // Create a new Word document
                XWPFDocument document = new XWPFDocument();

                // Create a paragraph in the document
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("Screenshot for Scenario: " + tcID);

                // Add the screenshot to the Word document
                try (FileInputStream fis = new FileInputStream(destinationPath)) {
                    byte[] imageBytes = IOUtils.toByteArray(fis);
                    String imageFileType = "PNG"; // You can modify this if necessary based on image format
                    XWPFPictureData picture = document.getPictureDataByID(imageFileType);
                  
//                    XWPFPictureData picture = document.addPictureData(imageBytes, XWPFPictureData.PICTURE_TYPE_PNG);
//                    document.createPicture(picture.getPackagePart().getPartName(), XWPFDocument.PICTURE_TYPE_PNG);
                }

                // Save the document
                String wordFilePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"
                        + tcID + "-" + DateUtils.getCurrentLocalDateTimeStamp("yyyyMMddHHmmssSSS") + ".docx";
                try (FileOutputStream out = new FileOutputStream(wordFilePath)) {
                    document.write(out);
                }

                System.out.println("Screenshot saved in Word document: " + wordFilePath);
            } catch (IOException e) {
                throw new RuntimeException("Some exceptions occurred while taking screenshot!!! Please check ", e);
            }
        } else {
            System.out.println("Scenario ID does not contain 'UI'. No screenshot taken.");
        }
    } else {
        System.out.println("Scenario passed!!!! No Screen capture required!!!");
    }
}
	

public void takeScreenshot() {
    try {
        Hooks.myScenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "New");
    } catch (Exception e) {
        e.printStackTrace();
    }
}




	}


	
	

	
	
	
