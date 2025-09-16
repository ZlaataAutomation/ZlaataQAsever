 package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
 public class ExcelReportUtils {

     private static Path excelPath;
     private static Workbook workbook;
     private static CreationHelper helper;

     /**
      * Writes a test result into the Excel file.
      *
      * @param feature        Feature name.
      * @param scenario       Scenario name.
      * @param steps          List of test steps.
      * @param status         Test status (PASS, FAIL, SKIP, etc.).
      * @param screenshotPath Path to the screenshot image (optional).
      * @param reason         Reason for failure or additional notes.
      * @throws IOException if an I/O error occurs.
      */
     public static synchronized void writeTestResult(
             String feature,
             String scenario,
             List<String> steps,
             String status,
             String screenshotPath,
             String reason
     ) throws IOException {

         if (excelPath == null) {
             excelPath = Paths.get("reports/Result.xlsx");
         }

         if (workbook == null) {
             if (Files.exists(excelPath)) {
                 try (InputStream in = Files.newInputStream(excelPath)) {
                     workbook = new XSSFWorkbook(in);
                 }
             } else {
                 workbook = new XSSFWorkbook();
             }
             helper = workbook.getCreationHelper();
         }

         String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         Sheet sheet = workbook.getSheet(today);
         if (sheet == null) {
             sheet = workbook.createSheet(today);
             writeHeader(sheet);
         }

         int rowNum = sheet.getLastRowNum() + 1;
         Row row = sheet.createRow(rowNum);

         boolean hasScreenshot = screenshotPath != null && !screenshotPath.isEmpty() && Files.exists(Paths.get(screenshotPath));
         row.setHeightInPoints(hasScreenshot ? 80f : 25f);
         row.createCell(0).setCellValue(rowNum);
         row.createCell(1).setCellValue(feature);
         row.createCell(2).setCellValue(scenario);

         if (steps != null && !steps.isEmpty()) {
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < steps.size(); i++) {
                 sb.append(i + 1).append(". ").append(steps.get(i));
                 if (i < steps.size() - 1) sb.append(" ");
             }
             Cell stepsCell = row.createCell(3);
             stepsCell.setCellValue(sb.toString());

             CellStyle wrap = workbook.createCellStyle();
             wrap.setWrapText(true);
             stepsCell.setCellStyle(wrap);
             sheet.setColumnWidth(3, 50 * 256);
         }

         Cell statusCell = row.createCell(4);
         statusCell.setCellValue(status);

         CellStyle statusStyle = workbook.createCellStyle();
         statusStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         switch (status.toUpperCase()) {
             case "PASS":
                 statusStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                 break;
             case "PASSED":
                 statusStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                 break;
             case "FAIL":
                 statusStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                 break;
             case "FAILED":
                 statusStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                 break;
             case "SKIP":
                 statusStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                 break;
             case "SKIPED":
                 statusStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                 break;
             default:
                 statusStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
         }
         statusCell.setCellStyle(statusStyle);
         sheet.setColumnWidth(4, 15 * 256);

         if (hasScreenshot) {
             try (InputStream is = Files.newInputStream(Paths.get(screenshotPath))) {
                 byte[] bytes = IOUtils.toByteArray(is);
                 int picIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                 Drawing<?> drawing = sheet.createDrawingPatriarch();
                 ClientAnchor anchor = helper.createClientAnchor();
                 anchor.setCol1(5);
                 anchor.setRow1(rowNum);
                 anchor.setCol2(6);
                 anchor.setRow2(rowNum + 1);
                 anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
                 Picture pict = drawing.createPicture(anchor, picIdx);
                 pict.resize(1.0);
             }
             sheet.setColumnWidth(5, 30 * 256);
         }

         for (int c = 0; c <= 3; c++) {
             sheet.autoSizeColumn(c);
         }

         row.createCell(5).setCellValue(reason);

         try (OutputStream out = Files.newOutputStream(
                 excelPath,
                 StandardOpenOption.CREATE,
                 StandardOpenOption.TRUNCATE_EXISTING)) {
             workbook.write(out);
         }
     }

     private static void writeHeader(Sheet sheet) {
         Row header = sheet.createRow(0);
         String[] columns = { "S.NO", "Feature", "Scenario", "Steps", "Scenario Status", "Screenshot", "Reason" };

         CellStyle boldStyle = workbook.createCellStyle();
         Font boldFont = workbook.createFont();
         boldFont.setBold(true);
         boldStyle.setFont(boldFont);

         for (int i = 0; i < columns.length; i++) {
             Cell cell = header.createCell(i);
             cell.setCellValue(columns[i]);
             cell.setCellStyle(boldStyle);
             sheet.autoSizeColumn(i);
         }
     }
 }
