package pages.employee;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class EditEmployeePage extends BasePage {
    public EditEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "edit-employee-name-input")
    private WebElement editEmployeeNameInput;

    @FindBy(id="edit-employee-email-input")
    private WebElement editEmployeeEmailInput;

    @FindBy(id ="edit-employee-employee-id-input")
    private WebElement editEmployeeIdInput;

    @FindBy(id ="edit-employee-phone-number-input")
    private WebElement editEmployeePhoneInput;

    @FindBy(id="edit-employee-save-changes-button")
    private WebElement editEmployeeSubmitButton;

    public void editEmployeeName(String name) {
      clearAndType(editEmployeeNameInput, name);
    }

    public void editEmployeeEmail(String email) {
        clearAndType(editEmployeeEmailInput, email);
    }

    public void editEmployeeId(String id) {
    clearAndType(editEmployeeIdInput, id);
    }

    public void editEmployeePhone(String phone) {
   clearAndType(editEmployeePhoneInput, phone);
    }

    public void saveChanges() {
        editEmployeeSubmitButton.click();
    }
}