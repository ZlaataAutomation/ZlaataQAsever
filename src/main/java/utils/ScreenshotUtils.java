package utils;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Enumeration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.Augmenter;
import org.zeroturnaround.zip.commons.FileUtils;

import com.vimalselvam.cucumber.listener.Reporter;
import manager.FileReaderManager;
import stepDef.Hooks;

import io.cucumber.java.Scenario;

public class ScreenshotUtils {

	public static void attachScreenshot(WebDriver driver) {
		boolean takeScreenshotForAll = FileReaderManager.getInstance().getConfigReader()
				.isScreenShotRequiredForAllScenario();
		System.out.println("Scenario Name :: " + Hooks.scenarioName);
		if (takeScreenshotForAll) {
			String[] subStrings = Hooks.scenarioName.split("\\|");
			String tcID = subStrings[0].trim();

			if ((tcID.split("_")[1]).contains("UI")) {
				try {
					// This takes a screenshot from the driver and saves it to the specified
					// location
					File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
					Common.createDirIfNotExists(filePath);
					File destinationPath = new File(filePath + File.separator + tcID + "-"
							+ DateUtils.getCurrentLocalDateTimeStamp("yyyyMMddHHmmssSSS") + ".png");

					// Copy the taken screenshot from source location to destination location
					FileUtils.copyFile(sourcePath, destinationPath);

					// Attach the specified screenshot to the test
					Reporter.addScreenCaptureFromPath(destinationPath.toString());

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
					Reporter.addScreenCaptureFromPath(destinationPath.toString());

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

	public static void captureFailureScreenshot(Enumeration<Driver> drivers, Scenario scenario) {
		// TODO Auto-generated method stub
		
	}
}
