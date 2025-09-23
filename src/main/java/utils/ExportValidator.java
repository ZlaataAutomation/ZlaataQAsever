package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ExportValidator {
	
	public File waitForDownload(String downloadDir, String fileName, int timeoutSeconds) {
	    File dir = new File(downloadDir);
	    if (!dir.exists()) {
	        throw new RuntimeException("❌ Download dir not found: " + downloadDir);
	    }

	    long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx"));
	        if (files != null && files.length > 0) {
	            // ✅ Take the latest file
	            File latestFile = Arrays.stream(files)
	                    .max(Comparator.comparingLong(File::lastModified))
	                    .orElse(null);

	            if (latestFile != null && latestFile.exists()) {
	                // ✅ Check if still downloading (.crdownload/.tmp present)
	                File partial1 = new File(latestFile.getAbsolutePath() + ".crdownload");
	                File partial2 = new File(latestFile.getAbsolutePath() + ".tmp");

	                if (partial1.exists() || partial2.exists()) {
	                    // Still downloading → wait more
	                    try {
	                        Thread.sleep(1000);
	                    } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                    }
	                    continue;
	                }

	                // ✅ Fully downloaded → rename/move
	                File targetFile = new File(dir, fileName);

	                // Delete old target if exists
	                if (targetFile.exists()) {
	                    targetFile.delete();
	                }

	                if (latestFile.getName().equals(fileName)) {
	                    // Already correct name
	                    return latestFile;
	                } else {
	                    boolean renamed = latestFile.renameTo(targetFile);
	                    if (!renamed) {
	                        throw new RuntimeException("❌ Could not rename " 
	                                + latestFile.getName() + " → " + fileName);
	                    }
	                    return targetFile;
	                }
	            }
	        }

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    throw new RuntimeException("❌ No Excel file was downloaded within timeout!");
	}
}
