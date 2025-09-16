package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.MUConstants;
import manager.FileReaderManager;
import utils.Common;
import utils.CsvWriterSimple;
import utils.ExcelUtils;

public final class ManualUploadTestData extends TestDataValueGenerator {

	private String testDataPath;
	private int headerRow;
	private int tranDataLastRow;
	private InputStream fis;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;

	public ManualUploadTestData(String tcID) throws IOException {
		testDataPath = FileReaderManager.getInstance().getConfigReader().getTestDataPath(tcID);
		ExcelUtils.setExcel(testDataPath);
		fis = new FileInputStream(testDataPath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("PI-PaymentUpload-File");
		headerRow = Integer.parseInt(Common.getValueFromTestDataMap("PI-PaymentUpload-File - Header"));
		tranDataLastRow = Integer
				.parseInt(Common.getValueFromTestDataMap("PI-PaymentUpload-File -Transaction Data End Row"));
	}

	public String getTransactionDataRowDetails() {
		String fileNamePrefix = Common.getValueFromTestDataMap("File_Name Prefix");
		ArrayList<String[]> String = new ArrayList<>();
		for (int i = headerRow; i <= tranDataLastRow - 1; i++) {
			row = sheet.getRow(i);
			ArrayList<String> transData = new ArrayList<>();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				final Cell cell = row.getCell(j);
				String value = cell.getRichStringCellValue().getString();
				if (value.equalsIgnoreCase("<Empty>") || value.equalsIgnoreCase("<NA>")) {
					continue;
				} else if (isReqRunTimeValueGen(value)) {
					transData.add(generateValueForExcelKey(value.trim()));
				} else {
					transData.add(value.replaceAll("^\"|\"$", ""));
				}
			}
//			String[] strings = transData.toArray(String[]::new);
//			String.add(strings);
		}
		CsvWriterSimple writer = new CsvWriterSimple();
		String fileName = MUConstants.getMuGenFileLoc() + fileNamePrefix + MUConstants.getMUFileName();
		try {
			writer.writeToCsvFile(String, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	private boolean isReqRunTimeValueGen(String value) {
		
		return false;
	}

}
