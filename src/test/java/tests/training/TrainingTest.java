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
        trainingPage.enterTrainingName(trainingName + " " + browserName);
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
        trainingPage.searchTraining("Basic Software Testing " + browserName);
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.updateTrainingButton();
        programListTrainingPage.updateTrainingNameField(trainingName + " " + browserName);
        programListTrainingPage.updateTrainingDescriptionField(description);
        programListTrainingPage.submitUpdateTraining();

        Assert.assertEquals(programListTrainingPage.getSuccessUpdateTrainingMessage(),
                "Success update program",
                "Success update program message should be displayed");
    }

}