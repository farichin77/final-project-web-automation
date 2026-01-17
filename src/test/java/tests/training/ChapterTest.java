package tests.training;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.training.ManageTrainingPage;
import pages.training.ProgramListTrainingPage;
import utils.ExcelDataProvider;

public class ChapterTest extends BaseTest {

    @Test(  priority = 1,
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
    @Test(  priority = 2,
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

}

