package stepDef;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.Augmenter;
import com.google.common.io.Files;
import context.Context;
import context.TestContext;
import dataProviders.TestData;
import manager.FileReaderManager;
import utils.*;
import utils.ExcelReportUtil.TestResult;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Hooks {
	TestContext testContext;
	static {
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket$Listener").setLevel(Level.OFF);
    }

	public Hooks(TestContext context) {
		testContext = context;
		driver=context.getWebDriverManager().getDriver();
	}

	public static Map<String, String> testDataMap;
	public static Map<String, String> paymentFileUploadMap;
	public static String scenarioName;
	public static Scenario myScenario;
	public static WebDriver driver;
	private static Scenario scenario;
	private long startTime;
	public static ThreadLocal<Throwable> lastError = new ThreadLocal<>();

//	private String extractFeatureName(Scenario scenario) {
//        try {
//            // Example: "features/signup.feature:5"
//            String id = scenario.getId();
//            if (id != null && id.contains(":")) {
//                String path = id.split(":")[0]; // features/signup.feature
//                String[] parts = path.replace("\\", "/").split("/");
//                String nameWithExt = parts[parts.length - 1]; // signup.feature
//                
//                return nameWithExt.replace(".feature", "").trim(); // signup
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "UnknownFeature";
//    }


	@Before(order=0)
	public void preCondition(Scenario scenario) throws IOException {
		
		 
	    
//		myScenario = scenario;
//		try {
//	        if (driver != null) {
//	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	            scenario.attach(screenshot, "image/png", "Initial Screenshot");
//	        } else {
//	            scenario.log("⚠️ Driver is null, screenshot not taken.");
//	        }
//	    } catch (Exception e) {
//	        scenario.log("❌ Failed to capture screenshot in @Before hook: " + e.getMessage());
//	        e.printStackTrace();
//	    }
        
        
		scenarioName = scenario.getName();
		Long startTime = System.currentTimeMillis();
		scenarioName = scenario.getName();
		testContext.getScenarioContext().setContext(Context.TC_START_TIME, startTime);
		String scenarioName = scenario.getName().trim();
		String[] subStrings = scenarioName.split("\\|");
		String tcID = subStrings[0].trim();
		testContext.getScenarioContext().setContext(Context.TC_ID, tcID);
		scenarioName = subStrings[1].trim();
		String tdID = subStrings[2].replace("\"", "").trim();
		testContext.getScenarioContext().setContext(Context.TD_ID, tdID);
		TestData testData = new TestData(tcID);
		testDataMap = testData.getTestData(tcID, tdID, scenarioName);
		//				String reportFileName = FileReaderManager.getInstance().getConfigReader().getTestReportPath(tcID, tdID);
		//				testContext.getScenarioContext().setContext(Context.TEST_REPORT_FILE_NAME, reportFileName);

	}
	@Before
	public void before_Scenario(Scenario scenario) {
	    System.out.println("\n---------------------------------------------------");
	    System.out.println("Starting scenario: " + scenario.getName());
	    Hooks.scenario = scenario;
	    startTime = System.currentTimeMillis();
	    String featureName = scenario.getUri().toString().split("/")[scenario.getUri().toString().split("/").length - 1];
	    ExcelReportUtil.executedFeatures.add(featureName.replace(".feature", ""));
	
	}
	public static Scenario getScenario() {
        return scenario;
    }
	
//	@After(order = 1)
//	public void attachScreenshot(Scenario scenario) {
//		if (scenario.isFailed()) {
//			
//			String scenarioName = scenario.getName();
//			String[] subStrings = scenarioName.split("\\|");
//			String tcID = subStrings[0].trim();
//			if ((tcID.split("_")[1]).contains("UI")) {
//				try {
//					// This takes a screenshot from the driver at save it to the specified location
//
//					File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver())
//							.getScreenshotAs(OutputType.FILE);
//					String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
//					Common.createDirIfNotExists(filePath);
//					File destinationPath = new File(filePath + File.separator + tcID + ".png");
//					// Copy taken screenshot from source location to destination location
//					Files.copy(sourcePath, destinationPath);
//					// This attach the specified screenshot to the test
//					//					Reporter.addScreenCaptureFromPath(destinationPath.toString());
//
//
//				} catch (IOException e) {
//					throw new RuntimeException("Some exceptions occurred while taking sceenshot!!! Please check ");
//				}
//			} else {
//				System.out.println("Sorry can't take any screenshot");
//			}
//		} else {
//			System.out.println("Scenario getting passed!!! No Screen capture required!!!");
//		}
//
//	}

//	public static void attachFullPageScreenshot(WebDriver driver) {
//		boolean takeScreenshotForAll = FileReaderManager.getInstance().getConfigReader()
//				.isScreenShotRequiredForAllScenario();
//		System.out.println("Scenario Name :: " + Hooks.scenarioName);
//
//		if (takeScreenshotForAll) {
//			String[] subStrings = Hooks.scenarioName.split("\\|");
//			String tcID = subStrings[0].trim();
//
//			if ((tcID.split("_")[1]).contains("UI")) {
//				try {
//					// Ensure the driver is augmented to take full page screenshots
//					WebDriver augmentedDriver = new Augmenter().augment(driver);
//
//					// This takes a full page screenshot from the driver and saves it to the
//					// specified location
//					File sourcePath = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//					String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
//					Common.createDirIfNotExists(filePath);
//					File destinationPath = new File(filePath + File.separator + tcID + "-"
//							+ DateUtils.getCurrentLocalDateTimeStamp("yyyyMMddHHmmssSSS") + ".png");
//
//					// Copy the taken screenshot from source location to destination location
//					FileHandler.copy(sourcePath, destinationPath);
//
//					// Attach the specified screenshot to the test
//					//					Reporter.addScreenCaptureFromPath(destinationPath.toString());
//
//					System.out.println("Screenshot taken: " + destinationPath.toString());
//				} catch (IOException e) {
//					throw new RuntimeException("Some exceptions occurred while taking screenshot!!! Please check ", e);
//				}
//			} else {
//				System.out.println("Scenario ID does not contain 'UI'. No screenshot taken.");
//			}
//		} else {
//			System.out.println("Scenario passed!!!! No Screen capture required!!!");
//		}
//	}	


	//	@After(order = 2)
	public void findTotalTimeForExecution() {
		String tcID = (String) testContext.getScenarioContext().getContext(Context.TC_ID);
		//		Long startTime = (Long) testContext.getScenarioContext().getContext(Context.TC_START_TIME);
		//		Long totalTimeTaken = System.currentTimeMillis() - startTime;
		String module = tcID.split("_")[1].toUpperCase();
		switch (module) {
		case "UI":
			//		case "RT":
			//			Reporter.addScenarioLog("<p><b>" + "Execution time in Seconds: "			//					+ TimeUnit.MILLISECONDS.toSeconds(totalTimeTaken) + "</b></p>");
			//			break;
			//		case "BP":
			//			Reporter.addScenarioLog("<p><b>" + "Execution time in Minutes: "
			//					+ TimeUnit.MILLISECONDS.toMinutes(totalTimeTaken) + "</b></p>");
			break;
		default:
			throw new RuntimeException(
					"Test Data Path not specified in the application.properties file for module" + module);
		}


	}
	 
	
	

	@After(order=1)
	public void afterScenario(Scenario scenario) {
		
		

//			System.out.println("\n---------------------------------------------------");
//	        System.out.println("\u001B[31m" + "Scenario failed: " + scenario.getName() + "\u001B[0m");
//	        System.out.println("---------------------------------------------------\n");
			
			  ExcelReportUtil.ensureReportFileExists();
		if (scenario.isFailed()) {

            try {
		        if (driver != null) {
		            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		            scenario.attach(screenshot, "image/png", "Initial Screenshot");
		            
		           
		        } else {
		        	
		            scenario.log("⚠️ Driver is null, screenshot not taken.");
		        }
		    } catch (Exception e) {
		        scenario.log("❌ Failed to capture screenshot in @After hook: " + e.getMessage());
		        e.printStackTrace();
		    }
	        
	        
			
		} else {
			System.out.println("\n---------------------------------------------------");
	        System.out.println("\u001B[32m"+ "Scenario passed: " + scenario.getName() + "\u001B[0m");
	        System.out.println("Scenario getting passed!!! No Screen capture required!!!");
		}
	}
	
	
	@After(order = 2)
	public void tearDown() throws IOException {
		testContext.getWebDriverManager().closeDriver();

	}
	
	@AfterStep
	public void afterStep(Scenario scenario) {
	    if (scenario.isFailed()) {
	        try {
	            throw new Exception("Step failed");
	        } catch (Exception e) {
	            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	            for (StackTraceElement element : stackTrace) {
	                if (element.getClassName().contains("StepDef")) { 
	                    ExceptionTracker.lastError.set(e);
	                    break;
	                }
	            }
	        }
	    }
	}
	
	@After
	public void tearDown(Scenario scenario) {
	    long endTime = System.currentTimeMillis();
	    double duration = (endTime - startTime) / 1000.0;

	    String testCaseId = scenario.getSourceTagNames().stream()
	            .filter(tag -> tag.startsWith("@TC"))
	            .findFirst()
	            .orElse("@NA").substring(1);

	    String testCaseName = scenario.getName();
	    String executedBy = System.getProperty("user.name", "QA");
	    String status = scenario.isFailed() ? "Fail" : "Pass";

	    String screenshotPath = null;
	    if (scenario.isFailed()) {
	        screenshotPath = ExcelReportUtil.captureScreenshot(driver, testCaseId);
	    }

	    String errorMessage = "N/A";
	    if (scenario.isFailed() && ExceptionTracker.lastError.get() != null) {
	        Throwable t = ExceptionTracker.lastError.get();
	        errorMessage = getStackTraceAsString(t);
	    }

	    // Extract feature name
	    String featureName = scenario.getUri().getPath().replaceAll(".*/", "").replace(".feature", "");
	    ExcelReportUtil.executedFeatures.add(featureName);

	    ExcelReportUtil.TestResult result = new ExcelReportUtil.TestResult(
	            testCaseId, testCaseName, duration, executedBy, status, errorMessage, screenshotPath, featureName
	    );

	    ExcelReportUtil.results.add(result);
	    ExceptionTracker.lastError.remove();
	}

	    private String getStackTraceAsString(Throwable t) {
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        t.printStackTrace(pw);
	        return sw.toString();
	    }

	    // Add this method call where appropriate in your step definitions or hooks
	    public static void captureException(Throwable t) {
	        lastError.set(t);
	    }
	
	public static void takeScreenshot() {

		try {
			myScenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "New");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



