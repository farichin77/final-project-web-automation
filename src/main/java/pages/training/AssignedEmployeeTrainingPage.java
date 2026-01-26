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

    @FindBy(xpath = "//input[@placeholder='Search name, ID...']")
    private WebElement searchInput;

    @FindBy(xpath = "//table//tr[1]//button")
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
        waitMillis(2000);
    }

    public void searchEmployee(String keyword) {
        clearAndType(searchInput, keyword);
        waitMillis(2000); // Critical for table filtering to finalize in CI
    }

    public void clickButtonCheck() {
        waitMillis(1000);
        scrollToElement(buttonCheck);
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
        return getText(successAssignEmployeeMessage);
    }

    public String getInvalidDateMessage() {
        return getText(invalidDateMessage);
    }

    public String GetMassageSelectEmployee() {
        return getText(selectEmployeeMessage);
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public String getAssignedEmployeeListText() {
        return getText(assignedEmployeeTab);
    }

    public void clickDetailAssignedEmployee() {
        waitMillis(1000); // Give time for list to finalize in Firefox
        waitForVisibility(btnDetailEmployee);
        click(btnDetailEmployee);
    }

    public void clickEditDetailAssigned() {
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
        return getText(successDeleteUserProgram);
    }

    public String getMessageUpdateAssignedEmployee() {
        return getText(successUpdateAssignedEmployeeMessage);
    }

}
