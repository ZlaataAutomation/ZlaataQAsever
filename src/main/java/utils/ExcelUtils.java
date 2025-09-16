package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import manager.FileReaderManager;
import org.apache.poi.ss.usermodel.*;


public class ExcelUtils {

    private static FileInputStream fileInputStream;
    private static XSSFWorkbook xssfWorkbook;
    private static XSSFSheet xssfSheet;
    private static XSSFRow xssfRow;
    private static String cellValue;
    

    public static void setExcel(String path) {
        try {
            fileInputStream = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getTotalRow(String sheetName) {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        int row = xssfSheet.getLastRowNum();
        return row;
    }

    public static int getTotalColumnsCount(String sheetName) {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        xssfRow = xssfSheet.getRow(0);
        int column = xssfRow.getLastCellNum();
        return column;
    }

    public static String getCellData(int rowNum, int colNum, String sheetName) {
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        cellValue = xssfSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        return cellValue;
    }
    
    public static void writeToExcel(LinkedHashMap<String, String> data, boolean... isNeedsToDelete) throws IOException {
    	String tcID = Common.getValueFromHashMap(data, "TC_ID");
		String tdID = Common.getValueFromHashMap(data, "TD_ID");
        String testReportPath = FileReaderManager.getInstance().getConfigReader().getTestReportPath(tcID, tdID);
        FileInputStream fileInputStream = new FileInputStream(testReportPath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        if (isNeedsToDelete.length > 0) {
            removeOtherSheets(xssfWorkbook, tdID);
        }
        XSSFSheet sheet = xssfWorkbook.createSheet(tdID);
        Set<String> keyset = data.keySet();
        int cellNum = 0;
        Row testDataKey = sheet.createRow(0);
        Row testDataVale = sheet.createRow(1);
        for (String key : keyset) {
            testDataKey.createCell(cellNum).setCellValue((String) key);
            testDataVale.createCell(cellNum).setCellValue((String) data.get(key));
            cellNum++;
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(testReportPath));
            xssfWorkbook.write(out);
            out.flush();
            out.close();
            System.out.println(testReportPath + " written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeOtherSheets(XSSFWorkbook xssfWorkbook, String sheetName) {
        for (int i = xssfWorkbook.getNumberOfSheets() - 1; i >= 0; i--) {
            XSSFSheet tmpSheet = xssfWorkbook.getSheetAt(i);
            if (tmpSheet.getSheetName().equals(sheetName)) {
                xssfWorkbook.removeSheetAt(i);
            }
        }
    }

    @SuppressWarnings("removal")
	public static void writeToExcelWorkBook(String tcID, LinkedHashMap<String, String> data, String fileName, int colLen) {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet(tcID);
        Set<String> keyset = data.keySet();
        List<String> list = new ArrayList<>(keyset);
        int valueRowNum = 1;
        int valueCellNum = 0;
        Row testDataKey = sheet.createRow(0);
        for (int i = 0; i <= list.size(); i++) {
            String value = list.get(i).split("\\|")[0];
            try {
                testDataKey.createCell(i).setCellValue(new Double(value));
            } catch (NumberFormatException exception) {
                testDataKey.createCell(i).setCellValue(value);
            }
            if (i == 8) {
                testDataKey.createCell(i).setCellValue(new Double(DateUtils.getCurrentLocalDateTimeStamp("ddMMyyyy")));
                break;
            }
        }
        Row testDataVale = sheet.createRow(valueRowNum);
        for (String key : keyset) {
            if (valueCellNum == 8) {
                valueCellNum = 0;
                testDataVale = sheet.createRow(valueRowNum + 1);
                valueRowNum++;
            }
            testDataVale.createCell(valueCellNum).setCellValue(data.get(key));
            valueCellNum++;
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            xssfWorkbook.write(out);
            out.flush();
            out.close();
            xssfWorkbook.close();
            System.out.println(fileName + " written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateTestResult(String filePath, int row, int column, String result) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
        Sheet sheet = workbook.getSheetAt(0);
        Row resultRow = sheet.getRow(row);
        if (resultRow == null) {
            resultRow = sheet.createRow(row);
        }
        Cell resultCell = resultRow.createCell(column);
        resultCell.setCellValue(result);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }
    public static Map<String, String> getTestCaseRowMap(Row row) {
        Map<String, String> rowMap = new HashMap<>();
        String[] values = getCellValues(row);
        // Assuming the first column is the key
        String key = values[0];
        rowMap.put(key, values[1]);
        return rowMap;
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return "";
        }
   
}
    private static String[] getCellValues(Row row) {
        String[] values = new String[row.getLastCellNum()];
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            values[i] = getCellValue(cell);
        }
        return values;
    }

	

	
   
	
}

