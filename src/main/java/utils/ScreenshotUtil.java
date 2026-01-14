package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String testName
    ) {
        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String relativePath = "screenshots/" + testName + ".png";
            File dest = new File("reports/" + relativePath);

            dest.getParentFile().mkdirs();

            Files.copy(
                    src.toPath(),
                    dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return relativePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

