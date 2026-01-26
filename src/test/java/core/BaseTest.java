package core;

import org.testng.annotations.*;
import pages.login.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected String browserName;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("") String browser) {
        // Browser Resolution Priority:
        // 1. Explicit XML Parameter (from testng.xml)
        // 2. System Property (passed via CLI -Pbrowser=xxx or IDE VM options)
        // 3. Fallback to Chrome
        
        String sysBrowser = System.getProperty("browser");
        
        if (browser == null || browser.isEmpty()) {
            if (sysBrowser != null && !sysBrowser.isEmpty()) {
                browser = sysBrowser;
            } else {
                browser = "chrome";
            }
        }

        this.browserName = browser;
        DriverManager.initDriver(browser);
        DriverManager.getDriver().get(ConfigReader.get("BASE_URL"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected void loginValid() {
        DriverManager.getDriver().get(ConfigReader.get("BASE_URL"));

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername(ConfigReader.get("LOGIN_EMAIL"));
        loginPage.enterPassword(ConfigReader.get("LOGIN_PASSWORD"));
        loginPage.clickLogin();

    }
}
