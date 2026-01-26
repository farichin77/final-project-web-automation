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