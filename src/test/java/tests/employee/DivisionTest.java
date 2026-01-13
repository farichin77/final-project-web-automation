package tests.employee;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import core.DriverManager;
import pages.employee.DivisionPage;
import utils.DownloadUtil;
import utils.ExcelDataProvider;

import java.io.File;

public class DivisionTest extends BaseTest {

    @Test(priority = 1)
    public void verifySwitchingDivisionTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        divisionPage.clickDivisionTab();

        Assert.assertEquals(divisionPage.getManageDivisionText(),
                "Manage Division",
                "Division tab not displayed");

    }

    @Test(priority = 2,
            dataProvider = "divisionData",
            dataProviderClass = ExcelDataProvider.class
    )
    public void verifyAddDivisionTest(String divisionName, String divisionDesc) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        divisionPage.clickDivisionTab();
        divisionPage.clickAddDivisionButton();
        divisionPage.enterDivisionName(divisionName);
        divisionPage.enterDivisionDesc(divisionDesc);
        divisionPage.clickSaveDivisionButton();

        Assert.assertEquals(divisionPage.getMessageText(),
                "Success create division",
                "Failed to add division");
    }

    @Test(priority = 3,
            dataProvider = "editDivision",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editDivisionValidTest(String divisionName, String divisionDesc) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        divisionPage.clickDivisionTab();
        divisionPage.searchDivision("Business");
        divisionPage.clickDetailBusinessButton();
        divisionPage.clickEditDivisionButton();
        divisionPage.enterDivisionName(divisionName);
        divisionPage.enterDivisionDesc(divisionDesc);
        divisionPage.clickSaveEditDivisionButton();

        Assert.assertEquals(divisionPage.getMessageEditText(),
                "Success update division",
                "Failed to edit division");


    }

    @Test(priority = 4)
    public void verifyDeleteDivisionTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        divisionPage.clickDivisionTab();
        divisionPage.searchDivision("Business");
        divisionPage.clickDetailBusinessButton();
        divisionPage.clickEditDivisionButton();
        divisionPage.clickDeleteDivisionButton();
        divisionPage.clickConfirmDeleteDivisionButton();

        Assert.assertEquals(divisionPage.getMessageDeleteText(),
                "Success delete division",
                "Failed to delete division");
    }

    @Test
    public void verifyExportCsvDivisionTest() throws InterruptedException {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        divisionPage.clickDivisionTab();
        divisionPage.searchDivision("Business");
        divisionPage.clickDetailBusinessButton();
        divisionPage.clickExportCsvButton();

        DownloadUtil.clearExcelFiles();

        boolean isDownloaded = DownloadUtil.waitForExcelFile(30);

        Assert.assertTrue(
                isDownloaded,
                "File Excel tidak ditemukan di " + DownloadUtil.getDownloadDir()
        );

    }
}