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

    @FindBy(id = "detail-assigned-employee-button")
    private WebElement btnDetailEmployee;

    @FindBy(id = "button-update-assigned-employee")
    private WebElement btnUpdateAssignedEmployee;

    @FindBy(id = "endDate")
    private WebElement inputDeadlineDate;

    @FindBy(id = "modal-update-assigned-employee-save-button")
    private WebElement btnSaveUserProgram;

    @FindBy(xpath = "//p[contains(text(),'Success update assigned employee')]")
    private WebElement successUpdateAssignedEmployeeMessage;

    @FindBy(id = "button-delete-assigned-employee")
    private WebElement btnDeleteAssignedEmployee;

    @FindBy(id = "modal-delete-assigned-employee-delete-button")
    private WebElement btnConfirmDelete;

    @FindBy(xpath = "//p[contains(text(),'Success delete Assigned Employee')]")
    private WebElement successDeleteUserProgram;


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
        setDate(startDateInput, date);
    }

    public void setDeadlineDate(String date) {
        setDate(deadlineDateInput, date);
    }

    public void clickSaveAssignEmployee() {
        click(saveEmployeeButton);
    }

    public String getSuccessMessageAssignEmployee() {
        waitForVisibility(successAssignEmployeeMessage);
        return successAssignEmployeeMessage.getText();
    }

    public String getInvalidDateMessage() {
        return invalidDateMessage.getText();
    }

    public String GetMassageSeleectEmployee() {
        return selectEmployeeMessage.getText();
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public String getAssignedEmployeeListText() {
        waitForVisibility(assignedEmployeeTab);
        return assignedEmployeeTab.getText();
    }

    public void clickDetailAssignedEmployee() {
        waitForVisibility(btnDetailEmployee);
        click(btnDetailEmployee);
    }

    public void clickEditDetailAssigned() {
        waitMillis(500);
        waitForVisibility(btnUpdateAssignedEmployee);
        click(btnUpdateAssignedEmployee);
    }

    public void editDeadlineDate(String date) {
        setDate(inputDeadlineDate, date);
    }

    public void clickSaveUserProgram() {
        click(btnSaveUserProgram);
    }

    public void clickDeleteUserProgram(){
        waitForVisibility(btnDeleteAssignedEmployee);
        click(btnDeleteAssignedEmployee);
    }

    public void buttonDeleteConfirm(){
        click(btnConfirmDelete);
    }

    public String getMessageSuccessDeleteAssignedEmployee (){
        waitForVisibility(successDeleteUserProgram);
        return successDeleteUserProgram.getText();
    }

    public String getMessageUpdateAssignedEmployee() {
        waitForVisibility(successUpdateAssignedEmployeeMessage);
        return successUpdateAssignedEmployeeMessage.getText();
    }

}
