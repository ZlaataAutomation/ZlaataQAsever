package stepDef;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            //ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Ranjith\\Desktop\\Zlaata PRoject\\ZlaataUpdated\\reports\\Extent-Report\\Zlaata-QAResults.html");
        	ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        	
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void removeTest() {
        test.remove();
    }

}
