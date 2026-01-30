package pages.training;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AssignedEmployeeTrainingPage extends BasePage {
    public AssignedEmployeeTrainingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "tabs-training-detail--tab-1")
    private WebElement assignedEmployeeTab;

    @FindBy(xpath= "//button[@id='assign-employee-button']")
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
        waitForVisibility(assignEmployeeButton);
        click(assignEmployeeButton);

    }

    public void searchEmployee(String keyword) {
        clearAndType(searchInput, keyword);
        waitForTableToLoad();
        waitForEmployeeInTable(keyword);
        System.out.println("Employee search completed for: " + keyword);
    }

    public void clickButtonCheck(){
        clickButtonInTable(buttonCheck);
    }

    public void setStartDate(String date) {
        setDate(startDateInput, date);
    }

    public void setDeadlineDate(String date) {
        setDate(deadlineDateInput, date);
    }

    public void clickSaveAssignEmployee() {
        waitForVisibility(saveEmployeeButton);
        click(saveEmployeeButton);
    }

    public String getSuccessMessageAssignEmployee() {
        waitForVisibility(successAssignEmployeeMessage);
        return getText(successAssignEmployeeMessage);
    }

    public String getInvalidDateMessage() {
        waitForVisibility(invalidDateMessage);
        return getText(invalidDateMessage);
    }

    public String GetMassageSelectEmployee() {
        waitForVisibility(selectEmployeeMessage);
        return getText(selectEmployeeMessage);
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public String getAssignedEmployeeListText() {
        waitForVisibility(assignedEmployeeTab);
        return getText(assignedEmployeeTab);
    }

    public void clickDetailAssignedEmployee() {
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
        waitForVisibility(successDeleteUserProgram);
        return getText(successDeleteUserProgram);
    }

    public String getMessageUpdateAssignedEmployee() {
        waitForVisibility(successUpdateAssignedEmployeeMessage);
        return getText(successUpdateAssignedEmployeeMessage);
    }
}
