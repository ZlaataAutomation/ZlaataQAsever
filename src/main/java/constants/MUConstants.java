package constants;

import java.io.File;
import java.util.Random;

import utils.Common;
import utils.DateUtils;

public final class MUConstants {

	private static final String MU_GEN_FILE_LOC = System.getProperty("user.dir") + File.separator + "target/ZlaataUI/MANUALUPLOAD"+ File.separator;

	public static String getMuGenFileLoc() {
		Common.createDirIfNotExists(MU_GEN_FILE_LOC);
		return MU_GEN_FILE_LOC;
	}

	public static String getMUFileName() {
		Random rnd = new Random();
		int n = 66666 + rnd.nextInt(99999);
		return DateUtils.getCurrentLocalDateTimeStamp("YYYYMMdd") + "8" + n + ".csv";
//		return DateUtils.addOrMinusDays("YYYYMMdd",1) + "8" + n + ".csv";
	}

}