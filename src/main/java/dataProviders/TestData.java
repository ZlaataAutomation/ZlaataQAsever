package dataProviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vimalselvam.cucumber.listener.Reporter;
import manager.FileReaderManager;
import utils.ExcelUtils;

public final class TestData extends TestDataValueGenerator {

	private final String testDataPath;
	private final String testDataSummarySheetName;

	public TestData(String tcID) {
		testDataPath = FileReaderManager.getInstance().getConfigReader().getTestDataPath(tcID);
		testDataSummarySheetName = FileReaderManager.getInstance().getConfigReader().getTestSummarySheetName(tcID);
		ExcelUtils.setExcel(testDataPath);
	}

	@SuppressWarnings("resource")
	public Map<String, String> getTestData(String tcID, String tdID, String scenarioToRun) {
		LinkedHashMap<String, String> testDataMap = null;
		String scenarioName;
		int totalData;
		String dealData;
		String eScenario;
		String etcID;
		String testDataID;
		String executionFlag;
		scenarioName = scenarioToRun;
		for (int scenario = 1; scenario <= ExcelUtils.getTotalRow(testDataSummarySheetName); scenario++) {
			eScenario = ExcelUtils.getCellData(scenario, 3, testDataSummarySheetName);
			{
				if (scenarioName.contains(eScenario)) {
					etcID = ExcelUtils.getCellData(scenario, 2, testDataSummarySheetName);
					if (etcID.equalsIgnoreCase(tcID)) {
						testDataID = ExcelUtils.getCellData(scenario, 5, testDataSummarySheetName);
						executionFlag = ExcelUtils.getCellData(scenario, 1, testDataSummarySheetName);
						if (executionFlag.equalsIgnoreCase("Y")) {
							totalData = ExcelUtils
									.getTotalRow(ExcelUtils.getCellData(scenario, 4, testDataSummarySheetName));
							if (!(totalData == 0)) {
								for (int data = 1; data <= totalData; data++) {
									dealData = ExcelUtils.getCellData(data, 0,
											ExcelUtils.getCellData(scenario, 4, testDataSummarySheetName));
									Pattern pattern = Pattern.compile("[^|]");
									Matcher matcher = pattern.matcher(testDataID);
									boolean isStringContainsSpecialCharacter = matcher.find();
									if (isStringContainsSpecialCharacter)
										testDataID = getRTTestDataID(tdID, testDataID);
									if (tdID.equalsIgnoreCase(dealData) && testDataID.contains(tdID)) {
										InputStream fis;
										XSSFWorkbook xssfWorkbook;
										testDataMap = new LinkedHashMap<>();
										testDataMap.put("TC_ID", tcID);
										testDataMap.put("TD_ID", tdID);
										try {
											fis = new FileInputStream(testDataPath);
											xssfWorkbook = new XSSFWorkbook(fis);
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
										XSSFSheet xssfSheet;
										xssfSheet = xssfWorkbook.getSheet(
												ExcelUtils.getCellData(scenario, 4, testDataSummarySheetName));
										int columns = xssfSheet.getRow(0).getLastCellNum();
										for (int i = 0; i <= columns - 1; i++) {
											String key = xssfSheet.getRow(0).getCell(i).getStringCellValue().trim();
											key = key.split("\\|")[0];
											String value = xssfSheet.getRow(data).getCell(i).getStringCellValue()
													.trim();
											if (value.equalsIgnoreCase("<Empty>") || value.equalsIgnoreCase("<NA>")) {
												continue;
											}
											if (isReqRunTimeValueGen(value)) {
												testDataMap.put(key, generateValueForExcelKey(value.trim()));
											} else {
												testDataMap.put(key, value);
											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
//		Reporter.addScenarioLog("Test Data ID :: " + testDataMap.get("TestData_ID"));
		try {
			boolean value = true;
			ExcelUtils.writeToExcel(testDataMap, value);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return testDataMap;
	}

	public boolean isReqRunTimeValueGen(String value) {
		
		return false;
	}

	public String getCellValue(XSSFCell xssfCell) {
		switch (xssfCell.getCellType()) {
		case NUMERIC:
			return String.valueOf(xssfCell.getNumericCellValue()).trim();
		case BOOLEAN:
			return String.valueOf(xssfCell.getBooleanCellValue()).trim();
		default:
			return xssfCell.getStringCellValue().trim();
		}
	}

	public static String getRTTestDataID(String tcIDFromFeature, String testDataToBeExecuted) {
		if (tcIDFromFeature.contains(testDataToBeExecuted.split("\\|")[0])) {
			return tcIDFromFeature;
		} else {
			throw new RuntimeException("Incorrect Test Data Passed");
		}
	}

	public boolean isManualUploadTestCase(String value) {
		return false;

//		List<String> list = List.of(values);
//		return list.contains(value);
	}

}
