package pages.training;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContentPage extends BasePage {
    public ContentPage(WebDriver driver) {
        super(driver);
    }

    //add content
    @FindBy(xpath = "//button[contains(text(), 'Add Content')]")
    private WebElement addContentButton;

    @FindBy(xpath = "//span[text()='Video']")
    private WebElement videoOption;

    @FindBy(xpath = "//span[contains(@class,'chakra-radio__label') and text()='Article']")
    private WebElement articleOption;

    @FindBy(xpath = "//span[text()='Test']")
    private WebElement testOption;

    @FindBy(xpath = "//input[@placeholder='Content title']")
    private WebElement titleContent;

    @FindBy(css = "div.ck.ck-editor__editable[contenteditable='true']")
    private WebElement contentDescription;

    @FindBy(id="button-upload-media")
    private WebElement uploadMediaButton;

    @FindBy(xpath = "//div[contains(@class,'css-14t4u77') or contains(text(), 'Choose Media')]")
    private WebElement selectMediaFile;

    @FindBy(xpath = "//div[contains(@class,'css-14t4u77') or contains(text(), 'Choose Article')]")
    private WebElement selectArticleFile;


    @FindBy(xpath = "//button[text()='Choose Media']")
    private WebElement chooseMediaButton;

    @FindBy(xpath = "//input[@placeholder='Estimated Video Duration']")
    private WebElement estimatedVideoDurationInput;

    @FindBy(xpath = "//input[@placeholder='Read Duration']")
    private WebElement readDurationInput;

    @FindBy(xpath = "//input[@placeholder='Test Duration']")
    private WebElement testDurationInput;

    @FindBy(xpath ="//label[.//p[text()='Shuffle Question']]")
    private WebElement shuffleQuestionCheckbox;


    @FindBy(xpath = "//button[starts-with(@id, 'submit-button-') and text()='Add Content']")
    private WebElement submitContentButton;

    @FindBy(xpath = "//p[contains(text(),'Success create content')]")
    private WebElement successCreateContentMessage;

    // edit content

    @FindBy(xpath ="//tr[td/span[text()='video']]//button[text()='Detail']")
    private WebElement detailVideoButton;

    @FindBy(xpath ="//tr[td/span[text()='article']]//button[text()='Detail']")
    private WebElement detailArticleButton;

    @FindBy(xpath ="//tr[td/span[text()='test']]//button[text()='Detail']")
    private WebElement detailTestButton;


    public void clickAddContentButton() {
        waitForVisibility(addContentButton);
        click(addContentButton);
    }


    public void selectVideoOption() {
        click(videoOption);
    }
    public void selectArticleOption() {
        click(articleOption);
    }
    public void selectTestOption() {
        click(testOption);
    }

    public void clickTitleContent(String contentName) {
      clearAndType(titleContent, contentName);
    }

    public void clickContentDescription(String description) {
        clearAndType(contentDescription, description);
    }

    public void uploadMediaButton() {
        click(uploadMediaButton);
    }
    public void selectMediaFile() {
        waitForVisibility(selectMediaFile);
        click(selectMediaFile);
    }

    public void selectArticleFile() {
        waitForVisibility(selectArticleFile);
        click(selectArticleFile);
    }

     public void clickChooseMediaButton() {
        click(chooseMediaButton);
    }
    public void enterEstimatedVideoDuration(String duration) {
        waitForVisibility(estimatedVideoDurationInput);
        clearAndType(estimatedVideoDurationInput, duration);
    }

    public void enterReadDuration(String duration) {
        waitForVisibility(readDurationInput);
        clearAndType(readDurationInput, duration);
    }

    public void enterTestDuration(String duration) {
        waitForVisibility(testDurationInput);
        clearAndType(testDurationInput, duration);
    }

    public void checklistShuffleQuestion() {
        click(shuffleQuestionCheckbox);
    }

    public void clickSubmitContentButton() {
        waitForVisibility(submitContentButton);
        click(submitContentButton);
    }

    public String getSuccessCreateContentMessage() {
        waitForVisibility(successCreateContentMessage);
        return getText(successCreateContentMessage);
    }

    public void clickDetailVideoButton() {
        click(detailVideoButton);
    }

    public void clickDetailArticleButton() {
        click(detailArticleButton);
    }

    public void clickDetailTestButton() {
        click(detailTestButton);
    }
}
