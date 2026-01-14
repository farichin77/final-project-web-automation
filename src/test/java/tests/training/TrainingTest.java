package tests.training;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.training.ContentPage;
import pages.training.ManageTrainingPage;
import pages.training.ProgramListTrainingPage;
import pages.training.UpdateContentPage;
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

    @Test(dataProvider = "addChapter",
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
    @Test(dataProvider = "editChapter",
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

    @Test(dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithVideoTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickAddContentButton();
        contentPage.selectVideoOption();
        contentPage.clickTitleContent(contentName);
        contentPage.clickContentDescription(description);
        contentPage.uploadMediaButton();
        contentPage.selectMediaFile();
        contentPage.clickChooseMediaButton();
        contentPage.enterEstimatedVideoDuration(duration);
        contentPage.clickSubmitContentButton();

        Assert.assertEquals(contentPage.getSuccessCreateContentMessage(),
                "Success create content",
                "Success add content message should be displayed");
    }

    @Test(dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithArticleTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickAddContentButton();
        contentPage.selectArticleOption();
        contentPage.clickTitleContent(contentName);
        contentPage.clickContentDescription(description);
        contentPage.uploadMediaButton();
        contentPage.selectArticleFile();
        contentPage.clickChooseMediaButton();
        contentPage.enterReadDuration(duration);
        contentPage.clickSubmitContentButton();

        Assert.assertEquals(contentPage.getSuccessCreateContentMessage(),
                "Success create content",
                "Success add content message should be displayed");
   }

    @Test(dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithTestTypeTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();
        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickAddContentButton();
        contentPage.selectTestOption();
        contentPage.clickTitleContent(contentName);
        contentPage.clickContentDescription(description);
        contentPage.enterTestDuration(duration);
        contentPage.checklistShuffleQuestion();
        contentPage.clickSubmitContentButton();

        Assert.assertEquals(contentPage.getSuccessCreateContentMessage(),
                "Success create content",
                "Success add content message should be displayed");
    }

    @Test(dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentVideoTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickDetailVideoButton();

       UpdateContentPage updateContentPage = new UpdateContentPage(DriverManager.getDriver());
       updateContentPage.clickEditContentButton();
       updateContentPage.updateTitle(contentName);
       updateContentPage.updateDescription(description);
       updateContentPage.updateVideoDuration(duration);
       updateContentPage.clickSaveChangesButton();

       Assert.assertEquals(updateContentPage.getSuccessUpdateContentMessage(),
               "Success update content",
               "Success update content message should be displayed");

    }

    @Test(dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentArticleTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining("Basic Software Testing");
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickDetailArticleButton();

        UpdateContentPage updateContentPage = new UpdateContentPage(DriverManager.getDriver());
        updateContentPage.clickEditContentButton();
        updateContentPage.updateTitle(contentName);
        updateContentPage.updateDescription(description);
        updateContentPage.updateReadDuration(duration);
        updateContentPage.clickSaveChangesButton();

        Assert.assertEquals(updateContentPage.getSuccessUpdateContentMessage(),
                "Success update content",
                "Success update content message should be displayed");
    }
    @Test(dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentTestTypeTest(String contentName, String description, String duration) {
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
        updateContentPage.updateTitle(contentName);
        updateContentPage.updateDescription(description);
        updateContentPage.updateTestDuration(duration);
        updateContentPage.checkBoxQuestionLimit();
        updateContentPage.clickSaveChangesButton();

        Assert.assertEquals(updateContentPage.getSuccessUpdateContentMessage(),
                "Success update content",
                "Success update content message should be displayed");
    }

}