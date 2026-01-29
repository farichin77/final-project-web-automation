package core;

import org.testng.annotations.*;
import pages.login.LoginPage;
import utils.ConfigReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BaseTest {

    protected String browserName;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("") String browser){
        
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

    protected String generateUniqueEmployeeId() {
        // Format: EMP + timestamp + random number
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        Random random = new Random();
        int randomNumber = random.nextInt(1000); // 0-999
        return "EMP" + timestamp + String.format("%03d", randomNumber);
    }

    protected String generateUniqueEmail(String baseEmail) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        int randomNum = (int) (Math.random() * 1000);
        return "test" + timestamp + randomNum + "@example.com";
    }
}
