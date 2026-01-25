package core;

import org.testng.annotations.*;
import pages.login.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected String browserName;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
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
