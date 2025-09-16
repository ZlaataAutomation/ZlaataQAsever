package constants;



public final class ZlaataUIConstants {
	 private ZlaataUIConstants() {
	    }
	
	    public static final String REPORTS_CUCUMBER_LOCATION;
	    public static final String REPORTS_CUCUMBER_LOCAL;
	    public static final String REPORTS_ZIPPED_FILE_NAME;
	    public static final String PROJECT_NAME;
	    public static final String SUBJECTS;
	   

	    static {
	    	SUBJECTS = "Zlaata Excel Automation Report";
	        PROJECT_NAME = "ZLAATA";
//	        REPORTS_CUCUMBER_LOCATION = "C:\\Users\\Ranjith\\Desktop\\ZlaataProduction\\reports\\Extent-Report\\Zlaata-QAResults.html";
	        REPORTS_CUCUMBER_LOCATION ="C:\\Users\\Ranjith\\Downloads\\ZlaataProductionPGR (3)\\ZlaataProductionPGR\\reports\\Automation_Report.xlsx";
	        REPORTS_CUCUMBER_LOCAL = REPORTS_CUCUMBER_LOCATION + "report.html";
	        REPORTS_ZIPPED_FILE_NAME = "./target/ZlaataAutomation.zip";
	       
	    }
}