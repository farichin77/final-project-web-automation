package core;

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

        String projectPath = System.getProperty("user.dir");
        String downloadPath = projectPath + File.separator + "Downloads";

        File directory = new File(downloadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Cek apakah berjalan di GitHub Actions
        boolean isCI = System.getenv("GITHUB_ACTIONS") != null;
        String os = System.getProperty("os.name").toLowerCase();

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                String profilePath = projectPath + File.separator + "firefox_profile";
                File profileDir = new File(profilePath);
                if (!profileDir.exists()) {
                    profileDir.mkdirs();
                }
                
                // Set all critical Firefox preferences untuk auto-download
                ffOptions.addPreference("browser.download.dir", downloadPath);
                ffOptions.addPreference("browser.download.folderList", 2);  // 0=Desktop, 1=Downloads, 2=Custom
                ffOptions.addPreference("browser.download.manager.showWhenStarting", false);
                ffOptions.addPreference("browser.download.manager.focusWhenStarting", false);
                ffOptions.addPreference("browser.download.useDownloadDir", true);
                ffOptions.addPreference("browser.download.always_ask_before_handling_user_defined_default_types", false);
                
                // CRITICAL: Prevent download manager from closing or interfering
                ffOptions.addPreference("browser.download.panel.shown", true);
                ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", 
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet," +
                    "application/vnd.ms-excel," +
                    "application/msword," +
                    "application/pdf," +
                    "application/x-msexcel," +
                    "application/excel");
                
                // Disable PDF viewer so it downloads instead
                ffOptions.addPreference("pdfjs.disabled", true);
                ffOptions.addPreference("browser.download.forbid_open_with", true);
                
                // Prevent navigation from interrupting downloads
                ffOptions.addPreference("browser.download.dont_prompt_on_exit", true);

                if (isCI) {
                    ffOptions.addArguments("--headless");
                    ffOptions.addArguments("--width=1920");
                    ffOptions.addArguments("--height=1080");
                }

                webDriver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                if (!isCI) {
                    // DI LOKAL: Pakai path manual atau let system find it (di sini pakai path kamu)
                    if (os.contains("win")) {
                        System.setProperty("webdriver.edge.driver", projectPath + File.separator + "Driver" + File.separator + "msedgedriver.exe");
                    } else {
                        System.setProperty("webdriver.edge.driver", "/usr/bin/msedgedriver");
                    }
                }

                EdgeOptions edgeOptions = new EdgeOptions();
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("download.default_directory", downloadPath);
                edgePrefs.put("download.prompt_for_download", false);
                edgePrefs.put("download.directory_upgrade", true);
                edgePrefs.put("profile.default_content_settings.popups", 0);
                edgePrefs.put("profile.managed_default_content_settings.popups", 0);
                edgePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                
                // CRITICAL: Force Edge to download Office/PDF files instead of opening them
                edgePrefs.put("edge_office_viewer.enabled", false);
                edgePrefs.put("plugins.always_open_pdf_externally", true);
                edgePrefs.put("download.open_pdf_in_system_viewer", false);
                edgePrefs.put("download.extensions_to_open", "");
                
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                
                // Disable notifications and bypass SmartScreen/SafeBrowsing/OfficeViewer prompts
                edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.addArguments("--disable-popup-blocking");
                edgeOptions.addArguments("--disable-features=msEdgeOfficeViewer,EdgeSafeBrowsingDownloadPrompt,msSmartScreenProtection");
                edgeOptions.addArguments("--safebrowsing-disable-download-protection");

                if (isCI) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }

                webDriver = new EdgeDriver(edgeOptions);
                break;

            default: // Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("download.default_directory", downloadPath);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                if (isCI) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                webDriver = new ChromeDriver(chromeOptions);
                break;
        }

        // Maximize hanya jika tidak headless (karena headless tidak punya window fisik)
        if (!isCI) {
            webDriver.manage().window().maximize();
        }

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