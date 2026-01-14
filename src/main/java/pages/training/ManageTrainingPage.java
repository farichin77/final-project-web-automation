package pages.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class ManageTrainingPage extends BasePage {
    public ManageTrainingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id ="add-training-button")
    private WebElement addTrainingButton;

    @FindBy(xpath = "//button[contains(@id,'button-detail-training')]")
    private WebElement detailButton;

    @FindBy(id="title")
    private WebElement titleField;

    @FindBy(id="description")
    private WebElement descriptionField;

    @FindBy(id="add-training-submit-button")
    private WebElement submitButton;

    @FindBy(xpath = "//p[contains(text(),'Success create program')]")
    private WebElement successCreateTrainingMessage;

    @FindBy(id ="search-training-input")
    private WebElement searchTrainingInput;



    public void clickAddTrainingButton() {
        addTrainingButton.click();
    }

    public void clickDetailButton() {
        // click() di BasePage sudah kita setting pakai ExpectedConditions.elementToBeClickable
        click(detailButton);
    }

    public void enterTrainingName(String trainingName) {
        clearAndType(titleField, trainingName);

    }
    public void enterDescription(String description) {
        clearAndType(descriptionField, description);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }


    public String getSuccessCreateTrainingMessage() {
        return successCreateTrainingMessage.getText();
    }

    public void searchTraining(String trainingName) {
        searchTrainingInput.sendKeys(trainingName);
        waitMillis(500);
    }


}
