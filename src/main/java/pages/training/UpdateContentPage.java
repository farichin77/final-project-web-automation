package pages.training;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateContentPage extends BasePage {
    public UpdateContentPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "button-update-content")
    private WebElement editContentButton;

    @FindBy(id = "input-title-content")
    private WebElement updateTitleField;

    @FindBy(xpath = "//div[@role='textbox' and @aria-label='Editor editing area: main']")
    private WebElement updateDescriptionField;

    @FindBy(xpath = "//*[name()='svg' and contains(@class,'iconify--bx')]")
    private WebElement editUploadVideoButton;

    @FindBy(css = "div.css-14t4u77")
    private WebElement chooseVideoButton;

    @FindBy(id = "input-estimated-video-duration-content")
    private WebElement videoDurationField;

    @FindBy(id = "input-read-duration-content")
    private WebElement readDurationInput;

    @FindBy(id = "input-test-duration-content")
    private WebElement testDurationInput;


    @FindBy(id = "modal-update-content-save-button")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[contains(., 'Delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//p[contains(text(),'Success update content')]")
    private WebElement successUpdateContentMessage;

    @FindBy(xpath = "//p[text()='Only get certain number of questions']")
    private WebElement maxQuestionCheckbox;


    public void clickEditContentButton() {
        waitMillis(3000);
        click(editContentButton);
    }
    public void updateTitle(String contentName) {
        clearAndType(updateTitleField, contentName);
    }
    public void updateDescription(String description) {
        clearAndType(updateDescriptionField, description);
    }

    public void updateReadDuration(String readDuration) {
        clearAndType(readDurationInput, readDuration);
    }

    public void updateTestDuration(String testDuration) {
        clearAndType(testDurationInput, testDuration);
    }

    public void clickEditUploadVideoButton() {
        click(editUploadVideoButton);
    }
    public void clickChooseVideoButton() {
        click(chooseVideoButton);
    }


    public void updateVideoDuration(String duration) {
        clearAndType(videoDurationField, duration);
    }
    public void clickSaveChangesButton() {
        click(saveChangesButton);
    }
    public void clickDeleteButton() {
        click(deleteButton);
    }
    public String getSuccessUpdateContentMessage() {
        return getText(successUpdateContentMessage);
    }

    public void checkBoxQuestionLimit() {
        click(maxQuestionCheckbox);
    }

}
