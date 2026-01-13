package tests.login;

import base.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginValidCredentialTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        Assert.assertEquals(dashboardPage.getDashboardText(),
                "Dashboard"
        );
    }
    @Test
    public void loginInvalidEmailTest() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername("wrong123@gmail.com");
        loginPage.enterPassword(ConfigReader.get("LOGIN_PASSWORD"));
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message is not displayed"
        );
    }

    @Test
    public void loginInvalidPasswordTest() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername(ConfigReader.get("LOGIN_EMAIL"));
        loginPage.enterPassword("wrongPassword123");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message is not displayed"
        );
    }
    @Test
    public void invalidEmptyEmailAndPasswordTest() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message is not displayed"
        );
    }
}

