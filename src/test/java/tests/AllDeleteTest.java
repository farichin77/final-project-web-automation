package tests;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.employee.DetailEmployeePage;
import pages.employee.DivisionPage;
import pages.employee.EmployeeListPage;
import pages.training.ContentPage;
import pages.training.ManageTrainingPage;
import pages.training.ProgramListTrainingPage;
import pages.training.UpdateContentPage;

public class AllDeleteTest extends BaseTest {

    @Test(priority=1)
    public void DeleteContentFromChapterTest(){
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickDetailTestButton();

        UpdateContentPage updateContentPage = new UpdateContentPage(DriverManager.getDriver());
        updateContentPage.clickEditContentButton();
        updateContentPage.clickDeleteButton();
        updateContentPage.clickConfirmDelete();

        Assert.assertEquals(updateContentPage.getSuccessDeleteContentMessage(),
                "Success delete content",
                "Delete content failed");

    }
    @Test (priority = 2)
    public void verifyDeleteEmployeeSuccess() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama");
        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickDeleteEmployee();
        detailEmployeePage.clickConfirmDeleteButton();

        // Verifikasi bahwa karyawan telah dihapus
        Assert.assertEquals(
                detailEmployeePage.getSuccessDeleteEmployeeText(),
                "Succes delete employee",
                "Failed to delete employee"
        );
    }
    @Test (priority = 3)
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



}
