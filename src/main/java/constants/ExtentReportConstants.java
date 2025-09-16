package constants;

import java.io.File;

public final class ExtentReportConstants {

    private static String EXTENT_RPT_CONFIG = System.getProperty("user.dir") + File.separator + "src/main/resources/REPORT/extent-config.xml";

    public static String getExtentRptConfig(){
        return EXTENT_RPT_CONFIG;
    }
}
