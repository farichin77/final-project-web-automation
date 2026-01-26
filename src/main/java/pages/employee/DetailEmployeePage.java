package pages.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

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
       click (editEmployeeDetailButton);
    }

    public void clickDeleteEmployee() {
        click(deleteEmployeeButton);

    }

    public void clickActivationEmployee() {
        click(activationEmployeeButton);
    }

    public void clickActivationEmployeeConfirm() {
        click(activationEmployeeConfirmButton);
    }

    public String getSuccessInactivateEmployeeText() {
        return getText(successInactivateEmployeeText);
    }

    public String getSuccessActivateEmployeeText() {
        return getText(successActivateEmployeeText);
    }

    public void clickAssignedProgramsTab() {
        click(assignedProgramsTab);
    }

    public String getVerifyNameText() {
        return getText(verifyNameText);
    }

    public void clickConfirmDeleteButton() {
        click(confirmDeleteButton);
    }

    public String getSuccessDeleteEmployeeText() {
        return getText(successDeleteEmployeeText);
    }

    public void clickResendEmailButton() {
        click(resendEmailButton);
    }

    public void clickResendEmailConfirmButton() {
        click(resendEmailConfirmButton);
    }

    public String getSuccessResendEmailText() {
        return getText(successResendEmailText);
    }
}
