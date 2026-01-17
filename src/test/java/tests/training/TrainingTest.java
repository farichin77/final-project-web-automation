package tests.training;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.training.*;
import utils.ExcelDataProvider;
import core.DriverManager;

public class TrainingTest extends BaseTest {
    @Test(  priority = 1,
            dataProvider = "addTraining",
            dataProviderClass = ExcelDataProvider.class
    )
    public void AddTrainingTest(String trainingName, String description) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.clickAddTrainingButton();
        trainingPage.enterTrainingName(trainingName);
        trainingPage.enterDescription(description);
        trainingPage.clickSubmitButton();

        Assert.assertEquals(trainingPage.getSuccessCreateTrainingMessage(),
                "Success create program",
                "Success create program message should be displayed");

    }

    @Test(  priority = 2,
            dataProvider = "editTraining",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editTrainingTest(String trainingName, String description) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.updateTrainingButton();
        programListTrainingPage.updateTrainingNameField(trainingName);
        programListTrainingPage.updateTrainingDescriptionField(description);
        programListTrainingPage.submitUpdateTraining();

        Assert.assertEquals(programListTrainingPage.getSuccessUpdateTrainingMessage(),
                "Success update program",
                "Success update program message should be displayed");
    }

    @Test(  priority = 3,
            dataProvider = "addChapter",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addChapterTest(String chapterName, String chapterDescription) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickAddChapterIcon();
        programListTrainingPage.clickChapterNameField(chapterName);
        programListTrainingPage.clickChapterDescriptionField(chapterDescription);
        programListTrainingPage.submitAddChapter();

        Assert.assertEquals(programListTrainingPage.getSuccessAddChapterMessage(),
                "Success create chapter",
                "Success add chapter message should be displayed");
    }
    @Test(  priority = 4,
            dataProvider = "editChapter",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editChapterTest(String chapterName, String chapterDescription) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();
        programListTrainingPage.clickEditChapterButton();
        programListTrainingPage.clickChapterNameField(chapterName);
        programListTrainingPage.clickChapterDescriptionField(chapterDescription);
        programListTrainingPage.saveEditChapter();

        Assert.assertEquals(programListTrainingPage.getSuccessUpdateChapterMessage(),
                "Success update chapter",
                "Success update chapter message should be displayed");

}

    @Test (priority =5)
    public void assignedEmployeeToTrainingTest(){
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.clickButtonCheck();
        assignedEmployeeTrainingPage.setStartDate("01012026");
        assignedEmployeeTrainingPage.setDeadlineDate("02022026");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.getSuccessMessageAssignEmployee(),
                "Success assign employee",
                "doesnt assign employee");

    }
    @Test(priority =6)
    public void verifyStartdatelaterThanDealineDateTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.clickButtonCheck();
        assignedEmployeeTrainingPage.setStartDate("01012026");
        assignedEmployeeTrainingPage.setDeadlineDate("02022025");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.getInvalidDateMessage(),
                "The end date must be later than the start date.",
                "success input date");
    }
    @Test(priority=7)
    public void verifyAssignEmployeeWithoutSelectEmployeeTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.setStartDate("01012026");
        assignedEmployeeTrainingPage.setDeadlineDate("02022026");
        assignedEmployeeTrainingPage.clickSaveAssignEmployee();

        Assert.assertEquals(assignedEmployeeTrainingPage.GetMassageSeleectEmployee(),
                "Please select employee",
                "Success Select employee");
    }

    @Test(priority=8)
    public void verifyCancelEmployeeTest() {
        loginValid();
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        trainingPage.clickDetailButton();

        AssignedEmployeeTrainingPage assignedEmployeeTrainingPage = new AssignedEmployeeTrainingPage(DriverManager.getDriver());
        assignedEmployeeTrainingPage.clickAssignedEmployeeTab();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        assignedEmployeeTrainingPage.clickAssignEmployeeButton();
        assignedEmployeeTrainingPage.setStartDate("01012026");
        assignedEmployeeTrainingPage.setDeadlineDate("02022026");
        assignedEmployeeTrainingPage.clickCancel();

        Assert.assertEquals(assignedEmployeeTrainingPage.getAssignedEmployeeListText(),
                "Assigned Employee");
    }


}