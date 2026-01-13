package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        WebDriver webDriver;

        // Mendapatkan path folder project saat ini secara dinamis
        String projectPath = System.getProperty("user.dir");

        // Menentukan folder 'Downloads' di dalam project
        String downloadPath = projectPath + File.separator + "Downloads";

        // Cek jika folder 'Downloads' belum ada, maka buat foldernya
        File directory = new File(downloadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addPreference("browser.download.dir", downloadPath);
                ffOptions.addPreference("browser.download.folderList", 2);
                ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/octet-stream,application/csv");
                webDriver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", projectPath + File.separator + "Driver" + File.separator + "msedgedriver.exe");
                EdgeOptions edgeOptions = new EdgeOptions();
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("download.default_directory", downloadPath);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                webDriver = new EdgeDriver(edgeOptions);
                break;

            default: // Chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("download.default_directory", downloadPath);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                webDriver = new ChromeDriver(chromeOptions);
                break;
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}