package tests.training;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.training.AssignedEmployeeTrainingPage;
import pages.training.ManageTrainingPage;
public class AssignedEmployeeTest extends BaseTest {

    @Test(priority =1)
    public void assignedEmployeeToTrainingTest(){
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.clickButtonCheck();
        assignedEmployeeTrainingPage.setStartDate("2026-01-01");
        assignedEmployeeTrainingPage.setDeadlineDate("2026-02-02");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.getSuccessMessageAssignEmployee(),
                "Success assign employee",
                "doesnt assign employee");

    }
    @Test(priority =2)
    public void verifyStartdatelaterThanDealineDateTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.clickButtonCheck();
        assignedEmployeeTrainingPage.setStartDate("2026-01-01");
        assignedEmployeeTrainingPage.setDeadlineDate("2025-01-01");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.getInvalidDateMessage(),
                "The end date must be later than the start date.",
                "success input date");
    }
    @Test(priority=3)
    public void verifyAssignEmployeeWithoutSelectEmployeeTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.setStartDate("2026-01-01");
        assignedEmployeeTrainingPage.setDeadlineDate("2026-02-02");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.GetMassageSeleectEmployee(),
                "Please select employee",
                "Success Select employee");
    }

    @Test(priority=4)
    public void verifyCancelEmployeeTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.setStartDate("2026-01-01");
        assignedEmployeeTrainingPage.setDeadlineDate("2026-02-02");
        assignedEmployeeTrainingPage.clickCancel();

        Assert.assertEquals(assignedEmployeeTrainingPage.getAssignedEmployeeListText(),
                "Assigned Employee");
    }
    @Test(priority= 5)
    public void editDetailAssignedEmployeeValidData(){

        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickDetailAssignedEmployee();
        assignedEmployeeTrainingPage.clickEditDetailAssigned();
        assignedEmployeeTrainingPage.editDeadlineDate("2026-05-05");
        assignedEmployeeTrainingPage.clickSaveUserProgram();

        Assert.assertEquals(assignedEmployeeTrainingPage.getMessageUpdateAssignedEmployee(),
                "Success update assigned employee",
                "failed edit assigned employee"
        );

    }
    @Test (priority= 6)
    public void deleteAssignedEmployeeValidData(){
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        assignedEmployeeTrainingPage.clickDetailAssignedEmployee();
        assignedEmployeeTrainingPage.clickDeleteUserProgram();
        assignedEmployeeTrainingPage.buttonDeleteConfirm();

        Assert.assertEquals(assignedEmployeeTrainingPage.getMessageSuccessDeleteAssignedEmployee(),
                "Success delete Assigned Employee",
                "failed delete employee program"
        );
    }
}
