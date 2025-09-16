package dataProviders;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import enums.DriverType;
import enums.EnvironmentType;
import utils.Common;

public final class ConfigFileReader  {

	private Properties properties;
	private final String propertyFilePath = System.getProperty("user.dir") + File.separator
			+ "src/main/resources/application.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("application.properties not found at " + propertyFilePath);
		}
	}

	public String getTestDataPath(String tcID) {
		String testDataPath = System.getProperty("user.dir") + File.separator + properties.getProperty("testDataPath")
				+ File.separator;
		String module = tcID.split("_")[1];
		switch (module) {
		case "UI":
			testDataPath = testDataPath + "zltUI/ZltTestData.xlsx";
			break;
		case "BP":
			testDataPath = testDataPath + "zltUI/ZlaataTestData.xlsx";
			break;
		case "RT":
			testDataPath = testDataPath + "zltUI/zlaatanew.xlsx";
			break;
		default:
			throw new RuntimeException(
					"Test Data Path not specified in the application.properties file for module" + module);
		}
		return testDataPath;
	}

	public String getTestSummarySheetName(String tcID) {
		String getTestSummaySheetName = null;
		String module = tcID.split("_")[1];
		switch (module) {
		case "UI":
			getTestSummaySheetName = "TestCases-Zlaata";
			break;
		case "BP":
			getTestSummaySheetName = "Testcases-REALTIME";
			break;
		case "RT":
			getTestSummaySheetName = "Testcases-REALTIME";
			break;
		default:
			throw new RuntimeException(
					"Test Data Path not specified in the application.properties file for module" + module);
		}
		return getTestSummaySheetName;
	}



	public String getTestReportPath(String tcID, String tdID) {
		String module = tcID.split("_")[1];
		String testReport = null;
		switch (module) {
		case "UI":
			testReport = System.getProperty("user.dir") + File.separator + properties.getProperty("testUIReportPath");
			Common.createDirIfNotExists(testReport);
			testReport = testReport + "/Zlaata.xlsx";
			break;
		case "BP":
			testReport = System.getProperty("user.dir") + File.separator + properties.getProperty("testBPReportPath");
			testReport = testReport + File.separator + getChannelName(tdID.split("_")[2]);
			Common.createDirIfNotExists(testReport + File.separator + tcID + File.separator + "DB_VERIFICATION");
			testReport = testReport + File.separator + tcID + File.separator + "DB_VERIFICATION" + File.separator + tcID
					+ ".xlsx";
			break;
		case "RT":
			testReport = System.getProperty("user.dir") + File.separator + properties.getProperty("testRTReportPath");
			if (tdID.contains("OTB")) {
				testReport = testReport + File.separator + (tdID.split("_")[2]) + File.separator + tdID.split("_")[3]
						+ File.separator + tdID.split("_")[4] + File.separator;
			} else if (tdID.contains("FAST")) {
				testReport = testReport + File.separator + getChannelName(tdID.split("_")[2]) + File.separator
						+ tdID.split("_")[3] + File.separator;
			} else {
				testReport = testReport + File.separator + getChannelName(tdID.split("_")[2]) + File.separator
						+ tdID.split("_")[3] + File.separator + tdID.split("_")[4] + File.separator;
			}
			Common.createDirIfNotExists(testReport + File.separator + tcID + File.separator + "DB_VERIFICATION");
			testReport = testReport + File.separator + tcID + File.separator + "DB_VERIFICATION" + File.separator + tdID
					+ ".xlsx";
			break;
		default:
			throw new RuntimeException(
					"Test Report Path not specified in the application.properties file for module" + module);
		}
		Common.createWorkbook(testReport, tdID);
		return testReport;
	}

	public static String getChannelName(String channel) {
		String reportLoc = "";
		if (channel.equalsIgnoreCase("ZLT")) {
			reportLoc = "ZLT";
		} else if (channel.equalsIgnoreCase("Zlaata")) {
			reportLoc = "Zlaata";
		}
		return reportLoc;
	}

	public long getImplicitlyWait() {
		Long implicitlyWait = Long.valueOf(properties.getProperty("implicitlyWait"));
		if (Objects.nonNull(implicitlyWait)) {
			return implicitlyWait;
		} else {
			throw new RuntimeException(
					"Implicit Wait Key value in application.properties is not matched : " + implicitlyWait);
		}
	}

	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (Objects.nonNull(url))
			return url;
		else
			throw new RuntimeException(
					"Application Url not specified in the application.properties file for the Key: " + url);
	}
	public String getApplicationAdminUrl() {
		String url = properties.getProperty("Adminurl");
		if (Objects.nonNull(url))
			return url;
		else
			throw new RuntimeException(
					"Application Url not specified in the application.properties file for the Key: " + url);
	}
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if (Objects.isNull(browserName) || browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("edge"))
			return DriverType.EDGE;
		else
			throw new RuntimeException(
					"Browser Name Key value in application.properties is not matched : " + browserName);
	}

	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if (Objects.isNull(environmentName) || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("remote"))
			return EnvironmentType.REMOTE;
		else
			throw new RuntimeException(
					"Environment Type Key value in application.properties is not matched : " + environmentName);
	}

	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if (Objects.nonNull(windowSize))
			return Boolean.valueOf(windowSize);
		return true;
	}

	public Boolean getHeadlessModeOption() {
		String isHeadLessModeEnabled = properties.getProperty("headlessMode");
		if (Objects.nonNull(isHeadLessModeEnabled))
			return Boolean.valueOf(isHeadLessModeEnabled);
		else
			throw new RuntimeException(
					"Headless not specified in the application.properties file for the Key:headlessMode");
	}

	public boolean isSendReportThroughMail() {
		boolean isValue = false;
		String sendReportThroughMail = properties.getProperty("sendReportThroughMail");
		if (Objects.nonNull(sendReportThroughMail)) {
			if (sendReportThroughMail.equalsIgnoreCase("Yes")) {
				isValue = true;
			}
		} else
			throw new RuntimeException(
					"Generated Date file Location not specified in the application.properties file for the Key:sendReportThroughMail");
		return isValue;
	}

	public boolean isBPReportReq() {
		String isBPReportRequired = properties.getProperty("isBPReportRequired");
		if (Objects.nonNull(isBPReportRequired))
			return Boolean.valueOf(isBPReportRequired);
		else
			throw new RuntimeException(
					"SFTP Remote File transfer Location not specified in the application.properties file for the Key:sftpRemoteFileLoc");
	}

	public boolean isScreenShotRequiredForAllScenario() {
		String isScreenShotRequired = properties.getProperty("screenshot.allScenarios");
		if (Objects.nonNull(isScreenShotRequired))
			return Boolean.valueOf(isScreenShotRequired);
		else
			throw new RuntimeException(
					"SFTP Remote File transfer Location not specified in the application.properties file for the Key:isScreenShotRequired");
	}
	public String getApplicationsaleUrl() {
		String url = properties.getProperty("saleUrl");
		if (Objects.nonNull(url))
			return url;
		else
			throw new RuntimeException(
					"Application Url not specified in the application.properties file for the Key: " + url);
	}
	
}
