package tests.training;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.training.*;
import utils.ExcelDataProvider;

import java.util.List;

public class ContentTest extends BaseTest {
    @Test(  priority = 1,
            dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithVideoTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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

    @Test(  priority = 2,
            dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithArticleTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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

    @Test(  priority = 3,
            dataProvider = "addContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addContentWithTestTypeTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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

    @Test(  priority = 4,
            dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentVideoTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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

    @Test(  priority = 5,
            dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentArticleTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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
    @Test(  priority =6,
            dataProvider = "editContent",
            dataProviderClass = ExcelDataProvider.class
    )
    public void editContentTestTypeTest(String contentName, String description, String duration) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
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

    @Test(
            priority = 7,
            dataProvider = "addQuestion",
            dataProviderClass = ExcelDataProvider.class
    )
    public void addQuestionTest(
            String question,
            String answer1,
            String answer2,
            String answer3,
            String answer4,
            String correctAnswer
    ) {

        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickDetailTestButton();

        AddQuestionPage addQuestionPage = new AddQuestionPage(DriverManager.getDriver());
        addQuestionPage.clickAddQuestion();
        addQuestionPage.selectMultipleSelection();
        addQuestionPage.inputQuestion(question);
        addQuestionPage.inputAnswers(List.of(answer1, answer2, answer3, answer4));
        addQuestionPage.selectCorrectAnswer(correctAnswer);
        addQuestionPage.clickSaveChanges();

        Assert.assertEquals(addQuestionPage.getSuccessSaveQuestion(),
                "Success save question",
                "doesnt save question");


    }

    @Test(
            priority = 8,
            dataProvider = "addQuestion",
            dataProviderClass = ExcelDataProvider.class
    )
    public void deleteQuestionTest(
            String question,
            String answer1,
            String answer2,
            String answer3,
            String answer4,
            String correctAnswer
    ) {

        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickTrainingMenu();

        ManageTrainingPage trainingPage = new ManageTrainingPage(DriverManager.getDriver());
        trainingPage.searchTraining(browserName);
        trainingPage.clickDetailButton();

        ProgramListTrainingPage programListTrainingPage = new ProgramListTrainingPage(DriverManager.getDriver());
        programListTrainingPage.clickChapter1Item();

        ContentPage contentPage = new ContentPage(DriverManager.getDriver());
        contentPage.clickDetailTestButton();

        AddQuestionPage addQuestionPage = new AddQuestionPage(DriverManager.getDriver());
        addQuestionPage.clickAddQuestion();
        addQuestionPage.selectMultipleSelection();
        addQuestionPage.inputQuestion(question);
        addQuestionPage.inputAnswers(List.of(answer1, answer2, answer3, answer4));
        addQuestionPage.selectCorrectAnswer(correctAnswer);
        addQuestionPage.clickDeleteQuestion();
        addQuestionPage.confirmDeleteQuestion();

        Assert.assertEquals(addQuestionPage.getSuccessDeleteQuestion(),
                "Success delete question",
                "doesnt delete question");


    }
}
