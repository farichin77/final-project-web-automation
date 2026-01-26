package utils;

import java.io.File;

public class DownloadUtil {

    private static final String DOWNLOAD_DIR =
            System.getProperty("user.dir") + File.separator + "Downloads";

    public static void clearExcelFiles() {
        File dir = new File(DOWNLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // Clear Excel files dan temporary download files
        File[] files = dir.listFiles((d, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".xlsx") || 
                   lower.endsWith(".xls") ||
                   lower.endsWith(".crdownload") ||
                   lower.endsWith(".part");
        });

        if (files != null) {
            for (File file : files) {
                if (file.delete()) {
                    System.out.println("[DownloadUtil] Deleted: " + file.getName());
                }
            }
        }
        System.out.println("[DownloadUtil] Download folder cleared. Directory: " + DOWNLOAD_DIR);
    }

    public static boolean waitForExcelFile(int timeoutSeconds) {
        File dir = new File(DOWNLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        long startTime = System.currentTimeMillis();
        File lastFile = null;
        int pollCount = 0;
        boolean foundAtLeastOnce = false;

        System.out.println("[DownloadUtil] Waiting for Excel file (timeout: " + timeoutSeconds + "s)");
        System.out.println("[DownloadUtil] Download directory: " + DOWNLOAD_DIR);

        while (System.currentTimeMillis() < endTime) {
            pollCount++;
            
            File[] allFiles = dir.listFiles();
            printDirectoryContents(allFiles, pollCount);
            
            // Check for final Excel files (not .crdownload or .part)
            File[] excelFiles = dir.listFiles((d, name) -> {
                String lower = name.toLowerCase();
                return (lower.endsWith(".xlsx") || lower.endsWith(".xls")) &&
                       !lower.endsWith(".crdownload") &&
                       !lower.endsWith(".part") &&
                       !lower.startsWith("~");
            });

            // Also check for .part files which are being downloaded (Firefox/Edge)
            File[] partFiles = dir.listFiles((d, name) -> {
                String lower = name.toLowerCase();
                return lower.endsWith(".part") || lower.endsWith(".crdownload");
            });

            if (excelFiles != null && excelFiles.length > 0) {
                foundAtLeastOnce = true;
                // Get the most recent file
                lastFile = excelFiles[0];
                for (File file : excelFiles) {
                    if (file.lastModified() > lastFile.lastModified()) {
                        lastFile = file;
                    }
                }

                System.out.println("[DownloadUtil] Found Excel file: " + lastFile.getName() + 
                                 " (Size: " + lastFile.length() + " bytes)");

                // Tunggu file selesai di-download (size stabil)
                if (isFileDownloadComplete(lastFile)) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    System.out.println("[DownloadUtil] ✓ File download confirmed as complete!");
                    System.out.println("[DownloadUtil] Total wait time: " + elapsedTime + "ms");
                    return true;
                }
            } else if (partFiles != null && partFiles.length > 0) {
                // .part files detected - download in progress
                foundAtLeastOnce = true;
                System.out.println("[DownloadUtil] Download in progress (.part files detected)...");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
        
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (!foundAtLeastOnce) {
            System.out.println("[DownloadUtil] ✗ Timeout! No download activity detected after " + elapsedTime + "ms");
            System.out.println("[DownloadUtil] WARNING: This might indicate the download link didn't trigger a download");
        } else {
            System.out.println("[DownloadUtil] ✗ Timeout! Download was detected but incomplete after " + elapsedTime + "ms");
        }
        return false;
    }

    // Check apakah file sudah selesai didownload
    private static boolean isFileDownloadComplete(File file) {
        if (!file.exists()) {
            System.out.println("[DownloadUtil] File tidak ada lagi: " + file.getName());
            return false;
        }

        // Tunggu sampai file tidak berubah ukuran (sign bahwa download selesai)
        long initialSize = file.length();
        System.out.println("[DownloadUtil] Checking file stability... Initial size: " + initialSize + " bytes");
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}

        long finalSize = file.length();
        System.out.println("[DownloadUtil] Final size: " + finalSize + " bytes");
        
        boolean isComplete = initialSize == finalSize;
        if (isComplete) {
            System.out.println("[DownloadUtil] File is stable (download complete)");
        } else {
            System.out.println("[DownloadUtil] File still changing size, continuing to wait...");
        }
        
        return isComplete;
    }

    // Print semua file di folder untuk debugging
    private static void printDirectoryContents(File[] files, int pollCount) {
        if (files == null || files.length == 0) {
            if (pollCount <= 3) { // Print hanya 3x pertama
                System.out.println("[DownloadUtil Poll #" + pollCount + "] Folder kosong");
            }
            return;
        }

        if (pollCount <= 3) { // Print hanya 3x pertama untuk menghindari spam
            System.out.println("[DownloadUtil Poll #" + pollCount + "] Files in directory (" + files.length + "):");
            for (File file : files) {
                System.out.println("  - " + file.getName() + " (" + file.length() + " bytes, modified: " + 
                                 new java.text.SimpleDateFormat("HH:mm:ss").format(file.lastModified()) + ")");
            }
        }
    }

    public static String getDownloadDir() {
        return DOWNLOAD_DIR;
    }
}
