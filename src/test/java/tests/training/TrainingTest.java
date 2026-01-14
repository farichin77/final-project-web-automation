package tests.training;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.training.ManageTrainingPage;
import pages.training.ProgramListTrainingPage;
import utils.ExcelDataProvider;
import core.DriverManager;

public class TrainingTest extends BaseTest {
    @Test(dataProvider = "addTraining",
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

    @Test(dataProvider = "editTraining",
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
    @Test (dataProvider = "addChapter",
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

}