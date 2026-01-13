package pages.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DetailEmployeePage extends BasePage {
    public DetailEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "edit-employee-button")
    WebElement editEmployeeDetailButton;

    @FindBy(id = "delete-employee-button")
    WebElement deleteEmployeeButton;

    @FindBy(id = "confirm-delete-button")
    WebElement confirmDeleteButton;

    @FindBy(id = "activation-employee-button")
    WebElement activationEmployeeButton;

    @FindBy(id = "activation-employee-confirm-button")
    WebElement activationEmployeeConfirmButton;

    @FindBy(xpath = "//p[contains(text(),'Succes inactivate employee account')]")
    WebElement successInactivateEmployeeText;

    @FindBy(xpath = "//p[contains(text(),'Succes activate employee account')]")
    WebElement successActivateEmployeeText;

    @FindBy(id = "tabs-employee-detail--tab-1")
    WebElement assignedProgramsTab;

    @FindBy(xpath = "//p[text()='Name']")
    WebElement verifyNameText;

    @FindBy(xpath = "//p[contains(text(),'Succes delete employee')]")
    WebElement successDeleteEmployeeText;

    @FindBy(xpath = "//p[contains(text(),'Success Resend Email')]")
    WebElement successResendEmailText;

    @FindBy(id = "resend-email-button")
    WebElement resendEmailButton;

    @FindBy(id = "resend-email-confirm-button")
    WebElement resendEmailConfirmButton;

    public void clickEditEmployeeDetail() {
        editEmployeeDetailButton.click();
    }

    public void clickDeleteEmployee() {
        deleteEmployeeButton.click();

    }

    public void clickActivationEmployee() {
        activationEmployeeButton.click();
    }

    public void clickActivationEmployeeConfirm() {
        activationEmployeeConfirmButton.click();
    }

    public String getSuccessInactivateEmployeeText() {
        return successInactivateEmployeeText.getText();
    }

    public String getSuccessActivateEmployeeText() {
        return successActivateEmployeeText.getText();
    }

    public void clickAssignedProgramsTab() {
        assignedProgramsTab.click();
    }

    public String getVerifyNameText() {
        return verifyNameText.getText();
    }

    public void clickConfirmDeleteButton() {
        confirmDeleteButton.click();
    }

    public String getSuccessDeleteEmployeeText() {
        return successDeleteEmployeeText.getText();
    }

    public void clickResendEmailButton() {
        resendEmailButton.click();
    }

    public void clickResendEmailConfirmButton() {
        resendEmailConfirmButton.click();
    }

    public String getSuccessResendEmailText() {
        return successResendEmailText.getText();
    }
}
