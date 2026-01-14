package pages.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class ProgramListTrainingPage extends BasePage {

    public ProgramListTrainingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id ="update-training-button")
    private WebElement updateTrainingButton;

    @FindBy(id ="title")
    private WebElement titleField;

    @FindBy(id="description")
    private WebElement descriptionField;

    @FindBy(id ="update-training-submit-button")
    private WebElement submitUpdateButton;

    @FindBy(xpath = "//p[contains(text(),'Success update program')]")
    private WebElement successUpdateTrainingMessage;

    @FindBy(xpath = "//button[@id='add-chapter-icon-button']//*[name()='svg']")
    private WebElement addChapterIcon;

    @FindBy(id ="title")
    private WebElement chapterNameField;

    @FindBy(id="description")
    private WebElement chapterDescriptionField;

    @FindBy(id="add-chapter-submit-button")
    private WebElement submitAddChapterButton;

    @FindBy(xpath = "//p[contains(text(),'Success create chapter')]")
    private WebElement successAddChapterMessage;

    public void updateTrainingButton(){
        waitMillis(1000);
        click(updateTrainingButton);

    }

    public void updateTrainingNameField(String trainingName) {
        clearAndType(titleField, trainingName);
    }
    public void updateTrainingDescriptionField(String Description) {
       clearAndType(descriptionField, Description);
    }

    public void submitUpdateTraining(){
        submitUpdateButton.click();
    }
    public String getSuccessUpdateTrainingMessage(){
        return successUpdateTrainingMessage.getText();
    }

    public void clickAddChapterIcon(){
        waitMillis(1000);
        click(addChapterIcon);
    }

    // Overloaded: click the field and fill with provided chapter name
    public void clickChapterNameField(String chapterName){
        clearAndType(chapterNameField, chapterName);
    }
    public void clickChapterDescriptionField(String Description){
        clearAndType(chapterDescriptionField,Description);
    }

    public void submitAddChapter(){
        click(submitAddChapterButton);
    }

    public String getSuccessAddChapterMessage() {
        return getText(successAddChapterMessage); // pakai BasePage method
    }
}
