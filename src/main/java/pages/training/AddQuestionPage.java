package pages.training;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddQuestionPage extends BasePage {

    public AddQuestionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "button-create-question")
    private WebElement addQuestion;

    @FindBy(xpath = "//span[normalize-space()='Single Selection']")
    private WebElement singleSelectionLabel;

    @FindBy(xpath = "//span[normalize-space()='Multiple Selection']")
    private WebElement multipleSelectionLabel;

    @FindBy(xpath = "//div[@contenteditable='true' and @role='textbox']")
    private WebElement inputQuestion;

    @FindBy(xpath = "//input[starts-with(@id,'input-answer-')]")
    private List<WebElement> answerInputs;

    @FindBy(id = "button-add-answer")
    private WebElement addAnswerButton;

    @FindBy(xpath = "//select[contains(@id, 'select-correct-answer')]")
    private WebElement correctAnswerSelect;


    @FindBy(id = "button-save-changes")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[normalize-space()='Delete Question']")
    private WebElement deleteQuestionButton;

    @FindBy(xpath = "//footer//button[normalize-space()='Delete']")
    private WebElement deleteConfirmationButton;

    @FindBy(xpath = "//p[contains(text(),'Success save question')]")
    private WebElement successSaveQuestionMessage;

    @FindBy(xpath = "//p[contains(text(),'Success delete question')]")
    private WebElement successDeleteQuestionMessage;


    public void clickAddQuestion() {
        click(addQuestion);
    }

    public void selectSingleSelection() {
        click(singleSelectionLabel);
    }

    public void selectMultipleSelection() {
        click(multipleSelectionLabel);
    }

    public void inputQuestion(String question) {
        click(inputQuestion);
        inputQuestion.sendKeys(question);
    }

    public void clickAddAnswer() {
        click(addAnswerButton);
    }

    public void inputFirstAnswer(String text) {
        answerInputs.get(0).sendKeys(text);
    }

    public void addAnswer(String text) {
        clickAddAnswer();
        answerInputs.get(answerInputs.size() - 1).sendKeys(text);
    }

    public void inputAnswers(List<String> answers) {
        inputFirstAnswer(answers.get(0));

        for (int i = 1; i < answers.size(); i++) {
            addAnswer(answers.get(i));
        }
    }


    public void selectCorrectAnswer(String text) {
        scrollToElement(correctAnswerSelect);
        waitForVisibility(correctAnswerSelect);
        Select select = new Select(correctAnswerSelect);
        select.selectByVisibleText(text);
    }



    public void clickSaveChanges() {
        click(saveChangesButton);
    }

    public void clickDeleteQuestion(){
        click(deleteQuestionButton);
    }

    public void confirmDeleteQuestion(){
        click(deleteConfirmationButton);
    }

    public String getSuccessSaveQuestion(){
        waitForVisibility(successSaveQuestionMessage);
        return successSaveQuestionMessage.getText();
    }

    public String getSuccessDeleteQuestion(){
        waitForVisibility(successDeleteQuestionMessage);
        return successDeleteQuestionMessage.getText();
    }
}




