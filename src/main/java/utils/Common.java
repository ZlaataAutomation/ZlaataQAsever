package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vimalselvam.cucumber.listener.Reporter;
import stepDef.Hooks;

public final class Common {
	static WebDriver driver;
	private Common() {
	}

	public static void waitForElement(int waitTime) {
		try {
			Thread.sleep(waitTime * 1000);
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isAllValuesAreTrueInList(List<Boolean> values) {
		for (boolean value : values) {
			if (!value) {
				return false;
			}
		}
		return values != null && values.size() > 0;
	}

	public static void createDirIfNotExists(String directPath) {
		File directory = new File(directPath);
		if (!directory.exists()) {
			directory.mkdirs();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
		} else {
			// System.out.println(directPath + " :: Already exits");
		}
	}

	public static String getValueFromHashMap(Map<String, String> dataMap, String key) {
		String value = dataMap.get(key);
		if (isStringEmptyOrNULL(value)) {
//            throw new RuntimeException("The requested key has " + key + "has null or empty value. Please check");
		}
		return value;
	}

 	public static boolean isStringEmptyOrNULL(String string) {
		boolean isEmptyORNull = false;
		try {
			if (Objects.isNull(string) || string.isEmpty() || string.isEmpty()) {
				isEmptyORNull = true;
			} else {
				// System.out.println("String is not null or empty!!! ");
			}
		} catch (Exception e) {
			System.out.println(string + "String you have passed has Null or Empty  ");
		}
		return isEmptyORNull;
	}

	public static String getKeyFromHashMap(Map<String, String> dataMap, int index) {
		List<String> list = new ArrayList<>(dataMap.keySet());
		list.get(index);
		isStringEmptyOrNULL(list.get(index));
		String value = list.get(index).split("\\|")[0];
		return value;
	}

	public static boolean verifyStringValue(String fieldName, String resp, String value) {
		boolean isAsExpected = false;
		int startNum = Integer.parseInt(value.split("\\|")[0]);
		int endNum = Integer.parseInt(value.split("\\|")[1]);
		String expectedValue = value.split("\\|")[2];
		String actualValue = resp.substring(startNum - 1, endNum);
		Reporter.addStepLog(fieldName + " - Actual Value :: " + actualValue + "  Expected value :: " + expectedValue);
		if (actualValue.equalsIgnoreCase(expectedValue)) {
			isAsExpected = true;
		}
		return isAsExpected;
	}

	public static boolean isAllValuesAreAsExpected(List<String> values, String expectedValue) {
		for (String value : values) {
			if (!value.equalsIgnoreCase(expectedValue)) {
				return false;
			}
		}
		return values != null && values.size() > 0;
	}

	public static void createWorkbook(String Path, String tcID) {
		File file = new File(Path);
		XSSFWorkbook workbook;
		if (file.exists()) {
			System.out.println(file.getName() + "is already exists..");
		} else {
			try (FileOutputStream is = new FileOutputStream(file)) {
				workbook = new XSSFWorkbook();
				workbook.createSheet(tcID);
				OutputStream fileOut = Files.newOutputStream(new File(Path).toPath());
				workbook.write(fileOut);
				System.out.println(file.getName() + "is not exists.. File Created");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String leftPaddingZero(String currentStr, int totalLength) {
		String valueStr = (currentStr == "ZEROS" ? " " : currentStr);
		return String.format("%" + totalLength + "s", valueStr).replace(' ', '0');
	}

	public static String getValueFromTestDataMap(String key) {
		String value = Hooks.testDataMap.get(key);
		if (isStringEmptyOrNULL(value)) {
//            throw new RuntimeException("The requested key has " + key + "has null or empty value. Please check");
		}
		return value;
	}

	public static String getSaltString(int len) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < len) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public static List<String> listFilesUsingDirectoryStream(String dir) throws IOException {
		List<String> fileSet = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
			for (Path path : stream) {
				if (!Files.isDirectory(path)) {
					fileSet.add(dir+path.getFileName().toString());
				}
			}
		}
		return fileSet;
	}
	
	 public static int[] splitAmount(int totalAmount, int numberOfUsers) {
	        if (numberOfUsers <= 0) {
	            throw new IllegalArgumentException("Number of users must be greater than zero");
	        }
	        int[] splitAmounts = new int[numberOfUsers];
	        double individualAmount = (double) totalAmount / numberOfUsers;

	        for (int i = 0; i < numberOfUsers; i++) {
	            splitAmounts[i] = (int) individualAmount;
	        }
	        return splitAmounts;
	    }
	 public static void safeClick(WebDriver driver, WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        wait.until(ExpectedConditions.elementToBeClickable(element));
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		        wait.until(ExpectedConditions.elementToBeClickable(element));
		        element.click();
		    } catch (ElementClickInterceptedException e) {
		        // fallback to JS click
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		    } catch (StaleElementReferenceException e) {
		        // element went stale — re-find and retry once
		        try {
		            WebElement fresh = driver.findElement(By.xpath(getElementXPath(element)));
		            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fresh);
		            fresh.click();
		        } catch (Exception ignore) { /* best-effort */ }
		    }
		}

		// NOTE: getElementXPath is left as a placeholder if you want re-find capability.
		// If you don't have a reliable way to map element->xpath, you can remove the StaleElementReference fallback.
		private static String getElementXPath(WebElement el) {
		    try {
		        return el.getAttribute("xpath");
		    } catch (Exception e) {
		        return "";
		    }
		}
   
}
