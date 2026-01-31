package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String testName
    ) {
        try {
            byte[] imageBytes = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            String relativePath = "screenshots/" + testName + ".png";
            File dest = new File("reports/" + relativePath);

            dest.getParentFile().mkdirs();

            Files.write(dest.toPath(), imageBytes);

            return relativePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

