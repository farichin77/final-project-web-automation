package pages.training;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignedEmployeeTrainingPage extends BasePage {
    public AssignedEmployeeTrainingPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "tabs-training-detail--tab-1")
    private WebElement assignedEmployeeTab;

    @FindBy(id = "assign-employee-button")
    private WebElement assignEmployeeButton;

    @FindBy(xpath = "//table//tr//button[contains(@class,'css-vc09vw')]")
    private WebElement buttonCheck;

    @FindBy(xpath = "//p[normalize-space()='Start Date']/following-sibling::input[@type='date']")
    private WebElement startDateInput;

    @FindBy(xpath = "//p[normalize-space()='Deadline Date']/following-sibling::input[@type='date']")
    private WebElement deadlineDateInput;

    @FindBy(xpath = "//button[normalize-space()='Assign Employee']")
    private WebElement saveEmployeeButton;

    @FindBy(xpath = "//p[contains(text(),'Success assign employee')]")
    private WebElement successAssignEmployeeMessage;

    @FindBy(xpath = "//p[contains(text(),'The end date must be later than the start date.')]")
    private WebElement invalidDateMessage;

    @FindBy(xpath = "//p[contains(text(),'Please select employee')]")
    private WebElement selectEmployeeMessage;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelButton;



    public void clickAssignedEmployeeTab() {
        click(assignedEmployeeTab);
    }

    public void clickAssignEmployeeButton() {
        click(assignEmployeeButton);
    }

    public void clickButtonCheck() {
        waitForVisibility(buttonCheck);
        click(buttonCheck);
    }
    public void setStartDate(String date) {
        clearAndType(startDateInput, date);
    }

    public void setDeadlineDate(String date) {
        clearAndType(deadlineDateInput, date);
    }


    public void clickSaveAssignEmployee() {
        click(saveEmployeeButton);
    }

    public String getSuccessMessageAssignEmployee(){
        return successAssignEmployeeMessage.getText();
    }

    public String getInvalidDateMessage(){
        return invalidDateMessage.getText();
    }

    public String GetMassageSeleectEmployee(){
        return selectEmployeeMessage.getText();
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public String getAssignedEmployeeListText() {
        waitForVisibility(assignedEmployeeTab);
        return assignedEmployeeTab.getText();
    }



}
