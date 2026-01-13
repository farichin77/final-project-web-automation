package utils;

import java.io.File;

public class DownloadUtil {

    private static final String DOWNLOAD_DIR =
            System.getProperty("user.dir") + File.separator + "Downloads";

    public static void clearExcelFiles() {
        File dir = new File(DOWNLOAD_DIR);
        File[] files = dir.listFiles((d, name) ->
                name.toLowerCase().endsWith(".xlsx")
        );

        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static boolean waitForExcelFile(int timeoutSeconds) {
        File dir = new File(DOWNLOAD_DIR);
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {
            File[] files = dir.listFiles((d, name) ->
                    name.toLowerCase().endsWith(".xlsx")
            );

            if (files != null && files.length > 0) {
                long diff = System.currentTimeMillis() - files[0].lastModified();
                if (diff < timeoutSeconds * 1000L) {
                    return true;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
        return false;
    }

    public static String getDownloadDir() {
        return DOWNLOAD_DIR;
    }
}
