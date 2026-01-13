package pages.employee;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

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
        editEmployeeNameInput.click();
        editEmployeeNameInput.sendKeys(Keys.CONTROL + "a");
        editEmployeeNameInput.sendKeys(Keys.DELETE);
        editEmployeeNameInput.sendKeys(name);
    }

    public void editEmployeeEmail(String email) {
        editEmployeeEmailInput.click();
        editEmployeeEmailInput.sendKeys(Keys.CONTROL + "a");
        editEmployeeEmailInput.sendKeys(Keys.DELETE);
        editEmployeeEmailInput.sendKeys(email);
    }

    public void editEmployeeId(String id) {
        editEmployeeIdInput.click();
        editEmployeeIdInput.sendKeys(Keys.CONTROL + "a");
        editEmployeeIdInput.sendKeys(Keys.DELETE);
        editEmployeeIdInput.sendKeys(id);
    }

    public void editEmployeePhone(String phone) {
        editEmployeePhoneInput.click();
        editEmployeePhoneInput.sendKeys(Keys.CONTROL + "a");
        editEmployeePhoneInput.sendKeys(Keys.DELETE);
        editEmployeePhoneInput.sendKeys(phone);
    }

    // Method untuk edit employee lengkap
    public void editEmployee(String name, String email, String employeeId, String phoneNumber) {
        editEmployeeName(name);
        editEmployeeEmail(email);
        editEmployeeId(employeeId);
        editEmployeePhone(phoneNumber);
    }

    public void saveChanges() {
        editEmployeeSubmitButton.click();
    }
}