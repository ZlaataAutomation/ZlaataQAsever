package runner;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import org.junit.runner.RunWith;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//@RunWith(Cucumber.class)
//@CucumberOptions(
//    plugin = {"pretty"},
//    features = "@target/rerun.txt",
//    glue = "stepDef"
//)
//
//public class FailedTestRunner {
//	static {
//        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
//        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
//        System.setProperty("webdriver.chrome.logfile", "NUL");
//        System.setProperty("webdriver.chrome.verboseLogging", "false");
//        
//        
//    }
//}




import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	    plugin = {"rerun:target/rerun.txt", "pretty", "listener.CustomTestListener"},
	    features = "@target/rerun.txt",
	    glue = "stepDef"
	)
public class FailedTestRunner {
    static {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
        System.setProperty("webdriver.chrome.logfile", "NUL");
        System.setProperty("webdriver.chrome.verboseLogging", "false");
    }

    public static void writeFailedTestCasesToExcel(String scenarioName, boolean isPassed) {
        Workbook workbook;
        Sheet sheet;
        File file = new File("target/FailedTestCases.xlsx");

        try {
            if (file.exists()) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Failed Test Cases");
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("Failed Test Cases");
                cell = row.createCell(1);
                cell.setCellValue("Re-run Status");
                cell = row.createCell(2);
                cell.setCellValue("Result");
            }

            int rowCounter = -1;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header row
                Cell cell = row.getCell(0);
                if (cell != null && cell.getStringCellValue().equals(scenarioName)) {
                    rowCounter = row.getRowNum();
                    break;
                }
            }

            Row row;
            if (rowCounter == -1) {
                rowCounter = sheet.getLastRowNum() + 1;
                row = sheet.createRow(rowCounter);
            } else {
                row = sheet.getRow(rowCounter);
            }

            // Ensure cells exist
            if (row.getLastCellNum() < 0) {
                Cell cell = row.createCell(0);
                cell.setCellValue(scenarioName);
                cell = row.createCell(1);
                cell.setCellValue("Done");
                cell = row.createCell(2);
                if (isPassed) {
                    cell.setCellValue("Pass");
                } else {
                    cell.setCellValue("Fail");
                }
            } else {
                if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
                    Cell cell = row.createCell(0);
                    cell.setCellValue(scenarioName);
                }
                if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) {
                    Cell cell = row.createCell(1);
                    cell.setCellValue("Done");
                }
                if (row.getCell(2) == null) {
                    Cell cell = row.createCell(2);
                    if (isPassed) {
                        cell.setCellValue("Pass");
                    } else {
                        cell.setCellValue("Fail");
                    }
                } else {
                    Cell cell = row.getCell(2);
                    if (isPassed) {
                        cell.setCellValue("Pass");
                    } else {
                        cell.setCellValue("Fail");
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
