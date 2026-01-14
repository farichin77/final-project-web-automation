package base;

import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import utils.ConfigReader;

public class BaseTest {

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        // Inisialisasi driver
        DriverManager.initDriver(browser);
        DriverManager.getDriver().get(ConfigReader.get("BASE_URL"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    // ===============================
    // Protected login method
    // ===============================
    protected void loginValid() {
        DriverManager.getDriver().get(ConfigReader.get("BASE_URL"));

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername(ConfigReader.get("LOGIN_EMAIL"));
        loginPage.enterPassword(ConfigReader.get("LOGIN_PASSWORD"));
        loginPage.clickLogin();

    }
}
